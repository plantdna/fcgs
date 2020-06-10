package com.pids.core.configure;

import com.pids.core.exception.ICoreException;

/**
 * 配置文件操作接口
 * @author Andory
 * @date 2012-5-25上午10:05:47
 */
public interface Configure<T> {
	/**
	 * 读取指定属性文件
	 * @author Andory
	 * @param key
	 * @return
	 * @date 2012-5-25上午10:05:58
	 */
	public String getProperty(String key);

	/**
	 * 添加配置属性
	 * @author Andory
	 * @param key
	 * @param value
	 * @date 2012-5-25上午08:59:24
	 */
	public abstract void addProperty(String key, String value);

	/**
	 * 删除配置属性
	 * @author Andory
	 * @param key 配置属性的键值
	 * @date 2012-5-25上午08:59:45
	 */
	public abstract void removeProperty(String key);

	/**
	 * <pre>给定属性参数是否存在，判定步骤：
	 * 	1、判定其普通属性值存在，返回true
	 * 	2、判定其操作系统平台依赖属性值存在，返回true
	 * 	3、否则返回false</pre>
	 * @author Andory
	 * @param key
	 * @return
	 * @date 2012-5-25上午10:44:48
	 */
	public abstract boolean exists(String key);

	/**
	 * 获取配置信息对象
	 * @author Andory
	 * @return
	 * @date 2012-5-24下午07:57:52
	 */
	public T getProperties();

	/**
	 * 设置配置信息对象
	 * @author Andory
	 * @param properties
	 * @date 2012-5-24下午07:58:07
	 */
	public void setProperties(T properties);

	/**
	 * 获取属性文件路径
	 * @author Andory
	 * @return
	 * @date 2012-5-25下午12:32:37
	 */
	public String getPropertiesFile();

	/**
	 * 设置属性文件路径
	 * @author Andory
	 * @param propertiesFile
	 * @date 2012-5-25下午12:32:53
	 */
	public void setPropertiesFile(String propertiesFile);

	/**
	 * 存储属性文件到存储介质上
	 * @author Andory
	 * @param filePath 文件路径
	 * @throws ICoreException
	 * @date 2012-5-25下午12:50:36
	 */
	public void storeProperties(String filePath) throws ICoreException;

	/**
	 * 加载属性文件
	 * @author Andory
	 * @throws ICoreException
	 * @date 2012-5-26上午11:24:53
	 */
	public boolean loadProperties() throws ICoreException;
}
