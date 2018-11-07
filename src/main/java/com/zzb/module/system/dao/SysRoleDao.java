package com.zzb.module.system.dao;

import org.apache.ibatis.annotations.Mapper;

import com.zzb.module.system.entity.SysRole;

@Mapper
public interface SysRoleDao {
    int deleteByPrimaryKey(String id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}