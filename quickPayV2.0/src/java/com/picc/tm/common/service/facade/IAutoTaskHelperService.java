package com.picc.tm.common.service.facade;

public interface IAutoTaskHelperService {

	public  void doEndTaskJob(String taskid,String comId,boolean runState);
	public  String doSaveAutoTask(String jsonString,String comId);
	public  String doUpdateAutoTask(String jsonString,String comId);
	public  String doSetValidIndByParam(String param,String validInd,String comId);
}
