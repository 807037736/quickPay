/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.picc.common.Constants;
import com.picc.common.utils.ToolsUtils;
import com.picc.um.schema.model.UmTAccount;
import com.picc.um.schema.model.UmTAccountId;
import com.picc.um.schema.model.UmTUser;
import com.picc.um.schema.model.UmTUserRelation;
import com.picc.um.schema.model.UmTUserRelationId;
import com.picc.um.schema.vo.SessionUser;
import com.picc.um.service.facade.IUmTAccountService;
import com.picc.um.service.facade.IUmTUserRelationService;
import com.picc.um.service.facade.IUmTUserService;

/** 
* @ClassName: UmTUserRelationAction 
*  
*/

public class UmTUserRelationAction extends Struts2Action{
	public static final String PARAM = "param";			

	/** 操作类型 **/
	private String operateType;
	
	private String param = PARAM;
	
	private IUmTUserService umTUserService;	
	private IUmTAccountService umTAccountService;	
	private IUmTUserRelationService umTUserRelationService;	
	private UmTUserRelation umTUserRelation;
	
	private UmTUserRelationId id;
	private String userCode;
//	private String passWord;
	private String userName;
	private String mobileNo;
	private String platId;
	private String userId;
	private String comCode;
	private String validStatus;
	
	
	private String code;
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public UmTUserRelation getUmTUserRelation() {
		return umTUserRelation;
	}
	public void setUmTUserRelation(UmTUserRelation umTUserRelation) {
		this.umTUserRelation = umTUserRelation;
	}
	public UmTUserRelationId getId() {
		return id;
	}
	public void setId(UmTUserRelationId id) {
		this.id = id;
	}
	public IUmTUserService getUmTUserService() {
		return umTUserService;
	}
	public void setUmTUserService(IUmTUserService umTUserService) {
		this.umTUserService = umTUserService;
	}
	public IUmTUserRelationService getUmTUserRelationService() {
		return umTUserRelationService;
	}
	public void setUmTUserRelationService(
			IUmTUserRelationService umTUserRelationService) {
		this.umTUserRelationService = umTUserRelationService;
	}
	public IUmTAccountService getUmTAccountService() {
		return umTAccountService;
	}
	public void setUmTAccountService(IUmTAccountService umTAccountService) {
		this.umTAccountService = umTAccountService;
	}

	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public String getValidStatus() {
		return validStatus;
	}
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
//	public String getPassWord() {
//		return passWord;
//	}
//	public void setPassWord(String passWord) {
//		this.passWord = passWord;
//	}
	
