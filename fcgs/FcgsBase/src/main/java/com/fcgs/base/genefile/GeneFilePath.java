package com.fcgs.base.genefile;

import java.io.Serializable;

/**
 * 指纹文件路径接口
 * @author Andory
 * @date 2013年9月9日下午6:35:35
 */
public interface GeneFilePath extends Serializable {
	/**
	 * 获取指纹数据文件路径
	 * @author Andory
	 * @return
	 * @date 2013年9月9日下午7:09:06
	 */
	public String getGeneFilePath();

	/**
	 * 设置指纹数据文件路径
	 * @author Andory
	 * @param geneFilePath
	 * @date 2013年9月9日下午7:09:08
	 */
	public void setGeneFilePath(String geneFilePath);
}
