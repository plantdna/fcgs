package com.pids.core.mode;

public class SimpleMode implements Mode {
	private Boolean mode;

	@Override
	public Boolean getMode() {
		return mode;
	}

	@Override
	public void setMode(Boolean mode) {
		this.mode = mode;
	}

}
