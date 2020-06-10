package com.fcgs.core.comparer.domain;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.*;

/**
 * 指纹比对结果集中有结果数据的行列索引号映射表
 *
 * @author jiangbin
 * @date 2019/11/16 2:21 下午
 **/

public class HasResultIndexMapper implements Serializable {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private static final long serialVersionUID = 1L;
	private Map<Integer, Integer> rows;
	private Map<Integer, Integer> cols;

	public HasResultIndexMapper() {
		this.rows = new LinkedHashMap<>();
		this.cols = new LinkedHashMap<>();
	}

	/**
	 * 获取存在数据的所有行索引号映射表
	 *
	 * @return java.util.Map<java.lang.Integer, java.lang.Integer>
	 * @author jiangbin
	 * @date 2019/11/16 3:08 下午
	 **/
	public Map<Integer, Integer> getRows() {
		return rows;
	}

	/**
	 * 获取存在数据的所有列索引号映射表
	 *
	 * @return java.util.Map<java.lang.Integer, java.lang.Integer>
	 * @author jiangbin
	 * @date 2019/11/16 3:08 下午
	 **/
	public Map<Integer, Integer> getCols() {
		return cols;
	}

	/**
	 * 判定给定行上是否存在数据
	 *
	 * @param row 行索引号
	 * @return boolean
	 * @author jiangbin
	 * @date 2019/11/16 3:08 下午
	 **/
	public boolean hasRowData(int row) {
		return rows.get(row) != null;
	}

	/**
	 * 判定给定列上是否存在数据
	 *
	 * @param col 列索引号
	 * @return boolean
	 * @author jiangbin
	 * @date 2019/11/16 3:08 下午
	 **/
	public boolean hasColData(int col) {
		return cols.get(col) != null;
	}

	/**
	 * 添加存在数据的行索引号
	 *
	 * @param row 行索引号
	 * @return void
	 * @author jiangbin
	 * @date 2019/11/16 3:07 下午
	 **/
	public void addRow(int row) {
		rows.put(row, row);
	}

	/**
	 * 添加存在数据的列索引号
	 *
	 * @param col 列索引号
	 * @return void
	 * @author jiangbin
	 * @date 2019/11/16 3:07 下午
	 **/
	public void addCol(int col) {
		cols.put(col, col);
	}

	/**
	 * 添加指纹比对结果集，以便过滤出所有存在数据的行和列索引号
	 *
	 * @param result
	 * @return void
	 * @author jiangbin
	 * @date 2019/11/16 3:07 下午
	 **/
	public void add(GeneResultSet result) {
		StopWatch watch = new StopWatch();
		watch.start();
		int rowCount = result.getRow();
		int colCount = result.getCol();
		for (int row = 0; row < rowCount; row++) {
			for (int col = 0; col < colCount; col++) {
				int diff = result.getDiff(row, col);
				if (result.valid(diff)) {
					addRow(row);
					addCol(col);
				}
			}
		}
		watch.stop();
		log.info("标记有指纹数据的行列索引号耗时(ms)==>" + watch.getTime());
	}

	/**
	 * 获取存在数据的行索引号列表
	 *
	 * @return java.util.List<java.lang.Integer>
	 * @author jiangbin
	 * @date 2019/11/16 3:07 下午
	 **/
	public List<Integer> getRowList() {
		List<Integer> rows = new ArrayList<>(this.rows.values());
		if (CollectionUtils.isNotEmpty(rows)) {
			Collections.sort(rows);
		}
		return rows;
	}

	/**
	 * 获取存在数据的列索引号列表
	 *
	 * @return java.util.List<java.lang.Integer>
	 * @author jiangbin
	 * @date 2019/11/16 3:06 下午
	 **/
	public List<Integer> getColList() {
		List<Integer> cols = new ArrayList<>(this.cols.values());
		if (CollectionUtils.isNotEmpty(cols)) {
			Collections.sort(cols);
		}
		return cols;
	}
}
