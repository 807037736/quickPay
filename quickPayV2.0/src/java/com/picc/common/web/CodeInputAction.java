
package com.picc.common.web;

import ins.framework.cache.CacheManager;
import ins.framework.cache.CacheService;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.utils.StringUtils;
import ins.framework.web.Struts2Action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;

import com.picc.common.utils.ToolsUtils;
import com.picc.platform.dms.schema.model.PrpDcompany;
import com.picc.platform.dms.service.facade.CodeService;
import com.picc.platform.dms.service.facade.MCDNewCodeService;
import com.picc.um.schema.model.UmTUser;
import com.picc.um.service.facade.IUmTUserService;
import com.sinosoft.bpsdriver.util.SystemCode;
import com.sinosoft.dmsdriver.service.common.DictPage;
import com.sinosoft.dmsdriver.service.server.PageService;


@SuppressWarnings("serial")
public class CodeInputAction extends Struts2Action {
	protected static CacheService cacheManager = CacheManager.getInstance("codeSelectCache");
	/** 字典服务 */
	protected MCDNewCodeService mcDNewCodeService;
	protected CodeService codeService;
	protected IUmTUserService umTUserService;
	public static final String CHANGE_METHOD = "change";
	public static final String QUERY_METHOD = "query";
	public static final String SELECT_METHOD = "select";
	public static final String CODE_INPUT = "codeInput";
	public static final String SESS_KEY = "CodeInputCondition";
	protected List<String> codeValues = new ArrayList<String>();
	protected List<String> codeLabels = new ArrayList<String>();
	protected List<Object[]> codeList = new ArrayList<Object[]>(0);
	protected int defaultPageSize = 50;
	protected int totalCount;
	protected int totalPage;
	protected String codeselect;
	protected String codeselectText;
	protected String fieldIndex;
	protected String fieldValue;
	protected String fieldNameValue;
	protected String codeMethod;
	protected String codeType;
	protected String typeParam;
	protected String codeRelation;
	protected String isClear;
	protected String otherCondition;
	protected String callBackMethod;
	protected String getDataMethod;
	protected String elementOrder;
	protected String elementLength;
	protected String riskCode;
	protected String extraCond;

	
	/********************** function start*************************/
	/**
	 * 双击域模板方法
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String query() {
		if (pageNo == 0) {
			pageNo = 1;
		}
		if (pageSize == 0) {
			pageSize = 10;
		}
		if(extraCond == null){
			extraCond = "";
		}
		//加入按照名称模糊查询的方式
//		if (codeMethod.equalsIgnoreCase(SELECT_METHOD) || codeMethod.equalsIgnoreCase(QUERY_METHOD)) {
//			if (fieldNameValue == null || fieldNameValue.equals("null")) {
//				fieldNameValue = "";
//			}
//			fieldNameValue = "%"+fieldNameValue + "%";
//		}
		String key = cacheManager.generateCacheKey("listDbclickCodeSelect", codeType,
				fieldValue, otherCondition, pageNo, pageSize, typeParam,extraCond);
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		if(ToolsUtils.notEmpty(codeType)){
			queryRule.addEqual("id.codeType", codeType);
		}
		//modified by liuyatao 2013-09-27
//		queryRule.addLike("id.codeCode", fieldValue);
//		queryRule.addLike("codeCName", fieldNameValue);
		if (codeMethod.equalsIgnoreCase(SELECT_METHOD) || codeMethod.equalsIgnoreCase(QUERY_METHOD)) {
			if (fieldValue == null || fieldValue.equals("null")) {
				fieldValue = "";
			}
			fieldValue = "%" + fieldValue + "%";
		}
		queryRule.addSql(" (codecode like '" + fieldValue + "' or codeCname like '" + fieldValue + "') ");
		if (StringUtils.isNotBlank(otherCondition)) {
			String[] conditions = otherCondition.split(",");
			for (int i = 0; i < conditions.length; i++) {
				String current = conditions[i];
				String[] keyVal = current.split("=");
				if (keyVal.length != 2) {
					continue;
				}
				queryRule.addEqual(keyVal[0], keyVal[1]);
			}
		}
		Page page = null;
		//先从缓存里查结果
		Object result = cacheManager.getCache(key);
		if ((Page) result != null) {
			page = (Page) result;
			this.writeJSONData(page,"id.codeCode","codeCName");
		}
		else{
			try {
				String valueString = "";
				page =  mcDNewCodeService.findNewCodeInfo(queryRule, pageNo, pageSize);
				if(page == null || page.getResult().size()== 0){
					if (codeMethod.equalsIgnoreCase(CHANGE_METHOD)){
						this.writeString(valueString);
					}
					else{
						this.writeJSONData(page,"id.codeCode","codeCName");
					}
					return NONE;
				}
				//进行select方法和change方法的区分 modified by liuyatao 2013-10-25
				if (codeMethod.equalsIgnoreCase(SELECT_METHOD) || codeMethod.equalsIgnoreCase(QUERY_METHOD)){
					this.writeJSONData(page,"id.codeCode","codeCName");
					cacheManager.putCache(key, page);
				}
				else{
					//获取第一个对象
					Object object = page.getResult().get(0);
					valueString = generateValues(object, "id.codeCode","codeCName");
					this.writeString(valueString);
				}
			} catch (Exception e) {
				this.writeJSONMsg(e.getMessage());
			}
		}
		return NONE;
	}
	/**
	 * modified by liuyatao 2013-8-30 15:46:18
	 * onchange 触发方法
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String changeCodeInput() {
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		if(ToolsUtils.notEmpty(codeType)){
			queryRule.addEqual("id.codeType", codeType);
		}
		queryRule.addEqual("id.codeCode", fieldValue);
		try {
			Page page = mcDNewCodeService.findNewCodeInfo(queryRule, pageNo, pageSize);
			if(page == null || page.getTotalCount()==0){
				return NONE;
			}
			//获取第一个对象
			Object object = page.getResult().get(0);
			String valueString = generateValues(object, "id.codeCode","codeCName");
			this.writeString(valueString);
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		return NONE;
	}
	/**
	 * 查询所有的codeType 来自MC_D_Type表
	 * @return
	 */
	public String queryCodeType() {
		if (pageNo == 0) {
			pageNo = 1;
		}
		if (pageSize == 0) {
			pageSize = 10;
		}
		if (codeMethod.equalsIgnoreCase(SELECT_METHOD) || codeMethod.equalsIgnoreCase(QUERY_METHOD)) {
			if (fieldValue == null || fieldValue.equals("null")) {
				fieldValue = "";
			}
			fieldValue = fieldValue + "%";
		}
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		//queryRule.addNotEqual("dataSource","dms3g:prpdtype");
		if(ToolsUtils.notEmpty(fieldValue)){
			queryRule.addLike("codeType", fieldValue);
		}
		try {
			Page page = mcDNewCodeService.findTypeInfo(queryRule, pageNo, pageSize);
			this.writeJSONData(page,"codeType","codeTypeDesc");
		} catch (Exception e) {
			this.writeJSONMsg(e.getMessage());
		}
		return NONE;
	}
	public String queryContinue() {
		this.query();
		return SUCCESS;
	}
	/**
	 * 清除系统中的缓存
	 */
	public String clearMemory() {
		cacheManager.clearAllCacheManager();
		return SUCCESS;
	}
	//解析所有的附加查询条件，并存放入map中，方便queryRule的使用。
	protected Map generateConditions(){
		if(ToolsUtils.isEmpty(otherCondition) ){
			return null;
		}
		//按照有序的方式进行map的存放
		Map conditionMap = new LinkedHashMap();
		String[] conditions = otherCondition.split(",");
		String key="";
		String value="";
		String[] keyValue=null;
		for(String condition : conditions){
			keyValue = condition.split("=");
			if(keyValue.length>1){
				key = keyValue[0];
				value = keyValue[1];
				//将该键值对放入map中
				conditionMap.put(key, value);
			}
		}
		return conditionMap;
	}

