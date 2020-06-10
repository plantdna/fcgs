package com.pids.core.parser.impl;

import com.pids.core.exception.ICoreException;
import com.pids.core.filter.impl.SimpleStrFilter;
import com.pids.core.parser.Parser;
import com.pids.core.utils.StringEx;
import org.apache.commons.collections.CollectionUtils;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 将XML解析成Map,Key值为结点名，Value为结点值，
 * 该解析器支持限定Key名称列表的方式构建Map，
 * (这种内建机制需要{@link Xml2MapParser.simpleStrFilter}属性不为null时才有效),
 * 这种内建过滤机制通过{@link SimpleStrFilter}来实现的,
 * 属性{@link Xml2MapParser.paramNames}用来作为
 * 解析后生成的Map的Key取值范围限定集合
 * @author Andory
 * @date 2012-7-24上午08:40:33
 */
public class Xml2MapParser implements Parser<Document, Map<String, String>> {
	private SimpleStrFilter simpleStrFilter = new SimpleStrFilter();
	private List<String> paramNames;

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> parser(Document source) throws ICoreException {
		Map<String, String> map = new HashMap<String, String>();
		if (source == null || source.getRootElement() == null) {
			return map;
		}
		if (this.simpleStrFilter != null) {
			this.simpleStrFilter.setFilter(paramNames);
		}
		Element rootEl = source.getRootElement();
		final List<Element> elements = rootEl.elements();
		if (elements != null && !elements.isEmpty()) {
			for (Element element : elements) {
				parserElement(element, map);
			}
		}
		return map;
	}

	/**
	 * 解析单个结点并添加到给定Map中
	 * @author Andory
	 * @param element xml结点对象
	 * @param map 存放解析出来的key-value对
	 * @throws ICoreException 
	 * @date 2012-7-24上午08:47:30
	 */
	protected void parserElement(Element element, Map<String, String> map) throws ICoreException {
		if (element != null && map != null) {
			String key = element.getName();
			String value = element.getText();
			if (checkItem(key, value)) {
				map.put(key, value);
			}
		}
	}

	/**
	 * 检查结点元素键值对合法性
	 * @author jiangbin
	 * @param key
	 * @param value
	 * @return
	 * @throws FilterException
	 * @date 2012-11-9下午2:26:59
	 */
	protected boolean checkItem(String key, String value) throws ICoreException {
		if (StringEx.isNull(key) || StringEx.isNull(value)) {
			return false;
		}
		if (this.simpleStrFilter == null)
			return true;
		if (CollectionUtils.isEmpty(this.simpleStrFilter.getFilter()))
			return true;
		if (this.simpleStrFilter.filter(key) == null) {
			return false;
		}
		return true;
	}

	/**
	 * 设置参数名列表,请注意：必需全部为小写参数名
	 * @author Andory
	 * @param paramNames
	 * @date 2012-7-24上午10:10:45
	 */
	public void setParamNames(List<String> paramNames) {
		this.paramNames = paramNames;
	}

	/**
	 * 获取参数名列表,请注意：全部为小写参数名
	 * @author Andory
	 * @return
	 * @date 2012-7-24上午10:10:57
	 */
	public List<String> getParamNames() {
		return paramNames;
	}

	/**
	 * 设置字符串过滤器
	 * @author Andory
	 * @param simpleFilter
	 * @date 2012-7-24上午10:53:41
	 */
	public void setSimpleStrFilter(SimpleStrFilter simpleStrFilter) {
		this.simpleStrFilter = simpleStrFilter;
	}

	/**
	 * 获取字符串过滤器
	 * @author Andory
	 * @return
	 * @date 2012-7-24上午10:53:50
	 */
	public SimpleStrFilter getSimpleStrFilter() {
		return simpleStrFilter;
	}
}
