package com.fcgs.base.filter;

import com.fcgs.base.domain.GeneLocation;
import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.domain.handle.GeneHandle;
import com.pids.core.exception.ICoreException;
import com.pids.core.filter.ListFilterTemplate;

/**
 * 基于{@link GeneHandle}接口的指纹位置信息过滤器
 * @author jiangbin
 * @date 2014年10月15日上午1:03:37
 */

public class GeneLocationFilter<S extends GeneHandle> extends ListFilterTemplate<S, GeneLocation> {
	@Override
	protected GeneLocation filter(S source, boolean isInternal) throws ICoreException {
		Gene gene = source.getGene();
		return gene != null ? gene.getLocation() : null;
	}

}
