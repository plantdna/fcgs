package com.pids.core.filter;

import com.pids.core.exception.ICoreException;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 集合类型对象数据列表过滤器顶级接口,本模板实现只是将符合条件的对象过滤后返回，具体过滤方法由用户自己实现，
 * 注意：若被过滤对象列表为空或过滤条件对象为null则直接返回null值
 * @author jiangbin
 * @date 2012-1-4上午2:04:52
 */
public abstract class ListObjFilterTemplate<S, P> implements ListObjFilter<S, P, S> {

	@Override
	public List<S> filter(List<S> sources, P params) throws ICoreException {
		if (CollectionUtils.isEmpty(sources) || params == null) {
			return null;
		}
		List<S> targets = new ArrayList<>();
		for (S source : sources) {
			S target = this.filter(source, params);
			if (target != null) {
				targets.add(target);
			}
		}
		return targets;
	}

}
