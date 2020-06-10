package com.pids.core.container;

import com.pids.core.id.ID;

import java.io.Serializable;
import java.util.List;

/**
 * 容器信息顶级接口
 */
public interface Container<S extends ID> extends Serializable {
	/**
	 * 获取样品编号关联信息表数据列表
	 * @return
	 */
	public List<S> getSourceList();

	/**
	 * 设置样品编号关联信息表数据列表
	 * @param sourceList
	 */
	public void setSourceList(List<S> sourceList);

	/**
	 * 获取数据长度
	 * @author DMT3工具自动生成
	 * @return
	 */
	public int size();

	/**
	 * 添加数据到容器
	 * @param source
	 */
	public void add(S source);

	/**
	 * 添加数据列表到容器
	 * @param sourceList
	 */
	public void add(List<S> sourceList);

	/**
	 * 清空数据列表
	 */
	public void clear();

	/**
	 * 获取数据列表的ID号列表
	 * @return
	 */
	public List<String> getIdList();

	/**
	 * 获取ID对应记录
	 * @param id
	 * @return
	 */
	public S getById(String id);

	/**
	 * 获取ID列表对应记录
	 * @param idList
	 * @return
	 */
	public List<S> getById(List<String> idList);

	/**
	 * 判定是否存在ID对应记录
	 * @param id
	 * @return
	 */
	public boolean exists(String id);
}
