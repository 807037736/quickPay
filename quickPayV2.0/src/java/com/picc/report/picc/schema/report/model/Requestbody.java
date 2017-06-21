package com.picc.report.picc.schema.report.model;

import java.io.Serializable;
import java.util.List;

import com.ymt.annotation.ClassConvert;

@ClassConvert(name="requestbody")
public class Requestbody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PolicyInfo policyInfo;
	
	private DamageInfo damageInfo;
	
	private List<ThirdCarLossInfo> thirdCarLossInfo;
	
	private List<PersonLossInfo> personLossInfo;
	
	private List<PropLossInfo> propLossInfo;

	public PolicyInfo getPolicyInfo() {
		return policyInfo;
	}

	public void setPolicyInfo(PolicyInfo policyInfo) {
		this.policyInfo = policyInfo;
	}

	public DamageInfo getDamageInfo() {
		return damageInfo;
	}

	public void setDamageInfo(DamageInfo damageInfo) {
		this.damageInfo = damageInfo;
	}

	public List<ThirdCarLossInfo> getThirdCarLossInfo() {
		return thirdCarLossInfo;
	}

	public void setThirdCarLossInfo(List<ThirdCarLossInfo> thirdCarLossInfo) {
		this.thirdCarLossInfo = thirdCarLossInfo;
	}

	public List<PersonLossInfo> getPersonLossInfo() {
		return personLossInfo;
	}

	public void setPersonLossInfo(List<PersonLossInfo> personLossInfo) {
		this.personLossInfo = personLossInfo;
	}

	public List<PropLossInfo> getPropLossInfo() {
		return propLossInfo;
	}

	public void setPropLossInfo(List<PropLossInfo> propLossInfo) {
		this.propLossInfo = propLossInfo;
	}

	
}
