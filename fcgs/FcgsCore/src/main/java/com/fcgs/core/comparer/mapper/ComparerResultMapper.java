package com.fcgs.core.comparer.mapper;

import com.fcgs.core.comparer.domain.ComparerResult;
import com.pids.core.mapper.MapperTemplate;
import org.apache.commons.lang3.StringUtils;

/**
 * 指纹比对结果Mapper,key/value--源指纹ID-目标指纹ID/比对结果对象
 * @author jiangbin
 * @date 2012-10-29上午10:44:31
 */
public class ComparerResultMapper<T extends ComparerResult> extends MapperTemplate<T, T> {

	private static final long serialVersionUID = -994571574249502174L;

	@Override
	protected String getMapperKey(T object) {
		if (object == null) {
			return null;
		}
		return this.getMapperKey(object.getSourceGeneId(), object.getTargetGeneId());
	}

	/**
	 * <pre>获取当前mapper格式的的Key值,格式如：
	 * 1、先对sourceID和targetID进行排序
	 * 2、格式化成:sourceID-targetID或targetID-sourceID</pre>
	 * @author jiangbin
	 * @param sourceID
	 * @param targetID
	 * @return
	 * @date 2013-3-28下午2:46:03
	 */
	public String getMapperKey(String sourceID, String targetID) {
		if (StringUtils.isBlank(sourceID) || StringUtils.isBlank(targetID)) {
			return null;
		}
		sourceID = StringUtils.stripToEmpty(sourceID).toLowerCase();
		targetID = StringUtils.stripToEmpty(targetID).toLowerCase();
		if (sourceID.compareTo(targetID) > 0) {
			return String.format("%s-%s", sourceID, targetID);
		} else {
			return String.format("%s-%s", targetID, sourceID);
		}

	}

	/**
	 * 获取指定记录ID对所对应的比对结果
	 * @author jiangbin
	 * @param sourceID 源ID
	 * @param targetID 目标ID
	 * @return
	 * @date 2013-3-28下午2:50:33
	 */
	public T get(String sourceID, String targetID) {
		return super.get(this.getMapperKey(sourceID, targetID));
	}

	@Override
	protected T getMapperValue(T object) {
		return object;
	}

}
