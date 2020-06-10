package com.pids.core.sysinfo;

import com.pids.core.utils.SizeFormater;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SimpleSystemInfo implements SystemInfo {
	private static final long serialVersionUID = -46287010198197919L;
	private String userName;
	private String computerName;
	private String userDomain;
	private String ip;
	private String hostName;
	private long totalMemory;
	private long freeMemory;
	private long maxMemory;
	private int processorsCount;
	private String javaVersion;
	private String javaVendor;
	private String javaVendorUrl;
	private String javaHome;
	private String javaVmVendor;
	private String javaVmName;
	private String osName;
	private String osArch;
	private String osVersion;

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String getComputerName() {
		return computerName;
	}

	@Override
	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}

	@Override
	public String getUserDomain() {
		return userDomain;
	}

	@Override
	public void setUserDomain(String userDomain) {
		this.userDomain = userDomain;
	}

	@Override
	public String getIp() {
		return ip;
	}

	@Override
	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String getHostName() {
		return hostName;
	}

	@Override
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	@Override
	public long getTotalMemory() {
		return totalMemory;
	}

	@Override
	public String getTotalMemoryStr() {
		return new SizeFormater().format(this.totalMemory);
	}

	@Override
	public void setTotalMemory(long totalMemory) {
		this.totalMemory = totalMemory;
	}

	@Override
	public long getFreeMemory() {
		return freeMemory;
	}

	@Override
	public String getFreeMemoryStr() {
		return new SizeFormater().format(this.freeMemory);
	}

	@Override
	public void setFreeMemory(long freeMemory) {
		this.freeMemory = freeMemory;
	}

	@Override
	public void setMaxMemory(long maxMemory) {
		this.maxMemory = maxMemory;
	}

	@Override
	public String getMaxMemoryStr() {
		return new SizeFormater().format(this.maxMemory);
	}

	@Override
	public long getMaxMemory() {
		return maxMemory;
	}

	@Override
	public long getTotalFreeMemory() {
		return this.maxMemory - this.getUsedMemory();
	}

	@Override
	public String getTotalFreeMemoryStr() {
		return new SizeFormater().format(getTotalFreeMemory());
	}

	@Override
	public long getUsedMemory() {
		return this.totalMemory - this.freeMemory;
	}

	@Override
	public String getUsedMemoryStr() {
		return new SizeFormater().format(this.getUsedMemory());
	}

	@Override
	public boolean isMemoryWarning(long limit) {
		return this.getTotalFreeMemory() <= limit;
	}

	@Override
	public boolean isMemoryWarning(String limit) {
		long limitSize = new SizeFormater().format(limit);
		if (limitSize == 0) {
			return false;
		}
		return this.isMemoryWarning(limitSize);
	}

	@Override
	public int getProcessorsCount() {
		return processorsCount;
	}

	@Override
	public void setProcessorsCount(int processorsCount) {
		this.processorsCount = processorsCount;
	}

	@Override
	public String getJavaVersion() {
		return javaVersion;
	}

	@Override
	public void setJavaVersion(String javaVersion) {
		this.javaVersion = javaVersion;
	}

	@Override
	public String getJavaVendor() {
		return javaVendor;
	}

	@Override
	public void setJavaVendor(String javaVendor) {
		this.javaVendor = javaVendor;
	}

	@Override
	public String getJavaVendorUrl() {
		return javaVendorUrl;
	}

	@Override
	public void setJavaVendorUrl(String javaVendorUrl) {
		this.javaVendorUrl = javaVendorUrl;
	}

	@Override
	public String getJavaHome() {
		return javaHome;
	}

	@Override
	public void setJavaHome(String javaHome) {
		this.javaHome = javaHome;
	}

	@Override
	public String getJavaVmVendor() {
		return javaVmVendor;
	}

	@Override
	public void setJavaVmVendor(String javaVmVendor) {
		this.javaVmVendor = javaVmVendor;
	}

	@Override
	public String getJavaVmName() {
		return javaVmName;
	}

	@Override
	public void setJavaVmName(String javaVmName) {
		this.javaVmName = javaVmName;
	}

	@Override
	public String getOsName() {
		return osName;
	}

	@Override
	public void setOsName(String osName) {
		this.osName = osName;
	}

	@Override
	public String getOsArch() {
		return osArch;
	}

	@Override
	public void setOsArch(String osArch) {
		this.osArch = osArch;
	}

	@Override
	public String getOsVersion() {
		return osVersion;
	}

	@Override
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
