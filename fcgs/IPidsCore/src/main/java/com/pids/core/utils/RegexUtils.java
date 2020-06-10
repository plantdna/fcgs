package com.pids.core.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则式工具类
 *
 * @author jiangbin
 * @date 2014年3月12日下午1:43:03
 */
public class RegexUtils {
    private static final Pattern enPat = Pattern.compile("[a-zA-Z]+");
    private static final Pattern numPat = Pattern.compile("[0-9]+");
    private static final String charParttern = "[-`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
    private static final char[] regexMetaChars = {'(', '[', '{', '\\', '^', '-', '$', '|', '}', ']', ')', '?', '*',
            '+', '.'};

    /**
     * 字符是否为正则式元字符
     *
     * @param c
     * @return true/false--是/不是正则式元字符
     * @author jiangbin
     * @date 2013-12-1下午3:19:17
     */
    private static boolean isRegexMetaChar(char c) {
        for (char regex : regexMetaChars) {
            if (c == regex) {
                return true;
            }
        }
        return false;
    }

    /**
     * 将字符串中的正则式元字符转换成普通字符
     *
     * @param source
     * @return
     * @author jiangbin
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
     * 获取给定字符串的数字字符串
     *
     * @param str
     * @return
     * @author jiangbin
     * @date 2014年3月16日上午1:13:50
     */
    public static String getMatchedNumberStr(String str) {
        return getRegexStr(str, numPat);
    }

    /**
     * 获取给定字符串的字母字符串
     *
     * @param str
     * @return
     * @author jiangbin
     * @date 2014年3月16日上午1:13:49
     */
    public static String getMatchedEnStr(String str) {
        return getRegexStr(str, enPat);
    }

    /**
     * 获取字符串中指定类型正则式字符串
     *
     * @param str
     * @param pattern
     * @return
     * @author jiangbin
     * @date 2014年3月16日上午1:15:46
     */
    public static String getRegexStr(String str, Pattern pattern) {
        Matcher m = pattern.matcher(StringUtils.stripToEmpty(str));
        if (m.find()) {
            return m.group();
        }
        return "";
    }

