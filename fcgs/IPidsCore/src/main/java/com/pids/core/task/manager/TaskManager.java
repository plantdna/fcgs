package com.pids.core.task.manager;

import com.pids.core.exception.ICoreException;

import java.util.List;

/**
 * 任务管理器接口，本功能支持同步或异步执行多线程处理业务，
 * 如果为同步状态则程序会等待各线程均执行完毕才返回，
 * 异步状态则会在开启线程执行后就返回，异步时的
 *
 * @author jiang
 * @date 2019-09-01 13:44
 */
public interface TaskManager<S, T> {
    /**
     * 多线程处理数据
     *
     * @param source
     * @return com.viathink.core.task.manager.TaskContainer
     * @author jiangbin
     * @date 2019-08-31 16:41
     **/
    void manage(final TaskContainer<S, T> source) throws ICoreException;

    /**
     * 多线程处理数据，本对象会自动创建一个{@link SimpleTaskContainer}类型任务容器对象来执行多线程任务处理
     *
     * @param sources   源数据列表
     * @param processor 业务处理器
     * @param isSync    ture/false--同步执行多线程/异步执行多线程
     * @return java.util.List<T>
     * @author jiangbin
     * @date 2019-09-03 14:05
     **/
    List<T> manage(final List<S> sources, TaskProcessor<S, T> processor, boolean isSync) throws ICoreException;

}
