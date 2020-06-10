package com.pids.core.formatter.msg;

import com.pids.core.exception.ICoreException;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 通用字符串列表格式化工具类，本类支持使用指定分隔符将给定字符串列表格式化成一个字符串，默认的分隔符为逗号(“,”)
 * @Author Andory
 * @Date 2012-8-15下午11:57:49
 */
public class StringFormatter extends MsgFormatterTemplate<String> {
	private String separator = ",";

	/**
	 * 按指定分隔符格式化给定字符串列表
	 * @Author Andory
	 * @param sources 字符串列表 
	 * @param separator 分隔符号
	 * @return 格式化后的字符串
	 * @throws ICoreException
	 * @Date 2012-8-16上午12:06:18
	 */
	public static String format(List<String> sources, String separator) throws ICoreException {
		StringFormatter formatter = new StringFormatter();
		formatter.setSeparator(separator);
		return formatter.format(sources);
	}

	@Override
	public String format(List<String> sources) throws ICoreException {
		return super.format(sources);
	}

	@Override
	public String format(String source) throws ICoreException {
		if (StringUtils.isBlank(source)) {
			return "";
		}
		return String.format(getPattern(), source);
	}

	/**
	 * <pre>获取格式化字符串的正则式模板，此处默认使用简单方式处理：
	 * 	%s+分隔符
	 *  格式化后的字符串格式示例如：Bgg1,Bgg2,Bgg3,Bgg4,Bgg5
	 *  此示例中的分隔符即为默认分隔符逗号“,”
	 * </pre>
	 * @Author Andory
	 * @return
	 * @Date 2012-8-16上午12:02:45
	 */
	protected String getPattern() {
		return "%s" + separator;
	}

	@Override
	protected String formatRawStr(String rawStr) throws ICoreException {
		rawStr = rawStr == null ? "" : rawStr;
		if (rawStr.length() > this.separator.length()) {
			return rawStr.substring(0, rawStr.length() - separator.length());
		} else {
			return "";
		}
	}

	/**
	 * 设置字符串分隔符号
	 * @Author Andory
	 * @param separator
	 * @Date 2012-8-15下午11:56:59
	 */
	public void setSeparator(String separator) {
		this.separator = separator;
	}

	/**
	 * 获取字符串分隔符号
	 * @Author Andory
	 * @return
	 * @Date 2012-8-15下午11:57:11
	 */
	public String getSeparator() {
		return separator;
	}

}
