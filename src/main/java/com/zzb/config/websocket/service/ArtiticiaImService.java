package com.zzb.config.websocket.service;

import java.util.concurrent.PriorityBlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zzb.common.service.redis.RedisService;
import com.zzb.module.im.entity.ServiceImRedis;
import com.zzb.module.im.entity.ServiceImUser;


@Service
@Scope("singleton")
public class ArtiticiaImService {
	
	private PriorityBlockingQueue<ServiceImUser> imQueue = new PriorityBlockingQueue<ServiceImUser>();
	@Autowired
	RedisService redisService;
	
	public void add(ServiceImUser bean) { 
		imQueue.offer(bean);
    } 

    public boolean remove(ServiceImUser bean) {
        if (imQueue.size() > 0) { 
            return imQueue.remove(bean); 
        } else { 
            return false; 
        } 
    } 
    
    public synchronized ServiceImUser get() {
    	ServiceImUser siu = imQueue.poll(); 
    	ServiceImRedis bean = redisService.getObj(siu.getServiceName());
		if(bean.getSessionNum()<bean.getMaxCsu()){
			bean.setSessionNum(bean.getSessionNum()+1);
			redisService.cacheObject(bean);
			return siu;
		}else{
			imQueue.offer(siu);
			return null;
		}
    }
}