    /**
     * <pre>通过正则式自动拆分字符串各部分，如：
     * ^([a-zA-Z]{1})([a-zA-Z]{1,5})([0-9]{2})(P)([0-9]{5})([a-zA-Z]{1,5})$
     * 以上正则式可以自动拆分条码号"MGJR14P00002AP"各部分为：M, GJR, 14, P, 00002, AP
     * 注意：对于需要拆分的部分必需使用括号包裹起来，否则将视为不会拆分，给定的字符串中不能包含回车换行符，因为本功能是单行扫描功能
     * </pre>
     *
     * @param source  源字符串
     * @param pattern 正则式
     * @return
     * @author jiangbin
     * @date 2014年8月7日下午5:02:40
     */
    public static List<String> match(String source, String pattern) {
        try {
            if (StringUtils.isBlank(source)) {
                return null;
            }
            if (StringUtils.isBlank(pattern)) {
                return null;
            }
            Scanner s = new Scanner(source);
            s.findInLine(pattern);
            MatchResult result = s.match();
            List<String> parts = new ArrayList<String>();
            for (int i = 1; i <= result.groupCount(); i++) {
                parts.add(result.group(i));
            }
            s.close();
            return parts;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 查找指定字符串中匹配到给定正则式的字符串，匹配时是忽略大小写并且“.”可匹配到行结束符的
     *
     * @param source  源字符串
     * @param pattern 正则式
     * @return
     * @author jiangbin
     * @date Jul 19, 2019 12:50:04 PM
     */
    public static List<String> find(String source, String pattern) {
        try {
            if (StringUtils.isBlank(source)) {
                return null;
            }
            if (StringUtils.isBlank(pattern)) {
                return null;
            }
            Matcher matcher = compile(source, pattern);
            List<String> targets = new ArrayList<>();
            while (matcher.find()) {
                targets.add(matcher.group());
            }
            return targets;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 查找指定字符串中匹配到给定正则式的字符串，匹配时是忽略大小写并且“.”可匹配到行结束符的
     *
     * @param source
     * @param pattern
     * @return java.util.List<java.lang.String>
     * @author jiangbin
     * @date 2019-09-25 09:54
     **/
    public static List<String> find(String source, Pattern pattern) {
        try {
            if (StringUtils.isBlank(source)) {
                return null;
            }
            if (pattern == null) {
                return null;
            }
            Matcher matcher = pattern.matcher(source);
            List<String> targets = new ArrayList<>();
            while (matcher.find()) {
                targets.add(matcher.group());
            }
            return targets;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取正则匹配器中的匹配数据列表
     *
     * @param matcher
     * @return java.util.List<java.lang.String>
     * @author jiangbin
     * @date 2019-09-05 15:53
     **/
    public static List<String> getValues(Matcher matcher) {
        if (matcher == null) {
            return null;
        }
        List<String> values = new ArrayList<>();
        while (matcher.find()) {
            for (int group = 1; group <= matcher.groupCount(); group++) {
                values.add(StringUtils.stripToEmpty(matcher.group(group)));
            }
        }
        return values;
    }

    /**
     * 用正则式匹配给定的字符串数据，支持忽略大小写并且“.”可匹配到行结束符的
     *
     * @param source  待匹配数据内容
     * @param pattern 正则式
     * @return java.util.regex.Matcher
     * @author jiangbin
     * @date 2019-08-21 16:56
     **/
    public static Matcher compile(String source, String pattern) {
        return pattern(pattern).matcher(source);
    }

    /**
     * 创建正则式匹配器
     *
     * @param pattern 正则式
     * @return java.util.regex.Pattern
     * @author jiangbin
     * @date 2019-09-05 15:29
     **/
    public static Pattern pattern(String pattern) {
        return Pattern.compile(pattern, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    }

    /**
     * 查找指定字符串中匹配到给定正则式的第一个字符串，匹配时是忽略大小写的
     *
     * @param source  源字符串
     * @param pattern 正则式
     * @return
     * @author jiangbin
     * @date Jul 19, 2019 12:50:44 PM
     */
    public static String findFirst(String source, String pattern) {
        return ListUtils.getFirst(find(source, pattern));
    }

    /**
     * 查找指定字符串中匹配到给定正则式的第一个字符串，匹配时是忽略大小写的
     *
     * @param source  字符串
     * @param pattern 正则式
     * @return java.lang.String
     * @author jiangbin
     * @date 2019-09-25 09:55
     **/
    public static String findFirst(String source, Pattern pattern) {
        return ListUtils.getFirst(find(source, pattern));
    }

    /**
     * 查找指定字符串中匹配到给定正则式的最的一个字符串，匹配时是忽略大小写的
     *
     * @param source  源字符串
     * @param pattern 正则式
     * @return
     * @author jiangbin
     * @date Jul 19, 2019 12:50:52 PM
     */
    public static String findLast(String source, String pattern) {
        return ListUtils.getLast(find(source, pattern));
    }

    /**
     * 去掉给定URL中的参数字符串部分
     *
     * @param url 如:/a/b/c?size=100&page=1，处理后得：/a/b/c
     * @return
     * @author jiangbin
     * @date 2016年8月25日下午12:12:20
     */
    public static String deleteParamStr(String url) {
        if (StringUtils.isBlank(url)) {
            return url;
        }
        //去掉末尾参数
        String pattern = "^.*(\\?.+=.+&*).*$";
        List<String> matcheds = RegexUtils.match(url, pattern);
        if (CollectionUtils.isEmpty(matcheds)) {
            return url;
        }
        for (String matched : matcheds) {//去掉参数
            url = url.replace(matched, "");
        }
        return url;
    }

    /**
     * 删除字符串的特殊字符，只留下中文、英文、数字、下划线、空格，并且会自动去队空白
     *
     * @param str
     * @return
     * @author jiangbin
     * @date 2017年6月13日下午11:10:46
     */
    public static String delSpecialChar(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        return str.replaceAll(charParttern, "");
    }

    /**
     * 替换字符串中特殊字符为下划线
     *
     * @param str
     * @return
     * @author jiangbin
     * @date 2017年6月14日上午3:35:46
     */
    public static String toUnderline(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        str = str.replaceAll(charParttern, "_");
        return ListUtils.list2Str(ListUtils.str2List(str, "_"), "_");
    }

    /**
     * 替换字符串中特殊字符为下划线
     *
     * @param strList
     * @return
     * @author jiangbin
     * @date 2017年6月14日上午3:35:46
     */
    public static List<String> toUnderline(List<String> strList) {
        if (CollectionUtils.isEmpty(strList)) {
            return null;
        }
        List<String> targets = new ArrayList<>();
        for (String str : strList) {
            targets.add(toUnderline(str));
        }
        return targets;
    }

    /**
     * 删除空白字符
     *
     * @param str
     * @return
     * @author jiangbin
     * @date 2017年6月13日下午11:18:27
     */
    public static String delSpace(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        return str.replaceAll(" ", "");
    }

    /**
     * 获取变量名，即必需以字母、下划线开头且由数字、下划线、字母组成的字符串，
     * 若以非字母 、下划线开头的变量将直接处理掉前部非法字符后返回，若无法找到字母或下划线则返回null
     *
     * @param target
     * @return
     * @author jiangbin
     * @date 2017年6月14日上午6:17:42
     */
    public static String getVariableName(String target) {
        for (int i = 0; i < target.length(); i++) {
            if (Character.isLetter(target.charAt(i))) {
                return target.substring(i);
            }
        }
        return null;
    }

    /**
     * 判断是否为包含中文的字符串
     *
     * @param str
     * @return
     */
    public static boolean isCnStr(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * 分析出URL中的参数名和参数值
     *
     * @param source
     * @return java.util.Map<java.lang.String, java.lang.String>
     * @author jiangbin
     * @date 2019-08-24 14:13
     **/
    public static Map<String, String> findUrlParams(String source) {
        Map<String, String> params = new HashMap<>();
        if (StringUtils.isBlank(source)) {
            return params;
        }
        Matcher matcher = RegexUtils.compile(source, "[&\\?]{1}([^&]*?)=([^&]+)");
        while (matcher.find()) {
            String paramName = StringUtils.stripToEmpty(matcher.group(1));
            String paramValue = StringUtils.stripToEmpty(matcher.group(2));
            params.put(paramName, paramValue);
        }
        return params;
    }
}
