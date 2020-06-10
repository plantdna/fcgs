package com.fcgs.core.comparer.domain;

/**
 * 快速比对结果信息对象
 * @author jiangbin
 * @date 2012-10-29上午10:51:20
 */
public interface SmartComparerResult extends ComparerResult {
	/**
	 * 设置无差异位点数
	 * @author jiangbin
	 * @param sameCount
	 * @date 2018年8月28日下午2:04:34
	 */
	void setSameCount(int sameCount);

	/**
	 * 获取无差异位点数
	 * @author jiangbin
	 * @return
	 * @date 2018年8月28日下午2:04:36
	 */
	int getSameCount();

	/**
	 * 设置差异位点数
	 * @author jiangbin
	 * @param diffCount
	 * @date 2018年8月28日下午2:04:38
	 */
	void setDiffCount(int diffCount);

	/**
	 * 获取差异位点数
	 * @author jiangbin
	 * @return
	 * @date 2018年8月28日下午2:04:40
	 */
	int getDiffCount();
}
