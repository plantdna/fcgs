package com.pids.core.mapper;

import com.pids.core.exception.ICoreException;

/**
 * 基于数据接口{@link MapperKey}的分组mapper：key/value--MapperKey的getMapperKey()接口返回值/分组数据对象列表
 * @author jiangbin
 * @date 2013-4-20上午10:16:16
 */
public class VGroupMapper<V extends MapperKey> extends GroupMapperTemplate<V, V> {
	private static final long serialVersionUID = 6093182041590402250L;

	@Override
	public V convert(V source) throws ICoreException {
		return source;
	}

	@Override
	protected String getMapperKey(V object) {
		return object.getMapperKey();
	}

}
