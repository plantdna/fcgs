package com.pids.core.utils;

import com.pids.core.exception.ICoreException;
import com.pids.core.formatter.Formatter;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 文件大小格式设置:返回文件大小并带单位，自动转换为合适单位的文件大小
 * 文件以KB/MB/GB/TB为单位显示，保留俩位小数
 * @author sunqiuyun
 * @date 2015年7月16日上午11:38:09
 */

public class SizeFormater implements Formatter<Long, String> {
	private static String pattern = "^([0-9]+)([a-zA-Z]+)$";
	private static Pattern sizePattern = Pattern.compile("^[0-9]+[a-zA-Z]+$");

	@Override
	public String format(Long fileSize) throws ICoreException {
		if (fileSize == null) {
			return null;
		}
		double kiloByte = fileSize / 1024;
		if (kiloByte < 1) {
			return fileSize + "Byte";
		}

		double megaByte = kiloByte / 1024;
		if (megaByte < 1) {
			BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
			return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
		}

		double gigaByte = megaByte / 1024;
		if (gigaByte < 1) {
			BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
			return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
		}

		double teraBytes = gigaByte / 1024;
		if (teraBytes < 1) {
			BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
			return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
		}
		BigDecimal result4 = new BigDecimal(teraBytes);
		return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
	}

	/**
	 * 将字符串表示的大小转换成字节表示的大小
	 * @author Jiangbin
	 * @param fileSize
	 * @return
	 * @throws ICoreException
	 * @date 2015年7月18日下午1:53:26
	 */
	public long format(String fileSize) throws ICoreException {
		if (!sizePattern.matcher(fileSize).find()) {
			return 0;
		}
		List<String> parts = RegexUtils.match(fileSize, pattern);
		String value = parts.get(0);
		String unit = parts.get(1);
		if (unit.equalsIgnoreCase("byte")) {
			return NumberUtils.toLong(value);
		}
		if (unit.equalsIgnoreCase("kb")) {
			return NumberUtils.toLong(value) * 1024;
		}
		if (unit.equalsIgnoreCase("mb")) {
			return NumberUtils.toLong(value) * 1024 * 1024;
		}
		if (unit.equalsIgnoreCase("gb")) {
			return NumberUtils.toLong(value) * 1024 * 1024 * 1024;
		}
		if (unit.equalsIgnoreCase("tb")) {
			return NumberUtils.toLong(value) * 1024 * 1024 * 1024 * 1024;
		}
		return 0;
	}
}
