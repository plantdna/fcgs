package com.fcgs.base.filter;

import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.domain.handle.GeneHandle;
import com.pids.core.exception.ICoreException;
import com.pids.core.filter.ListFilterTemplate;

/**
 * 指纹句柄过滤器
 * @author jiangbin
 * @date 2012-11-15上午9:33:15
 */

public class GeneFilter<S extends GeneHandle> extends ListFilterTemplate<S, Gene> {

	@Override
	protected Gene filter(S source, boolean isInternal) throws ICoreException {
		return source.getGene();
	}

}
