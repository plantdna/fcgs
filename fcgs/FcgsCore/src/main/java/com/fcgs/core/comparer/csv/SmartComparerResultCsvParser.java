package com.fcgs.core.comparer.csv;

import com.fcgs.core.comparer.domain.SimpleSmartComaprerResult;
import com.fcgs.core.comparer.domain.SmartComparerResult;
import com.pids.core.csv.parser.CsvParserTemplate;
import com.pids.core.utils.ListUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 构建比对结果数据列表
 *
 * @author jiang
 * @date 2019-11-07 22:11
 */

public class SmartComparerResultCsvParser extends CsvParserTemplate<SmartComparerResult> {
    @Override
    protected String[] getDefaultTitles() {
        return ListUtils.str2Array("待比样品,对比样品,比对位点数,差异位点数,无差异位点数");
    }

    @Override
    protected SmartComparerResult convertData(int rowIndex, String[] row) {
        int colIndex = 0;
        SmartComparerResult result = new SimpleSmartComaprerResult();
        result.setSourceGeneId(StringUtils.stripToEmpty(row[colIndex++]));
        result.setTargetGeneId(StringUtils.stripToEmpty(row[colIndex++]));
        colIndex++;
        result.setDiffCount(Integer.parseInt(row[colIndex++]));
        result.setSameCount(Integer.parseInt(row[colIndex++]));
        return result;
    }
}
