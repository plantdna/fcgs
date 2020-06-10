package com.pids.core.merger;

import com.pids.core.exception.ICoreException;

import java.util.List;

/**
 * 合并给定列表数据
 * @Author andory
 * @Date 2012-8-9下午11:19:24
 */
public interface ListMerger<S, T> extends Merger<List<S>, T> {
	/**
	 * 合并给定列表数据
	 * @Author andory
	 * @param sources 源数据列表
	 * @return 合并后的数据
	 * @throws ICoreException
	 * @Date 2012-8-9下午11:19:34
	 */
	@Override
	public T merger(List<S> sources) throws ICoreException;

}
