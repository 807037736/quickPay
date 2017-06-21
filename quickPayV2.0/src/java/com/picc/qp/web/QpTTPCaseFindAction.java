package com.picc.qp.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.picc.qp.schema.model.QpTTPCase;
import com.picc.qp.schema.vo.QpSelectDataVo;
import com.picc.qp.service.facade.IQpTCommonService;
import com.picc.qp.service.facade.IQpTFLOWService;
import com.picc.qp.service.facade.IQpTICAccidentService;
import com.picc.qp.service.facade.IQpTICCompanyService;
import com.picc.qp.service.facade.IQpTTPCaseService;
import com.picc.qp.service.facade.IQpTTPFastCenterService;
import com.picc.qp.service.facade.IQpTTPLawService;
import com.sinosoft.sysframework.common.datatype.DateTime;

public class QpTTPCaseFindAction extends Struts2Action {
	
    private IQpTTPCaseService qpTTPCaseService;	
	
	private IQpTCommonService qpTCommonService;
	
	private IQpTTPFastCenterService qpTTPFastCenterService;
	
	private IQpTICAccidentService qpTICAccidentService;
	
	private IQpTICCompanyService qpTICCompanyService;
	
	private IQpTTPLawService qpTTPLawService;
	
	private IQpTFLOWService qpTFLOWService;
	
	private QpTTPCase qpTTPCase;
	
	public IQpTTPCaseService getQpTTPCaseService() {
		return qpTTPCaseService;
	}

	public void setQpTTPCaseService(IQpTTPCaseService qpTTPCaseService) {
		this.qpTTPCaseService = qpTTPCaseService;
	}

	public IQpTCommonService getQpTCommonService() {
		return qpTCommonService;
	}

	public void setQpTCommonService(IQpTCommonService qpTCommonService) {
		this.qpTCommonService = qpTCommonService;
	}

	public IQpTTPFastCenterService getQpTTPFastCenterService() {
		return qpTTPFastCenterService;
	}

	public void setQpTTPFastCenterService(
			IQpTTPFastCenterService qpTTPFastCenterService) {
		this.qpTTPFastCenterService = qpTTPFastCenterService;
	}

	public IQpTICAccidentService getQpTICAccidentService() {
		return qpTICAccidentService;
	}

	public void setQpTICAccidentService(IQpTICAccidentService qpTICAccidentService) {
		this.qpTICAccidentService = qpTICAccidentService;
	}

	public IQpTICCompanyService getQpTICCompanyService() {
		return qpTICCompanyService;
	}

	public void setQpTICCompanyService(IQpTICCompanyService qpTICCompanyService) {
		this.qpTICCompanyService = qpTICCompanyService;
	}

	public IQpTTPLawService getQpTTPLawService() {
		return qpTTPLawService;
	}

	public void setQpTTPLawService(IQpTTPLawService qpTTPLawService) {
		this.qpTTPLawService = qpTTPLawService;
	}

	public IQpTFLOWService getQpTFLOWService() {
		return qpTFLOWService;
	}

	public void setQpTFLOWService(IQpTFLOWService qpTFLOWService) {
		this.qpTFLOWService = qpTFLOWService;
	}

	public QpTTPCase getQpTTPCase() {
		return qpTTPCase;
	}

