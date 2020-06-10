package com.fcgs.base.mapper;

import com.fcgs.base.domain.gene.Marker;
import com.pids.core.mapper.MapperTemplate;

/**
 * 位点的荧光与引物名关系映射器,key--荧光名,value--引物名
 * @author jiangbin
 * @date 2012-5-7下午4:31:55
 */
public class MarkerDyePrimerMapper extends MapperTemplate<String, Marker> {
	private static final long serialVersionUID = -8968165418673202930L;
	private String primerGroupId;

	/**
	 * 设置引物组ID
	 * @author Andory
	 * @param primerGroupId
	 * @date 2012-6-9下午02:23:43
	 */
	public void setPrimerGroupId(String primerGroupId) {
		this.primerGroupId = primerGroupId;
	}

	/**
	 * 获取引物组ID
	 * @author Andory
	 * @return
	 * @date 2012-6-9下午02:24:00
	 */
	public String getPrimerGroupId() {
		return primerGroupId;
	}

	@Override
	protected String getMapperKey(Marker object) {
		return object.getDye();
	}

	@Override
	protected String getMapperValue(Marker object) {
		return object.getPrimerName();
	}

}
