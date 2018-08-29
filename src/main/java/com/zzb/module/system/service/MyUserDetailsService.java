package com.zzb.module.system.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zzb.module.system.dao.SysPermissionDao;
import com.zzb.module.system.dao.SysUserDao;
import com.zzb.module.system.entity.SysPermission;
import com.zzb.module.system.entity.SysUser;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
    SysUserDao sysUserDao;
	@Autowired
	SysPermissionDao sysPermissionDao;
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		try {
			SysUser user = sysUserDao.findByUserName(name);
	        if (user != null) {
	            List<SysPermission> permissions = sysPermissionDao.findByUserId(user.getId());
	            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	            for (SysPermission permission : permissions) {
	                if (permission != null && permission.getPerName()!=null) {
		                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getUrl());
		                //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
		                grantedAuthorities.add(grantedAuthority);
	                }
	            }
	            return new User(user.getUserName(), user.getUserPass(), grantedAuthorities);
	        } else {
	            throw new UsernameNotFoundException("UserName do not exist!");
	        }
		} catch (Exception e) {
			throw new UsernameNotFoundException("UserName do not exist!");
		}
	}
}
