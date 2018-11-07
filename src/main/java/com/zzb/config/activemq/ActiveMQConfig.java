package com.zzb.config.activemq;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zzb.common.service.activemq.ActiveMQService;

/**
 * 
 * ClassName: ActiveMQConfig 
 * @Description: TODO 配置ActiveMQ容器，直接用java代码对ActiveMQ数据进行操作
 * @author zengzhibin
 * @date 2018年9月14日
 */
//@Configuration
public class ActiveMQConfig {

	/*@Value("${spring.activemq.jmx-url}")
	private String JMX_URL;
	
	@Value("${spring.activemq.brokerName}")
	private String BROKER_NAME;
	
	@Bean
	public Queue queueService() {
       return new ActiveMQQueue("queue_service");
    }
	@Bean
	public Queue queueUser(){
		return new ActiveMQQueue("queue_user");
	}
	
	@Bean
	public ActiveMQService activeMQService(){
		return new ActiveMQService(JMX_URL,BROKER_NAME);
	}*/
}
