package com.picc.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.picc.common.dao.CommonDaoHibernate;
import com.picc.common.utils.AppConfig;
import com.picc.common.utils.StringUtils;
import com.picc.qp.schema.model.QpTCOMDictionary;
import com.picc.qp.schema.model.QpTCOMDistrict;
import com.picc.qp.service.facade.IQpTCOMDictionaryService;
import com.picc.qp.service.facade.IQpTCOMDistrictService;
import com.picc.qp.util.Constants;
import com.picc.tm.common.schema.model.TmTAppServiceConfig;
import com.picc.tm.common.schema.model.TmTAppServiceConfigId;
import com.picc.tm.common.schema.model.TmTSystemConfig;
import com.picc.tm.common.service.facade.ITmTAppServiceConfigService;
import com.picc.tm.common.service.facade.ITmTSystemConfigService;
import com.picc.tm.schema.model.TmTApplicationConfig;
import com.picc.tm.service.facade.ITmTApplicationConfigService;
import com.picc.um.cognos.service.facade.ICognosService;
import com.picc.um.cognos.shema.CognosEnvironmentModel;
import com.picc.um.log.schema.model.LoGTTYPE;
import com.picc.um.log.service.facade.ILoGTTYPEService;
import com.picc.um.schema.model.UmTAccount;
import com.picc.um.schema.model.UmTMethodTask;
import com.picc.um.schema.model.UmTRole;
import com.picc.um.schema.model.UmTWhiteList;
import com.picc.um.schema.vo.RoleDimeVo;
import com.picc.um.service.facade.IUmTAccountService;
import com.picc.um.service.facade.IUmTMethodTaskService;
import com.picc.um.service.facade.IUmTRoleService;
import com.picc.um.service.facade.IUmTWhiteListService;

import ins.framework.cache.CacheManager;
import ins.framework.cache.CacheService;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.common.ServiceFactory;
import net.sf.json.JSONArray;

public class StartupListener implements ServletContextListener {

	private static final Logger logger = LoggerFactory.getLogger(StartupListener.class);

	private final static JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory
			.newInstance();

	static {
		CacheManager.setCacheType(2);
	}

	private static CacheService urlRoleCacheService = CacheManager
			.getInstance("UM_T_URLROLE");

	private static CacheService cognosConfigCacheService = CacheManager
			.getInstance("COGNOS_CONFIG");

	private static CacheService whiteListCacheService = CacheManager
			.getInstance("UM_T_WHITELIST");

	private static CacheService openedListCacheService = CacheManager
			.getInstance("UM_T_OPENEDLIST");

	private static CacheService roleDimeCacheService = CacheManager
			.getInstance("UM_T_ROLEDIME"); 

	private static CacheService LogTypeCacheService = CacheManager.getInstance("LOG_T_TYPE");

	private static CacheService applicationCacheService = CacheManager.getInstance("TM_T_ApplicationConfig"); 
	//接口缓存对象
	private static CacheService interfaceCacheService = CacheManager.getInstance("INTERFACEMANAGE");

	public void contextDestroyed(ServletContextEvent sce) {
	}

	public void contextInitialized(ServletContextEvent sce) {
		// 初始化ServiceFactory
		ServiceFactory.initServiceFactory(sce.getServletContext());
		// 设置查询优化
		// EntityDaoHibernate.setOptimizeFind(true);

		// ActivityConfigReader.reader();

		/** 初始化系统常量 **/
		initSysConst(sce.getServletContext());


		/*** 初始化WebService本地生成文件类型 **/
		//saveRemoteWSDLToLocale();
		/** 初始化角色与功能权限对应数据 **/
		initPowerCache();

		/** 初始化角色维度缓存信息 **/
		intRoleDimeCache();

		/** 初始化Cognos各地市环境信息 **/
		//initCognosEnvironment();

		/** 加载访问地址白名单信息 **/
		initWhiteList();
		/** 加载系统公开出来的名单信息**/
		initOpenedList();
		/** 加载日志监控类型 **/
		initLogTypeList();	
		/**加载对外发布接口信息**/
		//initInterfaceVisitList();
		/** 初始化应用配置，用于登录页面跳转、菜单获取等 **/
		//initApplicationCache();
		/**初始化java webservice**/
		//initJavaWebService();
		System.gc();
		// 初始化系统路径
		String webAppRootKey = sce.getServletContext().getRealPath("/");  
		System.setProperty("webapp.root" , webAppRootKey);
		// 初始化长沙市所有管辖区
		initChangshaAreaList();
	}

