package com.zzb.module.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.github.pagehelper.Page;
import com.zzb.module.system.entity.SysRole;
@Mapper
public interface SysRoleDao {
    int deleteByPrimaryKey(String roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    /**
     * 
     * @Description: TODO 分页查询角色
     * @param sysRole
     * @return Page<SysRole>  
     * @author zengzhibin
     * @date 2019年3月16日
     */
	Page<SysRole> selectPage(SysRole sysRole);

	/**
	 * 
	 * @Description: TODO 获取所有角色
	 * @param sysRole
	 * @return List<SysRole>  
	 * @author zengzhibin
	 * @date 2019年3月16日
	 */
	List<SysRole> selectAll(SysRole sysRole);
}