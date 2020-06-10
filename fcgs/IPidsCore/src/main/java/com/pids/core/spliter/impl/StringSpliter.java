package com.pids.core.spliter.impl;

import com.pids.core.exception.ICoreException;
import com.pids.core.spliter.Spliter;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串分割器，按照给定位数分割
 * @author jiangbin
 * @date 2013-12-3下午12:39:37
 */
public class StringSpliter implements Spliter<String, List<String>, Integer> {

	@Override
	public List<String> split(String sources, Integer step) throws ICoreException {
		List<String> targets = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < sources.length(); i++) {
			if (sb.length() == step) {
				targets.add(sb.toString());
				sb = new StringBuilder();
			}
			sb.append(sources.charAt(i));
		}
		if (sb.length() > 0) {
			targets.add(sb.toString());
		}
		return targets;
	}

}
