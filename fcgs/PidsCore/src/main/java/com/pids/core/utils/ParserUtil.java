package com.pids.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用于解析日期和数字字符串
 */
public class ParserUtil {
	private static Log log = LogFactory.getLog(ParserUtil.class);
	/**
	 * @fields DATE_PATTERN 日期格式 yyyy-MM-dd hh:mm:ss
	 */
	public static final String DATE_PATTERN = "yyyy-MM-dd hh:mm:ss";

	/**
	 * 解析日期字段，若格式化失败则返回null值
	 * @param filedName 字段名称，只是用来写日志
	 * @param dateStr 日期字符串值
	 * @return
	 */
	public static Date parserDate(String filedName, String dateStr) {
		return parserDate(filedName, dateStr, "yyyy-MM-dd", "yyyy年MM月dd日");
	}

	/**
	 * 解析日期字段，若格式化失败则返回null值
	 * @param filedName 字段名称，只是用来写日志
	 * @param dateStr 日期字符串值
	 * @param pattern 模式字符串，默认为"yyyy-MM-dd"
	 * @return
	 */
	public static Date parserDate(String filedName, String dateStr, String... pattern) {
		if (StringEx.isNull(pattern)) {
			pattern = new String[] { "yyyy-MM-dd" };
		}
		try {
			return DateUtils.parseDate(dateStr, pattern);
		} catch (Exception e) {
			log.warn("Fail to parser field " + filedName + " value:" + dateStr);
		}
		return null;
	}

	/**
	 * 格式化指定日期对象
	 * @author Andory
	 * @param date
	 * @param pattern
	 * @return
	 * @date 2013-5-19下午7:28:42
	 */
	public static String formatDate(Date date, String pattern) {
		if (StringEx.isNull(pattern)) {
			pattern = "yyyy-MM-dd";
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.format(date);
		} catch (Exception e) {
			log.warn("Fail to format date!");
		}
		return null;
	}

	/**
	 * 格式化指定日期对象为"yyyy-MM-dd"格式字符串
	 * @author Andory
	 * @param date
	 * @return
	 * @date 2013-5-19下午7:29:09
	 */
	public static String formatDate(Date date) {
		return formatDate(date, "yyyy-MM-dd");
	}

	/**
	 * 解析数字符串，若解析失败则返回null值
	 * @param filedName 字段名，只是用来记录日志
	 * @param numberStr 数字字符串
	 * @return
	 */
	public static Number parserNumber(String filedName, String numberStr) {
		try {
			return NumberFormat.getInstance().parse(numberStr);
		} catch (Exception e) {
			log.warn("Fail to parser field " + filedName + " value:" + numberStr);
		}
		return null;
	}

	/**
	 * 格式化成整型数据
	 * @author Andory
	 * @param filedName
	 * @param numberStr
	 * @return
	 * @date 2013-5-31下午6:13:54
	 */
	public static Integer parserInt(String filedName, String numberStr) {
		Number number = parserNumber(filedName, numberStr);
		return number == null ? 0 : number.intValue();
	}

	/**
	 * 格式化成双精度浮点数据
	 * @author Andory
	 * @param filedName
	 * @param numberStr
	 * @return
	 * @date 2013-5-31下午6:13:53
	 */
	public static Double parserDouble(String filedName, String numberStr) {
		Number number = parserNumber(filedName, numberStr);
		return number == null ? 0 : number.doubleValue();
	}

	/**
	 * 校正日期的时间，即在未指定时间时将自动设置成:12:00:00
	 * @author Andory
	 * @param date
	 * @return
	 * @date 2013-5-19下午7:17:26
	 */
	public static Date revise(Date date) {
		if (date == null) {
			return date;
		}
		String dateStr = formatDate(date, DATE_PATTERN);
		return parserDate("", dateStr);
	}

	/**
	 * 四舍五入方式格式化数值为指定精度数值
	 * @author Jiangbin
	 * @param number 数值，若值为null或空字符串则返回null
	 * @param precision 小数精度
	 * @return
	 * @date 2013-6-7上午1:27:23
	 */
	public static String formatNumber(Number number, int precision) {
		if (StringEx.isNull(number)) {
			return null;
		}
		String pattern = StringUtils.rightPad("#.", 2 + precision, '0');
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(number);
	}

}
