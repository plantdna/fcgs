package com.pids.core.saver.utils;

import com.pids.core.exception.ICoreException;
import com.pids.core.saver.Saver;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.File;

/**
 * XML保存器
 * @author Andory
 * @date 2012-5-25下午02:13:51
 */
public interface IXmlSaver extends Saver<Element, File> {

	/**
	 * 保存文档Xml对象到目标文件
	 * @author Jiangbin
	 * @param document
	 * @param targetFile
	 * @return
	 * @throws ICoreException
	 * @date 2014年8月23日下午10:43:27
	 */
	public boolean save(Document document, File targetFile) throws ICoreException;

	/**
	 * 保存给定Xml结点到目标文件
	 * @author Jiangbin
	 * @param source
	 * @param targetPath
	 * @return
	 * @throws ICoreException
	 * @date 2014年8月23日下午10:43:32
	 */
	public boolean save(Element source, String targetPath) throws ICoreException;
}
