package com.pids.core.grouper;

import com.pids.core.exception.ICoreException;
import com.pids.core.mapper.GroupMapperTemplate;

/**
 * 分组器,已过期请使用  {@link GroupMapperTemplate}接口
 * @author jiangbin
 * @date 2012-1-4下午11:46:30
 */
public interface Grouper<S, T> {
	/**
	 * 分组给定源数据
	 * @author Andory
	 * @param source 源数据
	 * @return 分组后的数据，若不符合分组条件则返回null
	 * @throws ICoreException
	 * @date 2012-6-14上午01:30:47
	 */
	public T group(S source) throws ICoreException;
}
