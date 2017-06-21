package com.picc.um.schema.vo;

@SuppressWarnings("serial")
public class UmTUserBindVo implements java.io.Serializable {
	
	private String userCode;
	
	private String userName;
	
	private String bindValue;
	
	private String platId;
	
	private String userId;
	
	private String identifyNo;
	
	private String identityNumber;
	
	private String mobileNo;
	
	private String custId;
	
	private String licenseNo;
	
	private String postAddress;
	
	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

 
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBindValue() {
		return bindValue;
	}

	public void setBindValue(String bindValue) {
		this.bindValue = bindValue;
	}

	public String getPlatId() {
		return platId;
	}

	public void setPlatId(String platId) {
		this.platId = platId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIdentifyNo() {
		return identifyNo;
	}

	public void setIdentifyNo(String identifyNo) {
		this.identifyNo = identifyNo;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getPostAddress() {
		return postAddress;
	}

	public void setPostAddress(String postAddress) {
		this.postAddress = postAddress;
	}

	public UmTUserBindVo(String userCode, String userName, String bindValue, String platId, 
			String userId, String identifyNo,String identityNumber,String mobileNo, String postAddress) {
		super();
		this.userCode = userCode;
		this.userName = userName;
		this.bindValue = bindValue;
		this.platId = platId;
		this.userId = userId;
		this.identifyNo = identifyNo;
		this.identityNumber = identityNumber;
		this.mobileNo = mobileNo;
		this.postAddress = postAddress;
	}
	 
	public UmTUserBindVo() {
	}

	@Override
	public String toString() {
		return "UmTUserBindVo [userCode=" + userCode + ", userName=" + userName
				+ ", bindValue=" + bindValue + ", platId=" + platId
				+ ", userId=" + userId + ", identifyNo=" + identifyNo
				+ ", identityNumber=" + identityNumber + ", mobileNo="
				+ mobileNo + ", custId=" + custId + ", licenseNo=" + licenseNo
				+ ", postAddress=" + postAddress + "]";
	}
	
	
}
