package com.pids.core.parser;

import com.pids.core.exception.ICoreException;

/**
 * 解析器接口
 * @author Andory
 * @date 2012-5-25下午04:42:10
 */
public interface Parser<S, T> {
	public T parser(S source) throws ICoreException;
}
