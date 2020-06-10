package com.fcgs.base.domain.gene;

import java.io.Serializable;

/**
 * 峰图质量值
 * @author jiang
 * @date 2015年8月7日下午3:02:51
 */
public interface Score extends Serializable {
	/**
	 * 设置峰图质量
	 * @author jiangbin
	 * @param score
	 * @date 2014年3月21日上午11:09:44
	 */
	public void setScore(float score);

	/**
	 * 获取峰图质量
	 * @author jiangbin
	 * @return
	 * @date 2014年3月21日上午11:09:45
	 */
	public float getScore();
}
