package com.fcgs.base.mapper;

import com.fcgs.base.domain.gene.Gene;
import com.pids.core.exception.ICoreException;
import com.pids.core.mapper.GroupMapperTemplate;

/**
 * 抽象的指纹分组Mapper实现
 * @author Andory
 * @date 2013-6-26下午5:04:21
 */
public abstract class AbstractGeneGrouperMapper extends GroupMapperTemplate<Gene, Gene> {

	private static final long serialVersionUID = -1607297699344318638L;

	@Override
	public Gene convert(Gene source) throws ICoreException {
		if (source == null || source.isEmpty()) {
			return null;
		}
		return source;
	}

}
