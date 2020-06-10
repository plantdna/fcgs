package com.pids.core.filepath;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 文件路径接口实现类
 * @author ANDORY
 * @date 2016年3月3日下午4:28:01
 */
public class SimpleFilePath implements IFilePath {
	private static final long serialVersionUID = 8922212658571292822L;
	private String filePath;
	private String fileContent;
	
	@Override
	public String getFilePath() {
		return filePath;
	}
	
	@Override
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public String getFileContent() {
		return fileContent;
	}
	
	@Override
	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}
	
	@Override
	public String getFileType() {
		return "common";
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
