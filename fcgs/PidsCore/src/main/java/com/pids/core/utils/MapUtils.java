package com.pids.core.utils;

import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Map工具类
 * @author Andory
 * @date 2013年11月7日上午10:03:49
 */
public class MapUtils {
	/**
	 * 创建Map并添加给定的键值数据对
	 * @author Andory
	 * @param key
	 * @param value
	 * @return
	 * @date 2013年11月7日上午10:03:20
	 */
	public static <V> Map<String, V> createMap(String key, V value) {
		Map<String, V> map = new HashMap<String, V>();
		map.put(key, value);
		return map;
	}

	/**
	 * 创建Map并添加给定的键值数据对,创建出的map其value类型为object
	 * @author Andory
	 * @param key
	 * @param value
	 * @return
	 * @date 2013年11月7日上午10:03:20
	 */
	public static <T> Map<String, Object> createObjMap(String key, T value) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, value);
		return map;
	}

	/**
	 * 创建Map,这些key对应同一个value
	 * @author ANDORY
	 * @param keyList key列表
	 * @param value 值
	 * @return 若未给定key则返回空Map
	 * @date 2016年3月1日上午11:30:59
	 */
	public static <V> Map<String, V> createMap(List<String> keyList, V value) {
		if (CollectionUtils.isEmpty(keyList)) {
			return new HashMap<String, V>();
		}
		Map<String, V> target = new HashMap<String, V>();
		for (String key : keyList) {
			target.put(key, value);
		}
		return target;
	}

	/**
	 * 创建Map,这些key对应同一个value
	 * @author ANDORY
	 * @param keyListStr key列表的字符串格式，以逗号分隔，如：key1,key2,key3,...
	 * @param value 值
	 * @return 若未给定key则返回空Map
	 * @date 2016年3月1日上午11:31:01
	 */
	public static <V> Map<String, V> createListMap(String keyListStr, V value) {
		List<String> keyList = ListUtils.str2List(keyListStr);
		return createMap(keyList, value);
	}

	/**
	 * 合并Map
	 * @author jiangbin
	 * @param smaps
	 * @return 若未给定或无键值对则返回空map
	 * @date 2017年11月22日下午7:09:40
	 */
	@SuppressWarnings("unchecked")
	public static <S, V> Map<S, V> merger(Map<S, V>... smaps) {
		Map<S, V> target = new HashMap<>();
		if (smaps == null || smaps.length < 1) {
			return target;
		}
		for (Map<S, V> smap : smaps) {
			if (smap != null && !smap.isEmpty()) {
				target.putAll(smap);
			}
		}

		return target;
	}
}
