package com.picc.um.schema.vo;

public class FieldOperateValue {
	
	private String targetField;
	
	private String operationSymbol;
	
	private String powerValue;
	
	public FieldOperateValue(){}
	
	public void setTargetField(String targetField) {
		this.targetField = targetField;
	}
	
	public void setPowerValue(String powerValue) {
		this.powerValue = powerValue;
	}
	
	public void setOperationSymbol(String operationSymbol) {
		this.operationSymbol = operationSymbol;
	}
	
	public String getTargetField() {
		return targetField;
	}
	
	public String getPowerValue() {
		return powerValue;
	}
	
	public String getOperationSymbol() {
		return operationSymbol;
	}
	
}
