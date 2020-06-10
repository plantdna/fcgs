package com.pids.core.filler;

import com.pids.core.exception.ICoreException;

import java.util.List;

/**
 * 数据对象填充器，用于支持向给定数据对象或对象列表中填充给定数据值信息
 * @author jiangbin
 * @date Jun 28, 201910:51:21 AM
 */
public interface DataFiller<S, V> {
	/**
	 * 填充指定值到源对象列表中
	 * @author jiangbin
	 * @param sources 源对象列表
	 * @param value 字段值
	 * @return
	 * @throws ICoreException
	 * @date Jun 28, 201910:43:11 AM
	 */
	public List<S> fill(List<S> sources, V value) throws ICoreException;

	/**
	 * 填充指定值到源对象中
	 * @author jiangbin
	 * @param source 源对象
	 * @param value 字段值
	 * @return
	 * @throws ICoreException
	 * @date Jun 28, 201910:49:16 AM
	 */
	public S fill(S source, V value) throws ICoreException;
}
