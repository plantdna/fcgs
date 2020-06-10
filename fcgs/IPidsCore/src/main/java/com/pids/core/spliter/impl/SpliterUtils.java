package com.pids.core.spliter.impl;

import com.pids.core.spliter.SimpleListSpliter;
import com.pids.core.utils.ListUtils;

import java.util.List;

/**
 * 分割器工具类
 *
 * @author jiangbin
 * @date 2014年7月13日上午1:23:46
 */
public class SpliterUtils {
    /**
     * 按指定步进值分割列表数据
     *
     * @param sources
     * @param step
     * @return
     * @author jiangbin
     * @date 2014年7月13日上午1:23:56
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <S> List<List<S>> split(List<S> sources, int step) {
        return new SimpleListSpliter().split(sources, step);
    }

    /**
     * 将给定数据列表分成给定分组数
     *
     * @param sources    源数据列表
     * @param groupCount 分组数
     * @return java.util.List<java.util.List < S>>
     * @author jiangbin
     * @date 2019/11/26 5:53 下午
     **/
    public static <S> List<List<S>> group(List<S> sources, int groupCount) {
        int step = ListUtils.size(sources) / groupCount;
        return new SimpleListSpliter().split(sources, step);
    }
}
