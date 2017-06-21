package com.picc.cxf.service;

import javax.jws.WebService;

@WebService
public interface ReportCaseForeign {

	/**
	 * 平安推送理赔结果
	 * @param json
	 * @return
	 */
	public String paicCallbackClaimsResult(String json);
}
