package com.pids.core.checker.number;

import java.io.Serializable;

/**
 * 数据范围定义
 * @author jiang
 * @date 2018年9月21日下午4:32:58
 */
public interface NumberRange extends Serializable {
	/**
	 * 获取最小值
	 * @author jiangbin
	 * @return
	 * @date 2012-11-6下午3:29:35
	 */
	Number getMinValue();

	/**
	 * 设置最小值
	 * @author jiangbin
	 * @param minValue
	 * @date 2012-11-6下午3:29:37
	 */
	void setMinValue(Number minValue);

	/**
	 * 获取最大值
	 * @author jiangbin
	 * @return
	 * @date 2012-11-6下午3:29:38
	 */
	Number getMaxValue();

	/**
	 * 设置最大值
	 * @author jiangbin
	 * @param maxValue
	 * @date 2012-11-6下午3:29:39
	 */
	void setMaxValue(Number maxValue);
}
