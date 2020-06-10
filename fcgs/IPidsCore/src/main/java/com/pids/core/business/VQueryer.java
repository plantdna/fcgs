package com.pids.core.business;

import com.pids.core.exception.ICoreException;

/**
 * 复杂数据查询操作业务接口
 * @author jiangbin
 * @date 2013-1-18下午4:02:51
 */
public interface VQueryer<S, T> {
	public T query(S sources) throws ICoreException;
}
