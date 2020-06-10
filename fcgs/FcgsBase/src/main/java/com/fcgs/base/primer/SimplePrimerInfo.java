package com.fcgs.base.primer;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 引物信息接口的简单实现类
 * 
 * @author Andory
 * @date 2013-6-20下午3:46:00
 */
public class SimplePrimerInfo implements PrimerInfo {
	private static final long serialVersionUID = 9021785392890146678L;
	private String primerName;
	private String primerCode;
	private String dye;

	@Override
	public String getPrimerName() {
		return primerName;
	}

	@Override
	public void setPrimerName(String primerName) {
		this.primerName = primerName;
	}

	@Override
	public String getPrimerCode() {
		return primerCode;
	}

	@Override
	public void setPrimerCode(String primerCode) {
		this.primerCode = primerCode;
	}

	@Override
	public String getDye() {
		return dye;
	}

	@Override
	public void setDye(String dye) {
		this.dye = dye;
	}

	@Override
	public void setPrimerInfo(PrimerInfo primerInfo) {
		if (primerInfo == null) {
			return;
		}
		this.setPrimerCode(primerInfo.getPrimerCode());
		this.setPrimerName(primerInfo.getPrimerName());
		this.setDye(primerInfo.getDye());
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
