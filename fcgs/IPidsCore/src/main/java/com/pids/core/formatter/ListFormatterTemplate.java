package com.pids.core.formatter;

import com.pids.core.exception.ICoreException;
import com.pids.core.iterator.ListIteratorTemplate;
import com.pids.core.task.manager.TaskContainer;
import com.pids.core.task.manager.TaskProcessor;

import java.util.List;

/**
 * 列表格式化模板实现
 *
 * @Author andory
 * @Date 2012-8-9下午1:06:06
 */
public abstract class ListFormatterTemplate<S, T> extends ListIteratorTemplate<S, T> implements ListFormatter<S, T>, TaskProcessor<S, T> {

    @Override
    public T call(S source) throws ICoreException {
        return this.format(source, true);
    }

    @Override
    public T format(S source) throws ICoreException {
        return this.format(source, false);
    }

    protected abstract T format(S source, boolean isInternal) throws ICoreException;

    @Override
    public List<T> format(List<S> sources) throws ICoreException {
        return this.iterator(sources);
    }

    @Override
    public List<T> format(List<S> sources, boolean fullMode) throws ICoreException {
        return this.iterator(sources, fullMode);
    }

    @Override
    public List<T> process(TaskContainer<S, T> container) throws ICoreException {
        return this.format(container.getSources());
    }
}
