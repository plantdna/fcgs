package com.fcgs.base.domain;

import java.io.Serializable;

/**
 * 检测技术平台代码接口
 * @author Jiangbin
 * @date 2015年7月7日下午2:33:07
 */
public interface PlatformCode extends Serializable {
	/**
	 * 设置检测技术平台代码
	 * @author Jiangbin
	 * @param platformCode
	 * @date 2015年7月5日上午12:29:54
	 */
	public void setPlatformCode(String platformCode);
	
	/**
	 * 获取检测技术平台代码
	 * @author Jiangbin
	 * @return
	 * @date 2015年7月5日上午12:29:55
	 */
	public String getPlatformCode();
}
