package com.fcgs.base.mapper;

import com.fcgs.base.domain.gene.Marker;
import com.pids.core.mapper.MapperTemplate;

/**
 * Marker映射信息对象，key--荧光名，value--Marker信息对象,
 * 一般用于高速缓存，可以快速获取相关指纹Marker信息
 * @author jiangbin
 * @date 2012-5-5上午4:21:12
 */
public class MarkerDyeMapper extends MapperTemplate<Marker, Marker> {

	private static final long serialVersionUID = 6519673701903639945L;
	private String geneId;

	/**
	 * 设置指纹ID
	 * @author Andory
	 * @param geneId
	 * @date 2012-6-9下午02:23:19
	 */
	public void setGeneId(String geneId) {
		this.geneId = geneId;
	}

	/**
	 * 获取指纹ID
	 * @author Andory
	 * @return
	 * @date 2012-6-9下午02:23:32
	 */
	public String getGeneId() {
		return geneId;
	}

	@Override
	protected String getMapperKey(Marker object) {
		if (object != null && object.size() > 0) {
			return object.getDye();
		} else {
			return null;
		}
	}

	@Override
	protected Marker getMapperValue(Marker object) {
		return object;
	}

}
