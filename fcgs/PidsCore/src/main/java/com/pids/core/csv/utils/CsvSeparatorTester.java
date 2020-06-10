package com.pids.core.csv.utils;

import com.pids.core.csv.parser.CsvParser;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * CSV分隔符检测功能
 * @author jiang
 * @date 2018年12月27日下午3:49:03
 */

public class CsvSeparatorTester<T> {
	private static Logger logger = LoggerFactory.getLogger(CsvSeparatorTester.class);

	/**
	 * 检测csv文件的分隔符，注意：只有csv内容能被给定CsvParser解析时才能正确检测出分隔符
	 * @author jiang
	 * @param csvParser csv解析器
	 * @param csvContents csv内容
	 * @param separators 分隔符范围
	 * @return 若未成功检测分隔符将返回0
	 * @date 2018年12月27日下午3:49:25
	 */
	public char test(CsvParser<T> csvParser, String csvContents, char... separators) {
		if (csvParser == null) {
			logger.warn("未给定csv解析器!");
			return 0;
		}
		if (StringUtils.isBlank(csvContents)) {
			logger.warn("未给定csv内容，无法检测分隔符!");
			return 0;
		}
		if (ArrayUtils.isEmpty(separators)) {
			logger.warn("未给定csv分隔符列表范围!");
			return 0;
		}
		for (char separator : separators) {
			try {
				List<T> results = csvParser.parser(csvContents, separator);
				if (CollectionUtils.isNotEmpty(results)) {
					return separator;
				}
			} catch (Exception e) {
				logger.warn(separator + "不是目标CSV内容的分隔符!");
			}
		}

		logger.warn("目标CSV内容不包含如下分隔符：" + separators.toString());
		return 0;
	}
}
