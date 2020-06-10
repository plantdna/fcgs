package com.pids.core.csv.domain;

public class SimpleCsvContainer implements CsvContainer {

	private static final long serialVersionUID = 2800681372203397079L;
	private String[] titles;
	private String[][] rows;

	@Override
	public String[] getTitles() {
		return titles;
	}

	@Override
	public void setTitles(String[] titles) {
		this.titles = titles;
	}

	@Override
	public String[][] getRows() {
		return rows;
	}

	@Override
	public void setRows(String[][] rows) {
		this.rows = rows;
	}

	@Override
	public int getColCount() {
		return this.titles == null ? 0 : this.titles.length;
	}

	@Override
	public int getRowCount() {
		return this.rows == null ? 0 : this.rows.length;
	}

}
