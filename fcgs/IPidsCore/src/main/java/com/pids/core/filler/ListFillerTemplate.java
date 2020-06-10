package com.pids.core.filler;

import com.pids.core.exception.ICoreException;
import com.pids.core.iterator.ListIteratorTemplate;
import com.pids.core.task.manager.TaskContainer;
import com.pids.core.task.manager.TaskProcessor;

import java.util.List;

/**
 * 列表填充器
 *
 * @author Andory
 * @date 2012-8-7下午8:16:25
 */
public abstract class ListFillerTemplate<S, T> extends ListIteratorTemplate<S, T> implements ListFiller<S, T>, TaskProcessor<S, T> {

    @Override
    public List<T> fill(List<S> sources) throws ICoreException {
        return this.iterator(sources);
    }

    @Override
    public List<T> fill(List<S> sources, boolean fullMode) throws ICoreException {
        return this.iterator(sources, fullMode);
    }

    @Override
    public T call(S source) throws ICoreException {
        return this.fill(source, true);
    }

    @Override
    public T fill(S source) throws ICoreException {
        return this.fill(source, false);
    }

    @Override
    public List<T> process(TaskContainer<S, T> container) throws ICoreException {
        return this.fill(container.getSources());
    }

    protected abstract T fill(S source, boolean isInternal) throws ICoreException;
}
