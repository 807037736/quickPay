/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.portal.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import com.picc.portal.schema.model.WfTPortletinfo;
import com.picc.portal.schema.model.WfTPortletinfoId;
import com.picc.portal.service.facade.IWfTPortletinfoService;


@SuppressWarnings("serial")
public class WfTPortletinfoAction extends Struts2Action{
	
	private IWfTPortletinfoService wfTPortletinfoService;	
	public void setWfTPortletinfoService(IWfTPortletinfoService wfTPortletinfoService) {
		this.wfTPortletinfoService = wfTPortletinfoService;
	}

	public IWfTPortletinfoService getWfTPortletinfoService() {
		return wfTPortletinfoService;
	}
	
	private WfTPortletinfo wfTPortletinfo;
	
	private WfTPortletinfoId wfTPortletinfoId;
	
	private String left;
	
	private String middle;
	
	private String right;
	
	private String portletId;

	public String getPortletId() {
		return portletId;
	}

	public void setPortletId(String portletId) {
		this.portletId = portletId;
	}

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public String getMiddle() {
		return middle;
	}

	public void setMiddle(String middle) {
		this.middle = middle;
	}

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}

	/** 操作类型 **/
	private String opreateType;
	/** WfTPortletinfo getter/setter **/
	public WfTPortletinfo getWfTPortletinfo() {
		return this.wfTPortletinfo;
	}
	
	public void setWfTPortletinfo(WfTPortletinfo wfTPortletinfo) {
		this.wfTPortletinfo = wfTPortletinfo;
	}
	/** WfTPortletinfoId getter/setter **/
	public WfTPortletinfoId getWfTPortletinfoId() {
		return this.wfTPortletinfoId;
	}
	
	public void setId(WfTPortletinfoId wfTPortletinfoId) {
		this.wfTPortletinfoId = wfTPortletinfoId;
	}
	/** opreateType getter/setter **/
	public String getOpreateType() {
		return opreateType;
	}

	public void setOpreateType(String opreateType) {
		this.opreateType = opreateType;
	}
	
	/** 主键对象 */
	private String id;
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
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
	 * WfTPortletinfo查询，根据wfTPortletinfo带过来的值为查询条件进行查询。
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
			Page resultPage = wfTPortletinfoService.findByWfTPortletinfo(wfTPortletinfo, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeJSONMsg(e.getMessage());
		}
		return NONE;
	}	


	/**
	 * 准备更新WfTPortletinfo信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		
		opreateType = "update";
		wfTPortletinfo = wfTPortletinfoService.findWfTPortletinfoByPK(wfTPortletinfoId);
		return SUCCESS;
	}
	
	/**
	 * 更新WfTPortletinfo信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		
		wfTPortletinfoService.updateWfTPortletinfo(wfTPortletinfo);
		return SUCCESS;
	}


	/**
	 * 准备增加WfTPortletinfo信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		
		opreateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增WfTPortletinfo信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		

		wfTPortletinfoService.saveWfTPortletinfo(wfTPortletinfo);
		return SUCCESS;
	}



	/**
	 * 删除WfTPortletinfo信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				
				wfTPortletinfoService.deleteByPK(wfTPortletinfoId);
				this.writeJSONMsg(SUCCESS);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("", e);
			this.writeJSONMsg(e.getMessage());
		}
		return NONE;
	}



	/**
	 * 查看WfTPortletinfo信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		
		opreateType = "view";
		wfTPortletinfo = wfTPortletinfoService.findWfTPortletinfoByPK(wfTPortletinfoId);
		return SUCCESS;
	}
	
	/**
	 * 保存页面portlet位置方法
	 * author zhou
	 */
	
	public String savePortletPosition() {
		
		//wfTPortletinfoService.savePortletPosition(a, "0000000000", porletcol);
		String userCode = getUserFromSession().getUserCode();
		String comId = getUserFromSession().getComId();
		String comCode = getUserFromSession().getComCode();
		wfTPortletinfoService.savePortletPosition(left, middle, right, userCode, comId, comCode);
		return NONE;
	}
	
	/**
	 * 修改isclosed为1
	 * Author zhou
	 * @return
	 */
	public String updateIsclosed(){
		
		String userCode = getUserFromSession().getUserCode();
		String comId = getUserFromSession().getComId();
		String comCode = getUserFromSession().getComCode();
		wfTPortletinfoService.updatePorletIsClose(userCode,comId,comCode);
		return NONE;
	}
	
//	/**
//	 * 获取用户要添加模块的Id、UserCode、ComCode，进行保存
//	 * @author ding
//	 * @return
//	 * @throws Exception
//	 */
//	public String finishAdd() throws Exception {
//		
//		HttpSession session=this.getSession();
//		String comId = (String) session.getAttribute("ComCode");
//		String userCode=(String) session.getAttribute("UserCode");
//		wfTPortletinfoService.addWfTPortletinfo(userCode,portletId,comId);
//		return NONE;
//	}
}
