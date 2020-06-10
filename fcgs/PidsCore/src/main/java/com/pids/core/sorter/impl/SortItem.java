package com.pids.core.sorter.impl;


/**
 *  要使用{@link SimpleListSorter}类对列表进行排序时，列表中的对象必须实现该接口
 * @author LiuJunGuang
 * @date 2012-12-12下午3:18:58
 */
public interface SortItem<V> {
	/**
	 * 获取key，该值用于确定对象在排序规则中的顺序
	 * @author LiuJunGuang
	 * @return string
	 * @date 2012-12-12下午3:18:55
	 */
	public String getKey();

	/**
	 * 获取value，该值用于确定排序后的新列表中的元素类型
	 * @author LiuJunGuang
	 * @return 泛型 V
	 * @date 2012-12-12下午3:18:56
	 */
	public V getValue();
}
