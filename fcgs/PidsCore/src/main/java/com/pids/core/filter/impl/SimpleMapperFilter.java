package com.pids.core.filter.impl;

import com.pids.core.exception.ICoreException;
import com.pids.core.filter.ListFilterTemplate;

import java.util.List;
import java.util.Map;

/**
 * 简单的Mapper过滤器，可以将指定的键名列表对应的键值列表从给定Map过滤出来
 * @author jiangbin
 * @date 2012-10-12下午10:36:55
 */
public class SimpleMapperFilter<T> extends ListFilterTemplate<String, T> {
	private Map<String, T> mapper;

	/**
	 * 获取Mapper
	 * @author jiangbin
	 * @param mapper
	 * @date 2012-10-12下午10:38:24
	 */
	public void setMapper(Map<String, T> mapper) {
		this.mapper = mapper;
	}

	/**
	 * 设置Mapper
	 * @author jiangbin
	 * @return
	 * @date 2012-10-12下午10:38:25
	 */
	public Map<String, T> getMapper() {
		return mapper;
	}

	@Override
	protected T filter(String key, boolean isInternal) throws ICoreException {
		if (this.mapper != null && !this.mapper.isEmpty()) {
			return this.mapper.get(key);
		}
		return null;
	}

	/**
	 * 将给定Key列表按照给定的映射规则过滤出值列表
	 * @author Andory
	 * @param keys Key列表
	 * @param rules 转换规则表
	 * @return 值列表
	 * @throws ICoreException
	 * @date 2013-3-31下午9:26:29
	 */
	public List<T> filter(List<String> keys, Map<String, T> rules) throws ICoreException {
		this.mapper = rules;
		return this.filter(keys);
	}
}
