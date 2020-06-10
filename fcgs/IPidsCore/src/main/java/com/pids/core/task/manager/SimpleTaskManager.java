package com.pids.core.task.manager;

import com.pids.core.exception.ExceptionUtils;
import com.pids.core.exception.ICoreException;
import com.pids.core.pools.ThreadPoolContainer;
import com.pids.core.utils.ListUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * 多线程任务处理管理器实现类
 *
 * @author jiang
 * @date 2019-08-30 8:52
 */
public class SimpleTaskManager<S, T> implements TaskManager<S, T> {
    protected Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public List<T> manage(List<S> sources, TaskProcessor<S, T> processor, boolean isSync) throws ICoreException {
        if (CollectionUtils.isEmpty(sources)) {
            return null;
        }
        TaskContainer<S, T> container = new SimpleTaskContainer<>();
        container.setSync(isSync);
        container.setSources(sources);
        container.setProcessor(processor);
        this.manage(container);
        return container.getChildResults();
    }

    @Override
    public void manage(final TaskContainer<S, T> source) throws ICoreException {
        if (source == null || source.isEmptySources()) {
            return;
        }

        log.info(getClass().getName() + "=>开始通过多线程处理" + ListUtils.size(source.getSources()) + "个数据!");

        StopWatch watch = new StopWatch();
        watch.start();

        try {
            //数据分片
            List<TaskContainer<S, T>> containers = source.split();
            if (CollectionUtils.isEmpty(containers)) {
                return;
            }

            //设置分片数据的详细信息
            fillDetail(source, containers);

            //创建线程对象
            List<Task<S, T>> threads = createTasks(containers);
            if (CollectionUtils.isEmpty(threads)) {
                return;
            }

            //多线程处理数据
            process(source, threads);
        } finally {
            watch.stop();
            log.info(getClass().getName() + "=>成功通过多线程处理" + ListUtils.size(source.getSources()) + "个数据共耗时(ms)==>" + watch.getTime());
        }

    }

    /**
     * 创建分片后任务容器对象的详细信息，如处理器对象、线程池信息、同步计数器等
     *
     * @param containers
     * @return void
     * @author jiangbin
     * @date 2019-09-11 16:11
     **/
    protected void fillDetail(TaskContainer<S, T> source, List<TaskContainer<S, T>> containers) {
        //回填分片数据
        source.setChilders(containers);

        //同步计数器
        CountDownLatch latch = null;
        if (source.isSync()) {
            latch = new CountDownLatch(containers.size());
        }

        //设置同步计数器
        source.setLatch(latch);

        //校正线程池大小
        if (containers.size() < source.getPoolSize()) {
            source.setPoolSize(containers.size());
        }

        //填充各关键字段
        for (int index = 0; index < containers.size(); index++) {
            TaskContainer<S, T> container = containers.get(index);
            container.setTaskIndex(index);
            container.setLatch(latch);
            container.setProcessor(source.getProcessor());
            container.setSync(source.isSync());
            container.setPoolSize(source.getPoolSize());
            container.setShardingCount(source.getShardingCount());
        }
    }

    /**
     * 创建线程列表
     *
     * @param containers
     * @return java.util.List<com.viathink.core.task.manager.Task < S, T>>
     * @author jiangbin
     * @date 2019-09-04 23:20
     **/
    protected List<Task<S, T>> createTasks(List<TaskContainer<S, T>> containers) {
        List<Task<S, T>> threads = new ArrayList<>();
        for (TaskContainer<S, T> container : containers) {
            threads.add(createTask(container));
        }

        log.info(getClass().getName() + "==>共创建" + threads.size() + "个线程执行业务功能!");

        return threads;
    }

    /**
     * 多线程处理数据
     *
     * @return void
     * @throws
     * @author jiang
     * @date 2019/8/30 12:02
     **/
    protected void process(final TaskContainer<S, T> source, final List<Task<S, T>> threads) {
        ExecutorService service = null;
        try {
            log.info(getClass().getName() + "=>开始通过线程池方式处理数据!");

            //线程池方式执行数据处理
            service = createThreadPool(source);
            final List<Future<List<T>>> futures = service.invokeAll(threads);
            source.setFutures(futures);

            //关闭线程池
            if (source.isSync()) {
                sync(source);
            } else {
                async(source);
            }
        } catch (InterruptedException e) {
            log.error(getClass().getName() + "=>线程池方式执行数据处理失败==>" + ExceptionUtils.getStackTrace(e));
            if (service != null) {
                service.shutdown();
            }
        }
    }

    /**
     * 创建线程池对象，用于执行多线程业务
     *
     * @param source
     * @return java.util.concurrent.ExecutorService
     * @author jiangbin
     * @date 2019-08-31 16:49
     **/
    protected ExecutorService createThreadPool(TaskContainer<S, T> source) {
        ExecutorService service = ThreadPoolContainer.create(source.getPoolSize());
        source.setService(service);
        return service;
    }

    /**
     * 异步方式处理结果数据
     *
     * @param source
     * @return void
     * @author jiangbin
     * @date 2019-09-04 23:19
     **/
    protected void async(final TaskContainer<S, T> source) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                finish(source);
            }
        }).start();
    }

    /**
     * 同步方式处理结果数据，本方法会等待所有线程执行完毕后再执行一些其它操作
     *
     * @param source
     * @return void
     * @author jiangbin
     * @date 2019-09-04 23:19
     **/
    protected void sync(TaskContainer<S, T> source) {
        try {
            source.getLatch().await();
            log.info(getClass().getName() + "=>同步执行多线程业务成功!");
        } catch (InterruptedException e) {
            log.error(getClass().getName() + "=>等待同步执行多线程业务失败==>" + ExceptionUtils.getStackTrace(e));
        }

        //关闭线程池
        if (source.getService() != null) {
            source.getService().shutdown();
        }
    }

    /**
     * 检测线程执行是否完毕
     *
     * @param source
     * @return void
     * @author jiangbin
     * @date 2019-09-04 23:19
     **/
    protected void finish(TaskContainer<S, T> source) {
        //轮循是否执行完成
        isTaskDone(source);

        //关闭线程池
        if (source.getService() != null) {
            source.getService().shutdown();
        }
    }

    /**
     * 检测各个线程是否均执行结束了
     *
     * @param source
     * @return void
     * @author jiangbin
     * @date 2019-08-30 15:33
     **/
    protected void isTaskDone(TaskContainer<S, T> source) {
        log.info(getClass().getName() + "=>开始检测线程池执行各线程是否执行完毕!");

        //轮询
        while (!source.isAllTaskDone()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }

        log.info(getClass().getName() + "=>线程池执行业务操作成功!");
    }

    /**
     * 创建业务处理任务线程对象
     *
     * @param container
     * @return
     */
    protected Task<S, T> createTask(TaskContainer<S, T> container) {
        return new Task<S, T>(container);
    }

}
