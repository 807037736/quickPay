package com.picc.qp.schema.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.picc.common.utils.StringUtils;

/**
 *  微信当事人信息
 * @author ff
 * 2016年7月4日
 */
@Entity
@Table(name = "wx_accident")
public class WxAccident  implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	// private WxAccidentId id;

	/**** 属性 ****/
	private Integer id;
	private String accidentname;// 当事人名称
	private String mobile;// 手机号
	private String licensenumber;// 车牌号
	private String identfinynumber;// 身份证号
	private String responsibilitycode;// 责任编号
	private String caseid;// 案件临时id
	private Date createdate;// 创建时间
	private Date modifydate;// 修改时间
	private String groupid;
	private String coid;// 保险公司ID
	private String companyNameOther;// 其他保险公司名称
	private String cartype;// 车辆类型
	private String drivetype;// 准驾车型
	private String lawid;// 违反法规ID
	private String realAccID;// 真实当事人ID
	private String openID;// 微信openID
	private String chassisNumber;//车架号
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "accidentname")
	public String getAccidentname() {
		return accidentname;
	}

	public void setAccidentname(String accidentname) {
		this.accidentname = accidentname;
	}

	@Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "licensenumber")
	public String getLicensenumber() {
		return licensenumber;
	}

	public void setLicensenumber(String licensenumber) {
		this.licensenumber = licensenumber;
	}

	@Column(name = "identfinynumber")
	public String getIdentfinynumber() {
		return identfinynumber;
	}

	public void setIdentfinynumber(String identfinynumber) {
		this.identfinynumber = identfinynumber;
	}

	@Column(name = "responsibilitycode")
	public String getResponsibilitycode() {
		return responsibilitycode;
	}

	public void setResponsibilitycode(String responsibilitycode) {
		this.responsibilitycode = responsibilitycode;
	}

	@Column(name = "caseid")
	public String getCaseid() {
		return caseid;
	}

	public void setCaseid(String caseid) {
		this.caseid = caseid;
	}

	@Column(name = "createdate")
	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@Column(name = "modifydate")
	public Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	@Column(name = "groupid")
	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	@Column(name = "coid")
	public String getCoid() {
		return coid;
	}

	public void setCoid(String coid) {
		this.coid = coid;
	}

	@Column(name = "companyNameOther")
	public String getCompanyNameOther() {
	    return companyNameOther;
	}

	public void setCompanyNameOther(String companyNameOther) {
	    this.companyNameOther = companyNameOther;
	}

	@Column(name = "cartype")
	public String getCartype() {
		return cartype;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
	}

	@Column(name = "drivetype")
	public String getDrivetype() {
		return drivetype;
	}

	public void setDrivetype(String drivetype) {
		this.drivetype = drivetype;
	}

	@Column(name = "lawid")
	public String getLawid() {
		return lawid;
	}

	public void setLawid(String lawid) {
		this.lawid = lawid;
	}

	@Column(name = "realAccID")
	public String getRealAccID() {
		return realAccID;
	}

	public void setRealAccID(String realAccID) {
		this.realAccID = realAccID;
	}
	
	@Column(name = "chassisNumber")
	public String getChassisNumber() {
		return chassisNumber;
	}

	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}

	@Column(name = "openID")
	public String getOpenID() {
		return openID;
	}

	public void setOpenID(String openID) {
		this.openID = openID;
	}

	public WxAccident mergeFromAccident(WxAccident accident) {
		if (accident.getAccidentname() != null) {
			this.setAccidentname(accident.getAccidentname());
		}
		if (accident.getMobile() != null) {
			this.setMobile(accident.getMobile());
		}
		if (accident.getLicensenumber() != null) {
			this.setLicensenumber(accident.getLicensenumber());
		}
		if (accident.getIdentfinynumber() != null) {
			this.setIdentfinynumber(accident.getIdentfinynumber());
		}
		if (accident.getResponsibilitycode() != null) {
			this.setResponsibilitycode(accident.getResponsibilitycode());
		}
		if (accident.getCoid() != null) {
			this.setCoid(accident.getCoid());
		}
		if (accident.getCompanyNameOther() != null) {
			this.setCompanyNameOther(accident.getCompanyNameOther());
		}
		if (accident.getCartype() != null) {
			this.setCartype(accident.getCartype());
		}
		if (accident.getDrivetype() != null) {
			this.setDrivetype(accident.getDrivetype());
		}
		if (accident.getLawid() != null) {
			this.setLawid(accident.getLawid());
		}
		if (accident.getGroupid() != null) {
			this.setGroupid(accident.getGroupid());
		}
		if (accident.getRealAccID() != null) {
			this.setRealAccID(accident.getRealAccID());
		}
		if (accident.getOpenID() != null) {
			this.setOpenID(accident.getOpenID());
		}
		this.setModifydate(new Date());
		return this;
	}
	
	public QpTICAccident changeToAccFromWxAcc(QpTTPCase qpTTPCase) {
		QpTICAccident accident = new QpTICAccident();
		accident.setDriverName(this.getAccidentname());
		accident.setDriverMobile(this.getMobile());
		accident.setDriverIDType("6");
		accident.setDriverIDNumber(this.getIdentfinynumber());
		// 根据身份证保存生日和性别
		try{
			String sexResult = StringUtils.getGenderFromIdCard(this.getIdentfinynumber());
			if ("M".equals(sexResult)){
				accident.setDriverSex("0");
				accident.setDriverSexDesc("男");
			}
			if ("F".equals(sexResult)){
				accident.setDriverSex("1");
				accident.setDriverSexDesc("女");
			}
			accident.setBirthDay(StringUtils.getBirthdayFromIdCard(this.getIdentfinynumber()));
		}catch(Exception e){
            e.printStackTrace();
		}
		accident.setDriverVehicleType(this.getCartype());
		accident.setDriverVehicleNumber(this.getLicensenumber());
		accident.setPermissionDrive(this.getDrivetype());
		accident.setCoId(this.getCoid());
		accident.setCompanyNameOther(this.getCompanyNameOther());
		accident.setGroupId(this.getGroupid());
		accident.setDriverLiability(this.getResponsibilitycode());
		accident.setAcciTime(qpTTPCase.getCaseTime());
		accident.setOpenID(this.getOpenID());
		accident.setChassisNumber(this.getChassisNumber());
		// 默认设置
		accident.setAcciCity("430100");
		return accident;
	}
	
	public QpTICAccident changeToAccFromWxAcc(QpTTPCase qpTTPCase, QpTICAccident qpTICAccident) {
		if (qpTICAccident == null) {
			return null;
		}
		QpTICAccident accident = qpTICAccident;
		accident.setDriverName(this.getAccidentname());
		accident.setDriverMobile(this.getMobile());
		accident.setDriverIDType("6");
		accident.setDriverIDNumber(this.getIdentfinynumber());
		// 根据身份证保存生日和性别
		try{
			String sexResult = StringUtils.getGenderFromIdCard(this.getIdentfinynumber());
			if ("M".equals(sexResult)){
				accident.setDriverSex("0");
				accident.setDriverSexDesc("男");
			}
			if ("F".equals(sexResult)){
				accident.setDriverSex("1");
				accident.setDriverSexDesc("女");
			}
			accident.setBirthDay(StringUtils.getBirthdayFromIdCard(this.getIdentfinynumber()));
		}catch(Exception e){
            e.printStackTrace();
		}
		accident.setDriverVehicleType(this.getCartype());
		accident.setDriverVehicleNumber(this.getLicensenumber());
		accident.setPermissionDrive(this.getDrivetype());
		accident.setCoId(this.getCoid());
		accident.setCompanyNameOther(this.getCompanyNameOther());
		accident.setGroupId(this.getGroupid());
		accident.setDriverLiability(this.getResponsibilitycode());
		accident.setOpenID(this.getOpenID());
		accident.setChassisNumber(this.getChassisNumber());
		return accident;
	}
	
}
