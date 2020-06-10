package com.pids.core.pair.utils;

import com.pids.core.exception.ICoreException;
import com.pids.core.filter.ListFilterTemplate;
import com.pids.core.pair.StringPair;

/**
 * 过滤出源字符串列表
 * @author jiangbin
 * @date 2014年2月22日下午2:27:16
 */
public class StringPairSourceFilter extends ListFilterTemplate<StringPair, String> {
	@Override
	protected String filter(StringPair source, boolean isInternal) throws ICoreException {
		return source.getSource();
	}

}
