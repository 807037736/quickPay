/**
 * 
 */
package com.picc.common.tag.ce;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.picc.platform.dms.service.facade.CodeService;

/**
 * 代码翻译Tag
 */
@SuppressWarnings("serial")
public class CodeTag extends TagSupport {
	protected String codeType;
	protected String codeCode;
	protected String language;
	protected String riskCode;
	protected CodeService codeService;
	
	public CodeTag() {
	}

	@Override
	public int doStartTag() throws JspException {
		if (language == null) {
			language = "C";
		}
		if (riskCode == null) {
			riskCode = "PUB";
		}
		if (codeCode != null) {
			codeCode = codeCode.trim();
		}
		if (codeService == null) {
			WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext
					.getServletContext());
			codeService = (CodeService) wac.getBean("codeService");
		}
		String codeName = null;
		if("UserCode".equals(codeType))
		{
			codeName = codeService.translateUserCode(codeType, codeCode, riskCode, language);
		}
		else if("ComCode".equals(codeType))
		{
			try {
				codeName = codeService.translateComCode(codeType, codeCode, riskCode, language);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
		{
			codeName = codeService.translateCode(codeType, codeCode, riskCode, language);
		}
		JspWriter writer = pageContext.getOut();
		try {
			if (codeName != null) {
				writer.write(codeName);
			} else {
				writer.write("null");
			}
		} catch (IOException e) {
			throw new JspException(e.toString());
		}
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		codeType = null;
		codeCode = null;
		language = null;
		riskCode = null;
		return EVAL_PAGE;
	}

	public void setCodeCode(String codeCode) throws JspException {
		this.codeCode = codeCode;
		if (codeCode != null && codeCode.startsWith("${") && codeCode.endsWith("}")) {
			this.codeCode = (String) ExpressionEvaluatorManager.evaluate("codeCode", codeCode, Object.class, this,
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
	
}
