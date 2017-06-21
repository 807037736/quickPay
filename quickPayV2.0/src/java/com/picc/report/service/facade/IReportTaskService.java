package com.picc.report.service.facade;

import java.util.List;

import com.picc.report.schema.model.ReportTask;

/**
 * 报案任务接口
 * @author 
 * 2017年2月21日
 */
public interface IReportTaskService {

	void add(ReportTask reportTask);
	
	void update(ReportTask reportTask);
	
	void deleteById(String id);
	
	List<ReportTask> findReportTaskList(ReportTask reportTask);
	
}
