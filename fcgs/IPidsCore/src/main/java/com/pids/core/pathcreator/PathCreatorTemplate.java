package com.pids.core.pathcreator;

import com.pids.core.utils.UuidUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <pre>路径构建器模板类,此构建器生成的目录格式为：
 * 文件路径：主目录/一级子目录/年份/月日/时间/[文件名前缀_]8位随机号[/文件名后缀+年月日].扩展名
 * 目录路径:主目录/一级子目录/年份/月日/时间/[目录名前缀_]8位随机号[扩展名]
 * 如：
 * 文件路径：D:/work/mditip/upload/SsrGeneFiles/2014/0220/050338/GeneDataDF4E4F83F8B74FC2/Gene20140521.xml
 * 目录路径：D:/work/mditip/upload/SsrGeneFiles/2014/0220/050338/GeneDataDF4E4F83F8B74FC2xml
 * 并且本接口类提供获取除主目录外的相对路径的方法</pre>
 * @author jiangbin
 * @date 2012-1-9下午6:12:48
 */
public abstract class PathCreatorTemplate extends AbstractPathCreator {
	private static final long serialVersionUID = 942444041782479963L;

	public PathCreatorTemplate() {
	}

	/**
	 * 构建路径创建器
	 * @param folder 顶级存储目录
	 * @param subFolder　一级子目录
	 * @param ext　文件扩展名
	 */
	public PathCreatorTemplate(String folder, String subFolder, String ext) {
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
	public PathCreatorTemplate(String folder, String subFolder, String filePrefix, String ext) {
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
	public PathCreatorTemplate(boolean isCreateFolder, String folder, String subFolder, String filePrefix, String ext) {
		super();
		this.isCreateFolder = isCreateFolder;
		this.folder = folder;
		this.subFolder = subFolder;
		this.filePrefix = filePrefix;
		this.ext = ext;
	}

	@Override
	public String createSubFolderPart() {
		StringBuilder path = new StringBuilder();
		if (!StringUtils.isBlank(subFolder)) {
			path.append(delLastBackSlant(getSubFolder()));
		} else if (!StringUtils.isBlank(getSubFolder())) {
			path.append(delLastBackSlant(getDefaultSubFolder()));
		}
		return path.toString();
	}

	@Override
	public String createDatePart() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MMdd/hhmmss");
		String time = format.format(date);
		String filePrefix = StringUtils.stripToEmpty(getFilePrefix());
		String fileSuffix = StringUtils.stripToEmpty(getFileSuffix());
		String uuid = UuidUtil.getUuid();
		String datePart = String.format("/%s/%s%s", time, filePrefix, uuid);
		if (StringUtils.isNotBlank(fileSuffix)) {
			String datePrefix = DateFormatUtils.format(new Date(), "yyyyMMdd");
			datePart = String.format("%s/%s%s", datePart, fileSuffix, datePrefix);
		}
		return datePart;
	}

	@Override
	public String createExtPart() {
		String tmpExt = StringUtils.stripToEmpty(this.getExt());
		if (tmpExt.isEmpty()) {
			tmpExt = getDefaultExt();
		}
		if (tmpExt.isEmpty()) {
			return "";
		}
		if (isCreateFolder) {
			tmpExt = createFolderExt(tmpExt);
		} else {
			tmpExt = createFileExt(tmpExt);
		}
		return tmpExt;
	}

	/**
	 * <pre>创建文件扩展名:
	 * 1、删除字符串起始的"."
	 * 2、若给定扩展名不包含"."，则在字符串头部添加"."
	 * 3、否则在字符串状头部添加"_"</pre>
	 * @author jiangbin
	 * @param fileExt 文件扩展名
	 * @return
	 * @date 2014年4月14日上午10:23:11
	 */
	protected String createFileExt(String fileExt) {
		fileExt = delFirstDot(fileExt);
		if (!fileExt.contains(".")) {
			fileExt = "." + fileExt;
		}
		return fileExt;
	}

	/**
	 * <pre>创建目录扩展名:
	 * 1、删除字符串起始的"."
	 * 2、替换字符串中所有"."为"_"</pre>
	 * @author jiangbin
	 * @param folderExt 目录扩展名
	 * @return
	 * @date 2014年4月14日上午10:25:56
	 */
	protected String createFolderExt(String folderExt) {
		folderExt = this.delFirstDot(folderExt);
		return folderExt.replaceAll("\\.", "");
	}

	@Override
	protected String delLastBackSlant(String str) {
		if (StringUtils.stripToEmpty(str).endsWith("/")) {
			return str.substring(0, str.length() - 1);
		} else {
			return str;
		}
	}

	@Override
	protected String delFirstBackSlant(String str) {
		if (StringUtils.stripToEmpty(str).startsWith("/")) {
			return str.substring(1);
		} else {
			return str;
		}
	}

	/**
	 * @Description　如果给定字符串以"."开始则删除之
	 * @author jiangbin
	 * @param str
	 * @return
	 * @date 2012-1-9下午6:28:15
	 */
	protected String delFirstDot(String str) {
		if (StringUtils.stripToEmpty(str).startsWith(".")) {
			return str.substring(1);
		} else {
			return str;
		}
	}

}
