package com.fcgs.base.domain.gene;

import com.fcgs.base.formatter.MarkerFormatter;
import com.fcgs.base.mapper.AlleleMapper;
import com.fcgs.base.primer.SimplePrimerInfo;
import com.pids.core.utils.ListUtils;
import com.pids.core.utils.ObjectCopier;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * 位点信息接口实现类
 * @author jiangbin
 * @date 2014年3月21日上午11:04:37
 */
public class SimpleMarker extends SimplePrimerInfo implements Marker {
	private static final long serialVersionUID = 6121475714389082241L;
	protected String geneId;//关联的指纹ID
	protected String picture;// 峰图截图路径
	protected String comment;// 备注
	protected Double weight;// 权重,与指纹权重相同
	protected boolean isManual;//是否为手动审核位点
	protected List<Allele> alleles;// 等位基因

	public SimpleMarker() {
		this.weight = 0d;
		this.alleles = new ArrayList<>();
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public int size() {
		return ListUtils.size(alleles);
	}

	@Override
	public void addAllele(Allele allele) {
		if (allele == null) {
			return;
		}
		if (this.alleles == null) {
			this.alleles = new ArrayList<Allele>();
		}
		this.alleles.add(allele);
	}

	@Override
	public void setGeneId(String geneId) {
		this.geneId = geneId;
	}

	@Override
	public String getGeneId() {
		return geneId;
	}

	@Override
	public void setAlleles(List<Allele> alleles) {
		this.alleles = alleles;
	}

	@Override
	public List<Allele> getAlleles() {
		return this.alleles;
	}

	@Override
	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public String getPicture() {
		return this.picture;
	}

	@Override
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@Override
	public Double getWeight() {
		return this.weight;
	}

	@Override
	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String getComment() {
		return this.comment;
	}

	@Override
	public void setMarker(Marker marker) {
		if (marker == null) {
			return;
		}
		Marker target = ObjectCopier.copy(marker);
		try {
			BeanUtils.copyProperties(this, target);
		} catch (Exception e) {
		}
	}

	@Override
	public String getMarkerStr() {
		return new MarkerFormatter().format(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	@Override
	public void clear() {
		this.alleles = null;
	}

	@Override
	public boolean isHomozygote() {
		if (this.isEmpty()) {
			return false;
		}
		return new AlleleMapper(this).size() == 1;
	}

	@Override
	public boolean isManual() {
		return isManual;
	}

	@Override
	public void setManual(boolean isManual) {
		this.isManual = isManual;
	}

}
