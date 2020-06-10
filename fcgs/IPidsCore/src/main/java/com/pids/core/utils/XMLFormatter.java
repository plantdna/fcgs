package com.pids.core.utils;

import com.pids.core.exception.ICoreException;
import com.pids.core.formatter.Formatter;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.IOException;
import java.io.StringWriter;

/**
 * XML文档格式化工具方法
 * @author Andory
 * @date 2012-5-24下午09:28:32
 */
public class XMLFormatter implements Formatter<Document, String> {
	private static String XML_ENCODING = "UTF-8";

	/**
	 * 格式化XML文档
	 * @author jiangbin
	 * @param sqlXml
	 * @return
	 * @date 2012-4-29下午3:07:22
	 */
	public String format(String xml) throws ICoreException {
		try {
			Document document = DocumentHelper.parseText(xml);
			document.setXMLEncoding(XML_ENCODING);
			return this.format(document);
		} catch (DocumentException e) {
			throw new ICoreException(e);
		}
	}

	/**
	 * 格式化XML节点
	 * @author Andory
	 * @param element
	 * @return
	 * @throws Exception
	 * @date 2012-5-24下午09:27:51
	 */
	public String format(Element element) throws ICoreException {
		Document document = DocumentHelper.createDocument(element);
		document.setXMLEncoding(XML_ENCODING);
		return this.format(document);
	}

	/**
	 * 获取实例
	 * @author jiangbin
	 * @return
	 * @date 2012-6-6下午1:21:58
	 */
	public static XMLFormatter getInstance() {
		return new XMLFormatter();
	}

	/**
	 * 格式化XML文档
	 * @author Andory
	 * @param document
	 * @return
	 * @throws Exception
	 * @date 2012-5-24下午09:28:06
	 */
	@Override
	public String format(Document document) throws ICoreException {
		try {
			OutputFormat formater = OutputFormat.createPrettyPrint();
			formater.setEncoding(XML_ENCODING);
			StringWriter out = new StringWriter();
			XMLWriter writer = new XMLWriter(out, formater);
			writer.write(document);
			writer.close();
			return out.toString();
		} catch (IOException e) {
			Logger.getLogger(getClass()).warn(e);
			throw new ICoreException(e);
		}
	}
}
