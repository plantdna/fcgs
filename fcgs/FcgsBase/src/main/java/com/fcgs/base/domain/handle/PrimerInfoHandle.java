package com.fcgs.base.domain.handle;

import com.fcgs.base.primer.PrimerInfo;

import java.io.Serializable;

/**
 * 引物信息句柄接口
 * @author jiangbin
 * @date 2014年3月21日下午12:01:22
 */
public interface PrimerInfoHandle extends Serializable {
	/**
	 * 获取引物信息对象
	 * @author jiangbin
	 * @return
	 * @date 2014年3月21日下午12:01:23
	 */
	public PrimerInfo getPrimerInfo();

	/**
	 * 设置引物信息对象
	 * @author jiangbin
	 * @param primerInfo
	 * @date 2014年3月21日下午12:01:24
	 */
	public void setPrimerInfo(PrimerInfo primerInfo);
}
