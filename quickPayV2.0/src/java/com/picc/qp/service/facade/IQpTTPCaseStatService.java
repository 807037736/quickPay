package com.picc.qp.service.facade;

import ins.framework.common.Page;

import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.picc.qp.schema.vo.QpTTPCaseStatTempVO;
import com.picc.qp.schema.vo.QpTTPCaseStatVO;

public interface IQpTTPCaseStatService{
	/**
	 * 根据qpTTPCase和页码信息，获取Page对象
	 * @param qpTTPCase
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTTPCase的Page对象
	 */
	public Page findByQpTTPCase(QpTTPCaseStatVO qpTTPCaseStat, int pageNo, int pageSize) throws Exception;
	public HashMap<String,Object> getFieldList(QpTTPCaseStatVO qpTTPCaseStat);
	public HSSFWorkbook getDownLoadWB(List<String> fieldList, List<QpTTPCaseStatTempVO> tempList) throws Exception;
	public HashMap<String, Object> getDoingTaskStat(QpTTPCaseStatVO qpTTPCaseStat);
}
