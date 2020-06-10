package com.pids.core.iterator;

import com.pids.core.exception.ICoreException;

import java.util.List;

/**
 * 列表数据迭代处理逻辑接口
 * 
 * @Author andory
 * @Date 2012-8-9下午1:52:25
 */
public interface ListIterator<S, T> extends Iterator<List<S>, List<T>> {
	/**
	 * 迭代数据列表
	 * @author jiangbin
	 * @param sources 源数据列表，若给定数据列表为null或空则直接返回null值
	 * @param fullMode 全模式开关，支持设置是否忽略迭代处理函数call()返回的null结果,true/false--不忽略/忽略
	 * @return
	 * @throws ICoreException
	 * @date 2014年3月13日下午7:24:06
	 */
	public List<T> iterator(List<S> sources, boolean fullMode) throws ICoreException;
}
