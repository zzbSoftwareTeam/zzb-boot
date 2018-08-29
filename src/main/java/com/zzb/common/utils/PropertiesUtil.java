package com.zzb.common.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * classes下面的属性文件读写工具 
 * zzb
 *
 */
public class PropertiesUtil {
	// 根据key读取value
	public static String readValue(String filePath, String key) {
		InputStream in = null;
		String value = null;
		try {
			Properties props = new Properties();
			in = PropertiesUtil.class.getResourceAsStream(filePath);
			//String path = PropertiesUtil.class.getClassLoader().getResource(filePath).getPath();
			//in = new FileInputStream(path);
			props.load(in);
			if (props.containsKey(key)) {
				value = props.getProperty(key);
			}
		} catch (Exception e) {
		} finally {
			try {
				if (in != null) {
					in.close();
					in = null;
				}
			} catch (Exception e) {
			}
		}
		return value;
	}

	// 写入properties信息
	public static void writeProperties(String filePath, String parameterName, String parameterValue) {
		OutputStream fos = null;
		try {
			Properties prop = new Properties();
			fos = new FileOutputStream(PropertiesUtil.class.getResource("/").getPath() + filePath);
			prop.setProperty(parameterName, parameterValue);
			prop.store(fos, "Update '" + parameterName + "' value");
		} catch (IOException e) {
		} finally {
			try {
				if (fos != null) {
					fos.close();
					fos = null;
				}
			} catch (Exception e) {
			}
		}
	}
}
