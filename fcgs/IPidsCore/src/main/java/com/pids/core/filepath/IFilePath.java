package com.pids.core.filepath;

import java.io.Serializable;

/**
 * 文件路径接口
 * @author ANDORY
 * @date 2016年3月3日下午3:27:44
 */
public interface IFilePath extends Serializable {
	/**
	 * 获取文件内容
	 * @author ANDORY
	 * @return
	 * @date 2016年3月3日下午3:53:01
	 */
	public String getFileContent();
	
	/**
	 * 设置文件内容
	 * @author ANDORY
	 * @param fileContent
	 * @date 2016年3月3日下午3:53:19
	 */
	public void setFileContent(String fileContent);
	
	/**
	 * 获取文件存储路径
	 * @author ANDORY
	 * @return
	 * @date 2016年3月3日下午3:27:53
	 */
	public String getFilePath();
	
	/**
	 * 设置文件存储路径
	 * @author ANDORY
	 * @param filePath
	 * @date 2016年3月3日下午3:27:56
	 */
	public void setFilePath(String filePath);
	
	/**
	 * 获取文件分类类型，该标志值一般通过常量或枚举定义，
	 * 用于区分文件，并且在自动构建文件存储路径时也可以用到，
	 * 本方法默认值为"common"，表示普通文件
	 * @author ANDORY
	 * @return
	 * @date 2016年3月3日下午6:50:11
	 */
	public String getFileType();
}
