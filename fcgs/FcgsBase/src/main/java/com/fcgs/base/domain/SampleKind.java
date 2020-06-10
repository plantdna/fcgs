package com.fcgs.base.domain;

import java.io.Serializable;

/**
 * 样品类型信息接口：对于玉米是：自交系/杂交种，其它的植物则需按物种特性进行配置
 * @author Jiangbin
 * @date 2014-4-25下午10:31:19
 */
public interface SampleKind extends Serializable {
	/**
	 * 设置样品类型
	 * @author jiangbin
	 * @return
	 * @date 2014年3月21日下午12:44:22
	 */
	public String getSamKind();

	/**
	 * 获取样品类型
	 * @author jiangbin
	 * @param samManager
	 * @date 2014年3月21日下午12:44:23
	 */
	public void setSamKind(String samKind);
}
