package com.pids.core.saver.utils;

import com.pids.core.exception.ICoreException;
import com.pids.core.i18n.IPidsCoreI18N;
import com.pids.core.utils.FileBuilder;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;

/**
 * XML保存器
 * @author Andory
 * @date 2012-5-25下午02:13:51
 */
public class XmlSaver implements IXmlSaver {
    private static IXmlSaver xmlSaver;

    @Override
    public boolean save(Element source, String targetPath) throws ICoreException {
        try {
            File targetFile = FileBuilder.build(targetPath, false);
            return save(source, targetFile);
        } catch (Exception e) {
            throw new ICoreException(IPidsCoreI18N.SSR_XMLSAVER_ERROR01.get(), e);
        }
    }

    @Override
    public boolean save(Element source, File targetFile) throws ICoreException {
        try {
            Document document = DocumentHelper.createDocument(source.createCopy());
            return save(document, targetFile);
        } catch (Exception e) {
            throw new ICoreException(IPidsCoreI18N.SSR_XMLSAVER_ERROR01.get(), e);
        }
    }

    @Override
    public boolean save(Document document, File targetFile) throws ICoreException {
        try {
            document.setXMLEncoding("UTF-8");
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter(new FileWriter(targetFile), format);
            writer.write(document);
            writer.close();
            return true;
        } catch (Exception e) {
            throw new ICoreException(IPidsCoreI18N.SSR_XMLSAVER_ERROR01.get(), e);
        }
    }

    /**
     * 获取xml保存器的实例
     * @return
     * @author jiangbin
     * @date 2014年3月13日下午4:07:13
     */
    public static IXmlSaver getInstance() {
        if (xmlSaver == null) {
            xmlSaver = new XmlSaver();
        }
        return xmlSaver;
    }

}
