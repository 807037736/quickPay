package com.picc.qp.schema.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * QpTIcCompanyGarageDetail entity. @author MyEclipse Persistence Tools
 */

@Entity
@Table(name = "Qp_T_Ic_Company_Garage_Detail")
public class QpTIcCompanyGarageDetail implements java.io.Serializable {

    	private static final long serialVersionUID = 1L;
    	
	/**
         * 
         */
	private QpTIcCompanyGarageDetailId id;
	private String name;
	private String isStation;
	private String isRemote;
	private String servicePhone;
	private String servicePhoneName;
	private String landlinePhone;
	private String qq;
	private int companyGarageId;
	private String companyGarageName;
	private String address;
	private Double lat;
	private Double lng;
	private String status;
	private Date createTime;
	private String createCode;
	private Double distance;
	// Constructors

	/** default constructor */
	public QpTIcCompanyGarageDetail() {
	}

	/** full constructor */
	public QpTIcCompanyGarageDetail(String name, String isStation,
			String isRemote, String servicePhone, String servicePhoneName,
			String landlinePhone, String qq, int companyGarageId,
			String companyGarageName, String address, Double lat, Double lng,
			String status, Timestamp createTime, String createCode) {
		this.name = name;
		this.isStation = isStation;
		this.isRemote = isRemote;
		this.servicePhone = servicePhone;
		this.servicePhoneName = servicePhoneName;
		this.landlinePhone = landlinePhone;
		this.qq = qq;
		this.companyGarageId = companyGarageId;
		this.companyGarageName = companyGarageName;
		this.address = address;
		this.lat = lat;
		this.lng = lng;
		this.status = status;
		this.createTime = createTime;
		this.createCode = createCode;
	}

	// Property accessors

	@EmbeddedId
	@AttributeOverrides( {
	    @AttributeOverride(name = "id", column = @Column(name = "id"))
	})
	public QpTIcCompanyGarageDetailId getId() {
	    return this.id;
	}

	public void setId(QpTIcCompanyGarageDetailId id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "isStation")
	public String getIsStation() {
		return this.isStation;
	}

	public void setIsStation(String isStation) {
		this.isStation = isStation;
	}

	@Column(name = "isRemote")
	public String getIsRemote() {
		return this.isRemote;
	}

	public void setIsRemote(String isRemote) {
		this.isRemote = isRemote;
	}

	@Column(name = "servicePhone")
	public String getServicePhone() {
		return this.servicePhone;
	}

	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}

	@Column(name = "servicePhoneName")
	public String getServicePhoneName() {
		return this.servicePhoneName;
	}

	public void setServicePhoneName(String servicePhoneName) {
		this.servicePhoneName = servicePhoneName;
	}

	@Column(name = "landlinePhone")
	public String getLandlinePhone() {
		return this.landlinePhone;
	}

	public void setLandlinePhone(String landlinePhone) {
		this.landlinePhone = landlinePhone;
	}

	@Column(name = "qq")
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "companyGarageId")
	public int getCompanyGarageId() {
		return this.companyGarageId;
	}

	public void setCompanyGarageId(int companyGarageId) {
		this.companyGarageId = companyGarageId;
	}

	@Column(name = "companyGarageName")
	public String getCompanyGarageName() {
		return this.companyGarageName;
	}

	public void setCompanyGarageName(String companyGarageName) {
		this.companyGarageName = companyGarageName;
	}

	@Column(name = "address")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "lat" , updatable = false)
	public Double getLat() {
		return this.lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	@Column(name = "lng" , updatable = false)
	public Double getLng() {
		return this.lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
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

	@Transient
	public Double getDistance() {
	    return distance;
	}

	public void setDistance(Double distance) {
	    this.distance = distance;
	}

}