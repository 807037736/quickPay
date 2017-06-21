/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2014
 */

package com.picc.um.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.picc.common.utils.ToolsUtils;
import com.picc.um.schema.model.UmTUserBind;
import com.picc.um.schema.model.UmTUserBindId;
import com.picc.um.schema.vo.SessionUser;
import com.picc.um.schema.vo.UmTUserBindVo;
import com.picc.um.service.facade.IUmTUserBindService;


public class UmTUserBindAction extends Struts2Action{
	private IUmTUserBindService umTUserBindService;	
	public void setUmTUserBindService(IUmTUserBindService umTUserBindService) {
		this.umTUserBindService = umTUserBindService;
	}

	public IUmTUserBindService getUmTUserBindService() {
		return umTUserBindService;
	}

	private UmTUserBind umTUserBind;
	
	private UmTUserBindId id;
	
	/** 操作类型 **/
	private String operateType;
	
	/** UmTUserBind getter/setter **/
	public UmTUserBind getUmTUserBind() {
		return this.umTUserBind;
	}
	
	public void setUmTUserBind(UmTUserBind umTUserBind) {
		this.umTUserBind = umTUserBind;
	}
	/** UmTUserBindId getter/setter **/
	public UmTUserBindId getId() {
		return this.id;
	}
	
	public void setId(UmTUserBindId id) {
		this.id = id;
	}
	/** operateType getter/setter **/
	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	
	/** 主键对象 */
	private String userCode;
	private String param;
	private String policynoList;
	private String registno;
	
	/****************************Function Start*******************************/
	public String getUserCode() {
		return this.userCode;
	}
	
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	public String getPolicynoList() {
		return policynoList;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public void setPolicynoList(String policynoList) {
		this.policynoList = policynoList;
	}

	public String getRegistno() {
		return registno;
	}

	public void setRegistno(String registno) {
		this.registno = registno;
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
	 * UmTUserBind查询，根据umTUserBind带过来的值为查询条件进行查询。
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
			Page resultPage = umTUserBindService.findByUmTUserBind(umTUserBind, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新UmTUserBind信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		operateType = "update";
		umTUserBind = umTUserBindService.findUmTUserBindByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新UmTUserBind信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		umTUserBindService.updateUmTUserBind(umTUserBind);
		return SUCCESS;
	}


	/**
	 * 准备增加UmTUserBind信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		operateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增UmTUserBind信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		umTUserBindService.saveUmTUserBind(umTUserBind);
		return SUCCESS;
	}



	/**
	 * 删除UmTUserBind信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				umTUserBindService.deleteByPK(id);
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
	 * 查看UmTUserBind信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		operateType = "view";
		umTUserBind = umTUserBindService.findUmTUserBindByPK(id);
		return SUCCESS;
	}
	
	
	public String userBind(){
		SessionUser sessionUser = getUserFromSession();
		Map paramMap = new HashMap();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		paramMap.put("umTUserBind", umTUserBind);
		paramMap.put("sessionUser", sessionUser);
		try {
			UmTUserBind umTUserBind = umTUserBindService.fdBindCust(paramMap);
			resultMap.put("umTUserBind", umTUserBind);
			JSONObject json = JSONObject.fromObject(resultMap);
			this.writeEasyUiData(json);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}
		return NONE;
	}
	/**
	 * 查询保单详细信息
	 * @date 2014年12月4日
	 * @user juzongyi
	 * @return
	 */
	public String queryPolicy(){
		String platid = "";
		String openid = "";
		String resultJsonStr = "";
		if(ToolsUtils.notEmpty(param)){
			String[] params = param.split("-");
			platid = params[0];
			openid = params[1];
			try {
				List<UmTUserBindVo> userList = umTUserBindService.findCustIdByOpenid(ToolsUtils.toStringHex(platid), ToolsUtils.toStringHex(openid));
				
				if(!ToolsUtils.isEmpty(userList)&& userList.size()>0){
					String custId = userList.get(0).getCustId();
					if(!ToolsUtils.isEmpty(custId)){
						String jsonString = "{\"head\":{\"user\":\"picc\",\"password\":\"111\"},\"body\":{\"custId\":\""+custId+"\",\"comId\":\"44030000\"}}";
						HashMap ruleParamMap = new HashMap();
						ruleParamMap.put("comcode", "44030000");
						ruleParamMap.put("interfaceName", "khyx_customerPolicyInfo");
						//resultJsonStr = interfaceService.callKHYXCOMMONInterface(ruleParamMap, jsonString);
					}
				}else{
					Map<String,Object> resultMap = new HashMap<String,Object>();
					resultMap.put("errcode", "0001");
					JSONObject json = JSONObject.fromObject(resultMap);
					resultJsonStr = json.toString();
				}
				this.writeString(resultJsonStr);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("", e);
			}
		}
		return NONE;
	}
	/**
	 * 查询理赔列表信息
	 * @date 2014年12月4日
	 * @user juzongyi
	 * @return
	 */
	public String queryClaimList(){
		String platid = "";
		String openid = "";
		String resultJsonStr = "";
		String username = "";
		if(ToolsUtils.notEmpty(param)){
			String[] params = param.split("-");
			platid = params[0];
			openid = params[1];
			try {
				platid = ToolsUtils.toStringHex(platid);
				openid = ToolsUtils.toStringHex(openid);
				String key = platid + openid + "picc8";
				List<UmTUserBindVo> userList = umTUserBindService.findCustIdByOpenid(platid,openid);
				
				if(!ToolsUtils.isEmpty(userList)){
					String custId = userList.get(0).getCustId();
					username = userList.get(0).getUserName();
					StringBuffer policyListStr = new StringBuffer();
					if(!ToolsUtils.isEmpty(custId)){
						if(!ToolsUtils.isEmpty(policynoList)&&policynoList.length()>10){
							String[] policyArr = policynoList.split(",");
							for(int i = 0;i<policyArr.length;i++){
								if(policyArr[i].length()==22){
									if(i==policyArr.length-1){
										policyListStr.append("'").append(policyArr[i]).append("'");
									}else{
										policyListStr.append("'").append(policyArr[i]).append("',");
									}
								}
								
							}
						}
					}
				}else{
					Map<String,Object> resultMap = new HashMap<String,Object>();
					resultMap.put("errcode", "0001");
					JSONObject json = JSONObject.fromObject(resultMap);
					resultJsonStr = json.toString();
					this.writeString(resultJsonStr);
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("", e);
			}
		}
		return NONE;
	}
}
