package com.fcgs.base.mapper;

import com.fcgs.base.domain.gene.Allele;
import com.fcgs.base.domain.gene.Marker;
import com.pids.core.mapper.MapperTemplate;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Allele信息Mapper,key/value--allele值/Allele对象
 * @author Jiangbin
 * @date 2013-8-20下午11:36:48
 */
public class AlleleMapper extends MapperTemplate<Allele, Allele> {

	private static final long serialVersionUID = 6914903091673726582L;

	@Override
	protected String getMapperKey(Allele object) {
		return StringUtils.stripToEmpty((int) object.getAllele() + "");
	}

	@Override
	protected Allele getMapperValue(Allele object) {
		return object;
	}

	public AlleleMapper() {
		super();
	}

	public AlleleMapper(List<Allele> alleles) {
		super();
		this.addAll(alleles);
	}

	public AlleleMapper(Marker marker) {
		super();
		if (marker != null && !marker.isEmpty()) {
			this.addAll(marker.getAlleles());
		}
	}

}
