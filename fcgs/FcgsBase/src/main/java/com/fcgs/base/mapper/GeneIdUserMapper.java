package com.fcgs.base.mapper;

import com.fcgs.base.domain.gene.Gene;
import com.pids.core.mapper.MapperTemplate;

/**
 * 指纹与用户ID映射关系Mapper,key/value--指纹ID/用户ID
 * @author Jiangbin
 * @date 2013-7-1下午10:09:38
 */
public class GeneIdUserMapper extends MapperTemplate<String, Gene> {
	private static final long serialVersionUID = 6055390795721578703L;

	@Override
	protected String getMapperKey(Gene object) {
		return object.getGeneId();
	}

	@Override
	protected String getMapperValue(Gene object) {
		return object.getSample().getDnaManager();
	}

}
