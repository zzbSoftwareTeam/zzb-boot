package com.zzb.module.push.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.zzb.module.push.entity.UserInfo;
import com.zzb.module.push.service.UserService;

@Service("userb")
public class UserbServiceImpl implements UserService{

	@Override
	public UserInfo findByUsername(String name){
		UserInfo u = new UserInfo();
		u.setName("b");
		return u;
	}

	@Override
	public List<UserInfo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<UserInfo> findPage(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(UserInfo user) {
		// TODO Auto-generated method stub
	}
}
