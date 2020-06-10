package com.fcgs.base.domain;

import com.fcgs.base.domain.handle.GeneLocationHandle;
import com.fcgs.base.domain.handle.PlatformHandle;
import com.fcgs.base.domain.handle.SampleDnaHandle;

import java.io.Serializable;

/**
 * 指纹信息劫持类,定义了一些基本的属性特征
 * @author jiangbin
 * @date 2014年3月21日下午3:07:50
 */
public interface GeneSupport extends Serializable, GeneLocationHandle, SampleDnaHandle<SampleDna>, PlatformHandle {
	
}
