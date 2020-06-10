package com.pids.core.id.utils;

import com.pids.core.exception.ICoreException;
import com.pids.core.filler.ListFillerTemplate;
import com.pids.core.id.ID;
import com.pids.core.utils.UuidUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * 填充ID号
 * @author jiangbin
 * @date 2015年1月7日上午11:27:05
 */
public class IdFiller<S extends ID> extends ListFillerTemplate<S, S> {
	@Override
	protected S fill(S source, boolean isInternal) throws ICoreException {
		if (StringUtils.isNotBlank(source.getId())) {
			return source;
		}
		source.setId(UuidUtil.getUuid());
		return source;
	}
}
