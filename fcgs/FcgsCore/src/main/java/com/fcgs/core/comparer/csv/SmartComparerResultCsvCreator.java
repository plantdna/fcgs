package com.fcgs.core.comparer.csv;

import com.fcgs.core.comparer.domain.SmartComparerResult;
import com.pids.core.csv.creator.CsvCreatorTemplate;
import com.pids.core.utils.ListUtils;

/**
 * 构建比对结果数据列表
 *
 * @author jiang
 * @date 2019-11-07 22:11
 */

public class SmartComparerResultCsvCreator extends CsvCreatorTemplate<SmartComparerResult> {
    @Override
    protected String[] createRow(int rowIndex, SmartComparerResult source) {
        String[] rowDatas = new String[getColCount()];
        int colIndex = 0;
        rowDatas[colIndex++] = source.getSourceGeneId();
        rowDatas[colIndex++] = source.getTargetGeneId();
        rowDatas[colIndex++] = (source.getSameCount() + source.getDiffCount()) + "";
        rowDatas[colIndex++] = source.getDiffCount() + "";
        rowDatas[colIndex++] = source.getSameCount() + "";
        return rowDatas;
    }

    @Override
    protected String[] getDefaultTitles() {
        return ListUtils.str2Array("待比样品,对比样品,比对位点数,差异位点数,无差异位点数");
    }
}
