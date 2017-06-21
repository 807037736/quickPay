package com.picc.report.quartz;

import ins.framework.common.ServiceFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.picc.common.handler.TaskHandler;
import com.picc.report.service.facade.IReportService;

/**
 * 处理报案失败、上传单证失败的定时任务
 * 2017年2月22日
 */
public class ReportFailTaskHandler implements TaskHandler{
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final long serialVersionUID = 1L;
	
	@Override
	public void execute() throws Exception {
		IReportService reportService = (IReportService)ServiceFactory.getService("iReportService");
		reportService.reportFailTask();
	}

}
