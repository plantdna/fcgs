package com.fcgs.base.domain.handle;

import com.fcgs.base.primer.PrimerInfo;

/**
 * 引物信息句柄对象实现类
 * @author jiangbin
 * @date 2014年3月21日下午12:05:05
 */
public class SimplePrimerInfoHandle implements PrimerInfoHandle {
	private static final long serialVersionUID = -2157575711645404731L;
	private PrimerInfo primerInfo;

	@Override
	public PrimerInfo getPrimerInfo() {
		return primerInfo;
	}

	@Override
	public void setPrimerInfo(PrimerInfo primerInfo) {
		this.primerInfo = primerInfo;
	}

}
