package com.picc.um.cognos.shema;

/**
 * Cognos环境变量Model
 * @author jiangweiyang
 */

public class CognosEnvironmentModel {
	
	private String dataBaseType;
	
	private String biWebIndexUrl;
	
	private String license;
	
	private String cognosUiGateWay;
	
	private String groupFolder;
	
	private String roleFolder;
	
	private String cognosUrl;
	
	private String crnEnvPath;
	
	private String defaultUserCode;						//默认权限UserCode
	
	private String outerUrl;										//cognos外网访问HOST
	
	public CognosEnvironmentModel(){}

	public String getDataBaseType() {
		return dataBaseType;
	}

	public void setDataBaseType(String dataBaseType) {
		this.dataBaseType = dataBaseType;
	}

	public String getBiWebIndexUrl() {
		return biWebIndexUrl;
	}

	public void setBiWebIndexUrl(String biWebIndexUrl) {
		this.biWebIndexUrl = biWebIndexUrl;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getCognosUiGateWay() {
		return cognosUiGateWay;
	}

	public void setCognosUiGateWay(String cognosUiGateWay) {
		this.cognosUiGateWay = cognosUiGateWay;
	}

	public String getGroupFolder() {
		return groupFolder;
	}

	public void setGroupFolder(String groupFolder) {
		this.groupFolder = groupFolder;
	}

	public String getRoleFolder() {
		return roleFolder;
	}

	public void setRoleFolder(String roleFolder) {
		this.roleFolder = roleFolder;
	}

	public String getCognosUrl() {
		return cognosUrl;
	}

	public void setCognosUrl(String cognosUrl) {
		this.cognosUrl = cognosUrl;
	}

	public String getCrnEnvPath() {
		return crnEnvPath;
	}

	public void setCrnEnvPath(String crnEnvPath) {
		this.crnEnvPath = crnEnvPath;
	}
	
	public void setDefaultUserCode(String defaultUserCode) {
		this.defaultUserCode = defaultUserCode;
	}
	
	public String getDefaultUserCode() {
		return defaultUserCode;
	}

	public String getOuterUrl() {
		return outerUrl;
	}

	public void setOuterUrl(String outerUrl) {
		this.outerUrl = outerUrl;
	}

}
