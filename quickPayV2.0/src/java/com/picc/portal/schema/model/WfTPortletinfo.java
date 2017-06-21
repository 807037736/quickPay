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
@Table(name = "WF_T_PORTLETINFO")
public class WfTPortletinfo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USERCODE = "usercode";
	public static final String ALIAS_PORLETROW = "porletrow";
	public static final String ALIAS_PORLETCOL = "porletcol";
	public static final String ALIAS_PORTLETID = "portletid";
	public static final String ALIAS_ISCLOSED = "isclosed";
	public static final String ALIAS_COMID = "comid";
	public static final String ALIAS_INSERTTIMEFORHIS = "inserttimeforhis";
	public static final String ALIAS_OPERATETIMEFORHIS = "operatetimeforhis";
	public static final String ALIAS_VALIDSTATUS = "validstatus";
	public static final String ALIAS_COMCODE = "comcode";
	
	/**	构造函数	**/
	public WfTPortletinfo() {
	}
	
	/** 主键对象 **/
	private WfTPortletinfoId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "id", column = @Column(name = "id"))
    })
	public WfTPortletinfoId getId() {
		return this.id;
	}

	public void setId(WfTPortletinfoId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** usercode **/
	private String usercode;
  	/** porletrow **/
	private String porletrow;
  	/** porletcol **/
	private String porletcol;
  	/** portletid **/
	private String portletid;
  	/** isclosed **/
	private BigDecimal isclosed;
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
	@Column(name = "usercode")
	public String getUsercode() {
		return this.usercode;
	}
	
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	@Column(name = "porletrow")
	public String getPorletrow() {
		return this.porletrow;
	}
	
	public void setPorletrow(String porletrow) {
		this.porletrow = porletrow;
	}
	@Column(name = "porletcol")
	public String getPorletcol() {
		return this.porletcol;
	}
	
	public void setPorletcol(String porletcol) {
		this.porletcol = porletcol;
	}
	@Column(name = "portletid")
	public String getPortletid() {
		return this.portletid;
	}
	
	public void setPortletid(String portletid) {
		this.portletid = portletid;
	}
	@Column(name = "isclosed")
	public BigDecimal getIsclosed() {
		return this.isclosed;
	}
	
	public void setIsclosed(BigDecimal isclosed) {
		this.isclosed = isclosed;
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