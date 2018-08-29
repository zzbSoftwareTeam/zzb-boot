package com.zzb.module.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zzb.module.system.entity.SysPermission;

@Mapper
public interface SysPermissionDao {

	public List<SysPermission> findAll();
	
	public List<SysPermission> findByUserId(@Param("userId") Long userId);
    
}
