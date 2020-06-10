package com.fcgs.base.mapper;

import com.fcgs.base.domain.gene.Marker;
import com.pids.core.mapper.MapperTemplate;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 位点的引物荧光关系映射器，key--引物名，value--荧光名
 * @author jiangbin
 * @date 2012-5-7下午4:31:55
 */
public class MarkerPrimerDyeMapper extends MapperTemplate<String, Marker> {
	private static final long serialVersionUID = -5989214385010465742L;
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

	/**
	 * 获取引物名列表
	 * @author jiangbin
	 * @return
	 * @date 2012-9-30下午7:34:25
	 */
	public List<String> getPrimerNames() {
		List<String> primerNames = new ArrayList<String>();
		if (this.keySet() != null) {
			primerNames.addAll(this.keySet());
		}
		return primerNames;
	}

	@Override
	protected String getMapperKey(Marker object) {
		return object.getPrimerName();
	}

	@Override
	protected String getMapperValue(Marker object) {
		if (object != null && !StringUtils.isBlank(object.getDye())) {
			return object.getDye();
		} else {
			return null;
		}
	}

}
