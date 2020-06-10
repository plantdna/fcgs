package com.pids.core.loader;

import com.pids.core.exception.ICoreException;
import com.pids.core.iterator.ListIteratorTemplate;
import com.pids.core.task.manager.TaskContainer;
import com.pids.core.task.manager.TaskProcessor;

import java.util.List;

/**
 * 列表数据加载器模板
 *
 * @Author andory
 * @Date 2012-8-9下午9:42:37
 */
public abstract class ListLoaderTemplate<S, T> extends ListIteratorTemplate<S, T> implements ListLoader<S, T>, TaskProcessor<S, T> {

    @Override
    public List<T> load(List<S> sources) throws ICoreException {
        return this.iterator(sources);
    }

    @Override
    public List<T> load(List<S> sources, boolean fullMode) throws ICoreException {
        return this.iterator(sources, fullMode);
    }

    @Override
    public T call(S source) throws ICoreException {
        return this.load(source, true);
    }

    @Override
    public T load(S source) throws ICoreException {
        return this.load(source, false);
    }

    @Override
    public List<T> process(TaskContainer<S, T> container) throws ICoreException {
        return this.load(container.getSources());
    }

    protected abstract T load(S source, boolean isInternal) throws ICoreException;
}
