package com.picc.qp.service.facade;

import ins.framework.common.Page;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.picc.qp.schema.vo.QpTTPResponsibleVO;

public interface IQpTTPResponsibleService {
	
	/**
	 * 定责信息查询
	 * @param qpTTPCaseStatVO
	 * @param filePath	excel模版路径
	 * @return
	 */
	public Page findByQpTTPResponsible(QpTTPResponsibleVO qpTTPResponsible, int pageNo, int pageSize) throws Exception;
	
	/**
	 * 定责合计信息查询
	 * @param qpTTPCaseStatVO
	 * @param filePath	excel模版路径
	 * @return
	 */
	public List findByTotalResponsible() throws Exception;
	
	/**
	 * 定责信息导出
	 * @param qpTTPCaseStatVO
	 * @param filePath	excel模版路径
	 * @return
	 */
	public HSSFWorkbook exportResponsibleExcel(QpTTPResponsibleVO qpTTPResponsible,String filePath)  throws Exception;
}
