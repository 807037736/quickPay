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
@Table(name = "um_t_saugroup")
public class UmTSaugroup implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//别名，页面中使用
	public static final String ALIAS_TEAM_I_D = "团队ID";
	public static final String ALIAS_UPPER_TEAM_I_D = "上级团队ID";
	public static final String ALIAS_TEAM_CODE = "团队编号";
	public static final String ALIAS_TEAM_NAME = "团队名称";
	public static final String ALIAS_TEAM_LEVEL = "团队级别";
	public static final String ALIAS_TEAM_TYPE = "团队类型";
	public static final String ALIAS_BRANCH_TYPE = "团队外部类型";
	public static final String ALIAS_MANAGER_CODE = "团队经理工号";
	public static final String ALIAS_MANAGER_NAME = "团队经理名称";
	public static final String ALIAS_CHARGE_MGR_CODE = "团队分管副经理工号";
	public static final String ALIAS_CHARGE_MGR_NAME = "团队分管副经理姓名";
	public static final String ALIAS_TRAIN_MGR_CODE = "团队组训经理工号";
	public static final String ALIAS_TRAIN_MGR_NAME = "团队组训经理姓名";
	public static final String ALIAS_ADDRESS = "地址";
	public static final String ALIAS_COM_CODE = "机构代码";
	public static final String ALIAS_UPPER_PATH = "所有上级团队路径";
	public static final String ALIAS_BRANCH_PHONE = "团队联系方式";
	public static final String ALIAS_CREATE_DATE = "网点创建时间";
	public static final String ALIAS_EXPIRE_DATE = "网点失效日期";
	public static final String ALIAS_CHECK_DATE = "审核状态";
	public static final String ALIAS_VALID_STATUS = "有效状态";
	public static final String ALIAS_FLAG = "标志";
	public static final String ALIAS_INSERT_TIME_FOR_HIS = "创建时间";
	public static final String ALIAS_OPERATE_TIME_FOR_HIS = "修改时间";
	public static final String ALIAS_COMID = "省机构代码";
	
	/**	构造函数	**/
	public UmTSaugroup() {
	}
	
	/** 主键对象 **/
	private UmTSaugroupId id;
	
    @EmbeddedId
    @AttributeOverrides( {
      @AttributeOverride(name = "teamID", column = @Column(name = "teamID"))
    })
	public UmTSaugroupId getId() {
		return this.id;
	}

	public void setId(UmTSaugroupId id) {
		this.id = id;
	}
	
	
	/**属性*/
  	/** 上级团队ID **/
	private String upperTeamID;
  	/** 团队编号 **/
	private String teamCode;
  	/** 团队名称 **/
	private String teamName;
  	/** 团队级别 **/
	private String teamLevel;
  	/** 团队类型 **/
	private String teamType;
  	/** 团队外部类型 **/
	private String branchType;
  	/** 团队经理工号 **/
	private String managerCode;
  	/** 团队经理名称 **/
	private String managerName;
  	/** 团队分管副经理工号 **/
	private String chargeMgrCode;
  	/** 团队分管副经理姓名 **/
	private String chargeMgrName;
  	/** 团队组训经理工号 **/
	private String trainMgrCode;
  	/** 团队组训经理姓名 **/
	private String trainMgrName;
  	/** 地址 **/
	private String address;
  	/** 机构代码 **/
	private String comCode;
  	/** 所有上级团队路径 **/
	private String upperPath;
  	/** 团队联系方式 **/
	private String branchPhone;
  	/** 网点创建时间 **/
	private Date createDate;
  	/** 网点失效日期 **/
	private Date expireDate;
  	/** 审核状态 **/
	private String checkDate;
  	/** 有效状态 **/
	private String validStatus;
  	/** 标志 **/
	private String flag;
  	/** 创建时间 **/
	private Date insertTimeForHis;
  	/** 修改时间 **/
	private Date operateTimeForHis;
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
	
	@Column(name = "upperTeamID")
	public String getUpperTeamID() {
		return this.upperTeamID;
	}
	
	public void setUpperTeamID(String upperTeamID) {
		this.upperTeamID = upperTeamID;
	}
	@Column(name = "teamCode")
	public String getTeamCode() {
		return this.teamCode;
	}
	
	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}
	@Column(name = "teamName")
	public String getTeamName() {
		return this.teamName;
	}
	
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	@Column(name = "teamLevel")
	public String getTeamLevel() {
		return this.teamLevel;
	}
	
	public void setTeamLevel(String teamLevel) {
		this.teamLevel = teamLevel;
	}
	@Column(name = "teamType")
	public String getTeamType() {
		return this.teamType;
	}
	
	public void setTeamType(String teamType) {
		this.teamType = teamType;
	}
	@Column(name = "branchType")
	public String getBranchType() {
		return this.branchType;
	}
	
	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}
	@Column(name = "managerCode")
	public String getManagerCode() {
		return this.managerCode;
	}
	
	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}
	@Column(name = "managerName")
	public String getManagerName() {
		return this.managerName;
	}
	
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	@Column(name = "chargeMgrCode")
	public String getChargeMgrCode() {
		return this.chargeMgrCode;
	}
	
	public void setChargeMgrCode(String chargeMgrCode) {
		this.chargeMgrCode = chargeMgrCode;
	}
	@Column(name = "chargeMgrName")
	public String getChargeMgrName() {
		return this.chargeMgrName;
	}
	
	public void setChargeMgrName(String chargeMgrName) {
		this.chargeMgrName = chargeMgrName;
	}
	@Column(name = "trainMgrCode")
	public String getTrainMgrCode() {
		return this.trainMgrCode;
	}
	
	public void setTrainMgrCode(String trainMgrCode) {
		this.trainMgrCode = trainMgrCode;
	}
	@Column(name = "trainMgrName")
	public String getTrainMgrName() {
		return this.trainMgrName;
	}
	
	public void setTrainMgrName(String trainMgrName) {
		this.trainMgrName = trainMgrName;
	}
	@Column(name = "address")
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "comCode")
	public String getComCode() {
		return this.comCode;
	}
	
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	@Column(name = "upperPath")
	public String getUpperPath() {
		return this.upperPath;
	}
	
	public void setUpperPath(String upperPath) {
		this.upperPath = upperPath;
	}
	@Column(name = "branchPhone")
	public String getBranchPhone() {
		return this.branchPhone;
	}
	
	public void setBranchPhone(String branchPhone) {
		this.branchPhone = branchPhone;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createDate")
	public Date getCreateDate() {
		return this.createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expireDate")
	public Date getExpireDate() {
		return this.expireDate;
	}
	
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	@Column(name = "checkDate")
	public String getCheckDate() {
		return this.checkDate;
	}
	
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
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
		
}