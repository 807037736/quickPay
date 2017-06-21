/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.schema.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "Qp_T_COM_HandlePolice")
public class QpTCOMHandlePolice implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**属性*/
	/** 主键ID**/
	private Integer handlePoliceId;
  	/** 交警姓名**/
	private String handlePoliceName;
	/** 快赔点ID **/
	private String centerId;
	/** 所在机构 **/
	private String organization;
  	/** 创建人代码 **/
	private String createCode;
  	/** 创建时间 **/
	private Date createTime;
  	/** 修改人代码 **/
	private String updaterCode;
	/** 修改时间 **/
	private Date updaterTime;
	/** 警号**/
	private String handlePoliceNO;
	/** 交警手机**/
	private String handlePolicePhone;
	/** 快赔点名称**/
	private String centerName;
	/** 有效状态**/
	private String validStatus;
	/** 标识**/
	private String flag;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "handlePoliceId")
	public Integer getHandlePoliceId() {
		return handlePoliceId;
	}
	
	public void setHandlePoliceId(Integer handlePoliceId) {
		this.handlePoliceId = handlePoliceId;
	}
	
	
	@Column(name = "handlePoliceName")
	public String getHandlePoliceName() {
		return handlePoliceName;
	}
	public void setHandlePoliceName(String handlePoliceName) {
		this.handlePoliceName = handlePoliceName;
	}
	

	@Column(name = "handlePoliceNO")
	public String getHandlePoliceNO() {
		return handlePoliceNO;
	}
	public void setHandlePoliceNO(String handlePoliceNO) {
		this.handlePoliceNO = handlePoliceNO;
	}

	@Column(name = "handlePolicePhone")
	public String getHandlePolicePhone() {
		return handlePolicePhone;
	}
	public void setHandlePolicePhone(String handlePolicePhone) {
		this.handlePolicePhone = handlePolicePhone;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "updaterCode")
	public String getUpdaterCode() {
		return updaterCode;
	}

	public void setUpdaterCode(String updaterCode) {
		this.updaterCode = updaterCode;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updaterTime")
	public Date getUpdaterTime() {
		return updaterTime;
	}

	public void setUpdaterTime(Date updaterTime) {
		this.updaterTime = updaterTime;
	}
	@Column(name = "centerId")
	public String getCenterId() {
		return centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
	@Column(name = "organization")
	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}
	@Column(name = "validStatus")
	public String getValidStatus() {
		return validStatus;
	}

	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
	@Column(name = "flag")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Column(name = "centerName")
	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	@Column(name = "createCode")
	public String getCreateCode() {
		return createCode;
	}

	public void setCreateCode(String createCode) {
		this.createCode = createCode;
	}
	
}