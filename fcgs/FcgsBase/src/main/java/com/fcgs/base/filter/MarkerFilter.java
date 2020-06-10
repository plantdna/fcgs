package com.fcgs.base.filter;

import com.fcgs.base.datacopier.AlleleCopier;
import com.fcgs.base.domain.gene.Allele;
import com.fcgs.base.domain.gene.Marker;
import com.fcgs.base.domain.gene.SimpleAllele;
import com.pids.core.exception.ICoreException;
import com.pids.core.filter.ListFilterTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤出无指纹数据的Marker列表
 * @author Andory
 * @date 2012-5-25下午06:13:56
 */
public class MarkerFilter extends ListFilterTemplate<Marker, Marker> {
	private AlleleCopier alleleCopier=new AlleleCopier();

	public void setAlleleCopier(AlleleCopier alleleCopier) {
		this.alleleCopier = alleleCopier;
	}

	public AlleleCopier getAlleleCopier() {
		return alleleCopier;
	}

	@Override
	protected Marker filter(Marker source, boolean isInternal) throws ICoreException {
		if (source == null || source.isEmpty()) {
			return null;
		}
		//将allele值为0的滤掉
		List<Allele> alleles = filterAlleles(source);
		//纯合则补成双倍体
		if (alleles.size() == 1) {
			Allele target = new SimpleAllele();
			alleleCopier.copy(alleles.get(0), target);
			alleles.add(target);
		}
		//至少1个allele值
		if (!alleles.isEmpty()) {
			return source;
		}
		return null;
	}

	/**
	 * 将allele值为0的Allele滤掉
	 * @author Andory
	 * @param source
	 * @return
	 * @date 2012-5-25下午06:48:06
	 */
	protected List<Allele> filterAlleles(Marker source) {
		List<Allele> alleles = source.getAlleles();
		List<Allele> tmpAlleles = new ArrayList<Allele>();
		for (Allele allele : alleles) {
			if (allele.getAllele() == 0) {
				tmpAlleles.add(allele);
			}
		}
		alleles.removeAll(tmpAlleles);
		return alleles;
	}

}
