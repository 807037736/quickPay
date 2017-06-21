/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.picc.um.schema.model.UmTUser;
import com.picc.um.schema.model.UmTUserTask;
import com.picc.um.schema.model.UmTUserTaskId;
import com.picc.um.schema.vo.SessionUser;
import com.picc.um.service.facade.IUmTUserService;
import com.picc.um.service.facade.IUmTUserTaskService;

/**
 * 用户关联功能处理Action层
 * @author 姜卫洋
 */
@SuppressWarnings("serial")
public class UmTUserTaskAction extends Struts2Action{
	
	private IUmTUserTaskService umTUserTaskService;	
	
	public void setUmTUserTaskService(IUmTUserTaskService umTUserTaskService) {
		this.umTUserTaskService = umTUserTaskService;
	}

	public IUmTUserTaskService getUmTUserTaskService() {
		return umTUserTaskService;
	}
	
	private UmTUserTask umTUserTask;
	
	private UmTUserTaskId id;
	
	private UmTUser umTUser;
	
	private String userCode;
	
	private IUmTUserService umTUserService;
	
	private String selectStr;
	
	private ArrayList<String> cacheNameArray;
	
	public ArrayList<String> getCacheNameArray() {
		return cacheNameArray;
	}

	public void setCacheNameArray(ArrayList<String> cacheNameArray) {
		this.cacheNameArray = cacheNameArray;
	}
	
	
	

	public void setSelectStr(String selectStr) {
		this.selectStr = selectStr;
	}
	
	public String getSelectStr() {
		return selectStr;
	}
	
	public void setUmTUserService(IUmTUserService umTUserService) {
		this.umTUserService = umTUserService;
	}
	
	public void setUmTUser(UmTUser umTUser) {
		this.umTUser = umTUser;
	}
	
	public IUmTUserService getUmTUserService() {
		return umTUserService;
	}
	
	public UmTUser getUmTUser() {
		return umTUser;
	}
	
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	 public String getUserCode() {
		return userCode;
	}
	
	/** 操作类型 **/
	private String operateType;
	/** UmTUserTask getter/setter **/
	public UmTUserTask getUmTUserTask() {
		return this.umTUserTask;
	}
	
	public void setUmTUserTask(UmTUserTask umTUserTask) {
		this.umTUserTask = umTUserTask;
	}
	/** UmTUserTaskId getter/setter **/
	public UmTUserTaskId getId() {
		return this.id;
	}
	
	public void setId(UmTUserTaskId id) {
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
	private String userTaskId;
	public String getUserTaskId() {
		return this.userTaskId;
	}
	
	public void setUserTaskId(String userTaskId) {
		this.userTaskId = userTaskId;
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
	 * UmTUserTask查询，根据umTUserTask带过来的值为查询条件进行查询。
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
			Page resultPage = umTUserTaskService.findByUmTUserTask(umTUserTask, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新UmTUserTask信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		
		operateType = "update";
		umTUserTask = umTUserTaskService.findUmTUserTaskByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新UmTUserTask信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		
		umTUserTaskService.updateUmTUserTask(umTUserTask);
		return SUCCESS;
	}


	/**
	 * 准备增加UmTUserTask信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		
		operateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增UmTUserTask信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		

		umTUserTaskService.saveUmTUserTask(umTUserTask);
		return SUCCESS;
	}



	/**
	 * 删除UmTUserTask信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				
				umTUserTaskService.deleteByPK(id);
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
	 * 查看UmTUserTask信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		
		operateType = "view";
		umTUserTask = umTUserTaskService.findUmTUserTaskByPK(id);
		return SUCCESS;
	}
	
	
	public String addUserTask() throws Exception {
		//根据用户代码查询用户对象
		if(userCode==null){
			throw new Exception("用户对象不能为空");
		}else {
			
			umTUser = umTUserService.findUmTUserByUserCode(userCode);
		}
		serverCode = this.getCurrentServerCode();
		logger.info("servercode,umtUserTaskAction:{}",serverCode);
		return SUCCESS;
	}
	
	
	
	public void addTaskToUser() {
		JSONObject resultValue = new JSONObject();
		if(userCode==null||"".equals(userCode)){
			super.writeString("传入的用户名不能为空");
		}else {
			try{
				String insertStr = getRequest().getParameter("add");				//获取添加的功能
				String deleteStr = getRequest().getParameter("delete");			//获取取消的功能
				String[] taskArray = null;
				insertStr = insertStr.replaceAll("\"", "");
				deleteStr = deleteStr.replaceAll("\"", "");
				if((insertStr==null||"".equals(insertStr))&&(deleteStr==null||"".equals(deleteStr))){
					resultValue.put("success", false);
					resultValue.put("errorMsg", "请选择添加或删除的功能,不能进行空提交");
				}else {
					if(insertStr!=null&&!"".equals(insertStr)){
						if(insertStr.indexOf(",")!=-1){
							//传入多条数据	
							taskArray = insertStr.split(",");								//传入多条数据
						}else {
							taskArray = new String[]{insertStr};					//只有一条数据
						}
						umTUserTaskService.addTaskToUser(userCode, taskArray, String.valueOf(getSession().getAttribute("UserCode")), 1);
					}
					taskArray = null;						//重置数据对象
					if(deleteStr!=null&&!"".equals(deleteStr)){
						if(deleteStr.indexOf(",")!=-1){
							//传入多条数据	
							taskArray = deleteStr.split(",");								//传入多条数据
						}else {
							taskArray = new String[]{deleteStr};					//只有一条数据
						}
						umTUserTaskService.addTaskToUser(userCode, taskArray, String.valueOf(getSession().getAttribute("UserCode")), 2);
					}
					resultValue.put("success", true);
				}
			}catch(Exception ex){
				resultValue.put("success", false);
				resultValue.put("errorMsg", ex.getMessage());
			}finally{
				this.writeEasyUiData(resultValue);
			}
		}
	}
	
	public void addTask4User()throws Exception{
		if(userCode==null){
			throw new Exception("用户对象不能为空");
			}
		SessionUser operateUser = getUserFromSession();
		umTUserTaskService.deleteByUserCode(userCode);
		if(cacheNameArray!=null&&cacheNameArray.size()>0){
		String[] taskidArray=(String[])(cacheNameArray.toArray(new String[cacheNameArray.size()]));
		umTUserTaskService.addTaskToUser(userCode, taskidArray, operateUser.getUserCode(), 1);
		}
	}
}
