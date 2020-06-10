package com.fcgs.base.primer;

import java.io.Serializable;

/**
 * 引物信息接口，包含：引物名、引物合成编号、引物编号
 * @author Andory
 * @date 2013-6-20下午3:44:41
 */
public interface PrimerInfo extends Serializable {

	/**
	 * 设置引物名称
	 * @author Andory
	 * @param primer
	 * @date 2013-6-18下午6:03:35
	 */
	public void setPrimerName(String primerName);

	/**
	 * 获取引物名称
	 * @author Andory
	 * @return
	 * @date 2013-6-18下午6:03:33
	 */
	public String getPrimerName();

	/**
	 * 设置引物合成编号
	 * @author Andory
	 * @param dye
	 * @date 2013-6-18下午6:03:32
	 */
	public void setDye(String dye);

	/**
	 * 获取引物合成编号
	 * @author Andory
	 * @return
	 * @date 2013-6-18下午6:03:32
	 */
	public String getDye();

	/**
	 * 设置引物编号
	 * @author Andory
	 * @param primerCode
	 * @date 2013-6-18下午6:04:53
	 */
	public void setPrimerCode(String primerCode);

	/**
	 * 获取引物编号
	 * @author Andory
	 * @return
	 * @date 2013-6-18下午6:04:52
	 */
	public String getPrimerCode();

	/**
	 * 设置引物信息对象
	 * @author Andory
	 * @param primerInfo
	 * @date 2013-6-20下午3:47:01
	 */
	public void setPrimerInfo(PrimerInfo primerInfo);

}