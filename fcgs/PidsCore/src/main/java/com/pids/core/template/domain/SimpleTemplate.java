package com.pids.core.template.domain;

/**
 * 模板信息定义对象
 * @author jiang
 * @date 2018年6月11日下午11:05:50
 */
public class SimpleTemplate implements Template {
	private static final long serialVersionUID = 5813464682660409190L;
	private String id;
	private String templateName;
	private String fileName;
	private String descName;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getTemplateName() {
		return templateName;
	}

	@Override
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	@Override
	public String getFileName() {
		return fileName;
	}

	@Override
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String getDescName() {
		return descName;
	}

	@Override
	public void setDescName(String descName) {
		this.descName = descName;
	}

}
