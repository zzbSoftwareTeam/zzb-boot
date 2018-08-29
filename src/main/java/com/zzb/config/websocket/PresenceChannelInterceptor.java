package com.zzb.config.websocket;

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

@Component
public class PresenceChannelInterceptor extends ChannelInterceptorAdapter {
	
	private Logger log =  LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RedisService redisService;
	
	/*@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
		StompCommand command = accessor.getCommand();
		if (command == null) {
			return message;
		}
		// 这里对应HandshakeInterceptor拦截器的存放key
		String sessionId = accessor.getSessionAttributes().get("SESSIONID").toString();
		String userName = accessor.getSessionAttributes().get("USERNAME").toString();
		// 判断客户端的连接状态
		System.out.println("status=================="+accessor.getCommand());
		switch (command) {
		case CONNECT: //连接成功
			connect(sessionId, userName);
			break;
		case DISCONNECT: //连接断开
			disconnect(sessionId, userName, accessor);
			break;
		default:
			break;
		}
		return message;
	}*/
	
	@Override
	public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
		StompCommand command = accessor.getCommand();
		if (command == null) {
			return;
		}
		// 这里对应HandshakeInterceptor拦截器的存放key
		String sessionId = accessor.getSessionAttributes().get("SESSIONID").toString();
		String userName = accessor.getSessionAttributes().get("USERNAME").toString();
		// 判断客户端的连接状态
		log.info("status=================="+accessor.getCommand());
		switch (command) {
		case CONNECT: //连接成功
			connect(sessionId, userName);
			break;
		case DISCONNECT: //连接断开
			disconnect(sessionId, userName, accessor);
			break;
		default:
			break;
		}
	}

	// 连接成功
	private void connect(String sessionId, String userName) {
		redisService.cacheValue(userName+"session", sessionId);
	}

	// 断开连接
	private void disconnect(String sessionId, String userName, StompHeaderAccessor sha) {
		sha.getSessionAttributes().remove("SESSIONID");
		sha.getSessionAttributes().remove("USERNAME");
		// redis移除
		if(redisService.containsValueKey(userName+"session")){//是否连接信息
			redisService.removeValue(userName+"session");
		}
		if(redisService.containsValueKey(userName+"socket")){//是否匹配通讯信息
			redisService.removeValue(userName+"socket");
		}
		log.info(userName+"断开连接,已被移除redis");
	}

}
