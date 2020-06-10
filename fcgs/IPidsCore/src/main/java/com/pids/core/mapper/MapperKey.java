package com.pids.core.mapper;

/**
 * Mapper的Key数据接口，相关数据对象扩展此接口即可获得使用
 * {@link VGroupMapper}和
 * {@link VMapper}的能力
 * @author jiangbin
 * @date 2013-4-20上午9:57:02
 */
public interface MapperKey {
	/**
	 * 获取Mapper的Key
	 * @author jiangbin
	 * @return
	 * @date 2013-4-20上午10:18:35
	 */
	public String getMapperKey();
}
