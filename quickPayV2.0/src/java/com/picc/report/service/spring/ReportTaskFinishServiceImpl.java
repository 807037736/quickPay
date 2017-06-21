package com.picc.report.service.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.dao.CommonDaoHibernate;
import com.picc.common.dao.SysCommonDaoHibernate;
import com.picc.report.dao.ReportTaskFinishDaoHibernate;
import com.picc.report.schema.model.ReportTaskFinish;
import com.picc.report.service.facade.IReportTaskFinishService;

@Service("iReportTaskFinishService")
public class ReportTaskFinishServiceImpl implements IReportTaskFinishService {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SysCommonDaoHibernate sysCommonDao;

	@Autowired
	private CommonDaoHibernate commonDao;
	
	@Autowired
	private ReportTaskFinishDaoHibernate reportTaskFinishDaoHibernate;
	
	@Override
	public void add(ReportTaskFinish reportTaskFinish) {
		// TODO Auto-generated method stub
		reportTaskFinishDaoHibernate.save(reportTaskFinish);
	}

}
