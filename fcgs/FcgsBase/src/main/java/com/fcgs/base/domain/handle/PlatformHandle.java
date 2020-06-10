package com.fcgs.base.domain.handle;

import com.fcgs.base.domain.Platform;
import com.fcgs.base.domain.PlatformCode;

import java.io.Serializable;

/**
 * 检测技术平台信息句柄接口
 * @author Jiangbin
 * @date 2015年7月5日上午12:34:23
 */
public interface PlatformHandle extends Serializable, PlatformCode {
	/**
	 * 设置检测技术平台信息
	 * @author Jiangbin
	 * @param snpPlatform
	 * @date 2015年7月5日上午12:35:05
	 */
	public void setPlatform(Platform snpPlatform);
	
	/**
	 * 获取检测技术平台信息
	 * @author Jiangbin
	 * @return
	 * @date 2015年7月5日上午12:35:05
	 */
	public Platform getPlatform();
	
}
