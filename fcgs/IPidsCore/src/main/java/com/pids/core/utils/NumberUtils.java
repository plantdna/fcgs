package com.pids.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 数字处理工具类
 *
 * @author jiangbin
 * @date 2015年5月6日下午4:34:13
 */
public class NumberUtils {
    /**
     * <pre>四舍五入方式格式化数值为指定精度数值，
     *  124 --> 124
     *  0.244-->0.24
     *  0.245-->0.25
     *  0.246-->0.25
     *  </pre>
     *
     * @param number    数值，若值为null或空字符串则返回null
     * @param precision 小数精度
     * @return
     * @author Jiangbin
     * @date 2013-6-7上午1:27:23
     */
    public static String format(Object number, int precision) {
        if (number == null) {
            return null;
        }
        if (BigDecimal.class.isAssignableFrom(number.getClass())) {
            String pattern = StringUtils.leftPad("", precision, "0");
            DecimalFormat myformat = new java.text.DecimalFormat("0." + pattern);
            return myformat.format(number);
        }
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setMaximumFractionDigits(precision);
        return format.format(number);
    }

    /**
     * 获取大写数字(key)与阿拉伯数字(value)映射关系Mapper，例如：零-->0
     *
     * @return
     * @author Jiangbin
     * @date 2013-8-26下午11:10:53
     */
    public static Map<String, String> getNumberMapper() {
        Map<String, String> mapper = new HashMap<String, String>();
        mapper.put("零", "0");
        mapper.put("一", "1");
        mapper.put("二", "2");
        mapper.put("三", "3");
        mapper.put("四", "4");
        mapper.put("五", "5");
        mapper.put("六", "6");
        mapper.put("七", "7");
        mapper.put("八", "8");
        mapper.put("九", "9");
        return mapper;
    }

    /**
     * 格式化给定double数据，四舍五入保留指定小数位数
     *
     * @param source 待格式化数据
     * @param scale  小数位数
     * @return double
     * @author jiangbin
     * @date 2019/12/24 11:23 上午
     **/
    public static double precision(double source, int scale) {
        BigDecimal b = new BigDecimal(source);
        return b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 格式化给定float数据，四舍五入保留指定小数位数
     *
     * @param source 待格式化数据
     * @param scale  小数位数
     * @return float
     * @author jiangbin
     * @date 2019/12/24 11:25 上午
     **/
    public static float precision(float source, int scale) {
        BigDecimal b = new BigDecimal(source);
        return b.setScale(scale, BigDecimal.ROUND_HALF_UP).floatValue();
    }

}
