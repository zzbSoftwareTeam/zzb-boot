package com.zzb.module.system.entity;

import java.util.Date;

public class SysUserBook {
    private String userBookId;

    private String userId;

    private String lesseeId;

    private Date entryDate;

    private Date workDate;

    private Date bwDate;

    private String userType;

    private String workType;

    public String getUserBookId() {
        return userBookId;
    }

    public void setUserBookId(String userBookId) {
        this.userBookId = userBookId == null ? null : userBookId.trim();
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

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public Date getBwDate() {
        return bwDate;
    }

    public void setBwDate(Date bwDate) {
        this.bwDate = bwDate;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType == null ? null : workType.trim();
    }
}