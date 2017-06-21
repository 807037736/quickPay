package com.picc.report.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.picc.common.dao.CommonDaoHibernate;
import com.picc.common.dao.SysCommonDaoHibernate;
import com.picc.report.dao.ReportTaskFailDaoHibernate;
import com.picc.report.schema.model.ReportTaskFail;
import com.picc.report.service.facade.IReportTaskFailService;

@Service("iReportTaskFailService")
public class ReportTaskFailServiceImpl implements IReportTaskFailService {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SysCommonDaoHibernate sysCommonDao;

	@Autowired
	private CommonDaoHibernate commonDao;
	
	@Autowired
	private ReportTaskFailDaoHibernate reportTaskFailDaoHibernate;
	
	@Override
	public void add(ReportTaskFail reportTaskFail) {
		// TODO Auto-generated method stub
		reportTaskFailDaoHibernate.save(reportTaskFail);
	}

	@Override
	public void update(ReportTaskFail reportTaskFail) {
		// TODO Auto-generated method stub
		reportTaskFailDaoHibernate.update(reportTaskFail);
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		reportTaskFailDaoHibernate.deleteByPK(ReportTaskFail.class, id);
	}

	@Override
	public List<ReportTaskFail> findReportTaskFailList(ReportTaskFail reportTaskFail) {
		// TODO Auto-generated method stub
		
		String sql = "select * from report_task_fail where 1=1 ";
		List<String> param = new ArrayList<String>();
		if(!StringUtils.isEmpty(reportTaskFail.getTaskDataId())){
			sql += "and taskDataId=? ";
			param.add(reportTaskFail.getTaskDataId());
		}
		if(!StringUtils.isEmpty(reportTaskFail.getTaskType())){
			sql += "and taskType=? ";
			param.add(reportTaskFail.getTaskType());
		}
		if(!StringUtils.isEmpty(reportTaskFail.getIsTask())){
			sql += "and isTask=? ";
			param.add(reportTaskFail.getIsTask());
		}
		sql += "order by errorTime ";
		Page page = sysCommonDao.findBySql(ReportTaskFail.class, sql, 0, 10, param.toArray());
		logger.info("失败任务数据:"+JSONObject.toJSONString(page));
		if(page != null){
			return (List<ReportTaskFail>)page.getResult();
		}
		return null;
	}
	
}
