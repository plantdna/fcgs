package com.fcgs.base.mapper;

import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.domain.gene.Marker;
import com.pids.core.mapper.MapperTemplate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 指纹Marker信息映射器:Key/Value==>String(GeneId)/MarkerMapper，主
 * 要是用于快速获取指定Gene记录相应Marker信息，通常用来作高速缓存
 * 注意：本对象中相关添加方法均采用合并的方式，即若存在给定id号的指纹映射信息时将合并给定指纹到原指纹中
 * @author jiangbin
 * @date 2012-5-5上午4:13:12
 */
public class GeneMapper extends MapperTemplate<MarkerMapper, Gene> {
	private static final long serialVersionUID = -3875002245833276779L;

	@Override
	protected String getMapperKey(Gene object) {
		return object.getGeneId();
	}

	@Override
	protected MarkerMapper getMapperValue(Gene object) {
		if (object != null && object.size() > 0) {
			MarkerMapper mapper = new MarkerMapper();
			mapper.setGeneId(getMapperKey(object));
			mapper.addAll(object.getMarkers());
			return mapper;
		} else {
			return null;
		}
	}

	/**
	 * <pre>获取位点的Mapper==>Map&lt;GeneId, Map&lt;PrimerName, Marker&gt;&gt;</pre>
	 * @author jiangbin
	 * @return
	 * @date 2012-11-22下午2:22:44
	 */
	public Map<String, Map<String, Marker>> getMarkerMapper() {
		Map<String, Map<String, Marker>> map = new HashMap<String, Map<String, Marker>>();
		for (String key : this.keySet()) {
			MarkerMapper markerMapper = this.get(key);
			if (markerMapper == null || markerMapper.isEmpty()) {
				continue;
			}
			map.put(key, markerMapper.getMapper());
		}
		return map;
	}

	/**
	 * 以给定指纹ID顺序获取指定引物名的位点列表
	 * @author Andory
	 * @param geneIds 指纹ID列表
	 * @param primerName 引物名
	 * @return
	 * @date 2013-7-24上午11:15:28
	 */
	public List<Marker> get(List<String> geneIds, String primerName) {
		if (CollectionUtils.isEmpty(geneIds)) {
			return null;
		}
		List<Marker> markers = new ArrayList<Marker>();
		for (String geneId : geneIds) {
			MarkerMapper mapper = this.get(geneId);
			markers.add(mapper.get(primerName));
		}
		return markers;
	}

	/**
	 * 创建指纹位点信息Mapper
	 * @author Andory
	 * @param genes
	 * @return
	 * @date 2013年9月14日下午2:40:40
	 */
	public static GeneMapper createMapper(List<Gene> genes) {
		GeneMapper mapper = new GeneMapper();
		mapper.addAll(genes);
		return mapper;
	}

	/**
	 * 获取指定位点列表
	 * @author jiangbin
	 * @param markerName 位点引物名
	 * @return
	 * @date 2015年5月22日下午5:59:16
	 */
	public List<Marker> getMarkers(String markerName) {
		if (StringUtils.isBlank(markerName)) {
			return null;
		}
		List<Marker> targets = new ArrayList<>();
		for (String geneId : this.keySet()) {
			MarkerMapper mapper = this.get(geneId);
			if (mapper == null || mapper.isEmpty()) {
				continue;
			}
			Marker marker = mapper.get(markerName);
			if (marker != null) {
				targets.add(marker);
			}
		}
		return targets;
	}
}
