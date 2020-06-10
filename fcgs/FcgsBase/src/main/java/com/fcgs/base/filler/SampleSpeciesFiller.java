package com.fcgs.base.filler;

import com.fcgs.base.domain.SampleSpecies;
import com.pids.core.exception.ICoreException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 样品种属信息填充，本功能只对未设置种属字段的情况进行设置，若已设置了种属字段则自动忽略，
 * 在某些情况下需要统一设置种属字段时，可以先行通过{@link EmptySampleSpeciesFiller}功能清空掉种属字段，
 * 再通过本功能来设置即可
 * @author Jiangbin
 * @date 2014-4-25下午11:19:25
 */

public class SampleSpeciesFiller<S extends SampleSpecies> {

	/**
	 * 将给定样品种属填充到源数据列表中
	 * @author Jiangbin
	 * @param sources
	 * @param samSpecies 种属，若数据中包含了种属则不进行填充
	 * @return
	 * @throws ICoreException
	 * @date 2014-4-25下午11:26:15
	 */
	public List<S> fill(List<S> sources, String samSpecies) throws ICoreException {
		List<S> targets = new ArrayList<>();
		for (S source : sources) {
			if (source == null) {
				continue;
			}
			String species = StringUtils.stripToEmpty(source.getSamSpecies());
			if (species.isEmpty()) {
				source.setSamSpecies(samSpecies);
			}
			targets.add(source);
		}
		return targets;
	}

}
