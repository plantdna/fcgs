package com.pids.core.utils;

import com.pids.core.exception.ICoreException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;

/**
 * 指定类文件同级包目录下的资源文件读取器，
 * 即资源文件必需与指定的类在同一个包目录下才能读取到
 * @author jiangbin
 * @date 2015年1月17日下午7:51:19
 */
public class ResourceReader {
	/**
	 * 读取资源文件的字符串内容
	 * @author jiangbin
	 * @param fileName 任意文件名，如:a.txt，
	 * 读取类目录外的文件要使用包名全路径格式:/com/viathink/core/datasource/test.txt
	 * @return
	 * @date 2015年1月17日下午7:45:01
	 */
	public static String readString(Class<?> clazz, String fileName) {
		return new String(readBytes(clazz, fileName));
	}

	/**
	 * 读取资源文件内容的字节数组
	 * @author jiangbin
	 * @param fileName 任意文件名，如:a.txt,
	 * 读取类目录外的文件要使用包名全路径格式:/com/viathink/core/datasource/test.txt
	 * @return
	 * @date 2015年1月17日下午7:46:57
	 */
	public static byte[] readBytes(Class<?> clazz, String fileName) {
		try {
			InputStream input = readStream(clazz, fileName);
			byte[] buffer = new byte[input.available()];
			IOUtils.read(input, buffer);
			return buffer;
		} catch (IOException e) {
			throw new ICoreException(e);
		}
	}

	/**
	 * 读取资源文件输入流
	 * @author jiangbin
	 * @param fileName 任意文件名，如:a.txt,
	 * 读取类目录外的文件要使用包名全路径格式:/com/viathink/core/datasource/test.txt
	 * @return
	 * @date 2015年1月17日下午7:50:16
	 */
	public static InputStream readStream(Class<?> clazz, String fileName) {
		return clazz.getResourceAsStream(fileName);
	}

	/**
	 * 获取资源的URL连接对象
	 * @author jiangbin
	 * @param resourceName 资源名称， 如:comm.properties
	 * @return
	 * @throws IOException
	 * @date 2016年8月28日下午5:18:35
	 */
	public static URLConnection get(String resourceName) throws IOException {
		if(StringUtils.isBlank(resourceName)){
			throw new ICoreException("");
		}
		ClassLoader clToUse = getDefaultClassLoader();
		Enumeration<URL> urls = clToUse.getResources(resourceName);
		while (urls.hasMoreElements()) {
			URL url = urls.nextElement();
			URLConnection con = url.openConnection();
			useCachesIfNecessary(con);
			return con;
		}
		return null;
	}

	/**
	 * Return the default ClassLoader to use: typically the thread context
	 * ClassLoader, if available; the ClassLoader that loaded the ClassUtils
	 * class will be used as fallback.
	 * <p>Call this method if you intend to use the thread context ClassLoader
	 * in a scenario where you absolutely need a non-null ClassLoader reference:
	 * for example, for class path resource loading (but not necessarily for
	 * {@code Class.forName}, which accepts a {@code null} ClassLoader
	 * reference as well).
	 * @return the default ClassLoader (never {@code null})
	 * @see Thread#getContextClassLoader()
	 */
	public static ClassLoader getDefaultClassLoader() {
		ClassLoader cl = null;
		try {
			cl = Thread.currentThread().getContextClassLoader();
		}
		catch (Throwable ex) {
			// Cannot access thread context ClassLoader - falling back to system class loader...
		}
		if (cl == null) {
			// No thread context class loader -> use class loader of this class.
			cl = ResourceReader.class.getClassLoader();
		}
		return cl;
	}

	/**
	 * Set the {@link URLConnection#setUseCaches "useCaches"} flag on the
	 * given connection, preferring {@code false} but leaving the
	 * flag at {@code true} for JNLP based resources.
	 * @param con the URLConnection to set the flag on
	 */
	public static void useCachesIfNecessary(URLConnection con) {
		con.setUseCaches(con.getClass().getSimpleName().startsWith("JNLP"));
	}

	/**
	 * 获取资源文件的实际文件对象
	 * @author jiangbin
	 * @param resourceName
	 * @return
	 * @throws IOException
	 * @date 2016年8月28日下午6:33:46
	 */
	public static File getResourceFile(String resourceName) throws IOException {
		URLConnection connection = ResourceReader.get(resourceName);
		if (connection == null) {
			return null;
		}
		return new File(connection.getURL().getPath());
	}
}
