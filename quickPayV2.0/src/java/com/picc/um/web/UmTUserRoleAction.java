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
import java.util.Map;

import net.sf.json.JSONObject;

import com.picc.um.schema.model.UmTUser;
import com.picc.um.schema.model.UmTUserRole;
import com.picc.um.schema.model.UmTUserRoleId;
import com.picc.um.service.facade.IUmTUserRoleService;
import com.picc.um.service.facade.IUmTUserService;

/**
 * 用户角色关联处理Action
 * @author 姜卫洋
 */
@SuppressWarnings("serial")
public class UmTUserRoleAction extends Struts2Action {

	private IUmTUserRoleService umTUserRoleService;

	public void setUmTUserRoleService(IUmTUserRoleService umTUserRoleService) {
		this.umTUserRoleService = umTUserRoleService;
	}

	public IUmTUserRoleService getUmTUserRoleService() {
		return umTUserRoleService;
	}

	private UmTUserRole umTUserRole;

	private UmTUserRoleId id;

	private IUmTUserService umTUserService;

	private UmTUser umTUser;

	public void setUmTUserService(IUmTUserService umTUserService) {
		this.umTUserService = umTUserService;
	}

	public IUmTUserService getUmTUserService() {
		return umTUserService;
	}

	public void setUmTUser(UmTUser umTUser) {
		this.umTUser = umTUser;
	}

	public UmTUser getUmTUser() {
		return umTUser;
	}

	// 用户代码
	private String userCode;

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserCode() {
		return userCode;
	}

	/** 操作类型 **/
	private String operateType;

	/** UmTUserRole getter/setter **/
	public UmTUserRole getUmTUserRole() {
		return this.umTUserRole;
	}

	public void setUmTUserRole(UmTUserRole umTUserRole) {
		this.umTUserRole = umTUserRole;
	}

	/** UmTUserRoleId getter/setter **/
	public UmTUserRoleId getId() {
		return this.id;
	}

	public void setId(UmTUserRoleId id) {
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
	private String userRoleId;

	public String getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}

	/**************************** Function Start *******************************/

	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 */
	public String prepareQuery() throws Exception {
		
		return SUCCESS;
	}

	/**
	 * UmTUserRole查询，根据umTUserRole带过来的值为查询条件进行查询。
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
			serverCode = this.getCurrentServerCode();
			//重载上下文 add by liuyatao 2014年8月1日
			Page resultPage = umTUserRoleService.findByUmTUserRole(umTUserRole,
					page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}

	/**
	 * 准备更新UmTUserRole信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		
		operateType = "update";
		umTUserRole = umTUserRoleService.findUmTUserRoleByPK(id);
		return SUCCESS;
	}

	/**
	 * 更新UmTUserRole信息
	 * 
	 * @return
	 */
	public String update() throws Exception {
		umTUserRole.setUpdaterCode(getUserFromSession().getUserCode());
		umTUserRole.setComId(getUserFromSession().getComId());
		umTUserRole.setOperateTimeForHis(new Date());
		umTUserRoleService.updateUmTUserRole(umTUserRole,getUserFromSession().getComId());
		return SUCCESS;
	}

	/**
	 * 准备增加UmTUserRole信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		
		operateType = "add";
		return SUCCESS;
	}

	/**
	 * 新增UmTUserRole信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		umTUserRole.setCreatorCode(getUserFromSession().getUserCode());
		umTUserRole.setComId(getUserFromSession().getComId());
		umTUserRoleService.saveUmTUserRole(umTUserRole,getUserFromSession().getComId());
		return SUCCESS;
	}

	/**
	 * 删除UmTUserRole信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try {
			if (id != null) {
				umTUserRoleService.deleteByPK(id);
			}
		} catch (Exception e) {
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
	 * 查看UmTUserRole信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		
		operateType = "view";
		umTUserRole = umTUserRoleService.findUmTUserRoleByPK(id);
		return SUCCESS;
	}

	/**
	 * 页面跳转至用户角色添加界面
	 * 
	 * @return
	 * @throws Exception
	 *             2013-8-9下午1:52:50 jiangweiyang
	 */
	public String preparedAddUserRole() throws Exception {
		try {
			if (userCode != null && !"".equals(userCode)) {
				umTUser = umTUserService.findUmTUserByUserCode(userCode);
				return SUCCESS;
			}
			serverCode = this.getCurrentServerCode();
			return NONE;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public String getRoleListByComCode() throws Exception {
		serverCode = this.getCurrentServerCode();
		Page page = umTUserRoleService.getRoleListByComCode(String
				.valueOf(getUserFromSession().getComId()),serverCode);
		
		this.writeJSONData(page, "id.roleId", "roleCName");
		// this.writeEasyUiData(page,
		// "creatorCode","flag","insertTimeForHis","operateTimeForHis","remark","roleCode","umRoleComs","umTTaskList","updaterCode","umTRoleTasks","validStatus");
		return NONE;
	}

	public void addRoleToUser() throws Exception {
		JSONObject resultValue = new JSONObject();
		if (userCode == null || "".equals(userCode)) {
			resultValue.put("success", false);
			resultValue.put("message", "传入的用户名不能为空");
		} else {
			try{
				String insertStr = getRequest().getParameter("inserted"); 			// 获取新添加的的角色清单
				String deleteStr = getRequest().getParameter("deleted"); 				// 获取取消的角色清单
				String[] roleArray = null;
				insertStr = insertStr.replaceAll("\"", "");
				deleteStr = deleteStr.replaceAll("\"", "");
				if(insertStr!=null&&!"".equals(insertStr)){
					if(insertStr.indexOf(",")!=-1){
						//传入多条数据	
						roleArray = insertStr.split(",");								//传入多条数据
					}else {
						roleArray = new String[]{insertStr};					//只有一条数据
					}
					umTUserRoleService.addRoleToUser(getUserFromSession().getComId(),userCode, roleArray, String.valueOf(getUserFromSession().getUserCode()), 1);
				}
				roleArray = null;						//重置数据对象
				if(deleteStr!=null&&!"".equals(deleteStr)){
					if(deleteStr.indexOf(",")!=-1){
						//传入多条数据	
						roleArray = deleteStr.split(",");								//传入多条数据
					}else {
						roleArray = new String[]{deleteStr};					//只有一条数据
					}
					umTUserRoleService.addRoleToUser(getUserFromSession().getComId(),userCode, roleArray, String.valueOf(getUserFromSession().getUserCode()), 2);
				}
				resultValue.put("success", true);
				resultValue.put("message", "角色信息更新成功");
			}catch(Exception ex){
				resultValue.put("success", false);
				resultValue.put("message", "更新失败:"+ex.getMessage());
			}
			this.writeEasyUiData(resultValue);
		}

	}

}
