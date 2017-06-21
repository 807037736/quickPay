/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.picc.common.utils.ToolsUtils;
import com.picc.qp.schema.model.QpTICCompany;
import com.picc.qp.schema.model.QpTTPFastCenter;
import com.picc.qp.schema.model.QpTTPFastCentercompare;
import com.picc.qp.schema.model.QpTTPTeam;
import com.picc.qp.service.facade.IQpTICCompanyService;
import com.picc.qp.service.facade.IQpTTPFastCenterService;
import com.picc.qp.service.facade.IQpTTPTeamService;
import com.picc.um.schema.model.UmTAccount;
import com.picc.um.schema.model.UmTAccountId;
import com.picc.um.schema.model.UmTSMSValid;
import com.picc.um.schema.model.UmTSMSValidId;
import com.picc.um.schema.model.UmTUser;
import com.picc.um.schema.model.UmTUserId;
import com.picc.um.schema.vo.SessionUser;
import com.picc.um.security.DESUtils;
import com.picc.um.service.facade.IUmTAccountService;
import com.picc.um.service.facade.IUmTSMSValidService;
import com.picc.um.service.facade.IUmTSaugroupService;
import com.picc.um.service.facade.IUmTSauuserService;
import com.picc.um.service.facade.IUmTUserService;
import com.sinosoft.bpsdriver.domain.getUserMsg.UserMsgResInfo;
import com.sinosoft.bpsdriver.service.facade.UserMgrAPIService;
import com.sinosoft.bpsdriver.service.spring.UserMgrAPIServiceImpl;


/** 
* @ClassName: UmTUserAction 
* @Description: TODO 用户管理、我的信息Action
* @author dijie
* @date 2013-8-6 上午10:22:42 
*  
*/

public class UmTUserAction extends Struts2Action{
	
	private IUmTUserService umTUserService;	
	private IQpTICCompanyService qpTICCompanyService;
	
	public IQpTICCompanyService getQpTICCompanyService() {
		return qpTICCompanyService;
	}

	public void setQpTICCompanyService(IQpTICCompanyService qpTICCompanyService) {
		this.qpTICCompanyService = qpTICCompanyService;
	}
	public void setUmTUserService(IUmTUserService umTUserService) {
		this.umTUserService = umTUserService;
	}

	public IUmTUserService getUmTUserService() {
		return umTUserService;
	}
	
	public IUmTSauuserService getUmTSauuserService() {
		return umTSauuserService;
	}

	public void setUmTSauuserService(IUmTSauuserService umTSauuserService) {
		this.umTSauuserService = umTSauuserService;
	}

	public IUmTSaugroupService getUmTSaugroupService() {
		return umTSaugroupService;
	}

	public void setUmTSaugroupService(IUmTSaugroupService umTSaugroupService) {
		this.umTSaugroupService = umTSaugroupService;
	}

	private IUmTSauuserService umTSauuserService;
	
	private IUmTSaugroupService umTSaugroupService;
	
	private IUmTAccountService umTAccountService;
	
	private IUmTSMSValidService umTSMSValidService;
	
	
	private IQpTTPFastCenterService qpTTPFastCenterService;
	
	private IQpTTPTeamService qpTTPTeamService;
	
	private SessionUser sessionUser=(SessionUser)getRequest().getSession().getAttribute("SessionUser");
	public IUmTSMSValidService getUmTSMSValidService() {
		return umTSMSValidService;
	}

	public void setUmTSMSValidService(IUmTSMSValidService umTSMSValidService) {
		this.umTSMSValidService = umTSMSValidService;
	}

	public IUmTAccountService getUmTAccountService() {
		return umTAccountService;
	}

	public void setUmTAccountService(IUmTAccountService umTAccountService) {
		this.umTAccountService = umTAccountService;
	}
	
	public IQpTTPFastCenterService getQpTTPFastCenterService() {
        return qpTTPFastCenterService;
    }

    public void setQpTTPFastCenterService(IQpTTPFastCenterService qpTTPFastCenterService) {
        this.qpTTPFastCenterService = qpTTPFastCenterService;
    }

    public IQpTTPTeamService getQpTTPTeamService() {
        return qpTTPTeamService;
    }

    public void setQpTTPTeamService(IQpTTPTeamService qpTTPTeamService) {
        this.qpTTPTeamService = qpTTPTeamService;
    }

    private UmTUser umTUser;
	
	private UmTAccount umTAccount;
	
	private UmTUserId id;
	
	
	/** 同步数据内容 **/
	private UserMsgResInfo userMsgResInfo;
	private String USERCODE;
	private String USERNAME;
	private String MOBILE;
	private String COMCODE;
	private String EMAIL;
	private String FLAG;
	private String USERTYPE;
	private String USERSORT;
	private String IDENTIFYNUMBER;
	private String AGE;
	private String SEX;
	private String BIRTHDATE;
	
	public String getCOMCODE() {
		return COMCODE;
	}

