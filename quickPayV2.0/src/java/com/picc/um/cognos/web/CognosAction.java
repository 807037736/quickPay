package com.picc.um.cognos.web;

import ins.framework.cache.CacheManager;
import ins.framework.cache.CacheService;
import ins.framework.web.JsonValueFormat;
import ins.framework.web.Struts2Action;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.eclipse.jetty.util.log.Log;

import com.picc.common.Constants;
import com.picc.common.utils.AppConfig;
import com.picc.common.utils.StringUtils;
import com.picc.common.utils.ToolsUtils;
import com.picc.tm.common.schema.model.TmTAppServiceConfig;
import com.picc.tm.common.schema.model.TmTAppServiceConfigId;
import com.picc.tm.common.schema.model.TmTSystemConfig;
import com.picc.tm.common.service.facade.ITmTAppServiceConfigService;
import com.picc.tm.common.service.facade.ITmTSystemConfigService;
import com.picc.um.cognos.service.facade.ICognosService;
import com.picc.um.cognos.shema.CognosEnvironmentModel;
import com.picc.um.cognos.utils.CRNConnect;
import com.picc.um.cognos.utils.GroupsAndRoles;
import com.picc.um.cognos.utils.Logon;
import com.picc.um.schema.model.UmTRole;
import com.picc.um.schema.model.UmTRoleId;
import com.picc.um.schema.model.UmTUser;
import com.picc.um.schema.vo.SessionUser;
import com.picc.um.service.facade.ICacheService;
import com.picc.um.service.facade.IUmTGroupService;
import com.picc.um.service.facade.IUmTRoleService;
import com.picc.um.service.facade.IUmTTaskService;
import com.picc.um.service.facade.IUmTUserService;
import com.sinosoft.bpsdriver.util.SystemCode;
import com.sinosoft.dmsdriver.model.PrpDcompany;
import com.sinosoft.dmsdriver.service.server.PageService;

/**
 * Cognos web处理Action
 * @author shenyichan 2013-08-02
 */
@SuppressWarnings("serial")
public class CognosAction extends Struts2Action {
	
	public static String NODIRECT = "nodirect";
	
	private static CacheService cognosConfigCacheService = CacheManager
			.getInstance("COGNOS_CONFIG");
	
	private static CacheService cognosMenuCacheService = CacheManager.getInstance("COGNOS_MENU");

	private static CacheService userSystemCacheService = CacheManager.getInstance("UM_T_USERSYSTEM");
	
	private IUmTUserService umTUserService;
	private ICognosService cognosService;

	private IUmTRoleService umTRoleService;

	private String roleId; // 角色ID, 将角色同步至Cognos时用到

	private String roleCName; // 角色名称

	private List<String> oc; // 对Cognos菜单的操作权限

	private List<String> addSearchPath = new ArrayList<String>(); // 被增加的各菜单的查询路径

	private List<String> delSearchPath = new ArrayList<String>(); // 被删除的各菜单的查询路径
	
	private String groupType = null;				//群组类型(comcode机构、monopolycode车行)
	
	private String groupCode = null;				//群组代码
	
	private IUmTGroupService  umTGroupService;
	
	private ITmTSystemConfigService tmTSystemConfigService;
	
	private ITmTAppServiceConfigService tmTAppServiceConfigService;
	
	private ICacheService cacheService = null;
	
	private String host = null;
	private IUmTTaskService umTTaskService = null;
	
	private List<List<TmTAppServiceConfig>> tmTAppServiceConfigTable;
	
	private String serverCode;
	
	private String serverName;

	
	
	
	public String getServerCode() {
		return serverCode;
	}

