package com.picc.report.picc.schema.report.model;

import java.io.Serializable;

import com.ymt.annotation.ClassConvert;

@ClassConvert(name="policyInfo")
public class PolicyInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 案件车牌号
	 */
	private String licenseNo;

	/**
	 * 交强保单号
	 */
	private String ciPolicyNo;
	
	/**
	 * 商业保单号
	 */
	private String biPolicyNo;

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getCiPolicyNo() {
		return ciPolicyNo;
	}

	public void setCiPolicyNo(String ciPolicyNo) {
		this.ciPolicyNo = ciPolicyNo;
	}

	public String getBiPolicyNo() {
		return biPolicyNo;
	}

	public void setBiPolicyNo(String biPolicyNo) {
		this.biPolicyNo = biPolicyNo;
	}

	@Override
	public String toString() {
		return "PolicyInfo [licenseNo=" + licenseNo + ", ciPolicyNo=" + ciPolicyNo + ", biPolicyNo=" + biPolicyNo + "]";
	}
	
}
