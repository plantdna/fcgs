package com.pids.core.checker.number;

import com.pids.core.exception.ICoreException;

/**
 * 数据范围检测接口
 * @author jiang
 * @date 2018年9月21日下午4:31:38
 */
public interface NumberRangeChecker extends NumberRange, DoubleRangeChecker, FloatRangeChecker, IntRangeChecker,
		LongRangeChecker, ShortRangeChecker, ByteRangeChecker {
	/**
	 * <pre>检查给定数据值是否在给定限定范围内，验证步骤如下：
	 * 1、若未给限定范围参数则返回true
	 * 2、若给定至少一个限定范围参数且未给定被检查值则返回false
	 * 3、若给定至少一个限定范围参数且给定被 检查参数则检查参数是否在范围内，
	 * 此时会检测给定被检查参数的类型为Integer/Short/Long/Float/Double/Byte中的
	 * 一个并调用对应方法进行核查其是否在范围内</pre>
	 * @author jiang
	 * @param source 被检查数据值，允许给定Null值
	 * @return
	 * @throws ICoreException
	 * @date 2019年6月8日下午1:03:00
	 */
	public boolean check(Number source) throws ICoreException;
}