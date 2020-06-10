package com.pids.core.mapper.updater;

/**
 * 模板实现类，用于其泛型类型均一致的情况下
 * @author jiangbin
 * @date 2018年5月30日下午6:34:32
 */
public abstract class MapperUpdater<S> extends MapperUpdaterTemplate<S, S> {

	private static final long serialVersionUID = 8853957002923633897L;

	@Override
	protected String getTargetKey(S target) {
		return getKey(target);
	}

}
