package com.pids.core.preprocessor;

import com.pids.core.exception.ICoreException;

/**
 * 预处理器接口
 * @author dengjunzhen
 * @date 2016年4月12日上午10:23:04
 */
public interface Preprocessor<S, T> {
	public T preprocessor(S source) throws ICoreException;
}
