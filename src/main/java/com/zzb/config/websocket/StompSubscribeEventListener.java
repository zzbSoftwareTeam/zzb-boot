package com.zzb.config.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;  

/**
 * 
 * ClassName: StompSubscribeEventListener 
 * @Description: TODO 监听订阅地址的用户处理相关业务  SessionConnectEvent SessionDisconnectEvent
 * @author zengzhibin
 * @date 2017年11月3日
 */
@Component
public class StompSubscribeEventListener implements ApplicationListener<SessionSubscribeEvent> {

	private Logger log =  LoggerFactory.getLogger(this.getClass());

	@Override
	public void onApplicationEvent(SessionSubscribeEvent sessionSubscribeEvent) {
		//StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionSubscribeEvent.getMessage());
		log.info("监听订阅----------------");
	}
}
