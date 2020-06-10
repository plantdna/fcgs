package com.pids.core.saver.impl;

import com.pids.core.exception.ICoreException;

import java.io.File;
import java.io.OutputStream;

/**
 * 通用文件内容保存器
 * @author Andory
 * @date 2012-5-24下午05:38:42
 */
public interface FileSaver<S> {
	/**
	 * 将excel保存到指定文件路径
	 * @author Andory
	 * @param source excel数据对象
	 * @param filePath 文件存储路径,若指定文件路径不存在将自动创建之
	 * @return true/false--保存成功/失败
	 * @throws ICoreException
	 * @date 2012-8-5上午10:36:53
	 */
	public boolean save(S source, String filePath) throws ICoreException;

	/**
	 * 保存excel到指定文件中
	 * @author Andory
	 * @param source excel数据对象
	 * @param file 文件对象
	 * @return true/false--保存成功/失败
	 * @throws ICoreException
	 * @date 2012-8-5上午10:48:10
	 */
	public boolean save(S source, File file) throws ICoreException;

	/**
	 * 保存源对象到目标输出流上
	 * @author jiangbin
	 * @param source
	 * @param outputStream
	 * @return
	 * @throws ICoreException
	 * @date 2013-11-18下午6:06:45
	 */
	public boolean save(S source, OutputStream outputStream) throws ICoreException;

}
