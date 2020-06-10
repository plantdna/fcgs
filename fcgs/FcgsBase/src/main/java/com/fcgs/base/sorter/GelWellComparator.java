package com.fcgs.base.sorter;

import java.util.Comparator;

/**
 * 板孔号比较器
 * @author Ritchieyan
 * @date 2014年7月11日上午10:04:08
 */

public class GelWellComparator implements Comparator<String> {

	private final StringAndNumberComparator comparator = new StringAndNumberComparator();

	@Override
	public int compare(String sGelWell, String tGelWell) {
		return comparator.compare(sGelWell, tGelWell);
	}

}
