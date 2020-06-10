package com.fcgs.core.comparer.domain;

import com.pids.core.threadlocal.ThreadLocalRegister;

/**
 * 统计方式的比对算法上下文对象，定义了几个主要参数，用于可以在比对前设置该上下文对象中的参数来优化比对效率
 *
 * @author jiangbin
 * @date 2019/11/27 10:45 上午
 **/

public class StatisticsContext {
    //单个线程处理的数据量
    private static ThreadLocal<Integer> group_size_tl = ThreadLocalRegister.regist();
    private static ThreadLocal<Integer> batch_group_size_tl = ThreadLocalRegister.regist();
    private static ThreadLocal<Boolean> is_ssr_tl = ThreadLocalRegister.regist();
    private static ThreadLocal<Integer> pool_size_tl = ThreadLocalRegister.regist();

    /**
     * 设置是否采用SSR模式
     *
     * @param ssr
     * @return void
     * @author jiangbin
     * @date 2019/11/29 2:04 下午
     **/
    public static void setIsSsr(boolean ssr) {
        is_ssr_tl.set(ssr);
    }

    /**
     * 是否采用SSR模式，默认为SSR模式
     *
     * @return boolean
     * @author jiangbin
     * @date 2019/11/29 2:04 下午
     **/
    public static boolean isSsr() {
        Boolean ssr = is_ssr_tl.get();
        if (ssr == null) {
            is_ssr_tl.set(true);
        }
        return is_ssr_tl.get();
    }

    /**
     * 设置单个线程处理的数据量
     *
     * @param groupSize
     * @return void
     * @author jiangbin
     * @date 2019/11/29 2:04 下午
     **/
    public static void setGroupSize(int groupSize) {
        group_size_tl.set(groupSize);
    }

    /**
     * 获取单个线程处理的数据量,默认为500个数据
     *
     * @return int
     * @author jiangbin
     * @date 2019/11/29 2:04 下午
     **/
    public static int getGroupSize() {
        Integer groupSize = group_size_tl.get();
        if (groupSize == null) {
            group_size_tl.set(500);
        }
        return group_size_tl.get();
    }

    /**
     * 设置大批量比对时的分组大小
     *
     * @param batchGroupSize
     * @return void
     * @author jiangbin
     * @date 2019/11/29 2:19 下午
     **/
    public static void setBatchGroupSize(int batchGroupSize) {
        batch_group_size_tl.set(batchGroupSize);
    }

    /**
     * 获取大批量比对时的分组大小，默认一次处理5000个指纹数据对
     *
     * @return int
     * @author jiangbin
     * @date 2019/11/29 2:19 下午
     **/
    public static int getBatchGroupSize() {
        Integer groupSize = batch_group_size_tl.get();
        if (groupSize == null) {
            batch_group_size_tl.set(5000);
        }
        return batch_group_size_tl.get();
    }

    /**
     * 设置线程池大小
     *
     * @param poolSize
     * @return void
     * @author jiangbin
     * @date 2019/11/29 2:40 下午
     **/
    public static void setPoolSize(int poolSize) {
        pool_size_tl.set(poolSize);
    }

    /**
     * 获取线程池大小,默认为15个线程大小
     *
     * @return int
     * @author jiangbin
     * @date 2019/11/29 2:41 下午
     **/
    public static int getPoolSize() {
        Integer poolSize = pool_size_tl.get();
        if (poolSize == null) {
            pool_size_tl.set(15);
        }
        return pool_size_tl.get();
    }
}
