package com.pids.core.pathcreator;

/**
 * 路径详细信息对象的一个扩展类，用于增加相关路径属性
 * @author Jiangbin
 * @date 2013-6-17上午2:28:20
 */
public class PathDetailEx extends SimplePathDetail implements RelativePath {
	private static final long serialVersionUID = -6791394778252829136L;
	private String relativePath;

	/**
	 * 设置相对路径
	 * @author Andory
	 * @param relatetivePath
	 * @date 2012-7-7下午03:17:45
	 */
	@Override
	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	/**
	 * 获取相对路径
	 * @author Andory
	 * @return
	 * @date 2012-7-7下午03:18:11
	 */
	@Override
	public String getRelativePath() {
		return relativePath;
	}

}
