package com.picc.report.picc.service.facade;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.picc.qp.schema.model.QpTICAccident;
import com.picc.qp.schema.model.QpTTPCase;

/**
 * 人保报案服务接口
 * @author ff
 * 2017年3月18日
 */
public interface IPiccReportService {

	/**
	 * 人保报案
	 * @param qpTTPCase
	 * @param qpTICAccident
	 * @return
	 */
	JSONObject toPiccReport(QpTTPCase qpTTPCase, List<QpTICAccident> qpTICAccident);
	
	/**
	 * 人保单证上传
	 * @param taskDataId
	 * @return
	 */
	JSONObject reportPiccUpload(String taskDataId, String taskType);
}
