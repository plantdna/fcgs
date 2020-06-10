package com.fcgs.base.mapper;

import com.fcgs.base.domain.gene.Gene;
import com.pids.core.mapper.MapperTemplate;

/**
 * 指纹信息Mapper抽象接口类
 * @author Andory
 * @date 2013年9月2日下午3:45:14
 */
public abstract class AbstractGeneMapper extends MapperTemplate<Gene, Gene> {
	private static final long serialVersionUID = 3892102741244420014L;

	@Override
	protected Gene getMapperValue(Gene source) {
		if (source == null || source.isEmpty()) {
			return null;
		}
		return source;
	}
}
