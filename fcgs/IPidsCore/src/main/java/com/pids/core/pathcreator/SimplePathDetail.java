package com.pids.core.pathcreator;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

/**
 * 路径信息接口的简单实现
 * @author Jiangbin
 * @date 2013-6-17上午2:26:45
 */
public class SimplePathDetail implements PathDetail {
	private static final long serialVersionUID = -6187542305090250567L;
	protected String folder;
	protected String subFolder;
	protected String filePrefix;
	protected String fileSuffix;
	protected String ext;
	protected boolean isCreateFolder = false;

	/**
	 * 构造函数
	 * @param folder 文件目录
	 * @param subFolder 文件子目录
	 * @param ext 扩展名
	 */
	public SimplePathDetail(String folder, String subFolder, String ext) {
		super();
		this.folder = folder;
		this.subFolder = subFolder;
		this.ext = ext;
	}

	/**
	 * 构造函数
	 * @param folder 文件目录
	 * @param subFolder 文件子目录
	 * @param filePrefix 文件名前缀
	 * @param ext 文件扩展名
	 */
	public SimplePathDetail(String folder, String subFolder, String filePrefix, String ext) {
		super();
		this.folder = folder;
		this.subFolder = subFolder;
		this.filePrefix = filePrefix;
		this.ext = ext;
	}

	/**
	 * 构造函数
	 * @param isCreateFolder 构建目录还是文件
	 * @param folder 文件目录
	 * @param subFolder 文件子目录
	 * @param filePrefix 文件名前缀
	 * @param ext 文件扩展名
	 */
	public SimplePathDetail(boolean isCreateFolder, String folder, String subFolder, String filePrefix, String ext) {
		super();
		this.isCreateFolder = isCreateFolder;
		this.folder = folder;
		this.subFolder = subFolder;
		this.filePrefix = filePrefix;
		this.ext = ext;
	}

	public SimplePathDetail() {
		super();
	}

	@Override
	public String getFolder() {
		return folder;
	}

	@Override
	public void setFolder(String folder) {
		this.folder = folder;
	}

	@Override
	public String getSubFolder() {
		return subFolder;
	}

	@Override
	public void setSubFolder(String subFolder) {
		this.subFolder = subFolder;
	}

	@Override
	public void setFilePrefix(String filePrefix) {
		this.filePrefix = filePrefix;
	}

	@Override
	public String getFilePrefix() {
		return filePrefix;
	}

	@Override
	public String getExt() {
		return ext;
	}

	@Override
	public void setExt(String ext) {
		this.ext = ext;
	}

	@Override
	public boolean isCreateFolder() {
		return isCreateFolder;
	}

	@Override
	public void setCreateFolder(boolean isCreateFolder) {
		this.isCreateFolder = isCreateFolder;
	}

	@Override
	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	@Override
	public String getFileSuffix() {
		return fileSuffix;
	}

	@Override
	public void setPathDetail(PathDetail pathDetail) {
		if (pathDetail == null) {
			return;
		}
		try {
			BeanUtils.copyProperties(this, pathDetail);
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error(e);
		}
	}
}
