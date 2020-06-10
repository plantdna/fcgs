package com.fcgs.base.filter;

import com.fcgs.base.primer.PrimerInfo;
import com.fcgs.base.primer.utils.PrimerInfoCodeMapper;
import com.fcgs.base.primer.utils.PrimerInfoDyeMapper;
import com.fcgs.base.primer.utils.PrimerInfoNameMapper;
import com.pids.core.exception.ICoreException;

import java.util.List;

/**
 * 过滤引物信息的引物名、引物编号、引物合成编号字段
 * @author jiangbin
 * @date 2014年3月21日下午7:33:34
 */

public class PrimerInfoFilter<S extends PrimerInfo> {

	/**
	 * 过滤引物名列表
	 * @author jiangbin
	 * @param primerInfos
	 * @return
	 * @throws ICoreException
	 * @date 2014年3月21日下午7:34:31
	 */
	public List<String> filterPrimerNames(List<S> primerInfos) throws ICoreException {
		PrimerInfoNameMapper<S> mapper = new PrimerInfoNameMapper<>();
		mapper.addAll(primerInfos);
		return mapper.getKeys();
	}

	/**
	 * 过滤引物编号列表
	 * @author jiangbin
	 * @param primerInfos
	 * @return
	 * @throws ICoreException
	 * @date 2014年3月21日下午7:34:32
	 */
	public List<String> filterPrimerCodes(List<S> primerInfos) throws ICoreException {
		PrimerInfoCodeMapper<S> mapper = new PrimerInfoCodeMapper<>();
		mapper.addAll(primerInfos);
		return mapper.getKeys();
	}

	/**
	 * 过滤引物合成编号列表
	 * @author jiangbin
	 * @param primerInfos
	 * @return
	 * @throws ICoreException
	 * @date 2014年3月21日下午7:34:36
	 */
	public List<String> filterDyes(List<S> primerInfos) throws ICoreException {
		PrimerInfoDyeMapper<S> mapper = new PrimerInfoDyeMapper<>();
		mapper.addAll(primerInfos);
		return mapper.getKeys();
	}
}
