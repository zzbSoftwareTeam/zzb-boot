package com.zzb.module.im.entity;


/**
 * ClassName: ServiceImUser 
 * @Description: TODO 客服队列对象
 * @author zengzhibin
 * @date 2018年9月18日
 */
public class ServiceImUser implements Comparable<ServiceImUser>{
	private String serviceName; //名称
	private Integer level; //优先级
	
	public ServiceImUser() {
	}

	/**
	 * 
	 * @Description: TODO 当前对象和其他对象做比较，当前优先级大就返回-1，优先级小就返回1 
	 * 值越小优先级越高
	 * @param arg0
	 * @return int  
	 * @author zengzhibin
	 * @date 2018年9月17日
	 */
	@Override
	public int compareTo(ServiceImUser arg0) {
		return this.level.compareTo(arg0.getLevel());
	}
	
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}