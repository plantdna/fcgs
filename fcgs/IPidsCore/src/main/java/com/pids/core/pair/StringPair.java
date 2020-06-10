package com.pids.core.pair;

import com.pids.core.utils.ListUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 字符串数据对象信息对象，本对象提供按自然升序排序功能，先按照source属性排序，再按target排序
 * @author jiangbin
 * @date 2012-11-25下午10:32:43
 */
public class StringPair extends SimplePair<String, String> implements Comparable<StringPair> {

	private static final long serialVersionUID = -9168881470200799567L;

	public StringPair() {
		super();
	}

	public StringPair(String source, String target) {
		super(source, target);
	}

	@Override
	public int compareTo(StringPair sourcePair) {
		if (sourcePair == null) {
			return 0;
		}
		String source = StringUtils.stripToEmpty(sourcePair.getSource());
		String target = StringUtils.stripToEmpty(this.getSource());
		int result = target.compareToIgnoreCase(source);
		if (result != 0) {
			return result;
		}
		source = StringUtils.stripToEmpty(sourcePair.getTarget());
		target = StringUtils.stripToEmpty(this.getTarget());
		return target.compareToIgnoreCase(source);
	}

	/**
	 * 转换成列表
	 * @author jiangbin
	 * @return
	 * @date 2014年10月30日下午6:29:50
	 */
	public List<String> asList() {
		return ListUtils.createList(getSource(), getTarget());
	}
}
