package com.zzb.module.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zzb.module.system.entity.SysRoleMenu;

@Mapper
public interface SysRoleMenuDao {
    int deleteByPrimaryKey(String id);

    int insert(SysRoleMenu record);

    int insertSelective(SysRoleMenu record);

    SysRoleMenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysRoleMenu record);

    int updateByPrimaryKey(SysRoleMenu record);

    /**
     * 
     * @Description: TODO 根据角色ID删除与资源的关联
     * @param id
     * @return int  
     * @author zengzhibin
     * @date 2019年3月16日
     */
    int deleteByRoleId(@Param("roleId")String roleId);

    /**
     * 
     * @Description: TODO 根据资源ID删除与角色的关联
     * @param id
     * @return int  
     * @author zengzhibin
     * @date 2019年3月16日
     */
	int deleteByMenuId(String id);
}