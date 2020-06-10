package com.pids.core.utils;

import com.pids.core.exception.ICoreException;
import com.pids.core.spliter.impl.StringSpliter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 字符串处理工具类
 * @author: jiangbin
 * @date: 2011-6-6上午10:28:09
 */
public class StringEx {
	private static final Pattern enPat = Pattern.compile("[a-zA-Z]+");
	private static final Pattern cnPat = Pattern.compile("[\u4e00-\u9fa5]+");
	private static final Pattern numPat = Pattern.compile("[0-9]+");
	private static final char[] regexMetaChars = { '(', '[', '{', '\\', '^', '-', '$', '|', '}', ']', ')', '?', '*',
			'+', '.' };

	/**
	 * <p>方法名称：sNull </p>
	 * <p>方法描述：处理obj==null函数,同时将删除末尾的特殊字符 </p>
	 * @param obj
	 * @return
	 * @since Jul 14, 2009
	 */
	public static String sNull(Object obj) {
		// 当obj==null时，如果使用toString()，将会出错！
		return obj == null ? "" : obj.toString();
	}

	/**
	 * <p>方法名称：isNull</p>
	 * <p>方法描述：判定字符串是否为null或空字符串</p>
	 * @param str
	 * @return
	 * @author jiangbin
	 * @since Nov 20, 2009
	 */
	public static boolean isNull(Object str) {
		return sNull(str).isEmpty();
	}

	/**
	 * 将字符串中的正则式元字符转换成普通字符
	 * @author jiangbin
	 * @param source
	 * @return
	 * @date 2013-12-1下午3:15:02
	 */
	public static String str2regex(String source) {
		StringBuffer target = new StringBuffer();
		for (int index = 0; index < source.length(); index++) {
			char c = source.charAt(index);
			if (isRegexMetaChar(c)) {
				target.append("\\").append(c);
			} else {
				target.append(c);
			}
		}
		return target.toString();
	}

