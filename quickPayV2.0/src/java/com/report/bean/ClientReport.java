package com.report.bean;

import java.io.InputStream;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ClientReport {

	private String accidentTime ;
	private String accidentPlace;
	private String weather ;
	private String quickPayNo;
	private String accResult ;
	private JRBeanCollectionDataSource accClientList ;
	private String accFact ;
	private String accDuty ;
	private String date ;
	private InputStream images;
	private String clientNames;
	private String policyName;
	public String getAccidentTime() {
		return accidentTime;
	}
	public void setAccidentTime(String accidentTime) {
		this.accidentTime = accidentTime;
	}
	public String getAccidentPlace() {
		return accidentPlace;
	}
	public void setAccidentPlace(String accidentPlace) {
		this.accidentPlace = accidentPlace;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getQuickPayNo() {
		return quickPayNo;
	}
	public void setQuickPayNo(String quickPayNo) {
		this.quickPayNo = quickPayNo;
	}
	public String getAccResult() {
		return accResult;
	}
	public void setAccResult(String accResult) {
		this.accResult = accResult;
	}
	
	public JRBeanCollectionDataSource getAccClientList() {
		return accClientList;
	}
	public void setAccClientList(JRBeanCollectionDataSource accClientList) {
		this.accClientList = accClientList;
	}
	public String getAccFact() {
		return accFact;
	}
	public void setAccFact(String accFact) {
		this.accFact = accFact;
	}
	public String getAccDuty() {
		return accDuty;
	}
	public void setAccDuty(String accDuty) {
		this.accDuty = accDuty;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getClientNames() {
		return clientNames;
	}
	public void setClientNames(String clientNames) {
		this.clientNames = clientNames;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public InputStream getImages() {
		return images;
	}
	public void setImages(InputStream images) {
		this.images = images;
	}
	
	
}
