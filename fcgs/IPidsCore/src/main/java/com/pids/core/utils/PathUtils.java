package com.pids.core.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 路径工具处理类
 * @author jiangbin
 * @date 2014年3月12日下午1:42:47
 */
public class PathUtils {
	
	/**
	 * @Description: 将windows格式目录转换成java格式目录,如:d:\\ss.txt-->d://ss.txt
	 * @author: jiangbin
	 * @param dirStr
	 * @return
	 * @date: 2011-8-18下午02:29:08
	 */
	public static String convertWinDir(String dirStr) {
		String target = StringUtils.stripToEmpty(dirStr);
		return target.replaceAll("\\\\", "/");
	}
	
	/**
	 * 获取WEB项目下的classes目录实际服务器路径
	 * @author jiangbin
	 * @return
	 * @date 2013-4-7上午11:16:05
	 */
	public static String getClassesPath() {
		//必需先创建新实例再调用getClass()方法
		PathUtils utils = new PathUtils();
		return utils.getClass().getClassLoader().getResource("").getPath();
	}
	
	/**
	 * 获取WEB项目下的classes目录里指定相对路径文件的实际服务器路径
	 * @author jiangbin
	 * @return
	 * @date 2013-4-7上午11:16:05
	 */
	public static String getClassesPath(String relativePath) {
		String classesPath = getClassesPath();
		String targetPath = String.format("%s/%s", classesPath, relativePath);
		return convertWinDir(targetPath);
	}
	
	/**
	 * <pre>获取指定类的包目录实际存储路径,如{@link PathUtils}类的包路径为：
	 * D:/Workspace/iCore3/target/classes/com/viathink/core/utils/
	 * </pre>
	 * @author jiangbin
	 * @param clazz
	 * @return
	 * @date 2014年3月17日上午11:24:11
	 */
	public static String getClassPackage(Class<?> clazz) {
		return clazz.getResource(".").getPath();
	}
	
	/**
	 * <pre>获取指定全路径中除去主目录路径外的相对路径，
	 * 如：全路径为：C://d-xx/sdfadf/aa/b.txt,主目录为：C://d-xx/sdfadf,
	 * 则本方法将取得的相对路径为：aa/b.txt</pre>
	 * @author jiangbin
	 * @param fullPath 全路径
	 * @param mainFolder 主目录
	 * @return
	 * @date 2015年4月11日下午6:24:55
	 */
	public static String getRelativePath(final String fullPath, final String mainFolder) {
		if (StringUtils.isBlank(fullPath) || StringUtils.isBlank(mainFolder)) {
			return null;
		}
		String _fullPath = convertWinDir(fullPath);
		String _mainFolder = convertWinDir(mainFolder);
		int index = _fullPath.indexOf(_mainFolder);
		if (index != 0) {
			return null;
		}
		String relativePath = _fullPath.replaceFirst(_mainFolder, "");
		if (StringUtils.isBlank(relativePath)) {
			return null;
		}
		if (relativePath.charAt(0) == '/') {
			return relativePath.substring(1);
		}
		return relativePath;
	}

	/**
	 * 获取当前调用此方法的文件的全路径信息
	 *
	 * @return java.lang.String
	 * <br/><br/>
	 * Create by WuHaotian on 2020-05-25 16:11:32
	 **/
	public static String getCurrentFilePath(Class<?> clazz){
		return clazz.getProtectionDomain().getCodeSource().getLocation().getPath();
	}
	
	public static void main(String[] args) {
		String fullPath = "C://d-xx/sdfadf/b.txt";
		String mainFolder = "C://d-xx/sdfadf";
		System.out.println(getRelativePath(fullPath, mainFolder));
		System.out.println(getRelativePath(fullPath, "d-xx"));
		System.out.println(getRelativePath(fullPath, "c"));
	}
}
