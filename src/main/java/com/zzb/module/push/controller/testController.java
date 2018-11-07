package com.zzb.module.push.controller;

import java.util.List;

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
