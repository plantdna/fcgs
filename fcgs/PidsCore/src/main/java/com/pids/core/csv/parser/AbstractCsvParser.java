package com.pids.core.csv.parser;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;
import com.pids.core.csv.utils.CsvUtils;
import com.pids.core.exception.ICoreException;
import com.pids.core.utils.FileDataReader;
import com.pids.core.utils.ListUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.input.BOMInputStream;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tools.ant.filters.StringInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCsvParser<T> implements CsvParser<T> {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public List<T> parserFile(String csvPath) throws ICoreException {
		if (StringUtils.isBlank(csvPath)) {
			throw new ICoreException("CSV文件路径必需给定!");
		}
		return this.parserFile(new File(csvPath), CSVParser.DEFAULT_SEPARATOR);
	}

	@Override
	public List<T> parserFile(List<File> csvFiles) throws ICoreException {
		return this.parserFile(csvFiles, CSVParser.DEFAULT_SEPARATOR);
	}

	@Override
	public List<T> parserFile(File csvFile) throws ICoreException {
		return this.parserFile(csvFile, CSVParser.DEFAULT_SEPARATOR);
	}

	@Override
	public List<T> parser(String csvStr) throws ICoreException {
		return this.parser(csvStr, CSVParser.DEFAULT_SEPARATOR);
	}

	@Override
	public List<T> parserFile(List<File> csvFiles, char separator) throws ICoreException {
		if (CollectionUtils.isEmpty(csvFiles)) {
			return null;
		}
		List<T> targets = new ArrayList<>();
		for (File csvFile : csvFiles) {
			if (csvFile == null || !csvFile.exists() || !csvFile.isFile()) {
				continue;
			}
			try {
				List<T> tmps = this.parserFile(csvFile, separator);
				if (CollectionUtils.isNotEmpty(tmps)) {
					targets.addAll(tmps);
				}
			} catch (Exception e) {
				logger.error("解析CSV文件失败==>" + csvFile.getAbsolutePath(), e);
			}
		}

		return targets;
	}

	@Override
	public List<T> parserFile(String csvPath, char separator) throws ICoreException {
		if (StringUtils.isBlank(csvPath)) {
			throw new ICoreException("CSV文件路径必需给定!");
		}
		return this.parserFile(new File(csvPath), separator);
	}

	@Override
	public List<T> parserFile(File csvFile, char separator) throws ICoreException {
		if (csvFile == null) {
			throw new ICoreException("CSV文件必需给定!");
		}
		String csvStr = loadCsvFile(csvFile);
		return this.parser(csvStr, separator);
	}

	@Override
	public List<T> parser(String csvStr, char separator) throws ICoreException {
		if (StringUtils.isBlank(csvStr)) {
			logger.warn("未给定CVS文件内容!");
			return null;
		}

		// 对内容进行转义，避免斜杠造成后面部分内容无法正确解析的问题
		csvStr = CsvUtils.escape(csvStr);

		CSVReader csvReader = null;
		try {
			Reader reader = new InputStreamReader(new BOMInputStream(new StringInputStream(csvStr)),
					CsvUtils.CSV_ENCODING);
			csvReader = new CSVReader(reader, separator);
			List<String[]> rows = csvReader.readAll();
			if (!checkCsv(rows)) {
				return null;
			}
			return convertDatas(rows);
		} catch (IOException e) {
			logger.warn("解析CSV文件失败!", e);
			return null;
		} finally {
			try {
				csvReader.close();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * 加载Csv文件内容
	 *
	 * @param csvFile
	 * @return
	 * @author jiang
	 * @date 2018年9月2日下午7:31:03
	 */
	protected String loadCsvFile(File csvFile) {
		try {
			return FileDataReader.read(csvFile, CsvUtils.CSV_ENCODING);
		} catch (IOException e) {
			logger.warn("读取CSV文件数据失败!", e);
			return null;
		}
	}

	/**
	 * 检查CSV数据合法性，主要是标题头是否与给定默认标题头一致
	 *
	 * @param rows
	 * @return
	 * @author jiang
	 * @date 2018年9月2日上午3:19:28
	 */
	protected boolean checkCsv(List<String[]> rows) {
		if (CollectionUtils.isEmpty(rows)) {
			logger.info("CSV文件未包含任何信息!");
			return false;
		}

		if (rows.size() == 1) {
			logger.info("CSV文件未包含任何数据信息!");
			return false;
		}

		if (!checkTitle(rows.get(0))) {
			logger.info("CSV文件标题信息不正确!");
			return false;
		}

		return true;
	}

	/**
	 * <pre>
	 * 检查标题行格式是否正确:
	 * 1、若无标题行数据则直接返回false
	 * 2、若无默认标题行数据则直接返回true
	 * 3、若标题行与默认标题行数据不符则返回false
	 * 4、否则返回true
	 * </pre>
	 *
	 * @param titles csv文件第一行标题行数据
	 * @return
	 * @author jiang
	 * @date 2018年9月2日下午4:14:01
	 */
	private boolean checkTitle(String[] titles) {
		if (ArrayUtils.isEmpty(titles)) {
			return false;
		}

		String[] defaultTitles = this.getDefaultTitles();
		if (ArrayUtils.isEmpty(defaultTitles)) {
			return true;
		}

		return ListUtils.isEquals(titles, defaultTitles);
	}

	/**
	 * 转换CSV行数据信息为对象列表
	 *
	 * @param rows
	 * @return
	 * @author jiang
	 * @date 2018年9月2日上午3:20:29
	 */
	protected List<T> convertDatas(List<String[]> rows) {
		int colCount = this.getColCount();
		List<T> targets = new ArrayList<>();
		for (int rowIndex = 1; rowIndex < rows.size(); rowIndex++) {
			// 行数据
			String[] row = rows.get(rowIndex);
			if (ArrayUtils.isEmpty(row)) {
				continue;
			}

			// 校正列宽
			if (colCount != row.length) {
				row = reviseRowData(row);
			}

			// 转换数据为对象
			T target = convertData(rowIndex - 1, row);
			if (target != null) {
				targets.add(target);
			}
		}
		return targets;
	}

	/**
	 * 校正行数据，一般重点在于较正其列宽与系统定义的要一致，便于后续转换对象时的数据处理
	 *
	 * @param row
	 * @return
	 * @author jiang
	 * @date 2018年9月2日下午5:10:37
	 */
	protected String[] reviseRowData(String[] row) {
		String[] target = new String[this.getColCount()];
		for (int i = 0; i < target.length && i < row.length; i++) {
			target[i] = row[i];
		}
		return target;
	}

	/**
	 * 获取默认标题行定义
	 *
	 * @return
	 * @author jiang
	 * @date 2018年9月2日下午4:18:52
	 */
	protected abstract String[] getDefaultTitles();

	/**
	 * 获取总列数
	 *
	 * @return
	 * @author jiang
	 * @date 2018年9月1日下午11:39:56
	 */
	protected int getColCount() {
		return ArrayUtils.getLength(this.getDefaultTitles());
	}

	/**
	 * 转换行数据为数据对象
	 *
	 * @param rowIndex 行数据索引号，即csv文件中标题行之后开始第二行计为0
	 * @param row      行数据，与默认标题宽度一致，之前会被自动校正
	 * @return
	 * @author jiang
	 * @date 2018年9月2日下午4:16:58
	 */
	protected abstract T convertData(int rowIndex, String[] row);

}
