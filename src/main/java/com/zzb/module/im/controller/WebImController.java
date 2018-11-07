package com.zzb.module.im.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzb.common.service.redis.RedisService;
import com.zzb.config.websocket.service.ArtiticiaImService;

@Controller
@RequestMapping("/im")
public class WebImController {

	@Autowired
	ArtiticiaImService artiticiaImService;
	@Autowired
	RedisService redisService;
	
    @GetMapping("/topic")  
	public String topic(){
		return "/im/topic";
	}
    
    @GetMapping("/userIm")  
	public String userIm(){
		return "/im/userIm";
	}
    
    @GetMapping("/serviceIm")  
	public String serviceIm(){
		return "/im/serviceIm";
	}
}
