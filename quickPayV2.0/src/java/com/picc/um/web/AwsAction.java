package com.picc.um.web;

import ins.framework.web.Struts2Action;

import com.picc.um.service.facade.IAwsService;

/**
 * 炎黄服务Action处理层
 * @author 沈一婵
 */
@SuppressWarnings("serial")
public class AwsAction extends Struts2Action {
	
	private IAwsService awsService = null;
	
	private String comCode = null;
	
	private String queryType = null;   //查本身还是子节点
	
	private String  treeType = null;   //树的类型
	
	private String gradeCode = null;
	
	private int start;
	
	private int limit;

	
	
	public IAwsService getAwsService() {
		return awsService;
	}

	public void setAwsService(IAwsService awsService) {
		this.awsService = awsService;
	}
	
	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	public String getQueryType() {
		return queryType;
	}
	
	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	
	public String getGradeCode() {
		return gradeCode;
	}

	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public void query() {
		if(comCode!=null&&queryType!=null){
			if("0".equals(treeType)){
				//只查询机构树
				this.renderText(awsService.getCompanyJsonByUpperCode(comCode, queryType));
			}else if("1".equals(treeType)){
				//查询岗位
				this.renderText(awsService.findGradeJsonListByUpperCode(gradeCode,queryType));
			}else if("2".equals(treeType)){
				//查询机构树与人
				this.renderText(awsService.getUserListByComCode(comCode, queryType, start, limit));
			}
		}else {
			this.renderText("ERROR");
		}
	}
	
	public String tree(){
		return SUCCESS;
	}

}
