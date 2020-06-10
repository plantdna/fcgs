package com.fcgs.core.comparer.utils;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 位点工具类
 * @author jiang
 * @date 2018年9月18日下午12:59:42
 */
public class MarkerUtils {
	/**
	 * 判定是否为缺失数据位点
	 * @author jiang
	 * @param marker
	 * @return
	 * @date 2018年9月18日下午12:32:57
	 */
	public static boolean isMissMarker(int[] marker) {
		return ArrayUtils.isEmpty(marker) || marker.length != 4 || (marker[0] == 0 && marker[2] == 0);
	}
}
