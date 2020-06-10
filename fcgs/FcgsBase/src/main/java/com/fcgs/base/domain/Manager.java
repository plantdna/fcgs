package com.fcgs.base.domain;

import java.io.Serializable;

/**
 * 负责人信息接口
 * @author Jiangbin
 * @date 2014-4-25下午10:31:19
 */
public interface Manager extends Serializable {
	/**
	 * 设置负责人帐号
	 * @author Jiangbin
	 * @param samSpecies
	 * @date 2014-4-25下午10:34:38
	 */
	public void setManager(String manager);

	/**
	 * 获取负责人帐号
	 * @author Jiangbin
	 * @return
	 * @date 2014-4-25下午10:34:37
	 */
	public String getManager();
}
