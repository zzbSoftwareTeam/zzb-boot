package com.zzb.config.websocket;

import java.security.Principal;
import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@Component
public class MyHandshakeHandler extends DefaultHandshakeHandler{
	/**
	 * 用户与WebSocket会话 的过程中被建立。
　　	 * 默认实现调用 { @link ServerHttpRequest # getPrincipal()}
　　	 * 自定义的逻辑关联用户会话
	 */
    @Override  
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,  
            Map<String, Object> attributes) {  
    	//设置认证通过的用户到当前会话中 
    	if(attributes.get("imClientUser")!=null){ //外部用户
            return (Principal)attributes.get("imClientUser");
    	}else{ //系统用户
    		return super.determineUser(request, wsHandler, attributes);  
    	}
    } 
}
