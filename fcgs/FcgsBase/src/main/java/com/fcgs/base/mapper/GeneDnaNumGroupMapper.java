package com.fcgs.base.mapper;

import com.fcgs.base.domain.gene.Gene;

/**
 * DNA编号分组Mapper,key/values--DNA编号/指纹(Gene)列表
 * @author jiangbin
 * @date 2013-4-9下午4:30:26
 */
public class GeneDnaNumGroupMapper extends AbstractGeneGrouperMapper {

	private static final long serialVersionUID = 503513109196573706L;

	@Override
	protected String getMapperKey(Gene object) {
		return object.getSample().getDnaBarcode();
	}

}
