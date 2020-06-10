package com.fcgs.base.mapper;

import com.fcgs.base.domain.gene.Gene;

/**
 * 指纹ID号分组Mapper,key/value--指纹ID(GeneId)/分组指纹列表(List<Gene>)
 * @author jiangbin
 * @date 2013-4-23上午10:14:31
 */
public class GeneGroupMapper extends AbstractGeneGrouperMapper {

	private static final long serialVersionUID = 1960476400302268777L;

	@Override
	protected String getMapperKey(Gene object) {
		return object.getGeneId();
	}

}
