package com.picc.report.dao;

import ins.framework.dao.GenericDaoHibernate;

import org.springframework.stereotype.Repository;

import com.picc.report.schema.model.ReportTaskData;

@Repository("reportTaskDataDao")
public class ReportTaskDataDaoHibernate extends GenericDaoHibernate<ReportTaskData,String>{

}
