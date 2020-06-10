package com.pids.core.mapper;

import org.apache.commons.lang3.StringUtils;

/**
 * Mapper模板实现类
 * @author Andory
 * @date 2012-6-9上午12:13:20
 */
public abstract class MapperTemplate<V, S> extends ObjectMapperTemplate<String, V, S> {
	private static final long serialVersionUID = -2590009870731959315L;

	@Override
	protected boolean isAcceptKey(String key) {
		return StringUtils.isNotBlank(key);
	}
}
