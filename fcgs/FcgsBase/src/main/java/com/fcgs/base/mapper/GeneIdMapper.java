package com.fcgs.base.mapper;

import com.fcgs.base.domain.gene.GeneId;
import com.pids.core.mapper.MapperTemplate;

/**
 * 指纹ID映射，kye/value--指纹ID/数据对象
 * @author jiangbin
 * @date 2017年6月1日上午9:43:54
 */
public class GeneIdMapper<S extends GeneId> extends MapperTemplate<S, S> {

	private static final long serialVersionUID = 1034042166833726097L;

	@Override
	protected String getMapperKey(S object) {
		return object.getGeneId();
	}

	@Override
	protected S getMapperValue(S object) {
		return object;
	}

}
