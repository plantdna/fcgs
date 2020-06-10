package com.pids.core.utils;

/**
 * 获得汉字首字母工具
 * @author jiangbin
 * @date 2014年2月26日下午6:22:49
 */
public class InitialsUtils {

	//字母Z使用了两个标签，这里有２７个值  
	//i, u, v都不做声母, 跟随前面的字母  
	protected static final char[] chartable = { '啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈', '哈', '击', '喀', '垃', '妈', '拿',
			'哦', '啪', '期', '然', '撒', '塌', '塌', '塌', '挖', '昔', '压', '匝', '座' };

	protected static final char[] alphatable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
			'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	protected static final int[] table = new int[27];

	static {//初始化  
		for (int i = 0; i < 27; ++i) {
			table[i] = getGBValue(chartable[i]);
		}
	}

	/**
	 * <pre>获取单个字符的声母：
	 * 1、英文字母返回对应的大写字母 
	 * 2、其他非简体汉字返回 '0'
	 * 3、简体汉字返回大写声母</pre>
	 * @author jiangbin
	 * @param ch
	 * @return
	 * @date 2014年2月26日下午6:27:12
	 */
	public static char getCharInitials(char ch) {
		if (ch >= 'a' && ch <= 'z')
			return (char) (ch - 'a' + 'A');
		if (ch >= 'A' && ch <= 'Z')
			return ch;
		int gb = getGBValue(ch);
		if (gb < table[0])
			return '0';
		int i;
		for (i = 0; i < 26; ++i) {
			if (match(i, gb))
				break;
		}
		if (i >= 26)
			return '0';
		else
			return alphatable[i];
	}

	/**
	 * 获取字符串各字符声母
	 * @author jiangbin
	 * @param sourceStr
	 * @return
	 * @date 2014年2月26日下午6:40:27
	 */
	public static String getStrInitials(String sourceStr) {
		String result = "";
		try {
			for (int i = 0; i < sourceStr.length(); i++) {
				result += getCharInitials(sourceStr.charAt(i));
			}
		} catch (Exception e) {
			result = "";
		}
		return result;
	}

	protected static boolean match(int i, int gb) {
		if (gb < table[i])
			return false;
		int j = i + 1;

		//字母Z使用了两个标签  
		while (j < 26 && (table[j] == table[i]))
			++j;
		if (j == 26)
			return gb <= table[j];
		else
			return gb < table[j];
	}

	/**
	 * 取出汉字的编码
	 * @author jiangbin
	 * @param ch
	 * @return
	 * @date 2014年2月26日下午6:23:27
	 */
	protected static int getGBValue(char ch) {
		String str = new String();
		str += ch;
		try {
			byte[] bytes = str.getBytes("GB2312");
			if (bytes.length < 2)
				return 0;
			return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
		} catch (Exception e) {
			return 0;
		}
	}

}