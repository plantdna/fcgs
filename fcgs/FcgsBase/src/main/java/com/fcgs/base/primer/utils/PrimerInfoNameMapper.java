package com.fcgs.base.primer.utils;

import com.fcgs.base.primer.PrimerInfo;
import com.pids.core.mapper.MapperTemplate;

/**
 * 引物名称信息Mapper,key/value--引物名/引物信息对象
 * @author jiangbin
 * @date 2013-12-4下午2:07:56
 */
public class PrimerInfoNameMapper<V extends PrimerInfo> extends MapperTemplate<V, V> {

	private static final long serialVersionUID = -4693875521133260508L;

	@Override
	protected String getMapperKey(V object) {
		return object.getPrimerName();
	}

	@Override
	protected V getMapperValue(V object) {
		return object;
	}

	/**
	 * 获取引物编号
	 * @author jiangbin
	 * @param primerName
	 * @return
	 * @date 2013-12-4下午2:17:58
	 */
	public String getPrimerCode(String primerName) {
		PrimerInfo info = this.get(primerName);
		return info == null ? "" : info.getPrimerCode();
	}

	/**
	 * 获取引物引物合成编号
	 * @author jiangbin
	 * @param primerName
	 * @return
	 * @date 2013-12-4下午2:17:59
	 */
	public String getDye(String primerName) {
		PrimerInfo info = this.get(primerName);
		return info == null ? "" : info.getDye();
	}

}
