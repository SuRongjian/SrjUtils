package com.srj.util.file;

import java.util.Properties;
/**
 * 读取配置文件
 *
 */
public class ReadConfigInfo {
	private static Properties cache = new Properties();
	static{
		try {
			cache.load(ReadConfigInfo.class.getClassLoader().getResourceAsStream("merchantInfo.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取指定key的值
	 * @param key
	 * @return
	 */
	public static String getValue(String key){
		return cache.getProperty(key);
	}
}
