package com.fcgs.base.mapper;

import com.fcgs.base.domain.gene.Gene;
import com.pids.core.mapper.MapperTemplate;

/**
 * 指纹ID样品条码号映射关系表,key/value--指纹记录ID/样品条码号
 * @author jiangbin
 * @date 2014年1月24日下午8:27:35
 */
public class GeneIdSamNumMapper extends MapperTemplate<String, Gene> {

	private static final long serialVersionUID = -2500991691094601359L;

	@Override
	protected String getMapperKey(Gene object) {
		return object.getGeneId();
	}

	@Override
	protected String getMapperValue(Gene object) {
		return object.getSample().getSamBarcode();
	}

}
