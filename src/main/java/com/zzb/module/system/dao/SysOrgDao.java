package com.zzb.module.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zzb.module.system.entity.SysOrg;


@Mapper
public interface SysOrgDao {
    int deleteByPrimaryKey(String orgId);

    int insert(SysOrg record);

    int insertSelective(SysOrg record);

    SysOrg selectByPrimaryKey(String orgId);

    int updateByPrimaryKeySelective(SysOrg record);

    int updateByPrimaryKey(SysOrg record);


	/**
	 * 
	 * @Description: TODO 获取当前机构最大排序值
	 * @return Integer  
	 * @author zengzhibin
	 * @date 2019年3月20日
	 */
	Integer selectMaxSort();

	/**
	 * 
	 * @Description: TODO 获取机构
	 * @param sysOrg
	 * @return List<SysOrg>  
	 * @author zengzhibin
	 * @date 2019年3月20日
	 */
	List<SysOrg> selectOrgs(SysOrg sysOrg);

	/**
	 * 
	 * @Description: TODO 获取当前登录人所能的操作的机构
	 * @param userId
	 * @return List<SysOrg>  
	 * @author zengzhibin
	 * @date 2019年3月20日
	 */
	List<SysOrg> selectUserOrgs(SysOrg sysOrg);

	
	/**
	 * 
	 * @Description 查询指定组织机构ID下的人员数
	 * @return List<Map<String,Object>>  
	 * @author zengzhibin
	 * @date 2019年3月20日
	 */
	Map<String, Object> selectOrgUserNum(String orgId);

	/**
	 * 
	 * @Description 查询指定组织机构ID下的负责人
	 * @return List<Map<String,Object>>  
	 * @author zengzhibin
	 * @date 2019年3月20日
	 */
	List<Map<String, Object>> selectOrgPost(String orgId);
	
}