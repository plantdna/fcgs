package com.pids.core.loader.impl;

import com.pids.core.exception.ICoreException;
import com.pids.core.i18n.PidsCoreI18N;
import com.pids.core.utils.StringEx;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * Excel文件加载器,本加载器会自动识别excel类型来构建正确的excel处理对象,目前支持2003和2007,
 * 通常我们不建议自己去手动加载excel,而应用此加载器来构建，防止造成兼容性问题
 * @author Andory
 * @date 2012-7-27上午01:44:45
 */
public class ExcelLoader implements ExcelLoaderSupport {

    @Override
    public Workbook load(String excelPath) throws ICoreException {
        if (StringEx.isNull(excelPath)) {
            return null;
        }
        return load(new File(excelPath));
    }

    @Override
    public Workbook load(File excelFile) throws ICoreException {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(excelFile);
            return load(inputStream);
        } catch (Exception e) {
            throw new ICoreException(String.format(PidsCoreI18N.VIA_EXCELLOADER_ERROR01.get(), excelFile.getName()), e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new ICoreException(e);
                }
            }
        }
    }

    @Override
    public Workbook load(InputStream excelInputStream) throws ICoreException {
        try {
            return createExcel(excelInputStream);
        } catch (Exception e) {
            throw new ICoreException(e);
        } finally {
            if (excelInputStream != null) {
                try {
                    excelInputStream.close();
                } catch (IOException e) {
                    throw new ICoreException(e);
                }
            }
        }
    }

    /**
     * 自动判定并创建正确的excel处理对象
     * @param inputStream excel文件流
     * @return Workbook对象
     * @throws ICoreException
     * @author Andory
     * @date 2012-8-2下午11:30:31
     */
    protected Workbook createExcel(InputStream inputStream) throws ICoreException {
        try {
            // 首先判断流是否支持mark和reset方法，最后两个if分支中的方法才能支持
            if (!inputStream.markSupported()) {
                // 还原流信息
                inputStream = new PushbackInputStream(inputStream, inputStream.available());
            }
            // EXCEL2003使用的是微软的文件系统
            if (isExcel2003(inputStream)) {
                return new HSSFWorkbook(inputStream);
            }
            // EXCEL2007使用的是OOM文件格式
            if (isExcel2007(inputStream)) {
                // 可以直接传流参数，但是推荐使用OPCPackage容器打开
                return new XSSFWorkbook(OPCPackage.open(inputStream));
            }
            throw new IOException(PidsCoreI18N.VIA_EXCELLOADER_ERROR02.get());
        } catch (Exception e) {
            throw new ICoreException(e);
        }
    }

    protected boolean isExcel2003(InputStream inputStream) throws IOException {
        return POIFSFileSystem.hasPOIFSHeader(inputStream);
    }

    protected boolean isExcel2007(InputStream inputStream) throws IOException {
        return POIXMLDocument.hasOOXMLHeader(inputStream);
    }

}
