package com.zzb.module.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {

	private Logger log =  LoggerFactory.getLogger(this.getClass());
	
    @GetMapping("/login")
	public String tologin(){
    	return"/login";
	}
    
    @GetMapping(value={"/","/index"})
	public String index(){
		return"/index";
	}
    
    @PreAuthorize("hasAuthority('/baidu')")
    @GetMapping("/baidu")  
	public String baidu(){
		return "/push/baidu";
	}
    

}
