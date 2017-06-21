/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.um.schema.model;

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
@Table(name = "um_t_userhistory")
public class UmTUserHistory implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_USERCODE = "usercode";
	public static final String ALIAS_USERNAME = "username";
	public static final String ALIAS_COMCODE = "comcode";
	public static final String ALIAS_TELEPHONE = "telephone";
	public static final String ALIAS_FAXNUMBER = "faxnumber";
	public static final String ALIAS_MOBILE = "mobile";
	public static final String ALIAS_EMAIL = "email";
	public static final String ALIAS_POSTADDRESS = "postaddress";
	public static final String ALIAS_UNIT = "unit";
	public static final String ALIAS_UNITADDRESS = "unitaddress";
	public static final String ALIAS_IMAGE = "image";
	public static final String ALIAS_INTERESTS = "interests";
	public static final String ALIAS_CREATORCODE = "creatorcode";
	public static final String ALIAS_INSERTTIMEFORHIS = "inserttimeforhis";
	public static final String ALIAS_UPDATERCODE = "updatercode";
	public static final String ALIAS_OPERATETIMEFORHIS = "operatetimeforhis";
	public static final String ALIAS_REMARK = "remark";
	public static final String ALIAS_FLAG = "flag";
	public static final String ALIAS_USERTYPE = "usertype";
	public static final String ALIAS_USERSORT = "usersort";
	public static final String ALIAS_IDENTITYNUMBER = "identitynumber";
	public static final String ALIAS_VALIDSTATUS = "validstatus";
	public static final String ALIAS_SEX = "sex";
	public static final String ALIAS_BIRTHDAY = "birthday";
	public static final String ALIAS_AGE = "age";
	public static final String ALIAS_COMID = "comid";
	public static final String ALIAS_BINDSTATUS = "bindstatus";
	public static final String ALIAS_SOURCEFLAG = "sourceflag";
	public static final String ALIAS_CUSTID = "custid";
	
	
	/**	构造函数	**/
	public UmTUserHistory() {
	}
	
	/** 主键对象 **/
	private UmTUserHistoryId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "usercode", column = @Column(name = "usercode"))
    })
	public UmTUserHistoryId getId() {
		return this.id;
	}

	public void setId(UmTUserHistoryId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** username **/
	private String username;
  	/** comcode **/
	private String comcode;
  	/** telephone **/
	private String telephone;
  	/** faxnumber **/
	private String faxnumber;
  	/** mobile **/
	private String mobile;
  	/** email **/
	private String email;
  	/** postaddress **/
	private String postaddress;
  	/** unit **/
	private String unit;
  	/** unitaddress **/
	private String unitaddress;
  	/** image **/
	private String image;
  	/** interests **/
	private String interests;
  	/** creatorcode **/
	private String creatorcode;
  	/** inserttimeforhis **/
	private Date inserttimeforhis;
  	/** updatercode **/
	private String updatercode;
  	/** operatetimeforhis **/
	private Date operatetimeforhis;
  	/** remark **/
	private String remark;
  	/** flag **/
	private String flag;
  	/** usertype **/
	private String usertype;
  	/** usersort **/
	private String usersort;
  	/** identitynumber **/
	private String identitynumber;
  	/** validstatus **/
	private String validstatus;
  	/** sex **/
	private String sex;
  	/** birthday **/
	private Date birthday;
  	/** age **/
	private Integer age;
  	/** comid **/
	private String comid;
  	/** bindstatus **/
	private String bindstatus;
  	/** sourceflag **/
	private String sourceflag;
  	/** custid **/
	private String custid;
	
	/**getter setter方法*/
	@Column(name = "USERNAME")
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name = "COMCODE")
	public String getComcode() {
		return this.comcode;
	}
	
	public void setComcode(String comcode) {
		this.comcode = comcode;
	}
	@Column(name = "TELEPHONE")
	public String getTelephone() {
		return this.telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@Column(name = "FAXNUMBER")
	public String getFaxnumber() {
		return this.faxnumber;
	}
	
	public void setFaxnumber(String faxnumber) {
		this.faxnumber = faxnumber;
	}
	@Column(name = "MOBILE")
	public String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name = "EMAIL")
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "POSTADDRESS")
	public String getPostaddress() {
		return this.postaddress;
	}
	
	public void setPostaddress(String postaddress) {
		this.postaddress = postaddress;
	}
	@Column(name = "UNIT")
	public String getUnit() {
		return this.unit;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Column(name = "UNITADDRESS")
	public String getUnitaddress() {
		return this.unitaddress;
	}
	
	public void setUnitaddress(String unitaddress) {
		this.unitaddress = unitaddress;
	}
	@Column(name = "IMAGE")
	public String getImage() {
		return this.image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	@Column(name = "INTERESTS")
	public String getInterests() {
		return this.interests;
	}
	
	public void setInterests(String interests) {
		this.interests = interests;
	}
	@Column(name = "CREATORCODE" , updatable = false)
	public String getCreatorcode() {
		return this.creatorcode;
	}
	
	public void setCreatorcode(String creatorcode) {
		this.creatorcode = creatorcode;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INSERTTIMEFORHIS" , updatable = false)
	public Date getInserttimeforhis() {
		return this.inserttimeforhis;
	}
	
	public void setInserttimeforhis(Date inserttimeforhis) {
		this.inserttimeforhis = inserttimeforhis;
	}
	@Column(name = "UPDATERCODE" , insertable = false)
	public String getUpdatercode() {
		return this.updatercode;
	}
	
	public void setUpdatercode(String updatercode) {
		this.updatercode = updatercode;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OPERATETIMEFORHIS" , insertable = false)
	public Date getOperatetimeforhis() {
		return this.operatetimeforhis;
	}
	
	public void setOperatetimeforhis(Date operatetimeforhis) {
		this.operatetimeforhis = operatetimeforhis;
	}
	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "FLAG")
	public String getFlag() {
		return this.flag;
	}
	
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Column(name = "USERTYPE")
	public String getUsertype() {
		return this.usertype;
	}
	
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	@Column(name = "USERSORT")
	public String getUsersort() {
		return this.usersort;
	}
	
	public void setUsersort(String usersort) {
		this.usersort = usersort;
	}
	@Column(name = "IDENTITYNUMBER")
	public String getIdentitynumber() {
		return this.identitynumber;
	}
	
	public void setIdentitynumber(String identitynumber) {
		this.identitynumber = identitynumber;
	}
	@Column(name = "VALIDSTATUS")
	public String getValidstatus() {
		return this.validstatus;
	}
	
	public void setValidstatus(String validstatus) {
		this.validstatus = validstatus;
	}
	@Column(name = "SEX")
	public String getSex() {
		return this.sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BIRTHDAY")
	public Date getBirthday() {
		return this.birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Column(name = "AGE")
	public Integer getAge() {
		return this.age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}
	@Column(name = "COMID")
	public String getComid() {
		return this.comid;
	}
	
	public void setComid(String comid) {
		this.comid = comid;
	}
	@Column(name = "BINDSTATUS")
	public String getBindstatus() {
		return this.bindstatus;
	}
	
	public void setBindstatus(String bindstatus) {
		this.bindstatus = bindstatus;
	}
	@Column(name = "SOURCEFLAG")
	public String getSourceflag() {
		return this.sourceflag;
	}
	
	public void setSourceflag(String sourceflag) {
		this.sourceflag = sourceflag;
	}
	@Column(name = "CUSTID")
	public String getCustid() {
		return this.custid;
	}
	
	public void setCustid(String custid) {
		this.custid = custid;
	}
		
}