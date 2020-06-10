package com.pids.core.sorter.impl;

import com.pids.core.mapper.MapperTemplate;

/**
 *  简单的{@link SortItem} 接口调用类，实现了{@link SortItem}接口的类能够生成一个Mapper对象
 * @author LiuJunGuang
 * @date 2012-12-12下午3:23:06
 */
public class SortItemMapper<V, T extends SortItem<V>> extends MapperTemplate<V, T> {
	private static final long serialVersionUID = -2417755317162839920L;

	@Override
	protected String getMapperKey(T object) {
		return object.getKey();
	}

	@Override
	protected V getMapperValue(T object) {
		return object.getValue();
	}

}
