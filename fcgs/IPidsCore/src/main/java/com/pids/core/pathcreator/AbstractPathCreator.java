package com.pids.core.pathcreator;

import com.pids.core.utils.PathUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 路径构建抽象类，定义了路径由主目录和相对路径两部分构成
 * @author Andory
 * @date 2012-7-7下午03:17:14
 */
public abstract class AbstractPathCreator extends PathDetailEx implements PathCreator {

	private static final long serialVersionUID = 5295375332638189674L;

	@Override
	public String create() {
		return this.create(this.isCreateFolder);
	}

	@Override
	public String create(boolean isCreatFolder) {
		this.setCreateFolder(isCreatFolder);
		setRelativePath(createRelativePath());
		return this.getFilePath(getRelativePath());
	}

	@Override
	public String createRelativePath() {
		return String.format("%s%s%s", createSubFolderPart(), createDatePart(), createExtPart());
	}

	@Override
	public String getFilePath(String relativePath) {
		String tmp = "";
		if (!StringUtils.isBlank(getFolder())) {
			tmp = delLastBackSlant(getFolder()) + "/" + delFirstBackSlant(relativePath);
		} else if (!StringUtils.isBlank(getDefaultFolder())) {
			tmp = delLastBackSlant(getDefaultFolder()) + "/" + delFirstBackSlant(relativePath);
		}
		return PathUtils.convertWinDir(tmp);
	}

	/**
	 * 构建路径的扩展名
	 * @author Andory
	 * @return
	 * @date 2012-7-7下午03:03:41
	 */
	protected abstract String createExtPart();

	/**
	 * 构建路径的日期部分
	 * @author Andory
	 * @return
	 * @date 2012-7-7下午03:03:04
	 */
	protected abstract String createDatePart();

	/**
	 * 构建路径的子目录部分
	 * @author Andory
	 * @return
	 * @date 2012-7-7下午03:03:20
	 */
	protected abstract String createSubFolderPart();

	/**
	 * 如果给定字符串以"/"结尾则删除之
	 * @author jiangbin
	 * @param str
	 * @return
	 * @date 2012-1-9下午6:24:14
	 */
	protected abstract String delLastBackSlant(String folder);

	/**
	 * 如果给定字符串以"/"开头则删除之
	 *
	 * @param relativePath 相对路径
	 * @return java.lang.String
	 * <br/><br/>
	 * Create by WuHaotian on 2020-05-25 14:56:02
	 **/
	protected abstract String delFirstBackSlant(String relativePath);

	/**
	 * 获取默认存储目录
	 * @author jiangbin
	 * @return
	 * @date 2012-1-9下午6:12:08
	 */
	public abstract String getDefaultFolder();

	/**
	 * 获取默认存储子目录
	 * @author jiangbin
	 * @return
	 * @date 2012-1-9下午6:12:11
	 */
	public abstract String getDefaultSubFolder();

	/**
	 * 获取默认扩展名称
	 * @author jiangbin
	 * @return
	 * @date 2012-1-9下午6:12:14
	 */
	public abstract String getDefaultExt();
}