	/**
	 * 字符是否为正则式元字符
	 * @author jiangbin
	 * @param c
	 * @return true/false--是/不是正则式元字符
	 * @date 2013-12-1下午3:19:17
	 */
	public static boolean isRegexMetaChar(char c) {
		for (char regex : regexMetaChars) {
			if (c == regex) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <p>方法名称：delCtlCh</p>
	 * <p>方法描述：删除字符串中的控制字符</p>
	 * @param str
	 * @return
	 * @author jiangbin
	 * @since 2010-3-22
	 */
	public static String delCtlCh(String str) {
		str = sNull(str);
		return str.replaceAll("\\s", "");
	}

	/**
	 * <p>方法名称：delEndCtr</p>
	 * <p>方法描述：删除字符串末尾的回车字符</p>
	 * @param value
	 * @return
	 * @author jiangbin
	 * @since 2010-3-22
	 */
	public static String delEndCtr(String str) {
		str = sNull(str);
		for (int i = str.length() - 1; i > -1; i--) {
			String ch = str.charAt(i) + "";
			if (delCtlCh(ch).equals("")) {
				str = str.substring(0, i + 1);
			} else {
				str = str.substring(0, i + 1);
				return str;
			}
		}
		return str;
	}

	/**
	 * <p>方法名称：dealSqlChars</p>
	 * <p>方法描述：处理字符串中的与SQL语句相关的特殊字符</p>
	 * @param str
	 * @return
	 * @author 江斌
	 * @since 2010-4-16
	 */
	public static String dealSqlChars(String str) {
		str = StringEx.sNull(str);
		StringBuilder newStr = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {// 必需先替换'\'为'\\'再进行其它字符的替换
			if (str.charAt(i) == 92) {
				newStr.append("\\\\");
			} else {
				newStr.append(str.charAt(i));
			}
		}
		str = newStr.toString();
		str = str.replaceAll("'", "''");
		return str;
	}

	/**
	 * <p>方法名称：getStr</p>
	 * <p>方法描述：
	 * 		获取字符串str中的字符字符串信息,如果未找到字符串则返回空字符串。
	 *      如果参数str为null或空字符串则按空字符串处理
	 * </p>
	 * <pre>
	 * 例如：
	 *      null      =  ""
	 *     ""        =  ""
	 *     23344     =  ""
	 *     aaa12345  =  aaa
	 *     bbb12345  =  bbb
	 *     acc12ebc  =  acc
	 *     1245bbbb  =  bbbb
	 * </pre>
	 * @author LiuJunGuang
	 * @param str
	 * @return
	 * @date 2013-5-28下午3:26:10
	 */
	public static String getStr(String str) {
		return getRegexStr(str, enPat);
	}

	/**
	 * <p>方法名称：getNumberStr</p>
	 * <p>方法描述：
	 * 		获取字符串str中的数字字符串信息,如果未找到字符串则返回空字符串。
	 *      如果参数str为null或空字符串则按空字符串处理
	 * </p>
	 * <pre>
	 * 例如：
	 *      null      =  ""
	 *     ""        =  ""
	 *     23344     =  23344
	 *     aaa12345  =  12345
	 *     bbb12345  =  12345
	 *     acc12ebc  =  12
	 *     1245bbbb  =  1245
	 * </pre>
	 * @author LiuJunGuang
	 * @param str
	 * @return
	 * @date 2013-5-28下午3:26:10
	 */
	public static String getNumberStr(String str) {
		return getRegexStr(str, numPat);
	}

	/**
	 * <p>方法名称：getCnStr</p>
	 * <p>方法描述：获取字符串中的中文字符</p>
	 * @author LiuJunGuang
	 * @param str
	 * @return
	 * @date 2013年11月21日下午7:22:51
	 */
	public static String getCnStr(String str) {
		return getRegexStr(str, cnPat);
	}

	/**
	 * <p>方法名称：getRegexStr</p>
	 * <p>方法描述：获取字符串中配置模式pat的子字符串，pat不能为null！</p>
	 * @author LiuJunGuang
	 * @param str
	 * @param pat 匹配模式
	 * @return
	 * @date 2013年11月21日下午7:23:48
	 */
	public static String getRegexStr(String str, Pattern pat) {
		if (pat == null)
			throw new IllegalArgumentException("The paramter pat is not null!");
		Matcher m = pat.matcher(sNull(str));
		if (m.find()) {
			return m.group();
		}
		return "";
	}

	/**
	 * <p>方法名称：getNumber</p>
	 * <p>方法描述：将获取指定输入数字字符串的数字对象，通过该对象可以获取各种格式的数据值，如int、double等</p>
	 * @param str 数字字符串，若未指定或为非数字字符串则会导致解析失败
	 * @return 若解析失败会返回null值
	 * @author 江斌
	 * @since Aug 10, 2010
	 */
	public static Number getNumber(String str) {
		try {
			return NumberFormat.getInstance().parse(str);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * <p>方法名称：getInt</p>
	 * <p>方法描述：格式化字符串为int类型参数</p>
	 * @param str 数字字符串
	 * @return 格式化失败则返回null
	 * @author Administrator
	 * @since 2010-7-1
	 */
	public static Integer getInt(String str) {
		Number number = getNumber(str);
		return number == null ? null : number.intValue();
	}

	/**
	 * <p>方法名称：getLong</p>
	 * <p>方法描述：格式化字符串为long类型参数</p>
	 * @param str 数字字符串
	 * @return 格式化失败则返回null
	 * @author Administrator
	 * @since 2010-7-7
	 */
	public static Long getLong(String str) {
		Number number = getNumber(str);
		return number == null ? null : number.longValue();
	}

	/**
	 * 获取long类型的值，如果参数为null则返回0
	 * @author LiuJunGuang
	 * @param l
	 * @return long
	 * @date 2013-3-29下午2:05:16
	 */
	public static long getLong(Long l) {
		return l == null ? 0 : l.longValue();
	}

	/**
	 * <p>方法名称：getBoolean</p>
	 * <p>方法描述：格式化指定字符串为Boolean值对象</p>
	 * @param booleanStr 字符串，若为空值或null值则返回null值
	 * @return true--成功转换了字符串为Boolean类型对象,false--转换失败
	 * @author jiangbin
	 * @since Aug 23, 2010
	 */
	public static Boolean getBoolean(String booleanStr) {
		if (StringEx.isNull(booleanStr)) {
			return null;
		}
		try {
			return Boolean.valueOf(booleanStr);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * <p>方法名称：toLower</p>
	 * <p>方法描述：将字符串转换成小写的</p>
	 * @param str 字符串，若为null或空则返回空字符串
	 * @return 转换后的小写字符串
	 * @author jiangbin
	 * @since Aug 24, 2010
	 */
	public static String toLower(String str) {
		return sNull(str).toLowerCase();
	}

	/**
	 * <p>方法名称：toUpper</p>
	 * <p>方法描述：将字符串转换成大写的</p>
	 * @param str 字符串，若为null或空则返回空字符串
	 * @return 转换后的大写字符串
	 * @author jiangbin
	 * @since Aug 24, 2010
	 */
	public static String toUpper(String str) {
		return sNull(str).toUpperCase();
	}

	/**
	 * <p>方法名称：match</p>
	 * <p>方法描述：给定字符串是否符合指定正则式格式</p>
	 * @param str 字符串,若为空则直接返回false
	 * @param regx 正则式,若为空则直接返回false
	 * @return true---校验格式正确，false---校验格式错误
	 * @author jiangbin
	 * @since Sep 16, 2010
	 */
	public static boolean match(String str, String regx) {
		str = sNull(str);
		Pattern p = Pattern.compile(regx);
		if (p.matcher(str).matches()) {
			return true;
		}
		return false;
	}

	/**
	 * <p>方法名称：isInteger</p>
	 * <p>方法描述：判定是否是整型数</p>
	 * @param str 字符串,若为空则直接返回false
	 * @return  true---是整型数，false---不是
	 * @author jiangbin
	 * @since 2010-5-14
	 */
	public static boolean isInteger(String str) {
		return match(str, "^[0-9]+$");
	}

	/**
	 * <p>方法名称：isFloat</p>
	 * <p>方法描述：判定是否是浮点型数</p>
	 * @param str 字符串,若为空则直接返回false
	 * @return  true---是，false---不是
	 * @author jiangbin
	 * @since 2010-5-14
	 */
	public static boolean isFloat(String str) {
		return match(str, "^[0-9]*(\\.?)[0-9]*$");
	}

	/**
	 * <p>方法名称：isNumber</p>
	 * <p>方法描述：判定是否是浮点数或整数</p>
	 * @param str 字符串,若为空则直接返回false
	 * @return  true---是，false---不是
	 * @author jiangbin
	 * @since 2010-5-14
	 */
	public static boolean isNumber(String str) {
		return match(str, "^([0-9]*(\\.?)[0-9]*)|([0-9]+)$");
	}

	/**
	 * <p>方法名称：convertEncode</p>
	 * <p>方法描述：转换指定字符串的编码格式</p>
	 * @param str 字符串，若为空或null则直接返回字字符串
	 * @param fromEncode 字符串当前编码，若未指定则使用默认编码
	 * @param toEncode 将要转换到的编码格式，若未指定则字符串不转换直接返回
	 * @return 转换编码后的字符串,若转换失败则返回null值
	 * @author jiangbin
	 * @since Sep 28, 2010
	 */
	public static String convertEncode(String str, String fromEncode, String toEncode) {
		if (isNull(str)) {
			return "";
		}
		if (isNull(toEncode)) {
			return str;
		}
		str = sNull(str);
		try {
			if (isNull(fromEncode)) {
				return new String(str.getBytes(), toEncode);
			} else {
				return new String(str.getBytes(fromEncode), toEncode);
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @Description: 将windows格式目录转换成java格式目录,如:d:\\ss.txt-->d://ss.txt
	 * @author: jiangbin
	 * @param dirStr
	 * @return
	 * @date: 2011-8-18下午02:29:08
	 */
	public static String convertWinDir(String dirStr) {
		String ss = sNull(dirStr);
		return ss.replace("\\", "/");
	}

	/**
	 * 格式化为指定位小数的数字,返回未使用科学计数法表示的具有指定位数的字符串。
	 * 该方法舍入模式：向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则为向上舍入的舍入模式。
	 * @param number Number类型的数字对象
	 * @param precision  小数精确度总位数,如2表示两位小数
	 * @return 返回格式化后的字符串
	 */
	public static String formatNumber(Number number, int precision) {
		if (number == null) {
			number = 0;
		}
		BigDecimal bg = new BigDecimal(number.toString());
		return bg.setScale(precision, BigDecimal.ROUND_HALF_UP).toPlainString();
	}

	/**
	 * 格式化为指定位小数的数字字符串
	 * @author Andory
	 * @param numberStr 数字字符串，可带小数
	 * @param precision  小数精确度总位数,如2表示两位小数
	 * @return 格式化失败时会返回null
	 * @date 2012-8-5下午5:02:40
	 */
	public static String formatNumber(String numberStr, int precision) {
		Number number = ParserUtil.parserNumber("Number", numberStr);
		return formatNumber(number, precision);
	}

	/**
	 * 获取文件扩展名，只截取最后的'.'分隔的扩展名
	 * @author jiangbin
	 * @param fileName 文件名
	 * @return 若不包含扩展名则返回空字符串
	 * @date 2012-7-17下午5:32:52
	 */
	public static String getFileExt(String fileName) {
		fileName = StringEx.sNull(fileName);
		int index = fileName.lastIndexOf('.');
		if (index > -1 && index < fileName.length() - 1) {
			return fileName.substring(index + 1);
		}
		return "";
	}

	/**
	 * 将格式为"KEY1:VALUE1,KEY2:VALUE2,..."这种字符串解析成Map
	 * @author jiangbin
	 * @param source
	 * @return 始终返回非null值的Map对象
	 * @date 2012-11-25下午3:39:14
	 */
	public static Map<String, String> str2Map(String source) {
		List<String> list = ListUtils.str2List(source);
		Map<String, String> target = new HashMap<String, String>();
		if (CollectionUtils.isEmpty(list)) {
			return target;
		}
		String[] mapArray = null;
		for (String str : list) {
			mapArray = str.split(":");
			if (!str.contains(":") || mapArray.length < 2) {
				continue;
			}
			String key = StringEx.sNull(mapArray[0]);
			String value = StringEx.sNull(mapArray[1]);
			if (StringEx.isNull(key) || StringEx.isNull(value)) {
				continue;
			}
			target.put(key, value);
		}
		return target;
	}

	/**
	 * 截取异常信息,返回最后一个":"之后的字符串信息(不包括最后一个":")
	 * @author LiuJunGuang
	 * @param message 异常信息
	 * @return string 如果message为null或空字符串则返回null或空字符串，如果message中不包含“：”则返回message本身
	 * @date 2013-3-12下午6:08:19
	 */
	public static String subMessage(String message) {
		if (StringUtils.isEmpty(message)) {
			return message;
		}
		int index = message.lastIndexOf(':');
		if (index != -1) {
			return message = message.substring(index + 1, message.length());
		}
		return message;
	}

	/**
	 * 判定单精度浮点值是否相同
	 * @author Andory
	 * @param source
	 * @param target
	 * @return
	 * @date 2013年8月21日下午5:07:47
	 */
	public static boolean isEqualFloat(float source, float target) {
		double exp = 10E-10;
		if (Math.abs(source - target) > -1 * exp && Math.abs(source - target) < exp) {
			return true;
		}
		return false;
	}

	/**
	 * 判定双精度浮点值是否相同
	 * @author Andory
	 * @param source
	 * @param target
	 * @return
	 * @date 2013年8月21日下午5:08:22
	 */
	public static boolean isEqualDouble(double source, double target) {
		double exp = 10E-10;
		if (Math.abs(source - target) > -1 * exp && Math.abs(source - target) < exp) {
			return true;
		}
		return false;
	}

	/**
	 * 判定给定字符串是否包含中文字符
	 * @author jiangbin
	 * @param str
	 * @return
	 * @date 2013年11月14日下午5:12:20
	 */
	public static boolean hasCn(String str) {
		Matcher matcher = cnPat.matcher(sNull(str));
		return matcher.find();
	}

	/**
	 * 判定给定字符串是否包含英文字母
	 * @author jiangbin
	 * @param str
	 * @return
	 * @date 2013年11月14日下午5:12:21
	 */
	public static boolean hasEn(String str) {
		Matcher matcher = enPat.matcher(sNull(str));
		return matcher.find();
	}

	/**
	 * 判定给定字符串是否包含数字字符
	 * @author jiangbin
	 * @param str
	 * @return
	 * @date 2013年11月14日下午5:12:23
	 */
	public static boolean hasDigit(String str) {
		Matcher matcher = numPat.matcher(sNull(str));
		return matcher.find();
	}

	/**
	 * 将字符串按给定步进值分割成列表
	 * @author jiangbin
	 * @param str
	 * @param step
	 * @return 若分割失败返回null值
	 * @date 2013-12-3下午12:46:58
	 */
	public static List<String> split(String str, int step) {
		try {
			return new StringSpliter().split(str, step);
		} catch (ICoreException e) {
			return null;
		}
	}

}
