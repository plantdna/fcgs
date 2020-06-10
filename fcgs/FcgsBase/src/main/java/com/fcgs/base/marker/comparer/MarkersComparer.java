package com.fcgs.base.marker.comparer;

import com.fcgs.base.domain.gene.Marker;
import com.pids.core.exception.ICoreException;

import java.util.List;

/**
 * 多Marker比较器，主要是用于将指定Marker与一个Marker列表进行比对
 * @author Andory
 * @date 2012-8-4下午1:54:34
 */
public interface MarkersComparer {
	/**
	 * 单Marker与多Marker列表比对，注意：本比对算法统计得到的权重值并不包含源Marker的权重
	 * @author Andory
	 * @param sourceMarker 源Marker
	 * @param targetMarkers 目标Marker列表
	 * @return 无差异Marker数
	 * @throws ICoreException
	 * @date 2012-8-4下午1:57:32
	 */
	public int comparer(Marker sourceMarker, List<Marker> targetMarkers) throws ICoreException;

	/**
	 * 获取无差异Marker数
	 * @author Andory
	 * @return
	 * @date 2012-8-4下午1:59:43
	 */
	public int getNoDiffMarkerCount();

	/**
	 * 设置无差异Marker数
	 * @author Andory
	 * @param noDiffMarkerCount 无差异Marker数
	 * @date 2012-8-4下午1:59:44
	 */
	public void setNoDiffMarkerCount(int noDiffMarkerCount);

	/**
	 * 获取无差异Marker权重和
	 * @author Andory
	 * @return
	 * @date 2012-8-4下午1:59:45
	 */
	public double getNoDiffMarkerWeightCount();

	/**
	 * 设置无差异Marker权重和
	 * @author Andory
	 * @param noDiffMarkerWeightCount 无差异Marker权重和
	 * @date 2012-8-4下午1:59:47
	 */
	public void setNoDiffMarkerWeightCount(double noDiffMarkerWeightCount);

	/**
	 * 设置Marker比较器
	 * @author Andory
	 * @param markerComparer Marker比较器
	 * @date 2012-8-4下午2:04:17
	 */
	public void setMarkerComparer(MarkerComparer markerComparer);

	/**
	 * 获取Marker比较器
	 * @author Andory
	 * @return
	 * @date 2012-8-4下午2:04:19
	 */
	public MarkerComparer getMarkerComparer();
}
