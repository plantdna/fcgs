package com.pids.core.threadlocal;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ThreadLocal注册器，可用于集中清除线程数据，防止数据泄漏
 *
 * @author jiangbin
 * @date 2019-08-21 10:38
 **/
public class ThreadLocalRegister {
    private final static List<ThreadLocal<?>> threadLocals = new ArrayList<>();

    /**
     * 获取本地化线程列表
     *
     * @return java.util.List<java.lang.ThreadLocal < ?>>
     * @author jiangbin
     * @date 2019-08-21 10:44
     **/
    public static List<ThreadLocal<?>> get() {
        return ThreadLocalRegister.threadLocals;
    }

    /**
     * 注册本地化线程
     *
     * @param threadLocal
     * @return void
     * @author jiangbin
     * @date 2019-08-21 10:44
     **/
    public static void regist(ThreadLocal<?> threadLocal) {
        if (threadLocal != null) {
            ThreadLocalRegister.threadLocals.add(threadLocal);
        }
    }

    /**
     * 注册本地化线程列表
     *
     * @param threadLocals
     * @return void
     * @author jiangbin
     * @date 2019-08-21 10:45
     **/
    public static void regist(List<ThreadLocal<?>> threadLocals) {
        if (CollectionUtils.isNotEmpty(threadLocals)) {
            ThreadLocalRegister.threadLocals.addAll(threadLocals);
        }
    }

    /**
     * 自动创建一个本地化线程并注册后返回该本地化线程对象
     *
     * @return java.lang.ThreadLocal<T>
     * @author jiangbin
     * @date 2019-08-21 10:50
     **/
    public static <T> ThreadLocal<T> regist() {
        ThreadLocal<T> threadLocal = new InheritableThreadLocal<>();
        ThreadLocalRegister.threadLocals.add(threadLocal);
        return threadLocal;
    }

    /**
     * 清除本地化线程中线程数据
     *
     * @return void
     * @author jiangbin
     * @date 2019-08-21 10:45
     **/
    public static void removeDatas() {
        if (CollectionUtils.isEmpty(ThreadLocalRegister.threadLocals)) {
            return;
        }
        for (ThreadLocal<?> threadLocal : ThreadLocalRegister.threadLocals) {
            if (threadLocal != null) {
                threadLocal.remove();
            }
        }
    }
}
