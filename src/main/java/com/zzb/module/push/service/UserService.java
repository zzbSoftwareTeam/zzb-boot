package com.zzb.module.push.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.zzb.module.push.entity.UserInfo;

public interface UserService {

	public UserInfo findByUsername(String name);

	public List<UserInfo> findAll();
	
	public Page<UserInfo> findPage(Map<String,Object> map);
	
    public void save(UserInfo user);
}
