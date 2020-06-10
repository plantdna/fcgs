package com.pids.core.callback;

import com.pids.core.exception.ICoreException;

/**
 * 回调接口
 * @Author andory
 * @Date 2012-8-11下午1:07:44
 */
public interface Callback<S, T> {
	/**
	 * 回调入口点,此方法通过回调其它方法来完成功能
	 * @Author andory
	 * @param source
	 * @return
	 * @throws ICoreException
	 * @Date 2012-8-11下午1:07:58
	 */
	public T call(S source) throws ICoreException;
}
