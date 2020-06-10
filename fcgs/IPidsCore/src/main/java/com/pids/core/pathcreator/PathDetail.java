package com.pids.core.pathcreator;

import java.io.Serializable;

/**
 * <pre>定义了标准路径构建所需参数信息，本接口主要定义三部分参数：
 * 1、文件主存储目录，一般整个系统统一配置到此目录下存储文件
 * 2、文件的二级目录,用于区分不同作用的文件
 * 3、文件的扩展名类型，如:jpg</pre>
 * @author Jiangbin
 * @date 2013-6-17上午2:25:42
 */
public interface PathDetail extends Serializable {
	/**
	 * 文件存储总目录,此目录一般在属性文件中配置好
	 * @author jiangbin
	 * @return
	 * @date 2012-1-9下午6:21:50
	 */
	public String getFolder();

	/**
	 * 文件存储总目录,此目录一般在属性文件中配置好
	 * @author jiangbin
	 * @param folder
	 * @date 2012-1-9下午6:22:39
	 */
	public void setFolder(String folder);

	/**
	 * 第一级子目录，用于区分存储不同类型文件
	 * @author jiangbin
	 * @return
	 * @date 2012-1-9下午6:21:43
	 */
	public String getSubFolder();

	/**
	 * 第一级子目录，用于区分存储不同类型文件
	 * @author jiangbin
	 * @param subFolder
	 * @date 2012-1-9下午6:21:16
	 */
	public void setSubFolder(String subFolder);

	/**
	 * 扩展名，示例格式如：jpg，不包括"."
	 * @author jiangbin
	 * @return
	 * @date 2012-1-9下午6:21:09
	 */
	public String getExt();

	/**
	 * 扩展名，示例格式如：jpg，不包括"."
	 * @author jiangbin
	 * @param ext
	 * @date 2012-1-9下午6:20:43
	 */
	public void setExt(String ext);

	/**
	 * 设置路径详细信息
	 * @author Jiangbin
	 * @param pathDetail
	 * @date 2013-6-17上午2:31:22
	 */
	public void setPathDetail(PathDetail pathDetail);

	/**
	 * 获取文件前缀名
	 * @author Andory
	 * @return
	 * @date 2013年9月9日下午7:36:09
	 */
	public String getFilePrefix();

	/**
	 * 设置文件前缀名
	 * @author Andory
	 * @param filePrefix
	 * @date 2013年9月9日下午7:36:05
	 */
	public void setFilePrefix(String filePrefix);

	/**
	 * 获取文件后缀名
	 * @author Andory
	 * @return
	 * @date 2013年9月9日下午7:36:09
	 */
	public String getFileSuffix();

	/**
	 * 设置文件后缀名
	 * @author Andory
	 * @param fileSuffix
	 * @date 2013年9月9日下午7:36:05
	 */
	public void setFileSuffix(String fileSuffix);

	/**
	 * 设置是否创建目录路径
	 * @author jiangbin
	 * @param isFolder true/false--创建目录/创建文件(默认)
	 * @date 2014年4月14日上午10:14:35
	 */
	public void setCreateFolder(boolean isCreateFolder);

	/**
	 * 是否创建目录路径
	 * @author jiangbin
	 * @return true/false--创建目录/创建文件(默认)
	 * @date 2014年4月14日上午10:15:29
	 */
	public boolean isCreateFolder();
}
