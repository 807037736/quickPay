package com.picc.qp.service.wx.facade;

import ins.framework.common.Page;

import com.picc.qp.schema.model.WxTaskFinish;


public interface WxTaskFinishService {
	
	Page getPage(WxTaskFinish wxTaskFinish, Integer pageNo, Integer pageSize) throws Exception;
	
	void addWxTaskFinish(WxTaskFinish wxTaskFinish);
	
	WxTaskFinish findWxTaskFinishById(Integer finishId);
	
	WxTaskFinish findWxTaskFinishByCaseId(String caseId);
}
