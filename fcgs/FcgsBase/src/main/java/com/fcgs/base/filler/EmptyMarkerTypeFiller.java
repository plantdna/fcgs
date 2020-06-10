package com.fcgs.base.filler;

import com.fcgs.base.domain.MarkerType;
import com.pids.core.exception.ICoreException;
import com.pids.core.filler.ListFillerTemplate;

/**
 * 清空标记类型字段信息
 * @author Jiangbin
 * @date 2014-4-25下午11:19:25
 */

public class EmptyMarkerTypeFiller<S extends MarkerType> extends ListFillerTemplate<S, S> {

	@Override
	protected S fill(S source, boolean isInternal) throws ICoreException {
		source.setMarkerType(null);
		return source;
	}

}
