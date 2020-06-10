package com.fcgs.base.mapper;

import com.pids.core.mapper.MapperTemplate;

/**
 * 引物组映射,key--引物组ID,value--引物荧光对照关系池
 * @author jiangbin
 * @date 2012-5-7下午6:10:56
 */
public class PrimerGroupMapper extends MapperTemplate<MarkerPrimerDyeMapper, MarkerPrimerDyeMapper> {

	private static final long serialVersionUID = -7433600931471958665L;

	@Override
	protected String getMapperKey(MarkerPrimerDyeMapper object) {
		return object.getPrimerGroupId();
	}

	@Override
	protected MarkerPrimerDyeMapper getMapperValue(MarkerPrimerDyeMapper object) {
		return object;
	}

}
