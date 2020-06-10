package com.pids.core.csv.saver;

import com.pids.core.pathcreator.PathDetail;

import java.util.List;

/**
 * 将给定数据保存到CSV文件中
 * @author jiang
 * @date 2018年9月1日下午10:59:24
 */
public interface CsvSaver<S> {
	/**
	 * 将给定数据保存到CSV文件中
	 * @author jiang
	 * @param sources
	 * @param filePath
	 * @return
	 * @date 2018年9月2日下午8:21:34
	 */
	public String save(List<S> sources, String filePath);

	/**
	 * 将给定数据保存到CSV文件中
	 * @author jiang
	 * @param sources
	 * @param detail
	 * @return
	 * @date 2018年9月2日下午8:22:35
	 */
	public String save(List<S> sources, PathDetail detail);

	/**
	 * 大批量数据分片保存到多个CSV文件中
	 * @author jiang
	 * @param sources
	 * @param detail
	 * @return
	 * @date 2018年9月2日下午8:22:35
	 */
	public List<String> sharding(List<S> sources, PathDetail detail);
}
