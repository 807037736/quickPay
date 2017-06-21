/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2014
 */

package com.picc.um.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.picc.common.utils.ToolsUtils;
import com.picc.um.schema.model.UmTAccount;
import com.picc.um.schema.model.UmTRegistuser;
import com.picc.um.schema.model.UmTRegistuserId;
import com.picc.um.schema.model.UmTUserBind;
import com.picc.um.schema.model.UmTUserRelation;
import com.picc.um.schema.vo.UmTUserBindVo;
import com.picc.um.security.DESUtils;
import com.picc.um.service.facade.IUmTAccountService;
import com.picc.um.service.facade.IUmTRegistuserService;
import com.picc.um.service.facade.IUmTUserBindService;
import com.picc.um.service.facade.IUmTUserRelationService;

public class UmTRegistuserAction extends Struts2Action{
	private String param1;
	private String licenseno;
	private String identifyno;
	private String carid;
	private String username;
	private String usercode;
	private String errorMsg;
	private String postAddress;
	private IUmTRegistuserService umTRegistuserService;	
	private IUmTAccountService umTAccountService;
	private IUmTUserBindService umTUserBindService;	
	private IUmTUserRelationService umTUserRelationService;	
	
	
	public void setUmTUserBindService(IUmTUserBindService umTUserBindService) {
		this.umTUserBindService = umTUserBindService;
	}

	public IUmTUserBindService getUmTUserBindService() {
		return umTUserBindService;
	}
	public void setUmTRegistuserService(IUmTRegistuserService umTRegistuserService) {
		this.umTRegistuserService = umTRegistuserService;
	}

	public IUmTUserRelationService getUmTUserRelationService() {
		return umTUserRelationService;
	}

