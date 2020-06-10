package com.pids.core.utils;

import com.pids.core.formatter.msg.StringFormatter;
import com.pids.core.mapper.utils.StringMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.*;

/**
 * 列表工具类
 *
 * @author jiangbin
 * @date 2014年3月12日下午1:26:37
 */
public class ListUtils {
    /**
     * @fields ROW_ENTER  换行符
     */
    private final static String ROW_ENTER = "\r\n";

    /**
     * 将给定的字符串数据构建一个列表类型对象
     *
     * @param params 参数列表，本方法会忽略其中的null值对象
     * @return
     * @author jiangbin
     * @date 2013-5-2下午3:26:37
     */
    public static List<String> createList(String... params) {
        return array2List(params);
    }

    /**
     * 将给定的数据对象构建一个列表类型对象
     *
     * @param params
     * @return
     * @author jiangbin
     * @date 2013-5-2下午3:26:37
     */
    @SafeVarargs
    public static <S> List<S> createList(S... params) {
        if (params == null) {
            return null;
        }
        List<S> targets = new ArrayList<S>();
        for (S param : params) {
            if (param != null) {
                targets.add(param);
            }
        }
        return targets;
    }

    /**
     * 合并给定两个列表数据到一个新列表中，源列表始终会排在前面,不会影响源和目标数据列表
     *
     * @param sources
     * @param targets
     * @return
     * @author Andory
     * @date 2013-5-6上午11:20:07
     */
    public static <T> List<T> mergerList(List<T> sources, List<T> targets) {
        List<T> results = new ArrayList<T>();
        if (CollectionUtils.isNotEmpty(sources)) {
            results.addAll(sources);
        }
        if (CollectionUtils.isNotEmpty(targets)) {
            results.addAll(targets);
        }
        return results;
    }

    /**
     * 将给定的列表合并成一个列表
     *
     * @param sources
     * @return
     * @author jiangbin
     * @date 2013年11月14日下午5:36:01
     */
    public static <T> List<T> mergerList(List<List<T>> sources) {
        List<T> results = new ArrayList<T>();
        if (CollectionUtils.isEmpty(sources)) {
            return results;
        }
        for (List<T> source : sources) {
            if (CollectionUtils.isNotEmpty(source)) {
                results.addAll(source);
            }
        }
        return results;
    }

    /**
     * 合并两个列表并对重复的元素去重
     *
     * @param sources
     * @param targets
     * @return
     * @author jiangbin
     * @date 2017年5月3日上午9:10:39
     */
    public static List<String> mergerUniqueList(List<String> sources, List<String> targets) {
        List<String> results = mergerList(sources, targets);
        if (CollectionUtils.isEmpty(results)) {
            return results;
        }
        return unique(results);
    }

    /**
     * 将字符串(支持中英文逗号、空格分隔，如:A,B,C)解析成List
     *
     * @param str
     * @return 若未指定字符串则返回空列表
     * @author jiangbin
     * @date 2012-4-25下午3:49:22
     */
    public static List<String> str2List(String str) {
        return str2List(str, "，, ");
    }

    /**
     * 将字符串(以指定分隔符分隔，如:A,B,C)解析成List
     *
     * @param str
     * @param separator 分隔符，若未给定则返回只包含指定字符串的列表
     * @return 若未指定字符串则返回空列表
     * @author jiangbin
     * @date 2012-4-25下午3:49:22
     */
    public static List<String> str2List(String str, String separator) {
        String[] array = StringUtils.split(str, separator);
        if (ArrayUtils.isEmpty(array)) {
            return new ArrayList<String>();
        }
        return array2List(array);
    }

