package com.zzb.common.utils;

import org.apache.commons.codec.digest.HmacUtils;

/**
 * 
 * ClassName: CodecUtils 
 * @Description: TODO 加密解密工具类
 * @author zengzhibin
 * @date 2017年10月9日
 */
public class CodecUtils {
	
    private static final String KEY="1111112222223333";//私钥 key
    
    //单例
    private CodecUtils(){} 
    
    //静态工厂方法   
    public static CodecUtils getInstance() {  
        return new CodecUtils();  
    }
    
    /**
     * 
     * @Description: TODO 传入明文密码加密
     * @param s
     * @return String  
     * @author zengzhibin
     * @date 2017年10月9日
     */
    public String encodeSha1(String s) {
    	String aaa = HmacUtils.hmacSha1Hex(KEY, s);
        return aaa;
    }
}