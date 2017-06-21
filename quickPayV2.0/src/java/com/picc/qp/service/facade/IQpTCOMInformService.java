package com.picc.qp.service.facade;

import ins.framework.common.Page;

import java.util.Date;
import java.util.List;

import com.picc.qp.schema.model.QpTCOMInform;

public interface IQpTCOMInformService {
	
	public Page findQpTCOMInformByQpTCOMInform(QpTCOMInform qpTCOMInform, int pageNo, int pageSize) throws Exception;
	
	public List<QpTCOMInform> findByQpTCOMInform(QpTCOMInform qpTCOMInform) throws Exception;
	
	public List<QpTCOMInform> findQpTCOMInformByState(String state) throws Exception;
	
	public void addQpTCOMInform(QpTCOMInform qpTCOMInform) throws Exception;
	
	public void deleteQpTCOMInform(int informId) throws Exception;
	
	public void deleteBySQL(String state) throws Exception;
	
	public void updateQpTCOMInform(QpTCOMInform qpTCOMInform) throws Exception;

	public void updateBySQL(String state) throws Exception;
	
	public boolean isTimeOut(Date date ,String endTime) throws Exception;
	
	public void updateByPk(int informId) throws Exception;
	
	public List<QpTCOMInform> getNearTimeInform(List<QpTCOMInform> informs) throws Exception;
}
