package com.zzb.module.system.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zzb.module.system.dao.SysMenuDao;
import com.zzb.module.system.dao.SysUserDao;
import com.zzb.module.system.entity.SysMenu;
import com.zzb.module.system.entity.SysUser;


@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
    SysUserDao sysUserDao;
	@Autowired
	SysMenuDao sysMenuDao;
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		try {
			SysUser user = sysUserDao.selectByAccount(name);
	        if (user != null) {
	            List<SysMenu> menus = sysMenuDao.selectByUserId(user.getUserId());
	            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	            for (SysMenu menu : menus) {
	                if (menu != null && StringUtils.isNotBlank(menu.getUrl())) {
		                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(menu.getUrl());
		                //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
		                grantedAuthorities.add(grantedAuthority);
	                }
	            }
	            return new User(user.getUserName(), user.getUserPass(), grantedAuthorities);
	        } else {
	            throw new UsernameNotFoundException("用户名不正确!");
	        }
		} catch (Exception e) {
			throw new UsernameNotFoundException("用户名不正确!");
		}
	}
}
