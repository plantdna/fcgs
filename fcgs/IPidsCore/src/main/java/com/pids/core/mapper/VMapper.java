package com.pids.core.mapper;

/**
 * 基于数据接口{@link MapperKey}的mapper：key/value--MapperKey的getMapperKey()接口返回值/数据对象
 * @author jiangbin
 * @date 2013-4-20上午10:18:15
 */
public class VMapper<V extends MapperKey> extends MapperTemplate<V, V> {

	private static final long serialVersionUID = 4133773235893730180L;

	@Override
	protected String getMapperKey(V object) {
		return object.getMapperKey();
	}

	@Override
	protected V getMapperValue(V object) {
		return object;
	}

}
