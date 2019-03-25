package com.zzb.module.system.entity;

public class SysRoleOrg {
    private String roleOrgId;

    private String roleId;

    private String orgId;

    private String lesseeId;

    public String getRoleOrgId() {
        return roleOrgId;
    }

    public void setRoleOrgId(String roleOrgId) {
        this.roleOrgId = roleOrgId == null ? null : roleOrgId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
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
}