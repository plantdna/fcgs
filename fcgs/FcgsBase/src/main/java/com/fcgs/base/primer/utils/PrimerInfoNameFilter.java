package com.fcgs.base.primer.utils;

import com.fcgs.base.primer.PrimerInfo;
import com.pids.core.exception.ICoreException;
import com.pids.core.filter.ListFilterTemplate;

/**
 * 引物名过滤器
 * @author jiangbin
 * @date 2014年3月20日上午9:41:13
 */

public class PrimerInfoNameFilter<S extends PrimerInfo> extends ListFilterTemplate<S, String> {
	@Override
	protected String filter(S source, boolean isInternal) throws ICoreException {
		return source.getPrimerName();
	}

}
