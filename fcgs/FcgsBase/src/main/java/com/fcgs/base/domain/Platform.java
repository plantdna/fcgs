package com.fcgs.base.domain;

import java.io.Serializable;

/**
 * 检测技术平台信息
 * @author Jiangbin
 * @date 2015年7月5日上午12:26:25
 */
public interface Platform extends Serializable, PlatformCode {
	
	/**
	 * 设置检测技术平台名称
	 * @author Jiangbin
	 * @param platformName
	 * @date 2015年7月5日上午12:29:52
	 */
	public void setPlatformName(String platformName);
	
	/**
	 * 获取检测技术平台名称
	 * @author Jiangbin
	 * @return
	 * @date 2015年7月5日上午12:29:53
	 */
	public String getPlatformName();
	
}
