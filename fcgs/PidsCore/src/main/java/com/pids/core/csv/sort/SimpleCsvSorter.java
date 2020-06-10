package com.pids.core.csv.sort;

import com.pids.core.csv.creator.CsvCreator;
import com.pids.core.csv.parser.CsvParser;

import java.util.Comparator;

/**
 * CSV数据排序组件通用实现类
 * @author jiang
 * @date 2018年9月2日下午6:35:52
 */
public class SimpleCsvSorter<T> extends CsvSorterTemplate<T> {
	private CsvParser<T> csvParser;
	private CsvCreator<T> csvCreator;
	private Comparator<T> comparer;

	@Override
	public CsvParser<T> getCsvParser() {
		return csvParser;
	}

	public void setCsvParser(CsvParser<T> csvParser) {
		this.csvParser = csvParser;
	}

	@Override
	public CsvCreator<T> getCsvCreator() {
		return csvCreator;
	}

	public void setCsvCreator(CsvCreator<T> csvCreator) {
		this.csvCreator = csvCreator;
	}

	@Override
	public Comparator<T> getComparer() {
		return comparer;
	}

	public void setComparer(Comparator<T> comparer) {
		this.comparer = comparer;
	}

}
