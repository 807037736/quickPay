package com.picc.report.dao;

import ins.framework.dao.GenericDaoHibernate;

import org.springframework.stereotype.Repository;

import com.picc.report.schema.model.ReportTask;

@Repository("reportTaskDao")
public class ReportTaskDaoHibernate extends GenericDaoHibernate<ReportTask,String>{

}
