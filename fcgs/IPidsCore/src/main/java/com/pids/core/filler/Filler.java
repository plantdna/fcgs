package com.pids.core.filler;

import com.pids.core.exception.ICoreException;

/**
 * 填充器接口
 * @author Andory
 * @date 2012-5-17下午09:25:20
 */
public interface Filler<S, T> {
	public T fill(S source) throws ICoreException;
}
