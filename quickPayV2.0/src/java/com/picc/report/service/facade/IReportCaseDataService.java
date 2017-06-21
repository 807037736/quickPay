package com.picc.report.service.facade;

import com.picc.report.schema.model.ReportCaseData;

public interface IReportCaseDataService {

	void add(ReportCaseData reportCaseData);
	
	void update(ReportCaseData reportCaseData);
	
	void deleteById(String id);
	
	ReportCaseData findReportCaseDataByCaseId(String caseId);
	
	ReportCaseData findReportCaseDataByCaseIdAndAccId(String caseId, String accID);
	
	ReportCaseData findReportCaseDataByTaskDataId(String taskDataId);
	
	ReportCaseData findReportCaseDataByReportNo(String reportNo);
}
