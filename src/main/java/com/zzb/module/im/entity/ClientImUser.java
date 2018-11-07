package com.zzb.module.im.entity;

/**
 * ClassName: ClientImUser 
 * @Description: TODO 访客队列对象
 * @author zengzhibin
 * @date 2018年9月18日
 */
public class ClientImUser implements Comparable<ClientImUser>{
	
	private String clientName;
	private Integer level;
	
	
	public ClientImUser() {
		
	}
	public ClientImUser(String clientName, Integer level) {
		this.clientName = clientName;
		this.level = level;
	}
	
	/**
	 * 
	 * @Description: TODO 当前对象和其他对象做比较，当前优先级大就返回-1，优先级小就返回1 
	 * 值越大优先级越高
	 * @param arg0
	 * @return int  
	 * @author zengzhibin
	 * @date 2018年9月17日
	 */
	@Override
	public int compareTo(ClientImUser arg0) {
		return arg0.getLevel().compareTo(this.level);
	}
	
	
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
}
