package com.pids.core.finder;

import com.pids.core.exception.ICoreException;

import java.util.List;

/**
 * 列表查找器
 * @author jiangbin
 * @date 2012-8-3下午5:17:53
 */
public interface ListFinder<S, T> extends Finder<S, T> {
	/**
	 * 查找符合条件的第(index+1)个数据，存在则返回，否则返回null
	 * @author Andory
	 * @param sources 源数据列表
	 * @param index 索引号，此索引用于标识查找符合条件的数据列表中的位置
	 * @return 查找到的目标数据,若不存在则返回null
	 * @throws ICoreException
	 * @date 2012-8-7下午8:36:37
	 */
	public abstract T find(List<S> sources, int index) throws ICoreException;

	/**
	 * 查找所有符合条件的数据
	 * @author Andory
	 * @param sources 源数据列表
	 * @return 查找到的目标数据,若不存在则返回null
	 * @throws ICoreException
	 * @date 2012-8-7下午8:40:44
	 */
	public abstract List<T> findAll(List<S> sources) throws ICoreException;

	/**
	 * 查找符合条件的第一个数据
	 * @author Andory
	 * @param sources 源数据列表
	 * @return 查找到的目标数据,若不存在则返回null
	 * @throws ICoreException
	 * @date 2012-8-7下午8:41:02
	 */
	public abstract T findFirst(List<S> sources) throws ICoreException;

	/**
	 * 查找符合条件的最后一个数据
	 * @author Andory
	 * @param sources 源数据列表
	 * @return 查找到的目标数据,若不存在则返回null
	 * @throws ICoreException
	 * @date 2012-8-7下午8:41:23
	 */
	public abstract T findLast(List<S> sources) throws ICoreException;
}
