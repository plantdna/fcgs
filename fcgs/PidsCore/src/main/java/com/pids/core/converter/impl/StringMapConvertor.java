package com.pids.core.converter.impl;

import com.pids.core.converter.ListConverterTemplate;
import com.pids.core.exception.ICoreException;
import org.apache.commons.collections.MapUtils;

import java.util.Map;

/**
 * 字符串间的映射关系转换器
 * @author jiangbin
 * @date 2015年10月22日下午7:16:40
 */
public class StringMapConvertor extends ListConverterTemplate<String, String> {
	private Map<String, String> mapper;
	
	public StringMapConvertor(Map<String, String> mapper) {
		this.mapper = mapper;
	}
	
	@Override
	protected String convert(String source, boolean isInternal) throws ICoreException {
		if (MapUtils.isEmpty(this.mapper)) {
			return null;
		}
		return this.mapper.get(source);
	}
	
	/**
	 * 设置字符串映射关系表
	 * @author jiangbin
	 * @param mapper
	 * @date 2015年10月22日下午7:16:19
	 */
	public void setMapper(Map<String, String> mapper) {
		this.mapper = mapper;
	}
	
	/**
	 * 获取字符串映射关系表
	 * @author jiangbin
	 * @return
	 * @date 2015年10月22日下午7:16:21
	 */
	public Map<String, String> getMapper() {
		return mapper;
	}
}
