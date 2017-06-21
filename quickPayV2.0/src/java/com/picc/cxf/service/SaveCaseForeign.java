package com.picc.cxf.service;

import javax.jws.WebService;

@WebService
public interface SaveCaseForeign {
	public String uploadImg(String json);

	public String uploadCase(String json);

	/**
	 * 获取省
	 * 
	 * @return
	 */
	String getProvince();

	/**
	 * 获取市
	 * 
	 * @param json
	 * @return
	 */
	String getCity(String json);

	/**
	 * 获取区
	 * 
	 * @param json
	 * @return
	 */
	String getDist(String json);

	/**
	 * 获取保险公司
	 * 
	 * @return
	 */
	String getCompany();
	
	/**
	 * 获取天气
	 * 
	 * @return
	 */
	String getWeatherType();

	/**
	 * 获取案件信息和当事人信息
	 * 
	 * @param json
	 * @return
	 */
	String getCaseInfo(String json);
	
	/**
	 * 获取案件信息和当事人信息
	 * 
	 * @param json
	 * @return
	 */
	String getCaseInfoPic(String json);
	/**
	 * 获取车损详情
	 * 
	 * @param json
	 * @return
	 */
	String getLoseInfoPic(String json);
	
	/**
	 * 生成图片组号
	 * @param json
	 * @return
	 */
	String getGroupID(String json);

}
