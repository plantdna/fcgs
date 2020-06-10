package com.pids.core.csv.handler;

import com.pids.core.csv.creator.CsvCreator;

public interface CsvCreatorHandler<S> {

	public CsvCreator<S> getCsvCreator();

	public void setCsvCreator(CsvCreator<S> csvCreator);

}
