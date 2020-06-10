package com.fcgs.base.marker.utils;

import com.fcgs.base.domain.gene.Allele;
import com.fcgs.base.domain.gene.Marker;
import com.pids.core.mapper.MapperTemplate;

import java.util.List;

/**
 * 指纹位点名与Allele信息映射关系表,key/value--引物名称/Allele数组,格式:[allele1,allele2,height1,height2]
 * @author jiangbin
 * @date 2017年5月12日下午12:17:02
 */
public class MarkerNameAllelesMapper extends MapperTemplate<int[], Marker> {

	private static final long serialVersionUID = 4516772740384053661L;

	@Override
	protected String getMapperKey(Marker object) {
		return object.getPrimerName();
	}

	@Override
	protected int[] getMapperValue(Marker object) {
		int[] alleles = new int[4];
		List<Allele> alleleList = object.getAlleles();
		int size = object.size();
		if (size == 0) {
			return null;
		} else if (size == 1) {
			alleles[0] = (int) alleleList.get(0).getAllele();
			alleles[1] = (int) alleleList.get(0).getAllele();
			alleles[2] = (int) alleleList.get(0).getHeight();
			alleles[3] = (int) alleleList.get(0).getHeight();
		} else if (size >= 2) {
			alleles[0] = (int) alleleList.get(0).getAllele();
			alleles[1] = (int) alleleList.get(1).getAllele();
			alleles[2] = (int) alleleList.get(0).getHeight();
			alleles[3] = (int) alleleList.get(1).getHeight();
		}
		return alleles;
	}

}
