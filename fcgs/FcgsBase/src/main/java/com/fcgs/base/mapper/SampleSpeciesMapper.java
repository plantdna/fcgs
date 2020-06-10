package com.fcgs.base.mapper;

import com.fcgs.base.domain.SampleSpecies;
import com.pids.core.mapper.MapperTemplate;

/**
 * 种属映射信息Mapper,key/value--种属/数据对象
 * @author jiangbin
 * @date 2016年7月1日下午2:17:04
 */
public class SampleSpeciesMapper<S extends SampleSpecies> extends MapperTemplate<S, S> {

	private static final long serialVersionUID = -7364011190826988382L;

	@Override
	protected String getMapperKey(S object) {
		return object.getSamSpecies();
	}

	@Override
	protected S getMapperValue(S object) {
		return object;
	}

}
