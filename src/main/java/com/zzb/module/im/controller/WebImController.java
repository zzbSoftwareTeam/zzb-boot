package com.zzb.module.im.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/im")
public class WebImController {

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
