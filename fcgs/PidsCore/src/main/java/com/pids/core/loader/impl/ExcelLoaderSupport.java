package com.pids.core.loader.impl;

import com.pids.core.exception.ICoreException;
import com.pids.core.loader.Loader;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * 提供excel加载功能支持的接口层
 * @author jiangbin
 * @date 2012-8-10下午1:10:28
 */
public interface ExcelLoaderSupport extends Loader<InputStream, Workbook> {
	/**
	 * excel的版本
	 * @author Andory
	 * @date 2012-8-2下午11:10:31
	 */
	public enum EXCEL_VERSION {
		EXCEL2003, EXCEL2007
	}

	/**
	 * 加载指定Excel文件
	 * @author jiangbin
	 * @param excelPath excel文件路径
	 * @return
	 * @throws ICoreException
	 * @date 2012-8-3下午5:03:02
	 */
	public abstract Workbook load(String excelPath) throws FileNotFoundException;

	/**
	 * 加载指定excel文件
	 * @author jiangbin
	 * @param excelFile excel文件对象
	 * @return
	 * @throws ICoreException
	 * @date 2012-8-3下午5:03:20
	 */
	public abstract Workbook load(File excelFile) throws FileNotFoundException;

}