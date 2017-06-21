package com.picc.qp.schema.vo;

import java.math.BigDecimal;
import java.math.BigInteger;

public class QpTTPResponsibleVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 保险公司名字 **/
	private String coname;
	/** 总量 **/
	private BigInteger total;
	/** 主责 **/
	private BigDecimal mainResponsibility;
	/** 次责 **/
	private BigDecimal secondaryResponsibility;
	/** 同责 **/
	private BigDecimal sameResponsibility;
	/** 全责 **/
	private BigDecimal allResponsibility;
	/** 无责 **/
	private BigDecimal noResponsibility;
	/** 20:00以后的案件量**/
	private BigDecimal afterEthig;
	/** 主责占比 **/
	private BigDecimal mainResponsibilityPro;
	/** 次责占比 **/
	private BigDecimal secondaryResponsibilityPro;
	/** 同责占比**/
	private BigDecimal sameResponsibilityPro;
	/** 全责占比**/
	private BigDecimal allResponsibilityPro;
	/** 无责占比 **/
	private BigDecimal noResponsibilityPro;
	/** 20:00以后的案件量占比**/
	private BigDecimal afterEthigPro;
	
	
	/** 统计开始时间 **/
	private String acciStartTime;
	/** 统计结束时间 **/
	private String acciEndTime;
	
	/** 合计总量**/
	private BigInteger totalAmount;
	/** 合计主责**/
	private BigDecimal totalMainResponsibility;
	/** 合计次责 **/
	private BigDecimal totalSecondaryResponsibility;
	/** 合计同责 **/
	private BigDecimal totalSameResponsibility;
	/** 合计全责 **/
	private BigDecimal totalAllResponsibility;
	/** 合计无责 **/
	private BigDecimal totalNoResponsibility;
	/**合计 20:00以后的案件量**/
	private BigDecimal totalfterEthig;
	/** 合计主责占比 **/
	private BigDecimal totalMainResponsibilityPro;
	/** 合计次责占比 **/
	private BigDecimal totalSecondaryResponsibilityPro;
	/** 合计同责占比**/
	private BigDecimal totalSameResponsibilityPro;
	/** 合计全责占比**/
	private BigDecimal totalSllResponsibilityPro;
	/** 合计无责占比 **/
	private BigDecimal totalNoResponsibilityPro;
	/** 合计20:00以后的案件量占比**/
	private BigDecimal totalSfterEthigPro;
	public String getConame() {
		return coname;
	}
	public void setConame(String coname) {
		this.coname = coname;
	}
	public BigInteger getTotal() {
		return total;
	}
	public void setTotal(BigInteger total) {
		this.total = total;
	}
	public BigDecimal getMainResponsibility() {
		return mainResponsibility;
	}
	public void setMainResponsibility(BigDecimal mainResponsibility) {
		this.mainResponsibility = mainResponsibility;
	}
	public BigDecimal getSecondaryResponsibility() {
		return secondaryResponsibility;
	}
	public void setSecondaryResponsibility(BigDecimal secondaryResponsibility) {
		this.secondaryResponsibility = secondaryResponsibility;
	}
	public BigDecimal getSameResponsibility() {
		return sameResponsibility;
	}
	public void setSameResponsibility(BigDecimal sameResponsibility) {
		this.sameResponsibility = sameResponsibility;
	}
	public BigDecimal getAllResponsibility() {
		return allResponsibility;
	}
	public void setAllResponsibility(BigDecimal allResponsibility) {
		this.allResponsibility = allResponsibility;
	}
	public BigDecimal getNoResponsibility() {
		return noResponsibility;
	}
	public void setNoResponsibility(BigDecimal noResponsibility) {
		this.noResponsibility = noResponsibility;
	}
	public BigDecimal getAfterEthig() {
		return afterEthig;
	}
	public void setAfterEthig(BigDecimal afterEthig) {
		this.afterEthig = afterEthig;
	}
	public BigDecimal getMainResponsibilityPro() {
		return mainResponsibilityPro;
	}
	public void setMainResponsibilityPro(BigDecimal mainResponsibilityPro) {
		this.mainResponsibilityPro = mainResponsibilityPro;
	}
	public BigDecimal getSecondaryResponsibilityPro() {
		return secondaryResponsibilityPro;
	}
	public void setSecondaryResponsibilityPro(BigDecimal secondaryResponsibilityPro) {
		this.secondaryResponsibilityPro = secondaryResponsibilityPro;
	}
	public BigDecimal getSameResponsibilityPro() {
		return sameResponsibilityPro;
	}
	public void setSameResponsibilityPro(BigDecimal sameResponsibilityPro) {
		this.sameResponsibilityPro = sameResponsibilityPro;
	}
	public BigDecimal getAllResponsibilityPro() {
		return allResponsibilityPro;
	}
	public void setAllResponsibilityPro(BigDecimal allResponsibilityPro) {
		this.allResponsibilityPro = allResponsibilityPro;
	}
	public BigDecimal getNoResponsibilityPro() {
		return noResponsibilityPro;
	}
	public void setNoResponsibilityPro(BigDecimal noResponsibilityPro) {
		this.noResponsibilityPro = noResponsibilityPro;
	}
	public BigDecimal getAfterEthigPro() {
		return afterEthigPro;
	}
	public void setAfterEthigPro(BigDecimal afterEthigPro) {
		this.afterEthigPro = afterEthigPro;
	}
	public BigDecimal getTotalSecondaryResponsibility() {
		return totalSecondaryResponsibility;
	}
	public void setTotalSecondaryResponsibility(
			BigDecimal totalSecondaryResponsibility) {
		this.totalSecondaryResponsibility = totalSecondaryResponsibility;
	}
	public BigDecimal getTotalSameResponsibility() {
		return totalSameResponsibility;
	}
	public void setTotalSameResponsibility(BigDecimal totalSameResponsibility) {
		this.totalSameResponsibility = totalSameResponsibility;
	}
	public BigDecimal getTotalAllResponsibility() {
		return totalAllResponsibility;
	}
	public void setTotalAllResponsibility(BigDecimal totalAllResponsibility) {
		this.totalAllResponsibility = totalAllResponsibility;
	}
	public BigDecimal getTotalNoResponsibility() {
		return totalNoResponsibility;
	}
	public void setTotalNoResponsibility(BigDecimal totalNoResponsibility) {
		this.totalNoResponsibility = totalNoResponsibility;
	}
	public BigDecimal getTotalfterEthig() {
		return totalfterEthig;
	}
	public void setTotalfterEthig(BigDecimal totalfterEthig) {
		this.totalfterEthig = totalfterEthig;
	}
	public BigDecimal getTotalMainResponsibilityPro() {
		return totalMainResponsibilityPro;
	}
	public void setTotalMainResponsibilityPro(BigDecimal totalMainResponsibilityPro) {
		this.totalMainResponsibilityPro = totalMainResponsibilityPro;
	}
	public BigDecimal getTotalSecondaryResponsibilityPro() {
		return totalSecondaryResponsibilityPro;
	}
	public void setTotalSecondaryResponsibilityPro(
			BigDecimal totalSecondaryResponsibilityPro) {
		this.totalSecondaryResponsibilityPro = totalSecondaryResponsibilityPro;
	}
	public BigDecimal getTotalSameResponsibilityPro() {
		return totalSameResponsibilityPro;
	}
	public void setTotalSameResponsibilityPro(BigDecimal totalSameResponsibilityPro) {
		this.totalSameResponsibilityPro = totalSameResponsibilityPro;
	}
	public BigDecimal getTotalSllResponsibilityPro() {
		return totalSllResponsibilityPro;
	}
	public void setTotalSllResponsibilityPro(BigDecimal totalSllResponsibilityPro) {
		this.totalSllResponsibilityPro = totalSllResponsibilityPro;
	}
	public BigDecimal getTotalNoResponsibilityPro() {
		return totalNoResponsibilityPro;
	}
	public void setTotalNoResponsibilityPro(BigDecimal totalNoResponsibilityPro) {
		this.totalNoResponsibilityPro = totalNoResponsibilityPro;
	}
	public BigDecimal getTotalSfterEthigPro() {
		return totalSfterEthigPro;
	}
	public void setTotalSfterEthigPro(BigDecimal totalSfterEthigPro) {
		this.totalSfterEthigPro = totalSfterEthigPro;
	}
	public BigInteger getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigInteger totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BigDecimal getTotalMainResponsibility() {
		return totalMainResponsibility;
	}
	public void setTotalMainResponsibility(BigDecimal totalMainResponsibility) {
		this.totalMainResponsibility = totalMainResponsibility;
	}
	public String getAcciStartTime() {
		return acciStartTime;
	}
	public void setAcciStartTime(String acciStartTime) {
		this.acciStartTime = acciStartTime;
	}
	public String getAcciEndTime() {
		return acciEndTime;
	}
	public void setAcciEndTime(String acciEndTime) {
		this.acciEndTime = acciEndTime;
	}
	
	
	

	

}
