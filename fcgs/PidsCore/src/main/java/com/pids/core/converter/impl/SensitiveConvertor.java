package com.pids.core.converter.impl;

import com.pids.core.converter.ListConverterTemplate;
import com.pids.core.exception.ICoreException;
import com.pids.core.utils.StringEx;

/**
 * 字符串大小写转换器，会过自动滤掉null和空字符串对象，可以设置转换为大写还是小写，默认为小写模式
 * @Author Andory
 * @Date 2012-8-21上午11:27:57
 */
public class SensitiveConvertor extends ListConverterTemplate<String, String> {
	private boolean toLower;

	public SensitiveConvertor() {
		this.toLower = true;
	}

	@Override
	protected String convert(String source, boolean isInternal) throws ICoreException {
		if (StringEx.isNull(source)) {
			return null;
		}
		if (toLower) {
			return source.toLowerCase();
		} else {
			return source.toUpperCase();
		}
	}

	/**
	 * 设置将字符串转换为小写还是大写模式
	 * @Author Andory
	 * @param toLower true/false--转换成小写/大写
	 * @Date 2012-8-21上午11:32:44
	 */
	public void setToLower(boolean toLower) {
		this.toLower = toLower;
	}

	/**
	 * 获取当前转换的模式是小写还是大写模式，默认为小写模式
	 * @Author Andory
	 * @return true/false--小写模式,大写模式
	 * @Date 2012-8-21上午11:32:46
	 */
	public boolean isToLower() {
		return toLower;
	}
}
