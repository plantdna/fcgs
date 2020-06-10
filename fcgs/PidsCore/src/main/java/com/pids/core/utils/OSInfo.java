package com.pids.core.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 操作系统信息
 * @author jiangbin
 * @date 2012-4-30下午2:02:32
 */
public class OSInfo {
	private static Log log=LogFactory.getLog(OSInfo.class);
	private static String OS_NAME="";
	private static String PROP_SUFFIX=".LINUX";
	
	/**
	 * @Description: 获取操作系统平台名称
	 * @author: jiangbin
	 * @return
	 * @date: 2011-7-26上午09:32:27
	 */
	public static String getOSName(){
		return System.getProperty("os.name");
	}
	
	/**
	 * 属性名后缀，此前缀有二：".WINDOWS"、".LINUX"，此前缀值分别对应到Windows和Linux两类操作系统
	 * @author Andory
	 * @return
	 * @date 2012-5-24下午08:03:24
	 */
	public static String getPropSuffix(){
		return PROP_SUFFIX;
	}
	
	static{
		//操作系统平台判定:windows/linux/mac
		OS_NAME=getOSName();
		log.info("Current OS Name : "+OS_NAME);
		if(OS_NAME.toUpperCase().contains("WINDOWS")){
			PROP_SUFFIX=".WINDOWS";
		}
		if(OS_NAME.toUpperCase().contains("MAC")){
			PROP_SUFFIX=".MAC";
		}
	}
	
}
