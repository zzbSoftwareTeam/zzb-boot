package com.zzb.module.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zzb.module.system.entity.SysRoleOrg;

@Mapper
public interface SysRoleOrgDao {
    int deleteByPrimaryKey(String roleOrgId);

    int insert(SysRoleOrg record);

    int insertSelective(SysRoleOrg record);

    SysRoleOrg selectByPrimaryKey(String roleOrgId);

    int updateByPrimaryKeySelective(SysRoleOrg record);

    int updateByPrimaryKey(SysRoleOrg record);

    /**
     * 
     * @Description: TODO 根据角色ID删除与机构的关联
     * @param id 
     * @return void  
     * @author zengzhibin
     * @date 2019年3月16日
     */
	void deleteByRoleId(@Param("roleId")String roleId);
}