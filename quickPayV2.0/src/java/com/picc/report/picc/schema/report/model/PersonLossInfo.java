package com.picc.report.picc.schema.report.model;

import java.io.Serializable;

import com.ymt.annotation.ClassConvert;

@ClassConvert
public class PersonLossInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * 损失类型
	 * 
	 * 010:三者人员	050:本车上人员
	 */
	private String lossItemType;
	
	/**
	 * 伤亡人员姓名
	 */
	private String personName;

	public String getLossItemType() {
		return lossItemType;
	}

	public void setLossItemType(String lossItemType) {
		this.lossItemType = lossItemType;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	@Override
	public String toString() {
		return "PersonLossInfo [lossItemType=" + lossItemType + ", personName=" + personName + "]";
	}
	
	
}
