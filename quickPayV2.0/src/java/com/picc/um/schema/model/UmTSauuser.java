/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
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
@Table(name = "um_t_sauuser")
public class UmTSauuser implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_SALE_USER_ID = "销售人员ID";
	public static final String ALIAS_TEAM_I_D = "团队ID";
	public static final String ALIAS_USER_NAME = "用户姓名";
	public static final String ALIAS_USER_TYPE = "人员属性";
	public static final String ALIAS_IDENTIFY_NUMBER = "身份证号";
	public static final String ALIAS_SEX = "性别";
	public static final String ALIAS_MOBILE = "手机号码";
	public static final String ALIAS_TEAM_CODE = "团队编码";
	public static final String ALIAS_COM_CODE = "归属机构";
	public static final String ALIAS_DISMISS_REASON = "流失原因";
	public static final String ALIAS_CHECK_STATUS = "审核状态";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	public static final String ALIAS_FLAG = "标志";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "修改时间";
	public static final String ALIAS_USERCODE = "人员代码";
	public static final String ALIAS_COMID = "省机构代码";
	/**	构造函数	**/
	public UmTSauuser() {
	}
	
	/** 主键对象 **/
	private UmTSauuserId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "saleUserId", column = @Column(name = "saleUserId"))
    })
	public UmTSauuserId getId() {
		return this.id;
	}

	public void setId(UmTSauuserId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 团队ID **/
	private String teamID;
  	/** 用户姓名 **/
	private String userName;
  	/** 人员属性 **/
	private String userType;
  	/** 身份证号 **/
	private String identifyNumber;
  	/** 性别 **/
	private String sex;
  	/** 手机号码 **/
	private String mobile;
  	/** 团队编码 **/
	private String teamCode;
  	/** 归属机构 **/
	private String comCode;
  	/** 流失原因 **/
	private String dismissReason;
  	/** 审核状态 **/
	private String checkStatus;
  	/** 有效状态 **/
	private String validStatus;
  	/** 标志 **/
	private String flag;
  	/** 备注 **/
	private String remark;
  	/** 创建时间 **/
	private Date insertTimeForHis;
  	/** 修改时间 **/
	private Date operateTimeForHis;
	/** 人员代码**/
	private String userCode;
	/** comId **/
	private String comId;
	
	/**getter setter方法*/
	@Column(name = "comId")
	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}
	@Column(name = "teamID")
	public String getTeamID() {
		return this.teamID;
	}
	
	public void setTeamID(String teamID) {
		this.teamID = teamID;
	}
	@Column(name = "userName")
	public String getUserName() {
		return this.userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name = "userType")
	public String getUserType() {
		return this.userType;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
	@Column(name = "identifyNumber")
	public String getIdentifyNumber() {
		return this.identifyNumber;
	}
	
	public void setIdentifyNumber(String identifyNumber) {
		this.identifyNumber = identifyNumber;
	}
	@Column(name = "sex")
	public String getSex() {
		return this.sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column(name = "mobile")
	public String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name = "teamCode")
	public String getTeamCode() {
		return this.teamCode;
	}
	
	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}
	@Column(name = "comCode")
	public String getComCode() {
		return this.comCode;
	}
	
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	@Column(name = "dismissReason")
	public String getDismissReason() {
		return this.dismissReason;
	}
	
	public void setDismissReason(String dismissReason) {
		this.dismissReason = dismissReason;
	}
	@Column(name = "checkStatus")
	public String getCheckStatus() {
		return this.checkStatus;
	}
	
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	@Column(name = "validStatus")
	public String getValidStatus() {
		return this.validStatus;
	}
	
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
	@Column(name = "flag")
	public String getFlag() {
		return this.flag;
	}
	
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Column(name = "remark")
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "insertTimeForHis" , updatable = false)
	public Date getInsertTimeForHis() {
		return this.insertTimeForHis;
	}
	
	public void setInsertTimeForHis(Date insertTimeForHis) {
		this.insertTimeForHis = insertTimeForHis;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "operateTimeForHis" , insertable = false)
	public Date getOperateTimeForHis() {
		return this.operateTimeForHis;
	}
	
	public void setOperateTimeForHis(Date operateTimeForHis) {
		this.operateTimeForHis = operateTimeForHis;
	}
	@Column(name = "userCode")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
}