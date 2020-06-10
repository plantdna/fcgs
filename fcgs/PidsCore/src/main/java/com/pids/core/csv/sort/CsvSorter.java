package com.pids.core.csv.sort;

import com.pids.core.exception.ICoreException;

/**
 * CSV数据排序工具
 * @author jiang
 * @date 2018年9月2日下午6:11:35
 */
public interface CsvSorter {
	/**
	 * 对Csv数据进行排序，该方法会将排序后的Csv数据回写到磁盘上的Csv文件，
	 * 默认回写到给定Csv路径对应的磁盘文件
	 * @author jiangbin
	 * @param csvPath Csv的磁盘存储路径
	 * @return 排序后Csv的磁盘存储路径
	 * @throws ICoreException
	 * @date 2014年8月22日上午2:18:38
	 */
	public String sort(String csvPath) throws ICoreException;

	/**
	 * 对Csv数据进行排序
	 * @author jiangbin
	 * @param csv csv数据
	 * @return 排序后的Csv数据
	 * @throws ICoreException
	 * @date 2014年8月22日上午2:18:54
	 */
	public String sortRows(String csvContent) throws ICoreException;
}
