package com.pids.core.task.manager;

import java.util.List;

/**
 * 任务管理器工具类，封装了针对同步和异步处理数据的方法，以简化任务管理器的使用
 *
 * @author jiangbin
 * @date 2019-09-03 15:32
 **/
public class TaskManagerUtils {

    /**
     * 创建任务管理器并以同步多任务方式执行
     *
     * @param container
     * @return java.util.List<T> 只在同步状态下返回结果是有效的，异步状态返回的结果将不完整
     * @author jiangbin
     * @date 2019-09-03 15:44
     **/
    public static <S, T> List<T> sync(TaskContainer<S, T> container) {
        container.setSync(true);
        new SimpleTaskManager<S, T>().manage(container);
        return container.getChildResults();
    }

    /**
     * 创建任务管理器并以异步多任务方式执行
     *
     * @param container
     * @return void
     * @author jiangbin
     * @date 2019-09-03 15:44
     **/
    public static <S, T> void async(TaskContainer<S, T> container) {
        container.setSync(false);
        new SimpleTaskManager<S, T>().manage(container);
    }

    /**
     * 创建任务管理器并以同步方式多任务方式执行
     *
     * @param sources   待处理数据列表
     * @param processor 业务处理器
     * @return java.util.List<T> 只在同步状态下返回结果是有效的，异步状态返回的结果将不完整
     * @author jiangbin
     * @date 2019-09-03 15:40
     **/
    public static <S, T> List<T> sync(List<S> sources, TaskProcessor<S, T> processor) {
        return new SimpleTaskManager<S, T>().manage(sources, processor, true);
    }

    /**
     * 创建任务管理器并以异步方式多任务方式执行
     *
     * @param sources   待处理数据列表
     * @param processor 业务处理器
     * @return java.util.List<T> 只在同步状态下返回结果是有效的，异步状态返回的结果将不完整
     * @author jiangbin
     * @date 2019-09-03 15:40
     **/
    public static <S, T> void async(List<S> sources, TaskProcessor<S, T> processor) {
        new SimpleTaskManager<S, T>().manage(sources, processor, false);
    }
}
