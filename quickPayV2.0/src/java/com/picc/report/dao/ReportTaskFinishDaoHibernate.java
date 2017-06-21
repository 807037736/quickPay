package com.picc.report.dao;

import ins.framework.dao.GenericDaoHibernate;

import org.springframework.stereotype.Repository;

import com.picc.report.schema.model.ReportTaskFinish;

@Repository("reportTaskFinishDao")
public class ReportTaskFinishDaoHibernate extends GenericDaoHibernate<ReportTaskFinish,String>{

}
