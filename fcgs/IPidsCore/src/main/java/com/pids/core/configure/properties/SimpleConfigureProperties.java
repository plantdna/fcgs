package com.pids.core.configure.properties;

import com.pids.core.exception.ICoreException;

/**
 * 属性文件(".properties")操作类通用实现类
 * @author Andory
 * @date 2012-5-25上午11:48:14
 */
public class SimpleConfigureProperties extends ConfigureProperties {

	public SimpleConfigureProperties(String propertiesFile) throws ICoreException {
		super(propertiesFile);
	}

	@Override
	public String getDefaultPropertiesFile() {
		return null;
	}

	public static void main(String[] args) {
		try {
			SimpleConfigureProperties properties = new SimpleConfigureProperties("/msswms.properties");
			String value = properties.getProperty("FILEFOLDER");
			System.out.println(value);
		} catch (ICoreException e) {
			e.printStackTrace();
		}

	}
}
