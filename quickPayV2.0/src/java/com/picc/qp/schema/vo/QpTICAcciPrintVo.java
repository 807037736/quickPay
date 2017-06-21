/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.schema.vo;


public class QpTICAcciPrintVo implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;

	/** 甲行驶方式 **/
	private String directionRoadA;
	private String directionFromA;
	private String directionToA;
	
	/** 乙行驶方式 **/
	private String directionRoadB;
	private String directionFromB;
	private String directionToB;
	
	/** 丙行驶方式 **/
	private String directionRoadC;
	private String directionFromC;
	private String directionToC;
	
	/** 事故详情 **/
	private String caseNotes;
	
	/** 认字编号 **/
	private String caseNoA; // 长公交
	private String caseNoB; // 认字

	public String getDirectionFromA() {
		return directionFromA;
	}

	public void setDirectionFromA(String directionFromA) {
		this.directionFromA = directionFromA;
	}

	public String getDirectionToA() {
		return directionToA;
	}

	public void setDirectionToA(String directionToA) {
		this.directionToA = directionToA;
	}

	public String getDirectionFromB() {
		return directionFromB;
	}

	public void setDirectionFromB(String directionFromB) {
		this.directionFromB = directionFromB;
	}

	public String getDirectionToB() {
		return directionToB;
	}

	public void setDirectionToB(String directionToB) {
		this.directionToB = directionToB;
	}

	public String getDirectionFromC() {
		return directionFromC;
	}

	public void setDirectionFromC(String directionFromC) {
		this.directionFromC = directionFromC;
	}

	public String getDirectionToC() {
		return directionToC;
	}

	public void setDirectionToC(String directionToC) {
		this.directionToC = directionToC;
	}

	public String getCaseNotes() {
		return caseNotes;
	}

	public void setCaseNotes(String caseNotes) {
		this.caseNotes = caseNotes;
	}

	public String getDirectionRoadA() {
		return directionRoadA;
	}

	public void setDirectionRoadA(String directionRoadA) {
		this.directionRoadA = directionRoadA;
	}

	public String getDirectionRoadB() {
		return directionRoadB;
	}

	public void setDirectionRoadB(String directionRoadB) {
		this.directionRoadB = directionRoadB;
	}

	public String getDirectionRoadC() {
		return directionRoadC;
	}

	public void setDirectionRoadC(String directionRoadC) {
		this.directionRoadC = directionRoadC;
	}

	public String getCaseNoA() {
		return caseNoA;
	}

	public void setCaseNoA(String caseNoA) {
		this.caseNoA = caseNoA;
	}

	public String getCaseNoB() {
		return caseNoB;
	}

	public void setCaseNoB(String caseNoB) {
		this.caseNoB = caseNoB;
	}
}