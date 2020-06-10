package com.fcgs.base.sorter;

import com.pids.core.utils.RegexUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Comparator;
import java.util.List;

/**
 * 由字母和数字组成的字符串排序器，先按字母字典顺序排然后按数字升序排
 * @author LiuJunGuang
 * @date 2013年6月20日上午9:47:54
 */

public class StringAndNumberComparator implements Comparator<String> {

	private static String pattern = "([A-Za-z]+)([0-9]+)";

	@Override
	public int compare(String sSamBarcode, String tSamBarcode) {
		if (StringUtils.isBlank(sSamBarcode) && StringUtils.isBlank(tSamBarcode)) {
			return 0;
		}
		if (StringUtils.isBlank(sSamBarcode)) {
			return 1;
		}
		if (StringUtils.isBlank(tSamBarcode)) {
			return -1;
		}
		List<String> sParts = RegexUtils.match(sSamBarcode, pattern);
		List<String> tParts = RegexUtils.match(tSamBarcode, pattern);
		if (CollectionUtils.isEmpty(sParts)) {
			return 1;
		}
		if (CollectionUtils.isEmpty(tParts)) {
			return -1;
		}
		int result = 0;
		//前缀字母
		String sPart = StringUtils.stripToEmpty(sParts.get(0));
		String tPart = StringUtils.stripToEmpty(tParts.get(0));
		result = sPart.compareToIgnoreCase(tPart);
		if (result != 0) {
			return result;
		}
		//数字部分
		int sPartNum = NumberUtils.toInt(sParts.get(1));
		int tPartNum = NumberUtils.toInt(tParts.get(1));
		return sPartNum - tPartNum;
	}
}
