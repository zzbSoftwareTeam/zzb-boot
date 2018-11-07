package com.zzb.config.websocket;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.stereotype.Component;

import com.zzb.common.service.redis.RedisService;
import com.zzb.common.utils.IdGenerateUtil;
import com.zzb.config.websocket.service.ArtiticiaImService;
import com.zzb.config.websocket.service.ClientImService;
import com.zzb.config.websocket.service.SocketMatchService;
import com.zzb.module.im.entity.ClientImUser;
import com.zzb.module.im.entity.ServiceImRedis;
import com.zzb.module.im.entity.ServiceImUser;

@Component
public class PresenceChannelInterceptor extends ChannelInterceptorAdapter {
	
	private Logger log =  LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RedisService redisService;
	@Autowired
	private ClientImService clientImService;
	@Autowired
	private ArtiticiaImService artiticiaImService;
	@Autowired
	private SocketMatchService socketMatchService;
	
	@Override
	public void postSend(Message<?> message, MessageChannel channel, boolean sent){
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
		StompCommand command = accessor.getCommand();
		if (command == null) {
			return;
		}
        
		// 这里对应HandshakeInterceptor拦截器的存放key
		Object serviceUser = accessor.getSessionAttributes().get("imServiceUser");
		Object clientUser =accessor.getSessionAttributes().get("imClientUser");
		 
		// 判断客户端的连接状态
		log.info("status=================="+accessor.getCommand());
		switch (command) {
		case CONNECT: //连接成功
			//判断就客服还是访客
			if(ObjectUtils.allNotNull(clientUser)){
				clientConnect(clientUser,accessor);
			}else if(ObjectUtils.allNotNull(serviceUser)){
				serviceConnect(serviceUser,accessor);
			}
			break;
		case SUBSCRIBE: //订阅成功
			break;
		case SEND: //发送成功
			break;
		case UNSUBSCRIBE: //退订成功
			break;
		case ACK: //消费成功
			break;
		case DISCONNECT: //连接断开
			//判断就客服还是访客
			if(ObjectUtils.allNotNull(clientUser)){
				disClientConnect(accessor);
			}else if(ObjectUtils.allNotNull(serviceUser)){
				disServiceConnect(serviceUser,accessor);
			}
			break;
		default:
			break;
		}
	}

	// 访客连接成功
	private void clientConnect(Object clientUser,StompHeaderAccessor sha) {
		if(clientUser!=null){
			ClientPrincipal principal = (ClientPrincipal) clientUser;
			
			//分配客服
			ServiceImUser service = artiticiaImService.get();
			if(service!=null){
				//建立会话redis存储
				Map<String,Object> map = new HashMap<String, Object>();
				String sessionId = IdGenerateUtil.uuid2();
				map.put("sessionId", sessionId);
				map.put("clients", principal.getName());
				map.put("services",service.getServiceName());
				redisService.cacheMap(sessionId, map);
				sha.getSessionAttributes().put("imSessionId",sessionId);
			}else{
				redisService.cacheValue(principal.getName()+"status", clientImService.queueSize().toString());
				ClientImUser clientImUser = new ClientImUser();
				clientImUser.setClientName(principal.getName());
				clientImUser.setLevel(1);
				clientImService.add(clientImUser);
				log.info(principal.getName()+"进入用户队列等待");
				
				//socketMatchService.match();
			}
		}
	}
	// 客服连接成功
	private void serviceConnect(Object serviceUserName, StompHeaderAccessor sha) {
		if(serviceUserName!=null){
			ServiceImRedis sBean = redisService.getObj(serviceUserName.toString());
			if(sBean==null){
				Integer level = 0;
				
				ServiceImRedis obj = new ServiceImRedis();
				obj.setServiceName(serviceUserName.toString());
				obj.setMaxCsu(2);//最大连接数
				obj.setSessionNum(level);//当前会话数
				obj.setAllotFlag(0);// 0可接入，1不可接入
				obj.setStatus(0); //0在线，1离线
				redisService.cacheObject(obj);
				
				ServiceImUser suser=new ServiceImUser();
				suser.setServiceName(serviceUserName.toString());
				suser.setLevel(level);
				artiticiaImService.add(suser);
				
			}else{
				Integer level = sBean.getSessionNum()>0?sBean.getSessionNum()-1:0;
				
				ServiceImUser suser=new ServiceImUser();
				suser.setServiceName(serviceUserName.toString());
				suser.setLevel(level);
				artiticiaImService.add(suser);
			}
		}
	}
		
	// 访客断开连接
	private void disClientConnect(StompHeaderAccessor sha) {
		String sessionId = sha.getSessionAttributes().get("imSessionId").toString();
		String userName = sha.getSessionAttributes().get("imClientUser").toString();
		// redis移除
		if(redisService.containsMapKey(sessionId)){//是否连接信息
			redisService.removeMap(sessionId);
			log.info("访客断开连接,session已被移除redis");
		}
		//在队列中移除
		ClientImUser clientImUser = new ClientImUser();
		clientImUser.setClientName(userName);
		clientImUser.setLevel(1);
		clientImService.remove(clientImUser);
		//推送消息给客服
		//。。。。
		//sha.updateStompCommandAsClientMessage();
	}
	
	// 客服断开连接
	private void disServiceConnect(Object serviceUserName, StompHeaderAccessor sha) {
		ServiceImRedis sBean = redisService.getObj(serviceUserName.toString());
		if(sBean!=null){
			Integer level = sBean.getSessionNum()>0?sBean.getSessionNum()-1:0;
			sBean.setSessionNum(level);
			redisService.cacheObject(sBean);
		}
		//推送消息给访客
		//。。。。
		//sha.updateStompCommandAsClientMessage();
	}

}
