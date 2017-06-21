package com.picc.report.quartz;

import ins.framework.common.ServiceFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.picc.common.handler.TaskHandler;
import com.picc.report.service.facade.IReportService;

/**
 * 报案定时任务
 * 2017年2月21日
 */
public class ReportHandler implements TaskHandler{

	private static final long serialVersionUID = 1L;

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void execute() throws Exception {
		IReportService reportService = (IReportService)ServiceFactory.getService("iReportService");
		reportService.report();
	}

}
