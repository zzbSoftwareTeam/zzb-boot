package com.zzb.module.system.dao;

import org.apache.ibatis.annotations.Mapper;

import com.zzb.module.system.entity.SysUserBook;

@Mapper
public interface SysUserBookDao {
    int deleteByPrimaryKey(String userBookId);

    int insert(SysUserBook record);

    int insertSelective(SysUserBook record);

    SysUserBook selectByPrimaryKey(String userBookId);

    int updateByPrimaryKeySelective(SysUserBook record);

    int updateByPrimaryKey(SysUserBook record);
}