package com.pids.core.id.utils;

import com.pids.core.id.ID;
import com.pids.core.mapper.MapperTemplate;

/**
 * ID映射信息Mapper,key/value--ID/数据对象
 * @author jiangbin
 * @date 2015年1月7日上午11:28:02
 */
public class IdMapper<S extends ID> extends MapperTemplate<S, S> {

	private static final long serialVersionUID = -6071263523854280283L;

	@Override
	protected String getMapperKey(S object) {
		return object.getId();
	}

	@Override
	protected S getMapperValue(S object) {
		return object;
	}

}
