package com.picc.um.service.spring;

import ins.framework.cache.CacheManager;
import ins.framework.cache.CacheService;
import ins.framework.cache.info.CacheManagerInfo;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.stereotype.Service;

import com.picc.common.QueryRuleHelper;
import com.picc.common.dao.CommonDaoHibernate;
import com.picc.common.utils.AppConfig;
import com.picc.tm.common.dao.TmTAppServiceConfigDaoHibernate;
import com.picc.tm.common.schema.model.TmTAppServiceConfig;
import com.picc.tm.common.schema.model.TmTAppServiceConfigId;
import com.picc.tm.common.schema.model.TmTSystemConfig;
import com.picc.tm.common.service.facade.ITmTAppEnvironmentService;
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
import com.picc.um.schema.model.UmTRoleId;
import com.picc.um.schema.model.UmTWhiteList;
import com.picc.um.schema.vo.RoleDimeVo;
import com.picc.um.service.facade.ICacheService;
import com.picc.um.service.facade.IUmTAccountService;
import com.picc.um.service.facade.IUmTMethodTaskService;
import com.picc.um.service.facade.IUmTRoleService;
import com.picc.um.service.facade.IUmTWhiteListService;

/**
 * 缓存服务接口实现类
 * @author 姜卫洋
 * @modify yaowenfeng
 * 
 */
@Service("cacheService")
public class CacheServiceSpringImpl implements ICacheService {

	/** 用户-角色Cache **/
	private static CacheService userRoleCacheService = CacheManager
			.getInstance("UM_T_USERROLE");

	/**
	 * 请求路径URL-角色 Cache
	 * 层级关系：url --> hashmap --> role -->  task
	 */
	private static CacheService urlRoleCacheService = CacheManager
			.getInstance("UM_T_URLROLE");
	

	/** 
	 * 用户-任务 Cache
	 * 层级关系  usercode --> hashmap --> url --> task
	 **/
	private static CacheService userTaskCacheService = CacheManager
			.getInstance("UM_T_USERTASK");
	
	private static CacheService whiteListCacheService = CacheManager
			.getInstance("UM_T_WHITELIST");
	
	private static CacheService roleDimeCacheService = CacheManager
			.getInstance("UM_T_ROLEDIME"); 
	
	private static CacheService cognosConfigCacheService = CacheManager
			.getInstance("COGNOS_CONFIG");
	
	private static CacheService logTypeCacheService = CacheManager
			.getInstance("LOG_T_TYPE");
	
	private static CacheService applicationCacheService = CacheManager.getInstance("TM_T_APPLICATIONCONFIG"); 
	
	private static Logger logger = LoggerFactory
			.getLogger(CacheServiceSpringImpl.class);

	@Autowired
	private IUmTRoleService umTRoleService = null;
	@Autowired
	private IUmTMethodTaskService umTMethodTaskService = null;
	@Autowired
	private IUmTWhiteListService umTWhiteListService = null;
	@Autowired
	private CommonDaoHibernate commonDao = null;
	@Autowired
	private ITmTSystemConfigService tmTSystemConfigService = null;
	@Autowired
	private TmTAppServiceConfigDaoHibernate tmTAppServiceConfigDao = null;
	@Autowired
	private ITmTAppEnvironmentService tmTAppEnvironmentService = null;
	@Autowired
	private IUmTAccountService umTAccountService = null;
	@Autowired
	private ICognosService cognosService = null;
	@Autowired
	private ITmTAppServiceConfigService tmTAppServiceConfigService = null;
	@Autowired
	private ILoGTTYPEService loGTTYPEService = null;
	@Autowired
	public ITmTApplicationConfigService tmTApplicationConfigService = null;
	

	public CacheManagerInfo findCacheManagerInfoByCacheName(String cacheName) {
		// TODO Auto-generated method stub
		CacheService cacheService = CacheManager.getInstance(cacheName);

		return cacheService.getCacheManagerInfo(cacheName);
	}

