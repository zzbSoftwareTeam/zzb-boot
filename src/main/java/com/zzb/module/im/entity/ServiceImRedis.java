package com.zzb.module.im.entity;

import java.io.Serializable;

/**
 * ClassName: ServiceImRedis
 * @Description: TODO  客服状态对象存redis
 * @author zengzhibin
 * @date 2018年9月18日
 */
public class ServiceImRedis implements Serializable{
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -7136972565784022980L;
	
	private String id; //id
	private String serviceName; //名称
	private Integer sessionNum; //当前会话数
	private Integer maxCsu; //最大会话数
	private Integer allotFlag; //是否可接入会话 0可接入，1不可接入
	private Integer status; //会话状态 0在线，1离线
	
	public ServiceImRedis() {
	}
	
	public String getKey() {  
        return getServiceName();  
    }  
  
    public String toString() {  
        return "ServiceImRedis [id=" + id + ", serviceName=" + serviceName + "]";  
    } 
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public Integer getSessionNum() {
		return sessionNum;
	}
	public void setSessionNum(Integer sessionNum) {
		this.sessionNum = sessionNum;
	}
	public Integer getMaxCsu() {
		return maxCsu;
	}
	public void setMaxCsu(Integer maxCsu) {
		this.maxCsu = maxCsu;
	}
	public Integer getAllotFlag() {
		return allotFlag;
	}
	public void setAllotFlag(Integer allotFlag) {
		this.allotFlag = allotFlag;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
