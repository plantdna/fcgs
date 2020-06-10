package com.fcgs.base.checker;

import com.pids.core.checker.Checker;
import com.pids.core.checker.number.NumberChecker;
import com.pids.core.checker.number.NumberRangeChecker;
import com.pids.core.exception.ICoreException;

/**
 * 差异位点数校验器
 * @author jiangbin
 * @date 2012-11-6下午4:01:44
 */
public class DiffMarkerCountChecker implements Checker<Integer, Boolean> {
	private Integer minDiffMarkerCount;
	private Integer maxDiffMarkerCount;
	private final NumberRangeChecker checker;

	public DiffMarkerCountChecker() {
		checker = new NumberChecker();
	}

	@Override
	public Boolean check(Integer diffMarkerCount) throws ICoreException {
		if (diffMarkerCount == null) {
			return false;
		}
		checker.setMaxValue(maxDiffMarkerCount);
		checker.setMinValue(minDiffMarkerCount);
		return checker.checkInt(diffMarkerCount);
	}

	public Integer getMinDiffMarkerCount() {
		return minDiffMarkerCount;
	}

	public void setMinDiffMarkerCount(Integer minDiffMarkerCount) {
		this.minDiffMarkerCount = minDiffMarkerCount;
	}

	public Integer getMaxDiffMarkerCount() {
		return maxDiffMarkerCount;
	}

	public void setMaxDiffMarkerCount(Integer maxDiffMarkerCount) {
		this.maxDiffMarkerCount = maxDiffMarkerCount;
	}

}
