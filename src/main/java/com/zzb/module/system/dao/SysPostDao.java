package com.zzb.module.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.zzb.module.system.entity.SysPost;
@Mapper
public interface SysPostDao {
    int deleteByPrimaryKey(String postId);

    int insert(SysPost record);

    int insertSelective(SysPost record);

    SysPost selectByPrimaryKey(String postId);

    int updateByPrimaryKeySelective(SysPost record);

    int updateByPrimaryKey(SysPost record);

    /**
     * 
     * @Description: TODO 分页带条件查询职位
     * @param sysPost
     * @return Page<SysPost>  
     * @author zengzhibin
     * @date 2019年3月16日
     */
	Page<SysPost> selectPage(SysPost sysPost);

	/**
	 * 
	 * @Description 根据职位名查询 
	 * @return SysPost  
	 * @author zengzhibin
	 * @date 2019年3月23日
	 */
	SysPost selectByPostName(@Param("postName")String postName);
}