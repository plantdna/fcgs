package com.pids.core.configure.xml;

import com.pids.core.configure.AbstractConfigure;
import org.dom4j.Element;

import java.util.Map;

/**
 * XML格式配置文件操作接口，该接口可以用于替换java属性文件
 * @author Andory
 */
public abstract class XmlConfigure extends AbstractConfigure<Map<String, String>> {
	/**
	 * 获取XML属性文件的结点
	 * @author Andory
	 * @return
	 * @date 2012-5-25下午12:01:42
	 */
	public Element getXmlProperties() {
		return this.fillProperties(getProperties());
	}

	/**
	 * 设置属性列表
	 * @author Andory
	 * @param rootElement
	 * @date 2012-5-25下午12:01:44
	 */
	public void setXmlProperties(Element rootElement) {
		this.setProperties(parserProperties(rootElement));
	}
	
	protected abstract Element fillProperties(Map<String, String> properties);
	protected abstract Map<String, String> parserProperties(Element rootElement);
}