	/**

	/**
	 * 初始化权限数据
	 * 
	 * @throws Exception
	 *             2013-8-13下午2:20:52 jiangweiyang
	 */
	private static void initPowerCache() {
		/** 初始化角色、流程URL对应关系 **/
		IUmTMethodTaskService methodTaskService = (IUmTMethodTaskService) ServiceFactory
				.getService("umTMethodTaskService");
		// 获取所有有效的角色功能对应关系列表
		List<UmTMethodTask> roleTaskList = methodTaskService
				.getValidMethodTaskList();
		/** 角色 **/
		IUmTRoleService umTRoleService = (IUmTRoleService) ServiceFactory
				.getService("umTRoleService");
		// 提取所有有效的角色信息
		List<UmTRole> roleList = umTRoleService.findValidUmTRoleList(); // 获取有效的角色信息
		if (roleList == null) {
			System.err.println("没有有效的角色数据");
		} else if (roleTaskList == null) {
			System.err.println("没有有效的角色功能数据");
		} else {
			urlRoleCacheService.clearCacheManager("UM_T_URLROLE"); // 将URL与角色对应缓存对象删除处理
			/**
			 * roleUrlMap
			 * 层级关系 map--role:map(url:task)
			 */
			Map<String, HashMap<String,UmTMethodTask>> roleUrlMap = new HashMap<String, HashMap<String,UmTMethodTask>>();
			List<String> urlList = null;
			HashMap<String,UmTMethodTask> taskMap = null;
			String roleId = null, roleCode = null;
			String visitURL = null;
			for (UmTRole roleValue : roleList) {
				roleCode = roleValue.getRoleCode();
				roleId = roleValue.getId().getRoleId(); // 直接获取角色ID
				//urlList = new ArrayList<String>();
				taskMap = new HashMap<String,UmTMethodTask>();
				for (UmTMethodTask method : roleTaskList) {
					if (roleId.equals(method.getRoleId())) {
						visitURL = method.getMethodCode();
						if(visitURL.indexOf("?")!=-1){
							visitURL = visitURL.substring(0, visitURL.indexOf("?"));
						}
						//						urlList.add(visitURL);
						taskMap.put(visitURL, method);
					}
				}
				if (taskMap != null && taskMap.size() > 0) {
					roleUrlMap.put(roleCode, taskMap);
				}
			}
			/** 将所有角色与URL的关系进行反处理 **/
			UmTMethodTask method = null;
			HashMap<String,UmTMethodTask> roleTaskMap = null;
			for (String roleCodeValue : roleUrlMap.keySet()) {//遍历角色-urlmap
				taskMap = roleUrlMap.get(roleCodeValue); // 获取当前UrlList
				for (String url:taskMap.keySet()) {
					if (urlRoleCacheService.containsKey(url)) {
						roleTaskMap = (HashMap<String,UmTMethodTask>)urlRoleCacheService.getCache(url);
					}else{
						roleTaskMap = new HashMap<String,UmTMethodTask>();
					}
					roleTaskMap.put(roleCodeValue, taskMap.get(url));
					urlRoleCacheService.putCache(url, roleTaskMap);
				}
			}
			roleUrlMap = null;
			urlList = null;
			roleTaskList = null;
			roleList = null;
		}
	}

