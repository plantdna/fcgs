package com.pids.core.task.manager;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * 任务信息容器接口
 *
 * @author jiangbin
 * @date 2019-09-03 14:20
 **/
public interface TaskContainer<S, T> extends Serializable {
    /**
     * 设置任务索引号
     *
     * @param taskIndex
     * @return void
     * @author jiangbin
     * @date 2019-09-12 16:08
     **/
    void setTaskIndex(int taskIndex);

    /**
     * 获取任务索引号
     *
     * @return int
     * @author jiangbin
     * @date 2019-09-12 16:08
     **/
    int getTaskIndex();

    /**
     * 获取待处理记录列表
     *
     * @return java.util.List<S>
     * @author jiangbin
     * @date 2019-08-30 13:39
     **/
    List<S> getSources();

    /**
     * 设置待处理记录列表
     *
     * @param sources
     * @return void
     * @author jiangbin
     * @date 2019-08-30 13:39
     **/
    void setSources(List<S> sources);

    /**
     * 获取线程池大小
     *
     * @return int
     * @author jiangbin
     * @date 2019-08-30 13:40
     **/
    int getPoolSize();

    /**
     * 设置线程池大小
     *
     * @param poolSize
     * @return void
     * @author jiangbin
     * @date 2019-08-30 13:40
     **/
    void setPoolSize(int poolSize);

    /**
     * 获取同步计数器
     *
     * @return java.util.concurrent.CountDownLatch
     * @author jiangbin
     * @date 2019-08-30 13:41
     **/
    CountDownLatch getLatch();

    /**
     * 设置同步计数器
     *
     * @param latch
     * @return void
     * @author jiangbin
     * @date 2019-08-30 13:41
     **/
    void setLatch(CountDownLatch latch);

    /**
     * 是否以同步方式执行指纹加载操作
     *
     * @return boolean true/false--同步/异步
     * @throws
     * @author jiang
     * @date 2019/8/30 11:25
     **/
    boolean isSync();

    /**
     * 设置是否以同步方式执行指纹加载操作
     *
     * @param sync true/false--同步/异步
     * @return void
     * @throws
     * @author jiang
     * @date 2019/8/30 11:27
     **/
    void setSync(boolean sync);

    /**
     * 获取处理结果列表
     *
     * @return java.util.List<T>
     * @author jiangbin
     * @date 2019-08-31 16:36
     **/
    List<T> getResults();

    /**
     * 设置处理结果列表
     *
     * @param results
     * @return void
     * @author jiangbin
     * @date 2019-08-31 16:36
     **/
    void setResults(List<T> results);

    /**
     * 获取线程处理数据量
     *
     * @return int
     * @author jiangbin
     * @date 2019-08-31 16:36
     **/
    int getShardingCount();

    /**
     * 设置线程处理数据量
     *
     * @param shardingCount
     * @return void
     * @author jiangbin
     * @date 2019-08-31 16:36
     **/
    void setShardingCount(int shardingCount);

    /**
     * 获取子任务列表
     *
     * @return java.util.List<com.viathink.core.task.manager.TaskContainer < S, T>>
     * @author jiangbin
     * @date 2019-08-31 16:37
     **/
    List<TaskContainer<S, T>> getChilders();

    /**
     * 设置子任务列表
     *
     * @param childers
     * @return void
     * @author jiangbin
     * @date 2019-08-31 16:37
     **/
    void setChilders(List<TaskContainer<S, T>> childers);

    /**
     * 设置线程池服务对象
     *
     * @param service
     * @return void
     * @author jiangbin
     * @date 2019-08-31 17:47
     **/
    void setService(ExecutorService service);

    /**
     * 获取线程池服务对象
     *
     * @return java.util.concurrent.ExecutorService
     * @author jiangbin
     * @date 2019-08-31 17:48
     **/
    ExecutorService getService();

    /**
     * 是否无待处理数据
     *
     * @return boolean
     * @throws
     * @author jiang
     * @date 2019/8/30 10:48
     **/
    boolean isEmptySources();

    /**
     * 将源数据列表分割后构建成任务容器信息列表,每个子任务将对应一个线程进行处理
     *
     * @return java.util.List<com.viathink.core.task.manager.TaskContainer < S, T>>
     * @author jiangbin
     * @date 2019-08-31 17:29
     **/
    List<TaskContainer<S, T>> split();

    /**
     * 添加处理结果
     *
     * @param result 处理结果
     * @return void
     * @author jiangbin
     * @date 2019-08-31 17:28
     **/
    void addResult(T result);

    /**
     * 添加处理结果列表
     *
     * @param results 处理结果列表
     * @return void
     * @author jiangbin
     * @date 2019-08-31 17:28
     **/
    void addResult(List<T> results);

    /**
     * 获取给定指纹文件容器列表中包含的所有指纹
     *
     * @return java.util.List<T>
     * @author jiangbin
     * @date 2019-08-31 17:29
     **/
    List<T> getChildResults();

    /**
     * 获取任务处理器
     *
     * @return com.viathink.core.task.manager.TaskProcessor<S, T>
     * @author jiangbin
     * @date 2019-09-03 15:00
     **/
    TaskProcessor<S, T> getProcessor();

    /**
     * 设置任务处理器
     *
     * @param processor
     * @return void
     * @author jiangbin
     * @date 2019-09-03 15:01
     **/
    void setProcessor(TaskProcessor<S, T> processor);

    /**
     * 获取异步执行结果的Future对象
     *
     * @return java.util.List<java.util.concurrent.Future < List < T>>>
     * @throws
     * @author jiang
     * @date 2019/9/3 22:10
     **/
    List<Future<List<T>>> getFutures();

    /**
     * 设置异步执行结果的Future对象
     *
     * @return void
     * @throws
     * @author jiang
     * @date 2019/9/3 22:10
     **/
    void setFutures(List<Future<List<T>>> futures);

    /**
     * 所有任务线程是否执行完毕，若未设置Future对象列表则返回true
     *
     * @return boolean
     * @author jiangbin
     * @date 2019-09-04 23:00
     **/
    boolean isAllTaskDone();
}