	protected String generateValues(Object element,String... args){
		int size = args.length;
		StringBuffer valueString = new StringBuffer();
		try{
			for (int i = 0; i < size; i++) {
				Object value = null;
				if (args[i].indexOf('.') > -1) {
					String[] arrArg = StringUtils.split(args[i], '.');
					value = element;
					for (int j = 0; j < arrArg.length - 1; j++) {
						value = PropertyUtils.getProperty(value, arrArg[j]);
						value = PropertyUtils.getProperty(value,
								arrArg[j + 1]);
					}
				}
				else {
					value = PropertyUtils.getProperty(element, args[i]);
				}
				if (i != size - 1) {
					valueString.append(value).append("_FIELD_SEPARATOR_");
				} else {
					valueString.append(value);
				}
			}
		}catch (Exception e) {
			logger.debug(e.getMessage());
			return "";
		}
		return valueString.toString();
	}
	
	public String codeTranslate(){
		HttpServletRequest request = getRequest();
		String codeCode = request.getParameter("codeCode");
        String codeType = request.getParameter("codeType");
        String riskCode = request.getParameter("riskCode");
        String language = request.getParameter("language");
        
        if (language == null) {
			language = "C";
		}
		
		if (riskCode == null) {
			riskCode = "PUB";
		}
		if (codeCode != null) {
			codeCode = codeCode.trim();
		}
		
		java.util.Map<String, String> codeMap = codeService.listCodes(codeType, riskCode, language);;
		if(codeMap.containsKey(codeCode)){
			this.writeJSONMsg(codeMap.get(codeCode));
		}else{
			this.writeJSONMsg("");
		}
        return NONE;
	}
	
	
	public String codeTranslateOfPolicyManager(){
		HttpServletRequest request = getRequest();
		String userCode = request.getParameter("codeCode").trim();
		try{
			if(!userCode.equals("")){	
				String key = cacheManager.generateCacheKey("getUserNameByUserCode", userCode);
				UmTUser umTUser = null;
				Object result = cacheManager.getCache(key);
				if (result != null) {
					umTUser = (UmTUser) result;
				}else{
					umTUser = umTUserService.findUmTUserByUserCode(userCode);
					cacheManager.putCache(key, umTUser);
				}
				StringBuffer codeString = new StringBuffer();
				if(umTUser != null){
					String userName = umTUser.getUserName();
					codeString.append(userCode).append("-").append(userName);
					this.writeJSONMsg(codeString.toString());
				}
				else{
					this.writeJSONMsg(userCode);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			return "";
		}
		
        return NONE;
	}
	
	public String getUserNameByUserCode(){
		HttpServletRequest request = getRequest();
		String userCode = request.getParameter("codeCode").trim();

		try{
			if(!userCode.equals(""))
			{			
				UmTUser umTUser = umTUserService.findUmTUserByUserCode(userCode);
				if(umTUser != null){
					String userName = umTUser.getUserName();
					this.writeJSONMsg(userName);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			return "";
		}
		
        return NONE;
	}
	
	public String queryComcode() throws Exception {
		if (pageNo == 0) {
			pageNo = 1;
		}
		if (pageSize == 0) {
			pageSize = 10;
		}
		String comCode = otherCondition;
		
		PrpDcompany prpDcompany = null;
		comCode="44030000";
		
		try {
			DictPage dictPage =PageService.getCompanys(SystemCode.DMS, comCode, "", "1", "9", "", 1, Integer.MAX_VALUE);
			Page page = null;
			if(dictPage != null && dictPage.getData() != null){
				long totalCount = dictPage.getTotalRecordCount();
				List<PrpDcompany> data = dictPage.getData();
				if (pageNo==1)  {
					data.add(0, prpDcompany);
				}
				 page = new Page((pageNo-1)*pageSize, totalCount, pageSize, data);
			}
			String valueResult = "";
			if (codeMethod.equalsIgnoreCase(CHANGE_METHOD)){
			    if(page == null || page.getResult().size()== 0){
			     this.writeString(valueResult);
			    }else{
			     //获取第一个对象
			     Object object = page.getResult().get(0);
			     valueResult = generateValues(object,"comCode","comCName");
			     this.writeString(valueResult);
			    }
			}else{
				this.writeJSONData(page,"comCode","comCName");
			}
		} catch (Exception e) {
			this.writeJSONMsg(e.getMessage());
		}
		return NONE;
	}
	
	
	/**
	 * get or set 方法
	 */
	public MCDNewCodeService getMcDNewCodeService() {
		return mcDNewCodeService;
	}

	public void setMcDNewCodeService(MCDNewCodeService mcDNewCodeService) {
		this.mcDNewCodeService = mcDNewCodeService;
	}
	public List<String> getCodeValues() {
		return codeValues;
	}

	public void setCodeValues(List<String> codeValues) {
		this.codeValues = codeValues;
	}

	public List<String> getCodeLabels() {
		return codeLabels;
	}

	public void setCodeLabels(List<String> codeLabels) {
		this.codeLabels = codeLabels;
	}

	public List<Object[]> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<Object[]> codeList) {
		this.codeList = codeList;
	}

	public String getFieldNameValue() {
		return fieldNameValue;
	}

	public void setFieldNameValue(String fieldNameValue) {
		this.fieldNameValue = fieldNameValue;
	}
	public String otherOperate() {
		return SUCCESS;
	}

	public String getCallBackMethod() {
		return callBackMethod;
	}

	public void setCallBackMethod(String callBackMethod) {
		this.callBackMethod = callBackMethod;
	}

	public String getCodeMethod() {
		return codeMethod;
	}

	public void setCodeMethod(String codeMethod) {
		this.codeMethod = codeMethod;
	}

	public String getCodeRelation() {
		return codeRelation;
	}

	public void setCodeRelation(String codeRelation) {
		this.codeRelation = codeRelation;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getElementLength() {
		return elementLength;
	}

	public void setElementLength(String elementLength) {
		this.elementLength = elementLength;
	}

	public String getElementOrder() {
		return elementOrder;
	}

	public void setElementOrder(String elementOrder) {
		this.elementOrder = elementOrder;
	}

	public String getFieldIndex() {
		return fieldIndex;
	}

	public void setFieldIndex(String fieldIndex) {
		this.fieldIndex = fieldIndex;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getGetDataMethod() {
		return getDataMethod;
	}

	public void setGetDataMethod(String getDataMethod) {
		this.getDataMethod = getDataMethod;
	}

	public String getIsClear() {
		return isClear;
	}

	public void setIsClear(String isClear) {
		this.isClear = isClear;
	}

	public String getOtherCondition() {
		return otherCondition;
	}

	public void setOtherCondition(String otherCondition) {
		this.otherCondition = otherCondition;
	}

	public String getRiskCode() {
		return riskCode;
	}

	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public String getCodeselect() {
		return codeselect;
	}

	public void setCodeselect(String codeselect) {
		this.codeselect = codeselect;
	}

	public String getCodeselectText() {
		return codeselectText;
	}

	public void setCodeselectText(String codeselectText) {
		this.codeselectText = codeselectText;
	}

	public String getTypeParam() {
		return typeParam;
	}

	public void setTypeParam(String typeParam) {
		this.typeParam = typeParam;
	}

	public String getExtraCond() {
		return extraCond;
	}

	public void setExtraCond(String extraCond) {
		this.extraCond = extraCond;
	}
	public CodeService getCodeService() {
		return codeService;
	}
	public void setCodeService(CodeService codeService) {
		this.codeService = codeService;
	}
	
	public IUmTUserService getUmTUserService() {
		return umTUserService;
	}

	public void setUmTUserService(IUmTUserService umTUserService) {
		this.umTUserService = umTUserService;
	}
}
