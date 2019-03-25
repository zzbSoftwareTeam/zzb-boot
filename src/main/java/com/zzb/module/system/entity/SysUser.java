package com.zzb.module.system.entity;

import java.util.Date;

import io.swagger.annotations.ApiParam;


public class SysUser {
	@ApiParam(hidden=true)
    private String userId;
	@ApiParam(hidden=true)
    private String lesseeId;
	@ApiParam(value="账号",required = false)
    private String userAccount;
    @ApiParam(hidden=true)
    private String userPass;
    @ApiParam(value="姓名",required = false)
    private String userName;
    @ApiParam(value="姓名拼音",required = false)
    private String userPinyin;
    @ApiParam(hidden=true)
    private String userBookId;
    @ApiParam(value="手机号码",required = false)
    private String userTel;
    @ApiParam(value="手机短号",required = false)
    private String seTel;
    @ApiParam(value="固定电话",required = false)
    private String telephone;
    @ApiParam(value="邮箱",required = false)
    private String email;
    @ApiParam(value="性别",required = false)
    private Integer sex;
    @ApiParam(value="生日",required = false)
    private Date birthday;
    @ApiParam(value="城市",required = false)
    private String city;
    @ApiParam(value="头像地址",required = false)
    private String avatarUrl;
    @ApiParam(value="机构部门ID",required = false)
    private String orgId;
    @ApiParam(value="职位ID",required = false)
    private String postId;
    @ApiParam(value="角色ID",required = false)
    private String roleId;
    @ApiParam(hidden=true)
    private String createUser;
    @ApiParam(hidden=true)
    private Date createTime;
    @ApiParam(hidden=true)
    private Date lastTime;
    @ApiParam(hidden=true)
    private Date passupTime;
    @ApiParam(value="用户状态0在职1离职",required = false)
    private String status;
    @ApiParam(hidden=true)
    private String delFlag;
    @ApiParam(hidden=true)
    private Integer sort;

    
    public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getLesseeId() {
        return lesseeId;
    }

    public void setLesseeId(String lesseeId) {
        this.lesseeId = lesseeId == null ? null : lesseeId.trim();
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass == null ? null : userPass.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPinyin() {
        return userPinyin;
    }

    public void setUserPinyin(String userPinyin) {
        this.userPinyin = userPinyin == null ? null : userPinyin.trim();
    }

    public String getUserBookId() {
        return userBookId;
    }

    public void setUserBookId(String userBookId) {
        this.userBookId = userBookId == null ? null : userBookId.trim();
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel == null ? null : userTel.trim();
    }

    public String getSeTel() {
        return seTel;
    }

    public void setSeTel(String seTel) {
        this.seTel = seTel == null ? null : seTel.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId == null ? null : postId.trim();
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

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Date getPassupTime() {
        return passupTime;
    }

    public void setPassupTime(Date passupTime) {
        this.passupTime = passupTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}