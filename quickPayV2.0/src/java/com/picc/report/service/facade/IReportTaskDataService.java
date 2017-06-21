package com.picc.report.service.facade;

import com.picc.report.schema.model.ReportTaskData;

public interface IReportTaskDataService {

	void add(ReportTaskData reportTaskData);
	
	void update(ReportTaskData reportTaskData);
	
//	void deleteById(String id);
	
	ReportTaskData findReportTaskDataByTaskDataId(String taskDataId);
	
	ReportTaskData findReportTaskDataByAccId(String accId);
}
