package com.picc.report.service.facade;

import java.util.List;

import com.picc.report.schema.model.ReportTaskFail;

public interface IReportTaskFailService {

	void add(ReportTaskFail reportTaskFail);
	
	void update(ReportTaskFail reportTaskFail);
	
	void deleteById(String id);
	
	List<ReportTaskFail> findReportTaskFailList(ReportTaskFail reportTaskFail);
	
}
