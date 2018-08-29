package com.zzb.config.websocket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Component
public class MyHandshakeInterceptor extends HttpSessionHandshakeInterceptor {
	
	private Logger log =  LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		log.info("beforeHandshake===========");
		//解决The extension [x-webkit-deflate-frame] is not supported问题  
        if(request.getHeaders().containsKey("Sec-WebSocket-Extensions")) {  
            request.getHeaders().set("Sec-WebSocket-Extensions", "permessage-deflate");  
        }  
        //检查session的值是否存在  
        if (request instanceof ServletServerHttpRequest) {  
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;  
            HttpSession session = servletRequest.getServletRequest().getSession(false);  
            SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
            Authentication authentication=securityContext.getAuthentication();
            //把session和accountId存放起来  
            attributes.put("SESSIONID", session.getId());  
            attributes.put("USERNAME",authentication.getName());  
        }  
		return super.beforeHandshake(request, response, wsHandler, attributes);
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception ex) {
		log.info("afterHandshake===========");
		super.afterHandshake(request, response, wsHandler, ex);
	}
}
