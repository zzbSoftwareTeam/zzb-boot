package com.zzb.module.push.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.QueueConnection;
import javax.jms.Session;
import javax.jms.Topic;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.advisory.DestinationSource;
import org.apache.activemq.broker.Broker;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.jmx.BrokerView;
import org.apache.activemq.broker.jmx.BrokerViewMBean;
import org.apache.activemq.broker.jmx.QueueView;
import org.apache.activemq.broker.jmx.QueueViewMBean;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.BrowserCallback;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.ProducerCallback;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hankcs.hanlp.seg.common.Term;
import com.zzb.common.service.activemq.ActiveMQService;
import com.zzb.common.service.nlp.HanLPService;
import com.zzb.common.service.redis.RedisService;


@RestController
@RequestMapping("/")
public class testController {
	
	private Logger log =  LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RedisService redisService;
	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	@Autowired
	private ActiveMQService activeMQService;
	@Autowired
    private Queue queueService;
    @Autowired
    private HanLPService hanLPService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    
	@RequestMapping("/redis")
	public String redis(){
		System.out.println(redisService.getValue("aaaa"));
		redisService.cacheValue("aaaa", "cccc");
		System.out.println(redisService.getValue("aaaa"));
		redisService.removeValue("aaaa");
		
		System.out.println(redisService.containsValueKey("aaaa"));
		return "redis";
	}
	
	@RequestMapping("/activemq")
	public String activemq() throws JsonProcessingException{
		jmsMessagingTemplate.convertAndSend(queueService, "zzb1");
		jmsTemplate.convertAndSend(queueService, "zzb2");
		jmsTemplate.convertAndSend(queueService, "zzb3");
		return "activemq";		
	}
	
	@RequestMapping("/getmq")
	public String getmq() throws Exception{
		long userint = activeMQService.getQueueSize("queue_user");
		return "getmq="+userint;  
	}
	
	@RequestMapping(path = "/toBaidu", method = RequestMethod.POST)  
	public String toBaidu(){
		return "/push/baidu";
	}
	
	@RequestMapping(path = "/todemo", method = RequestMethod.GET)  
	public String todemo(){
		return "/push/demo";
	}
	@GetMapping("/hlp")
	public List<Term> getHlp() throws JsonProcessingException{
		ObjectMapper objectMapper = new ObjectMapper();
		String type="";
		String text="如果你通过上述办法解决了问题，欢迎向我提交pull request，词典也是宝贵的财富";
		return hanLPService.segment(type, text);
	}
}
