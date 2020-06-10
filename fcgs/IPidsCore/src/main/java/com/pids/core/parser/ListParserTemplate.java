package com.pids.core.parser;

import com.pids.core.exception.ICoreException;
import com.pids.core.iterator.ListIteratorTemplate;
import com.pids.core.task.manager.TaskContainer;
import com.pids.core.task.manager.TaskProcessor;

import java.util.List;

/**
 * 列表数据解析器模板类
 *
 * @Author andory
 * @Date 2012-8-9下午1:40:41
 */
public abstract class ListParserTemplate<S, T> extends ListIteratorTemplate<S, T> implements ListParser<S, T>, TaskProcessor<S, T> {
    @Override
    public T call(S source) throws ICoreException {
        return this.parser(source, true);
    }

    @Override
    public T parser(S source) throws ICoreException {
        return this.parser(source, false);
    }

    protected abstract T parser(S source, boolean isInternal) throws ICoreException;

    @Override
    public List<T> parser(List<S> sources) throws ICoreException {
        return this.iterator(sources);
    }

    @Override
    public List<T> parser(List<S> sources, boolean fullMode) throws ICoreException {
        return this.iterator(sources, fullMode);
    }

    @Override
    public List<T> process(TaskContainer<S, T> container) throws ICoreException {
        return this.parser(container.getSources());
    }
}
