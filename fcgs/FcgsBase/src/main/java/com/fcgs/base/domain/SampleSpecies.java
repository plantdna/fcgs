package com.fcgs.base.domain;

import java.io.Serializable;

/**
 * 样品种属信息接口
 * @author Jiangbin
 * @date 2014-4-25下午10:31:19
 */
public interface SampleSpecies extends Serializable {
	/**
	 * 设置样品种属
	 * @author Jiangbin
	 * @param samSpecies
	 * @date 2014-4-25下午10:34:38
	 */
	public void setSamSpecies(String samSpecies);

	/**
	 * 获取样品种属
	 * @author Jiangbin
	 * @return
	 * @date 2014-4-25下午10:34:37
	 */
	public String getSamSpecies();
}