	public List<CacheManagerInfo> findAllCacheManager() {
		// TODO Auto-generated method stub
		String[] managerNameArray = CacheManager.getAllCacheManagerName();
		List<CacheManagerInfo> cacheManagerInfoList = new ArrayList<CacheManagerInfo>();
		for (String managerName : managerNameArray) {
			cacheManagerInfoList
					.add(findCacheManagerInfoByCacheName(managerName));
		}

		return cacheManagerInfoList;
	}

	public Page findCacheManager(CacheManagerInfo cacheManagerInfo, int pageNo,
			int pageSize) throws Exception {
		// TODO Auto-generated method stub
		if (pageNo <= 0)
			pageNo = 1;

		if (pageSize == 0)
			pageSize = 10;

		List<CacheManagerInfo> cacheManagerInfoList = findAllCacheManager();
		List<CacheManagerInfo> rcacheManagerInfoList = new ArrayList<CacheManagerInfo>();
		if (!cacheManagerInfo.getCacheName().equals("")) {
			for (CacheManagerInfo cmi : cacheManagerInfoList) {
				if (cmi.getCacheName().contains(
						(cacheManagerInfo.getCacheName()))) {

					rcacheManagerInfoList.add(cmi);
				}
			}
			cacheManagerInfoList=rcacheManagerInfoList;
			
		}

		int startIndex = (pageNo - 1) * pageSize;
		int endIndex = pageNo * pageSize;
		if (endIndex > cacheManagerInfoList.size()) {
			endIndex = cacheManagerInfoList.size();
		}
		List<CacheManagerInfo> resultList = new ArrayList<CacheManagerInfo>();
		for (int i = startIndex; i < endIndex; ++i) {
			resultList.add(cacheManagerInfoList.get(i));
		}
		Page page = new Page(startIndex, cacheManagerInfoList.size(), pageSize,
				resultList);
		return page;
	}

	public void deleteCacheManager(CacheManagerInfo cacheManagerInfo) {
		// TODO Auto-generated method stub
		CacheService cacheService = CacheManager.getInstance(cacheManagerInfo
				.getCacheName());
		cacheService.clearCacheManager(cacheManagerInfo.getCacheName());

	}
	
