package com.fcgs.base.mapper;

import com.fcgs.base.domain.SampleSpecies;
import com.pids.core.exception.ICoreException;
import com.pids.core.mapper.GroupMapperTemplate;

/**
 * 按种属进行分组，key/value--种属/分组对象列表
 * @author jiangbin
 * @date 2016年7月1日下午2:15:40
 */
public class SampleSpeciesGroupMapper<S extends SampleSpecies> extends GroupMapperTemplate<S, S> {

	private static final long serialVersionUID = 6890868968649259614L;

	@Override
	public S convert(S source) throws ICoreException {
		return source;
	}

	@Override
	protected String getMapperKey(S object) {
		return object.getSamSpecies();
	}

}
