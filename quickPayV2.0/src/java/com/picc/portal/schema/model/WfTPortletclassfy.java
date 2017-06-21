/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.portal.schema.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "WF_T_PORTLETCLASSFY")
public class WfTPortletclassfy implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_PORTLETID = "portletid";
	public static final String ALIAS_PORTLETNAME = "portletname";
	public static final String ALIAS_ACTIONURL = "actionurl";
	public static final String ALIAS_ISROLL = "isroll";
	public static final String ALIAS_PORLETHEIGHT = "porletheight";
	public static final String ALIAS_PORLETWIDTH = "porletwidth";
	public static final String ALIAS_COMID = "comid";
	public static final String ALIAS_INSERTTIMEFORHIS = "inserttimeforhis";
	public static final String ALIAS_OPERATETIMEFORHIS = "operatetimeforhis";
	public static final String ALIAS_VALIDSTATUS = "validstatus";
	public static final String ALIAS_COMCODE = "comcode";
	
	
	/**	构造函数	**/
	public WfTPortletclassfy() {
	}
	
	/** 主键对象 **/
	private WfTPortletclassfyId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "portletid", column = @Column(name = "portletid"))
    })
	public WfTPortletclassfyId getId() {
		return this.id;
	}

	public void setId(WfTPortletclassfyId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** portletname **/
	private String portletname;
  	/** actionurl **/
	private String actionurl;
  	/** isroll **/
	private BigDecimal isroll;
  	/** porletheight **/
	private String porletheight;
  	/** porletwidth **/
	private String porletwidth;
  	/** comid **/
	private String comid;
  	/** inserttimeforhis **/
	private Date inserttimeforhis;
  	/** operatetimeforhis **/
	private Date operatetimeforhis;
  	/** validflag **/
	private BigDecimal validstatus;
  	/** comcode **/
	private String comcode;
	/**getter setter方法*/
	
	@Column(name = "portletname")
	public String getPortletname() {
		return this.portletname;
	}
	
	public void setPortletname(String portletname) {
		this.portletname = portletname;
	}
	@Column(name = "actionurl")
	public String getActionurl() {
		return this.actionurl;
	}
	
	public void setActionurl(String actionurl) {
		this.actionurl = actionurl;
	}
	@Column(name = "isroll")
	public BigDecimal getIsroll() {
		return this.isroll;
	}
	
	public void setIsroll(BigDecimal isroll) {
		this.isroll = isroll;
	}
	@Column(name = "porletheight")
	public String getPorletheight() {
		return this.porletheight;
	}
	
	public void setPorletheight(String porletheight) {
		this.porletheight = porletheight;
	}
	@Column(name = "porletwidth")
	public String getPorletwidth() {
		return this.porletwidth;
	}
	
	public void setPorletwidth(String porletwidth) {
		this.porletwidth = porletwidth;
	}
	@Column(name = "comid")
	public String getComid() {
		return this.comid;
	}
	
	public void setComid(String comid) {
		this.comid = comid;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "inserttimeforhis" , updatable = false)
	public Date getInserttimeforhis() {
		return this.inserttimeforhis;
	}
	
	public void setInserttimeforhis(Date inserttimeforhis) {
		this.inserttimeforhis = inserttimeforhis;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "operatetimeforhis" , insertable = false)
	public Date getOperatetimeforhis() {
		return this.operatetimeforhis;
	}
	
	public void setOperatetimeforhis(Date operatetimeforhis) {
		this.operatetimeforhis = operatetimeforhis;
	}
	
	@Column(name = "validstatus")
	public BigDecimal getValidstatus() {
		return this.validstatus;
	}
	
	public void setValidstatus(BigDecimal validstatus) {
		this.validstatus = validstatus;
	}
	@Column(name = "comcode")
	public String getComcode() {
		return comcode;
	}

	public void setComcode(String comcode) {
		this.comcode = comcode;
	}
		
}