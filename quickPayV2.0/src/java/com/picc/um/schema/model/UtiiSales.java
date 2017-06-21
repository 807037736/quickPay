package com.picc.um.schema.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * POJO UtiiSales
 * @author rongxiaozheng
 */
@Entity
@Table(name = "UM_T_UTIISALES")
public class UtiiSales implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String userCode;

	private String userName;

	private String comCodeType;
	
	private String companyName;

	private String comCode;

	private String newUserCode;

	private String identifyNumber;

	private String articleCode;

	private String phoneNumber;

	private String faxNumber;

	private String mobilePhone;

	private String email;

	private String postAddress;

	private String postCode;

	private String remark;

	private String tcol1;

	private String tcol2;

	private String tcol3;

	private String tcol4;

	private String tcol5;

	private String validStatus;

	private String flag;
	
	private String manOrgCode;
	
	private String manOrgName;
	
	private String idtypeCode;
	
	private String idtypeName;
	
	private String comID;

	public String getIdtypeCode() {
		return idtypeCode;
	}

	public void setIdtypeCode(String idtypeCode) {
		this.idtypeCode = idtypeCode;
	}

	public String getIdtypeName() {
		return idtypeName;
	}

	public void setIdtypeName(String idtypeName) {
		this.idtypeName = idtypeName;
	}

	public UtiiSales() {
	}

	@Id
	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getComCodeType() {
		return this.comCodeType;
	}

	public void setComCodeType(String comCodeType) {
		this.comCodeType = comCodeType;
	}

	public String getComCode() {
		return this.comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	public String getNewUserCode() {
		return this.newUserCode;
	}

	public void setNewUserCode(String newUserCode) {
		this.newUserCode = newUserCode;
	}

	public String getIdentifyNumber() {
		return this.identifyNumber;
	}

	public void setIdentifyNumber(String identifyNumber) {
		this.identifyNumber = identifyNumber;
	}

	public String getArticleCode() {
		return this.articleCode;
	}

	public void setArticleCode(String articleCode) {
		this.articleCode = articleCode;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFaxNumber() {
		return this.faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}


	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPostAddress() {
		return this.postAddress;
	}

	public void setPostAddress(String postAddress) {
		this.postAddress = postAddress;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTcol1() {
		return this.tcol1;
	}

	public void setTcol1(String tcol1) {
		this.tcol1 = tcol1;
	}

	public String getTcol2() {
		return this.tcol2;
	}

	public void setTcol2(String tcol2) {
		this.tcol2 = tcol2;
	}

	public String getTcol3() {
		return this.tcol3;
	}

	public void setTcol3(String tcol3) {
		this.tcol3 = tcol3;
	}

	public String getTcol4() {
		return this.tcol4;
	}

	public void setTcol4(String tcol4) {
		this.tcol4 = tcol4;
	}

	public String getTcol5() {
		return this.tcol5;
	}

	public void setTcol5(String tcol5) {
		this.tcol5 = tcol5;
	}

	public String getValidStatus() {
		return this.validStatus;
	}

	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getManOrgCode() {
		return manOrgCode;
	}

	public void setManOrgCode(String manOrgCode) {
		this.manOrgCode = manOrgCode;
	}

	public String getManOrgName() {
		return manOrgName;
	}

	public void setManOrgName(String manOrgName) {
		this.manOrgName = manOrgName;
	}

	public String getComID() {
		return comID;
	}

	public void setComID(String comID) {
		this.comID = comID;
	}

	

}
