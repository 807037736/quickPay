
package com.picc.qp.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.picc.common.utils.ToolsUtils;
import com.picc.qp.schema.model.QpTICCompany;
import com.picc.qp.schema.model.QpTTPFastCenter;
import com.picc.qp.schema.model.QpTTPLaw;
import com.picc.qp.schema.vo.QpSelectDataVo;
import com.picc.qp.schema.vo.QpTTPCaseStatTempVO;
import com.picc.qp.schema.vo.QpTTPCaseStatVO;
import com.picc.qp.service.facade.IQpTCommonService;
import com.picc.qp.service.facade.IQpTICCompanyService;
import com.picc.qp.service.facade.IQpTTPCaseStatService;
import com.picc.qp.service.facade.IQpTTPFastCenterService;
import com.picc.qp.service.facade.IQpTTPLawService;
import com.picc.um.schema.model.UmTUser;
import com.picc.um.schema.vo.SessionUser;
import com.picc.um.service.facade.IUmTUserService;

@SuppressWarnings("serial")
public class QpTTPCaseStatAction extends Struts2Action{
	
	private IQpTTPCaseStatService qpTTPCaseStatService;	
	
	
	public IQpTTPCaseStatService getQpTTPCaseStatService() {
		return qpTTPCaseStatService;
	}

	public void setQpTTPCaseStatService(IQpTTPCaseStatService qpTTPCaseStatService) {
		this.qpTTPCaseStatService = qpTTPCaseStatService;
	}


	private QpTTPCaseStatVO qpTTPCaseStatVO;
	
	private IQpTCommonService qpTCommonService;
	private IQpTICCompanyService qpTICCompanyService;
	private IQpTTPFastCenterService qpTTPFastCenterService;
	private IQpTTPLawService qpTTPLawService;
	
	protected IUmTUserService umTUserService;
	
	public IUmTUserService getUmTUserService() {
		return umTUserService;
	}

	public void setUmTUserService(IUmTUserService umTUserService) {
		this.umTUserService = umTUserService;
	}
	

	
	/** 操作类型 **/
	private String operateType;
	
	public QpTTPCaseStatVO getQpTTPCaseStatVO() {
		return qpTTPCaseStatVO;
	}

	public void setQpTTPCaseStatVO(QpTTPCaseStatVO qpTTPCaseStatVO) {
		this.qpTTPCaseStatVO = qpTTPCaseStatVO;
	}

	/** operateType getter/setter **/
	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	
	public IQpTCommonService getQpTCommonService() {
		return qpTCommonService;
	}

	public void setQpTCommonService(IQpTCommonService qpTCommonService) {
		this.qpTCommonService = qpTCommonService;
	}


	public IQpTICCompanyService getQpTICCompanyService() {
		return qpTICCompanyService;
	}

	public void setQpTICCompanyService(IQpTICCompanyService qpTICCompanyService) {
		this.qpTICCompanyService = qpTICCompanyService;
	}

	public IQpTTPFastCenterService getQpTTPFastCenterService() {
		return qpTTPFastCenterService;
	}

	public void setQpTTPFastCenterService(
			IQpTTPFastCenterService qpTTPFastCenterService) {
		this.qpTTPFastCenterService = qpTTPFastCenterService;
	}

	
	public IQpTTPLawService getQpTTPLawService() {
		return qpTTPLawService;
	}

	public void setQpTTPLawService(IQpTTPLawService qpTTPLawService) {
		this.qpTTPLawService = qpTTPLawService;
	}



	/** 主键对象 */
	private String caseId;
	public String getCaseId() {
		return this.caseId;
	}
	
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	
	/****************************Function Start*******************************/
	
	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 */
	public String prepareQuery() throws Exception {		
		// 身份证类型
				List<QpSelectDataVo> identifyTypeList = qpTCommonService
						.getSelectData("IdentifyType");
				this.getRequest().setAttribute("identifyTypeList", identifyTypeList);
				// 天气情况
				List<QpSelectDataVo> weatherList = qpTCommonService
						.getSelectData("WeatherType");
				this.getRequest().setAttribute("weatherList", weatherList);
				// 快陪中心
				QpTTPFastCenter qpTTPFastCenter = new QpTTPFastCenter();
		        qpTTPFastCenter.setValidStatus("1");
				List<QpTTPFastCenter> fastCenterList = qpTTPFastCenterService.findByQpTTPFastCenter(qpTTPFastCenter);
				  // 设置默认值
		        SessionUser sessionUser = getUserFromSession();
		        qpTTPCaseStatVO = new QpTTPCaseStatVO();
		        qpTTPCaseStatVO.setCenterId(sessionUser.getCenterId());           // 默认受理点
				this.getRequest().setAttribute("fastCenterList", fastCenterList);
				// 保险公司
				List<QpTICCompany> qpTICCompanyList = qpTICCompanyService
						.findByQpTICCompany(new QpTICCompany(), 1, 1000).getResult();
				this.getRequest().setAttribute("qpTICCompanyList", qpTICCompanyList);
				// 法律法规
				List<QpTTPLaw> qpTTPLawList = qpTTPLawService.findByQpTTPLaw(new QpTTPLaw(), 1, 1000).getResult();
				this.getRequest().setAttribute("qpTTPLawList", qpTTPLawList);
				
				//受理时间
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			    Calendar nowDate=Calendar.getInstance();//获取当前日期 
			    nowDate.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
			    String nowDatefirstDay = sdf.format(nowDate.getTime());
				qpTTPCaseStatVO.setTPHandleTimeStart(nowDatefirstDay);

		return SUCCESS;
	}
	
