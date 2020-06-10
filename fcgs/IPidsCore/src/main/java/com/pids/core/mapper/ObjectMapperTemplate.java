package com.pids.core.mapper;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Mapper模板实现类
 * @author Andory
 * @date 2012-6-9上午12:13:20
 */
public abstract class ObjectMapperTemplate<K, V, S> extends AbstractObjectMapper<K, V, S> implements Iterable<K> {
	private static final long serialVersionUID = -2590009870731959315L;

	@Override
	public V getBySource(S source) {
		if (source == null) {
			return null;
		}
		return get(getMapperKey(source));
	}

	@Override
	public List<V> getBySourceList(List<S> sources) {
		List<K> keys = this.getKeys(sources);
		return this.getValues(keys);
	}

	/**
	 * 添加资源
	 * @author Andory
	 * @param source
	 * @date 2012-6-9上午12:57:27
	 */
	@Override
	public void add(S source) {
		if (source == null) {
			return;
		}
		K key = getMapperKey(source);
		V value = getMapperValue(source);
		if (isAcceptKey(key) && value != null) {
			this.add(key, value);
		}
	}

	/**
	 * 添加资源列表
	 * @author Andory
	 * @param sources
	 * @date 2012-6-9上午12:57:37
	 */
	@Override
	public void addAll(List<S> sources) {
		if (CollectionUtils.isEmpty(sources)) {
			return;
		}
		for (S source : sources) {
			this.add(source);
		}
	}

	@Override
	public List<K> getKeys(List<S> sources) {
		if (CollectionUtils.isEmpty(sources)) {
			return null;
		}
		List<K> keys = new ArrayList<K>();
		for (S source : sources) {
			if (source == null) {
				continue;
			}
			K key = this.getMapperKey(source);
			if (isAcceptKey(key)) {
				keys.add(key);
			}
		}
		return keys;
	}

	@Override
	public K getKey(S source) {
		if (source == null) {
			return null;
		}
		return this.getMapperKey(source);
	}

	/**
	 * 判定是否是一个合法的Key，默认判定其不为null即合法，根据不同实现的需要对Key合法性进行判定是很必要的
	 * @author jiangbin
	 * @param key
	 * @return
	 * @date 2014年10月13日下午8:43:27
	 */
	protected boolean isAcceptKey(K key) {
		return key != null;
	}

	@Override
	public Iterator<K> iterator() {
		return this.keySet().iterator();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/**
	 * 从给定资源对象中获取key
	 * @author Andory
	 * @param object
	 * @return
	 * @date 2012-6-9上午12:11:25
	 */
	protected abstract K getMapperKey(S object);

	/**
	 * 从给定资源对象中获取value
	 * @author Andory
	 * @param object
	 * @return
	 * @date 2012-6-9上午12:11:25
	 */
	protected abstract V getMapperValue(S object);

}
