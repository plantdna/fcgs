package com.pids.core.csv.sort;

import com.pids.core.csv.creator.CsvCreator;
import com.pids.core.csv.parser.CsvParser;
import com.pids.core.csv.utils.CsvUtils;
import com.pids.core.exception.ICoreException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * CSV数据排序功能模板实现类
 * 
 * @author jiang
 * @date 2018年9月2日下午6:32:38
 */
public abstract class CsvSorterTemplate<T> implements CsvSorter {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public String sort(String csvPath) throws ICoreException {
		if (StringUtils.isBlank(csvPath)) {
			return null;
		}

		// 加载CSV内容
		String csvContent = loadCsv(csvPath);
		if (StringUtils.isBlank(csvContent)) {
			return null;
		}

		// 排序CSV内容
		csvContent = this.sortRows(csvContent);
		if (StringUtils.isBlank(csvContent)) {
			return null;
		}

		// 保存CSV内容到磁盘
		saveCsv(csvContent, csvPath);
		return csvPath;
	}

	/**
	 * 回写CSV数据到磁盘文件中
	 * 
	 * @author jiang
	 * @param csvContent
	 * @param csvPath
	 * @date 2018年9月2日下午6:22:46
	 */
	protected void saveCsv(String csvContent, String csvPath) {
		try {
			FileUtils.write(new File(csvPath), csvContent);
		} catch (IOException e) {
			logger.error("回写排序后的CSV数据到磁盘文件(" + csvPath + ")失败!", e);
		}
	}

	/**
	 * 读取磁盘文件上的CSV文件内容
	 * 
	 * @author jiang
	 * @param csvPath
	 * @return
	 * @date 2018年9月2日下午6:25:17
	 */
	protected String loadCsv(String csvPath) {
		String filePath = null;
		try {
			return FileUtils.readFileToString(new File(csvPath), CsvUtils.CSV_ENCODING);
		} catch (IOException e) {
			logger.error("读取CSV数据磁盘文件(" + filePath + ")失败!", e);
			return null;
		}
	}

	@Override
	public String sortRows(String csvContent) throws ICoreException {
		if (StringUtils.isBlank(csvContent)) {
			return null;
		}

		// 解析CSV数据
		List<T> datas = parserCsv(csvContent);
		if (CollectionUtils.isEmpty(datas)) {
			return null;
		}

		// 数据排序
		datas = sortDatas(datas);
		if (CollectionUtils.isEmpty(datas)) {
			return null;
		}

		// 构建CSV数据
		return createCsv(datas);
	}

	/**
	 * 构建CSV数据内容
	 * 
	 * @author jiang
	 * @param datas
	 * @return
	 * @date 2018年9月2日下午6:30:00
	 */
	protected String createCsv(List<T> datas) {
		if (CollectionUtils.isEmpty(datas)) {
			return null;
		}
		return getCsvCreator().create(datas);
	}

	/**
	 * 对数据对象列表进行排序
	 * 
	 * @author jiang
	 * @param datas
	 * @return
	 * @date 2018年9月2日下午6:31:50
	 */
	protected List<T> sortDatas(List<T> datas) {
		if (CollectionUtils.isEmpty(datas)) {
			return null;
		}
		Collections.sort(datas, getComparer());
		return datas;
	}

	/**
	 * 解析CSV数据内容
	 * 
	 * @author jiang
	 * @param csvContent
	 * @return
	 * @date 2018年9月2日下午6:26:58
	 */
	protected List<T> parserCsv(String csvContent) {
		if (StringUtils.isBlank(csvContent)) {
			return null;
		}
		return getCsvParser().parser(csvContent);
	}

	/**
	 * 获取CSV解析器
	 * 
	 * @author jiang
	 * @return
	 * @date 2018年9月2日下午6:28:34
	 */
	protected abstract CsvParser<T> getCsvParser();

	/**
	 * 获取CSV构建器
	 * 
	 * @author jiang
	 * @return
	 * @date 2018年9月2日下午6:29:45
	 */
	protected abstract CsvCreator<T> getCsvCreator();

	/**
	 * 获取CSV数据对象的排序器
	 * 
	 * @author jiang
	 * @return
	 * @date 2018年9月2日下午6:32:10
	 */
	protected abstract Comparator<T> getComparer();

}