	public void setUmTUserRelationService(
			IUmTUserRelationService umTUserRelationService) {
		this.umTUserRelationService = umTUserRelationService;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public IUmTRegistuserService getUmTRegistuserService() {
		return umTRegistuserService;
	}
	
	public IUmTAccountService getUmTAccountService() {
		return umTAccountService;
	}

	public void setUmTAccountService(IUmTAccountService umTAccountService) {
		this.umTAccountService = umTAccountService;
	}

	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private UmTRegistuser umTRegistuser;
	private UmTUserBindVo umTUserBindVo;
	
	private UmTRegistuserId id;
	
	private String loginuser;
	private String password;
	private String mobileno;
	
	/** 操作类型 **/
	private String operateType;
	/** UmTRegistuser getter/setter **/
	public UmTRegistuser getUmTRegistuser() {
		return this.umTRegistuser;
	}
	
	public void setUmTRegistuser(UmTRegistuser umTRegistuser) {
		this.umTRegistuser = umTRegistuser;
	}
	/** UmTRegistuserId getter/setter **/
	public UmTRegistuserId getId() {
		return this.id;
	}
	
	public UmTUserBindVo getUmTUserBindVo() {
		return umTUserBindVo;
	}

	public void setUmTUserBindVo(UmTUserBindVo umTUserBindVo) {
		this.umTUserBindVo = umTUserBindVo;
	}

	public void setId(UmTRegistuserId id) {
		this.id = id;
	}
	public String getLoginuser() {
		return loginuser;
	}

	public void setLoginuser(String loginuser) {
		this.loginuser = loginuser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/** operateType getter/setter **/
	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	
	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getLicenseno() {
		return licenseno;
	}

	public void setLicenseno(String licenseno) {
		this.licenseno = licenseno;
	}

	public String getCarid() {
		return carid;
	}

	public void setCarid(String carid) {
		this.carid = carid;
	}

	public String getIdentifyno() {
		return identifyno;
	}

	public void setIdentifyno(String identifyno) {
		this.identifyno = identifyno;
	}

	/** 主键对象 */
	private String userCode;
	public String getUserCode() {
		return this.userCode;
	}
	
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}


	
	/****************************Function Start*******************************/
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getPostAddress() {
		return postAddress;
	}

	public void setPostAddress(String postAddress) {
		this.postAddress = postAddress;
	}

	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 */
	public String prepareQuery() throws Exception {		
		
		return SUCCESS;
	}
	
	/**
	 * UmTRegistuser查询，根据umTRegistuser带过来的值为查询条件进行查询。
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
			Page resultPage = umTRegistuserService.findByUmTRegistuser(umTRegistuser, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新UmTRegistuser信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		operateType = "update";
		umTRegistuser = umTRegistuserService.findUmTRegistuserByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新UmTRegistuser信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		umTRegistuserService.updateUmTRegistuser(umTRegistuser);
		return SUCCESS;
	}


	/**
	 * 准备增加UmTRegistuser信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		operateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增UmTRegistuser信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
			try {
				usercode = umTRegistuserService.registNewUser(umTRegistuser,param1,identifyno,licenseno,postAddress);
				username=umTRegistuser.getUserName();
				mobileno = umTRegistuser.getMobile();
				this.getRequest().setAttribute("title", "用户注册");
				this.getRequest().setAttribute("butMsg", "恭喜您,注册成功！");
			}catch(Exception e) {
				logger.error("", e);
				e.printStackTrace();	
			}
	
		return SUCCESS;
	}
	
	
	
	
	/**
	 * 修改UmTRegistuser信息
	 * 
	 * @return
	 */
	public String modify()  {
		
		try {
			usercode = "";
			username = "";
			String param1 = ""; 
		}catch(Exception e) {
			logger.debug(e.getMessage());
			e.printStackTrace();	
		}
		return SUCCESS;
	}
	

	/**
	 * 个人中心页面绑定
	 * 
	 * @return
	 */
	public String preBangding()  {
		String platId = "";
		String openId = "";
		if(ToolsUtils.notEmpty(param1)){
			String[] params = param1.split("-");
			platId = params[0];
			openId = params[1];
		}
		 List<UmTRegistuser> list = null;
		try {
			list = umTRegistuserService.findCustNameByOpenid(ToolsUtils.toStringHex(platId), ToolsUtils.toStringHex(openId));
			if(list!=null&&list.size()>0){
				umTRegistuser = list.get(0);
			}else{
				return ERROR;
			}
		}catch(Exception e) {
			e.printStackTrace();	
			logger.error("", e);
		}
		return SUCCESS;
	}
	
	/**
	 * 个人中心页面修改
	 * 
	 * @return
	 */
	public String preEdit()  {
		String platId = "";
		String openId = "";
		String param = this.getRequest().getParameter("param");
		param1 = param;
		if(ToolsUtils.notEmpty(param1)){
			String[] params = param1.split("-");
			platId = params[0];
			openId = params[1];
		}
		List<UmTUserBindVo> userList = new ArrayList<UmTUserBindVo>();
		try {
			if(ToolsUtils.notEmpty(platId)&&ToolsUtils.notEmpty(openId)){
				userList = umTUserBindService.findRegistUserByOpenid(ToolsUtils.toStringHex(platId), ToolsUtils.toStringHex(openId));
			}
			if(userList!=null&&userList.size()>0){
				umTUserBindVo = userList.get(0);
			}else{
				return ERROR;
			}
		}catch(Exception e) {
			e.printStackTrace();	
			logger.error("", e);
		}
		return SUCCESS;
	}
	/**
	 * 保存绑定信息
	 * 手机号暂时不提供修改
	 * @return
	 */
	public String edit()  {
		
//		Map paramMap = new HashMap();
//		Map<String,Object> resultMap = new HashMap<String,Object>();
//		UmTUserBind umTUserBind = new UmTUserBind();
//		umTUserBind.setCustomerName(username);
//		umTUserBind.setBindValue(identifyno);
//		umTUserBind.setUserCode(usercode);
//		paramMap.put("umTUserBind", umTUserBind);
//		paramMap.put("mobileno", mobileno);
//		paramMap.put("licenseno", licenseno);
//		paramMap.put("param", param1);
		String platId = "";
		String openId = "";
		if(ToolsUtils.notEmpty(param1)){
			String[] params = param1.split("-");
			platId = params[0];
			openId = params[1];
		}
		UmTUserRelation userRelation = null;
		UmTRegistuser userRegist = null;
		UmTUserBind userBind = null;
		//是否需要重新交互客户识别接口
		boolean update = false;
		try {
			userRelation = umTUserRelationService.findUmTUserRelationByUserId(platId,openId);
			
			//修改姓名，更新表
			if(userRelation.getUserName()!=null){
				if(!userRelation.getUserName().equals(username)){
					userRegist = umTRegistuserService.findUmTRegistuserByPK(new UmTRegistuserId(userRelation.getUserCode()));
					userRelation.setUserName(username);
					userRegist.setUserName(username);
					umTUserRelationService.updateUmTUserRelation(userRelation);
					umTRegistuserService.updateUmTRegistuser(userRegist);
					update = true;
				}
			}else{
				if(!username.equals("")){
					userRegist = umTRegistuserService.findUmTRegistuserByPK(new UmTRegistuserId(userRelation.getUserCode()));
					userRelation.setUserName(username);
					userRegist.setUserName(username);
					umTUserRelationService.updateUmTUserRelation(userRelation);
					umTRegistuserService.updateUmTRegistuser(userRegist);
					update = true;
				}
			}
			userBind = umTUserBindService.findListByUserCode(userRelation.getUserCode());
			//之前未绑定
			if(userBind==null){
				userBind = new UmTUserBind();
				userBind.setUserCode(usercode);
				userBind.setBindType("身份证");
				userBind.setBindValue(identifyno);
				userBind.setCustomerName(username);
				userBind.setLicenseNo(licenseno);
//				if(ToolsUtils.notEmpty(identifyno)&&ToolsUtils.notEmpty(username)){
//					update = true;
//				}
			}else{//之前有绑定信息
				userBind.setBindValue(identifyno);
				userBind.setCustomerName(username);
				userBind.setLicenseNo(licenseno);
/*				if(!username.equals("")&&!identifyno.equals("")&&!userBind.getBindValue().equals(identifyno)){
					update = true;
					userBind.setBindValue(identifyno);
				}
				if(!identifyno.equals("")&&!username.equals("")&&!userBind.getCustomerName().equals(username)){
					update = true;
					userBind.setCustomerName(username);
				}
				if(!userBind.getLicenseNo().equals(licenseno)){
					userBind.setLicenseNo(licenseno);
				}*/
			}
//			umTUserBindService.savBindCust(userRelation,userBind);
			errorMsg = "success";
		} catch (Exception e) {
			errorMsg = "完善信息出现错误，请联系客服！";
			e.printStackTrace();
			logger.error("", e);
		}
		return SUCCESS;
	}
	/**
	 * 绑定UmTRegistuser信息
	 * 
	 * @return
	 */
	public String bangding()  {
		
//		SessionUser sessionUser = getUserFromSession();
		Map paramMap = new HashMap();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		UmTUserBind umTUserBind = new UmTUserBind();
		umTUserBind.setCustomerName(username);
		umTUserBind.setBindValue(identifyno);
		umTUserBind.setUserCode(usercode);
		umTUserBind.setLicenseNo(licenseno);
		paramMap.put("umTUserBind", umTUserBind);
		paramMap.put("mobileno", mobileno);
		try {
			umTUserBind = umTUserBindService.fdBindCust(paramMap);
			errorMsg = "success";
		} catch (Exception e) {
			errorMsg = "完善信息出现错误，请联系客服！";
			e.printStackTrace();
			logger.error("", e);
		}
		return SUCCESS;
	}
	/**
	 * 判断手机是否已经注册
	 * @return
	 */
	public String checkMobile() {
		
		try {
			Map<String, String> resutlMap = umTRegistuserService.checkMobileno(mobileno);
			this.writeJson(resutlMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}
		return NONE;
	}

	/**
	 * 删除UmTRegistuser信息
	 * 
	 * @return
	 */
	public String delete()  {
		try{
			if(id!=null){
				umTRegistuserService.deleteByPK(id);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("", e);
			Map resultMap = new HashMap();
			resultMap.put("errorTitle", "错误信息(ERROR)：");
			resultMap.put("errorMsg", "删除数据时发生异常！");
			this.writeAjaxErrorByMap(resultMap);
		}
		return NONE;
	}
	
	/**
	 * 删除UmTRegistuser信息
	 * 
	 * @return
	 */
	public String delBangding() {
		String platId = "";
		String openId = "";
		if(ToolsUtils.notEmpty(param1)){
			String[] params = param1.split("-");
			platId = params[0];
			openId = params[1];
		}
		try{
			if(ToolsUtils.notEmpty(platId)&&ToolsUtils.notEmpty(openId)){
				 umTUserBindService.deleteByParam(ToolsUtils.toStringHex(platId), ToolsUtils.toStringHex(openId));
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("", e);
			Map resultMap = new HashMap();
			resultMap.put("errorTitle", "错误信息(ERROR)：");
			resultMap.put("errorMsg", "删除数据时发生异常！");
			this.writeAjaxErrorByMap(resultMap);
		}
		return NONE;
	}



	/**
	 * 查看UmTRegistuser信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		operateType = "view";
		umTRegistuser = umTRegistuserService.findUmTRegistuserByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 查看UmTRegistuser信息方法
	 * 
	 * @return
	 */
	public String personCenter() throws Exception {
		String param = this.getRequest().getParameter("param");
		//param = "piccwx-RU597422597";
		param1 = param;
		String platId = "";
		String openId = "";
		if(ToolsUtils.notEmpty(param)){
			String[] params = param.split("-");
			platId = params[0];
			openId = params[1];
		}
		List<UmTUserBindVo> userList = new ArrayList<UmTUserBindVo>();
		if(ToolsUtils.notEmpty(platId)&&ToolsUtils.notEmpty(openId)){
			//userList = umTUserBindService.findCustIdByOpenid(ToolsUtils.toStringHex(platId), ToolsUtils.toStringHex(openId));
			System.out.println("--------------------platId:"+ToolsUtils.toStringHex(platId)+"----\topenId:"+ToolsUtils.toStringHex(openId));
			userList = umTUserBindService.findRegistUserByOpenid(ToolsUtils.toStringHex(platId), ToolsUtils.toStringHex(openId));
		}
		if (!ToolsUtils.isEmpty(userList)) {
			umTUserBindVo = userList.get(0);
			
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("openId", openId);
			
		}else{
			return "zhuce";
		}
		return SUCCESS;
	}
	
	

	/**
	 * 登陆
	 * 
	 * @return
	 */
	public String login() throws Exception {
		
		UmTAccount account = null;
		String passw = null;
		String msg = "";
		
		if(ToolsUtils.isEmpty(loginuser)||ToolsUtils.isEmpty(password)) {
			msg = "no user or no password";
		}
		
		try {
			
			account = umTAccountService.findUmTAccountByUserCode(loginuser);
			if(account != null)
				passw = account.getPassword();
			
			if(account != null && passw != null && passw.equals(DESUtils.getEncryptStr(password))) {
				msg = "success";
				
			} else if(account == null) {
				msg = "user error";
			} else {
				msg = "password error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			
		}	
		this.writeJSONMsg(msg);
		logger.debug(ToolsUtils.convertDateToString(new Date(), "yyyy-MM-dd HH:mm:ss")+ "  login: " + msg);
		return NONE;
	}
	public static void main(String[] args) {
		System.out.println(ToolsUtils.toHexString("ovoSZjtWUI0vyv-XByTLbBtJTK5c"));
	}
	
	/**
	 * 采集页面
	 * @return
	 * @throws Exception
	 */
	public String hpvReportView() throws Exception {
		
		return SUCCESS;
	}
	
	/**
	 * 采集
	 * @return
	 * @throws Exception
	 */
	public String hpvReportDeal() throws Exception {
		
		return SUCCESS;
	}
	

	public String check() throws Exception{
		List<UmTRegistuser> umList = umTRegistuserService.check(licenseno, mobileno);
		PrintWriter printWriter=this.getResponse().getWriter();
		if(umList!=null && umList.size()>0){
			printWriter.write("Y");
			//this.writeJSONMsg("车牌号与手机号已同时注册！");
		}
		return NONE;
	}
	
}
