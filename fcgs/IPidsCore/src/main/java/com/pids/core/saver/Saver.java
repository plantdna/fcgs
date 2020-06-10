package com.pids.core.saver;

import com.pids.core.exception.ICoreException;

/**
 * 保存指定资源保存到目标存储体
 * @author Andory
 * @date 2012-5-19下午06:07:59
 */
public interface Saver<S, T> {
	/**
	 * 保存指定资源保存到目标存储体
	 * @author Andory
	 * @param source 资源信息对象
	 * @param target 目标存储体
	 * @return true/false--保存操作成功/失败
	 * @throws ICoreException
	 * @date 2012-5-19下午06:08:15
	 */
	public boolean save(S source, T target) throws ICoreException;
}
