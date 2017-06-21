package com.picc.report.dao;

import ins.framework.dao.GenericDaoHibernate;

import org.springframework.stereotype.Repository;

import com.picc.report.schema.model.ReportTaskFail;

@Repository("reportTaskFailDao")
public class ReportTaskFailDaoHibernate extends GenericDaoHibernate<ReportTaskFail,String>{

}
