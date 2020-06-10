package com.fcgs.base.filler;

import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.domain.gene.Marker;
import com.fcgs.base.primer.PrimerInfo;
import com.fcgs.base.primer.utils.PrimerInfoNameMapper;
import com.pids.core.exception.ICoreException;
import com.pids.core.filler.ListFillerTemplate;
import com.pids.core.utils.ListUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * <pre>指纹对象的引物编号信息填充器，主要完成如下功能：
 * 1、填充完成后指纹对象里只包含给定引物列表包含的引物
 * 2、根据引物名找到引物信息并将引物名、引物编号、引物合成编号信息一并替换
 * 注意：由于本功能会修改给定的指纹对象的位点列表，故在使用前若不想修改原指纹则需要先前生成拷贝再调用本功能</pre>
 * @author jiangbin
 * @date 2014年7月10日下午6:02:43
 */

public class GenePrimerCodeFiller {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 填充引物编号到指纹位点中
	 * @author jiangbin
	 * @param genes 指纹数据列表
	 * @param primers 引物信息列表
	 * @return
	 * @throws ICoreException
	 * @date 2014年7月10日下午6:08:18
	 */
	public List<Gene> fill(List<Gene> genes, List<PrimerInfo> primers) throws ICoreException {
		if (CollectionUtils.isEmpty(genes) || CollectionUtils.isEmpty(primers)) {
			return genes;
		}
		_Filler filler = new _Filler(primers);
		for (Gene gene : genes) {
			if (gene == null || gene.isEmpty()) {
				continue;
			}
			List<Marker> targets = filler.fill(gene.getMarkers());
			gene.setMarkers(targets);
		}
		return genes;
	}

	/**
	 * 填充引物信息
	 * @author jiangbin
	 * @param gene 指纹对象
	 * @param primers 引物信息列表
	 * @return
	 * @throws ICoreException
	 * @date 2014年7月10日下午8:04:38
	 */
	public Gene fill(Gene gene, List<PrimerInfo> primers) throws ICoreException {
		if (gene == null || gene.isEmpty() || CollectionUtils.isEmpty(primers)) {
			return gene;
		}
		List<Gene> genes = ListUtils.createList(gene);
		genes = this.fill(genes, primers);
		return CollectionUtils.isEmpty(genes) ? null : genes.get(0);
	}

	private class _Filler extends ListFillerTemplate<Marker, Marker> {
		private final PrimerInfoNameMapper<PrimerInfo> mapper;

		public _Filler(List<PrimerInfo> primers) {
			this.mapper = new PrimerInfoNameMapper<PrimerInfo>();
			this.mapper.addAll(primers);
		}

		@Override
		protected Marker fill(Marker source, boolean isInternal) throws ICoreException {
			if (source == null) {
				return null;
			}
			String primerName = source.getPrimerName();
			if (StringUtils.isBlank(primerName)) {
				if (logger.isDebugEnabled()) {
					logger.warn("位点中无引物名，请检查引物荧光对照关系是否正确，不正确会导致上传时无法设置引物信息到位点!" + source.toString());
				}
				return null;
			}
			PrimerInfo primerInfo = this.mapper.get(primerName);
			if (primerInfo == null) {
				if (logger.isDebugEnabled()) {
					logger.warn("引物[ " + primerName + " ]信息未定义!");
				}
				return source;
			}
			source.setPrimerInfo(primerInfo);
			return source;
		}

	}
}
