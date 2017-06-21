/*
 * Powered By limingguo
 * Email: limingguo03@chongq.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.picc.um.schema.model.UmTUserGroup;
import com.picc.um.schema.model.UmTUserGroupId;
import com.picc.um.schema.vo.SessionUser;
import com.picc.um.service.facade.IUmTUserGroupService;
import com.picc.um.service.facade.IUmTUserService;

/**
 * 用户自定义分组处理Action层
 * @author 李明果
 */
@SuppressWarnings("serial")
public class UmTUserGroupAction extends Struts2Action{
	
	private IUmTUserService umTUserService;
	
	public IUmTUserService getUmTUserService() {
		return umTUserService;
	}

	public void setUmTUserService(IUmTUserService umTUserService) {
		this.umTUserService = umTUserService;
	}

	private IUmTUserGroupService umTUserGroupService;	
	public void setUmTUserGroupService(IUmTUserGroupService umTUserGroupService) {
		this.umTUserGroupService = umTUserGroupService;
	}

	public IUmTUserGroupService getUmTUserGroupService() {
		return umTUserGroupService;
	}
	
	private UmTUserGroup umTUserGroup;
	
	private UmTUserGroupId id;
	
	/** 操作类型 **/
	private String operateType;
	/** UmTUserGroup getter/setter **/
	public UmTUserGroup getUmTUserGroup() {
		return this.umTUserGroup;
	}
	
	public void setUmTUserGroup(UmTUserGroup umTUserGroup) {
		this.umTUserGroup = umTUserGroup;
	}
	/** UmTUserGroupId getter/setter **/
	public UmTUserGroupId getId() {
		return this.id;
	}
	
	public void setId(UmTUserGroupId id) {
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
	private String userGroupId;
	public String getUserGroupId() {
		return this.userGroupId;
	}
	
	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}
	
	private String comCode;
	
	private String isLeader;
	
	public String getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(String isLeader) {
		this.isLeader = isLeader;
	}

	private String userValue;
	
	private String flag_ValueType;
	
	public String getUserValue() {
		return userValue;
	}

	public void setUserValue(String userValue) {
		this.userValue = userValue;
	}

	public String getFlag_ValueType() {
		return flag_ValueType;
	}

	public void setFlag_ValueType(String flag_ValueType) {
		this.flag_ValueType = flag_ValueType;
	}	
	

	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	private String groupId;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	public static boolean isNumeric(String str){  
		    Pattern pattern = Pattern.compile("[0-9]*");  
		    return pattern.matcher(str).matches();     
		} 


	/****************************Function Start*******************************/
	
	/**@author limingguo 2013-8-22
	 * UmTUserGroup查询，查询返回机构下属人员按钮。
	 * 
	 * @return
	 */
	public String queryUserPageByComCode() throws Exception {
		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 20;
		}
		String userName ="%";
		String userCode ="%";
		
		if(userValue == null||"".equals(userValue)){
			userValue = "%";
		}else{
			//userName =new String(userName.getBytes("ISO-8859-1"), "UTF-8");
			if((userValue.indexOf("*")==-1)&&(userValue.indexOf("%")==-1)){				
				userValue = "%"+userValue+"%";
			}else if(userValue.indexOf("*")!=-1){
				userValue = userValue.replace('*', '%');
				//System.out.println("*******"+userName.indexOf("*")+"***"+userName);
			}				
		}
		
		if("1".equals(flag_ValueType)){
			userName=userValue;
		}
		if("2".equals(flag_ValueType)){
			userCode=userValue;
		}

