package com.picc.qp.service.wx.facade;

import ins.framework.common.Page;

import com.picc.qp.schema.model.WxTask;


public interface WxTaskService {
	
	Page getPage(WxTask wxTask, Integer pageNo, Integer pageSize) throws Exception;
	
	void updateWxTask(WxTask wxTask);
	
	void addWxTask(WxTask wxTask);
	
	void deleteWxTaskById(Integer id);
	
	WxTask findWxTaskById(Integer taskId);
	
	Page getUserCodePage(WxTask wxTask, Integer pageNo, Integer pageSize,String userCode) throws Exception;
	
	WxTask findWxTaskByCaseId(String caseId);
	
}
