package com.pids.core.spliter.impl;

import com.pids.core.exception.ICoreException;
import com.pids.core.spliter.Spliter;

import java.util.LinkedList;
import java.util.List;

/**
 * 长整形数的分割器
 * @author jiangbin
 * @date 2013-4-30下午2:29:15
 */
public class LongNumberSpliter implements Spliter<Long, List<Long>, Long> {

	@Override
	public List<Long> split(Long source, Long step) throws ICoreException {
		List<Long> targets = new LinkedList<Long>();
		for (long i = 0; i < source; i += step) {
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
