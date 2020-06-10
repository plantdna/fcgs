package com.fcgs.base.sorter;

import com.pids.core.utils.RegexUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用样品条码号比较器，适用于由用户自定义的样品条码号，如:BGG1,BGG1-2,BGG2等
 *
 * @author jiang
 * @date 2019/9/6 23:01
 **/
public class BarcodeComparer implements Comparator<String> {
    //缓存及锁
    private static Map<String, Integer> cache = new HashMap<>();
    private static ReentrantLock lock = new ReentrantLock(true);

    //正则式
    private static Pattern PATTERN = RegexUtils.pattern("^([a-zA-Z]+)([0-9]+)$");
    private static Pattern PATTERN2 = RegexUtils.pattern("^([a-zA-Z]+)([0-9]+)-([0-9]+)$");

    @Override
    public int compare(String sSamBarcode, String tSamBarcode) {
        //读取缓存
        Integer result = getCacheValue(sSamBarcode, tSamBarcode);
        if (result != null) {
            return setCacheValue(sSamBarcode, tSamBarcode, result);
        }

        if (StringUtils.isBlank(sSamBarcode) && StringUtils.isBlank(tSamBarcode)) {
            return setCacheValue(sSamBarcode, tSamBarcode, 0);
        }
        if (StringUtils.isBlank(sSamBarcode)) {
            return setCacheValue(sSamBarcode, tSamBarcode, 1);
        }
        if (StringUtils.isBlank(tSamBarcode)) {
            return setCacheValue(sSamBarcode, tSamBarcode, -1);
        }
        if (sSamBarcode.equalsIgnoreCase(tSamBarcode)) {
            return setCacheValue(sSamBarcode, tSamBarcode, 0);
        }

        //解析源样品条码号
        List<String> sParts = parser(sSamBarcode);
        List<String> tParts = parser(tSamBarcode);

        //不合格的条码号直接字符串比较
        if (CollectionUtils.isEmpty(sParts) && CollectionUtils.isEmpty(tParts)) {
            result = sSamBarcode.compareToIgnoreCase(tSamBarcode);
            return setCacheValue(sSamBarcode, tSamBarcode, result);
        }
        if (CollectionUtils.isEmpty(sParts)) {
            return setCacheValue(sSamBarcode, tSamBarcode, 1);
        }
        if (CollectionUtils.isEmpty(tParts)) {
            return setCacheValue(sSamBarcode, tSamBarcode, -1);
        }

        //前缀字母
        String sPart = StringUtils.stripToEmpty(sParts.get(0));
        String tPart = StringUtils.stripToEmpty(tParts.get(0));
        result = sPart.compareToIgnoreCase(tPart);
        if (result != 0) {
            return setCacheValue(sSamBarcode, tSamBarcode, result);
        }

        //数字部分
        int sPartNum = NumberUtils.toInt(sParts.get(1));
        int tPartNum = NumberUtils.toInt(tParts.get(1));
        result = sPartNum - tPartNum;
        if (result != 0) {
            return setCacheValue(sSamBarcode, tSamBarcode, result);
        }

        //后缀数字
        if (sParts.size() == 3) {
            sPart = sParts.get(2);
        } else {
            sPart = null;
        }
        if (tParts.size() == 3) {
            tPart = tParts.get(2);
        } else {
            tPart = null;
        }
        sPartNum = NumberUtils.toInt(sPart, 0);
        tPartNum = NumberUtils.toInt(tPart, 0);
        return setCacheValue(sSamBarcode, tSamBarcode, sPartNum - tPartNum);
    }

    /**
     * 解析样品条码号各部分内容
     *
     * @param samBarcode
     * @return java.util.List<java.lang.String>
     * @author jiangbin
     * @date 2019-09-06 13:39
     **/
    private List<String> parser(String samBarcode) {
        if (StringUtils.isBlank(samBarcode)) {
            return null;
        }
        Matcher matcher = null;
        if (samBarcode.indexOf("-") == -1) {
            matcher = PATTERN.matcher(samBarcode);
        } else {
            matcher = PATTERN2.matcher(samBarcode);
        }
        return RegexUtils.getValues(matcher);
    }

    /**
     * 获取缓存数据
     *
     * @param sSamBarcode
     * @param tSamBarcode
     * @return java.lang.Integer
     * @author jiangbin
     * @date 2019-09-07 09:51
     **/
    private Integer getCacheValue(String sSamBarcode, String tSamBarcode) {
        try {
            lock.lock();
            return cache.get(getCacheKey(sSamBarcode, tSamBarcode));
        } finally {
            lock.unlock();
        }
    }

    /**
     * 设置缓存数据
     *
     * @param sSamBarcode
     * @param tSamBarcode
     * @param value
     * @return int
     * @author jiangbin
     * @date 2019-09-07 09:51
     **/
    private int setCacheValue(String sSamBarcode, String tSamBarcode, int value) {
        try {
            lock.lock();
            cache.put(getCacheKey(sSamBarcode, tSamBarcode), value);
            cache.put(getCacheKey(tSamBarcode, sSamBarcode), -value);
            return value;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 获取缓存数据Key
     *
     * @return java.lang.String
     * @throws
     * @author jiang
     * @date 2019/9/6 22:42
     **/
    private String getCacheKey(String sSamBarcode, String tSamBarcode) {
        return String.format("%s-%s", sSamBarcode, tSamBarcode);
    }
}
