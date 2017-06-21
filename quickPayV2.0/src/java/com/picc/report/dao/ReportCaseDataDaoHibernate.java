package com.picc.report.dao;

import ins.framework.dao.GenericDaoHibernate;

import org.springframework.stereotype.Repository;

import com.picc.report.schema.model.ReportCaseData;

@Repository("reportCaseDataDao")
public class ReportCaseDataDaoHibernate extends GenericDaoHibernate<ReportCaseData,String>{

}
