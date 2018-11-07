package com.zzb.config.websocket.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzb.common.service.redis.RedisService;
import com.zzb.common.utils.IdGenerateUtil;
import com.zzb.module.im.entity.ClientImUser;
import com.zzb.module.im.entity.ServiceImUser;

/**
 * ClassName: SocketMatchService 
 * @Description: TODO websocket匹配服务
 * @author zengzhibin
 * @date 2018年9月19日
 */
@Service
public class SocketMatchService {
	
	private Logger log =  LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RedisService redisService;
	@Autowired
	private ClientImService clientImService;
	@Autowired
	private ArtiticiaImService artiticiaImService;
	
	private static boolean taskStatus=true;
	private static final Integer SEC=3;
			
	
	/**
	 * @Description: TODO websocket匹配机制
	 * @return void  
	 * @author zengzhibin
	 * @date 2018年9月19日
	 */
	public synchronized void match(){
		
		if(taskStatus){
			Timer timer = new Timer();
			timer.schedule(new TimerTask(){
				public void run(){
					Integer its = clientImService.queueSize();
					if(its>0){
						ServiceImUser service = artiticiaImService.get();
						if(service!=null){
							ClientImUser client = clientImService.get();
							//建立会话redis存储
							Map<String,Object> map = new HashMap<String, Object>();
							String sessionId = IdGenerateUtil.uuid2();
							map.put("sessionId", sessionId);
							map.put("clients", client.getClientName());
							map.put("services",service.getServiceName());
							redisService.cacheMap(service.getServiceName()+"_"+client.getClientName(), map);
							
							//sessionId保存在会话中，触发其它状态时有用
							
						}else{
							//Map<String,String> map = clientImService.getMap();
						}
					}
					//判断用户队列是否有人，没人就干掉定时器
					Integer nowits = clientImService.queueSize();
					if(nowits>0){
						taskStatus=false;
					}else{
						timer.cancel();
						taskStatus=true;
					}
				}
			},0,SEC*1000);
		}
		//推送消息给访客
		//。。。。
	}
}
