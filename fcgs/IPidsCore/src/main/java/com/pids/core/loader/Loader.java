package com.pids.core.loader;

import com.pids.core.exception.ICoreException;

/**
 * 加载器接口
 * @author Andory
 * @date 2012-7-24下午03:43:11
 */
public interface Loader<S, T> {
	/**
	 * 加载操作接口方法
	 * @author Andory
	 * @return true/false--加载成功/失败
	 * @throws ICoreException
	 * @date 2012-7-24下午03:43:23
	 */
	public T load(S source) throws ICoreException;

}
