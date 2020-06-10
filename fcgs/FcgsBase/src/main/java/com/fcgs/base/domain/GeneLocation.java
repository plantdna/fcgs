package com.fcgs.base.domain;

import java.io.Serializable;

/**
 * 指纹位置信息接口
 * @author Andory
 * @date 2013-7-1上午10:30:07
 */
public interface GeneLocation extends Serializable {
	/**
	 * 获取上样板号
	 * @author Andory
	 * @return
	 * @date 2013-7-1上午10:30:48
	 */
	public String getPlate();

	/**
	 * 设置上样板号
	 * @author Andory
	 * @param plate
	 * @date 2013-7-1上午10:30:50
	 */
	public void setPlate(String plate);

	/**
	 * 获取板孔号
	 * @author Andory
	 * @return
	 * @date 2013-7-1上午10:30:51
	 */
	public String getWell();

	/**
	 * 设置板孔号
	 * @author Andory
	 * @param well
	 * @date 2013-7-1上午10:30:52
	 */
	public void setWell(String well);
}
