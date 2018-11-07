package com.zzb.config.websocket.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zzb.module.im.entity.ClientImUser;

@Service
@Scope("singleton")
public class ClientImService {
	
	private PriorityBlockingQueue<ClientImUser> imQueue = new PriorityBlockingQueue<ClientImUser>();
	
	private ConcurrentHashMap<String,String> imMap = new ConcurrentHashMap<String,String>();
	
	public void add(ClientImUser bean) { 
		imQueue.offer(bean);
		imMap.put(bean.getClientName(), bean.getClientName());
    } 

    public boolean remove(ClientImUser bean) {
        if (imQueue.size() > 0) { 
        	imMap.remove(bean.getClientName());
            return imQueue.remove(bean); 
        } else { 
            return false; 
        } 
    } 
    
    public ClientImUser get() {
    	ClientImUser bean = imQueue.poll();
    	imMap.remove(bean.getClientName());
    	return bean; 
    }
    
    public Integer queueSize() {
    	return imQueue.size(); 
    }
    
    public Map<String,String> getMap() {
    	return imMap; 
    }
    
    public Integer beforeIndex(ClientImUser bean){
    	return 0;
    }
}
