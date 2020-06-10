package com.pids.core.cutter;

import com.pids.core.exception.ICoreException;

/**
 * 裁剪器顶级接口
 * @author jiangbin
 * @date 2012-1-4上午2:04:52
 */
public interface Cutter<S, T> {
	/**
	 * 裁剪器顶级接口
	 * @author jiangbin
	 * @param source
	 * @return 给定对象裁剪不成功将返回null值
	 * @date 2012-1-4上午2:45:48
	 */
	public T cut(S source) throws ICoreException;
}
