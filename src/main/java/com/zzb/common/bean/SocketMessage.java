package com.zzb.common.bean;

public class SocketMessage {
	
	private String msessionId;
	private String type;
	private String services;
	private String clients;
	private String message;
	private String status;
	private String date;
    
	public SocketMessage() {
	}


	public SocketMessage(String msessionId, String type, String services, String clients, String message, String status,
			String date) {
		this.msessionId = msessionId;
		this.type = type;
		this.services = services;
		this.clients = clients;
		this.message = message;
		this.status = status;
		this.date = date;
	}



	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getMsessionId() {
		return msessionId;
	}




	public void setMsessionId(String msessionId) {
		this.msessionId = msessionId;
	}



	public String getServices() {
		return services;
	}



	public void setServices(String services) {
		this.services = services;
	}



	public String getClients() {
		return clients;
	}



	public void setClients(String clients) {
		this.clients = clients;
	}



	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
    
    
}
