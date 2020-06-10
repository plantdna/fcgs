package com.pids.core.checker;

import com.pids.core.exception.ICoreException;

import java.util.List;

/**
 * 列表校验器
 * @Author Andory
 * @Date 2012-9-24上午11:15:40
 */
public interface ListChecker<S, T> extends Checker<S, T> {
	/**
	 * 校验对象列表
	 * @Author Andory
	 * @param sources 待检源数据列表
	 * @return 检测结果列表
	 * @throws ICoreException
	 * @Date 2012-9-24上午11:17:25
	 */
	public List<T> check(List<S> sources) throws ICoreException;

	/**
	 * 校验对象列表
	 * @Author Andory
	 * @param sources 待检源数据列表
	 * @param fullMode 是/否使用全模式
	 * @return 检测结果列表
	 * @throws ICoreException
	 * @Date 2012-9-24上午11:17:25
	 */
	public List<T> check(List<S> sources, boolean fullMode) throws ICoreException;
}
