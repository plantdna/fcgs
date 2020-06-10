package com.pids.core.mapper.utils;

import com.pids.core.mapper.MapperTemplate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 用于字符串元素统计的Mapper，key/value--字符串元素(关键字将会自动小写)/元素的重复个数，
 * 一般用来统计出给定字符串列表中重复数最多的元素及其重复数，
 * 这在多种需要计算百分占比及字符串择优的功能中会得到应用
 * @author YuanChunYan
 * @date 2012-12-27上午10:11:00
 */
public class StringItemCountMapper extends MapperTemplate<Integer, String> {
	private static final long serialVersionUID = -5800425792674178121L;

	public StringItemCountMapper() {
	}

	public StringItemCountMapper(List<String> sources) {
		this.addAll(sources);
	}

	@Override
	protected String getMapperKey(String object) {
		return StringUtils.stripToEmpty(object).toUpperCase();
	}

	@Override
	protected Integer getMapperValue(String object) {
		Integer count = get(getMapperKey(object));
		if (count == null) {
			count = 0;
		}
		return ++count;
	}

	/**
	 * 获得最大值
	 * @author YuanChunYan
	 * @return
	 * @date 2012-12-27上午10:11:14
	 */
	public int getMaxItemCount() {
		int maxCount = 0;
		for (String key : getKeys()) {
			int tmpCount = get(key);
			if (tmpCount > maxCount) {
				maxCount = tmpCount;
			}
		}
		return maxCount;
	}

	/**
	 * 获得最大值
	 * @author YuanChunYan
	 * @return
	 * @date 2012-12-27上午10:11:14
	 */
	public String getMaxCountKey() {
		String target = null;
		int maxCount = 0;
		for (String key : getKeys()) {
			int tmpCount = get(key);
			if (tmpCount > maxCount) {
				maxCount = tmpCount;
				target = key;
			}
		}
		return target;
	}

	/**
	 * 获取重复(统计数大于1)的字符串列表
	 * @author Andory
	 * @return
	 * @date 2013年9月4日上午10:46:27
	 */
	public List<String> getRepeatStrs() {
		List<String> all = new ArrayList<String>(this.getKeys());
		List<String> uniques = this.getUniqueStrs();
		if (!CollectionUtils.isEmpty(uniques)) {
			all.removeAll(uniques);
		}
		Collections.sort(all);
		return all;
	}

	@Override
	public List<String> getKeys() {
		List<String> keys = super.getKeys();
		if (!CollectionUtils.isEmpty(keys)) {
			Collections.sort(keys);
		}
		return keys;
	}

	/**
	 * 获取不存在重复(即统计数为1)的字符串列表
	 * @author Andory
	 * @return
	 * @date 2013年9月4日上午10:47:48
	 */
	public List<String> getUniqueStrs() {
		List<String> uniques = new ArrayList<String>();
		for (String key : getKeys()) {
			int tmpCount = get(key);
			if (tmpCount == 1) {
				uniques.add(key);
			}
		}
		Collections.sort(uniques);
		return uniques;
	}

	/**
	 * 获取给定Key字符串出现频率值，若不存在则返回0
	 * @author jiangbin
	 * @param key
	 * @return
	 * @date 2014年8月26日上午1:59:28
	 */
	public float getFrquency(String key) {
		Integer itemCount = get(key);
		if (itemCount == null) {
			return 0;
		}
		return itemCount / getItemCount() * 1.0f;
	}

	/**
	 * 获取全部各项的统计总数
	 * @author jiangbin
	 * @return
	 * @date 2014年8月26日上午2:00:27
	 */
	public int getItemCount() {
		int count = 0;
		for (String key : keySet()) {
			Integer itemCount = get(key);
			if (itemCount == null) {
				continue;
			}
			count += itemCount;
		}
		return count;
	}

	/**
	 * 获取指定计数值的字符串Key列表
	 * @author jiangbin
	 * @param count
	 * @return
	 * @date 2015年1月7日下午7:26:54
	 */
	public List<String> getKeys(int count) {
		if (count <= 0) {
			return null;
		}
		List<String> keys = getKeys();
		List<String> targets = new ArrayList<String>();
		for (String key : keys) {
			Integer value = get(key);
			if (value != null && value.intValue() == count) {
				targets.add(key);
			}
		}
		return targets;
	}
}
