package com.pids.core.allocator;

import com.pids.core.exception.ICoreException;
import com.pids.core.iterator.ListIteratorTemplate;
import com.pids.core.task.manager.TaskContainer;
import com.pids.core.task.manager.TaskProcessor;

import java.util.List;

/**
 * 列表分配功能模板类
 *
 * @author jiangbin
 * @date 2012-11-9下午9:55:46
 */
public abstract class ListAllocatorTemplate<S, T> extends ListIteratorTemplate<S, T> implements ListAllocator<S, T>, TaskProcessor<S, T> {

    @Override
    public List<T> allocate(List<S> sources) throws ICoreException {
        return this.iterator(sources);
    }

    @Override
    public List<T> allocate(List<S> sources, boolean fullMode) throws ICoreException {
        return this.iterator(sources, fullMode);
    }

    @Override
    public T call(S source) throws ICoreException {
        return allocate(source, true);
    }

    @Override
    public T allocate(S source) throws ICoreException {
        return this.allocate(source, false);
    }

    @Override
    public List<T> process(TaskContainer<S, T> container) throws ICoreException {
        return this.allocate(container.getSources());
    }

    protected abstract T allocate(S source, boolean isInternal) throws ICoreException;
}
