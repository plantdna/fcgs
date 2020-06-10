package com.fcgs.base.domain;

/**
 * 负责人信息
 * @author Jiangbin
 * @date 2014-4-25下午10:31:19
 */
public class SimpleManager implements Manager {
	private static final long serialVersionUID = -5331256764819863081L;
	private String manager;

	@Override
	public void setManager(String manager) {
		this.manager = manager;
	}

	@Override
	public String getManager() {
		return manager;
	}

}
