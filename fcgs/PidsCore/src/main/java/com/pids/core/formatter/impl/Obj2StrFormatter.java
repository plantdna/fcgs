package com.pids.core.formatter.impl;

/**
 * 对象类型格式化，主要是对日期(可以设定日期格式)对象进行格式化，其它对象类型将调用对象的toString()进行格式化
 * @author Andory
 * @date 2012-7-24上午08:01:10
 */
public class Obj2StrFormatter extends AbstractObj2StrFormatter {
	@Override
	protected String decorate(Object source) {
		return null;
	}
}
