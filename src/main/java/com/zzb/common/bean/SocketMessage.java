package com.zzb.common.bean;

public class SocketMessage {
	public String user;
	public String message;
	public String status;
    public String date;
    
    
	public SocketMessage() {
		super();
	}


	public SocketMessage(String user, String message, String status, String date) {
		super();
		this.user = user;
		this.message = message;
		this.status = status;
		this.date = date;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
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
