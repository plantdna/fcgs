package com.pids.core.configure.properties;

import com.pids.core.configure.AbstractConfigure;
import com.pids.core.exception.ICoreException;
import com.pids.core.i18n.IPidsCoreI18N;
import com.pids.core.utils.FileBuilder;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 属性对象抽象类
 * @author Andory
 * @date 2012-5-24下午07:51:49
 */
public abstract class ConfigureProperties extends AbstractConfigure<Properties> {

    public ConfigureProperties() throws ICoreException {
        loadProperties();
    }

    public ConfigureProperties(String propertiesFile) throws ICoreException {
        try {
            setPropertiesFile(propertiesFile);
            loadProperties();
        } catch (Exception e) {
            throw new ICoreException(e);
        }
    }

    @Override
    public boolean loadProperties() throws ICoreException {
        try {
            if (StringUtils.isBlank(getPropertiesFile())) {
                setPropertiesFile(getDefaultPropertiesFile());
            }
            Properties properties = new Properties();
            properties.load(getClass().getResourceAsStream(getPropertiesFile()));
            setProperties(properties);
            return true;
        } catch (Exception e) {
            throw new ICoreException(String.format(IPidsCoreI18N.SSR_CONFIGUREPROPERTIES_ERROR01.get(), getPropertiesFile()), e);
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
        return getProperties().getProperty(key);
    }

    @Override
    public void storeProperties(String filePath) throws ICoreException {
        FileOutputStream outputStream = null;
        try {
            File file = FileBuilder.build(filePath, false);
            outputStream = new FileOutputStream(file);
            getProperties().store(outputStream, "");
            outputStream.flush();
        } catch (Exception e) {
            throw new ICoreException(IPidsCoreI18N.SSR_CONFIGUREPROPERTIES_ERROR02.get(), e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 默认属性文件路径如:/dmt.properties
     * @return
     * @author Andory
     * @date 2012-5-24上午10:44:08
     */
    @Override
    public abstract String getDefaultPropertiesFile();
}
