package com.pids.core.creator;

import com.pids.core.exception.ICoreException;

/**
 * 创建器接口
 * @author Andory
 * @date 2012-5-17下午09:25:20
 */
public interface Creator<S, T> {
	public T create(S source) throws ICoreException;
}
