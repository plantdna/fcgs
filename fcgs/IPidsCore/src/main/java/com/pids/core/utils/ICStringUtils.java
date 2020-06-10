package com.pids.core.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串一些通用处理方法，类名中IC表示为ICore3的缩写以区分于apache的工具类
 *
 * @author ANDORY
 * @date 2016年1月8日下午5:32:43
 */
public class ICStringUtils {
    /**
     * 用于将给定字符串按驼峰格式组织并且将第一个字母小写,如:AB_CD-->abCd
     * 本方法用来转换表字段为对象的属性字段:USER_ID-->userId
     *
     * @param str
     * @return
     * @author jiangbin
     * @date 2012-4-30上午12:30:45
     */
    public static String humpFormat(String str) {
        str = str.toLowerCase();
        StringBuilder colSb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '_') {
                i++;
                String ss = str.charAt(i) + "";
                colSb.append(ss.toUpperCase());
            } else {
                colSb.append(str.charAt(i));
            }
        }
        return colSb.toString();
    }

    /**
     * 将驼峰格式字符串逆向转换成下划线分隔的字符串，如：samBarcode-->sam_barcode
     *
     * @param humpStr 如果字符串为null、空字符串则不处理直接返回
     * @return
     * @author jiangbin
     * @date 2017年6月9日上午10:22:05
     */
    public static String rehump(String humpStr) {
        if (StringUtils.isBlank(humpStr)) {
            return humpStr;
        }
        humpStr = humpStr.replaceAll("\\.", "_");
        List<String> targets = new ArrayList<>();
        String[] items = humpStr.split("_");
        for (int i = 0; i < items.length; i++) {
            if (StringUtils.isBlank(items[i])) {
                continue;
            }
            List<String> words = RestUtils.getRestWords(items[i]);
            if (CollectionUtils.isNotEmpty(words)) {
                targets.addAll(words);
            }
        }

        return ListUtils.list2Str(targets, "_");
    }

    /**
     * 将字符串第一个字母转成大写
     *
     * @param str
     * @return
     * @author jiangbin
     * @date 2012-4-30上午12:54:12
     */
    public static String firstToUpper(String str) {
        if (StringUtils.isBlank(str))
            return "";
        String first = String.valueOf(str.charAt(0)).toUpperCase();
        if (str.length() == 1) {
            return first;
        } else {
            return first + str.substring(1);
        }
    }

    /**
     * 将字符串第一个字母转成小写
     *
     * @param str
     * @return
     * @author jiangbin
     * @date 2012-4-30上午12:54:12
     */
    public static String firstToLower(String str) {
        if (StringUtils.isBlank(str))
            return "";
        String first = String.valueOf(str.charAt(0)).toLowerCase();
        if (str.length() == 1) {
            return first;
        } else {
            return first + str.substring(1);
        }
    }
}
