package com.picc.um.schema.vo;

/*
 * Copyright(C)2001-2012 Actionsoft Co.,Ltd
 * AWS(Actionsoft workflow suite) BPM(Business Process Management) PLATFORM Source code 
 * AWS is a application middleware for BPM System

  
 * 本软件工程编译的二进制文件及源码版权归北京炎黄盈动科技发展有限责任公司所有，
 * 受中国国家版权局备案及相关法律保护，未经书面法律许可，任何个人或组织都不得泄漏、
 * 传播此源码文件的全部或部分文件，不得对编译文件进行逆向工程，违者必究。

 * $$本源码是炎黄盈动最高保密级别的文件$$
 * 
 * http://www.actionsoft.com.cn
 * 
 */


/**
 * User Model
 */
public class AwsUser implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
    
    public AwsUser() {
    }
	
	public static final String DATABASE_ENTITY = "ORGUSER";
    public static final String FIELD_ID = "ID";
    public static final String FIELD_USER_ID = "USERID";
    public static final String FIELD_PASSWORD = "PASSWORD";
    public static final String FIELD_DEPARTMENT_ID = "DEPARTMENTID";
    public static final String FIELD_ROLE_ID = "ROLEID";
    public static final String FIELD_SESSION_TIME = "SESSIONTIME";
    public static final String FIELD_DISENABLE = "DISENABLE";
    public static final String FIELD_USER_NAME = "USERNAME";
    public static final String FIELD_USER_NO = "USERNO";
    public static final String FIELD_LINE_NUMBER = "LINENUMBER";
    public static final String FIELD_LOGIN_COUNTER = "LOGINCOUNTER";
    public static final String FIELD_IS_MANAGER = "ISMANAGER";
    public static final String FIELD_OFFICE_TEL = "OFFICETEL";
    public static final String FIELD_OFFICE_FAX = "OFFICEFAX";
    public static final String FIELD_HOME_TEL = "HOMETEL";
    public static final String FIELD_MOBILE = "MOBILE";
    public static final String FIELD_EMAIL = "EMAIL";
    public static final String FIELD_JJLINKMAN = "JJLINKMAN";
    public static final String FIELD_JJTEL = "JJTEL";
    public static final String FIELD_ORDER_INDEX = "ORDERINDEX";
    public static final String FIELD_IS_IP_LOGIN = "ISIPLOGIN";
    public static final String FIELD_USER_IP = "USERIP";
    public static final String FIELD_IS_ROVING = "ISROVING";
    public static final String FIELD_EXTEND_1 = "EXTEND1";
    public static final String FIELD_EXTEND_2 = "EXTEND2";
    public static final String FIELD_IS_SINGLE_LOGIN = "ISSINGLELOGIN";
    public static final String FIELD_WORK_STATUS = "WORK_STATUS";
    public static final String FIELD_SMID = "SMID";
    public static final String FIELD_PC_MAN = "PC_MAN";
    public static final String FIELD_AWSPASS = "AWSPASS";
    public static final String FIELD_EMAILPASS = "EMAILPASS";
    public static final String FIELD_LAYOUT_MODEL = "LAYOUT_MODEL";
    public static final String FIELD_POSITION_NO = "POSITION_NO";
    public static final String FIELD_POSITION_NAME = "POSITION_NAME";
    public static final String FIELD_POSITION_LAYER = "POSITION_LAYER";
    public static final String FIELD_EXTEND_3 = "EXTEND3";
    public static final String FIELD_EXTEND_4 = "EXTEND4";
    public static final String FIELD_EXTEND_5 = "EXTEND5";
    public static final String FIELD_MSNID = "MSNID";
    public static final String FIELD_WORKCANLENDAR = "WORKCANLENDAR";
    public static final String FIELD_INTEGRATEID = "INTEGRATEID";
    
    
    private int id;
    private String uid = "";
    private String password = "";
    private int departmentId;
    private int roleId;
    private long sessionTime = 60 * 12;
    private boolean disabled;
    private String userName = "";
    private String userNameAlias = "";
    private String userNo = "";
    private int lineNumber = 18;
    private int loginCounter = 0;
    private boolean manager = false;
    private String officeTel = "";
    private String officeFax = "";
    private String homeTel = "";
    private String mobile = "";
    private String email = "";
    private String jjLinkMan = "";
    private String jjTel = "";
    private int orderIndex = 0;
    private boolean ipLogin = false;
    private boolean roving = false;
    private String userIp = "";
    private boolean singleLogin;
    private String workStatus="已离开";
    private String smid="";
    private String pcMan="";
    private String awsPass="";
    private String emailPass="";
    private String layoutModel="aws-portlet-layout.7";
    private String positionNo="";
    private String positionName="";
    private String positionLayer="";
    private String workcanlendar="";
    private String extend1 = "";
    private String extend2 = "";
    private String extend3 = "";
    private String extend4 = "";
    private String extend5 = "";
    private String msnId = "";
    private int cacheLoginTimes = 0;
    private long cachePauseTime = 0;
    private String integrateId = "";
    
    public void setModel(Object obj) {
        AwsUser model = (AwsUser) obj;
        this.setDepartmentId(model.getDepartmentId());
        this.setId(model.getId());
        this.setDisabled(model.isDisabled());
        this.setLineNumber(model.getLineNumber());
        this.setLoginCounter(model.getLoginCounter());
        this.setPassword(model.getPassword());
        this.setRoleId(model.getRoleId());
        this.setSessionTime(model.getSessionTime());
        this.setUID(model.getUID());
        this.setUserNameAlias(model.getUserNameAlias());
        this.setUserName(model.getUserName());
        this.setUserNo(model.getUserNo());
        this.setManager(model.isManager());
        this.setHomeTel(model.getHomeTel());
        this.setEmail(model.getEmail());
        this.setOfficeFax(model.getOfficeFax());
        this.setOfficeTel(model.getOfficeTel());
        this.setMobile(model.getMobile());
        this.setJjLinkMan(model.getJjLinkMan());
        this.setJjTel(model.getJjTel());
        this.setOrderIndex(model.getOrderIndex());
        this.setIpLogin(model.isIpLogin());
        this.setUserIp(model.getUserIp());
        this.setRoving(model.isRoving());
        this.setExtend1(model.getExtend1());
        this.setExtend2(model.getExtend2());
        this.setSingleLogin(model.isSingleLogin());
        this.setWorkStatus(model.getWorkStatus());
        this.setSMid(model.getSMid());
        this.setPcMan(model.getPcMan());
        this.setEmailPass(model.getEmailPass());
        this.setAwsPass(model.getAwsPass());
        this.setLayoutModel(model.getLayoutModel());
        this.setPositionNo(model.getPositionNo());
        this.setPositionName(model.getPositionName());
        this.setPositionLayer(model.getPositionLayer());
        this.setExtend3(model.getExtend3());
        this.setExtend4(model.getExtend4());
        this.setExtend5(model.getExtend5());
        this.setMsnId(model.getMsnId());
        this.setWorkcanlendar(model.getWorkcanlendar());
        //设置cache值 add by zhanghf 2010.10.14
        this.setCacheLoginTimes(model.getCacheLoginTimes());
        this.setCachePauseTime(model.getCachePauseTime());
    }

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setUID(String uid) {
		this.uid = uid;
	}

	public String getUID() {
		return uid;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setSessionTime(long sessionTime) {
		this.sessionTime = sessionTime;
	}

	public long getSessionTime() {
		return sessionTime;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public String setUserName(String userName) {
		this.userName = userName;
		return userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserNameAlias(String userNameAlias) {
		this.userNameAlias = userNameAlias;
	}

	public String getUserNameAlias() {
		return userNameAlias;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLoginCounter(int loginCounter) {
		this.loginCounter = loginCounter;
	}

	public int getLoginCounter() {
		return loginCounter;
	}

	public void setManager(boolean manager) {
		this.manager = manager;
	}

	public boolean isManager() {
		return manager;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	public String getOfficeTel() {
		return officeTel;
	}

	public void setOfficeFax(String officeFax) {
		this.officeFax = officeFax;
	}

	public String getOfficeFax() {
		return officeFax;
	}

	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}

	public String getHomeTel() {
		return homeTel;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return mobile;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setJjLinkMan(String jjLinkMan) {
		this.jjLinkMan = jjLinkMan;
	}

	public String getJjLinkMan() {
		return jjLinkMan;
	}

	public void setJjTel(String jjTel) {
		this.jjTel = jjTel;
	}

	public String getJjTel() {
		return jjTel;
	}

	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}

	public int getOrderIndex() {
		return orderIndex;
	}

	public void setIpLogin(boolean ipLogin) {
		this.ipLogin = ipLogin;
	}

	public boolean isIpLogin() {
		return ipLogin;
	}

	public void setRoving(boolean roving) {
		this.roving = roving;
	}

	public boolean isRoving() {
		return roving;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setSingleLogin(boolean singleLogin) {
		this.singleLogin = singleLogin;
	}

	public boolean isSingleLogin() {
		return singleLogin;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setSMid(String smid) {
		this.smid = smid;
	}

	public String getSMid() {
		return smid;
	}

	public void setPcMan(String pcMan) {
		this.pcMan = pcMan;
	}

	public String getPcMan() {
		return pcMan;
	}

	public void setAwsPass(String awsPass) {
		this.awsPass = awsPass;
	}

	public String getAwsPass() {
		return awsPass;
	}

	public void setEmailPass(String emailPass) {
		this.emailPass = emailPass;
	}

	public String getEmailPass() {
		return emailPass;
	}

	public void setLayoutModel(String layoutModel) {
		this.layoutModel = layoutModel;
	}

	public String getLayoutModel() {
		return layoutModel;
	}

	public void setPositionNo(String positionNo) {
		this.positionNo = positionNo;
	}

	public String getPositionNo() {
		return positionNo;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionLayer(String positionLayer) {
		this.positionLayer = positionLayer;
	}

	public String getPositionLayer() {
		return positionLayer;
	}

	public void setWorkcanlendar(String workcanlendar) {
		this.workcanlendar = workcanlendar;
	}

	public String getWorkcanlendar() {
		return workcanlendar;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}

	public String getExtend1() {
		return extend1;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}

	public String getExtend2() {
		return extend2;
	}

	public void setExtend3(String extend3) {
		this.extend3 = extend3;
	}

	public String getExtend3() {
		return extend3;
	}

	public void setExtend4(String extend4) {
		this.extend4 = extend4;
	}

	public String getExtend4() {
		return extend4;
	}

	public void setExtend5(String extend5) {
		this.extend5 = extend5;
	}

	public String getExtend5() {
		return extend5;
	}

	public void setMsnId(String msnId) {
		this.msnId = msnId;
	}

	public String getMsnId() {
		return msnId;
	}

	public void setCacheLoginTimes(int cacheLoginTimes) {
		this.cacheLoginTimes = cacheLoginTimes;
	}

	public int getCacheLoginTimes() {
		return cacheLoginTimes;
	}

	public void setCachePauseTime(long cachePauseTime) {
		this.cachePauseTime = cachePauseTime;
	}

	public long getCachePauseTime() {
		return cachePauseTime;
	}
	
	public String getIntegrateId() {
		return integrateId == null ? "" : integrateId;
	}

	public void setIntegrateId(String integrateId) {
		this.integrateId = integrateId;
	}
}
