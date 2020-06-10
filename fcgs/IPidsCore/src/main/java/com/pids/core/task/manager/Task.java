package com.pids.core.task.manager;

import com.pids.core.exception.ExceptionUtils;
import com.pids.core.exception.ICoreException;
import com.pids.core.utils.ListUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * 任务线程类
 *
 * @author jiang
 * @date 2019-08-30 4:15
 */

public class Task<S, T> implements Callable<List<T>>, TaskProcessor<S, T> {
	protected Logger log=LoggerFactory.getLogger(this.getClass());
    protected TaskContainer<S, T> container;

    /**
     * 任务构造函数
     *
     * @author jiang
     * @date 2019/9/1 13:48
     **/
    public Task(TaskContainer<S, T> container) {
        this.container = container;
    }

    @Override
    public List<T> call() throws Exception {
        StopWatch watch = new StopWatch();
        watch.start();
        try {
            log.info(getThreadName() + "=>" + this.getClassName() + "=>开始执行线程业务处理" + getDataCount() + "个数据.....");
            List<T> results = this.process(this.container);
            container.addResult(results);
            return results;
        } catch (Exception e) {
            log.error(getThreadName() + "执行线程出错==>" + ExceptionUtils.getStackTrace(e));
            throw e;
        } finally {
            watch.stop();
            log.info(getThreadName() + "=>" + this.getClassName() + "=>执行线程业务耗时(ms)=>" + watch.getTime());
            if (this.container.getLatch() != null) {
                this.container.getLatch().countDown();
            }
        }
    }

    /**
     * 获取待处理数据记录数
     *
     * @return int
     * @author jiangbin
     * @date 2019-09-23 11:20
     **/
    private int getDataCount() {
        return container == null ? 0 : ListUtils.size(container.getSources());
    }

    /**
     * 获取线程名称
     *
     * @return java.lang.String
     * @author jiangbin
     * @date 2019-09-18 11:35
     **/
    private String getThreadName() {
        return Thread.currentThread().getName();
    }

    /**
     * 获取待处理元素的类类型名
     *
     * @return java.lang.String
     * @throws
     * @author jiang
     * @date 2019/9/7 21:51
     **/
    private String getClassName() {
        S source = ListUtils.getFirst(container.getSources());
        return source == null ? "" : source.getClass().getName();
    }

    /**
     * 获取任务容器信息
     *
     * @return com.viathink.ssr.gene.loader.GeneFileContainer<S>
     * @throws
     * @author jiang
     * @date 2019/8/30 11:00
     **/
    public TaskContainer<S, T> getContainer() {
        return container;
    }

    /**
     * 设置任务容器信息对象
     *
     * @param container
     * @return void
     * @throws
     * @author jiang
     * @date 2019/9/7 21:56
     **/
    public void setContainer(TaskContainer<S, T> container) {
        this.container = container;
    }

    @Override
    public List<T> process(TaskContainer<S, T> container) throws ICoreException {
        return container.getProcessor().process(container);
    }
}
