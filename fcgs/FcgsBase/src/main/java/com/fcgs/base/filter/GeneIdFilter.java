package com.fcgs.base.filter;

import com.fcgs.base.domain.gene.GeneId;
import com.pids.core.exception.ICoreException;
import com.pids.core.filter.ListFilterTemplate;

/**
 * 指纹ID过滤器，过滤出给定指纹的ID列表
 * @author jiangbin
 * @date 2012-11-15上午9:33:15
 */
public class GeneIdFilter<S extends GeneId> extends ListFilterTemplate<S, String> {

	@Override
	protected String filter(S source, boolean isInternal) throws ICoreException {
		return source.getGeneId();
	}

}
