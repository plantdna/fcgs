package com.pids.core.sysinfo;

import java.io.Serializable;

/**
 * 系统信息接口
 * @author Jiangbin
 * @date 2015年7月18日下午12:38:51
 */
public interface SystemInfo extends Serializable {

	/**
	 * 设置用户名，如：Jiangbin
	 * @author Jiangbin
	 * @return
	 * @date 2015年7月18日下午12:27:27
	 */
	public String getUserName();

	/**
	 * 获取用户名，如：Jiangbin
	 * @author Jiangbin
	 * @param userName
	 * @date 2015年7月18日下午12:27:28
	 */
	public void setUserName(String userName);

	/**
	 * 获取计算机名，如：JIANGBIN-PC
	 * @author Jiangbin
	 * @return
	 * @date 2015年7月18日下午12:27:29
	 */
	public String getComputerName();

	/**
	 * 设置计算机名，如：JIANGBIN-PC
	 * @author Jiangbin
	 * @param computerName
	 * @date 2015年7月18日下午12:27:32
	 */
	public void setComputerName(String computerName);

	/**
	 * 获取计算机域名，如：Jiangbin-PC
	 * @author Jiangbin
	 * @return
	 * @date 2015年7月18日下午12:27:31
	 */
	public String getUserDomain();

	/**
	 * 设置计算机域名，如：Jiangbin-PC
	 * @author Jiangbin
	 * @param userDomain
	 * @date 2015年7月18日下午12:27:34
	 */
	public void setUserDomain(String userDomain);

	/**
	 * 获取本地ip地址
	 * @author Jiangbin
	 * @return
	 * @date 2015年7月18日下午12:27:36
	 */
	public String getIp();

	/**
	 * 设置本地ip地址
	 * @author Jiangbin
	 * @param ip
	 * @date 2015年7月18日下午12:27:36
	 */
	public void setIp(String ip);

	/**
	 * 获取本地主机名，如：Jiangbin-PC
	 * @author Jiangbin
	 * @return
	 * @date 2015年7月18日下午12:27:38
	 */
	public String getHostName();

	/**
	 * 设置本地主机名，如：Jiangbin-PC
	 * @author Jiangbin
	 * @param hostName
	 * @date 2015年7月18日下午12:27:39
	 */
	public void setHostName(String hostName);

	/**
	 * 获取JVM可以使用的总内存
	 * @author Jiangbin
	 * @return
	 * @date 2015年7月18日下午12:27:41
	 */
	public long getTotalMemory();

	/**
	 * 设置JVM可以使用的总内存
	 * @author Jiangbin
	 * @param totalMemory
	 * @date 2015年7月18日下午12:27:41
	 */
	public void setTotalMemory(long totalMemory);

	/**
	 * 获取JVM可以使用的剩余内存
	 * @author Jiangbin
	 * @return
	 * @date 2015年7月18日下午12:27:43
	 */
	public long getFreeMemory();

	/**
	 * 设置JVM可以使用的剩余内存
	 * @author Jiangbin
	 * @param freeMemory
	 * @date 2015年7月18日下午12:27:44
	 */
	public void setFreeMemory(long freeMemory);

	/**
	 * 设置JVM最大内存
	 * @author Jiangbin
	 * @param maxMemory
	 * @date 2015年7月18日下午12:56:51
	 */
	public void setMaxMemory(long maxMemory);

	/**
	 * 获取JVM最大内存
	 * @author Jiangbin
	 * @return
	 * @date 2015年7月18日下午12:56:52
	 */
	public long getMaxMemory();

	/**
	 * 获取JVM总内存格式化后的字符串表示
	 * @author Jiangbin
	 * @return
	 * @date 2015年7月18日下午1:15:41
	 */
	public String getTotalMemoryStr();

	/**
	 * 获取JVM剩余内存格式化后的字符串表示
	 * @author Jiangbin
	 * @return
	 * @date 2015年7月18日下午1:15:42
	 */
	public String getFreeMemoryStr();

	/**
	 * 获取JVM最大内存格式化后的字符串表示
	 * @author Jiangbin
	 * @return
	 * @date 2015年7月18日下午1:15:44
	 */
	public String getMaxMemoryStr();

	/**
	 * 获取JVM内存总余量
	 * @author Jiangbin
	 * @return
	 * @date 2015年7月18日下午1:32:17
	 */
	public long getTotalFreeMemory();

	/**
	 * JVM内存总余量格式化后的字符串表示
	 * @author Jiangbin
	 * @return
	 * @date 2015年7月18日下午1:32:19
	 */
	public String getTotalFreeMemoryStr();

	/**
	 * 获取JVM已使用内存
	 * @author Jiangbin
	 * @return
	 * @date 2015年7月18日下午1:24:27
	 */
	public long getUsedMemory();

	/**
	 * JVM已使用内存格式化后的字符串表示
	 * @author Jiangbin
	 * @return
	 * @date 2015年7月18日下午1:24:26
	 */
	public String getUsedMemoryStr();

	/**
	 * 判定是否内存用量预警，一旦内存余量小于等于指定限量值时将预警
	 * @author Jiangbin
	 * @param limit 内存余量限定值，单位为字节
	 * @return true/false--内存预警/内存充沛
	 * @date 2015年7月18日下午1:26:41
	 */
	public boolean isMemoryWarning(long limit);

