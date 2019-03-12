package com.zzb.config.security;

import java.io.Serializable;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

@Configuration
public class MethodPermissionEvaluator implements PermissionEvaluator {

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		boolean accessable = false;
		String ps = permission.toString();
		for(GrantedAuthority authority : authentication.getAuthorities()){
			if(ps.equalsIgnoreCase(authority.getAuthority())){
				accessable = true;
				break;
			}
		}
		return accessable;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		// TODO Auto-generated method stub
		return false;
	}


}
