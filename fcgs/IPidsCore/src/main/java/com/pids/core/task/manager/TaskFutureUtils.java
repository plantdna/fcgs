package com.pids.core.task.manager;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 任务Future对象的工具类
 *
 * @author jiang
 * @date 2019-09-07 23:46
 */

public class TaskFutureUtils {

    /**
     * 检测是否所有任务均被执行完成
     *
     * @param futures
     * @return boolean
     * @throws
     * @author jiang
     * @date 2019/9/7 23:47
     **/
    public static <T> boolean isAllTaskDone(List<Future<List<T>>> futures) {
        if (CollectionUtils.isEmpty(futures)) {
            return true;
        }
        boolean isDone = true;
        for (Future<List<T>> future : futures) {
            //检测到有任务线程未执行完成
            if (!future.isDone()) {
                isDone = false;
            }
        }
        return isDone;
    }

    /**
     * 检测是否所有任务均被执行完成
     *
     * @param container
     * @return boolean
     * @throws
     * @author jiang
     * @date 2019/9/7 23:47
     **/
    public static <S, T> boolean isAllTaskDone(TaskContainer<S, T> container) {
        return isAllTaskDone(container.getFutures());
    }

    /**
     * 获取所有执行完毕的任务结果数
     *
     * @param futures
     * @return int
     * @throws
     * @author jiang
     * @date 2019/9/7 23:49
     **/
    public static <T> int getTaskDoneResultCount(List<Future<List<T>>> futures) {
        if (CollectionUtils.isEmpty(futures)) {
            return 0;
        }
        int count = 0;
        for (Future<List<T>> future : futures) {
            //检测到有任务线程未执行完成
            if (future.isDone()) {
                try {
                    count += future.get().size();
                } catch (InterruptedException | ExecutionException e) {
                }
            }
        }
        return count;
    }

    /**
     * 获取所有执行完毕的任务结果数
     *
     * @param container
     * @return int
     * @throws
     * @author jiang
     * @date 2019/9/8 0:08
     **/
    public static <S, T> int getTaskDoneResultCount(TaskContainer<S, T> container) {
        return getTaskDoneResultCount(container.getFutures());
    }

    /**
     * 取消所有未执行以及执行中的任务线程
     *
     * @param futures
     * @return void
     * @throws
     * @author jiang
     * @date 2019/9/7 23:50
     **/
    public static <T> void cancelTasks(List<Future<List<T>>> futures) {
        if (CollectionUtils.isEmpty(futures)) {
            return;
        }
        for (Future<List<T>> future : futures) {
            if (!future.isDone()) {
                // Future.cancel(Boolean)方法业务逻辑如下：
                // 1、对于已完成或其它原因不能取消则返回false且不会取消任务
                // 2、对于未开始执行的线程则将被取消并返回true
                // 3、对于已开始执行的线程将根据方法参数来确定逻辑:
                // true  - 如果正在执行的任务将被取消
                // false - 如果正在执行的任务不会被取消
                future.cancel(true);
            }
        }
    }

    /**
     * 取消所有未执行以及执行中的任务线程
     *
     * @param container
     * @return void
     * @throws
     * @author jiang
     * @date 2019/9/7 23:50
     **/
    public static <S, T> void cancelTasks(TaskContainer<S, T> container) {
        cancelTasks(container.getFutures());
    }
}
