package com.pids.core.template.reader;

import com.pids.core.exception.ICoreException;
import com.pids.core.template.domain.Template;

/**
 * 模板读取接口
 * @author jiang
 * @date 2018年6月11日下午10:44:55
 */
public interface TemplateReader {
	/**
	 * 读取指定文件的模板
	 * @author jiang
	 * @param model 模板定义信息
	 * @return
	 * @throws ICoreException
	 * @date 2018年6月11日下午10:46:18
	 */
	public byte[] read(Template model) throws ICoreException;
}
