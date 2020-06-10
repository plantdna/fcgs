package com.pids.core.id.utils;

import com.pids.core.exception.ICoreException;
import com.pids.core.id.ID;
import com.pids.core.mapper.GroupMapperTemplate;

/**
 * ID分组映射信息Mapper,key/value--ID/分组数据对象列表
 * @author jiangbin
 * @date 2015年1月7日上午11:28:02
 */
public class IdGroupMapper<S extends ID> extends GroupMapperTemplate<S, S> {

	private static final long serialVersionUID = -6071263523854280283L;

	@Override
	protected String getMapperKey(S object) {
		return object.getId();
	}

	@Override
	public S convert(S source) throws ICoreException {
		return source;
	}

}