	/**
	 * 初始化角色维度表里面的
	 * 
	 * 2013-9-12下午1:31:09 jiangweiyang
	 */
	@SuppressWarnings("unchecked")
	public static void intRoleDimeCache() {
		CommonDaoHibernate commonDao = (CommonDaoHibernate) ServiceFactory
				.getService("commonDao");
		try {
			Page page = commonDao
					.findObjectPageBySql(
							"select a.dimensionvalue,c.rolecode,b.methodcode,a.operatetype from "
									+ "um_t_roledime a,um_t_methodtask b,um_t_role c where a.roleid = c.roleid and a.taskid = b.taskid and "
									+ "a.dimensioncode = 'comcode' and a.validstatus = '1' and b.validstatus = '1' and c.validstatus = '1'",
									RoleDimeVo.class, 1, Integer.MAX_VALUE);
			List<RoleDimeVo> roleDimeList = (List<RoleDimeVo>) page.getResult(); // 获取所有符合条件的角色维护扩展信息
			Map<String, Map<String, String>> urlRoleMap = null;
			Map<String, String> operateMap = null;
			for (RoleDimeVo roleDime : roleDimeList) {
				if ((urlRoleMap = (Map<String, Map<String, String>>) roleDimeCacheService
						.getCache(roleDime.getDimensionValue())) == null) {
					// 缓存对象中没有数据
					operateMap = new HashMap<String, String>();
					operateMap.put(roleDime.getRoleCode(),
							roleDime.getOperateType());
					urlRoleMap = new HashMap<String, Map<String, String>>();
					urlRoleMap.put(roleDime.getMethodCode(), operateMap);
					roleDimeCacheService.putCache(roleDime.getDimensionValue(),
							urlRoleMap);
				} else {
					if (urlRoleMap.keySet().contains(roleDime.getMethodCode())) {
						operateMap = urlRoleMap.get(roleDime.getMethodCode());
						operateMap.put(roleDime.getRoleCode(),
								roleDime.getOperateType());
						urlRoleMap.put(roleDime.getMethodCode(), operateMap);
						roleDimeCacheService.putCache(
								roleDime.getDimensionValue(), urlRoleMap);
					} else {
						operateMap = new HashMap<String, String>();
						operateMap.put(roleDime.getRoleCode(),
								roleDime.getOperateType());
						urlRoleMap.put(roleDime.getMethodCode(), operateMap);
						roleDimeCacheService.putCache(
								roleDime.getDimensionValue(), urlRoleMap);
					}
				}
			}
			//System.out.println("SIZE:" + roleDimeCacheService.size());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}
	}

	/**
	 * 初始化系统常量 2013-8-28上午9:27:08
	 * 
	 * @author shenyichan
	 */
	private static void initSysConst(ServletContext context) {
		try {
			IQpTCOMDictionaryService  iQpTCOMDictionaryService = (IQpTCOMDictionaryService) ServiceFactory.getService("iQpTCOMDictionaryService");
			List<QpTCOMDictionary> imagePaths = iQpTCOMDictionaryService.getImagePath("IMAGE");
			logger.info(JSONArray.fromObject(imagePaths).toString());
			
			for (QpTCOMDictionary qpTCOMDictionary : imagePaths) {
				if("img_path".equals(qpTCOMDictionary.getId().getCode())){
					Constants.IMAGE_PATH.put("imagePath", qpTCOMDictionary.getValue());
					logger.info("初始化image地址完成");
					logger.info(Constants.getIMAGEPATH());
				}
				if("image_http_path".equals(qpTCOMDictionary.getId().getCode())){
					Constants.IMAGE_PATH.put("imageHttpPath", qpTCOMDictionary.getValue());
					logger.info("初始化image http地址完成");
					logger.info(Constants.getIMAGEHTTPPATH());
				}
				if("image_http_query".equals(qpTCOMDictionary.getId().getCode())){
					Constants.IMAGE_PATH.put("imageHttpQuery", qpTCOMDictionary.getValue());
					logger.info("初始化image query地址完成");
					logger.info(Constants.getIMAGEHTTPQUERY());
				}
				if("img_user".equals(qpTCOMDictionary.getId().getCode())){
					Constants.setIMG_USER( qpTCOMDictionary.getValue());
					logger.info("初始化IMG_USER地址完成");
					logger.info(Constants.getIMG_USER());
				}
				if("img_user_key".equals(qpTCOMDictionary.getId().getCode())){
					Constants.setIMG_USER_KEY(qpTCOMDictionary.getValue());
					logger.info("初始化IMG_USER_KEY地址完成");
					logger.info(Constants.getIMG_USER_KEY());
				}
				if("img_plat".equals(qpTCOMDictionary.getId().getCode())){
					Constants.setIMG_PLAT(qpTCOMDictionary.getValue());
					logger.info("初始化IMG_PLAT地址完成");
					logger.info(Constants.getIMG_PLAT());
				}
			}

			List<QpTCOMDictionary> weixins = iQpTCOMDictionaryService.getImagePath("WEIXIN");
			if(StringUtils.isEmptyStr(weixins)){
				logger.error("初始化失败，appid appSecret 未配置");
			}else{
				for (QpTCOMDictionary qpTCOMDictionary : weixins) {
					System.out.println(qpTCOMDictionary.getId().getCode());
					if("appId".equals(qpTCOMDictionary.getId().getCode())){
						Constants.WX_ACCESS_TOKEN.put("JSSDK_APPID", qpTCOMDictionary.getValue());
						logger.info("初始化APPID地址完成");
						logger.info(Constants.WX_ACCESS_TOKEN.get("JSSDK_APPID").toString());
					}
					if("appSecret".equals(qpTCOMDictionary.getId().getCode())){
						Constants.WX_ACCESS_TOKEN.put("JSSDK_APPSECRET", qpTCOMDictionary.getValue());
						logger.info("初始化APPSECRET地址完成");
						logger.info(Constants.WX_ACCESS_TOKEN.get("JSSDK_APPSECRET").toString());
					}
					if("wx_url".equals(qpTCOMDictionary.getId().getCode())){
						Constants.setWX_URL(qpTCOMDictionary.getValue());
						logger.info("初始化WX_URL地址完成");
						logger.info(Constants.getWX_URL());
					}

					if("template_url".equals(qpTCOMDictionary.getId().getCode())){
						Constants.setTEMPLATEID_URL(qpTCOMDictionary.getValue());
						
						logger.info("初始化template_url地址完成");
						logger.info(Constants.getTEMPLATEID_URL());
						logger.info(qpTCOMDictionary.getValue());
					}
					
					if("accept_templateId".equals(qpTCOMDictionary.getId().getCode())){
						Constants.setACCEPT_TEMPLATEID(qpTCOMDictionary.getValue());

						logger.info("初始化accept_templateId地址完成");
						logger.info(Constants.getACCEPT_TEMPLATEID());
						logger.info(qpTCOMDictionary.getValue());
					}
					
					if("finish_templateId".equals(qpTCOMDictionary.getId().getCode())){
						Constants.setFINISH_TEMPLATEID(qpTCOMDictionary.getValue());
						
						logger.info("初始化accept_templateId地址完成");
						logger.info(Constants.getFINISH_TEMPLATEID());
						logger.info(qpTCOMDictionary.getValue());
					}
					if("acceptTemplate_url".equals(qpTCOMDictionary.getId().getCode())){
						Constants.setACCEPTTEMPLATE_URL(qpTCOMDictionary.getValue());
						
						logger.info("初始化acceptTemplate_url地址完成");
						logger.info(Constants.getACCEPTTEMPLATE_URL());
						logger.info(qpTCOMDictionary.getValue());
					}
					if("finishTemplate_url".equals(qpTCOMDictionary.getId().getCode())){
						Constants.setFINISHTEMPLATE_URL(qpTCOMDictionary.getValue());
						
						logger.info("初始化finishTemplate_url地址完成");
						logger.info(Constants.getFINISHTEMPLATE_URL());
						logger.info(qpTCOMDictionary.getValue());
					}
				}
			}

			List<QpTCOMDictionary> smss = iQpTCOMDictionaryService.getImagePath("SMS");
			for (QpTCOMDictionary qpTCOMDictionary : smss) {
				if("sms_url".equals(qpTCOMDictionary.getId().getCode())){
					Constants.setSMS_URL(qpTCOMDictionary.getValue());
					logger.info("初始化sms_url完成");
					logger.info(qpTCOMDictionary.getValue());
				}
			}

			List<QpTCOMDictionary> egoverments = iQpTCOMDictionaryService.getImagePath("EGOVERMENT");
			for (QpTCOMDictionary qpTCOMDictionary : egoverments) {
				if("async_url".equals(qpTCOMDictionary.getId().getCode())){
					Constants.setASYNC_URL(qpTCOMDictionary.getValue());
					logger.info("初始化一路通同步地址完成");
					logger.info(qpTCOMDictionary.getValue());
				}
			}

			String filePath = context.getInitParameter("sysconstDirLocation");
			if (filePath != null && filePath.length() > 0) {
				filePath = context.getRealPath(filePath);
				AppConfig.configure(filePath);
			}
			/**
			 * 
		    logger.info("初始化微信AccessToken");
		    String appId = Constants.getJSSDK_APPID();
			String appSecret = Constants.getJSSDK_APPSECRET();
			try {
				Map<String, Object> map = Constants.getWX_ACCESS_TOKEN();
				JSONObject accessToken = Sign.getAccessToken(appId, appSecret);
				map.put("token", accessToken);
				JSONObject ticket = Sign.getJsApiTicket(accessToken.getString("access_token"));
				map.put("ticket", ticket);
				System.out.println(accessToken);
				System.out.println(ticket);
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("初始化微信AccessToken失败");
			}
			 */
			List<QpTCOMDictionary> seals = iQpTCOMDictionaryService.getImagePath("SEAL");
			if(StringUtils.isEmptyStr(seals)){
				logger.error("初始化电子印章配置失败");
			}else{
				for (QpTCOMDictionary qpTCOMDictionary : seals) {
					if("rootPath".equals(qpTCOMDictionary.getId().getCode())){
						Constants.SEALS.put("rootPath", qpTCOMDictionary.getValue());
					}
					if("certPath".equals(qpTCOMDictionary.getId().getCode())){
						Constants.SEALS.put("certPath", qpTCOMDictionary.getValue());
					}
					if("signImage".equals(qpTCOMDictionary.getId().getCode())){
						Constants.SEALS.put("signImage", qpTCOMDictionary.getValue());
					}
					if("certPwd".equals(qpTCOMDictionary.getId().getCode())){
						Constants.SEALS.put("certPwd", qpTCOMDictionary.getValue());
					}
					if("serverType".equals(qpTCOMDictionary.getId().getCode())){
						Constants.SEALS.put("serverType", qpTCOMDictionary.getValue());
					}
					if("ruleInfo".equals(qpTCOMDictionary.getId().getCode())){
						Constants.SEALS.put("ruleInfo", qpTCOMDictionary.getValue());
					}
				}
			}
			
			
			List<QpTCOMDictionary> report = iQpTCOMDictionaryService.getImagePath("REPORT_INTERFACE");
			if(StringUtils.isEmptyStr(report)){
				logger.error("初始报案对接链接失败");
			}else{
				//开始初始化报案配置
				logger.info("开始初始化报案配置");
				Map<String, String> reportMap = new HashMap<String, String>();
				for (QpTCOMDictionary qpTCOMDictionary : report) {
					reportMap.put(qpTCOMDictionary.getId().getCode().toString(), qpTCOMDictionary.getValue());
				}
				Constants.setREPORT_INTERFACE(reportMap);
				logger.info("报案map配置信息:"+reportMap.toString());
				
				
//				for (QpTCOMDictionary qpTCOMDictionary : report) {
//					if("PAIC_report".equals(qpTCOMDictionary.getId().getCode())){
//						Constants.setPAIC_URL(qpTCOMDictionary.getValue());;
//						logger.info("初始化PAIC地址完成");
//						logger.info(Constants.getPAIC_URL());
//					}
//					if("PAIC_userName".equals(qpTCOMDictionary.getId().getCode())){
//						Constants.setPAIC_USERNAME(qpTCOMDictionary.getValue());
//						logger.info("初始化PAIC_USERNAME完成");
//						logger.info(Constants.getPAIC_USERNAME());
//					}
//					if("PAIC_passWord ".equals(qpTCOMDictionary.getId().getCode())){
//						Constants.setPAIC_PASSWORD(qpTCOMDictionary.getValue());
//						logger.info("初始化PAIC_PASSWORD完成");
//						logger.info(Constants.getPAIC_PASSWORD());
//					}
//					if("PICC_report".equals(qpTCOMDictionary.getId().getCode())){
//						Constants.setPICC_URL(qpTCOMDictionary.getValue());;
//						logger.info("初始化PICC地址完成");
//						logger.info(Constants.getPICC_URL());
//					}
//					if("PICC_userName".equals(qpTCOMDictionary.getId().getCode())){
//						Constants.setPICC_USERNAME(qpTCOMDictionary.getValue());
//						logger.info("初始化PICC_USERNAME完成");
//						logger.info(Constants.getPICC_USERNAME());
//					}
//					if("PICC_passWord ".equals(qpTCOMDictionary.getId().getCode())){
//						Constants.setPICC_PASSWORD(qpTCOMDictionary.getValue());
//						logger.info("初始化PICC_PASSWORD完成");
//						logger.info(Constants.getPICC_PASSWORD());
//					}
//				}
			}
			
//			List<QpTCOMDictionary> companyUrl = iQpTCOMDictionaryService.getImagePath("COMPANY_URL");
//			logger.info(JSONArray.fromObject(companyUrl).toString());
//			
//			HashMap<String, Object> companyUrlMap = new HashMap<String, Object>();
//			for(QpTCOMDictionary qpTCOMDictionary : companyUrl){
//				companyUrlMap.put(qpTCOMDictionary.getId().toString(), qpTCOMDictionary.getValue());
//				logger.info(qpTCOMDictionary.getId().toString()+":"+qpTCOMDictionary.getValue());
//			}
//			Constants.setCOMPANY_URL(companyUrlMap);
//			logger.info("初始化各保险公司接口地址完成");
			
			
//			List<QpTCOMDictionary> reportChannels = iQpTCOMDictionaryService.getImagePath("REPORT_CHANNEL");
//			if(StringUtils.isEmptyStr(reportChannels)){
//				logger.error("初始报案对接开关失败");
//			}else{
//				Map<String, Object> reportChannel = Constants.getREPORT_CHANNEL();
//				//开始初始化报案配置
//				logger.info("开始初始化报案开关配置");
//				for (QpTCOMDictionary qpTCOMDictionary : reportChannels) {
//					reportChannel.put(qpTCOMDictionary.getId().getCode(), qpTCOMDictionary.getValue());
//				}
//				logger.info("初始化报案开关完成"+reportChannel);
//			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("初始化报案开关失败", e);
		}
	}

	/**
	 * 初始化Cognos各地的环境变量信息 2013-9-5上午11:15:55 jiangweiyang
	 */
	@SuppressWarnings("unchecked")
	private static void initCognosEnvironment() {
		ITmTSystemConfigService systemConfigService = (ITmTSystemConfigService) ServiceFactory
				.getService("tmTSystemConfigService");
		TmTSystemConfig systemConfig = new TmTSystemConfig();
		systemConfig.setValidStatus(AppConfig.get("sysconst.VALID"));
		systemConfig.setConfigName("COGNOS");
		try {
			//	1.获取配置的Cognos菜单信息
			cognosConfigCacheService.clearCacheManager("COGNOS_CONFIG");
			List<TmTSystemConfig> systemConfigList = systemConfigService
					.findByTmTSystemConfig(systemConfig, 1, Integer.MAX_VALUE)
					.getResult();
			// 清除所有的缓存信息
			CognosEnvironmentModel model = null;
			for (TmTSystemConfig systemConfigVar : systemConfigList) {
				// 遍历全国的Cognos配置变量
				if (cognosConfigCacheService
						.containsKey(cognosConfigCacheService.generateCacheKey(
								"CONFIG", systemConfigVar.getComId()))) {
					// 已经有对象
					model = (CognosEnvironmentModel) cognosConfigCacheService
							.getCache(cognosConfigCacheService
									.generateCacheKey("CONFIG",
											systemConfigVar.getComId()));
				} else {
					model = CognosEnvironmentModel.class.newInstance(); // 重新初始化对象
				}
				for (Field field : CognosEnvironmentModel.class
						.getDeclaredFields()) {
					if (field.getName().equalsIgnoreCase(
							systemConfigVar.getConfigCode())) {
						field.setAccessible(true);
						field.set(model, systemConfigVar.getConfigValue());
					}
				}
				cognosConfigCacheService.putCache(cognosConfigCacheService
						.generateCacheKey("CONFIG",
								systemConfigVar.getComId()), model);
			}

			//2.根据最大权限的用户获取所有Cognos菜单集合
			//进行各地市配置数据处理
			IUmTAccountService accountService = (IUmTAccountService) ServiceFactory.getService("umTAccountService");;
			UmTAccount  account = null;
			ICognosService cognosService = null;
			String key = null;
			CognosEnvironmentModel modelValue = null;
			Set<String> citySet = new HashSet<String>();
			for(TmTSystemConfig systemConfigVar : systemConfigList){
				if(!citySet.contains(systemConfigVar.getComId())){
					citySet.add(systemConfigVar.getComId());
					key = cognosConfigCacheService.generateCacheKey("CONFIG",systemConfigVar.getComId());
					modelValue = (CognosEnvironmentModel)cognosConfigCacheService.getCache(key);
					account = accountService.findUmTAccountByUserCode(modelValue.getDefaultUserCode());
					cognosService = (ICognosService)ServiceFactory.getService("cognosService");
					//System.out.println(account.getId().getUserCode()+"\t"+account.getPassword());
					try{
						cognosService.getCognosMaxMenu(account);
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}


			//3.将各地市对应的外网地址提取出来
			ITmTAppServiceConfigService tmTAppServiceConfigService = (ITmTAppServiceConfigService)ServiceFactory.getService("tmTAppServiceConfigService");
			TmTAppServiceConfig serviceConfig = new TmTAppServiceConfig();
			TmTAppServiceConfigId id = new TmTAppServiceConfigId();
			id.setServerCode("environmentAddress*");
			serviceConfig.setId(id);
			QueryRule rule = QueryRuleHelper.generateQueryRule(serviceConfig);
			List<TmTAppServiceConfig> configList = tmTAppServiceConfigService.findListByQueryRule(rule);
			String configKey = null;
			List<String> ipList = null;
			for(TmTAppServiceConfig config : configList){
				configKey = cognosConfigCacheService.generateCacheKey("NET",config.getComId());				//生成缓存对应的ID主键
				if(cognosConfigCacheService.getCache(configKey)==null){
					ipList = new ArrayList<String>();
				} else {
					ipList =  (List<String>)cognosConfigCacheService.getCache(configKey);
				}
				ipList.add(config.getServerIp());
				cognosConfigCacheService.putCache(configKey, ipList);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("", e);
		}
	}

	/**
	 * 初始化访问白名单配置信息 2013-9-10上午10:09:29 jiangweiyang
	 */
	@SuppressWarnings("unchecked")
	public static void initWhiteList() {
		IUmTWhiteListService whiteListService = (IUmTWhiteListService) ServiceFactory
				.getService("umTWhiteListService");
		UmTWhiteList whiteList = new UmTWhiteList();
		whiteList.setValidStatus("1"); // 查询有效状态的白名单配置信息
		try {
			List<UmTWhiteList> resultList = whiteListService
					.findByUmTWhiteList(whiteList, 1, Integer.MAX_VALUE)
					.getResult();
			//System.out.println("whiteList size:" + resultList.size());
			for (UmTWhiteList wl : resultList) {
				whiteListCacheService.putCache(wl.getVisitUrl(),
						wl.getVisitUrl());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("", e);
		}
	}

	/**
	 * 初始化系统公开访问的链接信息 2014-8-10上午10:09:29 liuyatao
	 */
	@SuppressWarnings("unchecked")
	public static void initOpenedList() {
		IUmTMethodTaskService umTMethodTaskService = (IUmTMethodTaskService) ServiceFactory
				.getService("umTMethodTaskService");
		try {
			List<UmTMethodTask> resultList = umTMethodTaskService.getOpenedMethodTaskList();
			//System.out.println("whiteList size:" + resultList.size());
			//初始化一个，避免后续缓存为空的查询
			openedListCacheService.putCache("openedListCacheService", new UmTMethodTask());
			for (UmTMethodTask openedTask : resultList) {
				//以url为key，task对象对value
				openedListCacheService.putCache(openedTask.getMethodCode(),openedTask);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("", e);
		}
	}

	public static void initLogTypeList(){
		ILoGTTYPEService loGTTYPEService = (ILoGTTYPEService)ServiceFactory.getService("loGTTYPEService");
		try {
			List<LoGTTYPE> logTypeList = loGTTYPEService.findAllLogTypeList();
			//System.out.println("logTypeList size:" + logTypeList.size());
			for(LoGTTYPE lt:logTypeList){
				LogTypeCacheService.putCache(lt.getOperateMethod(), lt);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("", e);
		}

	}

	public static void initApplicationCache(){
		ITmTApplicationConfigService tmTApplicationConfigService = (ITmTApplicationConfigService)ServiceFactory.getService("tmTApplicationConfigService");

		TmTApplicationConfig tmTApplicationConfig = new TmTApplicationConfig();
		Page page = null;
		try{
			page = tmTApplicationConfigService.findByTmTApplicationConfig(tmTApplicationConfig, 1, 100); 
		}catch(Exception e){
			e.printStackTrace();
			logger.error("", e);
		}
		if(page!=null){
			List<TmTApplicationConfig> tmTApplicationConfigList = page.getResult();
			if (tmTApplicationConfigList != null && tmTApplicationConfigList.size() > 0) {
				for (TmTApplicationConfig tmTApplicationConfigTmp : tmTApplicationConfigList) {
					if(tmTApplicationConfigTmp.getServerContext()!=null&&!"".equals(tmTApplicationConfigTmp.getServerContext())){
						applicationCacheService.putCache(tmTApplicationConfigTmp.getServerContext(), tmTApplicationConfigTmp);
					}
				}
			}
		}
	}
	public static void initJavaWebService(){
		//Endpoint.publish("http://58.1.32.86:8080/Hello", new UnicomPayServiceSpringImpl());  
	}
	public static void main(String [] args ){
		String userType = "01";
		System.out.println(Integer.valueOf(userType));
	}
	
	/**
	 * 初始化长沙市的所有管辖区
	 */
	public void initChangshaAreaList() {
		IQpTCOMDistrictService qpTCOMDistrictService = (IQpTCOMDistrictService) ServiceFactory.getService("qpTCOMDistrictService");
		QpTCOMDistrict queryQpTCOMDistrict = new QpTCOMDistrict();
		queryQpTCOMDistrict.setCityId("430100");
		try {
			List<QpTCOMDistrict> qpTCOMDistricts = qpTCOMDistrictService.findByQpTCOMDistrict(queryQpTCOMDistrict);
			for (QpTCOMDistrict d : qpTCOMDistricts) {
				Constants.getChangshaAreaMap().put(d.getDistrictName(), d.getDistrictId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
