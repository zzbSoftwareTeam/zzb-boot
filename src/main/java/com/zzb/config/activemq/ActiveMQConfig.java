package com.zzb.config.activemq;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zzb.common.service.activemq.ActiveMQService;

@Configuration
public class ActiveMQConfig {

	@Value("${spring.activemq.jmx-url}")
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
	}
}
