package com.pids.core.finder;

import com.pids.core.exception.ICoreException;

/**
 * 查找器,用于完成对某些源数据的匹配查找
 * @author jiangbin
 * @date 2012-8-3下午5:07:17
 */
public interface Finder<S, T> {
	/**
	 * 从给定源列表中查找某些对象后返回
	 * @author jiangbin
	 * @param source
	 * @return 若未查找到则返回null值
	 * @throws FinderException
	 * @date 2012-8-3下午5:09:44
	 */
	public T find(S source) throws ICoreException;
}
