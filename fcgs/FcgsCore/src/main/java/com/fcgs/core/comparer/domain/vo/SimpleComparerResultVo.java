package com.fcgs.core.comparer.domain.vo;

import com.fcgs.base.domain.Sample;
import com.fcgs.base.marker.utils.MarkerNamesViwer;
import com.fcgs.core.comparer.domain.SimpleDetailComparerResult;
import com.pids.core.utils.ListUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * 比对结果视图对象的简单实现类
 *
 * @author Jiangbin
 * @date 2014-3-4上午12:40:08
 */
public class SimpleComparerResultVo extends SimpleDetailComparerResult implements ComparerResultVo {
	private static final long serialVersionUID = 992177773336617084L;
	private List<String> noDiffMarkerNames;
	private List<String> diffMarkerNames;
	private List<String> missMarkerNames;
	private Integer noDiffMarkerCount;
	private Integer diffMarkerCount;
	private Integer missMarkerCount;
	private Sample sourceSample;
	private Sample targetSample;
	private Integer sourceGeneStatus;
	private String sourceGeneStatusName;
	private Integer targetGeneStatus;
	private String targetGeneStatusName;
	private String sourceGeneLocation;
	private String targetGeneLocation;

	private Integer sourceGeneLib;
	private String sourceGeneLibName;

	private Integer targetGeneLib;
	private String targetGeneLibName;

	@Override
	public String getSourceGeneStatusName() {
		return sourceGeneStatusName;
	}

	@Override
	public void setSourceGeneStatusName(String sourceGeneStatusName) {
		this.sourceGeneStatusName = sourceGeneStatusName;
	}

	@Override
	public String getTargetGeneStatusName() {
		return targetGeneStatusName;
	}

	@Override
	public void setTargetGeneStatusName(String targetGeneStatusName) {
		this.targetGeneStatusName = targetGeneStatusName;
	}

	@Override
	public String getSourceGeneLibName() {
		return sourceGeneLibName;
	}

	@Override
	public void setSourceGeneLibName(String sourceGeneLibName) {
		this.sourceGeneLibName = sourceGeneLibName;
	}

	@Override
	public String getTargetGeneLibName() {
		return targetGeneLibName;
	}

	@Override
	public void setTargetGeneLibName(String targetGeneLibName) {
		this.targetGeneLibName = targetGeneLibName;
	}

	@Override
	public Integer getDiffMarkerCount() {
		return diffMarkerCount;
	}

	@Override
	public List<String> getDiffMarkerNames() {
		return diffMarkerNames;
	}

	@Override
	public Integer getMissMarkerCount() {
		return missMarkerCount;
	}

	@Override
	public List<String> getMissMarkerNames() {
		return missMarkerNames;
	}

	@Override
	public Integer getNoDiffMarkerCount() {
		return noDiffMarkerCount;
	}

	@Override
	public List<String> getNoDiffMarkerNames() {
		return noDiffMarkerNames;
	}

	@Override
	public String getSourceGeneLocation() {
		return sourceGeneLocation;
	}

	@Override
	public Integer getSourceGeneStatus() {
		return sourceGeneStatus;
	}

	@Override
	public Sample getSourceSample() {
		return sourceSample;
	}

	@Override
	public String getTargetGeneLocation() {
		return targetGeneLocation;
	}

	@Override
	public Integer getTargetGeneStatus() {
		return targetGeneStatus;
	}

	@Override
	public Sample getTargetSample() {
		return targetSample;
	}

	@Override
	public void setDiffMarkerCount(Integer diffMarkerCount) {
		this.diffMarkerCount = diffMarkerCount;
	}

	@Override
	public void setDiffMarkerNames(List<String> diffMarkerNames) {
		this.diffMarkerNames = diffMarkerNames;
	}

	@Override
	public void setMarkerNamesViwer(MarkerNamesViwer viwer) {
		if (viwer != null) {
			try {
				BeanUtils.copyProperties(viwer, this);
			} catch (Exception e) {
				Logger.getLogger(getClass()).warn(e);
			}
		}
	}

	@Override
	public void setMissMarkerCount(Integer missMarkerCount) {
		this.missMarkerCount = missMarkerCount;
	}

	@Override
	public void setMissMarkerNames(List<String> missMarkerNames) {
		this.missMarkerNames = missMarkerNames;
	}

