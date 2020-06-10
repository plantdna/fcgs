package com.pids.core.csv.utils;

import au.com.bytecode.opencsv.CSVWriter;
import com.pids.core.exception.ICoreException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * CSV工具类
 *
 * @author jiang
 * @date 2018年9月2日上午12:11:14
 */
public class CsvUtils {
    private static Logger logger = LoggerFactory.getLogger(CsvUtils.class);
    public static final String CSV_EXT = "csv";
    public static final String CSV_ENCODING = ByteOrderMark.UTF_8.getCharsetName();
    public static final byte[] CSV_BOM = ByteOrderMark.UTF_8.getBytes();

    /**
     * 添加CSV BOM(Bype Order Mark)
     *
     * @param csvStr
     * @return
     * @author jiangbin
     * @date 2018年9月3日下午2:42:17
     */
    public static String addCsvBOM(String csvStr) {
        return new String(CSV_BOM) + csvStr;
    }

    /**
     * 字符串数组转换成CSV格式字符串(以逗号作分隔符)
     *
     * @param items
     * @return
     * @author jiang
     * @date 2018年9月2日上午12:09:32
     */
    public static String toCsvStr(String[] items) {
        if (ArrayUtils.isEmpty(items)) {
            return "";
        }
        try {
            StringWriter writer = new StringWriter();
            CSVWriter csvWriter = new CSVWriter(writer);
            List<String[]> rows = new ArrayList<>();
            rows.add(items);
            csvWriter.writeAll(rows);
            csvWriter.close();
            return writer.getBuffer().toString();
        } catch (IOException e) {
            logger.error("构建CSV行数据失败!", e);
            return "";
        }
    }

    /**
     * 字符串数组转换成CSV格式字符串(以逗号作分隔符)
     *
     * @param items
     * @return
     * @author jiang
     * @date 2018年9月2日上午12:09:32
     */
    public static String toCsvStr(String[][] items) {
        if (ArrayUtils.isEmpty(items)) {
            return "";
        }
        try {
            StringWriter writer = new StringWriter();
            CSVWriter csvWriter = new CSVWriter(writer);
            List<String[]> rows = new ArrayList<>();
            for (String[] item : items) {
                if (ArrayUtils.isNotEmpty(item)) {
                    rows.add(item);
                }
            }
            csvWriter.writeAll(rows);
            csvWriter.close();
            return writer.getBuffer().toString();
        } catch (IOException e) {
            logger.error("构建CSV行数据失败!", e);
            return "";
        }
    }

    /**
     * 字符串数组转换成CSV格式字符串(以逗号作分隔符)
     *
     * @param items
     * @return
     * @author jiang
     * @date 2018年9月2日上午12:09:32
     */
    public static String toCsvStr(List<String[]> items) {
        if (CollectionUtils.isEmpty(items)) {
            return "";
        }
        try {
            StringWriter writer = new StringWriter();
            CSVWriter csvWriter = new CSVWriter(writer);
            csvWriter.writeAll(items);
            csvWriter.close();
            return writer.getBuffer().toString();
        } catch (IOException e) {
            logger.error("构建CSV行数据失败!", e);
            return "";
        }
    }

    /**
     * 纯粹通过字符串构建CSV文件，以提高数据处理速度
     *
     * @param items
     * @return java.lang.String
     * @author jiangbin
     * @date 2019-09-05 17:23
     **/
    public static String toCsvStrSmart(List<String[]> items) {
        if (CollectionUtils.isEmpty(items)) {
            return "";
        }
        try {
            StringBuilder target = new StringBuilder();
            for (String[] rows : items) {
                if (ArrayUtils.isEmpty(rows)) {
                    continue;
                }
                for (String item : rows) {
                    target.append("\"").append(item).append("\",");
                }
                deleteCamma(target);
                target.append("\n");
            }
            return target.toString();
        } catch (ICoreException e) {
            logger.error("构建CSV行数据失败!" + ExceptionUtils.getStackTrace(e));
            return "";
        }
    }

    /**
     * 移除最后一个逗号
     *
     * @param source
     * @return void
     * @author jiangbin
     * @date 2019-08-20 20:57
     **/
    private static void deleteCamma(StringBuilder source) {
        int indexOf = source.lastIndexOf(",");
        if (indexOf + 1 == source.length()) {
            source.deleteCharAt(indexOf);
        }
    }

    /**
     * 校正数组列宽
     *
     * @param row      若为null或空数组则直接返回null
     * @param colCount 目标列宽
     * @return 较正成目标列宽的数组
     * @author jiang
     * @date 2018年9月2日下午5:10:37
     */
    public static String[] reviseCol(String[] row, int colCount) {
        if (ArrayUtils.isEmpty(row)) {
            return null;
        }
        if (row.length == colCount) {
            return row;
        }
        String[] target = new String[colCount];
        for (int i = 0; i < colCount && i < row.length; i++) {
            target[i] = row[i];
        }
        return target;
    }

    /**
     * 对斜杠进行转义,保证不会出现带斜杠的数据后面被截断的问题
     *
     * @param contents
     * @return java.lang.String
     * @author jiangbin
     * @date 2020/3/21 19:57
     **/
    public static String escape(String contents) {
        return StringUtils.stripToEmpty(contents).replaceAll("\\\\", "\\\\\\\\");
    }
}
