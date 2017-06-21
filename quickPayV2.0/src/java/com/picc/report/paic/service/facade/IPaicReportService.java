package com.picc.report.paic.service.facade;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.picc.qp.schema.model.QpTICAccident;
import com.picc.qp.schema.model.QpTTPCase;

public interface IPaicReportService {

	JSONObject toPaicReport(QpTTPCase qpTTPCase, List<QpTICAccident> qpTICAccident, String token);
	
	JSONObject reportPaicUpload(String taskDataId,String token, String taskType);
}
