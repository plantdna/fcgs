package com.pids.core.converter;

import com.pids.core.exception.ICoreException;
import com.pids.core.iterator.ListIteratorTemplate;
import com.pids.core.task.manager.TaskContainer;
import com.pids.core.task.manager.TaskProcessor;

import java.util.List;

/**
 * 集合列表对象转换器，将列表对象转换成另一类型对象
 *
 * @author jiangbin
 * @date 2012-4-11下午12:02:20
 */
public abstract class ListConverterTemplate<S, T> extends ListIteratorTemplate<S, T> implements ListConverter<S, T>, TaskProcessor<S, T> {

    @Override
    public List<T> convert(List<S> sources) throws ICoreException {
        return this.iterator(sources);
    }

    @Override
    public List<T> convert(List<S> sources, boolean fullMode) throws ICoreException {
        return this.iterator(sources, fullMode);
    }

    @Override
    public T call(S source) throws ICoreException {
        return this.convert(source, true);
    }

    @Override
    public T convert(S source) throws ICoreException {
        return this.convert(source, false);
    }

    @Override
    public List<T> process(TaskContainer<S, T> container) throws ICoreException {
        return this.convert(container.getSources());
    }

    protected abstract T convert(S source, boolean isInternal) throws ICoreException;
}
