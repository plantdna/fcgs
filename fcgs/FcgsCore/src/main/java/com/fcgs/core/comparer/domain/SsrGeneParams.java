package com.fcgs.core.comparer.domain;

import java.io.Serializable;

/**
 * 指纹参数信息接口
 * @author jiang
 * @date 2018年9月21日下午2:01:05
 */
public interface SsrGeneParams extends Serializable {
	/**
	 * 设置最大碱基偏移量
	 * @author jiangbin
	 * @param maxSizeOffset
	 * @date 2018年8月27日下午12:17:17
	 */
	public void setMaxSizeOffset(Integer maxSizeOffset);

	/**
	 * 获取最大碱基偏移量
	 * @author jiangbin
	 * @return
	 * @date 2018年8月27日下午12:17:15
	 */
	public Integer getMaxSizeOffset();
}
