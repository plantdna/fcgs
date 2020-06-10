package com.fcgs.base.marker.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * 位点名信息视图类的实现类
 * @author jiangbin
 * @date 2012-11-24上午1:09:24
 */
public class SimpleMarkerNamesViwer implements MarkerNamesViwer {
	private static final long serialVersionUID = -4171086707345755690L;
	private List<String> noDiffMarkerNames;
	private List<String> diffMarkerNames;
	private List<String> missMarkerNames;
	private Integer noDiffMarkerCount;
	private Integer diffMarkerCount;
	private Integer missMarkerCount;
	
	@Override
	public List<String> getNoDiffMarkerNames() {
		return noDiffMarkerNames;
	}
	
	@Override
	public void setNoDiffMarkerNames(List<String> noDiffMarkerNames) {
		this.noDiffMarkerNames = noDiffMarkerNames;
	}
	
	@Override
	public List<String> getDiffMarkerNames() {
		return diffMarkerNames;
	}
	
	@Override
	public void setDiffMarkerNames(List<String> diffMarkerNames) {
		this.diffMarkerNames = diffMarkerNames;
	}
	
	@Override
	public List<String> getMissMarkerNames() {
		return missMarkerNames;
	}
	
	@Override
	public void setMissMarkerNames(List<String> missMarkerNames) {
		this.missMarkerNames = missMarkerNames;
	}
	
	@Override
	public Integer getNoDiffMarkerCount() {
		return noDiffMarkerCount;
	}
	
	@Override
	public void setNoDiffMarkerCount(Integer noDiffMarkerCount) {
		this.noDiffMarkerCount = noDiffMarkerCount;
	}
	
	@Override
	public Integer getDiffMarkerCount() {
		return diffMarkerCount;
	}
	
	@Override
	public void setDiffMarkerCount(Integer diffMarkerCount) {
		this.diffMarkerCount = diffMarkerCount;
	}
	
	@Override
	public Integer getMissMarkerCount() {
		return missMarkerCount;
	}
	
	@Override
	public void setMissMarkerCount(Integer missMarkerCount) {
		this.missMarkerCount = missMarkerCount;
	}
	
	@Override
	public void setMarkerNamesViwer(MarkerNamesViwer viwer) {
		if (viwer == null) {
			return;
		}
		try {
			BeanUtils.copyProperties(viwer, this);
		} catch (Exception e) {
			Logger.getLogger(getClass()).warn(e);
		}
	}
}
