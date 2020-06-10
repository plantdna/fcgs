package com.pids.core.chooser;

import com.pids.core.exception.ICoreException;

/**
 * 选择器接口
 * @author Andory
 * @date 2012-8-4下午2:25:54
 */
public interface Chooser<S, T> {
	/**
	 * 从源数据中按照一定规则进行择选
	 * @author Andory
	 * @param source 源数据
	 * @return 择选结果
	 * @throws ICoreException
	 * @date 2012-8-4下午2:26:04
	 */
	public T choose(S source) throws ICoreException;
}
