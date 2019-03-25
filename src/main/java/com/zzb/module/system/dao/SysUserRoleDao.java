package com.zzb.module.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zzb.module.system.entity.SysUserRole;

@Mapper
public interface SysUserRoleDao {
    int deleteByPrimaryKey(String id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);

    /**
     * 
     * @Description: TODO 根据角色ID删除与用户的关联
     * @param id
     * @return int  
     * @author zengzhibin
     * @date 2019年3月16日
     */
	int deleteByRoleId(@Param("roleId")String roleId);
}