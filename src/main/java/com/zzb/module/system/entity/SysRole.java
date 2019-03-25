package com.zzb.module.system.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="角色实体",description="角色实体")
public class SysRole {
	
	@ApiModelProperty(hidden=true)
    private String roleId;
	@ApiModelProperty(hidden=true)
    private String lesseeId;
	@ApiModelProperty(value="角色标识",required=false)
    private String roleCode;
	@ApiModelProperty(value="角色名称",required=false)
    private String roleName;
	@ApiModelProperty(value="角色简介",required=false)
    private String description;
    @ApiModelProperty(hidden=true)
    private String createUser;
    @ApiModelProperty(hidden=true)
    private Date createTime;
    @ApiModelProperty(hidden=true)
    private String updateUser;
    @ApiModelProperty(hidden=true)
    private Date updateTime;
    
    @ApiModelProperty(hidden=true)
    private Set<SysUser> users;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getLesseeId() {
        return lesseeId;
    }

    public void setLesseeId(String lesseeId) {
        this.lesseeId = lesseeId == null ? null : lesseeId.trim();
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
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

	public Set<SysUser> getUsers() {
		return users;
	}

	public void setUsers(Set<SysUser> users) {
		this.users = users;
	}
    
    
}