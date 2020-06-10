package com.fcgs.base.mapper;

import com.fcgs.base.domain.gene.Gene;
import com.pids.core.mapper.MapperTemplate;

/**
 * 指纹ID与库类型Mapper,key/value--指纹ID/库类型
 * @author Andory
 * @date 2013-6-20下午4:45:54
 */
public class GeneIdLibTypeMapper extends MapperTemplate<Integer, Gene> {

	private static final long serialVersionUID = -7656280424463272158L;

	@Override
	protected String getMapperKey(Gene object) {
		return object.getGeneId();
	}

	@Override
	protected Integer getMapperValue(Gene object) {
		return object.getGeneLib();
	}

}
