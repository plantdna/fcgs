package com.pids.core.template.domain;

import com.pids.core.id.ID;

/**
 * 模板定义
 * @author jiang
 * @date 2018年6月11日下午10:47:12
 */
public interface Template extends ID {
	/**
	 * 获取模板名
	 * @author jiang
	 * @return
	 * @date 2018年6月11日下午10:47:58
	 */
	public String getTemplateName();

	/**
	 * 获取模板文件名
	 * @author jiang
	 * @return
	 * @date 2018年6月11日下午10:48:01
	 */
	public String getFileName();

	/**
	 * 设置模板名称
	 * @author jiang
	 * @param templateName
	 * @date 2018年6月11日下午11:07:24
	 */
	public void setTemplateName(String templateName);

	/**
	 * 获取模板文件名称
	 * @author jiang
	 * @param fileName
	 * @date 2018年6月11日下午11:07:27
	 */
	public void setFileName(String fileName);

	/**
	 * 获取描述文件名，可以当作下载文件名来用
	 * @author jiang
	 * @return
	 * @date 2018年6月12日下午1:03:34
	 */
	public String getDescName();

	/**
	 * 设置描述文件名，可以当作下载文件名来用
	 * @author jiang
	 * @param descName
	 * @date 2018年6月12日下午1:03:37
	 */
	public void setDescName(String descName);
}