	public void setServerCode(String serverCode) {
		this.serverCode = serverCode;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public List<List<TmTAppServiceConfig>> getTmTAppServiceConfigTable() {
		return tmTAppServiceConfigTable;
	}

	public void setTmTAppServiceConfigTable(
			List<List<TmTAppServiceConfig>> tmTAppServiceConfigTable) {
		this.tmTAppServiceConfigTable = tmTAppServiceConfigTable;
	}

	public void setUmTTaskService(IUmTTaskService umTTaskService) {
		this.umTTaskService = umTTaskService;
	}

	public void setTmTAppServiceConfigService(
			ITmTAppServiceConfigService tmTAppServiceConfigService) {
		this.tmTAppServiceConfigService = tmTAppServiceConfigService;
	}

	public IUmTUserService getUmTUserService() {
		return umTUserService;
	}

	public void setUmTUserService(IUmTUserService umTUserService) {
		this.umTUserService = umTUserService;
	}

	public String getRoleCName() {
		return roleCName;
	}

	public void setRoleCName(String roleCName) {
		this.roleCName = roleCName;
	}

	public List<String> getAddSearchPath() {
		return addSearchPath;
	}

	public void setAddSearchPath(List<String> addSearchPath) {
		this.addSearchPath = addSearchPath;
	}

	public List<String> getDelSearchPath() {
		return delSearchPath;
	}

	public void setDelSearchPath(List<String> delSearchPath) {
		this.delSearchPath = delSearchPath;
	}

	public List<String> getOc() {
		return oc;
	}

	public void setOc(List<String> oc) {
		this.oc = oc;
	}

	public IUmTRoleService getUmTRoleService() {
		return umTRoleService;
	}

	public void setUmTRoleService(IUmTRoleService umTRoleService) {
		this.umTRoleService = umTRoleService;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public ICognosService getCognosService() {
		return cognosService;
	}

	public void setCognosService(ICognosService cognosService) {
		this.cognosService = cognosService;
	}
	
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	
	public String getGroupType() {
		return groupType;
	}
	
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	
	public String getGroupCode() {
		return groupCode;
	}
	
	public void setUmTGroupService(IUmTGroupService umTGroupService) {
		this.umTGroupService = umTGroupService;
	}
	
	public IUmTGroupService getUmTGroupService() {
		return umTGroupService;
	}
	
	public void setTmTSystemConfigService(
			ITmTSystemConfigService tmTSystemConfigService) {
		this.tmTSystemConfigService = tmTSystemConfigService;
	}
	
	public ITmTSystemConfigService getTmTSystemConfigService() {
		return tmTSystemConfigService;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public String getHost() {
		return host;
	}
	
	public ICacheService getCacheService() {
		return cacheService;
	}

	public void setCacheService(ICacheService cacheService) {
		this.cacheService = cacheService;
	}

	/**
	* @Title: getCognosManager
	* @Description: 获取Cognos后台资源
	* @return String    返回类型
	* @throws
	* @author 沈一婵
	* @date 2013/10/21
	 */
	public String getCognosManager() {
		try {
			SessionUser user = getUserFromSession();
			String ipHost = super.getRequest().getServerName();
			if(ipHost!=null){
				if(ipHost.indexOf(":")>0){
					ipHost = ipHost.substring(0,ipHost.indexOf(":"));
				}
			}
			List<UmTRole> roleList = umTRoleService.findByUserCode(user
					.getUserCode());
			if (user != null
					&& AppConfig.get("sysconst.VALID").equals(
							user.getValidStatus())) {
				// 存在用户且用户有效，异步方式则获取Cognos后台资源
				for (UmTRole role : roleList) {
					// 遍历角色，如果有cognos角色则获取Cognos后台资源
					String roleType = role.getFlag();
					if ((roleType != null
							&& !"".equals(roleType)
							&& AppConfig.get("sysconst.VALID").equals(
									roleType.substring(0, 1))) || "admin".equals(user.getUserCode())) {
						// 如果角色标识的第一位是1，则表示为Cognos角色，或当前用户超级管理员，则为当前用户加载Cognos后台资源
						getCognosManager(user,ipHost);
						getSession().setAttribute(AppConfig.get("um.COGNOS_ERR"), "success"); // Cognos访问标识，值为success时表示用户可正常访问cognos
						//System.out.println("成功获取Cognos后台资源...");
						return SUCCESS;
					}
				}
			}
		} catch (Exception e) {
			getSession().setAttribute(AppConfig.get("um.COGNOS_ERR"),
					e.getMessage());
			e.printStackTrace();
			logger.error("", e);
		}
		return SUCCESS;//return NODIRECT;
		
	}

	
	/**
	* @Title: getCognosManager
	* @Description: 判断一个用户是否拥有获取Cognos菜单的权限
	* @param  user		当前登录用哀悼
	* @return boolean    返回类型
	* @throws
	* @author 沈一婵
	* @date 2013/10/21
	 */
	@SuppressWarnings("unchecked")
	private boolean getCognosManager(SessionUser user,String ipHost) {
		// 根据当前用户Comcode获取Cognos的URL
		String comCode = user.getComCode();
		CognosEnvironmentModel cognosEnvironmentModel = null;
		cognosEnvironmentModel = (CognosEnvironmentModel) cognosConfigCacheService
				.getCache(cognosConfigCacheService.generateCacheKey("CONFIG",
						comCode));
		if (cognosEnvironmentModel == null) {
			// 如果当前用户的comcode不是省（市）级，则往上找对应省（市）的comcode
			cognosEnvironmentModel = (CognosEnvironmentModel) cognosConfigCacheService
					.getCache(cognosConfigCacheService.generateCacheKey(
						"CONFIG", SessionUser.getComIdByComCode(comCode)));
			if(cognosEnvironmentModel==null){
				//缓存中没有数据 进行数据库重新提取处理
				
				TmTSystemConfig systemConfig = new TmTSystemConfig();
				systemConfig.setValidStatus(AppConfig.get("sysconst.VALID"));
				systemConfig.setConfigName("COGNOS");
				try {
					cognosConfigCacheService.clearCacheManager("COGNOS_CONFIG");									//清空Cognos配置缓存数据
					List<TmTSystemConfig> systemConfigList = tmTSystemConfigService.findByTmTSystemConfig(systemConfig, 1, Integer.MAX_VALUE).getResult();
					// 清除所有的缓存信息
					CognosEnvironmentModel model = null;
					for (TmTSystemConfig systemConfigVar : systemConfigList) {
						// 遍历全国的Cognos配置变量
						if (cognosConfigCacheService
								.containsKey(cognosConfigCacheService.generateCacheKey(
										"CONFIG", systemConfigVar.getComCode()))) {
							// 已经有对象
							model = (CognosEnvironmentModel) cognosConfigCacheService
									.getCache(cognosConfigCacheService
											.generateCacheKey("CONFIG",
													systemConfigVar.getComCode()));
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
										systemConfigVar.getComCode()), model);
					}
				} catch (Exception e) {
					System.err.println("无法从数据库中提取Cognos的配置数据");
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error("", e);
				}
				cognosEnvironmentModel = (CognosEnvironmentModel) cognosConfigCacheService
						.getCache(cognosConfigCacheService.generateCacheKey(
							"CONFIG", SessionUser.getComIdByComCode(comCode)));
			}
		}

		if (cognosEnvironmentModel != null) {
			CRNConnect connection = new CRNConnect();
			connection.connectToCognosServer(cognosEnvironmentModel.getCognosUrl());
			Logon logon = new Logon();
			Logon.COGNOSDISPATCHER = cognosEnvironmentModel.getCognosUrl();

			String result = null;
			if (logon.loggedIn(connection) == false) {
				result = logon.quickLogon(connection, Logon.THIRDPJNAMESPACE,
						user.getUserCode(), user.getUserPass());
			}
			if (user.getUserCode().equals(result)) {
				// 登录成功后，返回用户的userName
				getSession().setAttribute(AppConfig.get("um.COGNOS_CRN"),
						connection); // 登录成功后将CRNConnect保存到session当中
				//加入URL放至Session中进行管理
				try{
					//System.out.println("visitUrl:"+ipHost);
					if(NET_OUTER.equals(super.checkRequestNet(ipHost))){
						String url = cognosEnvironmentModel.getCognosUrl();					//获取内网访问URL
						url = url.substring(7);
						url = url.substring(url.indexOf("/"));
						//进行外网URL拼接
						getSession().setAttribute("COGNOS_URL", "http://".concat(cognosEnvironmentModel.getOuterUrl()).concat(url));
					}else {
						getSession().setAttribute("COGNOS_URL", cognosEnvironmentModel.getCognosUrl());
					}
				}catch(Exception ex){
					ex.printStackTrace();
					throw new RuntimeException("查询Cognos请求Net出现错误");
				}
				
				getSession().setAttribute("CRNENV_PATH", cognosEnvironmentModel.getCrnEnvPath());
				return true;
			} else {
				System.err.println("COGNOS_ERROR:"+result);
				System.err.println("COGNOS_USERPASS:"+user.getUserPass());
				throw new RuntimeException("Cognos用户或密码不正确！");
			}
		} else {
			throw new RuntimeException("Cognos参数不匹配！请检查所在机构或Cognos系统参数配置！");
		}
	}

	
	/**
	* @Title: findMenuByUser
	* @Description: 根据用户代码返回配置给该用户Cognos报表菜单
	* @return String    满足条件的菜单形成的JSON格式数据
	* @throws
	* @author 沈一婵
	* @date 2013/10/13
	 */
	public String findMenuByUser() {
		String cognosMenu = null;
		try {
			String netType = super.checkRequestNet(host);				//获取访问类型
			String usercode = getUserFromSession().getUserCode();
			Object menuObject = cognosMenuCacheService.getCache(usercode);
			String menuKey = cognosMenuCacheService.generateCacheKey(netType,usercode);		//根据网络访问类型及对应的使用者代码生成菜单缓存主键Key
			if(menuObject==null||"[]".equals(String.valueOf(menuObject))){
				CRNConnect connection = (CRNConnect) getSession().getAttribute(AppConfig.get("um.COGNOS_CRN"));
				if (connection != null) {
					// 如果当前已获取cognos后台资源则去请求菜单
					cognosMenu = cognosService.findMenuByUserCode(connection, usercode, getUserFromSession().getComId(),netType);
					cognosMenuCacheService.putCache(menuKey, cognosMenu);
				}
			}
			cognosMenu = String.valueOf(cognosMenuCacheService.getCache(menuKey));
			//System.out.println(cognosMenu);
			this.writeString(cognosMenu);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}

		return NONE;
	}

	
	
	/**
	* @Title: addCognosRole
	* @Description:  同步Cognos角色信息，将营销系统中的应用角色同步至Cognos
	* @return String  同步结果的JSON数据格式
	* @throws
	* @author 沈一婵
	* @date 2013/10/11
	 */
	public String addCognosRole() {
		try {
			UmTRole umTRole = umTRoleService.findUmTRoleByPK(new UmTRoleId(
					roleId));
			CRNConnect connection = (CRNConnect) getSession().getAttribute(
					AppConfig.get("um.COGNOS_CRN"));
			
			
			if (connection != null) {
				if (!AppConfig.get("sysconst.VALID").equals(
						umTRole.getValidStatus())) {
					throw new RuntimeException("无效的角色, 请检查当前角色是否有效!");
				}
				
				
				//先去Cognos中查此角色，用于判断是否是Cognos角色
				GroupsAndRoles gr = new GroupsAndRoles();
				CognosEnvironmentModel cognosEnvironmentModel = null;
				cognosEnvironmentModel = (CognosEnvironmentModel) cognosConfigCacheService
						.getCache(cognosConfigCacheService.generateCacheKey("CONFIG",
								getUserFromSession().getComId()));
				String pathOfRole = "CAMID(\":"+cognosEnvironmentModel.getRoleFolder()+":" + umTRole.getRoleCName() + "\")";
				
				if(cognosService.checkCognosRole(connection, umTRole,getUserFromSession().getComId())){
					//如果当前角色已经是Cognos角色，将只同步人员到Cognos
					List<UmTUser> userList = umTUserService.findUserByRole(umTRole.getId()
							.getRoleId(), getUserFromSession().getComId());
					cognosService.addSomeUserToCognosRole(connection, umTRole, userList,getUserFromSession().getComId());
				} else {
					//如果当前角色不是Cognos角色，则将角色和角色下的所有人员都同步到Cognos
					cognosService.addCognosRole(connection, umTRole,
							getUserFromSession().getComId());
				}

			} else {
				throw new RuntimeException("错误的Cognos连接！请检查当前用户是否是Cognos用户！");
			}
			this.writeString(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeString(e.getMessage());
		}
		return NONE;
	}
	
	/**
	 * 同步系统的群组信息至Cognos进行处理
	 * @return
	 * 2013-10-16下午8:22:16
	 * jiangweiyang
	 */
	public String addCognosGroup(){
		//System.out.println("addCognosGroup...");
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			CRNConnect connection = (CRNConnect) getSession().getAttribute(AppConfig.get("um.COGNOS_CRN"));
			if (connection != null) {
				if(!ToolsUtils.isEmpty(groupType)&&!ToolsUtils.isEmpty(groupCode)){
					if("comcode".equals(groupType)){
						//较验机构是否正确
						if(groupCode.indexOf(",")==-1){
							if(PageService.getCompanys(SystemCode.DMS, groupCode, "", "", "1","", 1,1).getData().size()<1){
								resultMap.put("success", false);
								resultMap.put("message", "机构代码:"+groupCode+"不存在或无效");
							}
						}else {
							for(String comcode : groupCode.split(",")){
								if(PageService.getCompanys(SystemCode.DMS, comcode, "", "", "1","", 1,1).getData().size()<1){
									resultMap.put("success", false);
									resultMap.put("message", "机构代码:"+comcode+"不存在或无效");
								}
							}
						}
						if(resultMap.size()<1){
							cognosService.addCognosGroup(connection, groupType, groupCode, getUserFromSession().getComId());
							resultMap.put("success", true);
							resultMap.put("message", "Cognos群组信息同步成功");
						}
					}else if("monopolycode".equals(groupType)){
						//较验车行是否正确
						if(groupCode.indexOf(",")==-1){
							if(!umTGroupService.isGroupCode(groupCode)){
								resultMap.put("success", false);
								resultMap.put("message", "车行代码:"+groupCode+"不正确");
							}
						}else {
							for(String monopolyCode : groupCode.split(",")){
								if(!umTGroupService.isGroupCode(monopolyCode)){
									resultMap.put("success", false);
									resultMap.put("message", "车行代码:"+monopolyCode+"不正确");
								}
							}
						}
						//没有出错信息
						if(resultMap.size()<1){
							cognosService.addCognosGroup(connection, groupType, groupCode, getUserFromSession().getComId());
							resultMap.put("success", true);
							resultMap.put("message", "Cognos群组信息同步成功");
						}
					}
				}else {
					resultMap.put("success", false);
					resultMap.put("message", "传入的GroupCode或GroupType不能为空");
				}
			}else {
				resultMap.put("success", false);
				resultMap.put("message", "错误的Cognos连接！请检查当前用户是否是Cognos用户！");
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			resultMap.put("success", false);
			resultMap.put("message", e.getMessage());
			this.writeString(e.getMessage());
		}
		this.writeAjaxByMap(resultMap);
		return NONE;
	}
	
	/**
	 * 同步系统所有
	 * @return
	 * 2013-01-15
	 * shenyichan
	 */
	public String addAllCognosGroup(){
		//System.out.println("addAllCognosGroup...");
		SessionUser user = getUserFromSession();
		String comId = user.getComId();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			CRNConnect connection = (CRNConnect) getSession().getAttribute(AppConfig.get("um.COGNOS_CRN"));
			if (connection != null) {
				if(!ToolsUtils.isEmpty(groupType)){
					if("comcode".equals(groupType)){
						//获取当前机构下的所有的子机构
						List<PrpDcompany> companyList = PageService.getCompanys(SystemCode.DMS, comId, "", "", "1", "SUB", 1, Integer.MAX_VALUE).getData();
						companyList.addAll(PageService.getCompanys(SystemCode.DMS, comId, "", "", "1", "", 1, 1).getData());
						StringBuffer companyBuf = new StringBuffer();
						for(PrpDcompany company : companyList ){
							companyBuf.append(company.getComCode()).append(",");
						}
						companyBuf.deleteCharAt(companyBuf.length()-1);
						cognosService.addCognosGroup(connection, groupType, companyBuf.toString(), getUserFromSession().getComId());
						resultMap.put("success", true);
						resultMap.put("message", "Cognos群组信息同步成功");
					}else if("monopolycode".equals(groupType)){
						//获取当前机构下的所有车行
						List<String> groupList = umTGroupService.findAllGroupByType("2",comId);
						StringBuffer groupBf = new StringBuffer();
						if(groupList!=null && groupList.size()>0){
							for(String groupCode : groupList){
								groupBf.append(groupCode).append(",");
							}
							groupBf.deleteCharAt(groupBf.length()-1);
							cognosService.addCognosGroup(connection, groupType, groupBf.toString(), getUserFromSession().getComId());
							resultMap.put("success", true);
							resultMap.put("message", "Cognos群组信息同步成功");
						}
					}
				}else {
					resultMap.put("success", false);
					resultMap.put("message", "传入的GroupCode或GroupType不能为空");
				}
			}else {
				resultMap.put("success", false);
				resultMap.put("message", "错误的Cognos连接！请检查当前用户是否是Cognos用户！");
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			resultMap.put("success", false);
			resultMap.put("message", e.getMessage());
			this.writeString(e.getMessage());
		}
		this.writeAjaxByMap(resultMap);
		return NONE;
	}
	

	/**
	 * 预添加Cognos群组信息
	 * @return
	 * 2013-10-17下午3:26:43
	 * jiangweiyang
	 */
	public String prepareAddCognosGroup() {
		return SUCCESS;
	}
	
	

	/**
	* @Title: findAllMenu
	* @Description: 获取所有的Cognos菜单信息
	* @return String   满足条件的Cognos菜单形成的JSON格式字符串
	* @throws
	* @author 沈一婵
	* @date 2013/10/11
	 */
	public String findAllMenu() {
		// 异常信息的map
		Map<String, Object> errorMap = new HashMap<String, Object>();
		try {
			String netType = super.checkRequestNet(host);				//获取访问类型ss
			UmTRole umTRole = umTRoleService.findUmTRoleByPK(new UmTRoleId(
					roleId));
			CRNConnect connection = (CRNConnect) getSession().getAttribute(
					AppConfig.get("um.COGNOS_CRN"));

			// 检查是否是Cognos角色
			if (cognosService.checkCognosRole(connection, umTRole,getUserFromSession().getComId())) {
				String searchPath = AppConfig.get("sysconst.COGNOSSPACE_MENU");
				if (connection != null) {
					// 获取Cognos所有菜单，包括节点的选中状态
					String cognosMenu = cognosService.findAllMenu(connection,
							searchPath, umTRole.getRoleCName(),getUserFromSession().getComId(),netType);
					errorMap.put("status", "success");
					errorMap.put("content", cognosMenu);
					this.writeAjaxByMap(errorMap);
				} else {
					throw new RuntimeException("错误的Cognos连接！请检查当前用户是否具有该权限！");
				}
			} else {
				throw new RuntimeException("当前角色不是Cognos角色，不能进行菜单配置！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			errorMap.put("status", "fail");
			errorMap.put("content", e.getMessage());
			this.writeAjaxByMap(errorMap);
		}
		return NONE;
	}

	
	/**
	* @Title: writeAjaxByMap
	* @Description: 将Map对象以JSON的数据格式返回至前台界面
	* @param @param map    map对象
	* @return void    返回类型
	* @throws
	* @author 沈一婵
	* @date 2013/10/11
	 */
	public void writeAjaxByMap(Map map) {
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Date.class, new JsonValueFormat());

		JSONObject jsonObject = JSONObject.fromObject(map, config);
		try {
			HttpServletResponse response = getResponse();
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(jsonObject.toString());
			response.getWriter().flush();
		} catch (IOException e) {
			//logger.error(e.getMessage(), e);
			throw new IllegalStateException(e);
		}
	}

	/**
	* @Title: addMenuToCognosRole
	* @Description: 将Cognos菜单配置给应用角色
	* @param     配置成功的JSON格式字符串
	* @return String    返回类型
	* @throws
	* @author 沈一婵
	* @date 2013/10/11
	 */
	public String addMenuToCognosRole() {
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			for (int i = 0; i < addSearchPath.size(); i++) {
				addSearchPath.set(i, addSearchPath.get(i).replace("‘", "\'"));
			}
			for (int i = 0; i < delSearchPath.size(); i++) {
				delSearchPath.set(i, delSearchPath.get(i).replace("‘", "\'"));
			}
			CRNConnect connection = (CRNConnect) getSession().getAttribute(
					AppConfig.get("um.COGNOS_CRN"));
			String cognosMenu = AppConfig.get("sysconst.COGNOSSPACE_MENU");
			List<String> list = new ArrayList<String>();
			list.add(cognosMenu);
			if (connection != null) {
				
				if (addSearchPath.size() > 0) {

					// 添加菜单至Cognos角色
					cognosService.addMenuToCognosRole(connection, roleCName,
							addSearchPath, oc,getUserFromSession().getComId());
				}
				if (delSearchPath.size() > 0) {
					
					// 从Cognos角色中删除菜单
					cognosService.delMenuFromCognosRole(connection, roleCName,
							delSearchPath, cognosService.findSubMenuSearchPath(connection,cognosMenu), oc,getUserFromSession().getComId());
				}
				
				resultMap.put("status", "success");
				resultMap.put("content", "报表配置成功!");
				
				//清除对应的缓存数据
				//cognosMenuCacheService.clearCacheManager("COGNOS_MENU");					//清除用户Congos的数据缓存
				//String menuKey = cognosConfigCacheService.generateCacheKey("MENU",getUserFromSession().getComId());
				//清单最大权限菜单
				//cognosConfigCacheService.clearCache(menuKey);								//清除所对应的省市Cognos的缓存数据
				
				cacheService.reloadCognosCache(getUserFromSession().getComId());
				
				this.writeAjaxByMap(resultMap);
			} else {
				throw new RuntimeException("错误的Cognos连接！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			resultMap.put("status", "fail");
			resultMap.put("content", "报表配置失败!");
			this.writeAjaxByMap(resultMap);
			
		}
		return NONE;
	}
	
	/**
	 * add by shenyichan 2014/03/19
	 * modified by liuyatao 2014/06/19
	 * modified by yaowenfeng 2014/07/23
	 * @return
	 * @throws Exception 
	 */
	public String systemChoice() throws Exception{
		SessionUser user = getUserFromSession();
		if(user == null){
//			this.getResponse().sendRedirect("/login.do");
			return "fail";
		}
			
		List<TmTAppServiceConfig> allTmTAppSegjrviceConfigList = tmTAppServiceConfigService.findAll();
		
		if(userSystemCacheService.getCache(user.getUserCode()) == null){
			userSystemCacheService.putCache(user.getUserCode(), umTTaskService.findAppByUser(user.getUserCode(), user.getComId(), "menu"));
		}
		List<TmTAppServiceConfig> userTmTAppSegjrviceConfigList = (List<TmTAppServiceConfig>) userSystemCacheService.getCache(user.getUserCode());
		List<TmTAppServiceConfig> allAuthorizedAppSystemList = new ArrayList<TmTAppServiceConfig>();
		TmTAppServiceConfig defaultSystem = null;
		//找出当前系统环境对应的应用系统代码
		String defaultSystemCode = (String)getSession().getAttribute("ServerCode");
		Log.info("defulat systemcode is :{}",defaultSystemCode);
		String method = null;
		/*将有权限的系统显示为亮色，且将权限标识设置为1，即可访问*/
		for(TmTAppServiceConfig allApp:allTmTAppSegjrviceConfigList){
			for(TmTAppServiceConfig userApp:userTmTAppSegjrviceConfigList){
				if(allApp.getId().getServerCode().equals(userApp.getId().getServerCode())){
					method = allApp.getMethods();
					allApp.setMethods(method.substring(0, method.indexOf("_"))+method.substring(method.indexOf(".")));
					allApp.setIsAllow(1);
					if(defaultSystemCode != null && defaultSystemCode.equalsIgnoreCase(allApp.getId().getServerCode())){
						defaultSystem = allApp;
					}
					else{
						allAuthorizedAppSystemList.add(allApp);
					}
				}
			}
		}
		//将默认要访问的系统放在第一个位置
		if(defaultSystem != null){
			allAuthorizedAppSystemList.add(0, defaultSystem);
		}
		
		// 临时处理，增加反欺诈系统（待开发）
		TmTAppServiceConfigId tmTAppServiceConfigId = new TmTAppServiceConfigId();
		tmTAppServiceConfigId.setEnvironmentCode("4403test");
		tmTAppServiceConfigId.setServerCode("AntiFraud");
		allAuthorizedAppSystemList.add(tmTAppServiceConfigService.findTmTAppServiceConfigByPK(tmTAppServiceConfigId));
		
		//双重list，为了进行页面换行展示
		tmTAppServiceConfigTable = new LinkedList<List<TmTAppServiceConfig>>();
		if(allAuthorizedAppSystemList.size()==0){
			//无访问权限
			return "fail";
		}
		if(allAuthorizedAppSystemList.size()==1){
			//唯一一个系统，不需要进行系统选择，直接进入即可
			serverCode = allAuthorizedAppSystemList.get(0).getId().getServerCode();
			serverName = allAuthorizedAppSystemList.get(0).getServerName();
			return "default";
		}
		//设置统一门户的显示格式
		int i = 0;
		int tabLen = 4;
		List<TmTAppServiceConfig> list = null;
		tmTAppServiceConfigTable.add(list);
		for(TmTAppServiceConfig allApp:allAuthorizedAppSystemList){
			if(i%tabLen == 0){
				list = new ArrayList<TmTAppServiceConfig>();
				tmTAppServiceConfigTable.add(list);
			}
			list.add(allApp);
			i++;
		}
		return SUCCESS;
	}
	
	public String mainPage(){
		HttpSession session = this.getSession(true);
		if(session!=null){
			String serverCodeTemp = (String)session.getAttribute(Constants.SERVERCODE);
			String serverNameTemp = (String)session.getAttribute(Constants.SERVERNAME);

			if(StringUtils.isNotEmpty(serverCodeTemp)){
				serverCode = serverCodeTemp;
			}
			if(StringUtils.isNotEmpty(serverNameTemp)){
				serverName = serverNameTemp;
			}
		}
		return SUCCESS;
	}
	
}
