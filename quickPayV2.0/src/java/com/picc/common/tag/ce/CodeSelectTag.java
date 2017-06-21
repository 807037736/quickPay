package com.picc.common.tag.ce;

import ins.framework.cache.CacheManager;
import ins.framework.cache.CacheService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.struts2.ServletActionContext;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.util.ValueStack;
import com.picc.common.utils.ToolsUtils;
import com.picc.platform.dms.service.facade.CodeService;

@SuppressWarnings("serial")
public class CodeSelectTag extends ItemTag {
	/**
	 * 初始缓存实例
	 */
	private static CacheService cacheManager = CacheManager.getInstance("codeSelectCache");
	protected String list;
	protected String multiple;
	protected String codeType;
	protected String riskCode;
	protected String language;
	protected String emptyOption;
	protected String useDms;
	protected String withCode;
	protected String ignoreValue;
	protected CodeService codeService;

	@SuppressWarnings("unchecked")
	public int doStartTag() throws JspException {
		if (language == null) {
			language = "C";
		}
		if (riskCode == null) {
			riskCode = "PUB";
		}
		if (useDms == null) {
			useDms = "false";
		}
		if (codeService == null) {
			WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext
					.getServletContext());
			codeService = (CodeService) wac.getBean("codeService");
		}
		/*System.out.println(ServletActionContext.getContext());
		System.out.println(ActionContext.getContext());*/
		ValueStack valueStack = null;
		if(ServletActionContext.getContext()!=null){
			valueStack = ServletActionContext.getContext().getValueStack();
		}
		StringBuffer commonHTML = generateHTML();
		ArrayList valueList = new ArrayList();
		StringBuffer buffer = new StringBuffer();
		if (id == null) {
			id = name;
		}
		if (value != null) {
			if (value.startsWith("{") && value.endsWith("}")) {
				value = value.substring(1, value.length() - 1);
				String[] values = value.split(",");
				for (int i = 0; i < values.length; i++) {
					if (values[i].startsWith("'") && values[i].endsWith("'")) {
						values[i] = values[i].substring(1, values[i].length() - 1);
					}
					valueList.add(values[i].trim());
				}
			} else {
				if(valueStack!=null){
					Object valueObject = valueStack.findValue(value);
					if (valueObject instanceof java.util.List) {
						java.util.List objList =(java.util.List) valueObject;
						for (Object obj : objList) {
							valueList.add(obj.toString().trim());
						}
					} else {
						if (value.startsWith("'") && value.endsWith("'")) {
							value = value.substring(1, value.length() - 1);
						}
						valueList.add(value.trim());
					}
				}
			}
		}
		java.util.Map<String, String> map = null;
		String key = cacheManager.generateCacheKey("listCodeSelect", codeType, riskCode, language);
		
		Object result = cacheManager.getCache(key);
		if (result != null) {
			map = (java.util.Map<String, String>) result;
		} else {
			if (useDms.equals("true")) {
				//map = QtDmsUtil.getPrpDCodeList(codeType);
			} else {
				map = codeService.listCodes(codeType, riskCode, language);
			}
			cacheManager.putCache(key, map);
		}
		buffer.append("<select");
		buffer.append(commonHTML);
		if (id != null) {
			buffer.append(" id=\"").append(id).append("\"");
		}
		buffer.append(">");
		if (emptyOption != null && emptyOption.equalsIgnoreCase("true")) {
			buffer.append("<option value=\"\">请选择</option>");
		}
		Iterator iterator = map.keySet().iterator();
		String keyText;
		String valueText;
		String showText;
		HashMap ignoreMap = new HashMap();;
		if(ToolsUtils.notEmpty(ignoreValue)){
			String[] ignoreValues = ignoreValue.split(",");
			for(String value:ignoreValues){
				if(ToolsUtils.notEmpty(value)){
					ignoreMap.put(value, "");
				}
			}
		}
		while (iterator.hasNext()) {
			keyText = (String) iterator.next();
			valueText = map.get(keyText);
			if(!ignoreMap.containsKey(keyText)){
				showText = valueText;
				if (withCode != null && withCode.equalsIgnoreCase("true")) {
					showText = keyText + " - " + valueText;
				}
				buffer.append("<option value=\"").append(keyText).append("\"");
				if (valueList.contains(keyText)) {
					buffer.append(" selected");
				}
				buffer.append(">").append(showText).append("</option>");
			}
		}
		buffer.append("</select>");
		JspWriter writer = pageContext.getOut();
		try {
			writer.print(buffer.toString());
		} catch (IOException e) {
			throw new JspException(e.toString());
		}
		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspException {
		clearValue();
		list = null;
		multiple = null;
		return EVAL_PAGE;
	}

	public void setMultiple(String multiple) throws JspException {
		this.multiple = multiple;
		if (multiple != null && multiple.startsWith("${") && multiple.endsWith("}")) {
			this.multiple = (String) ExpressionEvaluatorManager.evaluate("multiple", multiple, Object.class, this,
					pageContext);
		}
	}

	public void setCodeType(String codeType) throws JspException {
		this.codeType = codeType;
		if (codeType != null && codeType.startsWith("${") && codeType.endsWith("}")) {
			this.codeType = (String) ExpressionEvaluatorManager.evaluate("codeType", codeType, Object.class, this,
					pageContext);
		}
	}

	public void setEmptyOption(String emptyOption) throws JspException {
		this.emptyOption = emptyOption;
		if (emptyOption != null && emptyOption.startsWith("${") && emptyOption.endsWith("}")) {
			this.emptyOption = (String) ExpressionEvaluatorManager.evaluate("emptyOption", emptyOption, Object.class,
					this, pageContext);
		}
	}

	public void setLanguage(String language) throws JspException {
		this.language = language;
		if (language != null && language.startsWith("${") && language.endsWith("}")) {
			this.language = (String) ExpressionEvaluatorManager.evaluate("language", language, Object.class, this,
					pageContext);
		}
	}

	public void setRiskCode(String riskCode) throws JspException {
		this.riskCode = riskCode;
		if (riskCode != null && riskCode.startsWith("${") && riskCode.endsWith("}")) {
			this.riskCode = (String) ExpressionEvaluatorManager.evaluate("riskCode", riskCode, Object.class, this,
					pageContext);
		}
	}
	
	public void setUseDms(String useDms) throws JspException {
		this.useDms = useDms;
		if (useDms != null && useDms.startsWith("${") && useDms.endsWith("}")) {
			this.useDms = (String) ExpressionEvaluatorManager.evaluate("useDms", useDms, Object.class, this,
					pageContext);
		}
	}
	public void setWithCode(String withCode) throws JspException {
		this.withCode = withCode;
		if (withCode != null && withCode.startsWith("${") && withCode.endsWith("}")) {
			this.withCode = (String) ExpressionEvaluatorManager.evaluate("withCode", withCode, Object.class, this,
					pageContext);
		}
	}
	public void setIgnoreValue(String ignoreValue) throws JspException {
		this.ignoreValue = ignoreValue;
		if (ignoreValue != null && ignoreValue.startsWith("${") && ignoreValue.endsWith("}")) {
			this.ignoreValue = (String) ExpressionEvaluatorManager.evaluate("ignoreValue", ignoreValue, Object.class, this,
					pageContext);
		}
	}
	
}
