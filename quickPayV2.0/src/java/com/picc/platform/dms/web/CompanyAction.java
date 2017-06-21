package com.picc.platform.dms.web;

import ins.framework.web.Struts2Action;

import java.util.List;

import com.picc.platform.dms.service.facade.ICompanyService;

@SuppressWarnings("serial")
public class CompanyAction extends Struts2Action {
	
	/** 机构服务 */
	private ICompanyService companyService;
	private List<String> comCodes;
	private String comCode;
	
	
	
	public List<String> getComCodes() {
		return comCodes;
	}

	public void setComCodes(List<String> comCodes) {
		this.comCodes = comCodes;
	}

	public ICompanyService getCompanyService() {
		return companyService;
	}

	public void setCompanyService(ICompanyService companyService) {
		this.companyService = companyService;
	}

	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	/**
	 * 查机构树
	 * @return
	 */
	public String queryCompanyTree() {
		if(comCode==null || "".equals(comCode)){
			comCode = getSession().getAttribute("ComCode").toString();
		}
		String queryType = getRequest().getParameter("queryType");
		this.renderText(companyService.findComTreeJsonByQueryType(comCode,queryType,comCodes));
		return NONE;
	}
}
