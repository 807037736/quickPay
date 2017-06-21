package com.picc.platform.dms.web;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.web.Struts2Action;

import com.picc.platform.dms.schema.model.PrpDuser;
import com.picc.platform.dms.service.facade.UserService;

@SuppressWarnings("serial")
public class UserAction extends Struts2Action {
	/** 员工服务 */
	private UserService userService; 
	/** 员工对象 */
	private PrpDuser prpDuser;
	/** 操作类型 */
	private String opreateType;
	/** 工号 */
	private String userCode;
    private String userName;
    private String password;
    

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserService getUserService() {
		return userService;
	}

	public PrpDuser getPrpDuser() {
		return prpDuser;
	}

	public void setPrpDuser(PrpDuser prpDuser) {
		this.prpDuser = prpDuser;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getOpreateType() {
		return opreateType;
	}

	public void setOpreateType(String opreateType) {
		this.opreateType = opreateType;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	/** **************************Function Start****************************** */
	/**
	 * 员工列表查询
	 * 
	 * @return
	 */
	public String query() {
		logger.debug("查询满足条件的 员工信息");
		if (pageNo == 0) {
			pageNo = 1;
		}
		if (pageSize == 0) {
			pageSize = 20;
		}
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		if (prpDuser.getUserCode() != null
				&& !"".equals(prpDuser.getUserCode())) {
			queryRule.addEqual("userCode", prpDuser.getUserCode());// 增加userCode的查询条件
		}
		if (prpDuser.getUserName() != null
				&& !"".equals(prpDuser.getUserName())) {
			queryRule.addLike("userName", prpDuser.getUserName());// 增加userName的查询条件
		}
		if (prpDuser.getPrpDcompany().getComCode() != null
				&& !"".equals(prpDuser.getPrpDcompany().getComCode())) {
			queryRule.addLike("prpDcompany.comCode", prpDuser.getPrpDcompany()
					.getComCode());// 增加comCode的查询条件
		}
		if (prpDuser.getNewUserCode() != null
				&& !"".equals(prpDuser.getNewUserCode())) {
			queryRule.addEqual("newUserCode", prpDuser.getNewUserCode());// 增加newUserCode的查询条件
		}
		try {
			Page page = userService.findUser(queryRule, pageNo, pageSize);
			this.writeJSONData(page, "userCode", "userName",
					"prpDcompany.comCode", "newUserCode");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeJSONMsg(e.getMessage());
		}
		return NONE;
	}

	/**
	 * 更新员工信息
	 * 
	 * @return
	 */
	public String update() { 
		userService.update(prpDuser);
		return SUCCESS;
	}

	/**
	 * 准备更新员工信息
	 * 
	 * @return
	 */
	public String prepareUpdate() {
		
		opreateType = "edit";
		prpDuser = userService.getUser(userCode);
		return SUCCESS;
	}

	/**
	 * 新增员工信息
	 * 
	 * @return
	 */
	public String add() {
		
		prpDuser.setUserName(userName);
		prpDuser.setPassword(password);
		userService.save(prpDuser);
		return SUCCESS;
	}

	/**
	 * 准备增加员工信息
	 * 
	 * @return
	 */
	public String prepareAdd() {
		
		opreateType = "add";
		return SUCCESS;
	}

	/**
	 * 删除员工信息
	 * 
	 * @return
	 */
	public String delete() {
		
		userService.delete(userCode);
		return NONE;
	}

	/**
	 * 准备查询方法
	 * 
	 * @return
	 */
	public String prepareQuery() {
		logger.debug("员工菜单跳转");
		return SUCCESS;
	}

	/**
	 * 查看员工信息方法
	 * 
	 * @return
	 */
	public String view() {
		logger.debug("查看员工信息");
		opreateType = "view";
		prpDuser = userService.getUser(userCode);
		return SUCCESS;
	}
	public String logout() {
		getSession().invalidate();
		return SUCCESS;
	}
}
