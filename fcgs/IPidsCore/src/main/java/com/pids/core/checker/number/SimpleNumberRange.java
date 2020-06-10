package com.pids.core.checker.number;

/**
 * 数字边界范围定义类
 * @author jiang
 * @date 2018年9月21日下午4:42:13
 */
public class SimpleNumberRange implements NumberRange {
	private static final long serialVersionUID = 3345327648493251809L;
	private Number minValue;
	private Number maxValue;

	@Override
	public Number getMinValue() {
		return minValue;
	}

	@Override
	public void setMinValue(Number minValue) {
		this.minValue = minValue;
	}

	@Override
	public Number getMaxValue() {
		return maxValue;
	}

	@Override
	public void setMaxValue(Number maxValue) {
		this.maxValue = maxValue;
	}

}
