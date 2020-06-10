package com.fcgs.base.domain;

import java.io.Serializable;

/**
 * 植物类样品信息接口定义，此接口定义样品是基于条码标识由一组特征信息共同描述的信息
 * @author Jiangbin
 * @date 2014-3-4上午12:18:18
 */
public interface Sample extends Serializable, SampleSpecies, SampleKind, SampleBarcode {

	/**
	 * 设置样品名称
	 * @author Jiangbin
	 * @param samName
	 * @date 2014-3-4上午12:18:35
	 */
	public abstract void setSamName(String samName);

	/**
	 * 获取样品真实名称
	 * @author Jiangbin
	 * @return
	 * @date 2014-3-4上午12:18:37
	 */
	public abstract String getSamName();

	/**
	 * 设置样品名称
	 * @author Jiangbin
	 * @param samRealName
	 * @date 2014-3-4上午12:18:35
	 */
	public abstract void setSamRealName(String samRealName);

	/**
	 * 获取样品真实名称
	 * @author Jiangbin
	 * @return
	 * @date 2014-3-4上午12:18:37
	 */
	public abstract String getSamRealName();

	/**
	 * 设置样品来源
	 * @author Jiangbin
	 * @param samOrigin
	 * @date 2014-3-4上午12:18:33
	 */
	public abstract void setSamOrigin(String samOrigin);

	/**
	 * 获取样品来源
	 * @author Jiangbin
	 * @return
	 * @date 2014-3-4上午12:18:34
	 */
	public abstract String getSamOrigin();

	/**
	 * 设置样品负责人帐号
	 * @author jiangbin
	 * @return
	 * @date 2014年3月21日下午12:44:22
	 */
	public String getSamManager();

	/**
	 * 获取样品负责人帐号
	 * @author jiangbin
	 * @param samManager
	 * @date 2014年3月21日下午12:44:23
	 */
	public void setSamManager(String samManager);

	/**
	 * 获取样品原编号
	 * @author sunqiuyun
	 * @return
	 * @date 2015年9月10日下午2:48:30
	 */
	public String getSamNumber();

	/**
	 * 设置样品原编号
	 * @author sunqiuyun
	 * @param samNumber
	 * @date 2015年9月10日下午2:48:47
	 */
	public void setSamNumber(String samNumber);

	/**
	 * 获取样品可否对照状态值,0/1--不可对照/可对照
	 * @author jiangbin
	 * @return
	 * @date Jul 12, 20193:03:27 PM
	 */
	public String getSamContrast();

	/**
	 * 设置样品可否对照状态值,0/1--不可对照/可对照
	 * @author jiangbin
	 * @param samContrast
	 * @date Jul 12, 20193:03:30 PM
	 */
	public void setSamContrast(String samContrast);
}
