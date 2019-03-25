package com.zzb.module.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zzb.module.system.entity.SysUserPost;

@Mapper
public interface SysUserPostDao {
    int deleteByPrimaryKey(String id);

    int insert(SysUserPost record);

    int insertSelective(SysUserPost record);

    SysUserPost selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUserPost record);

    int updateByPrimaryKey(SysUserPost record);

    /**
     * 
     * @Description: TODO 根据职位ID清除职位下的用户关联
     * @param id
     * @return int  
     * @author zengzhibin
     * @date 2019年3月16日
     */
	int deleteByPostId(@Param("postId")String id);
}