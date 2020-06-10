package com.pids.core.iterator;

import com.pids.core.exception.ICoreException;

/**
 * 迭代器
 * @Author andory
 * @Date 2012-8-9下午1:54:40
 */
public interface Iterator<S, T> {
	/**
	 * 迭代处理数据
	 * @Author andory
	 * @param source 源数据，若为null或空则直接返回null值
	 * @return 迭代处理后的数据
	 * @throws ICoreException
	 * @Date 2012-8-9下午1:52:37
	 */
	public T iterator(S source) throws ICoreException;
}