	public String getComCode() {
		return comCode;
	}
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	public String getPlatId() {
		return platId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPlatId(String platId) {
		this.platId = platId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}


	protected String obtainParam(HttpServletRequest request) {
		return request.getParameter(PARAM);
	}


	/****************************Function Start*******************************/
	
	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 */
	public String prepareQuery() throws Exception {		
		
		return SUCCESS;
	}
	
	/**
	 * UmTUserrelation查询，根据umTUserrelation带过来的值为查询条件进行查询。
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
			Page resultPage = umTUserRelationService.findByUmTUserRelation(umTUserRelation, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新UmTUserrelation信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		operateType = "update";
		umTUserRelation = umTUserRelationService.findUmTUserRelationByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新UmTUserrelation信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		umTUserRelationService.updateUmTUserRelation(umTUserRelation);
		return SUCCESS;
	}


	/**
	 * 准备增加UmTUserrelation信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		operateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增UmTUserrelation信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		umTUserRelationService.saveUmTUserRelation(umTUserRelation);
		return SUCCESS;
	}



	/**
	 * 删除UmTUserrelation信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				umTUserRelationService.deleteByPK(id);
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
	 * 审核UmTUserrelation信息
	 * 
	 * @return
	 */
	public String verify() throws Exception {
		UmTUserRelation userRelation2 = new UmTUserRelation();
		SessionUser user = getUserFromSession();
		String comcode2 = user.getComCode();
		try{
			if(id!=null){
				userRelation2 = umTUserRelationService.findUmTUserRelationByPK(id);
				if(userRelation2!=null){
					userRelation2.setUpdaterCode(user.getUserCode());
					userRelation2.setComCode(comcode2);
					userRelation2.setValidStatus("1");
					umTUserRelationService.updateUmTUserRelation(userRelation2);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("", e);
			Map resultMap = new HashMap();
			resultMap.put("errorTitle", "错误信息(ERROR)：");
			resultMap.put("errorMsg", "更新数据时发生异常！");
			this.writeAjaxErrorByMap(resultMap);
		}
		return NONE;
	}



	/**
	 * 查看UmTUserrelation信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		operateType = "view";
		umTUserRelation = umTUserRelationService.findUmTUserRelationByPK(id);
		return SUCCESS;
	}


	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 */
	public String prepareBinding() throws Exception {	
		HttpServletRequest request = getRequest();
		String param = obtainParam(request);
		String[] params = param.split("-");
		platId = params[0];
		userId = params[1];
		userName = "";
		UmTUserRelationId id = new UmTUserRelationId(ToolsUtils.toStringHex(platId),ToolsUtils.toStringHex(userId));
		UmTUserRelation userR = umTUserRelationService.findUmTUserRelationByPK(id);
		if(userR==null){
			return SUCCESS;
		} else {
//			Map resultMap = new HashMap();
			userCode = userR.getUserCode();
			userName = userR.getUserName();
			mobileNo = userR.getMobileNo();
			comCode = userR.getComCode();
			validStatus = userR.getValidStatus();
			if(!ToolsUtils.isEmpty(comCode)&&comCode.length()>5){
				comCode = Constants.COMPANY_SET.get(comCode.substring(0,6));
			}
			if(ToolsUtils.isEmpty(comCode)){
				comCode = "归属错误";
			}
			if(!ToolsUtils.isEmpty(mobileNo)){
				mobileNo = mobileNo.substring(0, mobileNo.length()<8?mobileNo.length()-1:8)+"***";
			}else{
				mobileNo = "*";
			}
			return SUCCESS;
		}
	}
	/**
	 * 用户绑定
	 * 
	 * @return
	 */
	public String binding() {
		String comName = "";
		if (!ToolsUtils.isEmpty(platId) && !ToolsUtils.isEmpty(userId)) {
			try {
				UmTUser user = umTUserService.findUmTUserByUserCode(userCode);
				List<UmTUserRelation> userRList = umTUserRelationService
						.findUmTUserRelationByUsercode(userCode);
				//未绑定
				if (!ToolsUtils.notEmpty(userRList)) {
					if (user != null && user.getUserName().equals(userName)) {
						platId = ToolsUtils.toStringHex(platId);
						userId = ToolsUtils.toStringHex(userId);
						UmTUserRelationId id = new UmTUserRelationId(platId,userId);
						UmTUserRelation userR = new UmTUserRelation();
						Date date = new Date();
						userR.setId(id);
						userR.setUserName(userName);
						userR.setUserCode(userCode);
						userR.setMobileNo(mobileNo);
						userR.setToolType("weixin");
						userR.setComCode(user.getComCode());
						userR.setInsertTimeForHis(date);
						userR.setValidStatus("1");
						UmTAccount umTAccount = new UmTAccount();
						umTAccount = umTAccountService.findUmTAccountByUserCode(userCode);
						if(umTAccount==null){
							umTAccount = new UmTAccount();
							umTAccount.setId(new UmTAccountId(userCode));
							umTAccount.setComId("44030000");
							umTAccount.setInsertTimeForHis(date);
							umTAccount.setValidStatus("1");
							umTAccountService.saveUmTAccount(umTAccount);
						}
						umTUserRelationService.saveUmTUserRelation(userR);
						if(!ToolsUtils.isEmpty(user.getComCode())&&user.getComCode().length()>5){
							comName = Constants.COMPANY_SET.get(user.getComCode().substring(0,6));
						}
						if(ToolsUtils.isEmpty(comName)){
							comName = "归属错误";
						}
						JSONObject json = new JSONObject();
						json.put("resCode", "0000");
						json.put("resMsg", "恭喜您签到成功！归属："+comName);
						this.writeEasyUiData(json);
						return NONE;
					} else {
						//工号姓名在user表没找到情况
						platId = ToolsUtils.toStringHex(platId);
						userId = ToolsUtils.toStringHex(userId);
						UmTUserRelationId id = new UmTUserRelationId(platId,userId);
						UmTUserRelation userR = new UmTUserRelation();
						Date date = new Date();
						userR.setId(id);
						userR.setUserName(userName);
						userR.setUserCode(userCode);
						userR.setMobileNo(mobileNo);
						userR.setToolType("weixin");
						userR.setComCode("44030000");
						userR.setInsertTimeForHis(date);
						userR.setValidStatus("0");//待审核状态
						UmTAccount umTAccount = new UmTAccount();
						umTAccount = umTAccountService.findUmTAccountByUserCode(userCode);
						if(umTAccount==null){
							umTAccount = new UmTAccount();
							umTAccount.setId(new UmTAccountId(userCode));
							umTAccount.setComId("44030000");
							umTAccount.setInsertTimeForHis(date);
							umTAccount.setValidStatus("0");
							umTAccountService.saveUmTAccount(umTAccount);
						}
						umTUserRelationService.saveUmTUserRelation(userR);
						JSONObject json = new JSONObject();
						json.put("resCode", "0001");
						json.put("resMsg", "工号或姓名不对应，待审核状态，请联系在线客服确认。");
						this.writeEasyUiData(json);
						return NONE;
					}

				} else {
					JSONObject json = new JSONObject();
					json.put("resCode", "0002");
					json.put("resMsg", "该工号已经在其他微信号签到，请咨询在线客服。");
					this.writeEasyUiData(json);
					return NONE;
				}
			} catch (Exception e) {
				logger.error("", e);
				JSONObject json = new JSONObject();
				json.put("resCode", "0003");
				json.put("resMsg", "查找用户失败，请返回重试。");
				this.writeEasyUiData(json);
				return NONE;
			}
		} else {
			JSONObject json = new JSONObject();
			json.put("resCode", "0004");
			json.put("resMsg", "获取微信号出错，请返回重试。");
			this.writeEasyUiData(json);
			return NONE;
		}
	}
	
	/**
	 * 内部公众号员工绑定
	 * @author yuwenhui   2014-08-28
	 */
	public String innerBinding() throws Exception{
		Map map=umTUserRelationService.validInnerUser(umTUserRelation);
		this.writeJson(map);
		return NONE;
	}
}
