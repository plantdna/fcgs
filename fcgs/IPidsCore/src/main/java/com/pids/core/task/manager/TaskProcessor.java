package com.pids.core.task.manager;

import com.pids.core.exception.ICoreException;

import java.util.List;

/**
 * 任务处理器业务接口，本方法只专注于执行业务处理逻辑代码并返回处理结果，用于剥离任务线程的业务处理逻辑
 *
 * @author jiangbin
 * @date 2019-09-03 14:59
 **/
public interface TaskProcessor<S, T> {
    /**
     * 完成业务处理
     *
     * @return T
     * @author jiangbin
     * @date 2019-08-31 16:40
     **/
    List<T> process(TaskContainer<S, T> container) throws ICoreException;
}
