package com.pids.core.utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 文件扩展名判定工具类
 * @author jiangbin
 * @date 2019年1月30日下午4:13:39
 */
public class FileExtUtils {
	/**
	 * 判定是否为Excel文件扩展名
	 * @author jiangbin
	 * @param ext 文件扩展名
	 * @return
	 * @date 2019年1月30日下午4:16:40
	 */
	public static boolean isExcelExt(String ext) {
		return StringUtils.isNotBlank(ext) && (ext.equalsIgnoreCase("xls") || ext.equalsIgnoreCase("xlsx"));
	}

	/**
	 * 判定是否为csv文件扩展名
	 * @author jiangbin
	 * @param ext 文件扩展名
	 * @return
	 * @date 2019年1月30日下午4:16:43
	 */
	public static boolean isCsvExt(String ext) {
		return StringUtils.isNotBlank(ext) && ext.equalsIgnoreCase("csv");
	}

	/**
	 * 判定是否为Excel文件
	 * @author jiangbin
	 * @param filePath 文件路径
	 * @return
	 * @date 2019年1月30日下午4:16:46
	 */
	public static boolean isExcelFile(String filePath) {
		return isExcelExt(FilenameUtils.getExtension(filePath));
	}

	/**
	 * 判定是否为csv文件
	 * @author jiangbin
	 * @param filePath 文件路径
	 * @return
	 * @date 2019年1月30日下午4:16:49
	 */
	public static boolean isCsvFile(String filePath) {
		return isCsvExt(FilenameUtils.getExtension(filePath));
	}
}
