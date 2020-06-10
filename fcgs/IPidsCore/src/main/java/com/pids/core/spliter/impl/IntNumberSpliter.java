package com.pids.core.spliter.impl;

import com.pids.core.exception.ICoreException;
import com.pids.core.spliter.Spliter;

import java.util.LinkedList;
import java.util.List;

/**
 * 该功能主要是用于计算指定整数按照给定步进值分割后各个步进长度列表
 * @author jiangbin
 * @date 2013-1-29下午12:10:15
 */
public class IntNumberSpliter implements Spliter<Integer, List<Integer>, Integer> {

	@Override
	public List<Integer> split(Integer source, Integer step) throws ICoreException {
		List<Integer> targets = new LinkedList<Integer>();
		for (int i = 0; i < source; i += step) {
			if (i > source) {
				targets.add(i);
				break;
			} else {
				targets.add(i);
			}
		}
		targets.add(source);
		return targets;
	}

}