	public void setCOMCODE(String cOMCODE) {
		COMCODE = cOMCODE;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getFLAG() {
		return FLAG;
	}

	public void setFLAG(String fLAG) {
		FLAG = fLAG;
	}

	public String getUSERTYPE() {
		return USERTYPE;
	}

	public void setUSERTYPE(String uSERTYPE) {
		USERTYPE = uSERTYPE;
	}

	public String getUSERSORT() {
		return USERSORT;
	}

	public void setUSERSORT(String uSERSORT) {
		USERSORT = uSERSORT;
	}

	public String getIDENTIFYNUMBER() {
		return IDENTIFYNUMBER;
	}

	public void setIDENTIFYNUMBER(String iDENTIFYNUMBER) {
		IDENTIFYNUMBER = iDENTIFYNUMBER;
	}

	public String getAGE() {
		return AGE;
	}

	public void setAGE(String aGE) {
		AGE = aGE;
	}

	public String getSEX() {
		return SEX;
	}

	public void setSEX(String sEX) {
		SEX = sEX;
	}

	public String getBIRTHDATE() {
		return BIRTHDATE;
	}

	public void setBIRTHDATE(String bIRTHDATE) {
		BIRTHDATE = bIRTHDATE;
	}

	public String getMOBILE() {
		return MOBILE;
	}

	public void setMOBILE(String mOBILE) {
		MOBILE = mOBILE;
	}

	public String getUSERNAME() {
		return USERNAME;
	}

	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}

	

	public String getUSERCODE() {
		return USERCODE;
	}

	public void setUSERCODE(String uSERCODE) {
		USERCODE = uSERCODE;
	}

	/** 操作类型 **/
	private String opreateType;
	/** UmTUser getter/setter **/
	
	
	
	
	public UmTUser getUmTUser() {
		return this.umTUser;
	}
	
	public UmTAccount getUmTAccount() {
		return umTAccount;
	}

	public void setUmTAccount(UmTAccount umTAccount) {
		this.umTAccount = umTAccount;
	}

	public UserMsgResInfo getUserMsgResInfo() {
		return userMsgResInfo;
	}

	public void setUserMsgResInfo(UserMsgResInfo userMsgResInfo) {
		this.userMsgResInfo = userMsgResInfo;
	}

	public void setUmTUser(UmTUser umTUser) {
		this.umTUser = umTUser;
	}
	/** UmTUserId getter/setter **/
	public UmTUserId getId() {
		return this.id;
	}
	
	public void setId(UmTUserId id) {
		this.id = id;
	}
	/** opreateType getter/setter **/
	public String getOpreateType() {
		return opreateType;
	}

	public void setOpreateType(String opreateType) {
		this.opreateType = opreateType;
	}
	
	/** 主键对象 */
	private String userCode;
	public String getUserCode() {
		return this.userCode;
	}
	
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	private String teamCode;
	private String teamName;
	private String interests;
	private String mobile;
	private String verificationCode;
	private String oldPassWord;
	private String newPassWord;
	private String verification;
	

	public String getVerification() {
		return verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
	}

	public String getOldPassWord() {
		return oldPassWord;
	}

	public void setOldPassWord(String oldPassWord) {
		this.oldPassWord = oldPassWord;
	}

	public String getNewPassWord() {
		return newPassWord;
	}

