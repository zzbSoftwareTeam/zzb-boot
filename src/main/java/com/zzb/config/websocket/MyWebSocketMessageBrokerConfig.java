package com.zzb.config.websocket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
@EnableWebSocketMessageBroker
@Order(Ordered.HIGHEST_PRECEDENCE + 99) 
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
     * 注册STOMP端点，将每个端点映射到特定URL，并（可选）启用和配置SockJS后备选项。
     */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) { // endPoint
        //为js客户端提供链接  
		registry.addEndpoint("/websocket")
		//允许指定的域名或IP(含端口号)建立长连接，如果不限时使用"*"号，如果指定了域名，则必须要以http或https开头。
        .setAllowedOrigins("*")  
        //自定义握手处理器
        .setHandshakeHandler(myHandshakeHandler)
        //自定义握手拦截器
        .addInterceptors(myHandshakeInterceptor)  
        .withSockJS(); 
	}

	/** 
     * applicationDestinationPrefixes应用前缀，（发送消息url)所有请求的消息将会路由到@MessageMapping的controller上， 而不会发布到 代理队列或主题中；
     * enableStompBrokerRelay是代理前缀，而返回的消息将会路由到代理上，所有订阅该代理的将收到响应的消息。 
     * 配置消息代理选项
     */  
    @Override  
    public void configureMessageBroker(MessageBrokerRegistry registry) {  
        registry.setApplicationDestinationPrefixes("/ws"); 
        //表示给指定用户发送一对一的主题
        registry.setUserDestinationPrefix("/user");  
        //基于内存的STOMP消息代理
        //registry.enableSimpleBroker("/topic", "/queue")  
        //基于中间件的STOMP消息代理  @SendTo("/topic/**") @SendTo("/queue/**")
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
     * 配置与处理从WebSocket客户端接收和发送到WebSocket客户端的消息相关的选项
     */  
    @Override  
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {  
    	//super.configureWebSocketTransport(registration);  
    	registration.setMessageSizeLimit(128 * 1024) //设置消息字节数大小
    	.setSendTimeLimit(15 * 1000)//设置消息发送时间限制毫秒 
    	.setSendBufferSizeLimit(512 * 1024); //设置消息缓存大小
    }  
      
    /** 
     * 输入通道参数设置 ,客户端输入对象
     * 配置MessageChannel用于来自WebSocket客户端的传入消息
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
     * 输出通道参数配置 ，中间代理器输出对象
     * 配置MessageChannel用于出站消息到WebSocket客户端。
     */  
    @Override  
    public void configureClientOutboundChannel(ChannelRegistration registration) {  
    	//super.configureClientOutboundChannel(registration);  
        //线程信息  
    	registration//.setInterceptors(presenceChannelInterceptor)//传输通道拦截器，判断接连是否断开  
    	.taskExecutor().corePoolSize(10).maxPoolSize(20);
    }  
      
    /**
     * 消息转发器
     */
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