package com.fcgs.core.comparer.domain;

/**
 * 快速比对结果信息对象实现类
 * @author Jiangbin
 * @date 2014-3-4上午12:28:55
 */
public class SimpleSmartComaprerResult extends SimpleComparerResult implements SmartComparerResult {

	private static final long serialVersionUID = 1515824993746149703L;
	private int diffCount;
	private int sameCount;

	@Override
	public int getDiffCount() {
		return diffCount;
	}

	@Override
	public void setDiffCount(int diffCount) {
		this.diffCount = diffCount;
	}

	@Override
	public int getSameCount() {
		return sameCount;
	}

	@Override
	public void setSameCount(int sameCount) {
		this.sameCount = sameCount;
	}
}
