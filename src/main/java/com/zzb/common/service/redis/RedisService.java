package com.zzb.common.service.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.zzb.common.utils.SerializeUtil;
import com.zzb.module.im.entity.ServiceImRedis;

/**
 * 
 * ClassName: RedisService
 * @Description: TODO redis服务类
 * @author zengzhibin
 * @date 2017年9月27日
 */
@Service
public class RedisService {

	/** 日志记录 */
	private Logger logger =  LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
    /**
     * 前缀
     */
    public static final String KEY_PREFIX_VALUE = "str:";
    public static final String KEY_PREFIX_SET = "set:";
    public static final String KEY_PREFIX_LIST = "list:";
    public static final String KEY_PREFIX_MAP = "map:";

    /**
     * 缓存value操作
     * @param k
     * @param v
     * @param time
     * @return
     */
    public boolean cacheValue(String k, String v, long time) {
        String key = KEY_PREFIX_VALUE + k;
        try {
            ValueOperations<String, String> valueOps =  redisTemplate.opsForValue();
            valueOps.set(key, v);
            if (time > 0) redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存value操作
     * @param k
     * @param v
     * @return
     */
    public boolean cacheValue(String k, String v) {
        return cacheValue(k, v, -1);
    }

    /**
     * 获取缓存
     * @param k
     * @return
     */
    public String getValue(String k) {
        try {
            ValueOperations<String, String> valueOps =  redisTemplate.opsForValue();
            return valueOps.get(KEY_PREFIX_VALUE + k);
        } catch (Throwable t) {
            logger.error("获取缓存失败key[" + KEY_PREFIX_VALUE + k + ", error[" + t + "]");
        }
        return null;
    }
    
    /** 
     * 如果 key 存在则覆盖,并返回旧值. 
     * 如果不存在,返回null 并添加 
     * @param key 
     * @param value 
     * @return 
     */  
    public String getAndSet(String key,String value){  
        return (String) redisTemplate.opsForValue().getAndSet(KEY_PREFIX_VALUE+key, value);  
    }  
      
      
    /** 
     * 批量添加 key-value (重复的键会覆盖) 
     * @param keyAndValue 
     */  
    public void batchSet(Map<String,String> keyAndValue){  
        redisTemplate.opsForValue().multiSet(keyAndValue);  
    }  
      
    /** 
     * 批量添加 key-value 只有在键不存在时,才添加 
     * map 中只要有一个key存在,则全部不添加 
     * @param keyAndValue 
     */  
    public void batchSetIfAbsent(Map<String,String> keyAndValue){  
        redisTemplate.opsForValue().multiSetIfAbsent(keyAndValue);  
    } 
    
    /**
     * 判断缓存是否存在
     * @param k
     * @return
     */
    public boolean containsValueKey(String k) {
        return containsKey(KEY_PREFIX_VALUE + k);
    }

    /**
     * 判断缓存是否存在
     * @param k
     * @return
     */
    public boolean containsSetKey(String k) {
        return containsKey(KEY_PREFIX_SET + k);
    }

    /**
     * 判断缓存是否存在
     * @param k
     * @return
     */
    public boolean containsListKey(String k) {
        return containsKey(KEY_PREFIX_LIST + k);
    }

    
    public boolean containsMapKey(String k) {
        return containsKey(KEY_PREFIX_MAP + k);
    }
    
    public boolean containsKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Throwable t) {
            logger.error("判断缓存存在失败key[" + key + ", error[" + t + "]");
        }
        return false;
    }

    /**
     * 移除缓存
     * @param k
     * @return
     */
    public boolean removeValue(String k) {
        return remove(KEY_PREFIX_VALUE + k);
    }

    public boolean removeSet(String k) {
        return remove(KEY_PREFIX_SET + k);
    }

    public boolean removeList(String k) {
        return remove(KEY_PREFIX_LIST + k);
    }

    
    public boolean removeMap(String k) {
        return remove(KEY_PREFIX_MAP + k);
    }
    