	public void setQpTTPCase(QpTTPCase qpTTPCase) {
		this.qpTTPCase = qpTTPCase;
	}

	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 */
	public String prepareQuery() throws Exception {
	    HttpServletRequest httpServletRequest = this.getRequest();
	    HttpSession session = httpServletRequest.getSession();
	  //如果存在案件查询的session信息就删除掉
	    if(session.getAttribute("qpqpttpcaseprepareQueryqpTTPCase") != null){
	    	session.removeAttribute("qpqpttpcaseprepareQueryqpTTPCase");
	    }
	    
	    // 身份证类型
        List<QpSelectDataVo> identifyTypeList =  qpTCommonService.getSelectData("IdentifyType");
        httpServletRequest.setAttribute("identifyTypeList", identifyTypeList);
        
        qpTTPCase = new QpTTPCase();
        qpTTPCase.setDriverVehicleNumber("湘");
    	//qpTTPCase.setTpHandleTimeStart(new DateTime(new DateTime(), DateTime.YEAR_TO_MILLISECOND));
    	//如果存在案件查询的session信息就删除掉
    	Object object = session.getAttribute("qpqpttpcasefindprepareQueryqpTTPCase");
		if(object != null){
			session.removeAttribute("qpqpttpcasefindprepareQueryqpTTPCase");
		}
      //  httpServletRequest.setAttribute("qpTTPCase", qpTTPCase);
		return SUCCESS;
	}
	
	
	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 注： 用于综合查询 中  查看案件信息某条 -- 查看 进入详情界面  -- 点击返回调用方法   
	 * 区别： 获取上一次查询的参数 
	 * 
	 * @return
	 */
	public String prepareQuery2() throws Exception {
	    HttpServletRequest httpServletRequest = this.getRequest();
	    HttpSession session = httpServletRequest.getSession();
	    
	    // 身份证类型
        List<QpSelectDataVo> identifyTypeList =  qpTCommonService.getSelectData("IdentifyType");
        httpServletRequest.setAttribute("identifyTypeList", identifyTypeList);
        
        qpTTPCase = new QpTTPCase();
        Object object = session.getAttribute("qpqpttpcasefindprepareQueryqpTTPCase");
		if(object != null){
			httpServletRequest.setAttribute("isReturn", "2");
		    qpTTPCase = (QpTTPCase)object;
		   //格式化时间
            if(qpTTPCase != null){
           	    if(qpTTPCase.getTpHandleTimeStart() != null && !"".equals(qpTTPCase.getTpHandleTimeStart())){
           	    	qpTTPCase.setTpHandleTimeStart(new DateTime(qpTTPCase.getTpHandleTimeStart(), DateTime.YEAR_TO_MILLISECOND));
                }
                if(qpTTPCase.getTpHandleTimeEnd() != null && !"".equals(qpTTPCase.getTpHandleTimeEnd())){
                	qpTTPCase.setTpHandleTimeEnd(new DateTime(qpTTPCase.getTpHandleTimeEnd(), DateTime.YEAR_TO_MILLISECOND));
                }
                if(qpTTPCase.getCaseTimeStart() != null && !"".equals(qpTTPCase.getCaseTimeStart())){
                	qpTTPCase.setCaseTimeStart(new DateTime(qpTTPCase.getCaseTimeStart(), DateTime.YEAR_TO_MILLISECOND));
                }
                if(qpTTPCase.getCaseTimeEnd() != null && !"".equals(qpTTPCase.getCaseTimeEnd())){
                	qpTTPCase.setCaseTimeEnd(new DateTime(qpTTPCase.getCaseTimeEnd(), DateTime.YEAR_TO_MILLISECOND));
                }
            }
        }else {
        	qpTTPCase.setTpHandleTimeStart(new DateTime(new DateTime(), DateTime.YEAR_TO_MILLISECOND));
		}
		if(object != null){
			session.removeAttribute("qpqpttpcasefindprepareQueryqpTTPCase");
		}
      //  httpServletRequest.setAttribute("qpTTPCase", qpTTPCase);
		return SUCCESS;
	}
	
	/**
	 * QpTTPCase查询，根据qpTTPCase带过来的值为查询条件进行查询。
	 * 
	 * @return
	 */
	public String query() throws Exception {
		HttpServletRequest httpServletRequest = this.getRequest();
		HttpSession session = httpServletRequest.getSession();
		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 20;
		}
		try {		
			session.setAttribute("qpqpttpcasefindprepareQueryqpTTPCase", qpTTPCase);
			Page resultPage = qpTTPCaseService.findQpTTPCasePageBySql(qpTTPCase, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	
}
