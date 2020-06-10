package com.pids.core.task.manager;

import com.pids.core.spliter.impl.SpliterUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * 任务加载指容器信息对象默认实现类
 *
 * @author jiang
 * @date 2019-08-30 5:10
 */
public class SimpleTaskContainer<S, T> implements TaskContainer<S, T> {
	private static final long serialVersionUID = 1L;
	private int taskIndex;//任务索引号
    private List<S> sources;//待处理数据列表
    private List<T> results;//处理结果列表
    private int poolSize = getDefaultPoolSize();//线程池大小
    private int shardingCount = 2000;//线程处理的数据量
    private CountDownLatch latch;//同步计数器
    private boolean sync = true;//同步或异步执行方式
    private List<TaskContainer<S, T>> childers;//子线程数据列表
    private ExecutorService service;//线程池
    private TaskProcessor<S, T> processor;//任务处理器
    private List<Future<List<T>>> futures;//任务Future列表

    @Override
    public void setTaskIndex(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public int getTaskIndex() {
        return taskIndex;
    }

    @Override
    public List<S> getSources() {
        return sources;
    }

    @Override
    public void setSources(List<S> sources) {
        this.sources = sources;
    }

    @Override
    public int getPoolSize() {
        return poolSize;
    }

    @Override
    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    @Override
    public CountDownLatch getLatch() {
        return latch;
    }

    @Override
    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public boolean isSync() {
        return sync;
    }

    @Override
    public void setSync(boolean sync) {
        this.sync = sync;
    }

    @Override
    public List<T> getResults() {
        return results;
    }

    @Override
    public void setResults(List<T> results) {
        this.results = results;
    }

    @Override
    public int getShardingCount() {
        return shardingCount;
    }

    @Override
    public void setShardingCount(int shardingCount) {
        this.shardingCount = shardingCount;
    }

    @Override
    public List<TaskContainer<S, T>> getChilders() {
        return childers;
    }

    @Override
    public void setChilders(List<TaskContainer<S, T>> childers) {
        this.childers = childers;
    }

    @Override
    public void setService(ExecutorService service) {
        this.service = service;
    }

    @Override
    public ExecutorService getService() {
        return service;
    }

    @Override
    public void setProcessor(TaskProcessor<S, T> processor) {
        this.processor = processor;
    }

    @Override
    public TaskProcessor<S, T> getProcessor() {
        return this.processor;
    }

    @Override
    public List<Future<List<T>>> getFutures() {
        return futures;
    }

    @Override
    public void setFutures(List<Future<List<T>>> futures) {
        this.futures = futures;
    }

    @Override
    public boolean isEmptySources() {
        return CollectionUtils.isEmpty(this.sources);
    }

    @Override
    public List<TaskContainer<S, T>> split() {
        if (CollectionUtils.isEmpty(this.sources)) {
            return null;
        }

        //数据分片
        List<List<S>> groups = SpliterUtils.split(this.sources, this.shardingCount);

        //分片结果
        this.childers = new ArrayList<>();
        for (List<S> group : groups) {
            TaskContainer<S, T> container = createTaskContainer(group);
            container.setSources(group);
            this.childers.add(container);
        }

        return this.childers;
    }

    /**
     * 用于任务分片时创建一个新任务容器对象
     *
     * @param group 新任务容器的分片数据内容
     * @return com.viathink.core.task.manager.TaskContainer<S, T>
     * @author jiangbin
     * @date 2019-09-11 16:21
     **/
    protected TaskContainer<S, T> createTaskContainer(List<S> group) {
        return new SimpleTaskContainer<>();
    }

    @Override
    public void addResult(T result) {
        if (this.results == null) {
            this.results = new ArrayList<>();
        }
        if (result != null) {
            this.results.add(result);
        }
    }

    @Override
    public void addResult(List<T> results) {
        if (this.results == null) {
            this.results = new ArrayList<>();
        }
        if (CollectionUtils.isNotEmpty(results)) {
            this.results.addAll(results);
        }
    }

    @Override
    public List<T> getChildResults() {
        if (CollectionUtils.isEmpty(this.childers)) {
            return null;
        }
        List<T> results = new ArrayList<>();
        for (TaskContainer<S, T> container : this.childers) {
            if (CollectionUtils.isNotEmpty(container.getResults())) {
                results.addAll(container.getResults());
            }
        }
        return results;
    }

    @Override
    public boolean isAllTaskDone() {
        return TaskFutureUtils.isAllTaskDone(this.futures);
    }

    /**
     * 初始化缓存池大小，默认值为50
     *
     * @return int
     * @author jiangbin
     * @date 2019-08-30 14:49
     **/
    protected int getDefaultPoolSize() {
        return 50;
    }

}
