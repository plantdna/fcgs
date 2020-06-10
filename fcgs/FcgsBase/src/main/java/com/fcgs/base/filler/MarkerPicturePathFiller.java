package com.fcgs.base.filler;

import com.fcgs.base.domain.SampleDna;
import com.fcgs.base.domain.gene.Gene;
import com.fcgs.base.domain.gene.Marker;
import com.pids.core.exception.ICoreException;
import com.pids.core.filler.ListFillerTemplate;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.List;

/**
 * <pre>指纹位点图片路径填充器,构建好的图片路径，这是相对路径，格式如:
 * 	<b>用户ID/年/月/日/电泳板编号/孔位号/样品编号_引物合成编号.PNG</b>
 * 示例如下:
 * 	<b>SSRADMIN/2014/01/22/GJR2011-PLATE7-P--P1-0920/A01/NE116_M60.PNG</b>
 * 其中"样品编号"始终是全大写格式的字符串
 * </pre>
 * @author jiangbin
 * @date 2013-4-19上午11:08:35
 */
public class MarkerPicturePathFiller extends ListFillerTemplate<Marker, Marker> {
	private String userId;
	private String gelRecordId;
	private String well;
	private String samBarcode;

	@Override
	protected Marker fill(Marker source, boolean isInternal) throws ICoreException {
		if (source.isEmpty()) {
			return source;
		}
		String dye = StringUtils.stripToEmpty(source.getDye());
		String picturePath = this.createMarkerPicturePath(dye);
		source.setPicture(picturePath);
		return source;
	}

	/**
	 * 填充指纹各位点的图像路径
	 * @author jiangbin
	 * @param source
	 * @return
	 * @throws ICoreException
	 * @date 2013-4-19上午11:14:30
	 */
	public Gene fill(Gene source) throws ICoreException {
		if (source == null || source.isEmpty()) {
			return source;
		}
		SampleDna dna = source.getSample();
		String gelRecordId = StringUtils.stripToEmpty(source.getLocation().getPlate());
		String well = StringUtils.stripToEmpty(source.getLocation().getWell());
		String userId = StringUtils.stripToEmpty(dna.getDnaManager());
		String samBarcode = StringUtils.stripToEmpty(dna.getSamBarcode());
		this.setGelRecordId(gelRecordId);
		this.setWell(well);
		this.setUserId(userId);
		this.setSamBarcode(samBarcode);
		List<Marker> markers = this.fill(source.getMarkers());
		source.setMarkers(markers);
		return source;
	}

	/**
	 * 构建单个位点路径
	 * @author Jiangbin
	 * @param dye
	 * @return
	 * @date 2014-4-17上午3:02:01
	 */
	protected String createMarkerPicturePath(String dye) {
		String date = DateFormatUtils.format(new Date(), "yyyy/MM/dd");
		return String.format("%s/%s/%s/%s/%s_%s.png", userId, date, gelRecordId, well, samBarcode, dye).toUpperCase();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGelRecordId() {
		return gelRecordId;
	}

	public void setGelRecordId(String gelRecordId) {
		this.gelRecordId = gelRecordId;
	}

	public String getWell() {
		return well;
	}

	public void setWell(String well) {
		this.well = well;
	}

	public String getSamBarcode() {
		return samBarcode;
	}

	public void setSamBarcode(String samBarcode) {
		this.samBarcode = samBarcode;
	}

}
