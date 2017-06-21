package com.picc.qp.schema.model;



public class QpTIcCompanyGarageDetailShow implements java.io.Serializable {

    	private static final long serialVersionUID = 1L;
    	
	private String name;
	private String servicePhone;
	private String servicePhoneName;
	private String landlinePhone;
	private String qq;
	private String companyGarageName;
	private String address;
	private Double lat;
	private Double lng;
	private Double caseLat;
	private Double caseLng;
	public String getName() {
	    return name;
	}
	public void setName(String name) {
	    this.name = name;
	}
	public String getServicePhone() {
	    return servicePhone;
	}
	public void setServicePhone(String servicePhone) {
	    this.servicePhone = servicePhone;
	}
	public String getServicePhoneName() {
	    return servicePhoneName;
	}
	public void setServicePhoneName(String servicePhoneName) {
	    this.servicePhoneName = servicePhoneName;
	}
	public String getLandlinePhone() {
	    return landlinePhone;
	}
	public void setLandlinePhone(String landlinePhone) {
	    this.landlinePhone = landlinePhone;
	}
	public String getQq() {
	    return qq;
	}
	public void setQq(String qq) {
	    this.qq = qq;
	}
	public String getCompanyGarageName() {
	    return companyGarageName;
	}
	public void setCompanyGarageName(String companyGarageName) {
	    this.companyGarageName = companyGarageName;
	}
	public String getAddress() {
	    return address;
	}
	public void setAddress(String address) {
	    this.address = address;
	}
	public Double getLat() {
	    return lat;
	}
	public void setLat(Double lat) {
	    this.lat = lat;
	}
	public Double getLng() {
	    return lng;
	}
	public void setLng(Double lng) {
	    this.lng = lng;
	}
	public Double getCaseLat() {
		return caseLat;
	}
	public void setCaseLat(Double caseLat) {
		this.caseLat = caseLat;
	}
	public Double getCaseLng() {
		return caseLng;
	}
	public void setCaseLng(Double caseLng) {
		this.caseLng = caseLng;
	}
	
}