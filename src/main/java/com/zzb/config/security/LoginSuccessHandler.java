package com.zzb.config.security;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.zzb.module.system.dao.SysUserDao;
import com.zzb.module.system.entity.SysUser;

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	@Autowired
    SysUserDao sysUserDao;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// 获得授权后可得到用户信息 可使用Service进行数据库操作
		Object obj=authentication.getCredentials();
		Object obj1=authentication.getDetails();
		User userDetails=(User) authentication.getPrincipal();
		SysUser user = sysUserDao.findByUserName(userDetails.getUsername());
		Collection<? extends GrantedAuthority> auths=authentication.getAuthorities();
		// 输出登录提示信息
		System.out.println("username:"+user.getUserName()+"IP :" + getIpAddress(request));
		for (GrantedAuthority grantedAuthority : auths) {
			System.out.println("permission："+grantedAuthority.getAuthority());
		}
		super.onAuthenticationSuccess(request, response, authentication);
	}

	public String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
