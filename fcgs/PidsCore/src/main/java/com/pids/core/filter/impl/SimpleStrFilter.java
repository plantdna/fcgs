package com.pids.core.filter.impl;

import com.pids.core.exception.ICoreException;
import com.pids.core.filter.ListFilterTemplate;
import com.pids.core.utils.ListUtils;

import java.util.List;

/**
 * <pre>通用字符串过滤器,判定给定字符串是否在目标集合中：
 * 1、若字符串为null或空则返回null
 * 2、若目标集合为null或空则直接返回字符串
 * 3、若目标集合中不存在给定字符串则返回null，否则返回字符串
 * 
 * 返回字符串本身则表示未被过滤掉，否则表示字符串被过滤掉了,
 * 注意：本过滤器是忽略字符串大小写的</pre>
 * @author Andory
 * @date 2012-7-24上午10:51:52
 */
public class SimpleStrFilter extends ListFilterTemplate<String, String> {
	private List<String> filter;

	public SimpleStrFilter() {
		super();
	}

	@Override
	protected String filter(String source, boolean isInternal) throws ICoreException {
		if (source == null || source.isEmpty()) {
			return null;
		} else if (filter == null || filter.isEmpty()) {
			return null;
		} else if (filter.contains(source.toLowerCase())) {
			return source;
		} else {
			return null;
		}
	}

	@Override
	protected String refilter(String source) throws ICoreException {
		if (source == null || source.isEmpty()) {
			return null;
		} else if (filter == null || filter.isEmpty()) {
			return source;
		} else if (filter.contains(source.toLowerCase())) {
			return null;
		} else {
			return source;
		}
	}

	/**
	 * 设置用于过滤的目标集合
	 * @author Andory
	 * @param filter
	 * @date 2012-7-24上午11:09:28
	 */
	public void setFilter(List<String> filter) {
		this.filter = ListUtils.toLower(filter);
	}

	/**
	 * 获取过滤集合
	 * @author Andory
	 * @return
	 * @date 2012-7-24上午11:09:57
	 */
	public List<String> getFilter() {
		return filter;
	}

}
