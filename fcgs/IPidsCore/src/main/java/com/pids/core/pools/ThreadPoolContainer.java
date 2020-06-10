package com.pids.core.pools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池容器对象
 *
 * @author jiangbin
 * @date 2019-08-30 15:48
 **/
public class ThreadPoolContainer {

    /**
     * 获取线程池对象
     *
     * @param poolSize
     * @return java.util.concurrent.ExecutorService
     * @author jiangbin
     * @date 2019-08-30 15:50
     **/
    public static ExecutorService create(int poolSize) {
        return Executors.newFixedThreadPool(poolSize);
    }

}
