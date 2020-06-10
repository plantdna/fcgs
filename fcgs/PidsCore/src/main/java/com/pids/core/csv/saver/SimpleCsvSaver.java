package com.pids.core.csv.saver;

import com.pids.core.csv.creator.CsvCreator;
import com.pids.core.csv.handler.CsvCreatorHandler;

/**
 * 通用的CSV数据保存器实现类
 * @author jiang
 * @date 2018年9月2日下午8:28:24
 */
public class SimpleCsvSaver<S> extends CsvSaverTemplate<S> implements CsvCreatorHandler<S> {
	private CsvCreator<S> csvCreator;

	@Override
	public CsvCreator<S> getCsvCreator() {
		return csvCreator;
	}

	@Override
	public void setCsvCreator(CsvCreator<S> csvCreator) {
		this.csvCreator = csvCreator;
	}

}
