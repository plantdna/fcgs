package com.pids.core.pair;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

;

/**
 * 数据对信息对象的简单实现类
 * @author jiangbin
 * @date 2012-11-25下午10:33:39
 */
public class SimplePair<S, T> implements Pair<S, T> {
	private static final long serialVersionUID = -629398355880727451L;
	private S source;
	private T target;

	public SimplePair() {
	}

	public SimplePair(S source, T target) {
		this.source = source;
		this.target = target;
	}

	@Override
	public S getSource() {
		return source;
	}

	@Override
	public void setSource(S source) {
		this.source = source;
	}

	@Override
	public T getTarget() {
		return target;
	}

	@Override
	public void setTarget(T target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void wrap() {
		S tmp = source;
		source = (S) target;
		target = (T) tmp;
	}
}