    /**
     * 移除缓存
     * @param key
     * @return
     */
    public boolean remove(String key) {
        try {
            redisTemplate.delete(key);
            return true;
        } catch (Throwable t) {
            logger.error("获取缓存失败key[" + key + ", error[" + t + "]");
        }
        return false;
    }
    /**
     * 缓存set操作
     * @param k
     * @param v
     * @param time
     * @return
     */
    public boolean cacheSet(String k, String v, long time) {
        String key = KEY_PREFIX_SET + k;
        try {
            SetOperations<String, String> valueOps =  redisTemplate.opsForSet();
            valueOps.add(key, v);
            if (time > 0) redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存set
     * @param k
     * @param v
     * @return
     */
    public boolean cacheSet(String k, String v) {
        return cacheSet(k, v, -1);
    }

    /**
     * 缓存set
     * @param k
     * @param v
     * @param time
     * @return
     */
    public boolean cacheSet(String k, Set<String> v, long time) {
        String key = KEY_PREFIX_SET + k;
        try {
            SetOperations<String, String> setOps =  redisTemplate.opsForSet();
            setOps.add(key, v.toArray(new String[v.size()]));
            if (time > 0) redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存set
     * @param k
     * @param v
     * @return
     */
    public boolean cacheSet(String k, Set<String> v) {
        return cacheSet(k, v, -1);
    }

    /**
     * 获取缓存set数据
     * @param k
     * @return
     */
    public Set<String> getSet(String k) {
        try {
            SetOperations<String, String> setOps = redisTemplate.opsForSet();
            return setOps.members(KEY_PREFIX_SET + k);
        } catch (Throwable t) {
            logger.error("获取set缓存失败key[" + KEY_PREFIX_SET + k + ", error[" + t + "]");
        }
        return null;
    }

    /**
     * list缓存
     * @param k
     * @param v
     * @param time
     * @return
     */
    public boolean cacheList(String k, String v, long time) {
        String key = KEY_PREFIX_LIST + k;
        try {
            ListOperations<String, String> listOps =  redisTemplate.opsForList();
            listOps.rightPush(key, v);
            if (time > 0) redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存list
     * @param k
     * @param v
     * @return
     */
    public boolean cacheList(String k, String v) {
        return cacheList(k, v, -1);
    }

    /**
     * 缓存list
     * @param k
     * @param v
     * @param time
     * @return
     */
    public boolean cacheList(String k, List<String> v, long time) {
        String key = KEY_PREFIX_LIST + k;
        try {
            ListOperations<String, String> listOps =  redisTemplate.opsForList();
            listOps.rightPushAll(key, v);
            if (time > 0) redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存list
     * @param k
     * @param v
     * @return
     */
    public boolean cacheList(String k, List<String> v) {
       return cacheList(k, v, -1);
    }

    /**
     * 获取list缓存
     * @param k
     * @param start
     * @param end
     * @return
     */
    public List<String> getList(String k, long start, long end) {
        try {
            ListOperations<String, String> listOps =  redisTemplate.opsForList();
            return listOps.range(KEY_PREFIX_LIST + k, start, end);
        } catch (Throwable t) {
            logger.error("获取list缓存失败key[" + KEY_PREFIX_LIST + k + ", error[" + t + "]");
        }
        return null;
    }

    /**
     * 获取总条数, 可用于分页
     * @param k
     * @return
     */
    public long getListSize(String k) {
        try {
            ListOperations<String, String> listOps =  redisTemplate.opsForList();
            return listOps.size(KEY_PREFIX_LIST + k);
        } catch (Throwable t) {
            logger.error("获取list长度失败key[" + KEY_PREFIX_LIST + k + "], error[" + t + "]");
        }
        return 0;
    }

    /**
     * 获取总条数, 可用于分页
     * @param listOps
     * @param k
     * @return
     */
    public long getListSize(ListOperations<String, String> listOps, String k) {
        try {
            return listOps.size(KEY_PREFIX_LIST + k);
        } catch (Throwable t) {
            logger.error("获取list长度失败key[" + KEY_PREFIX_LIST + k + "], error[" + t + "]");
        }
        return 0;
    }

    /**
     * 移除list缓存
     * @param k
     * @return
     */
    public boolean removeOneOfList(String k) {
        String key = KEY_PREFIX_LIST + k;
        try {
            ListOperations<String, String> listOps =  redisTemplate.opsForList();
            listOps.rightPop(key);
            return true;
        } catch (Throwable t) {
            logger.error("移除list缓存失败key[" + KEY_PREFIX_LIST + k + ", error[" + t + "]");
        }
        return false;
    }
    
    /**
     * 增加map缓存
     * @param k
     * @return
     */
    public boolean cacheMap(String k,Map<String,Object> v) {
    	String key = KEY_PREFIX_MAP + k;
        try {
        	HashOperations<String, String, Object> hashOps =  redisTemplate.opsForHash();
        	hashOps.putAll(key, v);
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }
    
    /**
     * 获取map缓存
     * @param k
     * @return
     */
    public Map<String,Object> getMap(String k) {
        try {
        	HashOperations<String, String, Object> hashOps =  redisTemplate.opsForHash();
            return hashOps.entries(KEY_PREFIX_MAP + k);
        } catch (Throwable t) {
            logger.error("获取map缓存失败key[" + KEY_PREFIX_MAP + k + ", error[" + t + "]");
        }
        return null;
    }
    
    public boolean cacheObject(ServiceImRedis obj) {
    	try {
	    	final byte[] value = SerializeUtil.serialize(obj);
	        redisTemplate.execute((RedisCallback<Void>) connection -> {
	            connection.set(obj.getKey().getBytes(), value);
	            return null;
	        });
	        return true;
        } catch (Throwable t) {
            logger.error("缓存[" + obj.getKey() + "]失败, value[" + obj.toString() + "]", t);
        }
        return false;
    }
    
    public void removeObj(String key) {  
        redisTemplate.execute((RedisCallback<Void>) connection -> {
            connection.del(key.getBytes());
            return null;
        });
    }  
  
    public ServiceImRedis getObj(String key) {  
    	byte[] result = redisTemplate.execute((RedisCallback<byte[]>) connection -> connection.get(key.getBytes()));
        if (result == null) {
            return null;
        }
        return (ServiceImRedis) SerializeUtil.unserialize(result);
    } 
}
