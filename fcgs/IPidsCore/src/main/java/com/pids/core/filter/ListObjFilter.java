package com.pids.core.filter;

import com.pids.core.exception.ICoreException;

import java.util.List;

/**
 * 集合类型对象数据列表过滤器顶级接口
 * @author jiangbin
 * @date 2012-1-4上午2:04:52
 */
public interface ListObjFilter<S, P, T> extends ObjFilter<S, P, T> {
	/**
	 * 过滤器顶级接口,该接口使用非全模式进行处理
	 * @author jiangbin
	 * @param sources 被过滤对象列表
	 * @param params 过滤条件
	 * @return
	 * @date 2012-1-4上午2:45:48
	 */
	public List<T> filter(List<S> sources, P params) throws ICoreException;

}
