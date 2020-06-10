package com.pids.core.pathcreator;

/**
 * <pre>路径构建接口,此构建器生成的目录格式为：
 * 文件路径：主目录/一级子目录/年份/月日/时间/[文件名前缀_]8位随机号.扩展名
 * 目录路径:主目录/一级子目录/年份/月日/时间/[目录名前缀_]8位随机号[扩展名]
 * 如：
 * 文件路径：D:/work/mditip/upload/SsrGeneFiles/2014/0220/050338/GeneDataDF4E4F83F8B74FC2.xml
 * 目录路径：D:/work/mditip/upload/SsrGeneFiles/2014/0220/050338/GeneDataDF4E4F83F8B74FC2xml
 * 并且本接口类提供获取除主目录外的相对路径的方法</pre>
 * @author jiangbin
 * @date 2012-1-9下午6:09:56
 */
public interface PathCreator extends PathDetail, RelativePath {
	/**
	 * <pre>构建标准存储路径，
	 * 本方法默认创建的是文件路径，通过设置{@link #setCreateFolder(boolean)}方法来设置创建的路径类型
	 * </pre>
	 * @author jiangbin
	 * @return
	 * @date 2012-1-11下午3:32:51
	 */
	public String create();

	/**
	 * 创建目录或文件路径
	 * @author jiangbin
	 * @param isCreatFolder true/false--创建目录/文件
	 * @return
	 * @date 2014年4月14日上午11:37:30
	 */
	public String create(boolean isCreatFolder);

	/**
	 * 通过相对路径获取全路径
	 * @author Andory
	 * @param relativePath 该路径是除去主存储目录的部分：子目录+日期目录+扩展名
	 * @return
	 * @date 2012-7-7下午03:04:17
	 */
	public abstract String getFilePath(String relativePath);

	/**
	 *<pre> 构建相对路径部分：子目录+日期路径+[文件前缀]+8位UUID+扩展名,
	 * 示例如:sysUpload/2012/0302/023828/a9850d8cxxx.jpg
	 * 含义:sysUpload-->子目录,用于区分不同类型文件
	 *      2012/0302/023828/a9850d8c-->日期路径，格式为:年份/月日/时分秒/8位UUID
	 *      xxx.jpg-->文件扩展名类型，xxx用于区分同类文件扩展名的不同应用</pre>
	 * @author Andory
	 * @return
	 * @date 2012-7-7下午03:09:56
	 */
	public abstract String createRelativePath();

}
