package com.picc.common.handler;

import ins.framework.common.ServiceFactory;

import java.util.List;
import java.util.Map;

import com.picc.cxf.schema.model.CommonEnum;
import com.picc.cxf.schema.model.JsonResult;
import com.picc.qp.schema.model.QpTAsyncTaskErr;
import com.picc.qp.schema.model.QpTAsyncTaskSucc;
import com.picc.qp.schema.model.QpTAsyncTaskSuccId;
import com.picc.qp.service.facade.IQpTAsyncTaskErrService;
import com.picc.qp.service.facade.IQpTAsyncTaskSuccService;
import com.picc.qp.util.CopyUtils;

public class UploadFailCaseToCSHandler extends UploadCaseToTashHandlerCommon implements TaskHandler{

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
		IQpTAsyncTaskErrService qpTAsyncTaskErrService = (IQpTAsyncTaskErrService) ServiceFactory.getService("qpTAsyncTaskErrService");
		List<QpTAsyncTaskErr> qpTAsyncTaskErrs = qpTAsyncTaskErrService.getFailTaskListByType(type, pageSize);
		
		for(QpTAsyncTaskErr err :qpTAsyncTaskErrs){
			Map<String,String> params =err.getParams();
			JsonResult result = uploadCaseToJob(params);
			String code = result.getState();
			String errorLog = result.getMsg();
			if(CommonEnum.SUCCESS.getCode().equals(code)){
				IQpTAsyncTaskSuccService qpTAsyncTaskSuccService = (IQpTAsyncTaskSuccService) ServiceFactory.getService("qpTAsyncTaskSuccService");
				QpTAsyncTaskSucc qpTAsyncTaskSucc = new QpTAsyncTaskSucc();
				CopyUtils.copyObject(err, qpTAsyncTaskSucc);
				qpTAsyncTaskSucc.setErrorLog(errorLog);
				QpTAsyncTaskSuccId QpTAsyncTaskSuccId = new QpTAsyncTaskSuccId();
				QpTAsyncTaskSuccId.setTaskId(err.getId().getTaskId());
				qpTAsyncTaskSucc.setId(QpTAsyncTaskSuccId);
				qpTAsyncTaskSucc.setStatus("2");
				qpTAsyncTaskSuccService.saveQpTAsyncTaskSucc(qpTAsyncTaskSucc);
				qpTAsyncTaskErrService.deleteByPK(err.getId());
			}
		}
	}
	
}
