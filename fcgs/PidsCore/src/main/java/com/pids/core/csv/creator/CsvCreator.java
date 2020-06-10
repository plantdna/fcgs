package com.pids.core.csv.creator;

import com.pids.core.creator.Creator;

import java.util.List;

/**
 * CSV构建器，只支持第一行为标题行，其余均为对应各列的行数据二维表格式
 * @author jiang
 * @date 2018年9月1日下午10:56:05
 */
public interface CsvCreator<S> extends Creator<List<S>, String> {

}
