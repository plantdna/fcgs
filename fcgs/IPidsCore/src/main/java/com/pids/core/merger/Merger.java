package com.pids.core.merger;

import com.pids.core.exception.ICoreException;

/**
 * 合并操作接口
 * @author Andory
 * @date 2012-8-5下午3:35:41
 */
public interface Merger<S, T> {
	/**
	 * 合并操作
	 * @author Andory
	 * @param source
	 * @return
	 * @throws ICoreException
	 * @date 2012-8-5下午3:41:10
	 */
	public T merger(S source) throws ICoreException;
}
