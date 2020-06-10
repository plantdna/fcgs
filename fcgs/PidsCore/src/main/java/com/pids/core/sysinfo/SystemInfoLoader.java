package com.pids.core.sysinfo;

import com.pids.core.exception.ICoreException;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Properties;

/**
 * 系统信息加载器
 * @author Jiangbin
 * @date 2015年7月18日下午12:42:42
 */
public class SystemInfoLoader {

	/**
	 * 加载系统信息
	 * @author Jiangbin
	 * @return
	 * @throws ICoreException
	 * @date 2015年7月18日下午12:42:41
	 */
	public static SystemInfo load() throws ICoreException {
		try {
			Runtime runtime = Runtime.getRuntime();
			Properties props = System.getProperties();
			InetAddress addr = InetAddress.getLocalHost();
			Map<String, String> map = System.getenv();

			SystemInfo info = new SimpleSystemInfo();
			info.setComputerName(map.get("COMPUTERNAME"));
			info.setFreeMemory(runtime.freeMemory());
			info.setTotalMemory(runtime.totalMemory());
			info.setMaxMemory(runtime.maxMemory());
			info.setHostName(map.get("USERNAME"));
			info.setIp(addr.getHostAddress());
			info.setJavaHome(props.getProperty("java.home"));
			info.setJavaVendor(props.getProperty("java.vendor"));
			info.setJavaVendorUrl(props.getProperty("java.vendor.url"));
			info.setJavaVersion(props.getProperty("java.version"));
			info.setJavaVmName(props.getProperty("java.vm.name"));
			info.setJavaVmVendor(props.getProperty("java.vm.vendor"));
			info.setOsArch(props.getProperty("os.arch"));
			info.setOsName(props.getProperty("os.name"));
			info.setOsVersion(props.getProperty("os.version"));
			info.setProcessorsCount(runtime.availableProcessors());
			info.setUserDomain(map.get("USERDOMAIN"));
			info.setUserName(props.getProperty("user.name"));
			return info;
		} catch (UnknownHostException e) {
			throw new ICoreException(e);
		}
	}
}
