package com.pids.core.csv.parser;

import com.pids.core.exception.ICoreException;
import com.pids.core.parser.Parser;

import java.io.File;
import java.util.List;

/**
 * CSV文件解析器，只支持第一行为标题行，其余均为对应各列的行数据二维表格式
 * 
 * @author jiang
 * @date 2018年9月1日下午10:57:51
 */
public interface CsvParser<T> extends Parser<String, List<T>> {
	/**
	 * 解析指定CSV文件内容
	 * 
	 * @author jiang
	 * @param csvPath CSV文件存储路径
	 * @return
	 * @throws ICoreException
	 * @date 2018年9月2日下午7:18:28
	 */
	List<T> parserFile(String csvPath) throws ICoreException;

	/**
	 * 解析指定CSV文件内容
	 * 
	 * @author jiang
	 * @param csvFile CSV文件
	 * @return
	 * @throws ICoreException
	 * @date 2018年9月2日下午7:18:28
	 */
	List<T> parserFile(File csvFile) throws ICoreException;

	/**
	 * 解析指定CSV文件内容
	 * 
	 * @author jiang
	 * @param csvFiles CSV文件
	 * @return
	 * @throws ICoreException
	 * @date 2018年9月2日下午7:18:28
	 */
	List<T> parserFile(List<File> csvFiles) throws ICoreException;

	/**
	 * 解析指定CSV文件内容
	 * 
	 * @author jiang
	 * @param csvPath   CSV文件存储路径
	 * @param separator CSV分隔符
	 * @return
	 * @throws ICoreException
	 * @date 2018年9月2日下午7:18:28
	 */
	List<T> parserFile(String csvPath, char separator) throws ICoreException;

	/**
	 * 解析指定CSV文件内容
	 * 
	 * @author jiang
	 * @param csvFile   CSV文件
	 * @param separator CSV分隔符
	 * @return
	 * @throws ICoreException
	 * @date 2018年9月2日下午7:18:28
	 */
	List<T> parserFile(File csvFile, char separator) throws ICoreException;

	/**
	 * 解析指定CSV文件内容
	 * 
	 * @author jiang
	 * @param csvFiles  CSV文件
	 * @param separator CSV分隔符
	 * @return
	 * @throws ICoreException
	 * @date 2018年9月2日下午7:18:28
	 */
	List<T> parserFile(List<File> csvFiles, char separator) throws ICoreException;

	/**
	 * 解析指定CSV内容
	 * 
	 * @author jiang
	 * @param csvStr    CSV内容
	 * @param separator CSV分隔符
	 * @return
	 * @throws ICoreException
	 * @date 2018年12月27日下午2:56:55
	 */
	public List<T> parser(String csvStr, char separator) throws ICoreException;
}
