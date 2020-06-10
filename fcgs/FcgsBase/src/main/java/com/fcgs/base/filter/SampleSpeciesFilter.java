package com.fcgs.base.filter;

import com.fcgs.base.domain.SampleSpecies;
import com.pids.core.exception.ICoreException;
import com.pids.core.filter.ListFilterTemplate;

/**
 * 种属字段过滤器，所有扩展了{@link SampleSpecies}接口的类均可以使用本功能
 * @author ANDORY
 * @date 2016年5月16日下午8:49:45
 */

public class SampleSpeciesFilter<S extends SampleSpecies> extends ListFilterTemplate<S, String> {
	
	@Override
	protected String filter(S source, boolean isInternal) throws ICoreException {
		return source.getSamSpecies();
	}
	
}