    /**
     * 将字符串列表对象转换成字符串数组对象，如果list为null或空列表，则返回null
     *
     * @param list 要转换的字符串列表对象
     * @return 字符串数组
     * @author LiuJunGuang
     * @date 2012-11-6下午2:34:01
     */
    public static String[] list2Array(List<String> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        String[] strArray = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strArray[i] = list.get(i);
        }
        return strArray;
    }

    /**
     * 将字符串数组转换成以逗号分隔的字符串
     *
     * @param array
     * @return
     * @author Andory
     * @date 2013-5-12上午11:03:16
     */
    public static String array2Str(String[] array) {
        return array2Str(array, ",");
    }

    /**
     * 以指定分隔符构建字符串，且一个一维数组占一行,单行内默认使用逗号","作为分隔符
     *
     * @param arrays 二维数组
     * @return
     * @author jiangbin
     * @date 2014年4月8日下午1:14:29
     */
    public static String array2Str(String[][] arrays) {
        return array2Str(arrays, ",");
    }

    /**
     * 以指定分隔符构建字符串，且一个一维数组占一行，本方法不会忽略数组中的空格或null元素项
     *
     * @param arrays    二维数组
     * @param separator 若未给定则默认使用逗号","作为分隔符
     * @return
     * @author jiangbin
     * @date 2014年4月8日下午1:14:29
     */
    public static String array2Str(String[][] arrays, String separator) {
        if (arrays == null) {
            return "";
        }
        if (StringUtils.isBlank(separator)) {
            separator = ",";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrays.length; i++) {
            String[] array = arrays[i];
            sb.append(array2Str(array, false, separator));
            if (i != arrays.length - 1) {
                sb.append(ROW_ENTER);
            }
        }
        return sb.toString();
    }

    /**
     * 以指定分隔符构建字符串，本方法会自动忽略数组中的空格或null值元素项
     *
     * @param array     数组
     * @param separator 分隔符，默认为逗号","
     * @return
     * @author jiangbin
     * @date 2014年4月8日下午1:14:29
     */
    public static String array2Str(String[] array, String separator) {
        return array2Str(array, true, separator);
    }

    /**
     * 将一维数组以指定分隔符转换成字符串
     *
     * @param array       数组,若为null则返回空字符串
     * @param ignoreBlank 是否忽略数组中的null和空格字符串
     * @param separator   分隔符，若未给定则使用默认的逗号","分隔符
     * @return
     * @author jiangbin
     * @date 2016年6月7日上午9:29:44
     */
    public static String array2Str(String[] array, boolean ignoreBlank, String separator) {
        if (ArrayUtils.isEmpty(array)) {
            return "";
        }
        if (StringUtils.isBlank(separator)) {
            separator = ",";
        }
        StringBuilder sb = new StringBuilder();
        for (String word : array) {
            if (ignoreBlank && StringUtils.isBlank(word)) {
                continue;
            }
            sb.append(StringUtils.stripToEmpty(word)).append(separator);
        }
        return StringUtils.removeEnd(sb.toString(), separator);
    }

    /**
     * 将给定列表List转换成以逗号分隔的字符串
     *
     * @param sources 列表
     * @return 若未指定列表则返回空字符串，若返回null则表示格式化失败
     * @author jiangbin
     * @date 2012-4-25下午3:50:22
     */
    public static String list2Str(List<String> sources) {
        return list2Str(sources, ",");
    }

    /**
     * 将给定列表List转换成以指定分隔符分隔的字符串
     *
     * @param sources
     * @param separator 分隔符，若未指定默认以逗号代替
     * @return
     * @author jiangbin
     * @date 2014年3月13日下午4:18:55
     */
    public static String list2Str(List<String> sources, String separator) {
        try {
            return StringFormatter.format(sources, separator);
        } catch (Exception e) {
            Logger.getLogger(ListUtils.class).warn(e);
            return null;
        }
    }

    /**
     * 将给定类型的对象列表转换成列表
     *
     * @param array
     * @return
     * @author jiangbin
     * @date 2014年3月24日下午2:20:36
     */
    @SuppressWarnings("unchecked")
    public static <S> List<S> array2List(S... array) {
        List<S> strList = new LinkedList<S>();
        if (ArrayUtils.isNotEmpty(array)) {
            for (S item : array) {
                if (item != null) {
                    strList.add(item);
                }
            }
        }
        return strList;
    }

    /**
     * 字符串列表去重
     *
     * @param sources
     * @return
     * @author jiangbin
     * @date 2013-4-19下午7:50:50
     */
    public static List<String> unique(List<String> sources) {
        if (CollectionUtils.isEmpty(sources)) {
            return sources;
        }
        Set<String> targets = new LinkedHashSet<>();
        for (String source : sources) {
            if (StringUtils.isNotBlank(source)) {
                targets.add(source);
            }
        }
        return new ArrayList<>(targets);
    }

    /**
     * 将字符串列表转换成大写字符串列表，同时过滤掉null及空字符串
     *
     * @param strList
     * @return
     * @author jiangbin
     * @date 2013-4-21下午4:46:08
     */
    public static List<String> toUpper(List<String> strList) {
        List<String> targets = new ArrayList<String>();
        for (String string : strList) {
            if (StringUtils.isNotBlank(string)) {
                targets.add(string.toUpperCase());
            }
        }
        return targets;
    }

    /**
     * 将字符串列表转换成小写字符串列表，同时过滤掉null及空字符串
     *
     * @param strList
     * @return
     * @author jiangbin
     * @date 2013-4-21下午4:46:10
     */
    public static List<String> toLower(List<String> strList) {
        List<String> targets = new ArrayList<String>();
        for (String string : strList) {
            if (StringUtils.isNotBlank(string)) {
                targets.add(string.toLowerCase());
            }
        }
        return targets;
    }

    /**
     * 计算指定列表的长度
     *
     * @param lists
     * @return
     * @author Andory
     * @date 2013-5-12下午6:30:29
     */
    public static int size(Collection<?> lists) {
        return lists == null ? 0 : lists.size();
    }

    /**
     * 判定集合为空
     *
     * @param entityList
     * @return
     * @author jiangbin
     * @date 2013-4-2下午5:15:37
     */
    public static boolean isEmpty(Collection<?> entityList) {
        return entityList == null || entityList.isEmpty();
    }

    /**
     * 判定集合非空
     *
     * @param entityList
     * @return
     * @author jiangbin
     * @date 2013-4-2下午5:15:37
     */
    public static boolean isNotEmpty(Collection<?> entityList) {
        return entityList != null && !entityList.isEmpty();
    }

    /**
     * 列表排序,本排序方法是null值安全的
     *
     * @param sources
     * @param comparer
     * @author Andory
     * @date 2013年8月30日上午11:30:31
     */
    public static <T> void sortList(List<T> sources, Comparator<? super T> comparer) {
        if (isEmpty(sources) || comparer == null) {
            return;
        }
        Collections.sort(sources, comparer);
    }

    /**
     * 列表排序
     *
     * @param sources
     * @author Andory
     * @date 2013年8月30日上午11:30:30
     */
    public static <T extends Comparable<? super T>> void sortList(List<T> sources) {
        if (isEmpty(sources)) {
            return;
        }
        Collections.sort(sources);
    }

    /**
     * 获取列表的第一个对象，若不存在则返回null
     *
     * @param sources
     * @return
     * @author jiangbin
     * @date 2015年2月5日下午6:54:49
     */
    public static <S> S getFirst(List<S> sources) {
        return CollectionUtils.isNotEmpty(sources) ? sources.get(0) : null;
    }

    /**
     * 获取列表的最后一个对象，若不存在则返回null
     *
     * @param sources
     * @return
     * @author jiangbin
     * @date 2015年2月5日下午6:55:41
     */
    public static <S> S getLast(List<S> sources) {
        return CollectionUtils.isNotEmpty(sources) ? sources.get(sources.size() - 1) : null;
    }

    /**
     * 获取末尾项
     *
     * @param source    字符串，如:A,B,C
     * @param separator 各项间的分隔符
     * @return
     * @author jiangbin
     * @date 2018年7月9日上午11:10:06
     */
    public static String getLastStr(String source, String separator) {
        List<String> itemList = str2List(source, separator);
        return getLast(itemList);
    }

    /**
     * 获取末尾项
     *
     * @param source 字符串，如:A,B,C
     * @return
     * @author jiangbin
     * @date 2018年7月9日上午11:10:04
     */
    public static String getLastStr(String source) {
        return getLastStr(source, "");
    }

    /**
     * 获取第一项
     *
     * @param source    字符串，如:A,B,C
     * @param separator 各项间的分隔符
     * @return
     * @author jiangbin
     * @date 2018年7月9日上午11:10:06
     */
    public static String getFirstStr(String source, String separator) {
        List<String> itemList = str2List(source, separator);
        return getFirst(itemList);
    }

    /**
     * 获取第一项
     *
     * @param source 字符串，如:A,B,C
     * @return
     * @author jiangbin
     * @date 2018年7月9日上午11:10:04
     */
    public static String getFirstStr(String source) {
        return getFirstStr(source, "");
    }

    /**
     * 将给定列表各元素重复给定次数，如:[A,B,C]列表重复2次，得到[A,A,B,B,C,C]
     *
     * @param sources     需要进行重复的源列表，要求该列表中元素对象可序列化
     * @param repeatCount 重复次数，若小于1则直接返回源列表
     * @return
     * @author jiangbin
     * @date 2016年6月15日下午4:36:26
     */
    public static <S extends Serializable> List<S> repeat(List<S> sources, int repeatCount) {
        if (CollectionUtils.isEmpty(sources)) {
            return null;
        }
        if (repeatCount < 1) {
            return sources;
        }
        List<S> targets = new ArrayList<>();
        for (S source : sources) {
            for (int i = 0; i < repeatCount; i++) {
                S target = ObjectCopier.copy(source);
                targets.add(target);
            }
        }
        return targets;
    }

    /**
     * <pre>创建数组列表，支持数字和字母两种格式，如：
     * ['1','2','3']
     * 或
     * ['A','B','C']
     * 本方法可应用于创建电泳板的行和列标题</pre>
     *
     * @param count 目标元素数，若给定数字如3表示创建数字格式数组['1','2','3']，
     *              若给定字符如c或C则表示创建字母数组['A','B','C']
     * @return
     * @author jiangbin
     * @date 2016年10月12日下午8:37:06
     */
    public static String[] createArray(int count) {
        int length = 0;
        boolean isLetter = true;
        if (Character.isUpperCase(count)) {
            length = count - 'A' + 1;
        } else if (Character.isLowerCase(count)) {
            length = count - 'a' + 1;
        } else if (NumberUtils.isDigits("" + count)) {
            length = count;
            isLetter = false;
        }
        String[] array = new String[length];
        for (int i = 0; i < length; i++) {
            if (isLetter) {
                array[i] = "" + (char) ('A' + i);
            } else {
                array[i] = "" + (i + 1);
            }
        }
        return array;
    }

    /**
     * 去除重复的分隔符
     *
     * @param target
     * @param separator
     * @return
     * @author jiangbin
     * @date 2017年6月14日上午3:53:49
     */
    public static String delRepeat(String target, String separator) {
        return list2Str(str2List(target, separator), separator);
    }

    /**
     * 判定列表长度是否一致
     *
     * @param sources
     * @param targets
     * @return
     * @author jiangbin
     * @date 2017年7月7日下午5:32:04
     */
    public static boolean isEqualSize(Collection<?> sources, Collection<?> targets) {
        return ListUtils.size(sources) == ListUtils.size(targets);
    }

    /**
     * 字符串转换成数组
     *
     * @param source 源字符串，以逗号","分隔
     * @return 若字符串为空或null则返回null
     * @author jiang
     * @date 2018年6月1日下午9:28:44
     */
    public static String[] str2Array(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        List<String> targets = str2List(source);
        return list2Array(targets);
    }

    /**
     * 字符串转换成数组
     *
     * @param source    源字符串
     * @param separator 分隔符
     * @return 若字符串为空或null则返回null
     * @author jiang
     * @date 2018年6月1日下午9:31:13
     */
    public static String[] str2Array(String source, String separator) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        List<String> targets = str2List(source, separator);
        return list2Array(targets);
    }

    /**
     * Int类型列表转换成数组
     *
     * @param list
     * @return
     * @author jiang
     * @date 2018年8月28日上午10:59:29
     */
    public static int[] intList2Array(List<Integer> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    /**
     * Int数组转换成List
     *
     * @param array
     * @return
     * @author jiang
     * @date 2018年8月28日上午11:12:34
     */
    public static List<Integer> intArray2List(int[] array) {
        List<Integer> strList = new LinkedList<>();
        if (ArrayUtils.isNotEmpty(array)) {
            for (int item : array) {
                strList.add(item);
            }
        }
        return strList;
    }

    /**
     * 比较两个字符串数据组是否相同
     *
     * @param sources
     * @param targets
     * @return
     * @author jiang
     * @date 2018年9月2日下午4:27:23
     */
    public static boolean isEquals(String[] sources, String[] targets) {
        List<String> sList = array2List(sources);
        List<String> tList = array2List(targets);
        return isEquals(sList, tList);
    }

    /**
     * 比较两个字符串列表是否相同
     *
     * @param sources
     * @param targets
     * @return boolean
     * @author jiangbin
     * @date 2019/11/26 2:26 下午
     **/
    public static boolean isEquals(List<String> sources, List<String> targets) {
        StringMapper smapper = new StringMapper();
        smapper.add(sources);
        smapper.delete(targets);

        StringMapper tmapper = new StringMapper();
        tmapper.add(targets);
        tmapper.delete(sources);

        return smapper.isEmpty() && tmapper.isEmpty();
    }

    /**
     * 获取两个字符串列表中的重复字符串
     *
     * @param sources
     * @param targets
     * @return java.util.List<java.lang.String>
     * @author jiangbin
     * @date 2019/11/26 3:35 下午
     **/
    public static List<String> getRepeats(List<String> sources, List<String> targets) {
        StringMapper smapper = new StringMapper();
        smapper.add(sources);
        return smapper.getValues(targets);
    }

    /**
     * 转换成错误消息，主要是将自动截取部分消息，对后面内容进行省略
     *
     * @param messageList
     * @return
     * @author jiang
     * @date 2018年11月2日下午2:26:15
     */
    public static String toErrorMsg(List<String> messageList) {
        if (CollectionUtils.isEmpty(messageList)) {
            return "";
        }
        int maxSize = 8;
        if (ListUtils.size(messageList) > maxSize) {
            return ListUtils.list2Str(messageList.subList(0, maxSize)) + ",...";
        }
        return ListUtils.list2Str(messageList);
    }

    /**
     * 去重、合并并排序字符串中各子项并将合并项转换成逗号分隔字符串返回，
     * 如:A,B,C和B,C,D两个字符串合并后生成A,B,C,D字符串
     *
     * @param sItems 格式如:A,B,C
     * @param tItems 格式如:A,B,C
     * @return 格式如:A,B,C
     * @author jiangbin
     * @date Jun 27, 20192:30:03 PM
     */
    public static String mergerStr(String sItems, String tItems) {
        List<String> sList = ListUtils.str2List(sItems);
        List<String> tList = ListUtils.str2List(tItems);
        tList = mergerUniqueList(sList, tList);
        if (CollectionUtils.isNotEmpty(tList)) {
            Collections.sort(tList);
        }
        return list2Str(tList);
    }

    /**
     * 合并给定元素列表并去重和排序
     *
     * @param sources 元素列表，各元素支持A,B,C格式，本方法会自动解析元素成列表再合并，若未给定元素列表则返回null
     * @return
     * @author jiangbin
     * @date Jun 28, 201911:11:02 AM
     */
    public static List<String> mergerStr(List<String> sources) {
        if (CollectionUtils.isEmpty(sources)) {
            return null;
        }
        //合并所有关联归档号列表
        List<String> targets = new ArrayList<String>();
        for (String source : sources) {
            List<String> items = str2List(source);
            if (CollectionUtils.isNotEmpty(items)) {
                targets.addAll(items);
            }
        }

        //去重并排序
        if (CollectionUtils.isNotEmpty(targets)) {
            targets = unique(targets);
            Collections.sort(targets);
        }
        return targets;
    }

    /**
     * 移除字符串中的空格
     *
     * @param source 字符串
     * @return java.lang.String
     * @author jiangbin
     * @date 2019-08-17 20:06
     **/
    public static String removeSpace(String source) {
        source = StringUtils.stripToEmpty(source);
        if (source.isEmpty()) {
            return source;
        }
        return source.replaceAll(" ", "");
    }

    /**
     * 转换字符串一维数组为元素的集合为二维数组
     *
     * @param rows
     * @return java.lang.String[][]
     * @author jiangbin
     * @date 2019-09-07 14:52
     **/
    public static String[][] toArray(List<String[]> rows) {
        if (CollectionUtils.isEmpty(rows)) {
            return null;
        }
        String[][] target = new String[rows.size()][];
        for (int index = 0; index < rows.size(); index++) {
            target[index] = rows.get(index);
        }
        return target;
    }

    /**
     * 从列表中随机选择一个元素
     *
     * @param items
     * @return S
     * @author jiangbin
     * @date 2020/5/30 10:56
     **/
    public static <S> S randomSelect(List<S> items) {
        if (CollectionUtils.isEmpty(items)) {
            return null;
        }
        if (items.size() == 1) {
            return items.get(0);
        }
        Random random = new Random();
        int n = random.nextInt(items.size());
        return items.get(n);
    }
}
