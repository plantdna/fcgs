package com.pids.core.grouper;

import com.pids.core.converter.Converter;
import com.pids.core.mapper.GroupMapperTemplate;
import com.pids.core.mapper.VGroupMapper;

import java.util.List;
import java.util.Map;

/**
 * <pre>列表分组操作接口，注意：本接口的功能已被
 * {@link GroupMapperTemplate}
 * 及{@link VGroupMapper}所替代，
 * 本分组器接口最大的问题是实现分组的时候代码量过大，测试工作量过大</pre>
 * @author Andory
 * @date 2012-8-7下午8:54:19
 */
public abstract class ListGrouper<S, T> implements Grouper<List<S>, Map<String, List<T>>>, Converter<S, T> {
}
