package com.picc.cxf.schema.model;

import java.io.Serializable;

public class PersonInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9160512681343353249L;
	private String identityno;
	private String name;
	private String mobile;
	private String carno;
	private String cartype;
	private String drivingtype;
	private String insucompany;
	private String dict;
	private String provID;
	private String cityID;
	private String distID;
	private String address;
	private String resp;
	private String groupID;
	private String accidentNotes;
	
	public String getIdentityno() {
		return identityno;
	}
	public void setIdentityno(String identityno) {
		this.identityno = identityno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCarno() {
		return carno;
	}
	public void setCarno(String carno) {
		this.carno = carno;
	}
	public String getCartype() {
		return cartype;
	}
	public void setCartype(String cartype) {
		this.cartype = cartype;
	}
	public String getDrivingtype() {
		return drivingtype;
	}
	public void setDrivingtype(String drivingtype) {
		this.drivingtype = drivingtype;
	}
	public String getInsucompany() {
		return insucompany;
	}
	public void setInsucompany(String insucompany) {
		this.insucompany = insucompany;
	}
	public String getDict() {
		return dict;
	}
	public void setDict(String dict) {
		this.dict = dict;
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
	public String getResp() {
		return resp;
	}
	public void setResp(String resp) {
		this.resp = resp;
	}
	public String getGroupID() {
		return groupID;
	}
	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}
	public String getAccidentNotes() {
		return accidentNotes;
	}
	public void setAccidentNotes(String accidentNotes) {
		this.accidentNotes = accidentNotes;
	}

}
