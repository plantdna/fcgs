package com.pids.core.configure.xml;

import com.pids.core.exception.ICoreException;
import com.pids.core.i18n.IPidsCoreI18N;
import com.pids.core.saver.utils.XmlSaver;
import com.pids.core.utils.FileBuilder;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * XML格式配置文件抽象实现类，具备读写功能
 * @author Andory
 */
public abstract class AbstractXmlConfigure extends XmlConfigure {
    public AbstractXmlConfigure() throws ICoreException {
        try {
            loadProperties();
        } catch (Exception e) {
            throw new ICoreException(e);
        }
    }

    public AbstractXmlConfigure(String propertiesFile) throws ICoreException {
        try {
            setPropertiesFile(propertiesFile);
            loadProperties();
        } catch (Exception e) {
            throw new ICoreException(e);
        }
    }

    @Override
    public boolean loadProperties() throws ICoreException {
        if (StringUtils.isBlank(getPropertiesFile())) {
            setPropertiesFile(getDefaultPropertiesFile());
        }
        setProperties(new HashMap<String, String>());
        try {
            File targetFile = new File(getPropertiesFile());
            if (targetFile.exists()) {
                Element element = loadXML();
                if (element != null) {
                    setXmlProperties(element);
                }
            }
        } catch (Exception ex) {
            throw new ICoreException(IPidsCoreI18N.SSR_ABSTRACTXMLCONFIGURE_ERROR01.get(), ex);
        }
        return true;
    }

    /**
     * 加载XML内容
     * @throws Exception
     * @author Andory
     * @date 2012-5-25下午02:46:49
     */
    protected Element loadXML() throws Exception {
        try {
            SAXReader reader = new SAXReader();
            File file = FileBuilder.build(getPropertiesFile(), false);
            if (file.length() > 0) {
                Document document = reader.read(file);
                if (document != null && document.getRootElement() != null) {
                    return document.getRootElement();
                }
            }
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void addProperty(String key, String value) {
        getProperties().put(key, value);
    }

    @Override
    public void removeProperty(String key) {
        getProperties().remove(key);
    }

    @Override
    public String getPropertyValue(String key) {
        return getProperties().get(key);
    }

    /**
     * 清空参数
     */
    public void clearProperties() {
        getProperties().clear();
    }

    @Override
    public Element fillProperties(Map<String, String> properties) {
        Element element = DocumentHelper.createElement(getRootName());
        for (String key : getProperties().keySet()) {
            String value = getProperty(key);
            if (!StringUtils.isBlank(key) && !StringUtils.isBlank(value)) {
                element.add(createPropertyElement(key, value));
            }
        }
        return element;
    }

    @Override
    public Map<String, String> parserProperties(Element rootElement) {
        Map<String, String> map = new HashMap<String, String>();
        if (rootElement != null) {
            List<Element> elements = rootElement.elements();
            for (Element element : elements) {
                String[] tmp = parserPropertyElement(element);
                if (tmp != null && !StringUtils.isBlank(tmp[0]) && !StringUtils.isBlank(tmp[1])) {
                    map.put(tmp[0], tmp[1]);
                }
            }
        }
        return map;
    }

    @Override
    public void storeProperties(String filePath) throws ICoreException {
        try {
            Element element = getXmlProperties();
            XmlSaver.getInstance().save(element, filePath);
        } catch (Exception e) {
            throw new ICoreException(IPidsCoreI18N.SSR_ABSTRACTXMLCONFIGURE_ERROR02.get(), e);
        }
    }

    /**
     * 根结点名称
     * @return
     * @author Andory
     * @date 2012-5-25上午10:39:03
     */
    protected abstract String getRootName();

    /**
     * 创建参数结点
     * @param key
     * @param value
     * @return
     */
    protected abstract Element createPropertyElement(String key, String value);

    /**
     * 解析参数结点数据
     * @param element
     * @return 参数名和值:[key,value]
     */
    protected abstract String[] parserPropertyElement(Element element);

}
