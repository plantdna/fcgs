package com.pids.core.utils;

import com.pids.core.formatter.msg.StringFormatter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Rest规范相关工具类
 * @author jiangbin
 * @date 2014年3月24日上午10:34:09
 */
public class RestUtils {
	/**
	 * 获取指定字符串的Rest表示，如:SysUser==>/sys/user
	 * @author jiangbin
	 * @param str
	 * @return
	 * @date 2014年3月24日上午10:34:57
	 */
	public static String getRestPath(String str) {
		List<String> words = getRestWords(str);
		return "/" + StringFormatter.format(words, "/");
	}

	/**
	 * 解析成Rest风格的Word列表，如:SysUserInfo==>{"sys","user","info"}
	 * @author jiangbin
	 * @param str
	 * @return
	 * @date 2014年3月24日上午10:50:42
	 */
	public static List<String> getRestWords(String str) {
		if (StringUtils.isEmpty(str))
			return null;
		String[] words = str.split("(?<!^)(?=[A-Z])");
		for (int i = 0; i < words.length; i++) {
			words[i] = StringUtils.lowerCase(words[i]);
		}
		return ListUtils.array2List(words);
	}

	public static void main(String[] args) {
		System.out.println(getRestPath("SysUserInfo"));
		System.out.println(getRestPath("sysuserinfo"));
		System.out.println(getRestPath(null));
		System.out.println(getRestPath(""));
	}
}
