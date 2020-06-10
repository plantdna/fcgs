package com.pids.core.saver.impl;

import com.pids.core.exception.ICoreException;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 保存Excel到磁盘
 * @author Andory
 * @date 2012-7-26下午07:21:04
 */
public class ExcelSaver extends FileSaverTemplate<Workbook> {

	@Override
	protected boolean saveDetail(Workbook source, OutputStream outputStream) throws ICoreException {
		try {
			source.write(outputStream);
			return true;
		} catch (IOException e) {
			throw new ICoreException(e);
		}
	}
}
