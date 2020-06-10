package com.pids.core.datacopier;

import com.pids.core.exception.ICoreException;

import java.io.Serializable;

/**
 * 数据对象的拷贝，要求被拷贝的对象支持序列化，否则拷贝操作将失败
 * @author jiangbin
 * @date 2012-1-10下午3:02:00
 */
public interface DataCopier<S extends Serializable, T> {
	/**
	 * 生成源对象的一个拷贝
	 * @author jiangbin
	 * @param source
	 * @return
	 * @throws DataCopierException
	 * @date 2014年2月17日上午11:41:40
	 */
	public T copy(S source) throws ICoreException;

	/**
	 * 拷贝源数据到目标对象
	 * @author jiangbin
	 * @param source 源数据对象，若为null则不执行拷贝并返回false
	 * @param target 目标数据对象，若为null则不执行拷贝并返回false
	 * @return true/false--拷贝成功/失败
	 * @throws ICoreException
	 * @date 2014年2月17日上午11:53:47
	 */
	public boolean copy(S source, T target) throws ICoreException;

}
