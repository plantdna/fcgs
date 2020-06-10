package com.pids.core.grouper;

import com.pids.core.exception.ICoreException;
import com.pids.core.mapper.GroupMapperTemplate;
import com.pids.core.mapper.VGroupMapper;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>列表分组器模板类实现，注意：本接口的功能已被
 * {@link GroupMapperTemplate}
 * 及{@link VGroupMapper}所替代，
 * 本分组器接口最大的问题是实现分组的时候代码量过大，测试工作量过大</pre>
 * @author jiangbin
 * @date 2012-5-5上午12:24:25
 */
public abstract class ListGrouperTemplate<S, T> extends ListGrouper<S, T> {
	@Override
	public Map<String, List<T>> group(List<S> source) throws ICoreException {
		if (!isNeedGroup(source)) {
			return null;
		}
		Map<String, List<T>> grouper = new HashMap<String, List<T>>();
		for (S obj : source) {
			if (obj != null) {
				group(obj, grouper);
			}
		}
		return grouper;
	}

	/**
	 * 将给定对象添加到对应分组列表中
	 * @author jiangbin
	 * @param resultObj
	 * @param grouper
	 * @throws ICoreException 
	 * @date 2012-5-5上午1:23:49
	 */
	protected void group(S source, Map<String, List<T>> grouper) throws ICoreException {
		String key = getKey(source);
		if (!StringUtils.isNotBlank(key)) {
			List<T> list = grouper.get(key);
			if (list == null) {
				list = new ArrayList<T>();
				grouper.put(key, list);
			}
			T target = convert(source);
			if (target != null) {
				list.add(target);
			}
		}
	}

	/**
	 * 获取用于分组结果Map的key键,若返回null或空字符串值将忽略该key值
	 * @author jiangbin
	 * @param source
	 * @return
	 * @date 2012-5-5上午12:36:11
	 */
	public abstract String getKey(S source);

	/**
	 * 判定是否需要进行分组，主要是判定源数据列表是否为null或空列表
	 * @Author Andory
	 * @param sources
	 * @return true/false--需要/不需要分组
	 * @Date 2012-6-14上午01:15:40
	 */
	protected boolean isNeedGroup(List<S> sources) {
		return sources != null && !sources.isEmpty();
	}
}
