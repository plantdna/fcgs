package com.pids.core.preprocessor;

import com.pids.core.exception.ICoreException;

import java.util.List;

/**
 * 
 * @author dengjunzhen
 * @date 2016年4月12日上午10:23:58
 */
public interface ListPreprocessor<S,T> extends Preprocessor<S,T> {
	
	/**
	 * 列表数据构建
	 * @author dengjunzhen
	 * @param sources
	 * @return
	 * @throws ICoreException
	 * @date 2016年4月12日上午10:24:41
	 */
	public List<T> build(List<S> sources) throws ICoreException;
	
	/**
	 * 列表数据构建
	 * @author dengjunzhen
	 * @param sources
	 * @param fullMode 是否使用全模式
	 * @return
	 * @throws ICoreException
	 * @date 2016年4月12日上午10:25:06
	 */
	public List<T> build(List<S> sources, boolean fullMode) throws ICoreException;

}
