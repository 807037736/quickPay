package com.picc.qp.schema.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * QpTIcCompanyGarage entity. @author MyEclipse Persistence Tools
 */

@Entity
@Table(name = "Qp_T_Ic_Company_Garage")
public class QpTIcCompanyGarage implements java.io.Serializable {

    	private static final long serialVersionUID = 1L;

	private QpTIcCompanyGarageId id;
	private String companyGarageName;
	private String coId;
	private String coName;
	private String status;
	private Date createTime;
	private String createCode;

	
	@EmbeddedId
	@AttributeOverrides( {
	    @AttributeOverride(name = "id", column = @Column(name = "id"))
	})
	public QpTIcCompanyGarageId getId() {
	    return id;
	}

	public void setId(QpTIcCompanyGarageId id) {
	    this.id = id;
	}
	

	@Column(name = "companyGarageName")
	public String getCompanyGarageName() {
		return this.companyGarageName;
	}

	public void setCompanyGarageName(String companyGarageName) {
		this.companyGarageName = companyGarageName;
	}

	@Column(name = "coId")
	public String getCoId() {
		return this.coId;
	}

	public void setCoId(String coId) {
		this.coId = coId;
	}

	@Column(name = "coName")
	public String getCoName() {
		return this.coName;
	}

	public void setCoName(String coName) {
		this.coName = coName;
	}

	@Column(name = "status")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "createTime")
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "createCode")
	public String getCreateCode() {
		return this.createCode;
	}

	public void setCreateCode(String createCode) {
		this.createCode = createCode;
	}

}