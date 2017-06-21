package com.picc.common.handler;

import ins.framework.common.ServiceFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.picc.cxf.schema.model.CommonEnum;
import com.picc.cxf.schema.model.JsonResult;
import com.picc.qp.schema.model.QpTAsyncTask;
import com.picc.qp.schema.model.QpTAsyncTaskErr;
import com.picc.qp.schema.model.QpTAsyncTaskErrId;
import com.picc.qp.schema.model.QpTAsyncTaskId;
import com.picc.qp.schema.model.QpTAsyncTaskSucc;
import com.picc.qp.schema.model.QpTAsyncTaskSuccId;
import com.picc.qp.service.facade.IQpTAsyncTaskErrService;
import com.picc.qp.service.facade.IQpTAsyncTaskService;
import com.picc.qp.service.facade.IQpTAsyncTaskSuccService;
import com.picc.qp.util.CopyUtils;

public class UploadCaseToCSHandler extends UploadCaseToTashHandlerCommon  implements TaskHandler{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6787238734608020697L;
	
	public static final String  type = "UPLOADCASETOCSJJ";
	
	private static final Integer pageSize = 50;
	
	public void execute() throws Exception {
		if (!isOpen()) {
			return;
		}
		IQpTAsyncTaskService qpTAsyncTaskService = (IQpTAsyncTaskService) ServiceFactory.getService("qpTAsyncTaskService");
		if(qpTAsyncTaskService!=null){
			List<QpTAsyncTask> taskList = qpTAsyncTaskService.getTaskListByType(type, pageSize);
			for(QpTAsyncTask task :taskList){
				task.setStartTime(new Date());
				Map<String,String> params = task.getParams();
				JsonResult result = uploadCaseToJob(params);
				task.setFinishTime(new Date());
				String code = result.getState();
				String errorLog = result.getMsg();
				String errorData = result.getData().toString();
				task.setErrorLog(errorLog+",data:"+errorData);
				task.setUploadTimes(1);
				//判断成功  成功执行
				if(CommonEnum.SUCCESS.getCode().equals(code)){
					IQpTAsyncTaskSuccService qpTAsyncTaskSuccService = (IQpTAsyncTaskSuccService) ServiceFactory
							.getService("qpTAsyncTaskSuccService");
					QpTAsyncTaskSucc qpTAsyncTaskSucc = new QpTAsyncTaskSucc();
					CopyUtils.copyObject(task, qpTAsyncTaskSucc);
					QpTAsyncTaskSuccId QpTAsyncTaskSuccId = new QpTAsyncTaskSuccId();
					QpTAsyncTaskSuccId.setTaskId(task.getId().getTaskId());
					qpTAsyncTaskSucc.setId(QpTAsyncTaskSuccId);
					qpTAsyncTaskSucc.setStatus("2");
					qpTAsyncTaskSuccService.saveQpTAsyncTaskSucc(qpTAsyncTaskSucc);
					
				}else{
					IQpTAsyncTaskErrService qpTAsyncTaskErrService = (IQpTAsyncTaskErrService) ServiceFactory
							.getService("qpTAsyncTaskErrService");
					QpTAsyncTaskErr qpTAsyncTaskErr = new QpTAsyncTaskErr();
					CopyUtils.copyObject(task, qpTAsyncTaskErr);
					QpTAsyncTaskErrId QpTAsyncTaskErrId = new QpTAsyncTaskErrId();
					QpTAsyncTaskErrId.setTaskId(task.getId().getTaskId());
					qpTAsyncTaskErr.setStatus("3");
					qpTAsyncTaskErr.setId(QpTAsyncTaskErrId);
					qpTAsyncTaskErrService.saveQpTAsyncTaskErr(qpTAsyncTaskErr);
				}
				QpTAsyncTaskId id = new QpTAsyncTaskId();
				id.setTaskId(task.getTaskId());
				qpTAsyncTaskService.deleteByPK(id);
			}
		}	
	}
}
