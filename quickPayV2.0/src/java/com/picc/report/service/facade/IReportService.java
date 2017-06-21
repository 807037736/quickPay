package com.picc.report.service.facade;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.picc.qp.schema.model.QpTICAccident;
import com.picc.qp.schema.model.QpTTPCase;

public interface IReportService {

	//taskType 1报案   2单证和groupId图片 3surveyGroupID图片  4案件信息修改单证
	
	/**
	 * 报案任务
	 */
	void report();
	
	/**
	 * 认字上传认为
	 */
	void reportsUpload();
	
	/**
	 * 失败任务
	 */
	void reportFailTask();
	
	/**
	 * 通用获取责任认定书
	 * @param caseId
	 * @return
	 */
	JSONObject getCaseInfoPic(String caseId);
	
	/**
	 * 通用获取定责图片
	 * @param caseId
	 * @return
	 */
	JSONObject getAccidentPicUrl(String caseId, String taskType);
	
	/**
	 * 通用报案接口
	 * @return
	 */
	JSONObject toGeneralReport(QpTTPCase qpTTPCase, List<QpTICAccident> qpTICAccidents); 
	
	/**
	 * 通用单证上传接口
	 * @return
	 */
	JSONObject toGeneralUpload(String taskDataId, String taskType); 
}
