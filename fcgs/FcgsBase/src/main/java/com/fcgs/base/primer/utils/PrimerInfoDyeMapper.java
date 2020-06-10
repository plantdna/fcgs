package com.fcgs.base.primer.utils;

import com.fcgs.base.primer.PrimerInfo;
import com.pids.core.mapper.MapperTemplate;

/**
 * 引物合成编号信息Mapper,key/value--引物合成编号/引物信息对象
 * @author jiangbin
 * @date 2013-12-4下午2:07:56
 */
public class PrimerInfoDyeMapper<V extends PrimerInfo> extends MapperTemplate<V, V> {

	private static final long serialVersionUID = 1515847075589018171L;

	@Override
	protected String getMapperKey(V object) {
		return object.getDye();
	}

	@Override
	protected V getMapperValue(V object) {
		return object;
	}

	/**
	 * 获取引物名
	 * @author jiangbin
	 * @param primerName
	 * @return
	 * @date 2013-12-4下午2:17:58
	 */
	public String getPrimerName(String dye) {
		PrimerInfo info = this.get(dye);
		return info == null ? "" : info.getPrimerName();
	}

	/**
	 * 获取引物编号
	 * @author jiangbin
	 * @param primerName
	 * @return
	 * @date 2013-12-4下午2:17:59
	 */
	public String getPrimerCode(String dye) {
		PrimerInfo info = this.get(dye);
		return info == null ? "" : info.getPrimerCode();
	}
}
