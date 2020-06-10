package com.pids.core.filter;

import com.pids.core.exception.ICoreException;
import com.pids.core.iterator.ListIteratorTemplate;
import com.pids.core.task.manager.TaskContainer;
import com.pids.core.task.manager.TaskProcessor;

import java.util.List;

/**
 * 集合类型数据过滤器模板类
 * </pre>
 *
 * @author jiangbin
 * @date 2012-1-4上午2:04:52
 */
public abstract class ListFilterTemplate<S, T> extends ListIteratorTemplate<S, T> implements ListFilter<S, T>, TaskProcessor<S, T> {

    @Override
    public List<T> filter(List<S> sources) throws ICoreException {
        return this.iterator(sources);
    }

    @Override
    public List<T> filter(List<S> sources, boolean fullMode) throws ICoreException {
        return this.iterator(sources, fullMode);
    }

    @Override
    public T call(S source) throws ICoreException {
        return this.filter(source, true);
    }

    @Override
    public T filter(S source) throws ICoreException {
        return this.filter(source, false);
    }

    protected abstract T filter(S source, boolean isInternal) throws ICoreException;

    /**
     * <pre>反向过滤逻辑，一般通过如下代码方式使用:
     * 1、以下是默认内置了正向逻辑的过滤器，同时实现了refiter方法:
     * public class StringFilter extends ListFilterTemplate<String, String> {
     * 		//过滤出以"china"开头的字符串
     *        @Override
     *        public String filter(String source) throws ICoreException {
     * 			if (source == null) {
     * 				return null;
     *            }
     * 			source = source.toLowerCase();
     * 			return source.startsWith("china") ? source : null;
     *        }
     * 		//过滤出不以"china"开头的字符串
     *        @Override
     *        protected String refilter(String source) throws ICoreException {
     * 			if (source == null) {
     * 				return null;
     *            }
     * 			source = source.toLowerCase();
     * 			return !source.startsWith("china") ? source : null;
     *        }
     *    }
     * 2、以下代码通常在需要使用反向过滤器的类中进行定义，以下划线("_")开头表示是一个内部临时类，
     *  该类重载了迭代逻辑的回调接口调用反向过滤逻辑，这样_StringFilter这个内部类就具备反向过滤功能
     * 	private class _StringFilter extends StringFilter {
     *        @Override
     *        public String call(String source) throws ICoreException {
     * 			return super.refilter(source);
     *        }
     *    }
     * </pre>
     *
     * @param source
     * @return
     * @throws ICoreException
     * @author jiangbin
     * @date 2014年3月13日下午7:38:21
     */
    protected T refilter(S source) throws ICoreException {
        return this.filter(source);
    }

    @Override
    public List<T> process(TaskContainer<S, T> container) throws ICoreException {
        return this.filter(container.getSources());
    }
}
