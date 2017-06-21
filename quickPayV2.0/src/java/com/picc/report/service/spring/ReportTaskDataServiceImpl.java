package com.picc.report.service.spring;

import ins.framework.common.QueryRule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.dao.CommonDaoHibernate;
import com.picc.common.dao.SysCommonDaoHibernate;
import com.picc.report.dao.ReportTaskDataDaoHibernate;
import com.picc.report.schema.model.ReportTaskData;
import com.picc.report.service.facade.IReportTaskDataService;

@Service("iReportTaskDataService")
public class ReportTaskDataServiceImpl implements IReportTaskDataService {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SysCommonDaoHibernate sysCommonDao;

	@Autowired
	private CommonDaoHibernate commonDao;
	
	@Autowired
	private ReportTaskDataDaoHibernate reportTaskDataDaoHibernate;
	
	@Override
	public void add(ReportTaskData reportTaskData) {
		// TODO Auto-generated method stub
		reportTaskDataDaoHibernate.save(reportTaskData);
	}

	@Override
	public void update(ReportTaskData reportTaskData) {
		// TODO Auto-generated method stub
		reportTaskDataDaoHibernate.update(reportTaskData);
	}

//	@Override
//	public void deleteById(String id) {
//		// TODO Auto-generated method stub
//		reportTaskDataDaoHibernate.deleteByPK(ReportTaskData.class, id);
//	}

	@Override
	public ReportTaskData findReportTaskDataByTaskDataId(String taskDataId) {
		// TODO Auto-generated method stub
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("taskDataId", taskDataId);
		List<ReportTaskData> reportTaskData = reportTaskDataDaoHibernate.find(queryRule);
		return reportTaskData.isEmpty() ? null : reportTaskData.get(0);
	}
	
	@Override
	public ReportTaskData findReportTaskDataByAccId(String accId) {
		// TODO Auto-generated method stub
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("accId", accId);
		List<ReportTaskData> reportTaskData = reportTaskDataDaoHibernate.find(queryRule);
		return reportTaskData.isEmpty() ? null : reportTaskData.get(0);
	}

}
