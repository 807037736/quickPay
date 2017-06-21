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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.picc.common.dao.CommonDaoHibernate;
import com.picc.common.dao.SysCommonDaoHibernate;
import com.picc.report.dao.ReportTaskDaoHibernate;
import com.picc.report.schema.model.ReportTask;
import com.picc.report.schema.model.ReportTaskFail;
import com.picc.report.service.facade.IReportTaskService;

@Service("iReportTaskService")
public class ReportTaskServiceImpl implements IReportTaskService {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SysCommonDaoHibernate sysCommonDao;

	@Autowired
	private CommonDaoHibernate commonDao;
	
	@Autowired
	private ReportTaskDaoHibernate reportTaskDaoHibernate;

	@Override
	public void add(ReportTask reportTask) {
		// TODO Auto-generated method stub
		reportTaskDaoHibernate.save(reportTask);
	}

	@Override
	public void update(ReportTask reportTask) {
		// TODO Auto-generated method stub
		reportTaskDaoHibernate.update(reportTask);
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		reportTaskDaoHibernate.deleteByPK(ReportTask.class, id);
	}

	@Override
	public List<ReportTask> findReportTaskList(ReportTask reportTask) {
		// TODO Auto-generated method stub
		String sql = "select * from report_task where 1=1 ";
		List<String> param = new ArrayList<String>();
		if(!StringUtils.isEmpty(reportTask.getTaskDataId())){
			sql += "and taskDataId=? ";
			param.add(reportTask.getTaskDataId());
		}
		if(!StringUtils.isEmpty(reportTask.getTaskType())){
			sql += "and taskType=? ";
			param.add(reportTask.getTaskType());
		}
		if(!StringUtils.isEmpty(reportTask.getNotTaskType())){
			sql += "and taskType!=? ";
			param.add(reportTask.getNotTaskType());
		}
		sql += "order by createTime ";
		Page page = sysCommonDao.findBySql(ReportTask.class, sql, 0, 10, param.toArray());
		logger.info("任务查询数据:" + JSONObject.toJSONString(page));
		if(page != null){
			return (List<ReportTask>)page.getResult();
		}
		return null;
	}


}
