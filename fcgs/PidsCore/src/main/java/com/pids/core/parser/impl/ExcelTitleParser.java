package com.pids.core.parser.impl;

import com.pids.core.exception.ICoreException;
import com.pids.core.parser.Parser;
import com.pids.core.utils.StringEx;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Excel标题解析器
 * @author jiangbin
 * @date 2013-4-2上午9:51:42
 */
public class ExcelTitleParser implements Parser<Workbook, String[]> {
	/**
	 * 解析出给定Excel的第一个 Sheet的第一行数据
	 * @author jiangbin
	 * @param excel
	 * @return
	 * @throws ParserException
	 * @date 2013-4-2上午9:51:56
	 */
	@Override
	public String[] parser(Workbook excel) throws ICoreException {
		Sheet sheet = excel.getSheetAt(0);
		int rowCount = sheet.getPhysicalNumberOfRows();
		if (rowCount == 0) {
			return null;
		}
		Row row = sheet.getRow(0);
		if (row == null || row.getPhysicalNumberOfCells() == 0) {
			return null;
		}
		int colCount = row.getPhysicalNumberOfCells();
		String[] titles = new String[colCount];
		for (int i = 0; i < colCount; i++) {
			titles[i] = StringEx.sNull(row.getCell(i));
		}
		return titles;
	}
}
