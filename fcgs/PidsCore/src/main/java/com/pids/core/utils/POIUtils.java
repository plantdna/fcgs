package com.pids.core.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;

/**
 * Poi套件相关工具方法
 * @author jiangbin
 * @date 2012-11-9下午1:58:03
 */
public class POIUtils {
	/**
	 * 创建POI的Excel2003实例
	 * @author jiangbin
	 * @return
	 * @date 2012-11-9下午1:57:43
	 */
	public static Workbook createExcel() {
		return new HSSFWorkbook();
	}

	/**
	 * 使用给定的输入流创建POI的Excel2003实例，若创建失败则返回null
	 * @author jiangbin
	 * @return
	 * @date 2012-11-9下午1:57:43
	 */
	public static Workbook createExcel(InputStream inputStream) {
		try {
			return new HSSFWorkbook(inputStream);
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * 清空Excel2003内的Sheet
	 * @author jiangbin
	 * @param excel 若给定null值则会自动创建一个新Excel2003对象
	 * @return
	 * @date 2014年8月22日上午2:26:58
	 */
	public static Workbook clear(Workbook excel) {
		if (excel == null) {
			return createExcel();
		}
		int count = excel.getNumberOfSheets();
		if (count < 1) {
			return excel;
		}
		for (int index = count - 1; index >= 0; index--) {
			excel.removeSheetAt(index);
		}
		return excel;
	}

	/**
	 * 创建POI的Excel2007实例
	 * @author jiangbin
	 * @return
	 * @date 2012-11-9下午1:57:43
	 */
	public static Workbook createExcel2007() {
		return new XSSFWorkbook();
	}

	/**
	 * 使用给定的输入流创建POI的Excel2007实例，若创建失败则返回null
	 * @author jiangbin
	 * @return
	 * @date 2012-11-9下午1:57:43
	 */
	public static Workbook createExcel2007(InputStream inputStream) {
		try {
			return new XSSFWorkbook(inputStream);
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * 清空Excel2007内的Sheet
	 * @author jiangbin
	 * @param excel 若给定null值则会自动创建一个新Excel2007对象
	 * @return
	 * @date 2014年8月22日上午2:26:58
	 */
	public static Workbook clearExcel2007(Workbook excel) {
		if (excel == null) {
			return createExcel2007();
		}
		int count = excel.getNumberOfSheets();
		if (count < 1) {
			return excel;
		}
		for (int index = count - 1; index >= 0; index--) {
			excel.removeSheetAt(index);
		}
		return excel;
	}

}
