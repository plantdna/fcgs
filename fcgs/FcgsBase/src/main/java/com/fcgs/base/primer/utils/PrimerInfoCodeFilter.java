package com.fcgs.base.primer.utils;

import com.fcgs.base.primer.PrimerInfo;
import com.pids.core.exception.ICoreException;
import com.pids.core.filter.ListFilterTemplate;

/**
 * 引物编号过滤器
 * @author jiangbin
 * @date 2014年3月20日上午9:40:49
 */

public class PrimerInfoCodeFilter<S extends PrimerInfo> extends ListFilterTemplate<S, String> {
	@Override
	protected String filter(S source, boolean isInternal) throws ICoreException {
		return source.getPrimerCode();
	}

}
