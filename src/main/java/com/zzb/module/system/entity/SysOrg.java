package com.zzb.module.system.entity;

import java.util.Date;

import io.swagger.annotations.ApiParam;

public class SysOrg {
	@ApiParam(hidden=true)
    private String orgId;
	@ApiParam(hidden=true)
    private String lesseeId;
	@ApiParam(name="parentId",value="父id",required = false)
    private String parentId;
	@ApiParam(name="orgCode",value="机构标识",required = false)
    private String orgCode;
	@ApiParam(name="topName",value="展示机构名称",required = false)
    private String topName;
	@ApiParam(name="orgName",value="机构名称",required = false)
    private String orgName;
	@ApiParam(name="description",value="简介",required = false)
    private String description;
    @ApiParam(hidden=true)
    private String createUser;
    @ApiParam(hidden=true)
    private Date createTime;
    @ApiParam(hidden=true)
    private String updateUser;
    @ApiParam(hidden=true)
    private Date updateTime;
    @ApiParam(hidden=true)
    private Integer sort;

    /**
     * 机构要操作的人，用于查询某人所能操作的机构
     */
    @ApiParam(hidden=true)
    private String myUserId;
    
    
    public String getMyUserId() {
		return myUserId;
	}

	public void setMyUserId(String myUserId) {
		this.myUserId = myUserId;
	}

	public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getLesseeId() {
        return lesseeId;
    }

    public void setLesseeId(String lesseeId) {
        this.lesseeId = lesseeId == null ? null : lesseeId.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getTopName() {
        return topName;
    }

    public void setTopName(String topName) {
        this.topName = topName == null ? null : topName.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}