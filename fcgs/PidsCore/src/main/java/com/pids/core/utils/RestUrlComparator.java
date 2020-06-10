package com.pids.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.List;

/**
 * REST风格URL比较器
 * @author jiangbin
 * @date 2014年6月20日下午1:49:15
 */

public class RestUrlComparator implements Comparator<String> {

	@Override
	public int compare(String source, String target) {
		if (StringUtils.isBlank(source)) {
			return -1;
		}
		if (StringUtils.isBlank(target)) {
			return 1;
		}
		List<String> sUrls = preDealUrl(source);
		List<String> tUrls = preDealUrl(target);
		int lenght = Math.max(ListUtils.size(sUrls), ListUtils.size(tUrls));
		int result = 0;
		for (int index = 0; index < lenght; index++) {
			if (ListUtils.size(sUrls) == index) {
				return -1;
			}
			if (ListUtils.size(tUrls) == index) {
				return 1;
			}
			String sUrl = StringUtils.stripToEmpty(sUrls.get(index));
			String tUrl = StringUtils.stripToEmpty(tUrls.get(index));
			result = sUrl.compareToIgnoreCase(tUrl);
			if (result != 0) {
				return result;
			}
		}
		return result;
	}

	/**
	 * 对URL进行预处理，如去掉特殊字符，分割成列表
	 * @author jiangbin
	 * @param restUrl
	 * @return
	 * @date 2014年6月20日下午12:44:40
	 */
	protected List<String> preDealUrl(String restUrl) {
		String targetUrl = StringUtils.stripToEmpty(restUrl);
		targetUrl = targetUrl.replaceAll("\\{|\\:|\\.|\\+|\\}", "");
		return ListUtils.array2List(StringUtils.split(targetUrl, "/"));
	}

}