	public void setNewPassWord(String newPassWord) {
		this.newPassWord = newPassWord;
	}

	
	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}
	
	// 封装上传文件域的属性
    private File img;
	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public String getImgContentType() {
		return imgContentType;
	}

	public void setImgContentType(String imgContentType) {
		this.imgContentType = imgContentType;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	// 封装上传文件类型的属性
    private String imgContentType;
	// 封装上传文件名的属性
    private String imgFileName;
    // 接受依赖注入的属性
    private String savePath;


	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	
	private final long _timeout = 60*60*24;
	
	
	/****************************Function Start*******************************/
	


	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 */
	public String prepareQuery() throws Exception {
	    
	    HttpServletRequest httpServletRequest = this.getRequest();
	    
        // 所属交警大队
        QpTTPTeam qpTTPTeam = new QpTTPTeam();
        List<QpTTPTeam> qpTTPTeamList = qpTTPTeamService.findByQpTTPTeam(qpTTPTeam);
        httpServletRequest.setAttribute("qpTTPTeamList", qpTTPTeamList);
        
        // 受理点
        QpTTPFastCenter qpTTPFastCenter = new QpTTPFastCenter();
        qpTTPFastCenter.setValidStatus("1");
        List<QpTTPFastCenter> fastCenterList = qpTTPFastCenterService.findByQpTTPFastCenter(qpTTPFastCenter);
        httpServletRequest.setAttribute("fastCenterList", fastCenterList);
        
	    // 保险公司
	    List<QpTICCompany> qpTICCompanyList = qpTICCompanyService.findByQpTICCompany(new QpTICCompany());
	    httpServletRequest.setAttribute("qpTICCompanyList", qpTICCompanyList);
		
		return SUCCESS;
	}
	
	/*送修码配置页面*/
	public String prepareUserMonopolyQuery() throws Exception {		
				
		return SUCCESS;
	}
	
	public String prepareUserMonopoly() throws Exception {		

		return SUCCESS;

	}
	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 */
	public String prepareConfig() throws Exception {		
		
		
		return SUCCESS;
	}
	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 */
	public String prepareQueryOuter() throws Exception {		
		
		
		return SUCCESS;
	}
	
	/**
	 * UmTUser查询，根据umTUser带过来的值为查询条件进行查询。
	 * 
	 * @return
	 */
	public String query() throws Exception {
		
		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 20;
		}
		try {
//			DictPage page1 =PageService.getAgent(SystemCode.DMS, "", 1, Integer.MAX_VALUE);
//			Date date1= new Date();
//			System.out.println(date1.toString());
//			DictPage page1=PageService.getAgent(SystemCode.DMS, "", date1, "", "", "", "", 1, Integer.MAX_VALUE);
//			System.out.println("****"+page1.getTotalRecordCount());
			umTUser.setComId(getUserFromSession().getComId());
			umTUser.setCity(getUserFromSession().getCity());
			Page resultPage = umTUserService.findByUmTUserSec(umTUser, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 根据接口查询用户。
	 * 
	 * @return
	 */
	public String queryOuter() throws Exception {
		
		UserMgrAPIService userService = new UserMgrAPIServiceImpl();
		try {
			userMsgResInfo = userService.getUserMsg(userMsgResInfo.getUSERCODE());
			List<UserMsgResInfo> list = new ArrayList<UserMsgResInfo>();
			list.add(userMsgResInfo);
		    this.writeEasyUiData(list);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("", e);
				Map resultMap = new HashMap();
				resultMap.put("errorTitle", "错误信息(ERROR)：");
				resultMap.put("errorMsg", "该用户在用户系统中不存在！");
				this.writeAjaxErrorByMap(resultMap);
			}
			return NONE;
	}	
	
	/**
	 * 准备更新UmTUser信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		
		opreateType = "update";
		
		HttpServletRequest httpServletRequest = this.getRequest();
        // 所属交警大队
        QpTTPTeam qpTTPTeam = new QpTTPTeam();
        List<QpTTPTeam> qpTTPTeamList = qpTTPTeamService.findByQpTTPTeam(qpTTPTeam);
        httpServletRequest.setAttribute("qpTTPTeamList", qpTTPTeamList);
        
        // 受理点
        QpTTPFastCenter qpTTPFastCenter = new QpTTPFastCenter();
        qpTTPFastCenter.setValidStatus("1");
        List<QpTTPFastCenter> fastCenterList = qpTTPFastCenterService.findByQpTTPFastCenter(qpTTPFastCenter);
        httpServletRequest.setAttribute("fastCenterList", fastCenterList);
        
     // 保险公司
	    List<QpTICCompany> qpTICCompanyList = qpTICCompanyService.findByQpTICCompany(new QpTICCompany());
	    httpServletRequest.setAttribute("qpTICCompanyList", qpTICCompanyList);
		
        UmTUserId umTUserId = new UmTUserId();
        umTUserId.setUserCode(httpServletRequest.getParameter("usercode"));
        
		umTUser = umTUserService.findUmTUserByPK(umTUserId);
		if(ToolsUtils.notEmpty(umTUser.getUserSort()) && (umTUser.getUserSort().equals("04")||umTUser.getUserSort().equals("02")) ){
			List<QpTTPFastCentercompare> fastCentercompareList = qpTTPFastCenterService.findQpTTPFastCenterCompare(umTUser);
			 String centercompareName = "";
		     for(QpTTPFastCentercompare centercompare : fastCentercompareList){
		    	centercompareName += centercompare.getCenterName()+",";
	         }
			 httpServletRequest.setAttribute("centercompareName", centercompareName);
		}
		
		UmTAccountId umTAccountId = new UmTAccountId();
		umTAccountId.setUserCode(httpServletRequest.getParameter("usercode"));
		umTAccount = umTAccountService.findUmTAccountByPK(umTAccountId);
		
		return SUCCESS;
	}
	
	/**
	 * 更新UmTUser信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
	    HttpServletRequest httpServletRequest = this.getRequest();
		try{
			//地市交警02，或查勘员04
			if(("02".equals(umTUser.getUserSort()) || "04".equals(umTUser.getUserSort())) && ToolsUtils.notEmpty(umTUser.getSurveyCenterId())){
				if(umTUser.getSurveyCenterId().contains(",") || (!umTUser.getSurveyCenterId().contains(",") && umTUser.getSurveyCenterId().length()==1)){
					qpTTPFastCenterService.updateQpTTPFastCenterCompare(umTUser);
				}
			}
			if("0".equals(umTUser.getCenterId()) || "".equals(umTUser.getCenterId()) ){
				umTUser.setCenterId(null);
			}
			if("0".equals(umTUser.getCoId()) || "".equals(umTUser.getCoId()) ){
				umTUser.setCoId(null);
			}
			if("0".equals(umTUser.getPoliceTeamId()) || "".equals(umTUser.getPoliceTeamId()) ){
				umTUser.setPoliceTeamId(null);
			}
			String updater =getUserFromSession().getUserCode();
			umTUser.setUpdaterCode(updater);
			umTUser.setComId(getUserFromSession().getComId());
			umTUser.setProvince(httpServletRequest.getParameter("umTUserProvince"));
            umTUser.setCity(httpServletRequest.getParameter("umTUserCity"));
            umTUser.setDistrict(httpServletRequest.getParameter("umTUserDistrict"));
            umTUser.setRoad(httpServletRequest.getParameter("umTUserRoad"));
            umTUser.setStreet(httpServletRequest.getParameter("umTUserStreet"));
			umTUserService.updateUmTUser(umTUser);
			this.writeString(SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("", e);
			this.writeJSONMsg(e.getMessage());
		}
		return NONE;
	}


	/**
	 * 准备增加UmTUser信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
	    opreateType = "add";
	    
	    SessionUser sessionUser = getUserFromSession();
	    HttpServletRequest httpServletRequest = this.getRequest();
	    // 所属交警大队
	    QpTTPTeam qpTTPTeam = new QpTTPTeam();
	    List<QpTTPTeam> qpTTPTeamList = qpTTPTeamService.findByQpTTPTeam(qpTTPTeam);
	    httpServletRequest.setAttribute("qpTTPTeamList", qpTTPTeamList);
	    
		// 受理点
        QpTTPFastCenter qpTTPFastCenter = new QpTTPFastCenter();
        qpTTPFastCenter.setValidStatus("1");
        List<QpTTPFastCenter> fastCenterList = qpTTPFastCenterService.findByQpTTPFastCenter(qpTTPFastCenter);
        httpServletRequest.setAttribute("fastCenterList", fastCenterList);
        
     // 保险公司
	    List<QpTICCompany> qpTICCompanyList = qpTICCompanyService.findByQpTICCompany(new QpTICCompany());
	    httpServletRequest.setAttribute("qpTICCompanyList", qpTICCompanyList);
        
        umTUser = new UmTUser();
        
        if(sessionUser.getUserCode().equals("admin")){
        	 httpServletRequest.setAttribute("admin", "admin");
        }
        umTUser.setProvince(sessionUser.getProvince());
		umTUser.setCity(sessionUser.getCity());
        
		return SUCCESS;
	}
	
	/**
	 * 增加用户信息
	 * 
	 * @author shenyichan
	 * 2014/03/17
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		try{
			//地市交警02，或查勘员04
			if(("02".equals(umTUser.getUserSort())||"04".equals(umTUser.getUserSort())) && ToolsUtils.notEmpty(umTUser.getSurveyCenterId())){
				qpTTPFastCenterService.saveQpTTPFastCenterCompare(umTUser);
			}
			if("0".equals(umTUser.getCenterId()) || "".equals(umTUser.getCenterId()) ){
				umTUser.setCenterId(null);
			}
			if("0".equals(umTUser.getCoId()) || "".equals(umTUser.getCoId()) ){
				umTUser.setCoId(null);
			}
			if("0".equals(umTUser.getPoliceTeamId()) || "".equals(umTUser.getPoliceTeamId()) ){
				umTUser.setPoliceTeamId(null);
			}
				
		    HttpServletRequest httpServletRequest = this.getRequest();
		    umTUser.setProvince(httpServletRequest.getParameter("umTUserProvince"));
		    umTUser.setCity(httpServletRequest.getParameter("umTUserCity"));
		    umTUser.setDistrict(httpServletRequest.getParameter("umTUserDistrict"));
		    umTUser.setRoad(httpServletRequest.getParameter("umTUserRoad"));
		    umTUser.setStreet(httpServletRequest.getParameter("umTUserStreet"));
			umTUser.setComId(getUserFromSession().getComId());
			//add by liuyatao 2014年7月25日 默认设置用户来源为系统增加
			umTUser.setSourceFlag("01");//系统后台添加
			umTUserService.saveUmTUser(umTUser);
			this.writeString(SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("", e);
			this.writeJSONMsg(e.getMessage());
		}
		return NONE;
		
	}

	/**
	 * 同步用户信息
	 * 
	 * @return
	 */
	public String save() throws Exception {
		try{
			  UmTUserId id1 = new UmTUserId(USERCODE);
			  UmTUser umTUser1 =new UmTUser();
			  umTUser1 = umTUserService.findUmTUserByPK(id1);
			  if(null!=umTUser1){
				umTUser1.setUserName(USERNAME);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if(BIRTHDATE!=null&&!"".equals(BIRTHDATE)){
				Date date = sdf.parse(BIRTHDATE);
				umTUser1.setBirthday(date);
				}
				umTUser1.setComCode(COMCODE);
				umTUser1.setFlag(FLAG);
				umTUser1.setIdentityNumber(IDENTIFYNUMBER);
				umTUser1.setSex(SEX);
				umTUser1.setUserSort(USERSORT);
				if(AGE!=null&&!"".equals(AGE)){
				umTUser1.setAge(new BigDecimal(Integer.parseInt(AGE)));
				}
				umTUser1.setValidStatus("1");
				umTUser1.setUpdaterCode(getUserFromSession().getUserCode());
				umTUserService.updateUmTUser(umTUser1);
			  }  
				  else{
					   UmTUser umTUser2 =new UmTUser();
					    umTUser2.setId(id1);
					    umTUser2.setUserName(USERNAME);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						if(BIRTHDATE!=null&&!"".equals(BIRTHDATE)){
						Date date = sdf.parse(BIRTHDATE);
						umTUser2.setBirthday(date);
						}
						umTUser2.setComCode(COMCODE);
						String comId= SessionUser.getComIdByComCode(COMCODE);
						umTUser2.setComId(comId);
						umTUser2.setEmail(EMAIL);
						umTUser2.setFlag(FLAG);
						umTUser2.setIdentityNumber(IDENTIFYNUMBER);
						umTUser2.setMobile(MOBILE);
						umTUser2.setSex(SEX);
						umTUser2.setUserSort(USERSORT);
						if(USERTYPE!=null&&!"".equals(USERTYPE)){
						umTUser2.setUserType("01");
						}else{
							umTUser2.setUserType("02");
						}
						if(AGE!=null&&!"".equals(AGE)){
						umTUser2.setAge(new BigDecimal(Integer.parseInt(AGE)));
						}
						umTUser2.setValidStatus("1");
						umTUser2.setCreatorCode(getUserFromSession().getUserCode());
						UmTAccountId umTAccountId = new UmTAccountId(USERCODE);
						UmTAccount umTAccount = new UmTAccount();
						umTAccount.setId(umTAccountId);
						umTAccount.setComId(comId);
						String password =DESUtils.encodeSHA("111111");
						umTAccount.setPassword(DESUtils.getEncryptStr(password));
						umTAccount.setValidStatus("1");
						umTAccount.setCreatorCode(getUserFromSession().getUserCode());
					  umTUserService.saveUmTUserAndAccount(umTUser2,umTAccount); 
				  }
				
				this.writeJSONMsg(SUCCESS);
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("", e);
			this.writeJSONMsg(e.getMessage());
		}
		return NONE;
	}



	/**
	 * 查看UmTUser信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		opreateType = "view";
		
		HttpServletRequest httpServletRequest = this.getRequest();
        // 所属交警大队
        QpTTPTeam qpTTPTeam = new QpTTPTeam();
        List<QpTTPTeam> qpTTPTeamList = qpTTPTeamService.findByQpTTPTeam(qpTTPTeam);
        httpServletRequest.setAttribute("qpTTPTeamList", qpTTPTeamList);
        
        // 受理点
        QpTTPFastCenter qpTTPFastCenter = new QpTTPFastCenter();
        qpTTPFastCenter.setValidStatus("1");
        List<QpTTPFastCenter> fastCenterList = qpTTPFastCenterService.findByQpTTPFastCenter(qpTTPFastCenter);
        httpServletRequest.setAttribute("fastCenterList", fastCenterList);
		
     // 保险公司
	    List<QpTICCompany> qpTICCompanyList = qpTICCompanyService.findByQpTICCompany(new QpTICCompany());
	    httpServletRequest.setAttribute("qpTICCompanyList", qpTICCompanyList);
        
        UmTUserId umTUserId = new UmTUserId();
        umTUserId.setUserCode(httpServletRequest.getParameter("usercode"));
		umTUser = umTUserService.findUmTUserByPK(umTUserId);
		
		if(ToolsUtils.notEmpty(umTUser.getUserSort()) && (umTUser.getUserSort().equals("04") || umTUser.getUserSort().equals("02"))){
			List<QpTTPFastCentercompare> fastCentercompareList = qpTTPFastCenterService.findQpTTPFastCenterCompare(umTUser);
			 String centercompareName = "";
		     for(QpTTPFastCentercompare centercompare : fastCentercompareList){
		    	centercompareName += centercompare.getCenterName()+",";
	         }
			 httpServletRequest.setAttribute("centercompareName", centercompareName);
		}
		
		UmTAccountId umTAccountId = new UmTAccountId();
		umTAccountId.setUserCode(httpServletRequest.getParameter("usercode"));
		umTAccount = umTAccountService.findUmTAccountByPK(umTAccountId);
		
		return SUCCESS;
	}
	
	public String resetPassWord() throws Exception {
		return SUCCESS;
	}
	
	/**
	 * 个人信息显示
	 * 
	 * @return
	 */
	public String prepareMyInfo() throws Exception {
		
		if(userCode==null || "".equals(userCode)){
			userCode=getSession().getAttribute("UserCode").toString();
		}
		/*HttpSession session=getSession();
		String gC=((SessionUser) session.getAttribute("SessionUser")).getGroupCodeList().get(0).getGroupCode();
		System.out.println("groupcode"+gC);*/
		try {
			String comId =getUserFromSession().getComId();
			umTUser = umTUserService.findUmTUserByUserCode(userCode);
			String teamCode = "";
			String teamName = "";
			if(umTSauuserService.findUmTSauuserByUsercode(userCode,comId)!=null){
				teamCode = umTSauuserService.findUmTSauuserByUsercode(userCode,comId).getTeamCode();
				if(umTSaugroupService.findUmTSaugroupByTeamCode(teamCode,comId)!=null)
				teamName = umTSaugroupService.findUmTSaugroupByTeamCode(teamCode,comId).getTeamName();
			}
			//JSONArray jsonArray = JSONArray.fromObject(umTUser);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("userCode", userCode);
			jsonObject.put("userName", umTUser.getUserName());
			jsonObject.put("comCode", umTUser.getComCode());
			jsonObject.put("telePhone",umTUser.getTelePhone());
			jsonObject.put("mobile",umTUser.getMobile());
			jsonObject.put("email",umTUser.getEmail());
			jsonObject.put("sex",umTUser.getSex());
			jsonObject.put("identityNumber",umTUser.getIdentityNumber());
			jsonObject.put("interests",umTUser.getInterests());
			jsonObject.put("image",umTUser.getImage());
			jsonObject.put("teamName",teamName);
			renderHtml(jsonObject.toString());
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("", e);
				this.writeAjaxErrorByMap(null);
			}
			return NONE;
	}
	
	/**
	 * 个人信息中更新用户密码
	 * 
	 * @return
	 */
	public String updateUserPassWord() throws Exception {

		userCode = getSession().getAttribute("UserCode").toString();
		String comId = getUserFromSession().getComId();
		JSONObject jsonObject = new JSONObject();
		
		try {
			mobile = umTUserService.findUmTUserByUserCode(userCode).getMobile();
			if(umTSMSValidService.findSMSValidByMobile(mobile).size() != 0){
				UmTSMSValid umTSMSValid = umTSMSValidService.findSMSValidByMobile(mobile).get(0);
				String oldVCode = umTSMSValid.getVerification();
				Date insertTime = umTSMSValid.getInsertTimeForHis();
				Date newTime = new Date();
				long time = (newTime.getTime()-insertTime.getTime())/1000;
				/* 一天超时 */
				logger.info("_________timeout"+_timeout);
				if(time >= _timeout){
					while(umTSMSValidService.findSMSValidByMobile(mobile).size()!=0){
						UmTSMSValidId svid = umTSMSValidService.findSMSValidByMobile(mobile).get(0).getId();
						umTSMSValidService.deleteByPK(svid);
					}
					jsonObject.put("msg", "outtime");
					this.writeEasyUiData(jsonObject);
					return NONE;
				}
				logger.info("verification"+verification);
				if(verification.equals(oldVCode))
				{
					UmTAccountId umTAccountId = new UmTAccountId(userCode);
					UmTAccount umTAccount = umTAccountService.findUmTAccountByPK(umTAccountId);
					String opw = DESUtils.getEncryptStr(oldPassWord);
					String npw = DESUtils.getEncryptStr(newPassWord);
					if(opw.equals(umTAccount.getPassword())&&(!npw.equals(umTAccount.getPassword())))
					{
						umTAccount.setPassword(npw);
						umTAccount.setComId(comId);
						umTAccountService.updateUmTAccount(umTAccount);
					}else{
						jsonObject.put("msg", "oldPassWord fail");
						this.writeEasyUiData(jsonObject);
						return NONE;
					}					
					
					while(umTSMSValidService.findSMSValidByMobile(mobile).size()!=0){
						UmTSMSValidId svid = umTSMSValidService.findSMSValidByMobile(mobile).get(0).getId();
						umTSMSValidService.deleteByPK(svid);
					}
				}else{
					jsonObject.put("msg", "no equals");
					this.writeEasyUiData(jsonObject);
					return NONE;
				}
			}else{
				jsonObject.put("msg", "no mobile");
				this.writeEasyUiData(jsonObject);
				return NONE;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}

	/**
	 * 登陆时重置密码
	 * 
	 * @return
	 */
	public String updatePassWord() throws Exception {

		String comId = "";
		JSONObject jsonObject = new JSONObject();
		
		try {
			
			if(umTUserService.findUmTUserByUserCode(userCode)!=null){
				mobile = umTUserService.findUmTUserByUserCode(userCode).getMobile();
				comId = umTUserService.findUmTUserByUserCode(userCode).getComCode().substring(0, 4).concat("0000");
			if(umTSMSValidService.findSMSValidByMobile(mobile).size() != 0){
				UmTSMSValid umTSMSValid = umTSMSValidService.findSMSValidByMobile(mobile).get(0);
				String oldVCode = umTSMSValid.getVerification();
				Date insertTime = umTSMSValid.getInsertTimeForHis();
				Date newTime = new Date();
				long time = (newTime.getTime()-insertTime.getTime())/1000;
				/* 超时 */
				System.out.println("updatePassWord_________timeout"+_timeout);
				if(time >= _timeout){
					while(umTSMSValidService.findSMSValidByMobile(mobile).size()!=0){
						UmTSMSValidId svid = umTSMSValidService.findSMSValidByMobile(mobile).get(0).getId();
						umTSMSValidService.deleteByPK(svid);
					}
					jsonObject.put("msg", "outtime");
					this.writeEasyUiData(jsonObject);
					return NONE;
				}
				logger.info("verification"+verification);
				if(verification.equals(oldVCode))
				{
					UmTAccountId umTAccountId = new UmTAccountId(userCode);
					UmTAccount umTAccount = umTAccountService.findUmTAccountByPK(umTAccountId);
					//String opw = DESUtils.getEncryptStr(oldPassWord);
					String npw = DESUtils.getEncryptStr(newPassWord);
						umTAccount.setPassword(npw);
						umTAccount.setComId(comId);
						umTAccountService.updateUmTAccount(umTAccount);					
					
					while(umTSMSValidService.findSMSValidByMobile(mobile).size()!=0){
						UmTSMSValidId svid = umTSMSValidService.findSMSValidByMobile(mobile).get(0).getId();
						umTSMSValidService.deleteByPK(svid);
					}					
					
					jsonObject.put("msg", "success");
					this.writeEasyUiData(jsonObject);
					return NONE;					
				}else{
					jsonObject.put("msg", "no equals");
					this.writeEasyUiData(jsonObject);
					return NONE;
				}
			}else{
				jsonObject.put("msg", "no vc mobile");
				this.writeEasyUiData(jsonObject);
				return NONE;
			}
			}else{
				jsonObject.put("msg", "no user");
				this.writeEasyUiData(jsonObject);
				return NONE;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}
	
	/**
	 * 发送验证码
	 * 
	 * @return
	 */
	public String sendSMSvCode()throws Exception {

		userCode = getSession().getAttribute("UserCode").toString();
		String cc = getSession().getAttribute("ComCode").toString();
		String comId = getUserFromSession().getComId();

		String str="0123456789";
		Random random=new Random();
		String sfvCode=new String();
		 for(int i=0;i<6;i++)
		 {
		  int number=random.nextInt(10);//0~61
		  sfvCode+=(str.charAt(number));		  
		}
		 logger.info("---------sf="+sfvCode);
		try {
			UmTSMSValid umTSMSValid = new UmTSMSValid();
			if(mobile==null||"".equals(mobile)){
				mobile=umTUserService.findUmTUserByUserCode(userCode).getMobile();
			}
			logger.info("-------mobile="+mobile);
			//发短信 mobile sfvCode
			//sMSInterfaceSysService.sendSingleMessage(mobile,userCode,cc,comId,ToolsUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"),"M002",sfvCode,"0","1");
			
			umTSMSValid.setMobile(mobile);
			umTSMSValid.setVerification(sfvCode);
			umTSMSValid.setCreatorCode(userCode);
			umTSMSValidService.saveUmTSMSValid(umTSMSValid);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		
		return NONE;
	}

	/**
	 * 登陆时修改密码发送验证码
	 * 
	 * @return
	 */
	public String sendvCode()throws Exception {
		
		String cc = "";
		String comId = "";
		JSONObject jsonObject = new JSONObject();

		//String str="abcdefghigklmnopkrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
		String str="0123456789";
		Random random=new Random();
		String sfvCode=new String();
		 for(int i=0;i<6;i++)
		 {
		  int number=random.nextInt(10);//0~61
		  sfvCode+=(str.charAt(number));		  
		 }
		 logger.info("---------login verication = "+sfvCode);
		try {
			UmTSMSValid umTSMSValid = new UmTSMSValid();
			mobile=umTUserService.findUmTUserByUserCode(userCode).getMobile();
			cc = umTUserService.findUmTUserByUserCode(userCode).getComCode();
			comId = cc.substring(0, 4).concat("0000");
			logger.info("-------mobile = "+mobile);
			
			if(mobile==null||"".equals(mobile)){
				jsonObject.put("msg", "no mobile");
				this.writeEasyUiData(jsonObject);
				return NONE;
			}else{
				//sMSInterfaceSysService.sendSingleMessage(mobile,userCode,cc,comId,ToolsUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"),"M002",sfvCode,"0","1");
				umTSMSValid.setMobile(mobile);
				umTSMSValid.setVerification(sfvCode);
				umTSMSValid.setCreatorCode(userCode);
				umTSMSValidService.saveUmTSMSValid(umTSMSValid);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		
		return NONE;
	}
	
	/**
	 * 个人信息更新用户手机
	 * 
	 * @return
	 */
	public String updateUserMobile() throws Exception {

		userCode = getSession().getAttribute("UserCode").toString();
		String comId = getUserFromSession().getComId();
		//System.out.println("sf="+sfvCode+"verificationCode="+verificationCode);
		
		JSONObject jsonObject = new JSONObject();		
		
		try {
			if(umTSMSValidService.findSMSValidByMobile(mobile).size() != 0){
				UmTSMSValid umTSMSValid = umTSMSValidService.findSMSValidByMobile(mobile).get(0);
				String oldVCode = umTSMSValid.getVerification();
				Date insertTime = umTSMSValid.getInsertTimeForHis();
				Date newTime = new Date();
				long time = (newTime.getTime()-insertTime.getTime())/1000;
				
				if(time>= _timeout){
					while(umTSMSValidService.findSMSValidByMobile(mobile).size()!=0){
						UmTSMSValidId svid = umTSMSValidService.findSMSValidByMobile(mobile).get(0).getId();
						umTSMSValidService.deleteByPK(svid);
					}
					jsonObject.put("msg", "outtime");
					this.writeEasyUiData(jsonObject);
					return NONE;
				}

				if(verificationCode.equals(oldVCode))
				{
					umTUser = umTUserService.findUmTUserByUserCode(userCode);
					umTUser.setMobile(mobile);
					umTUser.setComId(comId);
					umTUserService.updateUmTUser(umTUser);
					while(umTSMSValidService.findSMSValidByMobile(mobile).size()!=0){
						UmTSMSValidId svid = umTSMSValidService.findSMSValidByMobile(mobile).get(0).getId();
						umTSMSValidService.deleteByPK(svid);
					}
				}else{
					jsonObject.put("msg", "no equals");
					this.writeEasyUiData(jsonObject);
					return NONE;
				}
			}else{
				jsonObject.put("msg", "no mobile");
				this.writeEasyUiData(jsonObject);
				return NONE;
			}
			jsonObject.put("msg", "success");
			this.writeEasyUiData(jsonObject);
			return NONE;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}
	
	/**
	 * 个人信息更新用户兴趣爱好
	 * 
	 * @return
	 */
	public String updateUserInterests() throws Exception {
		
		userCode = getSession().getAttribute("UserCode").toString();
		String comId = getUserFromSession().getComId();
		try {
			umTUser = umTUserService.findUmTUserByUserCode(userCode);
			//umTUser.setInterests(new String(interests.getBytes("ISO-8859-1"), "UTF-8"));
			umTUser.setInterests(interests);
			umTUser.setComId(comId);
			umTUserService.updateUmTUser(umTUser);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}
	
	/**
	 * 个人信息更新用户电子邮箱
	 * 
	 * @return
	 */
	public String updateUserEmail() throws Exception {
		
		userCode = getSession().getAttribute("UserCode").toString();
		String comId = getUserFromSession().getComId();
		try {
			umTUser = umTUserService.findUmTUserByUserCode(userCode);
			umTUser.setEmail(EMAIL);
			umTUser.setComId(comId);
			umTUserService.updateUmTUser(umTUser);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}
	
	/**
	 * 个人信息上传用户头像
	 * 
	 * @return
	 */
	public String saveUserImage() throws Exception {
		
		FileOutputStream fos = null;
        FileInputStream fis = null;
		
        HttpSession session=getSession();
		userCode= ((SessionUser) session.getAttribute("SessionUser")).getUserCode();
		String comId = getUserFromSession().getComId();
		//System.out.println("start ++++++");
		
		//String realpath = this.getClass().getClassLoader().getResource("../../pages/uploadfiles").getPath();
				
		//String fileName = new String(imgFileName.getBytes("ISO-8859-1"), "UTF-8");
		try {
			//System.out.println("getServletContext++++++"+ServletActionContext.getServletContext().getResourceAsStream(""));
			//System.out.println("getClassLoader++++++"+this.getClass().getClassLoader().getResource("/").getPath().replaceFirst("/WEB-INF/classes/", "/pages/uploadfiles"));
			//System.out.println("getResource++++++"+ServletActionContext.getServletContext().getResource("/").getPath());
			String realpath = this.getClass().getClassLoader().getResource("/").getPath().replaceFirst("/WEB-INF/classes/", "/pages/uploadfiles");//weblogic success
			//String realpath = ServletActionContext.getServletContext().getRealPath("/pages/uploadfiles"); //tomcat success
			logger.info("个人信息上传用户头像:"+realpath);
			File picFileDir = new File(realpath + File.separator + userCode);//仅创建路径的File对象

			if(!picFileDir.exists()){
				picFileDir.mkdir();//如果路径不存在就先创建路径
			}
			else{
				if(picFileDir.listFiles().length==0){//若目录下没有文件则直接删除  
					picFileDir.delete();  
				}else{//若有则把文件放进数组，并判断是否有下级目录  
					File delFile[]=picFileDir.listFiles();  
					int i =picFileDir.listFiles().length;  
					for(int j=0;j<i;j++){
						delFile[j].delete();//删除文件  
					}
					picFileDir.delete();
				}
				picFileDir.mkdir();
			}
			File picFile = new File(picFileDir,imgFileName);//然后再创建路径和文件的File对象

			//fos = new FileOutputStream(realpath + "\\" + imgFileName);
            fos = new FileOutputStream(picFile);
            // 建立文件上传流
            fis = new FileInputStream(img);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fis.close(); 
            fos.close();
            
			umTUser = umTUserService.findUmTUserByUserCode(userCode);
			//umTUser.setImage(imgFileName);
			umTUser.setImage(userCode + "/" + imgFileName);
			umTUser.setComId(comId);
			umTUserService.updateUmTUser(umTUser);
			
			JSONObject jsonObject = new JSONObject();
			//jsonObject.put("filename", imgFileName);
			jsonObject.put("filename", userCode + "/" + imgFileName);
			jsonObject.put("msg", "success");
			this.writeEasyUiData(jsonObject);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}
	
	public String findUserMcbc() throws Exception {
		String piid=this.getRequest().getParameter("piid");
		umTUser=umTUserService.findUserMcbc(piid);
		return SUCCESS;
	}
	
	public String prepareResetPassword() {
		return "success";
	}
	
	/**
	 * 个人信息中更新用户密码
	 * 
	 * @return
	 */
	public String resetUserPassWord() throws Exception {

		String userCode = getSession().getAttribute("UserCode").toString();
		String comId = getUserFromSession().getComId();
		JSONObject jsonObject = new JSONObject();

		UmTAccountId umTAccountId = new UmTAccountId(userCode);
		UmTAccount umTAccount = umTAccountService.findUmTAccountByPK(umTAccountId);
		String opw = DESUtils.getEncryptStr(oldPassWord);
		String npw = DESUtils.getEncryptStr(newPassWord);
		if(opw.equals(umTAccount.getPassword()) && (!npw.equals(umTAccount.getPassword()))) {
			umTAccount.setPassword(npw);
			umTAccount.setComId(comId);
			umTAccountService.updateUmTAccount(umTAccount);
		}
		else {
			jsonObject.put("msg", "oldPassWord fail");
			this.writeEasyUiData(jsonObject);
			return NONE;
		}
		jsonObject.put("msg", "success");
		this.writeEasyUiData(jsonObject);
		return NONE;
	}
}
