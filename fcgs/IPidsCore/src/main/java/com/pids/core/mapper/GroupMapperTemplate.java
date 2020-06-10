package com.pids.core.mapper;

import org.apache.commons.lang3.StringUtils;

/**
 * 支持分组功能的Mapper模板类
 * @author jiangbin
 * @date 2013-3-31上午11:19:33
 */
public abstract class GroupMapperTemplate<V, S> extends ObjectGroupMapperTemplate<String, V, S> {

	private static final long serialVersionUID = -8636199403784157116L;

	@Override
	protected boolean isAcceptKey(String key) {
		return StringUtils.isNotBlank(key);
	}


}
