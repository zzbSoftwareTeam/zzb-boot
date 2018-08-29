package com.zzb.module.im.controller;

import java.security.Principal;

import javax.jms.JMSException;
import javax.jms.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.zzb.common.bean.SocketMessage;
import com.zzb.common.service.activemq.ActiveMQService;
import com.zzb.common.service.redis.RedisService;

/**
 * 
 * ClassName: WebSocketController 
 * @Description: TODO 即时通讯处理Controller
 * @author zengzhibin
 * @date 2017年11月2日
 */
@Controller
public class WebSocketController {
	
	private Logger log =  LoggerFactory.getLogger(this.getClass());
	/*JMS template*/
	@Autowired
	private JmsTemplate jmsTemplate;
	
	/*stomp协义消息发送template*/
	@Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
	
	/*消息发送接收template*/
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	
	@Autowired
	private ActiveMQService activeMQService;
	@Autowired
	private RedisService redisService;
	
    @Autowired
    private Queue queueService;
    @Autowired
    private Queue queueUser;
    
    /**
     * 
     * @Description: TODO 用户接入通讯入口
     * @param principal
     * @param message 
     * @return void  
     * @author zengzhibin
     * @date 2017年11月2日
     */
    @MessageMapping("/queue_toUser")
    public void forwarding(Principal principal, SocketMessage message) {
		log.info(principal.getName()+"进入用户队列-----------------------");
		//用户接入队列等待
		jmsMessagingTemplate.convertAndSend(queueUser,principal.getName());
		long userint=1l;
		try {
			userint = activeMQService.getQueueSize(queueUser.getQueueName());
		}catch (Exception e) {
			e.printStackTrace();
		}
		message.setMessage("当前等待人数为："+userint);
		simpMessagingTemplate.convertAndSendToUser(principal.getName(),"/queue_im",message);
	}
    
    /**
     * 
     * @Description: TODO 客服接入通讯入口
     * @param principal
     * @param message 
     * @return void  
     * @author zengzhibin
     * @date 2017年11月2日
     */
    @MessageMapping("/queue_toService")
    public void queueService(Principal principal, SocketMessage message) {
		String goUser = principal.getName();
		String toUser = redisService.getValue(goUser+"socket");
		message.setMessage(toUser+"连接建立");
		simpMessagingTemplate.convertAndSendToUser(goUser,"/queue_im",message);
	}
    
    /**
     * 
     * @Description: TODO 监听用户队列消息
     * @param text 
     * @return void  
     * @author zengzhibin
     * @date 2017年11月3日
     */
    @JmsListener(destination = "queue_user")
    public void listenerQueue(String text){
    	SocketMessage message = new SocketMessage();
    	try{
			if(activeMQService.getQueueSize(queueService.getQueueName())>0){//客服队列有人匹配成功
				//判断队列中的用户是否已退出socket连接
				if(redisService.containsValueKey(text+"session")){
					if(!redisService.containsValueKey(text+"socket")){//判断是否已匹配客服
						Object sname=jmsMessagingTemplate.receiveAndConvert(queueService,String.class);
						log.info(text+"="+sname+"匹配聊天-----------------------");
						redisService.cacheValue(text+"socket", sname.toString());
						redisService.cacheValue(sname.toString()+"socket", text);
						message.setMessage(sname.toString()+"连接建立");
						simpMessagingTemplate.convertAndSendToUser(text,"/queue_im",message);
					}else{//已匹配客服不处理消息
						log.info(text+"已匹配客服不处理消息-----------------------");
					}
				}else{
					log.info(text+"已退出等待-----------------------");
				}
				
			}else{//客服队列无人匹配等待
				
				long userint = activeMQService.getQueueSize(queueUser.getQueueName());
				message.setMessage("当前等待人数为："+userint);
				simpMessagingTemplate.convertAndSendToUser(text,"/queue_im",message);
				log.info(text+"已在等待-----------------------");
				
				boolean bool = true;
				int ins = 0;
				while (bool) {
					long serviceint = activeMQService.getQueueSize(queueService.getQueueName());
					if(serviceint>0){
						bool = false;
						Object sname=jmsMessagingTemplate.receiveAndConvert(queueService,String.class);
						log.info(text+"="+sname+"匹配聊天-----------------------");
						redisService.cacheValue(text+"socket", sname.toString());
						redisService.cacheValue(sname.toString()+"socket", text);
						message.setMessage(sname.toString()+"连接建立");
						simpMessagingTemplate.convertAndSendToUser(text,"/queue_im",message);
					}else{
						Thread.sleep(3000);
						ins++;
					}
					if(ins%5==0){
						long userint1 = activeMQService.getQueueSize(queueUser.getQueueName());
						message.setMessage("当前等待人数为："+userint1);
						simpMessagingTemplate.convertAndSendToUser(text,"/queue_im",message);
					}
					log.info(ins+"=轮询客服队列-----------------------");
				}
				
				
			}
		}catch(Exception e){
			log.error(e.getMessage());
		}finally {
			
		}
    }
    
    /**
     * 
     * @Description: TODO 通讯消息转发器
     * @param principal
     * @param message 
     * @return void  
     * @author zengzhibin
     * @date 2017年11月2日
     */
    @MessageMapping("/queue_transfer")
    public void queueUser(Principal principal, SocketMessage message) {
    	log.info(principal.getName()+"======"+message.getMessage());
		String goUser = principal.getName();
		String toUser = redisService.getValue(goUser+"socket");
		simpMessagingTemplate.convertAndSendToUser(toUser,"/queue_im",message);
	}
    
   
	
	//广播模式下发送给定阅者
	/*@SubscribeMapping("/topic")
    public void forwarding(SocketMessage message) {
		System.out.println(message.getMessage());
		simpMessagingTemplate.convertAndSend("/topic/topic_forwarding",message);
	}*/
}
