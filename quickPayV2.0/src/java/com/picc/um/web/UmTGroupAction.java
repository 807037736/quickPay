/*
 * Powered By limingguo
 * Email: limingguo03@chongq.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.picc.um.schema.model.UmTGroup;
import com.picc.um.schema.model.UmTGroupId;
import com.picc.um.schema.vo.SessionUser;
import com.picc.um.service.facade.IUmTGroupService;


/**
 * 自定义组Action处理层
 * @author 李明果
 */
@SuppressWarnings("serial")
public class UmTGroupAction extends Struts2Action{
	
	private IUmTGroupService umTGroupService;
	private String comCode;
	

	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	public void setUmTGroupService(IUmTGroupService umTGroupService) {
		this.umTGroupService = umTGroupService;
	}

	public IUmTGroupService getUmTGroupService() {
		return umTGroupService;
	}
	
	private UmTGroup umTGroup;
	
	
	private UmTGroupId id;
	
	private String comId;
	
	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	/** 操作类型 **/
	private String operateType;
	/** UmTGROUP getter/setter **/
	public UmTGroup getUmTGroup() {
		return this.umTGroup;
	}
	
	public void setUmTGroup(UmTGroup umTGroup) {
		this.umTGroup = umTGroup;
	}
	/** UmTGROUPId getter/setter **/
	public UmTGroupId getId() {
		return this.id;
	}
	
	public void setId(UmTGroupId id) {
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
	private String groupId;
	public String getGroupId() {
		return this.groupId;
	}
	
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	private String userCode;
	
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	private int page;
	
	private int rows;
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	
	private int flag_4s;
	public int getFlag_4s() {
		return flag_4s;
	}

	public void setFlag_4s(int flag_4s) {
		this.flag_4s = flag_4s;
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
	 * UmTGROUP查询，根据umTGROUP带过来的值为查询条件进行查询。
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
			//if(umTGroup!=null){
				//umTGroup.setGroupName(new String(umTGroup.getGroupName().getBytes("ISO-8859-1"), "UTF-8"));
			//}
			String comcode= getUserFromSession().getComCode();
			Page resultPage = umTGroupService.findByUmTGroup(umTGroup,comcode, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			//this.writeJSONMsg(e.getMessage());
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}
	
	
	public String queryGroupCom4s() throws Exception {
		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 20;
		}

		try {

			Page resultPage = umTGroupService.findGroupAnd4sComCode(umTGroup, page, rows);
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
	 * 准备更新UmTGROUP信息
	 * 
	 * @return
	 */
	/*public String prepareUpdate() throws Exception {
		
		//System.out.println("正在执行UmTGROUPAction.prepareUpdate，准备更新UmTGROUP信息");
		operateType = "update";		
		
		umTGroup = umTGroupService.findUmTGroupByPK(id);
		return SUCCESS;
	}*/
	
	/**
	 * 更新UmTGROUP信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		

		HttpSession session=getSession();
		String userCode=((SessionUser) session.getAttribute("SessionUser")).getUserCode();

		umTGroup.setUpdaterCode(userCode);
		try {
			if(umTGroup.getGroupType().equals("1")){
				umTGroup.setGroupName(umTGroup.getGroupName().trim());
				umTGroup.setGroupCode(umTGroup.getGroupCode().trim());
				umTGroupService.updateUmTGroup(umTGroup);
				
				message = "success";
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("msg", message); 
				this.writeEasyUiData(jsonObject);
			}else{
				message = "不能修改车行信息";
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("msg", message); 
				this.writeEasyUiData(jsonObject);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("", e);
			this.writeJSONMsg(e.getMessage());
		}
		return NONE;
	}
	
	/**
	 * 新增UmTGROUP信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		
		
		HttpSession session=getSession();
		String userCode = ((SessionUser) session.getAttribute("SessionUser")).getUserCode();
		umTGroup.setCreatorCode(userCode);
		//umTGroup.setGroupType("1");

		try {
			/*去掉空格*/
			umTGroup.setGroupName(umTGroup.getGroupName().trim());
			umTGroup.setGroupCode(umTGroup.getGroupCode().trim());
			umTGroupService.saveUmTGroup(umTGroup);
			
			//返回groupid
			//umTGroupService.findGroupId(umTGroup.getGroupCode());
			message = "success";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", message); 
			jsonObject.put("groupid", umTGroupService.findGroupId(umTGroup.getGroupCode())); 
			this.writeEasyUiData(jsonObject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("", e);
			this.writeJSONMsg(e.getMessage());
		}
		return NONE;
	}


	/**
	 * 管理UmTGROUP
	 * 
	 * @return
	 */
	public String prepareManage() throws Exception {
		
		comCode = getUserFromSession().getComCode();
		comId = getUserFromSession().getComId();
		return SUCCESS;
	}
	
	/**
	 * 车行管理
	 * 
	 * @return
	 */
	public String prepare4sManage() throws Exception {
		
		comCode=getUserFromSession().getComCode();
		return SUCCESS;
	}
	
	/**
	 * 车行管理
	 * 
	 * @return
	 */
	public String prepare4sEdit() throws Exception {
		
		comCode=getUserFromSession().getComCode();
		return SUCCESS;
	}
	
	/**
	 * 配置流程角色
	 * 
	 * @return
	 */
	public String prepareUmGroupEdit() throws Exception {	
		comCode=getUserFromSession().getComCode();
		flag_4s = this.getFlag_4s();
		
		return SUCCESS;
	}
	
}
