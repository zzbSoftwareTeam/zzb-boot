package com.zzb.config.websocket;

import java.security.Principal;

public class ClientPrincipal implements Principal{

	//没用到
	private String sessionId;
	
	private String name;
	
	
	
	public String getSessionId() {
		return sessionId;
	}


	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String getName() {
		return name;
	}

}
