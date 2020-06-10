package com.fcgs.base.filter;

import com.fcgs.base.domain.handle.GeneHandle;
import com.pids.core.exception.ICoreException;
import com.pids.core.filter.ListFilterTemplate;

/**
 * 未设置指纹信息的指纹句柄对象过滤器
 * @author jiangbin
 * @date 2016年8月21日下午2:02:53
 */

public class NoGeneHandleFilter<S extends GeneHandle> extends ListFilterTemplate<S, S> {

	@Override
	protected S filter(S source, boolean isInternal) throws ICoreException {
		return source.getGene() == null ? source : null;
	}

}
