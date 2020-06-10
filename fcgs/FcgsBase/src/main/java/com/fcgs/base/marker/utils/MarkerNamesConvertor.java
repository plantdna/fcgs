package com.fcgs.base.marker.utils;

import com.pids.core.exception.ICoreException;
import com.pids.core.filter.utils.SimpleMapperFilter;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 引物名-引物编号的互相转换
 * @author jiangbin
 * @date 2012-11-24上午1:07:25
 */

public class MarkerNamesConvertor {

	/**
	 * 将缺失位点列表、无差异位点列表、差异位点列表按引物名-引物编号映射关系表转换成引物编号
	 */
	public MarkerNamesViwer convert(MarkerNamesViwer source, Map<String, String> primerNameCodeMapper)
			throws ICoreException {
		if (source == null) {
			return null;
		}
		if (primerNameCodeMapper == null || primerNameCodeMapper.isEmpty()) {
			return source;
		}
		source.setDiffMarkerNames(convert(source.getDiffMarkerNames(), primerNameCodeMapper));
		source.setNoDiffMarkerNames(convert(source.getNoDiffMarkerNames(), primerNameCodeMapper));
		source.setMissMarkerNames(convert(source.getMissMarkerNames(), primerNameCodeMapper));
		return source;
	}

	/**
	 * 将缺失位点列表、无差异位点列表、差异位点列表按引物编号-引物名映射关系表转换成引物名
	 */
	public MarkerNamesViwer reconvert(MarkerNamesViwer target, Map<String, String> primerCodeNameMapper)
			throws ICoreException {
		if (target == null) {
			return null;
		}
		if (primerCodeNameMapper == null || primerCodeNameMapper.isEmpty()) {
			return target;
		}
		target.setDiffMarkerNames(convert(target.getDiffMarkerNames(), primerCodeNameMapper));
		target.setNoDiffMarkerNames(convert(target.getNoDiffMarkerNames(), primerCodeNameMapper));
		target.setMissMarkerNames(convert(target.getMissMarkerNames(), primerCodeNameMapper));
		return target;
	}

	/**
	 * 将给定的Key列表按照给定的规则转换成值列表
	 * @author jiangbin
	 * @param keys key列表
	 * @param rules　转换规则表
	 * @return　值列表
	 * @throws ICoreException
	 * @throws ICoreException 
	 * @date 2012-11-24上午1:05:16
	 */
	protected List<String> convert(List<String> keys, Map<String, String> rules) throws ICoreException {
		if (CollectionUtils.isEmpty(keys)) {
			return keys;
		}
		SimpleMapperFilter<String> filter = new SimpleMapperFilter<String>();
		List<String> values = filter.filter(keys, rules);
		if (CollectionUtils.isNotEmpty(values)) {
			Collections.sort(values);//排序
		}
		return values;
	}

}
