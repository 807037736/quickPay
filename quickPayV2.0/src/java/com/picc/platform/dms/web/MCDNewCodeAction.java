package com.picc.platform.dms.web;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.web.Struts2Action;

import java.util.List;

import com.picc.common.utils.ToolsUtils;
import com.picc.platform.dms.schema.model.MCDNewCode;
import com.picc.platform.dms.schema.model.MCDNewCodeId;
import com.picc.platform.dms.schema.model.MCDType;
import com.picc.platform.dms.service.facade.MCDNewCodeService;

public class MCDNewCodeAction extends Struts2Action {
	/** 字典服务 */
	private MCDNewCodeService mcDNewCodeService;
	
	/** 字典对象 */
	private MCDNewCode mcDNewCode;
	/** 字典类型对象 */
	private MCDType mcDType;
	/** 主键信息*/
	private MCDNewCodeId id;
	
	private String codeType;
	
	private String codeTypeName;

	private String dataSource;
	/** 操作类型 */
	private String opreateType;

	public MCDNewCodeService getMcDNewCodeService() {
		return mcDNewCodeService;
	}

	public MCDType getMcDType() {
		return mcDType;
	}

	public void setMcDType(MCDType mcDType) {
		this.mcDType = mcDType;
	}

	public void setMcDNewCodeService(MCDNewCodeService mcDNewCodeService) {
		this.mcDNewCodeService = mcDNewCodeService;
	}

	public MCDNewCode getMcDNewCode() {
		return mcDNewCode;
	}

	public void setMcDNewCode(MCDNewCode mcDNewCode) {
		this.mcDNewCode = mcDNewCode;
	}

	public MCDNewCodeId getId() {
		return id;
	}

	public void setId(MCDNewCodeId id) {
		this.id = id;
	}
	
	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCodeTypeName() {
		return codeTypeName;
	}

	public void setCodeTypeName(String codeTypeName) {
		this.codeTypeName = codeTypeName;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getOpreateType() {
		return opreateType;
	}

	public void setOpreateType(String opreateType) {
		this.opreateType = opreateType;
	}


	/** **************************Function Start****************************** */
	
	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 */
	public String prepareQuery() throws Exception {
		
		return SUCCESS;
	}
	
	/**
	 * NewCode查询，根据NewCode带过来的值为查询条件进行查询。
	 * 
	 * @return
	 */
	public String query() {
		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 20;
		}
		QueryRule queryRule = QueryRule.getInstance();
		if(ToolsUtils.notEmpty(codeType)) {
			queryRule.addEqual("id.codeType", codeType);
		}
		if(ToolsUtils.notEmpty(codeType)) {
			queryRule.addEqual("id.codeType", codeType);
		}
		if(ToolsUtils.notEmpty(dataSource)) {
			queryRule.addEqual("dataSource", dataSource);
		}
		queryRule.addAscOrder("sort");
		try {
			Page resultPage = mcDNewCodeService.findByNewCode(queryRule, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeJSONMsg(e.getMessage());
		}
		return NONE;
	}	
	/**
	 * 准备更新NewCode信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		QueryRule queryRule= QueryRule.getInstance();
		List<MCDType> typelist=mcDNewCodeService.findTypeInfo(queryRule, 1, 1000).getResult();
		this.getRequest().setAttribute("typelist", typelist);
		opreateType = "update";
		mcDNewCode = mcDNewCodeService.findNewCodeByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新NewCode信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		String comId = getUserFromSession().getComId();
		//mcDNewCode.setComId(comId);
		mcDNewCodeService.updateNewCode(mcDNewCode);
		return SUCCESS;
	}


	/**
	 * 准备增加NewCode信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {

		
		QueryRule queryRule= QueryRule.getInstance();
		List<MCDType> typelist=mcDNewCodeService.findTypeInfo(queryRule, 1, 1000).getResult();
		this.getRequest().setAttribute("typelist", typelist);
		opreateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增NewCode信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		String comId = getUserFromSession().getComId();
		//comId= comId.substring(0, 4)+"0000";
		//mcDNewCode.setComId(comId);
		mcDNewCodeService.saveNewCode(mcDNewCode);
		return SUCCESS;
	}



	/**
	 * 删除NewCode信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				
				mcDNewCodeService.deleteNewCodeByPK(id);
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
	 * 查看NewCode信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		
		opreateType = "view";
		mcDNewCode = mcDNewCodeService.findNewCodeByPK(id);
		return SUCCESS;
	}
	/**对字典类型的增删改查**/
	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 */
	public String prepareQueryType() throws Exception {		
		
		return SUCCESS;
	}
	
	/**
	 * NewCode查询，根据NewCode带过来的值为查询条件进行查询。
	 * 
	 * @return
	 */
	public String queryType() throws Exception {
		
		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 20;
		}
		QueryRule queryRule = QueryRule.getInstance();
		if(ToolsUtils.notEmpty(codeType))
			queryRule.addEqual("codeType", codeType);
		if(ToolsUtils.notEmpty(dataSource))
			queryRule.addEqual("dataSource", dataSource);
		try {
			Page resultPage = mcDNewCodeService.findTypeInfo(queryRule, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeJSONMsg(e.getMessage());
		}
		return NONE;
	}	
	/**
	 * 准备更新NewCode信息
	 * 
	 * @return
	 */
	public String prepareUpdateType() throws Exception {
		
		opreateType = "update";
		mcDType = mcDNewCodeService.findTypeByPK(codeType);
		return SUCCESS;
	}
	
	/**
	 * 更新NewCode信息
	 * 
	 * @return
	 */
	public String updateType() throws Exception { 
		if(ToolsUtils.isEmpty(mcDType.getNewCodeType()))
			mcDType.setNewCodeType(mcDType.getCodeType());
		String comId = getUserFromSession().getComId();
		//mcDNewCode.setComId(comId);
		mcDNewCodeService.updateType(mcDType);
		return SUCCESS;
	}


	/**
	 * 准备增加NewCode信息
	 * 
	 * @return
	 */
	public String prepareAddType() throws Exception {
		
		opreateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增NewCode信息
	 * 
	 * @return
	 */
	public String addType() throws Exception {
		//由于newcodetype和comid不允许为空，因而需要对这两个字段进行赋值
		if(ToolsUtils.isEmpty(mcDType.getNewCodeType()))
			mcDType.setNewCodeType(mcDType.getCodeType());
		String comId = getUserFromSession().getComId();
		//comId= comId.substring(0, 4)+"0000";
		//mcDType.setComId(comId);
		mcDNewCodeService.saveType(mcDType);
		return SUCCESS;
	}



	/**
	 * 删除NewCode信息
	 * 
	 * @return
	 */
	public String deleteType() throws Exception {
		try{
			if(ToolsUtils.notEmpty(codeType)){
				
				mcDNewCodeService.deleteTypeByPK(codeType);
				this.writeJSONMsg(SUCCESS);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("", e);
			this.writeJSONMsg(e.getMessage());
		}
		return NONE;
	}
}
