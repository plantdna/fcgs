package com.fcgs.base.genefile;

import com.fcgs.base.domain.handle.GeneHandle;

/**
 * 指纹数据文件详细信息接口，该接口是指纹数据磁盘文件与指纹对象转换的核心接口，
 * 基于此接口即可实现磁盘指纹数据文件与指纹对象的双向自动转换
 * @author Andory
 * @date 2013年9月10日下午8:10:53
 */
public interface GeneFileDetail extends GeneFilePath, GeneHandle {

}
