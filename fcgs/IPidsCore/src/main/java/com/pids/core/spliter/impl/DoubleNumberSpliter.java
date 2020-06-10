package com.pids.core.spliter.impl;

import com.pids.core.exception.ICoreException;
import com.pids.core.spliter.Spliter;

import java.util.LinkedList;
import java.util.List;

/**
 * 双精度浮点数的分割器
 * @author jiangbin
 * @date 2013-4-30下午2:28:54
 */
public class DoubleNumberSpliter implements Spliter<Double, List<Double>, Double> {

	@Override
	public List<Double> split(Double source, Double step) throws ICoreException {
		List<Double> targets = new LinkedList<Double>();
		for (double i = 0; i < source; i += step) {
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
