package com.fcgs.base.filler;

import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.domain.gene.Marker;
import com.fcgs.base.primer.PrimerInfo;
import com.fcgs.base.primer.utils.PrimerInfoDyeMapper;
import com.pids.core.exception.ICoreException;
import com.pids.core.filler.ListFillerTemplate;
import com.pids.core.utils.ListUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * <pre>指纹对象的引物编号信息填充器，主要完成如下功能：
 * 1、填充完成后指纹对象里只包含给定引物列表包含的引物
 * 2、根据引物合成编号找到引物信息并将引物名、引物编号、引物合成编号信息一并替换
 * 注意：由于本功能会修改给定的指纹对象的位点列表，故在使用前若不想修改原指纹则需要先前生成拷贝再调用本功能</pre>
 * @author jiangbin
 * @date 2014年7月10日下午6:02:43
 */

public class GenePrimerInfoByDyeFiller {

	/**
	 * 根据引物合成编号填充引物信息到指纹位点中
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
		private final PrimerInfoDyeMapper<PrimerInfo> mapper;

		public _Filler(List<PrimerInfo> primers) {
			this.mapper = new PrimerInfoDyeMapper<>();
			this.mapper.addAll(primers);
		}

		@Override
		protected Marker fill(Marker source, boolean isInternal) throws ICoreException {
			if (source == null) {
				return null;
			}
			String dye = source.getDye();
			if (StringUtils.isBlank(dye)) {
				return null;
			}
			PrimerInfo primerInfo = this.mapper.get(dye);
			if (primerInfo == null) {
				return null;
			}
			source.setPrimerInfo(primerInfo);
			return source;
		}

	}
}
