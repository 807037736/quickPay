package com.picc.report.service.spring;

import ins.framework.common.QueryRule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.dao.CommonDaoHibernate;
import com.picc.common.dao.SysCommonDaoHibernate;
import com.picc.report.dao.ReportCaseDataDaoHibernate;
import com.picc.report.schema.model.ReportCaseData;
import com.picc.report.service.facade.IReportCaseDataService;

@Service("iReportCaseDataService")
public class ReportCaseDateServiceImpl implements IReportCaseDataService {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SysCommonDaoHibernate sysCommonDao;

	@Autowired
	private CommonDaoHibernate commonDao;
	
	@Autowired
	private ReportCaseDataDaoHibernate reportCaseDataDaoHibernate;

	@Override
	public void add(ReportCaseData reportCaseData) {
		// TODO Auto-generated method stub
		reportCaseDataDaoHibernate.save(reportCaseData);
	}

	@Override
	public void update(ReportCaseData reportCaseData) {
		// TODO Auto-generated method stub
		reportCaseDataDaoHibernate.update(reportCaseData);
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		reportCaseDataDaoHibernate.deleteByPK(ReportCaseData.class, id);
	}

	@Override
	public ReportCaseData findReportCaseDataByCaseId(String caseId) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("caseId", caseId);
		List<ReportCaseData> reportCaseDataEntities = reportCaseDataDaoHibernate.find(queryRule);
		if(reportCaseDataEntities != null && reportCaseDataEntities.size() > 0){
			return reportCaseDataEntities.get(0);
		}
		return null;
	}
	
	@Override
	public ReportCaseData findReportCaseDataByCaseIdAndAccId(String caseId, String accId) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("caseId", caseId);
		queryRule.addEqual("accId", accId);
		List<ReportCaseData> reportCaseDataEntities = reportCaseDataDaoHibernate.find(queryRule);
		if(reportCaseDataEntities != null && reportCaseDataEntities.size() > 0){
			return reportCaseDataEntities.get(0);
		}
		return null;
	}

	@Override
	public ReportCaseData findReportCaseDataByReportNo(String reportNo) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("reportNo", reportNo);
		List<ReportCaseData> reportCaseDataEntities = reportCaseDataDaoHibernate.find(queryRule);
		if(reportCaseDataEntities != null && reportCaseDataEntities.size() > 0){
			return reportCaseDataEntities.get(0);
		}
		return null;
	}

	@Override
	public ReportCaseData findReportCaseDataByTaskDataId(String taskDataId) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("taskDataId", taskDataId);
		List<ReportCaseData> reportCaseDataEntities = reportCaseDataDaoHibernate.find(queryRule);
		if(reportCaseDataEntities != null && reportCaseDataEntities.size() > 0){
			return reportCaseDataEntities.get(0);
		}
		return null;
	}

}
