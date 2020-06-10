package com.pids.core.builder;

import com.pids.core.exception.ICoreException;
import com.pids.core.iterator.ListIteratorTemplate;
import com.pids.core.task.manager.TaskContainer;
import com.pids.core.task.manager.TaskProcessor;

import java.util.List;

/**
 * 列表数据构建器
 *
 * @Author andory
 * @Date 2012-8-9下午2:32:01
 */
public abstract class ListBuilderTemplate<S, T> extends ListIteratorTemplate<S, T> implements ListBuilder<S, T>, TaskProcessor<S, T> {
    @Override
    public List<T> build(List<S> sources) throws ICoreException {
        return this.iterator(sources);
    }

    @Override
    public List<T> build(List<S> sources, boolean fullMode) throws ICoreException {
        return this.iterator(sources, fullMode);
    }

    @Override
    public T call(S source) throws ICoreException {
        return this.build(source, true);
    }

    @Override
    public T build(S source) throws ICoreException {
        return this.build(source, false);
    }

    @Override
    public List<T> process(TaskContainer<S, T> container) throws ICoreException {
        return this.build(container.getSources());
    }

    protected abstract T build(S source, boolean isInternal) throws ICoreException;
}
