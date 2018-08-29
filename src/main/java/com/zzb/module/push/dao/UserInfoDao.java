package com.zzb.module.push.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.zzb.module.push.entity.UserInfo;

@Mapper
public interface UserInfoDao {

	UserInfo findByUsername(@Param(value = "name") String name);

	List<UserInfo> findAll();
	
	Page<UserInfo> findPage();
	
	Integer save(UserInfo user);

}
