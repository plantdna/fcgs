package com.pids.core.mapper.utils;

import com.pids.core.mapper.MapperTemplate;

import java.util.Map;

/**
 * 字符串索引Mapper,key/value---字符串/字符串，
 * 本Mapper通常用来进行快速检索字符串，
 * 因为List列表在查找字符串上效率跟Hashmap有差距，
 * 故通常在需要进行索引时可以使用本Mapper来提高检索效率
 * @author jiangbin
 * @date 2013-4-19下午5:42:52
 */
public class StringIndexMapper extends MapperTemplate<String, String> {

	private static final long serialVersionUID = -3813898511789488047L;

	public StringIndexMapper() {
		super();
	}

	public StringIndexMapper(Map<String, String> mapper) {
		super();
		this.setMapper(mapper);
	}

	@Override
	protected String getMapperKey(String object) {
		return object;
	}

	@Override
	protected String getMapperValue(String object) {
		return object;
	}

}
