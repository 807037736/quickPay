package com.picc.cxf.schema.model;

import java.io.Serializable;

public class CaseInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -98088040316312287L;
	
	private String casedate;
	private String provID;
	private String cityID;
	private String distID;
	private String address;
	private String weather;
	private String isDue;// 是否定责
	private String resultPicture;// 责任书图片
	private String longitude;//经度
	private String latitude;//纬度
	public String getCasedate() {
		return casedate;
	}
	public void setCasedate(String casedate) {
		this.casedate = casedate;
	}
	public String getProvID() {
		return provID;
	}
	public void setProvID(String provID) {
		this.provID = provID;
	}
	public String getCityID() {
		return cityID;
	}
	public void setCityID(String cityID) {
		this.cityID = cityID;
	}
	public String getDistID() {
		return distID;
	}
	public void setDistID(String distID) {
		this.distID = distID;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getIsDue() {
		return isDue;
	}
	public void setIsDue(String isDue) {
		this.isDue = isDue;
	}
	public String getResultPicture() {
		return resultPicture;
	}
	public void setResultPicture(String resultPicture) {
		this.resultPicture = resultPicture;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

}
