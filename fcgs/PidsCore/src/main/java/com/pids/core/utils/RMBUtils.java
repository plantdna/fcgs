package com.pids.core.utils;

import java.text.DecimalFormat;

/**
 * 转人民币大写工具类
 * @author jiangbin
 * @date 2014年2月14日下午6:44:15
 */
public class RMBUtils {

	/**
	 * 转换成人民币大写格式
	 * @author jiangbin
	 * @param value
	 * @return
	 * @date 2014年2月14日下午6:44:29
	 */
	public static String upper(double value) {
		String s = new DecimalFormat("#.00").format(value);
		s = s.replaceAll("\\.", "");
		char[] digit = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' };
		String unit = "仟佰拾兆仟佰拾亿仟佰拾万仟佰拾元角分";
		int l = unit.length();
		StringBuffer sb = new StringBuffer(unit);
		for (int i = s.length() - 1; i >= 0; i--)
			sb = sb.insert(l - s.length() + i, digit[(s.charAt(i) - 0x30)]);
		s = sb.substring(l - s.length(), l + s.length());
		s = s.replaceAll("零[拾佰仟]", "零").replaceAll("零{2,}", "零").replaceAll("零([兆万元])", "$1").replaceAll("零[角分]", "");
		if (s.endsWith("角"))
			s += "零分";
		if (!s.contains("角") && !s.contains("分") && s.contains("元"))
			s += "整";
		if (s.contains("分") && !s.contains("整") && !s.contains("角"))
			s = s.replace("元", "元零");
		return s;
	}

}