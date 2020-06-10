package com.fcgs.base.filter;

import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.domain.handle.GeneHandle;
import com.pids.core.exception.ICoreException;
import com.pids.core.filter.ListFilterTemplate;

/**
 * 非空指纹信息对象过滤器，对于无指纹数据或位点列表为空的指纹均会被过滤掉
 * @author Andory
 * @date 2013-6-22上午10:13:55
 */
public class NotEmptyGeneFilter<S extends GeneHandle> extends ListFilterTemplate<S, S> {
	@Override
	protected S filter(S source, boolean isInternal) throws ICoreException {
		Gene gene = source.getGene();
		if (gene == null || gene.isEmpty()) {
			return null;
		}
		return source;
	}
}
