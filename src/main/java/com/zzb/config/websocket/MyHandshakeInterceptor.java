package com.zzb.config.websocket;

import java.security.Principal;
import java.util.Enumeration;
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

import com.zzb.common.utils.IdGenerateUtil;


@Component
public class MyHandshakeInterceptor extends HttpSessionHandshakeInterceptor {
	
	private Logger log =  LoggerFactory.getLogger(this.getClass());

	/**
     * websocket握手连接
     * @return 返回是否同意握手
     */
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		log.info("-------------websocket拦截器--------------");
		
		//解决The extension [x-webkit-deflate-frame] is not supported问题 ，针对某些浏览器报错。
        if(request.getHeaders().containsKey("Sec-WebSocket-Extensions")) {  
            request.getHeaders().set("Sec-WebSocket-Extensions", "permessage-deflate");  
        }  
        if (request instanceof ServletServerHttpRequest) {  
        	
        	ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(true);  
            Enumeration<String> names = session.getAttributeNames();
			while (names.hasMoreElements()) {
				String name = names.nextElement();
				if (isCopyAllAttributes() || getAttributeNames().contains(name)) {
					attributes.put(name, session.getAttribute(name));
				}
			}
			
            SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
            if(securityContext!=null){//系统用户
	            Authentication authentication=securityContext.getAuthentication();
	            attributes.put("imServiceUser",authentication.getName());
	            return super.beforeHandshake(request, response, wsHandler, attributes);
	        }else{ //外部用户
	        	//通过url的query参数获取认证参数
	            String token = servletRequest.getServletRequest().getParameter("token");
	        	//根据token认证用户，不通过返回拒绝握手
                Principal clientUser = authenticate(token);
                if(clientUser == null){
                    return false;
                }
                //保存认证用户
                attributes.put("imClientUser", clientUser);
	            return super.beforeHandshake(request, response, wsHandler, attributes);
	        }
            
        }else{
        	return false;
        }
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception ex) {
		log.info("-------------websocket 握手成功--------------");
		super.afterHandshake(request, response, wsHandler, ex);
	}
	
	 /**
     * 根据token认证授权
     * @param token
     */
    private Principal authenticate(String token){
    	ClientPrincipal principal=new ClientPrincipal();
    	String clientUserId = String.valueOf(IdGenerateUtil.randomLong());
		principal.setName(clientUserId);
		return principal;
    }
}
