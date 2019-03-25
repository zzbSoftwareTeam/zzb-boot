package com.zzb.module.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.zzb.module.system.entity.SysMenu;
@Mapper
public interface SysMenuDao {
    int deleteByPrimaryKey(String menuId);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(String menuId);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    /**
     * 
     * @Description: TODO 根据用户ID获取用所有资源权限 
     * @param userId 用户ID
     * @return List<SysMenu>  
     * @author zengzhibin
     * @date 2019年3月11日
     */
	List<SysMenu> selectByUserId(@Param("userId")String userId);

	/**
	 * 
	 * @Description: TODO 分页查询资源
	 * @param sysMenu
	 * @return Page<SysMenu>  
	 * @author zengzhibin
	 * @date 2019年3月16日
	 */
	Page<SysMenu> selectPage(SysMenu sysMenu);

	/**
	 * 
	 * @Description: TODO 查询当前登录用户所有资源
	 * @param sysMenu
	 * @return List<SysMenu>  
	 * @author zengzhibin
	 * @date 2019年3月16日
	 */
	List<SysMenu> selectMyMenu(SysMenu sysMenu);

}