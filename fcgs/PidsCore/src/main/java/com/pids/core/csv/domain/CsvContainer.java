package com.pids.core.csv.domain;

import java.io.Serializable;

/**
 * CSV数据文件内容信息容器接口，定义了标题头和行数据信息
 * @author jiang
 * @date 2018年9月2日上午2:37:15
 */
public interface CsvContainer extends Serializable {

	/**
	 * 获取数据行数，与数据行数一致
	 * @author jiang
	 * @return
	 * @date 2018年9月2日上午2:40:14
	 */
	int getRowCount();

	/**
	 * 获取数据列宽，与标题头一致
	 * @author jiang
	 * @return
	 * @date 2018年9月2日上午2:40:17
	 */
	int getColCount();

	/**
	 * 设置行数据信息
	 * @author jiang
	 * @param rows
	 * @date 2018年9月2日上午2:40:19
	 */
	void setRows(String[][] rows);

	/**
	 * 获取行数据信息
	 * @author jiang
	 * @return
	 * @date 2018年9月2日上午2:40:21
	 */
	String[][] getRows();

	/**
	 * 设置标题头
	 * @author jiang
	 * @param titles
	 * @date 2018年9月2日上午2:40:24
	 */
	void setTitles(String[] titles);

	/**
	 * 获取标题头
	 * @author jiang
	 * @return
	 * @date 2018年9月2日上午2:40:26
	 */
	String[] getTitles();

}
