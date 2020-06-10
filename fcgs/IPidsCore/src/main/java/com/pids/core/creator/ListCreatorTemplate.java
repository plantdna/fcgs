package com.pids.core.creator;

import com.pids.core.exception.ICoreException;
import com.pids.core.iterator.ListIteratorTemplate;
import com.pids.core.task.manager.TaskContainer;
import com.pids.core.task.manager.TaskProcessor;

import java.util.List;

/**
 * 列表构建器的模板实现
 *
 * @Author andory
 * @Date 2012-8-9下午1:29:55
 */
public abstract class ListCreatorTemplate<S, T> extends ListIteratorTemplate<S, T> implements ListCreator<S, T>, TaskProcessor<S, T> {

    @Override
    public List<T> create(List<S> sources) throws ICoreException {
        return this.iterator(sources);
    }

    @Override
    public List<T> create(List<S> sources, boolean fullMode) throws ICoreException {
        return this.iterator(sources, fullMode);
    }

    @Override
    public T call(S source) throws ICoreException {
        return this.create(source, true);
    }

    @Override
    public T create(S source) throws ICoreException {
        return this.create(source, false);
    }

    @Override
    public List<T> process(TaskContainer<S, T> container) throws ICoreException {
        return this.create(container.getSources());
    }

    protected abstract T create(S source, boolean isInternal) throws ICoreException;
}
