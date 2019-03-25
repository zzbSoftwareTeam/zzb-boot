package com.zzb.module.system.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value="职位实体",description="职位实体")
public class SysPost {
	
	@ApiModelProperty(hidden=true)
    private String postId;
	@ApiModelProperty(value="所属机构ID",required=false)
    private String orgId;
	@ApiModelProperty(hidden=true)
    private String lesseeId;
    @ApiModelProperty(value="父职位ID",required=false)
    private String parentId;
    @ApiModelProperty(value="职位标识",required=false)
    private String postCode;
    @ApiModelProperty(value="职位名称",required=false)
    private String postName;
    @ApiModelProperty(value="职位简介",required=false)
    private String description;
    @ApiModelProperty(value="状态0部门负责人，普通员工",required=false)
    private String status;
    @ApiModelProperty(hidden=true)
    private String createUser;
    @ApiModelProperty(hidden=true)
    private Date createTime;
    @ApiModelProperty(hidden=true)
    private String updateUser;
    @ApiModelProperty(hidden=true)
    private Date updateTime;
    @ApiModelProperty(hidden=true)
    private Integer sort;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId == null ? null : postId.trim();
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

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName == null ? null : postName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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