	/**
	 * 判定是否内存用量预警，一旦内存余量小于等于指定限量值时将预警
	 * @author Jiangbin
	 * @param limit 内存余量限定值，格式如：<b>5KB、5MB、5GB、5TB</b>，且后面的单位不区分大小写，并且<b>只支持整数</b>，对于5.3GB这样的值将返回0
	 * @return
	 * @date 2015年7月18日下午1:51:11
	 */
	public boolean isMemoryWarning(String limit);

	/**
	 * 获取JVM可以使用的处理器个数，如：4
	 * @author Jiangbin
	 * @return
	 * @date 2015年7月18日下午12:27:46
	 */
	public int getProcessorsCount();

	/**
	 * 设置JVM可以使用的处理器个数，如：4
	 * @author Jiangbin
	 * @param processorsCount
	 * @date 2015年7月18日下午12:27:45
	 */
	public void setProcessorsCount(int processorsCount);

	/**
	 * 获取Java的运行环境版本，如：1.7.0_51
	 * @author Jiangbin
	 * @return
	 * @date 2015年7月18日下午12:27:48
	 */
	public String getJavaVersion();

	/**
	 * 设置Java的运行环境版本，如：1.7.0_51
	 * @author Jiangbin
	 * @param javaVersion
	 * @date 2015年7月18日下午12:27:47
	 */
	public void setJavaVersion(String javaVersion);

	/**
	 * 获取Java的运行环境供应商，如：Oracle Corporation
	 * @author Jiangbin
	 * @return
	 * @date 2015年7月18日下午12:27:50
	 */
	public String getJavaVendor();

	/**
	 * 设置Java的运行环境供应商，如：Oracle Corporation
	 * @author Jiangbin
	 * @param javaVendor
	 * @date 2015年7月18日下午12:27:50
	 */
	public void setJavaVendor(String javaVendor);

	/**
	 * 获取Java供应商的URL，如：http://java.oracle.com/
	 * @author Jiangbin
	 * @return
	 * @date 2015年7月18日下午12:27:52
	 */
	public String getJavaVendorUrl();

	/**
	 * 设置Java供应商的URL，如：http://java.oracle.com/
	 * @author Jiangbin
	 * @param javaVendorUrl
	 * @date 2015年7月18日下午12:27:54
	 */
	public void setJavaVendorUrl(String javaVendorUrl);

	/**
	 * 获取Java的安装路径，如：C:\Program Files\Java\jre7
	 * @author Jiangbin
	 * @return
	 * @date 2015年7月18日下午12:27:53
	 */
	public String getJavaHome();

	/**
	 * 设置Java的安装路径，如：C:\Program Files\Java\jre7
	 * @author Jiangbin
	 * @param javaHome
	 * @date 2015年7月18日下午12:27:56
	 */
	public void setJavaHome(String javaHome);

	/**
	 * 获取Java的虚拟机实现供应商，如：Oracle Corporation
	 * @author Jiangbin
	 * @return
	 * @date 2015年7月18日下午12:27:58
	 */
	public String getJavaVmVendor();

	/**
	 * 设置Java的虚拟机实现供应商，如：Oracle Corporation
	 * @author Jiangbin
	 * @param javaVmVendor
	 * @date 2015年7月18日下午12:27:57
	 */
	public void setJavaVmVendor(String javaVmVendor);

	/**
	 * 获取Java的虚拟机实现名称，如：Java HotSpot(TM) 64-Bit Server VM
	 * @author Jiangbin
	 * @return
	 * @date 2015年7月18日下午12:28:00
	 */
	public String getJavaVmName();

	/**
	 * 设置Java的虚拟机实现名称，如：Java HotSpot(TM) 64-Bit Server VM
	 * @author Jiangbin
	 * @param javaVmName
	 * @date 2015年7月18日下午12:27:59
	 */
	public void setJavaVmName(String javaVmName);

	/**
	 * 获取操作系统的名称，如：Windows 7
	 * @author Jiangbin
	 * @return
	 * @date 2015年7月18日下午12:28:01
	 */
	public String getOsName();

	/**
	 * 设置操作系统的名称，如：Windows 7
	 * @author Jiangbin
	 * @param osName
	 * @date 2015年7月18日下午12:28:03
	 */
	public void setOsName(String osName);

	/**
	 * 获取操作系统的构架，如：amd64
	 * @author Jiangbin
	 * @return
	 * @date 2015年7月18日下午12:28:03
	 */
	public String getOsArch();

	/**
	 * 设置操作系统的构架，如：amd64
	 * @author Jiangbin
	 * @param osArch
	 * @date 2015年7月18日下午12:28:07
	 */
	public void setOsArch(String osArch);

	/**
	 * 获取操作系统的版本，如：6.1
	 * @author Jiangbin
	 * @return
	 * @date 2015年7月18日下午12:28:06
	 */
	public String getOsVersion();

	/**
	 * 设置操作系统的版本，如：6.1
	 * @author Jiangbin
	 * @param osVersion
	 * @date 2015年7月18日下午12:28:08
	 */
	public void setOsVersion(String osVersion);

}