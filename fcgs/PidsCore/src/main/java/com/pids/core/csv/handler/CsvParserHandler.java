package com.pids.core.csv.handler;

import com.pids.core.csv.parser.CsvParser;

public interface CsvParserHandler<T> {

	public CsvParser<T> getCsvParser();

	public void setCsvParser(CsvParser<T> csvParser);

}
