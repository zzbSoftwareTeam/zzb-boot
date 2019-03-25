package com.zzb.module.system.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.zzb.module.system.entity.SysMenu;
import com.zzb.module.system.entity.SysUser;

@Mapper
public interface SysUserDao {


    int deleteByPrimaryKey(String userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     *
     * @Description: TODO 根据账号获取用户实体
     * @param userAccount 账号
     * @return SysUser
     * @author zengzhibin
     * @date 2019年3月11日
     */
	SysUser selectByAccount(@Param("userAccount")String userAccount);


	/**
	 * 
	 * @Description 用户附属信息保存
	 * @return int  
	 * @author zengzhibin
	 * @date 2019年3月21日
	 */
	int insertAffiliate(@Param("affiliateSql")String affiliateSql);

	/**
	 * 
	 * @Description 清除人员的机构信息
	 * @return int  
	 * @author zengzhibin
	 * @date 2019年3月22日
	 */
	int updateUserByOrgId(@Param("orgId")String orgId, @Param("lesseeId")String lesseeId);

	/**
	 * 
	 * @Description 用户信息分页查询 
	 * @return Page<SysMenu>  
	 * @author zengzhibin
	 * @date 2019年3月22日
	 */
	Page<SysMenu> selectPage(SysUser sysUser);

	/**
	 * 
	 * @Description 
	 * @return List<SysUser>  
	 * @author zengzhibin
	 * @date 2019年3月22日
	 */
	List<SysUser> selectAll(SysUser sysUser);

	/**
	 * 
	 * @Description 获取要导出用户数据
	 * @return List<Map<String,Object>>  
	 * @author zengzhibin
	 * @date 2019年3月23日
	 */
	List<Map<String, Object>> selectExportUser();




}