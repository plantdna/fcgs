package com.pids.core.spliter.impl;

import com.pids.core.exception.ICoreException;
import com.pids.core.filter.ListFilterTemplate;
import com.pids.core.utils.ListUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 根据关键字列表分割字符串，分割后的字符串列表中包含这些关键字
 * @author jiangbin
 * @date Jul 19, 2019 10:48:58 AM
 */
public class KeyWordSpliter {
	/**
	 * 依据关键字分割字符串，关键字包含在分割的列表中
	 * @author jiangbin
	 * @param source 源字符串
	 * @param keyWords 关键字
	 * @return 若参数不正确直接返回null，若未包含关键字则返回只包含源字符串的列表，否则返回分割后的字符串列表
	 * @date Jul 19, 2019 10:51:19 AM
	 */
	public List<SplitItem> split(String source, List<String> keyWords) {
		if (StringUtils.isBlank(source) || CollectionUtils.isEmpty(keyWords)) {
			return null;
		}

		//定义各关键字在字符串中位置索引号映射关系
		List<KeyPosition> positions = findPositions(source, keyWords);
		if (CollectionUtils.isEmpty(positions)) {
			return ListUtils.createList(new SplitItem(source, false));
		}

		//按照分割位置索引号排序
		sortPositions(positions);

		//分割字符串
		return splitItems(source, positions);
	}

	/**
	 * 依据关键字分割字符串，关键字包含在分割的列表中
	 * @author jiangbin
	 * @param source 源字符串
	 * @param keyWords 关键字
	 * @return 若参数不正确直接返回null，若未包含关键字则返回只包含源字符串的列表，否则返回分割后的字符串列表
	 * @date Jul 19, 2019 1:42:42 PM
	 */
	public List<String> splitList(String source, List<String> keyWords) {
		List<SplitItem> items = this.split(source, keyWords);
		return new SplitItemFilter().filter(items);
	}

	/**
	 * 按照关键字位置分割字符串
	 * @author jiangbin
	 * @param source
	 * @param positions
	 * @return
	 * @date Jul 19, 2019 11:04:47 AM
	 */
	private List<SplitItem> splitItems(String source, List<KeyPosition> positions) {
		List<SplitItem> items = new ArrayList<>();
		int start = 0;
		int end = 0;
		for (int index = 0; index < positions.size(); index++) {
			KeyPosition position = positions.get(index);
			end = position.start;

			//关键字前面部分
			String prefix = source.substring(start, end);
			if (StringUtils.isNotBlank(prefix)) {
				items.add(new SplitItem(prefix, false));
			}

			//关键字
			String keyWord = source.substring(position.start, position.end);
			items.add(new SplitItem(keyWord, true));

			//切换起始位置索引号
			start = position.end;

			//最后一个关键字后面部分
			if (index == positions.size() - 1) {
				String last = source.substring(start);
				if (StringUtils.isNotBlank(last)) {
					items.add(new SplitItem(last, false));
				}
			}

		}
		return items;
	}

	/**
	 * 对关键字位置信息排序
	 * @author jiangbin
	 * @param positions
	 * @date Jul 19, 2019 11:05:38 AM
	 */
	private void sortPositions(List<KeyPosition> positions) {
		Collections.sort(positions);
	}

	/**
	 * 查找关键字的位置列表
	 * @author jiangbin
	 * @param source
	 * @param keyWord
	 * @return
	 * @date Jul 19, 2019 11:01:46 AM
	 */
	private List<KeyPosition> findPositions(String source, String keyWord) {
		List<KeyPosition> positions = new ArrayList<>();
		Matcher matcher = Pattern.compile(keyWord, Pattern.CASE_INSENSITIVE | Pattern.DOTALL).matcher(source);
		while (matcher.find()) {
			KeyPosition position = new KeyPosition();
			position.start = matcher.start();
			position.end = matcher.end();
			position.keyWord = keyWord;
			positions.add(position);
		}
		return positions;
	}

	/**
	 * 查找关键字的位置列表
	 * @author jiangbin
	 * @param source
	 * @param keys
	 * @return
	 * @date Jul 19, 2019 12:19:15 PM
	 */
	private List<KeyPosition> findPositions(String source, List<String> keys) {
		List<KeyPosition> positions = new ArrayList<>();
		for (String key : keys) {
			List<KeyPosition> keyPositions = findPositions(source, key);
			if (CollectionUtils.isNotEmpty(keyPositions)) {
				positions.addAll(keyPositions);
			}
		}
		return positions;
	}

	/**
	 * 关键字位置信息，支持按起始位置索引号升序排序
	 * @author jiangbin
	 * @date Jul 19, 2019 11:02:02 AM
	 */
	public static class KeyPosition implements Serializable, Comparable<KeyPosition> {
		private static final long serialVersionUID = -6804501875957834819L;
		public Integer start;//起始位置
		public Integer end;//结束位置
		public String keyWord;//关键字

		@Override
		public int compareTo(KeyPosition target) {
			if (target == null || target.start == null) {
				return -1;
			}
			if (start == null) {
				return 1;
			}
			return start - target.start;
		}
	}

	/**
	 * 分割后的内容项
	 * @author jiangbin
	 * @date Jul 19, 2019 1:36:48 PM
	 */
	public static class SplitItem {
		public String item;
		public boolean isKeyWord = false;

		public SplitItem(String item, boolean isKey) {
			super();
			this.item = item;
			this.isKeyWord = isKey;
		}

	}

	/**
	 * 过滤分割项中的字符串
	 * @author jiangbin
	 * @date Jul 19, 2019 1:41:59 PM
	 */
	public static class SplitItemFilter extends ListFilterTemplate<SplitItem, String> {

		@Override
		protected String filter(SplitItem source, boolean isInternal) throws ICoreException {
			return source.item;
		}

	}

}
