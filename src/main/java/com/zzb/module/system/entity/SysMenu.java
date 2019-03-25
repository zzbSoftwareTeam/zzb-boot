package com.zzb.module.system.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="资源菜单实体",description="资源菜单实体")
public class SysMenu {
	
	@ApiModelProperty(hidden=true)
    private String menuId;
	@ApiModelProperty(value="父资源id",required=false)
    private String parentId;
    @ApiModelProperty(value="资源菜单类型0菜单1按钮",required=false)
    private String menuType;
    @ApiModelProperty(value="展示名称",required=false)
    private String topTitle;
    @ApiModelProperty(value="实际名称",required=false)
    private String title;
    @ApiModelProperty(value="资源地址，用于权限验证",required=false)
    private String url;
    @ApiModelProperty(value="图标",required=false)
    private String icon;
    @ApiModelProperty(value="状态0可用，1不可用",required=false)
    private String status;
    @ApiModelProperty(hidden=true)
    private Integer sort;
    @ApiModelProperty(hidden=true)
    private String createUser;
    @ApiModelProperty(hidden=true)
    private Date createTime;
    @ApiModelProperty(hidden=true)
    private String updateUser;
    @ApiModelProperty(hidden=true)
    private Date updateTime;
    @ApiModelProperty(hidden=true)
    private String lesseeId;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType == null ? null : menuType.trim();
    }

    public String getTopTitle() {
        return topTitle;
    }

    public void setTopTitle(String topTitle) {
        this.topTitle = topTitle == null ? null : topTitle.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public String getLesseeId() {
        return lesseeId;
    }

    public void setLesseeId(String lesseeId) {
        this.lesseeId = lesseeId == null ? null : lesseeId.trim();
    }
}