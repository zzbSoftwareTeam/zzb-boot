package com.zzb.module.im.controller;

import java.security.Principal;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.zzb.common.bean.SocketMessage;
import com.zzb.common.service.redis.RedisService;
import com.zzb.config.websocket.ClientPrincipal;

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
	private RedisService redisService;
    
    
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
    public void forwarding(StompHeaderAccessor accessor,ClientPrincipal principal, SocketMessage message) {
    	String sessionId = String.valueOf(accessor.getSessionAttributes().get("imSessionId"));
    	Boolean bool = redisService.containsMapKey(sessionId);
    	if(bool){
    		Map<String,Object> sesMap = redisService.getMap(sessionId);
    		message.setMessage("与客服"+String.valueOf(sesMap.get("services"))+"已建立连接");
    		message.setMsessionId(sessionId);
    		message.setStatus("one");
    		simpMessagingTemplate.convertAndSendToUser(principal.getName(),"/queue_im",message);
    	}else{
    		String str = redisService.getValue(principal.getName()+"status");
    		if(!str.equals("success")){
    			message.setMessage("当前等待人数为："+str);
        		simpMessagingTemplate.convertAndSendToUser(principal.getName(),"/queue_im",message);
    		}
    	}
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
    	message.setMessage("客服上线成功");
		simpMessagingTemplate.convertAndSendToUser(principal.getName(),"/queue_im",message);
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
    public void queueUser(ClientPrincipal principal, SocketMessage message) {
		String myUser = principal.getName();
		String sessionId = message.getMsessionId();
		Map<String,Object> sesMap = redisService.getMap(sessionId);
		if(sesMap!=null){
			if(StringUtils.equals(myUser, String.valueOf(sesMap.get("services")))){
				simpMessagingTemplate.convertAndSendToUser(String.valueOf(sesMap.get("clients")),"/queue_im",message);
			}
			if(StringUtils.equals(myUser, String.valueOf(sesMap.get("clients")))){
				simpMessagingTemplate.convertAndSendToUser(String.valueOf(sesMap.get("services")),"/queue_im",message);
			}
		}
	}
    
    /**
     * @Description: TODO 用户连上socket,回调给客服，让双方建立连接
     * @param principal
     * @param message 
     * @return void  
     * @author zengzhibin
     * @date 2018年9月14日
     */
    @MessageMapping("/user_callback")
    public void userCallback(StompHeaderAccessor accessor,ClientPrincipal principal, SocketMessage message) {
		String sessionId = message.getMsessionId();
		Map<String,Object> sesMap = redisService.getMap(sessionId);
		if(sesMap!=null){
			message.setStatus("one");
			message.setMessage("与用户"+String.valueOf(sesMap.get("clients")+"已建立连接"));
			simpMessagingTemplate.convertAndSendToUser(String.valueOf(sesMap.get("services")),"/queue_im",message);
		}else{
			message.setMessage("sessionId不存在");
			simpMessagingTemplate.convertAndSendToUser(principal.getName(),"/queue_im",message);
		}
	}
    
    /**
     * @Description: TODO 客服回调，同意让双方建立连接
     * @param principal
     * @param message 
     * @return void  
     * @author zengzhibin
     * @date 2018年9月14日
     */
   /* @MessageMapping("/service_callback")
    public void serviceCallback(StompHeaderAccessor accessor,ClientPrincipal principal,SocketMessage message) {
    	//同步双方的imsessionId;
    	String sessionId = message.getMsessionId();
    	Map<String,Object> sesMap = redisService.getMap(sessionId);
		if(sesMap!=null){
			accessor.getSessionAttributes().put("imSessionId", sessionId);
			
	    	message.setStatus("one");
			message.setMessage("与客服"+String.valueOf(sesMap.get("services")+"已建立连接"));
			simpMessagingTemplate.convertAndSendToUser(String.valueOf(sesMap.get("clients")),"/queue_im",message);
		}else{
			message.setMessage("sessionId不存在");
			simpMessagingTemplate.convertAndSendToUser(principal.getName(),"/queue_im",message);
		}
	}*/
    
    
    
    
    
	//广播模式下发送给定阅者  群聊
	/*@SubscribeMapping("/topic")
    public void forwarding(SocketMessage message) {
		System.out.println(message.getMessage());
		simpMessagingTemplate.convertAndSend("/topic/topic_forwarding",message);
	}*/
}