	/**
	 * QpTTPCase查询，根据qpTTPCase带过来的值为查询条件进行查询。
	 * 
	 * @return
	 */
	public String query() throws Exception {
		try {
			if (page == 0) {
				page = 1;
			}
			if (rows == 0) {
				rows = 10;
			}
			Page resultPage = null;
			List<QpTTPCaseStatTempVO> tempList = (List<QpTTPCaseStatTempVO>)this.getRequest().getSession().getAttribute("tempList");
			//没有数据，返回NULL
			if(tempList ==null){
				return NONE;
			}
			//分页
			List<QpTTPCaseStatTempVO> dataList = new ArrayList<QpTTPCaseStatTempVO>();
			int count = tempList.size();
			int j=1;
			for(int i= (page-1) * rows;i<count;i++){
				j++;
				if(j>rows){
					break;
				}
				dataList.add(tempList.get(i));
			}
			resultPage = new Page(page, tempList.size(), rows, dataList);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	
	
	/**
	 * 删除FdTCase信息
	 * 
	 * @return
	 */
	public String getFieldList() throws Exception {
		try {
			Map resultMap = new HashMap();
			//设置默认市
			qpTTPCaseStatVO.setCaseCity(getUserFromSession().getCity());
			HashMap rMap =  qpTTPCaseStatService.getFieldList(qpTTPCaseStatVO);
			
			this.getRequest().getSession().setAttribute("fieldList", rMap.get("fieldList"));
			this.getRequest().getSession().setAttribute("tempList",  rMap.get("tempList"));
			
			resultMap.put("fieldList", rMap.get("fieldList"));
			this.writeAjaxByMap(resultMap);
		}
		catch(Exception e) {
			e.printStackTrace();
			logger.error("", e);
			Map resultMap = new HashMap();
			resultMap.put("errorTitle", "错误信息(ERROR)：");
			resultMap.put("errorMsg", "查询数据时发生异常！");
			this.writeAjaxErrorByMap(resultMap);
		}
		return NONE;
	}
	
	/**
	 * 明细表下载
	 * 
	 * @throws Exception
	 */
	public void expExcel() throws Exception {
		HttpServletResponse response = getResponse();
		String[] fieldAarr = (String[])this.getRequest().getSession().getAttribute("fieldList");
		List<QpTTPCaseStatTempVO> tempList = (List<QpTTPCaseStatTempVO>)this.getRequest().getSession().getAttribute("tempList");
		
		List<String> fieldList = new ArrayList<String>();
		for(int i=0;i<fieldAarr.length;i++){
			fieldList.add(fieldAarr[i]);
		}
		HSSFWorkbook wb = qpTTPCaseStatService.getDownLoadWB(fieldList,tempList);
		// 页面直接提示下载信息
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + ToolsUtils.changeFileName("*.xls"));
		OutputStream os = response.getOutputStream();
		wb.write(os);
		os.close();
	}
	
	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 */
	public String doingTaskPrepareQuery() throws Exception {
		qpTTPCaseStatVO = new QpTTPCaseStatVO();
		// 初始化受理时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	    String now = sdf.format(new Date(System.currentTimeMillis()));
		qpTTPCaseStatVO.setTPHandleTimeStart(now);
		this.getRequest().setAttribute("qpTTPCaseStatVO", qpTTPCaseStatVO);
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String doingTaskQuery() throws Exception {
		try {
			if (page == 0) {
				page = 1;
			}
			if (rows == 0) {
				rows = 10;
			}
			Page resultPage = null;
			List<QpTTPCaseStatTempVO> tempList = (List<QpTTPCaseStatTempVO>) this.getRequest().getSession().getAttribute("tempList");
			//没有数据，返回NULL
			if(tempList ==null){
				return NONE;
			}
			//分页
			List<QpTTPCaseStatTempVO> dataList = new ArrayList<QpTTPCaseStatTempVO>();
			int count = tempList.size();
			int j=1;
			for(int i= (page-1) * rows;i<count;i++){
				j++;
				if(j>rows){
					break;
				}
				dataList.add(tempList.get(i));
			}
			resultPage = new Page(page, tempList.size(), rows, dataList);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	
	
	public String getDoingTaskFieldList() throws Exception {
		try {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			String userCode = ((SessionUser) this.getRequest().getSession().getAttribute("SessionUser")).getUserCode();
			UmTUser umTUser = umTUserService.findUmTUserByUserCode(userCode);
			qpTTPCaseStatVO.setCoId(umTUser.getCoId());
			HashMap<String, Object> rMap = qpTTPCaseStatService.getDoingTaskStat(
					qpTTPCaseStatVO);
			this.getRequest().getSession()
					.setAttribute("fieldList", rMap.get("fieldList"));
			this.getRequest().getSession()
					.setAttribute("tempList", rMap.get("tempList"));
			resultMap.put("fieldList", rMap.get("fieldList"));
			this.writeAjaxByMap(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("errorTitle", "错误信息(ERROR)：");
			resultMap.put("errorMsg", "查询数据时发生异常！");
			this.writeAjaxErrorByMap(resultMap);
		}
		return NONE;
	}

}