		try {			
			Page resultPage = umTUserGroupService.findUserPageByComCode(page, rows, comCode, userName, userCode);
			//Page resultPage = umTUserService.findUserPageByComCode(comCode, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}
	
	/**@author limingguo 2013-8-22
	 * UmTUserGroup查询，查询返回机构下属人员。
	 * 
	 * @return
	 */
	public String queryUserByCom() throws Exception {
		/*减少循环*/
		try {
			Page resultPage = umTUserGroupService.findUmTUserByComCode(comCode);
			//System.out.println("queryUserByCom resultPage.getResult().size()="+resultPage.getResult().size());
			JSONArray jsonArr = JSONArray.fromObject(resultPage.getResult());
			renderHtml(jsonArr.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}
	
	/**@author limingguo 2013-8-22
	 * UmTUserGroup查询，查询返回机构下属人员。
	 * 
	 * @return
	 */
	public String query4sUserByComCode() throws Exception {
		/*减少循环*/
		try {
			comCode = comCode.substring(0,6).concat("%");
			Page resultPage = umTUserGroupService.findUmTUserByComCode(comCode);
			//System.out.println("queryUserByCom resultPage.getResult().size()="+resultPage.getResult().size());
			JSONArray jsonArr = JSONArray.fromObject(resultPage.getResult());
			renderHtml(jsonArr.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}
	
	/**
	 * UmTUserGroup查询，根据umTUserGroup带过来的值为查询条件进行查询。
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
			Page resultPage = umTUserGroupService.findByUmTUserGroup(umTUserGroup, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}

	/**@author limingguo 2013-8-22
	 * 某流程角色关联的人 管理人员车行和流程角色
	 * @param comCode groupId
	 * @return 
	 */
	public String queryByGroupIdAndCom() throws Exception {
		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 20;
		}
		try {			

			Page resultPage = umTUserGroupService.findUserGroupByGroupIdCom(page, rows, groupId, comCode);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			//this.writeJSONMsg(e.getMessage());
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}

	/**@author limingguo 2013-8-22
	 * 某车行关联的人
	 * @param comCode groupId
	 * @return 
	 */
	public String query4sByGroupIdAndComCode() throws Exception {
		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 20;
		}
		try {	
			//comCode = comCode.substring(0,6).concat("%");
			comCode ="%";

			Page resultPage = umTUserGroupService.findUserGroupByGroupIdCom(page, rows, groupId, comCode);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			//this.writeJSONMsg(e.getMessage());
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}

	/**@author limingguo 2013-10-22
	 * 查询人员关联的车行
	 * @param comId userCode
	 * @return 
	 */
	public String query4sByUserCode() throws Exception {
		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 20;
		}
		String comId = getUserFromSession().getComId();
		try {			
			logger.info("查询人员关联的车行!"+umTUserGroup.getUserCode());
			Page resultPage = umTUserGroupService.find4sByUserCode(page, rows, umTUserGroup.getUserCode(), comId);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			//this.writeJSONMsg(e.getMessage());
			this.writeAjaxErrorByMap(null);
		}
		
		return NONE;
	}
	
	/**
	 * 更新UmTUserGroup信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		
		HttpSession session=getSession();
		String userCode= ((SessionUser) session.getAttribute("SessionUser")).getUserCode();
		String comId = getUserFromSession().getComId();
		umTUserGroup.setUpdaterCode(userCode);
		umTUserGroup.setComId(comId);
		
		umTUserGroupService.updateUmTUserGroup(umTUserGroup);
		return SUCCESS;
	}
	
	/**
	 * 更新UmTUserGroup 设置车行团队经理
	 * 
	 * @return
	 */
	
	public String updateLeader() throws Exception { 
		

		HttpSession session=getSession();
		String userCode=((SessionUser) session.getAttribute("SessionUser")).getUserCode();

		try {
			umTUserGroup = umTUserGroupService.findUmTUserGroupByPK(id);
			umTUserGroup.setUpdaterCode(userCode);
			umTUserGroup.setIsleader(isLeader);
			umTUserGroupService.updateUmTUserGroup(umTUserGroup);
			
			message = "success";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", message); 
			this.writeEasyUiData(jsonObject);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.writeJSONMsg(e.getMessage());
		}
		return NONE;
	}
	
	/**
	 * 新增UmTUserGroup信息
	 * 
	 * @return
	 */
	/*public String add() throws Exception {
		
		
		HttpSession session=getSession();
		String userCode=(String) session.getAttribute("UserCode");
		umTUserGroup.setCreatorCode(userCode);		
		
		try {
			umTUserGroupService.saveUmTUserGroup(umTUserGroup);
			message = "success";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", message); 
			this.writeEasyUiData(jsonObject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}*/
	
	/**@author limingguo 2013-8-22
	 * 保存人员关联有权限的流程角色和车行
	 * 
	 * @return
	 */
	public String addUserGroupIdList() throws Exception {
		
		HttpSession session=getSession();
		String userCode= ((SessionUser) session.getAttribute("SessionUser")).getUserCode();
		String comId = getUserFromSession().getComId();
		
		List<UmTUserGroup> userGroupList = new ArrayList<UmTUserGroup>();
		String ugi[]=umTUserGroup.getGroupId().split(", ");
		
		for(int i=0;i<ugi.length;i++){

			UmTUserGroup ug = new UmTUserGroup();
			/*过滤字符串*/
			if(ugi[i].length()==30){
				ug.setGroupId(ugi[i]);
			}else{
				continue;
			}
			ug.setCreatorCode(userCode);
			ug.setComId(comId);
			ug.setUserCode(umTUserGroup.getUserCode());
			ug.setValidStatus(umTUserGroup.getValidStatus());
			userGroupList.add(ug);		
		}
        //剔除重复项		
		for(int x=0;x<umTUserGroupService.findByUmTGroupId(umTUserGroup).size();x++){

			for(int y=0;y<userGroupList.size();y++){
				if(umTUserGroupService.findByUmTGroupId(umTUserGroup).get(x).getGroupId().equals(userGroupList.get(y).getGroupId())){
					userGroupList.remove(y);
					break;
				}
			}
			if(userGroupList.size()==0) break;

		}
		
		try {
			
			umTUserGroupService.saveUmTUserGroupList(userGroupList);
			
			message = "success";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", message); 
			this.writeEasyUiData(jsonObject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
		
	}
	
	/**
	 * 批量保存UmTUserGroup关联信息
	 * 
	 * @return
	 */	
	public String addUserGroupList() throws Exception {
		

		HttpSession session=getSession();
		String userCode= ((SessionUser) session.getAttribute("SessionUser")).getUserCode();
		String comId = getUserFromSession().getComId();
		
		
		List<UmTUserGroup> userGroupList = new ArrayList<UmTUserGroup>();
		String ucs[]=umTUserGroup.getUserCode().split(", ");

		for(int i=0;i<ucs.length;i++){

				UmTUserGroup ug = new UmTUserGroup();
				
				if(isNumeric(ucs[i])&&ucs[i].length()>=8&&ucs[i].length()<=10){
					ug.setUserCode(ucs[i]);
				}else{
					continue;
				}
				ug.setCreatorCode(userCode);
				ug.setComId(comId);
				ug.setGroupId(umTUserGroup.getGroupId());
				ug.setValidStatus(umTUserGroup.getValidStatus());
				userGroupList.add(ug);
			
		}
		
		for(int x=0;x<umTUserGroupService.findByUmTUserCode(umTUserGroup).size();x++){

			for(int y=0;y<userGroupList.size();y++){
				if(umTUserGroupService.findByUmTUserCode(umTUserGroup).get(x).getUserCode().equals(userGroupList.get(y).getUserCode())){
					userGroupList.remove(y);
					break;
				}
			}
			if(userGroupList.size()==0) break;

		}

		try {
			umTUserGroupService.saveUmTUserGroupList(userGroupList);
			
			message = "success";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", message); 
			this.writeEasyUiData(jsonObject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}



	/**
	 * 删除UmTUserGroup信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				
				String ugid[]=id.getUserGroupId().split(", ");
				for(int i=0;i<ugid.length;i++){
					id.setUserGroupId(ugid[i]);
					umTUserGroupService.deleteByPK(id);
				}
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
	/**@author limingguo 2013-10-22
	 * 批量删除人员关联的车行
	 * 
	 * @return
	 */
	public String del4sUser() throws Exception {

		try{
			if(umTUserGroup!=null){
				
				String ugid[]=umTUserGroup.getGroupId().split(", ");
				for(int i=0;i<ugid.length;i++){
					//usergroupid = umTUserGroupService.find4sUserByGroupIdAndUserCode(ugid[i],umTUserGroup.getUserCode()).get(0).getId();
					umTUserGroupService.deleteByPK(umTUserGroupService.find4sUserByGroupIdAndUserCode(ugid[i],umTUserGroup.getUserCode()).get(0).getId());
				}
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
	 * 查看UmTUserGroup信息方法
	 * 
	 * @return
	 */
/*	public String view() throws Exception {
		
		operateType = "view";
		umTUserGroup = umTUserGroupService.findUmTUserGroupByPK(id);
		return SUCCESS;
	}*/
}