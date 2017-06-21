package com.picc.tm.common.service.spring;

import ins.framework.cache.CacheManager;
import ins.framework.cache.CacheService;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;

import com.picc.tm.common.service.facade.IAutoTaskHelperService;
import com.picc.tm.common.service.facade.ITmTAppEnvironmentService;
import com.picc.tm.common.service.facade.ITmTAppServiceConfigService;
/**
 * 自动任务辅助类
 * @author moxiaoguang 更新 2013-12-12
 *
 */
@Service("autoTaskHelperService")
public class AutoTaskHelperServiceSpringImpl implements IAutoTaskHelperService {
	protected final Logger logger = (Logger) LoggerFactory.getLogger(this
			.getClass());
	private static CacheService cacheService = CacheManager.getInstance("AutoTaskHelperCache");
	@Autowired
	private ITmTAppEnvironmentService tmTAppEnvironmentService;
	@Autowired
	private ITmTAppServiceConfigService tmTAppServiceConfigService;
	
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getComId() {
		return comId;
	}
	public void setComId(String comId) {
		this.comId = comId;
	}
	public String getWsUrl() {
		return wsUrl;
	}
	public void setWsUrl(String wsUrl) {
		this.wsUrl = wsUrl;
	}
	/** 自动任务Id */
	private String taskId;
	/** 分区使用comId*/
	private String comId;
	/** 需要返回值的webservice */
	private String wsUrl ;
	/**
	 * 回调变换自动任务状态
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public  void doEndTaskJob(String taskId,String comId,boolean runState)
	{
		String envCode  = tmTAppEnvironmentService.getEnvironmentCode(comId);
		String wsUrl = tmTAppServiceConfigService.getServiceUrl("AutoTaskChangeStatusService", envCode);
//		String wsUrl = "http://58.1.30.67:7002/autoTask/webservice/autoTaskChangeStatusService?wsdl";
//		JaxWsDynamicClientFactory dcf = null;
//		try {
//			dcf = JaxWsDynamicClientFactory.newInstance();
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
		
		String method = "changeAutoTaskStatus";//webservice的方法名
		try {
//			Client client = dcf.createClient(wsUrl);
			Client client = (Client) cacheService.getCache("AutoTaskChangeStatusService");
			if(null==client) {
				JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
				client =  dcf.createClient(wsUrl);
				cacheService.putCache("AutoTaskChangeStatusService",client);
			}
			Object[] res = null;
			String status = runState ? "F" : "E";
			res = client.invoke(method, new Object[] { taskId, status });// 调用webservice
		} catch (Exception e) {
			logger.info("无法连接webservice,处理任务处理结果返回状态");
//			System.out.println("无法连接webservice,处理任务处理结果返回状态");
		}
	}
	/**
	 * 保存自动任务
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public  String doSaveAutoTask(String jsonString,String comId)
	{
		String envCode  = tmTAppEnvironmentService.getEnvironmentCode(comId);
		String wsUrl = tmTAppServiceConfigService.getServiceUrl("AutoTaskWebService", envCode);
//		JaxWsDynamicClientFactory dcf = null;
//		try {
//			dcf = JaxWsDynamicClientFactory.newInstance();
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
		
		String method = "saveAutoTask";//webservice的方法名
		try {
//			Client client = dcf.createClient(wsUrl);
			Client client = (Client) cacheService.getCache("AutoTaskWebService");
			if(null==client) {
				JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
				client =  dcf.createClient(wsUrl);
				cacheService.putCache("AutoTaskWebService",client);
			}
			Object[] res = null;
			res = client.invoke(method,jsonString );// 调用webservice
			return res[0].toString();
		} catch (Exception e) {
			logger.info("无法连接webservice,无法保存自动任务记录");
//			System.out.println("无法连接webservice,无法保存自动任务记录");
		}
		return new StringBuffer("{'result':1,'errorCode':110,'errorMsg':'系统拥堵，发送失败','body':{}}").toString();
	}
	/**
	 * 更新自动任务
	 * @author moxiaoguang 更新 2013-12-12
	 */
	public  String doUpdateAutoTask(String jsonString,String comId)
	{
		String envCode  = tmTAppEnvironmentService.getEnvironmentCode(comId);
		String wsUrl = tmTAppServiceConfigService.getServiceUrl("AutoTaskWebService", envCode);
//		JaxWsDynamicClientFactory dcf = null;
//		try {
//			dcf = JaxWsDynamicClientFactory.newInstance();
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
		
		String method = "updateAutoTask";//webservice的方法名
		try {
//			Client client = dcf.createClient(wsUrl);
			Client client = (Client) cacheService.getCache("AutoTaskWebService");
			if(null==client) {
				JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
				client =  dcf.createClient(wsUrl);
				cacheService.putCache("AutoTaskWebService",client);
			}
			Object[] res = null;
			res = client.invoke(method,jsonString );// 调用webservice
			return res[0].toString();
		} catch (Exception e) {
			logger.info("无法连接webservice,无法保存自动任务记录");
//			System.out.println("无法连接webservice,无法保存自动任务记录");
		}
		return new StringBuffer("{'result':1,'errorCode':110,'errorMsg':'系统拥堵，发送失败','body':{}}").toString();
	}
	
	/**
	 *  入参解释：
     *  param：业务参数，自动任务中会根据param来查询需要置为有效或无效的记录
     *  validInd:为“0”或者“1”，传入“0”则表示置为无效，“1”则表示置为有效。
     *  comId:8位机构码
     *  
  	 * 	返回值：是一个json格式的String
     * 	如果入参validInd不在“0”“1”中返回：{'result':1,'errorCode':110,'errorMsg':'传入的有效性参数只能为0或者1,'body':}}"
     * 	如果调用成功："{'result':0,'errorCode':0,'errorMsg':'','body':{'msgId':'999'}}"
     * 	如果调用失败："{'result':1,'errorCode':110,'errorMsg':'系统拥堵，发送失败','body':{}}"
	 *	@author moxiaoguang 更新 2013-12-12
	 */
	public  String doSetValidIndByParam(String param,String validInd,String comId)
	{
		if((!"0".equals(validInd))&&(!"1".equals(validInd))){
			return new StringBuffer("{'result':1,'errorCode':110,'errorMsg':'传入的有效性参数只能为0或者1,'body':{}}").toString();
		}
		String envCode  = tmTAppEnvironmentService.getEnvironmentCode(comId);
		String wsUrl = tmTAppServiceConfigService.getServiceUrl("AutoTaskWebService", envCode);
		/*JaxWsDynamicClientFactory dcf = null;
		try {
			dcf = JaxWsDynamicClientFactory.newInstance();
		} catch (Exception e1) {
			e1.printStackTrace();
		}*/
		
		String method = "setValidIndByParam";//webservice的方法名
		try {
//			Client client = dcf.createClient(wsUrl);
			Client client = (Client) cacheService.getCache("AutoTaskWebService");
			if(null==client) {
				JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
				client =  dcf.createClient(wsUrl);
				cacheService.putCache("AutoTaskWebService",client);
			}
			Object[] res = null;
			res = client.invoke(method,param,validInd,comId );// 调用webservice
			return res[0].toString();
		} catch (Exception e) {
			logger.info("无法连接webservice,无法保存自动任务记录");
//			System.out.println("无法连接webservice,无法保存自动任务记录");
		}
		return new StringBuffer("{'result':1,'errorCode':110,'errorMsg':'系统拥堵，发送失败','body':{}}").toString();
	}
	
}
