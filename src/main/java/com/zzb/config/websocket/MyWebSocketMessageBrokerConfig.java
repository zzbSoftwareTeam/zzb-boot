package com.zzb.config.websocket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
@EnableWebSocketMessageBroker
// 通过EnableWebSocketMessageBroker 开启使用STOMP协议来传输基于代理(message
// broker)的消息,此时浏览器支持使用@MessageMapping 就像支持@RequestMapping一样。
public class MyWebSocketMessageBrokerConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Autowired
	private MyHandshakeHandler myHandshakeHandler;
	
	@Autowired
	private MyHandshakeInterceptor myHandshakeInterceptor;
	
	@Autowired
	private PresenceChannelInterceptor presenceChannelInterceptor;
	
	/** 
     * 连接的端点，客户端建立连接时需要连接这里配置的端点 
     */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) { // endPoint
        //为js客户端提供链接  
		registry.addEndpoint("/websocket")
        .setAllowedOrigins("*")  
        .setHandshakeHandler(myHandshakeHandler)  
        .addInterceptors(myHandshakeInterceptor)  
        .withSockJS(); 
	}

	/** 
     * applicationDestinationPrefixes应用前缀，所有请求的消息将会路由到@MessageMapping的controller上， 
     * enableStompBrokerRelay是代理前缀，而返回的消息将会路由到代理上，所有订阅该代理的将收到响应的消息。 
     *  
     */  
    @Override  
    public void configureMessageBroker(MessageBrokerRegistry registry) {  
        registry.setApplicationDestinationPrefixes("/ws");  
        registry.setUserDestinationPrefix("/user");  
        //registry.enableSimpleBroker("/topic", "/queue")  
        registry.enableStompBrokerRelay("/topic", "/queue")  
        //下面这配置为默认配置，如有变动修改配置启用
        .setRelayHost("127.0.0.1") //activeMq服务器地址  
        //.setRelayHost("120.78.165.88")
      	.setRelayPort(61613)//activemq 服务器服务端口  
      	.setClientLogin("mqAdmin")    //登陆账户  
     	.setClientPasscode("zzb123")
     	.setSystemLogin("mqAdmin")
     	.setSystemPasscode("zzb123")
     	.setSystemHeartbeatReceiveInterval(5000) // 设置心跳信息接收时间间隔
        .setSystemHeartbeatSendInterval(5000) // 设置心跳信息发送时间间隔
     	;  
    }  
      
    /** 
     * 消息传输参数配置 
     */  
    @Override  
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {  
    	//super.configureWebSocketTransport(registration);  
    	registration.setMessageSizeLimit(8192) //设置消息字节数大小
        .setSendBufferSizeLimit(8192)//设置消息缓存大小
        .setSendTimeLimit(10000); //设置消息发送时间限制毫秒 
    }  
      
    /** 
     * 输入通道参数设置 
     */  
    @Override  
    public void configureClientInboundChannel(ChannelRegistration registration) {  
    	//super.configureClientInboundChannel(registration);  
    	registration.setInterceptors(presenceChannelInterceptor)//传输通道拦截器，判断接连是否断开  
    	.taskExecutor().corePoolSize(10) //设置消息输入通道的线程池线程数
        .maxPoolSize(20)//最大线程数
        .keepAliveSeconds(60);//线程活动时间
    }  
    /** 
     * 输出通道参数配置 
     */  
    @Override  
    public void configureClientOutboundChannel(ChannelRegistration registration) {  
    	//super.configureClientOutboundChannel(registration);  
        //线程信息  
    	registration//.setInterceptors(presenceChannelInterceptor)//传输通道拦截器，判断接连是否断开  
    	.taskExecutor().corePoolSize(10).maxPoolSize(20);
    }  
      
    @Override  
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {  
    	//return super.configureMessageConverters(messageConverters);  
        return true;  
    }
    
    
    /**
     * 
     * @Description: TODO websocket注解引用Bean
     * @return ServerEndpointExporter  
     * @author zengzhibin
     * @date 2017年11月6日
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}