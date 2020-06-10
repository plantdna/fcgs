package com.pids.core.register;

import com.pids.core.exception.ICoreException;
import com.pids.core.iterator.ListIteratorTemplate;

import java.util.List;

/**
 * 列表注册器模板实现类
 * @Author andory
 * @Date 2012-8-15下午2:17:13
 */
public abstract class ListRegisterTemplate<S> extends ListIteratorTemplate<S, Boolean> implements ListRegister<S> {
	@Override
	public boolean register(List<S> sources) throws ICoreException {
		List<Boolean> results = this.iterator(sources);
		return results != null && sources != null && results.size() == sources.size() && isAllRegisterOk(results);
	}

	@Override
	public boolean register(List<S> sources, boolean fullMode) throws ICoreException {
		List<Boolean> results = this.iterator(sources, fullMode);
		return results != null && sources != null && results.size() == sources.size() && isAllRegisterOk(results);
	}

	@Override
	public Boolean call(S source) throws ICoreException {
		return this.register(source, true);
	}

	@Override
	public boolean register(S source) throws ICoreException {
		return this.register(source, false);
	}

	protected abstract Boolean register(S source, boolean isInternal) throws ICoreException;

	/**
	 * <pre>判定是否所有对象均注册成功，原则：
	 * 	给定结果列表中结果值为null或false时则判定为注册失败</pre>
	 * @Author andory
	 * @param results
	 * @return
	 * @Date 2012-8-15下午2:25:51
	 */
	protected boolean isAllRegisterOk(List<Boolean> results) {
		for (Boolean resulte : results) {
			if (resulte == null || (resulte != null && !resulte)) {
				return false;
			}
		}
		return true;
	}
}
