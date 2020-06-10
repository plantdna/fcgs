package com.fcgs.base.formatter;

import com.fcgs.base.domain.gene.Allele;
import com.fcgs.base.domain.gene.Marker;
import com.pids.core.exception.ICoreException;
import com.pids.core.formatter.ListFormatterTemplate;
import com.pids.core.threadlocal.ThreadLocalRegister;
import com.pids.core.utils.ListUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 格式化Allele为页面显示所需的格式：Allele1/Allele2,
 * 默认的空数据位点表示为：--/--
 * @author Andory
 * @date 2012-7-27下午08:54:27
 */

public class MarkerFormatter extends ListFormatterTemplate<Marker, String> {
	/**
	 * @fields emptyMarker 空数据Marker的格式化结果
	 */
	public static String emptyMarker = "--/--";
	private final static ThreadLocal<String> empty = ThreadLocalRegister.regist();

	public MarkerFormatter() {
	}

	public MarkerFormatter(String emptyMarker) {
		empty.set(emptyMarker);
	}

	@Override
	public List<String> format(List<Marker> sources) throws ICoreException {
		try {
			return super.format(sources);
		} finally {
			empty.remove();
		}
	}

	@Override
	protected String format(Marker source, boolean isInternal) throws ICoreException {
		try {
			if (source == null) {
				return getEmptyMarker();
			}
			List<Allele> alleles = source.getAlleles();
			if (CollectionUtils.isEmpty(alleles)) {
				return getEmptyMarker();
			}
			List<String> targets = new ArrayList<>();
			for (Allele allele : alleles) {
				targets.add(getAllele(allele.getAllele()) + "");
			}
			return ListUtils.list2Str(targets, "/");
		} finally {
			if (!isInternal) {
				empty.remove();
			}
		}
	}

	/**
	 * 设置空Marker占位符
	 * @author jiangbin
	 * @param emptyMarker
	 * @date 2014年11月20日上午12:17:54
	 */
	public void setEmptyMarker(String emptyMarker) {
		empty.set(emptyMarker);
	}

	/**
	 * 获取空Marker占位符
	 * @author jiangbin
	 * @return
	 * @date 2014年11月20日上午12:17:53
	 */
	public String getEmptyMarker() {
		return empty.get() == null ? emptyMarker : empty.get();
	}

	/**
	 * 格式化浮点数Allele为整型Allele
	 * @author Andory
	 * @param allele
	 * @return
	 * @date 2013-7-24上午11:25:58
	 */
	protected int getAllele(float allele) {
		return (int) allele;
	}

}
