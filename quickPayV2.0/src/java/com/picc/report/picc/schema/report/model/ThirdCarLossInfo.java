package com.picc.report.picc.schema.report.model;

import java.io.Serializable;

import com.ymt.annotation.ClassConvert;

/**
 * @author ff
 * 2017年3月18日
 * 三者车辆包含信息
 */
@ClassConvert
public class ThirdCarLossInfo implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String lossItemType;
	
	private String licenseNo;
	
	private String licenseType;
	
	private String company;
	
	private String thirdCarBrandCode;
	
	private String thirdCarBrandName;
	
	private String linkMobile;
	
	private String checkAddress;

	public String getLossItemType() {
		return lossItemType;
	}

	public void setLossItemType(String lossItemType) {
		this.lossItemType = lossItemType;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getThirdCarBrandCode() {
		return thirdCarBrandCode;
	}

	public void setThirdCarBrandCode(String thirdCarBrandCode) {
		this.thirdCarBrandCode = thirdCarBrandCode;
	}

	public String getThirdCarBrandName() {
		return thirdCarBrandName;
	}

	public void setThirdCarBrandName(String thirdCarBrandName) {
		this.thirdCarBrandName = thirdCarBrandName;
	}

	public String getLinkMobile() {
		return linkMobile;
	}

	public void setLinkMobile(String linkMobile) {
		this.linkMobile = linkMobile;
	}

	public String getCheckAddress() {
		return checkAddress;
	}

	public void setCheckAddress(String checkAddress) {
		this.checkAddress = checkAddress;
	}

	@Override
	public String toString() {
		return "ThirdCarLossInfo [lossItemType=" + lossItemType + ", licenseNo=" + licenseNo + ", licenseType=" + licenseType + ", company=" + company + ", thirdCarBrandCode=" + thirdCarBrandCode + ", thirdCarBrandName=" + thirdCarBrandName + ", linkMobile=" + linkMobile
				+ ", checkAddress=" + checkAddress + "]";
	}
	
}
