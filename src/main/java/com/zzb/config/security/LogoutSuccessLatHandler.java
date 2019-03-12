package com.zzb.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

/**
 * 
 * ClassName: LogoutSuccessLatHandler 
 * @Description: TODO 退出登录后自定义处理
 * @author zengzhibin
 * @date 2019年1月31日
 */
public class LogoutSuccessLatHandler extends SimpleUrlLogoutSuccessHandler {

	private Logger log =  LoggerFactory.getLogger(this.getClass());

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		log.info("---------LogoutSuccessLatHandler----------");
		super.handle(request, response, authentication);
	}
	
	
}
