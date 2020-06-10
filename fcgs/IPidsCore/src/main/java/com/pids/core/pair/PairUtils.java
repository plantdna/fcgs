package com.pids.core.pair;

import com.pids.core.utils.ListUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据对象信息对象工具类
 * @author jiangbin
 * @date 2012-11-25下午10:13:16
 */
public class PairUtils {
	/**
	 * 以指定数据对构建一个数据对象信息对象
	 * @author jiangbin
	 * @param source
	 * @param target
	 * @return
	 * @date 2012-11-25下午10:18:27
	 */
	public static StringPair create(String source, String target) {
		return new StringPair(source, target);
	}

	/**
	 * 解析出指定源字符串中的数据对象信息，解析时会忽略掉不完整的数据信息对象
	 * @author jiangbin
	 * @param source　数据对信息字符串，格式为：S1:T1,S2:T2,....
	 * @return 始终返回非null列表对象
	 * @date 2012-11-25下午10:18:53
	 */
	public static List<StringPair> parser(String source) {
		List<String> list = ListUtils.str2List(source);
		List<StringPair> target = new ArrayList<StringPair>();
		if (CollectionUtils.isEmpty(list)) {
			return target;
		}
		for (String str : list) {
			if (!str.contains(":") || str.split(":").length < 2) {
				continue;
			}
			String key = StringUtils.stripToEmpty(str.split(":")[0]);
			String value = StringUtils.stripToEmpty(str.split(":")[1]);
			if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
				continue;
			}
			target.add(create(key, value));
		}
		return target;
	}

	/**
	 * 将给定的信息对象数据列表格式化成格式为:S1:T1,S2:T2,....的字符串
	 * @author jiangbin
	 * @param pairs
	 * @return
	 * @date 2012-11-25下午10:25:16
	 */
	public static String format(List<StringPair> pairs) {
		if (CollectionUtils.isEmpty(pairs)) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (StringPair pair : pairs) {
			if (StringUtils.isBlank(pair.getSource()) || StringUtils.isBlank(pair.getTarget())) {
				continue;
			}
			sb.append(String.format("%s:%s,", pair.getSource(), pair.getTarget()));
		}
		return sb.toString();
	}

	/**
	 * 将字符串数据对信息对象列表转换成Map
	 * @author jiangbin
	 * @param pairs
	 * @return 始终返回非null的Map对象
	 * @date 2012-11-25下午11:09:58
	 */
	public static Map<String, String> toMap(List<StringPair> pairs) {
		Map<String, String> map = new HashMap<String, String>();
		if (CollectionUtils.isEmpty(pairs)) {
			return map;
		}
		for (StringPair pair : pairs) {
			map.put(pair.getSource(), pair.getTarget());
		}
		return map;
	}

	/**
	 * 将Map转换成字符串数据对信息对象列表
	 * @author jiangbin
	 * @param map
	 * @return 始终返回非null列表对象
	 * @date 2012-11-25下午11:10:18
	 */
	public static List<StringPair> toPairs(Map<String, String> map) {
		List<StringPair> pairs = new ArrayList<StringPair>();
		if (map == null || map.isEmpty()) {
			return pairs;
		}
		for (String key : map.keySet()) {
			if (StringUtils.isBlank(key) || StringUtils.isBlank(map.get(key))) {
				continue;
			}
			pairs.add(create(key, map.get(key)));
		}
		return pairs;
	}

	/**
	 * 过滤出源数据列表
	 * @author jiangbin
	 * @param pairs
	 * @return
	 * @date 2012-11-25下午10:36:19
	 */
	public static List<String> filterSources(List<StringPair> pairs)  {
		if(CollectionUtils.isEmpty(pairs)){
			return null;
		}
		List<String> targets = new ArrayList<String>();
		for(StringPair pair:pairs){
			if(StringUtils.isNotBlank(pair.getSource())){
				targets.add(pair.getSource());
			}
		}
		return targets;
	}
	
	/**
	 * 过滤出源数据列表
	 * @author jiangbin
	 * @param pairs
	 * @return
	 * @date 2012-11-25下午10:36:19
	 */
	public static List<String> filterTargets(List<StringPair> pairs)  {
		if(CollectionUtils.isEmpty(pairs)){
			return null;
		}
		List<String> targets = new ArrayList<String>();
		for(StringPair pair:pairs){
			if(StringUtils.isNotBlank(pair.getTarget())){
				targets.add(pair.getTarget());
			}
		}
		return targets;
	}
}
