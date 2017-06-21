package com.picc.report.picc.schema.report.model;

import java.io.Serializable;

import com.ymt.annotation.ClassConvert;

@ClassConvert
public class PropLossInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * 损失类型
	 * 010:三者财产损失 050:本车上财产
	 */
	private String lossItemType;
	
	/**
	 * 财产名称
	 */
	private String lossItemName;
	
	public String getLossItemType() {
		return lossItemType;
	}
	public void setLossItemType(String lossItemType) {
		this.lossItemType = lossItemType;
	}
	public String getLossItemName() {
		return lossItemName;
	}
	public void setLossItemName(String lossItemName) {
		this.lossItemName = lossItemName;
	}
	@Override
	public String toString() {
		return "PropLossInfo [lossItemType=" + lossItemType + ", lossItemName=" + lossItemName + "]";
	}
	
	
}
