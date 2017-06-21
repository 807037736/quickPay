package com.picc.qp.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.picc.common.utils.ToolsUtils;
import com.picc.qp.schema.vo.QpTTPResponsibleVO;
import com.picc.qp.service.facade.IQpTTPResponsibleService;

public class QpTTPResponsibleAction extends Struts2Action{
	
	private IQpTTPResponsibleService qpTTPResponsibleService;
	
	private QpTTPResponsibleVO qpTTPResponsible;
	
	
	
	public QpTTPResponsibleVO getQpTTPResponsible() {
		return qpTTPResponsible;
	}
	public void setQpTTPResponsible(QpTTPResponsibleVO qpTTPResponsible) {
		this.qpTTPResponsible = qpTTPResponsible;
	}
	public IQpTTPResponsibleService getQpTTPResponsibleService() {
		return qpTTPResponsibleService;
	}
	public void setQpTTPResponsibleService(IQpTTPResponsibleService qpTTPResponsibleService) {
		this.qpTTPResponsibleService = qpTTPResponsibleService;
	}
	
	
	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 */
	public String prepareQuery() throws Exception {
		    
		   // HttpServletRequest httpServletRequest = this.getRequest();
		   
		   // httpServletRequest.setAttribute("Responsible", httpServletRequest.getParameter("Responsible"));

			return SUCCESS;
	}
	
	
	/**
	 * 定责统计查询。
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
			Page resultPage =  qpTTPResponsibleService.findByQpTTPResponsible(qpTTPResponsible, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	
	
	
	/**
	 * 定责合计统计查询。
	 * 
	 * @return
	 */
	public String queryTotal() throws Exception {

		try {
			qpTTPResponsibleService.findByTotalResponsible();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	

	
	/**
	 * 导出Excel
	 * 
	 * @return
	 */
	public void exportResponsibleExcel() throws Exception {
		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 20;
		}
		HttpServletResponse response = getResponse();
		 //excel模板路径
		String filePath = getServletContext().getRealPath("/") + "/template/exportResponsibleExcelTemp.xls";
		HSSFWorkbook wb = qpTTPResponsibleService.exportResponsibleExcel(qpTTPResponsible, filePath);
		// 页面直接提示下载信息
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + ToolsUtils.changeFileName("*.xls"));
		OutputStream os = response.getOutputStream();
		wb.write(os);
		os.close();
	}
	
}
