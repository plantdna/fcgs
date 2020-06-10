package com.pids.core.pathcreator.utils;

import com.pids.core.pathcreator.PathCreatorTemplate;
import com.pids.core.pathcreator.PathDetail;

/**
 * <pre>通用路径构建器,此构建器不支持默认参数相关方法:
 * 		getDefaultFolder、getDefaultSubFolder、getDefaultExt</pre>
 * @author jiangbin
 * @date 2012-1-9下午6:42:33
 */
public class SimplePathCreator extends PathCreatorTemplate {

	private static final long serialVersionUID = 5978098687775604385L;

	public SimplePathCreator() {
	}

	/**
	 * 构建路径创建器
	 * @param folder 顶级存储目录
	 * @param subFolder　一级子目录
	 * @param ext　文件扩展名
	 */
	public SimplePathCreator(String folder, String subFolder, String ext) {
		super(folder, subFolder, ext);
	}

	/**
	 * 构造函数
	 * @param isCreateFolder 构建目录还是文件
	 * @param folder 文件目录
	 * @param subFolder 文件子目录
	 * @param filePrefix 文件名前缀
	 * @param ext 文件扩展名
	 */
	public SimplePathCreator(boolean isCreateFolder, String folder, String subFolder, String filePrefix, String ext) {
		super(isCreateFolder, folder, subFolder, filePrefix, ext);
	}

	/**
	 * 构造函数
	 * @param folder 文件目录
	 * @param subFolder 文件子目录
	 * @param filePrefix 文件名前缀
	 * @param ext 文件扩展名
	 */
	public SimplePathCreator(String folder, String subFolder, String filePrefix, String ext) {
		super(folder, subFolder, filePrefix, ext);
	}

	/**
	 * @param pathDetail 路径详细信息对象
	 */
	public SimplePathCreator(PathDetail pathDetail) {
		this.setPathDetail(pathDetail);
	}

	@Override
	public String getDefaultFolder() {
		return "";
	}

	@Override
	public String getDefaultSubFolder() {
		return "";
	}

	@Override
	public String getDefaultExt() {
		return "";
	}

}
