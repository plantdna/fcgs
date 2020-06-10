package com.pids.core.pathcreator;

public class SimpleRelativePath implements RelativePath {
	private static final long serialVersionUID = -5962595338534745701L;
	private String relativePath;

	@Override
	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	@Override
	public String getRelativePath() {
		return relativePath;
	}

}
