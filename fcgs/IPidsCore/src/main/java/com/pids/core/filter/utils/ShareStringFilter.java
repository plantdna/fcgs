package com.pids.core.filter.utils;

import com.pids.core.exception.ICoreException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤出两个字符串列表中共同的字符串
 * @author jiangbin
 * @date 2015年7月11日下午2:05:23
 */
public class ShareStringFilter {

	/**
	 * 过滤出两个字符串列表中共同的字符串
	 * @author jiangbin
	 * @param sources
	 * @param targets
	 * @return
	 * @throws ICoreException
	 * @date 2015年7月11日下午2:08:10
	 */
	public static List<String> filter(List<String> sources, List<String> targets) throws ICoreException {
		List<String> results = new ArrayList<>();
		for (String source : sources) {
			if (StringUtils.isBlank(source)) {
				continue;
			}
			for (String target : targets) {
				if (StringUtils.isBlank(target)) {
					continue;
				}
				if (source.equalsIgnoreCase(target)) {
					results.add(source);
				}
			}
		}
		return results;
	}
}