	/**
	 * 重新加载应用配置缓存
	 * 
	 *@author yaowenfeng
	 */
	public void reloadApplicationCache() {
		
		TmTApplicationConfig tmTApplicationConfig = new TmTApplicationConfig();
		Page page = null;
		try{
			page = tmTApplicationConfigService.findByTmTApplicationConfig(tmTApplicationConfig, 1, 100); 
		}catch(Exception e){
			e.printStackTrace();
			logger.error("", e);
		}
		if(page!=null){
			applicationCacheService.clearAllCacheManager();
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

	/**
	 * 重新加载用户与角色的缓存集合
	 * 
	 * @param userCode
	 *            用户代码 2013-9-6下午7:19:50 jiangweiyang
	 */
	public void reloadUserRoleCache(String userCode) {
		List<UmTRole> userRoleList = umTRoleService.findByUserCode(userCode); // 获取该配置给该用户功能List
		if (userRoleList != null && userRoleList.size() > 0) {
			GrantedAuthority[] authorityList = new GrantedAuthority[userRoleList
					.size()]; // 构造角色集合
			int index = 0;
			for (UmTRole role : userRoleList) {
				authorityList[index++] = new GrantedAuthorityImpl(
						role.getRoleCode());
			}
			userRoleCacheService
					.putCache(userRoleCacheService.generateCacheKey("USERROLE",
							userCode), authorityList);
		}
	}

	/**
	 * 重新构建访问请求与角色之间的对应关系 2013-9-6下午7:36:12 jiangweiyang
	 */
	public void reloadUrlRoleCache() {
		List<UmTMethodTask> roleTaskList = umTMethodTaskService
				.getValidMethodTaskList();
		/** 角色 **/
		List<UmTRole> roleList = umTRoleService.findValidUmTRoleList();
		if (roleList == null) {
			logger.warn("没有有效的角色数据");
		} else if (roleTaskList == null) {
			logger.warn("没有有效的角色功能数据");
		} else {
			urlRoleCacheService.clearCacheManager("UM_T_URLROLE"); // 将URL与角色对应缓存对象删除处理
			/**
			 * roleUrlMap
			 * 层级关系 map--role:map(url:task)
			 */
			Map<String, HashMap<String,UmTMethodTask>> roleUrlMap = new HashMap<String, HashMap<String,UmTMethodTask>>();
			//List<String> urlList = null;
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
			taskMap = null;
			roleTaskList = null;
			roleList = null;
		}
	}

	/**
	 * 更新角色与功能之间的对应关系(updateType:1 给角色添加功能 updateType:-1 给角色去除功能)
	 * 
	 * @param roleId
	 *            角色ID
	 * @param taskId
	 *            功能ID
	 * @param updateType
	 *            映射关系更新方式 2013-9-6下午7:41:15 jiangweiyang
	 */
	public void updateUrlRoleMatcher(String roleId, String taskId,
			int updateType) {
		// 根据roleID获取角色代码roleCode
		String roleCode = null;
		try {
			UmTRole umTRole = umTRoleService.findUmTRoleByPK(new UmTRoleId(
					roleId));
			if (umTRole != null) {
				roleCode = umTRole.getRoleCode(); // 获取角色代码
			} else {
				throw new Exception("更新角色缓存:没有查询到角色ID为:" + roleId + "的角色信息");
			}
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		// 根据功能代码获取功能代码所对应的MethodCode(TaskID与MethodCode一一对应)
		String methodCode = null;
		UmTMethodTask methodTask = umTMethodTaskService.findByTaskId(taskId);
		if (methodTask != null) {
			methodCode = methodTask.getMethodCode();
		} else {
			logger.warn("没有查询到功能ID为:" + taskId + "的功能信息");
			return;
		}
		methodCode = methodTask.getMethodCode();
		if(methodCode!=null){
			if(methodCode.indexOf("?")!=-1){
				methodCode = methodCode.substring(0, methodCode.indexOf("?"));
			}
			String roleStr = String.valueOf(urlRoleCacheService
					.getCache(methodCode)); // 根据访问URL从缓存中查询出其分配的角色清单
			if (updateType == 1) {
				// 给角色上添加功能
				if (roleStr == null || "".equals(roleStr)) {
					// 该功能没有配置过
					urlRoleCacheService.putCache(methodCode, roleCode);
				} else {
					urlRoleCacheService.putCache(methodCode, roleStr.concat(",")
							.concat(roleCode)); // 在原有可访问的角色字符串中添加新的角色信息
				}
			} else if (updateType == -1) {
				// 去除角色与功能之间的对应关系
				if (roleStr != null && !"".equals(roleStr)) {
					roleStr = roleStr.replaceAll(roleCode, ""); // 将当前用户角色去除
					urlRoleCacheService.putCache(methodCode, roleStr);
				}
			}
		}
	}

	/**
	 * 更新用户与功能的对应关系(重新加载配置给用户的功能List)
	 * 
	 * @param userCode
	 *            用户代码 2013-9-6下午8:17:57 jiangweiyang
	 */
	public void reloadUserTaskCache(String userCode) {
		if (userCode != null && !"".equals(userCode)) {
			/**
			 * 姚文锋：将userTaskCacheService缓存改为map
			 */
			HashMap<String,UmTMethodTask> taskMap = null;
			try {
				taskMap = umTMethodTaskService.getMethodTaskMapByUserCode(userCode);
			} catch (Exception e) {
				logger.warn(e.getMessage());
			}
			if (taskMap != null){
				userTaskCacheService.clearCache(userCode);
				userTaskCacheService.putCache(userCode, taskMap);
			}
		}
	}

	/**
	 * 修改功能模块时
	 * 
	 * @param methodCode
	 *            修改访问点URL
	 * @param operateType
	 *            操作类型(1 置为有效 -1置为无效,修改访问点URL) 2013-9-8下午7:04:28 jiangweiyang
	 */
	@SuppressWarnings("unchecked")
	public void updateModifyUrl(String methodCode, int operateType) {
		if (operateType == -1) {
			/** 去除角色与功能之间的对应关系 **/
			if (urlRoleCacheService.containsKey(methodCode)) {
				// 角色-功能缓存中包含该访问URL,删除该MethodCode所对应的缓存信息
				urlRoleCacheService.remove(methodCode);
			}
			// 去除用户-功能的缓存关联
			/*List<String> taskList = null;
			for (Object objectKey : userTaskCacheService.getKeys()) {
				taskList = (List<String>) userTaskCacheService.getCache(String
						.valueOf(objectKey));
				if (taskList != null && taskList.size() > 0) {
					for (int index = taskList.size() - 1; index >= 0; index--) {
						if (taskList.get(index).equals(methodCode)) {
							taskList.remove(index);
							userTaskCacheService.putCache(
									String.valueOf(objectKey), taskList); // 重新加载用户可用Operate
																			// URL
							break;
						}
					}
				}
			}*/
			/**
			 * 姚文锋：将userTaskCacheService缓存改为map
			 */
			HashMap<String,UmTMethodTask> taskMap = null;
			for (Object objectKey : userTaskCacheService.getKeys()) {
				taskMap = (HashMap<String,UmTMethodTask>) userTaskCacheService.getCache(String
						.valueOf(objectKey));
				if (taskMap != null && taskMap.size() > 0) {
					if(taskMap.containsKey(methodCode)){
						taskMap.remove(methodCode);
						userTaskCacheService.putCache(
								String.valueOf(objectKey), taskMap); // 重新加载用户可用Operate
																		// URL
						break;
					}
				}
			}
		} else if (operateType == 1) {
			// 将该功能点置成有效状态
			List<String> roleList = umTMethodTaskService
					.getRoleCodeListByMethodCode(methodCode); // 根据访问URL获取有效的角色List
			StringBuffer buffer = new StringBuffer();
			for (String roleCode : roleList) {
				buffer = buffer.append(roleCode).append(",");
			}
			buffer = buffer.delete(buffer.length() - 1, buffer.length()); // 删除最后的逗号
			urlRoleCacheService.putCache(methodCode, buffer.toString());
		}
	}

	/**
	 * 修改角色信息，同步更新缓存信息
	 * 
	 * @param roleId
	 *            角色ID
	 * @param operateType
	 *            操作类型(1添加角色 -1删除角色) 2013-9-8下午9:08:07 jiangweiyang
	 */
	public void updateRole(String roleId, String roleCode,
			int operateType) {
		if (roleId == null || "".equals(roleId)) {
			logger.warn("传入角色ID:" + roleId + "为空");
		} else {
			if (operateType == 1) {
				// 添加角色信息
				try {
					List<String> methodCodeList = umTMethodTaskService
							.getMethodCodeByRoleCode(roleId);
					for (String methodCode : methodCodeList) {
						if (urlRoleCacheService.containsKey(methodCode)) {
							urlRoleCacheService.putCache(
									methodCode,
									String.valueOf(
											urlRoleCacheService
													.getCache(methodCode))
											.concat(",").concat(roleCode));
						}
					}
				} catch (Exception e) {
					logger.warn(e.getMessage());
				}
			} else if (operateType == -1) {
				try {
					List<String> methodCodeList = umTMethodTaskService
							.getMethodCodeByRoleCode(roleId);
					String roleStr = "";
					for (String methodCode : methodCodeList) {
						if (urlRoleCacheService.containsKey(methodCode)) {
							roleStr = String.valueOf(urlRoleCacheService
									.getCache(methodCode));
							if (roleStr != null && !"".equals(roleStr)
									&& !"null".equals(roleStr)) {
								roleStr = roleStr.replaceAll(roleCode, ""); // 去除角色信息
							}
							urlRoleCacheService.putCache(methodCode, roleStr);
						}
					}
				} catch (Exception e) {
					logger.warn(e.getMessage());
				}
			}
		}
	}
	
	
	/**
	* @Title: reloadWhiteListCache				
	* @Description: 重新加载白名单缓存
	* @param     设定文件
	* @return void    返回类型
	* @throws
	 */
	@SuppressWarnings("unchecked")
	public void reloadWhiteListCache() {
		UmTWhiteList whiteList = new UmTWhiteList();
		whiteList.setValidStatus("1"); // 查询有效状态的白名单配置信息
		try {
			List<UmTWhiteList> resultList = umTWhiteListService.findByUmTWhiteList(whiteList, 1, Integer.MAX_VALUE).getResult();
			whiteListCacheService.clearCacheManager("UM_T_WHITELIST");				//清除白名单缓存
			for (UmTWhiteList wl : resultList) {
				whiteListCacheService.putCache(wl.getVisitUrl(),wl.getVisitUrl());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("", e);
		}		
	}
	
	
	/**
	* @Title: reloadRoleDimeCache
	* @Description:	重新加载角色维度扩展对象
	* @param     
	* @return void    返回类型
	* @author jiangweiyang
	* @date 2014/01/13
	 */
	@SuppressWarnings("unchecked")
	public void reloadRoleDimeCache() {
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
			roleDimeCacheService.clearCacheManager("UM_T_ROLEDIME");					//清除RoleDime的缓存
			for (RoleDimeVo roleDime : roleDimeList) {
				if ((urlRoleMap = (Map<String, Map<String, String>>) roleDimeCacheService.getCache(roleDime.getDimensionValue())) == null) {
					// 缓存对象中没有数据
					operateMap = new HashMap<String, String>();
					operateMap.put(roleDime.getRoleCode(),roleDime.getOperateType());
					urlRoleMap = new HashMap<String, Map<String, String>>();
					urlRoleMap.put(roleDime.getMethodCode(), operateMap);
					roleDimeCacheService.putCache(roleDime.getDimensionValue(),urlRoleMap);
				} else {
					if (urlRoleMap.keySet().contains(roleDime.getMethodCode())) {
						operateMap = urlRoleMap.get(roleDime.getMethodCode());
						operateMap.put(roleDime.getRoleCode(),roleDime.getOperateType());
						urlRoleMap.put(roleDime.getMethodCode(), operateMap);
						roleDimeCacheService.putCache(roleDime.getDimensionValue(), urlRoleMap);
					} else {
						operateMap = new HashMap<String, String>();
						operateMap.put(roleDime.getRoleCode(),roleDime.getOperateType());
						urlRoleMap.put(roleDime.getMethodCode(), operateMap);
						roleDimeCacheService.putCache(roleDime.getDimensionValue(), urlRoleMap);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public void reloadLogTypeCache(String comId) {
		LoGTTYPE logType = new LoGTTYPE();
		logType.setComid(comId);
		Page page = null;
		try{
			page = loGTTYPEService.findByLoGTTYPE(logType, 1, Integer.MAX_VALUE);
		}catch(Exception ex){
			return;
		}
		if(page!=null){
			List<LoGTTYPE> list = (List<LoGTTYPE>)page.getResult();
			for(LoGTTYPE lt:list){
				logTypeCacheService.putCache(lt.getOperateMethod(), lt);
			}
		}
	}
	
	/**
	* @Title: reloadCognosCache
	* @Description: 重新加截Cognos缓存信息
	* @param     设定文件
	* @return void    返回类型
	* @author jiangweiyang
	* @date 2014/01/13
	 */
	@SuppressWarnings("unchecked")
	public void reloadCognosCache(String comId) {
		TmTSystemConfig systemConfig = new TmTSystemConfig();
		systemConfig.setValidStatus(AppConfig.get("sysconst.VALID"));
		systemConfig.setConfigName("COGNOS");
		try {
			cognosConfigCacheService.remove(cognosConfigCacheService.generateCacheKey("CONFIG", comId));				//删除对应省市cognos缓存数据
			List<TmTSystemConfig> systemConfigList = tmTSystemConfigService.findByTmTSystemConfig(systemConfig, 1, Integer.MAX_VALUE).getResult();
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
			String key = cognosConfigCacheService.generateCacheKey("CONFIG",comId);
			CognosEnvironmentModel modelValue = (CognosEnvironmentModel)cognosConfigCacheService.getCache(key);
			UmTAccount	account = umTAccountService.findUmTAccountByUserCode(modelValue.getDefaultUserCode());
			if(cognosService!=null) {
				cognosService.getCognosMaxMenu(account);
			}
			
			/**外网数据初始化处理**/
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
	 * 刷新各节点中的缓存数据信息
	* @Title: refreshNodeCache
	* @Description: 轮循刷新各应用节点中的缓存数据
	* @param     设定文件
	* @return void    返回类型
	* @throws
	* @author jiangweiyang
	* @date 2014/01/13
	 */
	public Map<String,String> refreshNodeCache(List<String> cacheList,String comId) throws Exception {
		//查询appServiceConfig表中各应用节点的配置信息
		TmTAppServiceConfig appServiceConfig = new TmTAppServiceConfig();
		TmTAppServiceConfigId id = new TmTAppServiceConfigId();
		String envCode  = tmTAppEnvironmentService.getEnvironmentCode(comId);						//获取应用服务代码
		id.setEnvironmentCode(envCode);
		id.setServerCode("AppServer%");																									//提取所有以App开头的节点信息
		appServiceConfig.setId(id);
		appServiceConfig.setComId(comId);																							//查询本级机构的ComID
		appServiceConfig.setValidStatus("1");																							//查询有效状态的数据
		QueryRule queryRule = QueryRuleHelper.generateQueryRule(appServiceConfig);				//生成查询对象
		List<TmTAppServiceConfig> appConfigList = tmTAppServiceConfigDao.find(queryRule);		//查询满足条件的服务节点对象
		if(appConfigList==null||appConfigList.size()<1){
			throw new Exception("AppServiceConfig表中没有配置AppServer应用节点");
		}else {
			//对于配置信息进行URL组装操作
			StringBuffer stringBuffer = null;
			Map<String,String> requestPath = new HashMap<String,String>();
			for(TmTAppServiceConfig serviceConfig :  appConfigList) {
				stringBuffer = new StringBuffer();
				//加入请求协议
				if(serviceConfig.getProtocolType()!=null&&!"".equals(serviceConfig.getProtocolType())){
					stringBuffer.append(serviceConfig.getProtocolType());
					stringBuffer.append("://");
				}
				//加入对应的IP地址
				if(serviceConfig.getServerIp()!=null&&!"".equals(serviceConfig.getServerIp())){
					stringBuffer.append(serviceConfig.getServerIp());
				}
				//加入访问端口号
				if(serviceConfig.getServerPort()!=null&&!"".equals(serviceConfig.getServerPort())){
					stringBuffer.append(":").append(serviceConfig.getServerPort());
					stringBuffer.append("/");
				}
				//加入访问Context信息
				if(serviceConfig.getServerContext()!=null&&!"".equals(serviceConfig.getServerContext())){
					stringBuffer.append(serviceConfig.getServerContext());
				}
				requestPath.put(serviceConfig.getId().getEnvironmentCode()+":"+serviceConfig.getId().getServerCode(), stringBuffer.toString());			//将请求路径Path作为其中一项
			}
			
			//进行参数处理
			StringBuffer cacheNameBuffer = new StringBuffer();;
			for(String cache : cacheList){
				cacheNameBuffer.append(cache).append(",");
			}
			if(cacheNameBuffer.length()>0){
				cacheNameBuffer.delete(cacheNameBuffer.length()-1, cacheNameBuffer.length());
			}
			
			//对于所有的请求路径进行Http模拟处理访问
			HttpClient client = new HttpClient();
			PostMethod postMethod = null;
			String requestUrl = null;
			int statusCode = -1;
			Map<String,String> resultMap = new HashMap<String,String>();
			for(String appCode : requestPath.keySet()) {
				requestUrl = requestPath.get(appCode);
				requestUrl = requestUrl.concat("?cacheName=").concat(cacheNameBuffer.toString());
				//刷新缓存时加入comId的限制处理
				requestUrl = requestUrl.concat("&comId=").concat(comId);				//加入对于ComID的参数传递
				try{
					postMethod = new PostMethod(requestUrl);
					statusCode = client.executeMethod(postMethod);									//client执行请求
				}catch(Exception ex){
					resultMap.put(appCode, "节点连接异常");
					continue;
				}
				if(statusCode == HttpStatus.SC_OK){
					//服务请求OK
					resultMap.put(appCode, "节点更新成功");
				}else {
					resultMap.put(appCode, "节点更新失败");
				}
				postMethod.releaseConnection();											//释放请求
			}
			client = null;
			postMethod = null;
			requestPath = null;
			return resultMap;
		}
	}

}
