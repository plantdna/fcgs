package com.pids.core.sorter;

import com.pids.core.exception.ICoreException;

/**
 *  排序接口方法
 * @author LiuJunGuang
 * @date 2012-12-12上午11:25:51
 */
public interface Sorter<S, T> {
	public T sort(S sources) throws ICoreException;
}
