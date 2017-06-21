package com.picc.common.schedule;

import ins.framework.common.ServiceFactory;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.picc.common.handler.TaskHandler;
import com.picc.common.utils.StringUtils;

public class AsyncTaskService {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void executeAsyncTask(String args){
		System.out.println(new Date());
		System.out.println(args);
		try{
			if(StringUtils.isNotEmpty(args)){
				TaskHandler handler = (TaskHandler) ServiceFactory.getService(args);
				handler.execute();
//				Method method = handler.getClass().getMethod("execute");
//				method.invoke(handler);
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
}
