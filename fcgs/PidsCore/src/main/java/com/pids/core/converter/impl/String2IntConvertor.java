package com.pids.core.converter.impl;

import com.pids.core.converter.ListConverterTemplate;
import com.pids.core.exception.ICoreException;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 字符串列表转换成数字列表，若无法转换则置为0
 * @author jiangbin
 * @date 2014年7月22日下午4:19:09
 */
public class String2IntConvertor extends ListConverterTemplate<String, Integer> {
	@Override
	protected Integer convert(String source, boolean isInternal) throws ICoreException {
		return NumberUtils.toInt(source);
	}
}
