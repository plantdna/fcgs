/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pids.core.configure.xml;

import com.pids.core.exception.ICoreException;
import com.pids.core.utils.XMLFormatter;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * XML配置文件的一个简单实现，本实现类主要是实现XML节点的结构构建与解析控制
 *
 * @author Andory
 */
public class SimpleXmlConfigure extends AbstractXmlConfigure {
    public SimpleXmlConfigure() throws ICoreException {
        super();
    }

    public SimpleXmlConfigure(String propertiesFile) throws ICoreException {
        super(propertiesFile);
    }

    @Override
    public String getRootName() {
        return "root";
    }

    @Override
    public Element createPropertyElement(String key, String value) {
        Element element = DocumentHelper.createElement("param");
        element.addAttribute("name", key);
        element.addCDATA(value);
        return element;
    }

    @Override
    public String[] parserPropertyElement(Element element) {
        String name = element.attributeValue("name");
        String value = element.getTextTrim();
        return new String[]{name, value};
    }

    @Override
    public String getDefaultPropertiesFile() {
        return null;
    }

    public static void main(String[] args) {
        try {
            String config = "./config.xml";
            SimpleXmlConfigure configXml = new SimpleXmlConfigure(config);
            configXml.addProperty("userName", "\r \n \" < > <[CDATA[ ]] Andory是江彬");
            configXml.addProperty("sex", "男");
            String xml = XMLFormatter.getInstance().format(configXml.getXmlProperties());
            System.out.println(xml);
            configXml.storeProperties(config);

            configXml = new SimpleXmlConfigure(config);
            System.out.println(configXml.getProperty("userName"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
