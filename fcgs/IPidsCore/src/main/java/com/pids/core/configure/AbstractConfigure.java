package com.pids.core.configure;

import com.pids.core.utils.OSInfo;
import org.apache.commons.lang3.StringUtils;

/**
 * 属性文件抽象类，本类提供方法允许用户配置具有操作系统平台依赖性的属性，
 * 获取这些属性时会自动根据当前操作系统类型取得对应属性，这种方式通常很有用，
 * 这样可以屏蔽掉一些开发及部署环境问题，操作系统判定逻辑由OSInfo类来决定，
 * 且平台相关的属性的后缀名也由该类OSInfo.getPropSuffix()获取，默认定义了Windows和Linux系统后缀
 * @author Andory
 * @date 2012-5-26下午03:06:34
 */
public abstract class AbstractConfigure<T> implements Configure<T> {
	private T properties;
	private String propertiesFile;

	/**
	 * 获取具有操作系统平台信赖性的参数值,
	 * 这种参数主要是在其后加上:.LINUX和.WINDOWS进行区分,
	 * 在调用于自动判定系统类型并调用正确的参数,
	 * 通常本方法应用于一些系统路径等参数的配置
	 * @author jiangbin
	 * @param key 属性名
	 * @return
	 * @date 2012-4-30下午2:10:30
	 */
	protected final String getOSDependProperty(String key) {
		return getPropertyValue(getOsDependKey(key));
	}

	@Override
	public final String getProperty(String key) {
		String value = getPropertyValue(key);
		if (StringUtils.isBlank(value)) {
			value = getOSDependProperty(key);
		}
		return value;
	}

	protected final String getOsDependKey(String key) {
		return key + OSInfo.getPropSuffix();
	}

	@Override
	public void setProperties(T properties) {
		this.properties = properties;
	}

	@Override
	public T getProperties() {
		return properties;
	}

	@Override
	public void setPropertiesFile(String propertiesFile) {
		this.propertiesFile = propertiesFile;
	}

	@Override
	public String getPropertiesFile() {
		return propertiesFile;
	}

	@Override
	public final boolean exists(String key) {
		return StringUtils.isBlank(getProperty(key));
	}

	/**
	 * 获取默认属性文件路径
	 * @author Andory
	 * @return
	 * @date 2012-5-25下午12:35:52
	 */
	public abstract String getDefaultPropertiesFile();

	/**
	 * 获取指定属性值
	 * @author Andory
	 * @param key 属性键值
	 * @return
	 * @date 2012-5-26下午03:04:23
	 */
	protected abstract String getPropertyValue(String key);
}
