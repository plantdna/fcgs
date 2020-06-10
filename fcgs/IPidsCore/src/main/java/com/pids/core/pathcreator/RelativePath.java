package com.pids.core.pathcreator;

import java.io.Serializable;

/**
 * 获取相对路径
 * @author Jiangbin
 * @date 2013-8-23下午12:28:25
 */
public interface RelativePath extends Serializable {
	/**
	 * 设置相对路径
	 * @author Andory
	 * @param relatetivePath
	 * @date 2012-7-7下午03:17:45
	 */
	public void setRelativePath(String relativePath);

	/**
	 * 获取相对路径
	 * @author Andory
	 * @return
	 * @date 2012-7-7下午03:18:11
	 */
	public String getRelativePath();
}
