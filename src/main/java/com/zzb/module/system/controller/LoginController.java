package com.zzb.module.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class LoginController {

	private Logger log =  LoggerFactory.getLogger(this.getClass());
	
    @GetMapping("/login")
	public String tologin(){
    	return"/login";
	}
    
    //@PreAuthorize("hasAuthority('/baidu')")
    @GetMapping("/login/return")
	public @ResponseBody String loginReturn(HttpServletRequest request){
    	String status="";
    	AuthenticationException exception = (AuthenticationException) request.getSession()
    			.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    	if (exception instanceof BadCredentialsException) {
    		status = "用户名或密码不正确";
        }else if(exception instanceof UsernameNotFoundException) {
        	status = "用户找不到";
        }else if(exception instanceof AccountExpiredException) {
        	status = "账户过期";
        }else if(exception instanceof LockedException) {
        	status = "账户锁定";
        }else if(exception instanceof DisabledException) {
        	status = "账户不可用";
        }else if(exception instanceof CredentialsExpiredException) {
        	status = "证书过期";
        }else{
    		status="success";
    	}
		return status;
		
	}
    
    @GetMapping(value={"/","/index"})
	public String index(){
		return"/index";
	}
    
    @GetMapping(value={"/index1"})
	public String index1(){
		return"/index_bak";
	}
}
