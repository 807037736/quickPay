/*
 * Powered By limingguo
 * Email: limingguo03@chongq.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.picc.um.schema.model.UmTGroupCom;
import com.picc.um.schema.model.UmTGroupComId;
import com.picc.um.schema.vo.SessionUser;
import com.picc.um.service.facade.IUmTGroupComService;

/**
 * 自定义组机构关联Action处理层
 * @author 李明果
 */
@SuppressWarnings("serial")
public class UmTGroupComAction extends Struts2Action{

	private IUmTGroupComService umTGroupComService;
	
	public IUmTGroupComService getUmTGroupComService() {
		return umTGroupComService;
	}

	public void setUmTGroupComService(IUmTGroupComService umTGroupComService) {
		this.umTGroupComService = umTGroupComService;
	}

	private UmTGroupCom umTGroupCom;
	
	private UmTGroupComId id;
	
	/** 操作类型 **/
	private String operateType;
	/** UmTGROUPCOM getter/setter **/
	public UmTGroupCom getUmTGroupCom() {
		return this.umTGroupCom;
	}
	
	public void setUmTGroupCom(UmTGroupCom umTGroupCom) {
		this.umTGroupCom = umTGroupCom;
	}
	/** UmTGROUPCOMId getter/setter **/
	public UmTGroupComId getId() {
		return this.id;
	}
	
	public void setId(UmTGroupComId id) {
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
	private String groupComId;
	public String getGroupComId() {
		return this.groupComId;
	}
	
	public void setGroupComId(String groupComId) {
		this.groupComId = groupComId;
	}
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	private String comCode;
	
	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
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
	
	/****************************Function Start*******************************/
	
	

	

	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 */
	/*public String prepareQuery() throws Exception {		
		
		
		return SUCCESS;
	}*/
	
	/**
	 *  通过comcode 查找返回group 和 groupcom 联合有效的流程角色
	 * @author limingguo 2013-8-22
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
			Page resultPage = umTGroupComService.findGroupAndComByUmTGroupComCode(umTGroupCom, page, rows);
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
	 * 通过comcode 查找返回group 和 groupcom 联合有效的流程角色和车行列表
	 * @author limingguo 2013-8-22
	 * @return
	 */	
	public String queryGroupAnd4sCombo() throws Exception{
		
		try {			
			Page resultPage = umTGroupComService.findGroupAnd4sByComcode(comCode);
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
	 * 通过comcode 查找返回group 和 groupcom 联合有效的车行列表
	 * @author limingguo 2013-8-22
	 * @return
	 */	
	public String queryCom4sCombo() throws Exception{
		
		try {			
			Page resultPage = umTGroupComService.findCom4sByComcode(comCode);
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
	 * 通查询机构拥有的车行
	 * @author limingguo 2013-8-22
	 * @return
	 */	
	public String queryCom4sComboByComCode() throws Exception{
		
		try {	
			//comCode = comCode.substring(0,6).concat("%");
			comCode = "%";
			Page resultPage = umTGroupComService.findCom4sByComcode(comCode);

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
	 * 查询车行和流程角色所有机构
	 * @author limingguo 2013-8-22
	 * @return
	 */	
	public String queryByGroupId() throws Exception {
		
		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 20;
		}
		try {			
			String comcode= getUserFromSession().getComCode();
			Page resultPage = umTGroupComService.findByUmTGroupCom(umTGroupCom, comcode,page, rows);			
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
		
	}
	
	/**
	 * 通过comcode 查找返回group 和 groupcom 联合有效的车行Page
	 * @author limingguo 2013-8-22
	 * @return
	 */	
	public String queryGroupComByComCode() throws Exception {
		
		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 20;
		}

		try {			
			Page resultPage = umTGroupComService.findGroupAnd4sByUmTGroupComCode(umTGroupCom, page, rows);
			
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			//this.writeJSONMsg(e.getMessage());
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}
	
	public String query4sGroupComByComCode() throws Exception {
		
		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 20;
		}

		try {
			umTGroupCom.setComCode(umTGroupCom.getComCode().substring(0,6).concat("%"));
			Page resultPage = umTGroupComService.find4sByUmTGroupComCode(umTGroupCom, page, rows);
			
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
	 * 更新UmTGROUPCOM信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		
		HttpSession session=getSession();
		String userCode=((SessionUser) session.getAttribute("SessionUser")).getUserCode();
		umTGroupCom.setUpdaterCode(userCode);
		
		umTGroupComService.updateUmTGroupCom(umTGroupCom);
		return SUCCESS;
	}


	/**
	 * 准备增加UmTGROUPCOM信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		
		operateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增UmTGROUPCOM信息 添加机构关联
	 * 
	 * @return
	 */
	public String add() throws Exception {		

		HttpSession session=getSession();
		String userCode= ((SessionUser) session.getAttribute("SessionUser")).getUserCode();
		umTGroupCom.setCreatorCode(userCode);
		
		if("".equals(umTGroupCom.getValidStatus())||umTGroupCom.getValidStatus()==null){
			umTGroupCom.setValidStatus("1");
		}
		

		try {
			
			for(int x=0;x<umTGroupComService.findUmTGroupComByGroupId(umTGroupCom).size();x++){
				if(umTGroupCom.getComCode().equals(umTGroupComService.findUmTGroupComByGroupId(umTGroupCom).get(x).getComCode())){
					return NONE;
				}
				
			}
			umTGroupComService.saveUmTGroupCom(umTGroupCom);
			
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
	
	/*public String addGroupComList() throws Exception {
		
		
		HttpSession session=getSession();
		String userCode=(String) session.getAttribute("UserCode");
		
		List<UmTGroupCom> groupComList = new ArrayList<UmTGroupCom>();
		String gi[]=umTGroupCom.getGroupId().split(", ");		
		
		for(int i=0;i<gi.length;i++){
			UmTGroupCom gc = new UmTGroupCom();
			gc.setComCode(umTGroupCom.getComCode());
			gc.setGroupId(gi[i]);
			gc.setValidStatus(umTGroupCom.getValidStatus());
			gc.setCreatorCode(userCode);
			groupComList.add(gc);
		}
		
		
		try {
			umTGroupComService.saveUmTGroupComList(groupComList);
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



	/**
	 * 删除UmTGROUPCOM信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				
				umTGroupComService.deleteByPK(id);
				//this.writeJSONMsg(SUCCESS);
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

}
