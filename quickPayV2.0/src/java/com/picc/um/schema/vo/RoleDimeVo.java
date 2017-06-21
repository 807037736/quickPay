package com.picc.um.schema.vo;

public class RoleDimeVo {
	
	private String dimensionValue;					//维度值
	
	private String roleCode;								//角色代码
	
	private String methodCode;						//访问路径URL
	
	private String operateType;						//操作方式(Add 添加 Sub去除)
	
	public RoleDimeVo(){}

	public String getDimensionValue() {
		return dimensionValue;
	}

	public void setDimensionValue(String dimensionValue) {
		this.dimensionValue = dimensionValue;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getMethodCode() {
		return methodCode;
	}

	public void setMethodCode(String methodCode) {
		this.methodCode = methodCode;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

}
