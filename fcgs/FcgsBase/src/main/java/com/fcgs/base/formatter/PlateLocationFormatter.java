package com.fcgs.base.formatter;

import com.fcgs.base.domain.GeneLocation;
import com.fcgs.base.domain.gene.Gene;
import com.pids.core.exception.ICoreException;
import com.pids.core.formatter.Formatter;
import com.pids.core.pair.PairUtils;
import com.pids.core.pair.StringPair;
import com.pids.core.utils.ListUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 板位置格式化，格式化后的格式为:上样板号,板孔号
 * 
 * @author jiangbin
 * @date 2012-11-26上午1:37:30
 */

public class PlateLocationFormatter implements Formatter<StringPair, String> {
	/**
	 * @fields EMPTY_LOCATION 空位置占位符 --
	 */
	public static String EMPTY_LOCATION = "--";

	@Override
	public String format(StringPair source) throws ICoreException {
		if (source == null) {
			return EMPTY_LOCATION;
		}
		if (StringUtils.isBlank(source.getSource()) && StringUtils.isBlank(source.getTarget())) {
			return EMPTY_LOCATION;
		}
		StringBuffer sb = new StringBuffer();
		if (StringUtils.isNotEmpty(source.getSource())) {
			sb.append(source.getSource());
		}
		if (StringUtils.isNotEmpty(source.getTarget())) {
			if (sb.length() > 0) {
				sb.append(",");
			}
			sb.append(source.getTarget());
		}
		return sb.toString();
	}

	/**
	 * 格式化指纹位置信息
	 * 
	 * @author Andory
	 * @param source
	 * @return
	 * @throws ICoreException
	 * @date 2013-7-1上午11:04:59
	 */
	public String format(GeneLocation source) throws ICoreException {
		StringPair pair = null;
		if (source != null) {
			pair = PairUtils.create(source.getPlate(), source.getWell());
		}
		return this.format(pair);
	}

	/**
	 * 格式化指纹的孔位信息
	 * 
	 * @author jiang
	 * @param source
	 * @return
	 * @throws ICoreException
	 * @date 2018年6月8日下午12:39:38
	 */
	public String format(Gene source) throws ICoreException {
		if (source == null) {
			return EMPTY_LOCATION;
		}
		GeneLocation location = source.getLocation();
		return this.format(location);
	}

	/**
	 * 格式化指纹列表的孔位信息
	 * 
	 * @author jiang
	 * @param sources
	 * @return
	 * @throws ICoreException
	 * @date 2018年6月8日下午12:39:12
	 */
	public List<String> format(List<Gene> sources) throws ICoreException {
		if (CollectionUtils.isEmpty(sources)) {
			return ListUtils.createList();
		}
		List<String> targets = new ArrayList<>();
		for (Gene gene : sources) {
			targets.add(this.format(gene));
		}
		return targets;
	}

	/**
	 * 获取电泳板孔位置键值
	 * 
	 * @author Jiangbin
	 * @param source
	 * @return
	 * @throws ICoreException
	 * @date 2014-4-21下午9:01:16
	 */
	public String getGelLocationKey(GeneLocation source) throws ICoreException {
		if (source == null) {
			return "";
		}
		if (StringUtils.isBlank(source.getPlate()) && StringUtils.isBlank(source.getWell())) {
			return "";
		}
		if (StringUtils.isBlank(source.getPlate())) {
			return source.getWell().toUpperCase();
		}
		if (StringUtils.isBlank(source.getWell())) {
			return source.getPlate().toUpperCase();
		}
		return String.format("%s-%s", source.getPlate(), source.getWell()).toUpperCase();
	}
}
