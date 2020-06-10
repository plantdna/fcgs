package com.pids.core.checker;

import com.pids.core.exception.ICoreException;
import com.pids.core.iterator.ListIteratorTemplate;
import com.pids.core.task.manager.TaskContainer;
import com.pids.core.task.manager.TaskProcessor;

import java.util.List;

public abstract class ListCheckerTemplate<S, T> extends ListIteratorTemplate<S, T> implements ListChecker<S, T>, TaskProcessor<S, T> {
    @Override
    public List<T> check(List<S> sources) throws ICoreException {
        return this.iterator(sources);
    }

    @Override
    public List<T> check(List<S> sources, boolean fullMode) throws ICoreException {
        return this.iterator(sources, fullMode);
    }

    @Override
    public T call(S source) throws ICoreException {
        return this.check(source, true);
    }

    @Override
    public T check(S source) throws ICoreException {
        return this.check(source, false);
    }

    @Override
    public List<T> process(TaskContainer<S, T> container) throws ICoreException {
        return this.check(container.getSources());
    }

    protected abstract T check(S source, boolean isInternal) throws ICoreException;

}
