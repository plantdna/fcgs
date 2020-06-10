package com.pids.core.sorter.impl;

import com.pids.core.exception.ICoreException;
import com.pids.core.filter.impl.SimpleMapperFilter;
import com.pids.core.sorter.ListSorterTemplate;
import com.pids.core.utils.StringEx;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *  简单的列表排序器，该排序器将给定列表按照指定的顺序进行排序，待排序的对象必须实现{@link SortItem}接口；
 *  该排序器会尽量将指定的列表按照给定参数列表顺序进行排序，若对象不在列表中，则向后排，给定参数列表优先排在前面。
 *  S 要排序的对象，V 拍完序之后的返回对象
 * @author LiuJunGuang
 * @date 2012-12-12下午1:59:28
 */
public class SimpleListSorter<S extends SortItem<V>, V> extends ListSorterTemplate<S, V> {
	private List<String> orders;

	/**
	 * 对给定的sources列表按照orders参数列表进行排序，orders中的元素会排在新列表的前面，不在orders中的元素会排在后面。
	 * 如果给定的sources列表和orders列表为null或空，则返回一个空的列表。
	 * @param sources 待排序的列表对象
	 * @return List 新的列表对象
	 */
	@Override
	public List<V> sort(List<S> sources) throws ICoreException {
		if (CollectionUtils.isEmpty(sources) || CollectionUtils.isEmpty(orders)) {
			return new ArrayList<V>();
		}
		SortItemMapper<V, S> mapper = new SortItemMapper<V, S>();
		mapper.addAll(sources);
		SimpleMapperFilter<V> filter = new SimpleMapperFilter<V>();
		filter.setMapper(mapper.getMapper());
		List<V> sortItems = filter.filter(orders);
		List<V> otherItems = filterOther(sources, orders);
		sortItems.addAll(otherItems);
		return sortItems;
	}

	/**
	 * 过滤出非指定顺序的列表
	 * @author LiuJunGuang
	 * @param sources
	 * @param orders
	 * @return
	 * @date 2012-12-12下午2:17:30
	 */
	protected List<V> filterOther(List<S> sources, List<String> orders) {
		List<V> otherItems = new ArrayList<V>();
		for (S item : sources) {
			if (item == null || StringEx.isNull(item.getKey())) {
				continue;
			}
			String key = item.getKey();
			if (!orders.contains(key)) {
				otherItems.add(item.getValue());
			}
		}
		return otherItems;
	}

	/**
	 * 获取排序规格列表
	 * @author LiuJunGuang
	 * @return list
	 * @date 2012-12-12下午3:14:28
	 */
	public List<String> getOrders() {
		return orders;
	}

	/**
	 * 设置列表的排序规格，可以是对象的某个属性，但必须是String类型
	 * @author LiuJunGuang
	 * @param orders 排序规则列表
	 * @date 2012-12-12下午3:14:30
	 */
	public void setOrders(List<String> orders) {
		this.orders = orders;
	}

}
