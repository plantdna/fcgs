package com.fcgs.base.domain.handle;

import com.fcgs.base.domain.GeneLocation;

/**
 * 指纹位置信息句柄实现类
 * @author jiangbin
 * @date 2014年3月21日下午2:08:43
 */
public class SimpleGeneLocationHandle implements GeneLocationHandle {
	private static final long serialVersionUID = -7496936557341872929L;
	private GeneLocation location;

	@Override
	public void setLocation(GeneLocation location) {
		this.location = location;
	}

	@Override
	public GeneLocation getLocation() {
		return location;
	}
}
