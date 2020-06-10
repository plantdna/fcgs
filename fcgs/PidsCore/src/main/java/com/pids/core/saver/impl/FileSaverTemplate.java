package com.pids.core.saver.impl;

import com.pids.core.exception.ICoreException;
import com.pids.core.utils.FileBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 通用文件内容保存器模板实现类
 * @author Andory
 * @date 2012-5-24下午05:38:42
 */
public abstract class FileSaverTemplate<S> implements FileSaver<S> {
	/**
	 * 将excel保存到指定文件路径
	 * @author Andory
	 * @param source excel数据对象
	 * @param filePath 文件存储路径,若指定文件路径不存在将自动创建之
	 * @return true/false--保存成功/失败
	 * @throws ICoreException
	 * @date 2012-8-5上午10:36:53
	 */
	@Override
	public boolean save(S source, String filePath) throws ICoreException {
		try {
			File file = FileBuilder.build(filePath, false);
			return this.save(source, file);
		} catch (Exception e) {
			throw new ICoreException(e);
		}
	}

	/**
	 * 保存excel到指定文件中
	 * @author Andory
	 * @param source excel数据对象
	 * @param file 文件对象
	 * @return true/false--保存成功/失败
	 * @throws ICoreException
	 * @date 2012-8-5上午10:48:10
	 */
	@Override
	public boolean save(S source, File file) throws ICoreException {
		try {
			if (!file.exists()) {
				file = FileBuilder.build(file.getAbsolutePath(), false);
			}
			OutputStream outputStream = new FileOutputStream(file);
			return this.save(source, outputStream);
		} catch (Exception e) {
			throw new ICoreException(e);
		}
	}

	@Override
	public boolean save(S source, OutputStream outputStream) throws ICoreException {
		try {
			if (source != null && outputStream != null) {
				return saveDetail(source, outputStream);
			}
			return false;
		} catch (Exception e) {
			throw new ICoreException(e);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					throw new ICoreException(e);
				}
			}
		}

	}

	/**
	 * 保存源数据信息到目标输出流上，本方法无需对输出流对象进行释放，因为模板方法中已包含此业务逻辑
	 * @author jiangbin
	 * @param source
	 * @param outputStream
	 * @return
	 * @throws ICoreException
	 * @date 2013-11-18下午6:09:27
	 */
	protected abstract boolean saveDetail(S source, OutputStream outputStream) throws ICoreException;

}