	@Override
	public void setNoDiffMarkerCount(Integer noDiffMarkerCount) {
		this.noDiffMarkerCount = noDiffMarkerCount;
	}

	@Override
	public void setNoDiffMarkerNames(List<String> noDiffMarkerNames) {
		this.noDiffMarkerNames = noDiffMarkerNames;
	}

	@Override
	public void setSourceGeneLib(Integer sourceGeneLib) {
		this.sourceGeneLib = sourceGeneLib;
	}

	@Override
	public void setSourceGeneLocation(String sourceGeneLocation) {
		this.sourceGeneLocation = sourceGeneLocation;
	}

	@Override
	public void setSourceGeneStatus(Integer sourceGeneStatus) {
		this.sourceGeneStatus = sourceGeneStatus;
	}

	@Override
	public void setSourceSample(Sample sourceSample) {
		this.sourceSample = sourceSample;
	}

	@Override
	public void setTargetGeneLib(Integer targetGeneLib) {
		this.targetGeneLib = targetGeneLib;
	}

	@Override
	public void setTargetGeneLocation(String targetGeneLocation) {
		this.targetGeneLocation = targetGeneLocation;
	}

	@Override
	public void setTargetGeneStatus(Integer targetGeneStatus) {
		this.targetGeneStatus = targetGeneStatus;
	}

	@Override
	public void setTargetSample(Sample targetSample) {
		this.targetSample = targetSample;
	}

	@Override
	public Integer getSourceGeneLib() {
		return sourceGeneLib;
	}

	@Override
	public Integer getTargetGeneLib() {
		return targetGeneLib;
	}

	@Override
	public List<String> getGeneIds() {
		return ListUtils.createList(getSourceGeneId(), getTargetGeneId());
	}

	@Override
	public float getDiffMarkerPercent() {
		int count = getComparerMarkerCount();
		if (count == 0) {
			return 0;
		}
		if (diffMarkerCount != null) {
			return diffMarkerCount * (float) 1.0 / count;
		}
		return 0;
	}

	@Override
	public float getNoDiffMarkerPercent() {
		int count = getComparerMarkerCount();
		if (count == 0) {
			return 0;
		}
		if (noDiffMarkerCount != null) {
			return noDiffMarkerCount * (float) 1.0 / count;
		}
		return 0;
	}

	@Override
	public int getComparerMarkerCount() {
		int count = 0;
		if (noDiffMarkerCount != null) {
			count += noDiffMarkerCount;
		}
		if (diffMarkerCount != null) {
			count += diffMarkerCount;
		}
		return count;
	}

	@Override
	public void swap() {
		ComparerResultVo tmp = new SimpleComparerResultVo();

		// 拷贝全部属性
		tmp.setComaprerResult(this);

		// 交换待比属性
		tmp.setSourceGeneId(getTargetGeneId());
		tmp.setSourceGeneLib(getTargetGeneLib());
		tmp.setSourceGeneLibName(getTargetGeneLibName());
		tmp.setSourceGeneLocation(getTargetGeneLocation());
		tmp.setSourceGeneStatus(getTargetGeneStatus());
		tmp.setSourceGeneStatusName(getTargetGeneStatusName());
		tmp.setSourceSample(getTargetSample());

		// 交换对比属性
		tmp.setTargetGeneId(getSourceGeneId());
		tmp.setTargetGeneLib(getSourceGeneLib());
		tmp.setTargetGeneLibName(getSourceGeneLibName());
		tmp.setTargetGeneLocation(getSourceGeneLocation());
		tmp.setTargetGeneStatus(getSourceGeneStatus());
		tmp.setTargetGeneStatusName(getSourceGeneStatusName());
		tmp.setTargetSample(getSourceSample());

		// 重新设置回当前对象
		setComaprerResult(tmp);
	}

	@Override
	public double getDiffRate() {
		Integer diffMarkerCount = getDiffMarkerCount();
		Integer markerCount = getMarkerCount();
		return ((double) diffMarkerCount / markerCount);
	}

	@Override
	public int getIntDiffRate() {
		return (int) Math.round(getDiffRate() * 100);
	}

	@Override
	public String getDiffMarkerNamesStr() {
		return ListUtils.list2Str(getDiffMarkerNames());
	}

	@Override
	public String getNoDiffMarkerNamesStr() {
		return ListUtils.list2Str(getNoDiffMarkerNames());
	}
}
