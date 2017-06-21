package com.picc.um.cognos.service.spring;

/**
 * Cognos自定义接口实现类
 * @author shenyichan
 */
import ins.framework.cache.CacheManager;
import ins.framework.cache.CacheService;
import ins.framework.common.ServiceFactory;
import ins.framework.web.Struts2Action;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognos.developer.schemas.bibus._3.AccessEnum;
import com.cognos.developer.schemas.bibus._3.Account;
import com.cognos.developer.schemas.bibus._3.Analysis;
import com.cognos.developer.schemas.bibus._3.BaseClass;
import com.cognos.developer.schemas.bibus._3.Dashboard;
import com.cognos.developer.schemas.bibus._3.Folder;
import com.cognos.developer.schemas.bibus._3.InteractiveReport;
import com.cognos.developer.schemas.bibus._3.OrderEnum;
import com.cognos.developer.schemas.bibus._3.PackageConfiguration;
import com.cognos.developer.schemas.bibus._3.Pagelet;
import com.cognos.developer.schemas.bibus._3.Permission;
import com.cognos.developer.schemas.bibus._3.Policy;
import com.cognos.developer.schemas.bibus._3.PolicyArrayProp;
import com.cognos.developer.schemas.bibus._3.PropEnum;
import com.cognos.developer.schemas.bibus._3.QueryOptions;
import com.cognos.developer.schemas.bibus._3.Report;
import com.cognos.developer.schemas.bibus._3.ReportView;
import com.cognos.developer.schemas.bibus._3.Role;
import com.cognos.developer.schemas.bibus._3.SearchPathMultipleObject;
import com.cognos.developer.schemas.bibus._3.Sort;
import com.cognos.developer.schemas.bibus._3.URL;
import com.cognos.developer.schemas.bibus._3.UpdateOptions;
import com.picc.common.utils.AppConfig;
import com.picc.common.utils.ToolsUtils;
import com.picc.common.vo.TreeNode;
import com.picc.tm.common.schema.model.TmTSystemConfig;
import com.picc.tm.common.service.facade.ITmTSystemConfigService;
import com.picc.um.cognos.service.facade.ICognosService;
import com.picc.um.cognos.shema.CognosEnvironmentModel;
import com.picc.um.cognos.shema.CognosMenuVo;
import com.picc.um.cognos.utils.CRNConnect;
import com.picc.um.cognos.utils.CSHandlers;
import com.picc.um.cognos.utils.GroupsAndRoles;
import com.picc.um.cognos.utils.Logon;
import com.picc.um.schema.model.UmTAccount;
import com.picc.um.schema.model.UmTRole;
import com.picc.um.schema.model.UmTUser;
import com.picc.um.schema.model.UmTUserGroup;
import com.picc.um.schema.vo.SessionUser;
import com.picc.um.service.facade.IUmTAccountService;
import com.picc.um.service.facade.IUmTRoleService;
import com.picc.um.service.facade.IUmTUserGroupService;
import com.picc.um.service.facade.IUmTUserService;

@Service("cognosService")
public class CognosServiceSpringImpl implements ICognosService {
	private static final Logger logger = LoggerFactory.getLogger(CognosServiceSpringImpl.class);
	private static CacheService cognosConfigCacheService = CacheManager.getInstance("COGNOS_CONFIG");
	public static String BASENODENAME = "统计分析系统";
	public static final String P_READ = "read";
	public static final String FOLDER = "folder";
	public static final String PACKAGE = "package";
	public static final String ANALYSIS = "analysis";
	public static final String REPORT = "report";
	public static final String URL = "url";
	public static final String PAGELET = "pagelet";
	public static final String REPORTVIEW = "reportview";
	public static final String DASHBOARD = "dashboard";
	public static final String INTERACTIVEREPORT = "interactivereport";
	public static final String GROUP = "group";
	public static final String ROLE = "role";

	@Autowired
	private IUmTRoleService umTRoleService;

	@Autowired
	private IUmTUserService umTUserService;
	
	@Autowired
	private IUmTUserGroupService  umTUserGroupService;
	
	@Autowired
	private ITmTSystemConfigService tmTSystemConfigService;
	
	@SuppressWarnings("unchecked")
	public String findMenuByUserCode(CRNConnect connection, String userCode,String comId,String netType)
			throws Exception {
		CognosEnvironmentModel model = (CognosEnvironmentModel) cognosConfigCacheService.getCache(cognosConfigCacheService.generateCacheKey("CONFIG",comId));
		if(model==null){
			initCognosConfigSet();					//初始化数据
			model = (CognosEnvironmentModel) cognosConfigCacheService.getCache(cognosConfigCacheService.generateCacheKey("CONFIG",comId));
			if(model==null){
				throw new Exception("无法获取Cognos菜单Model对象");
			}
		}
		
		// 查询出所有Cognos菜单
		List<CognosMenuVo> cognosMenuVoList = new ArrayList<CognosMenuVo>();
		String menuKey = cognosConfigCacheService.generateCacheKey("MENU",comId);
		cognosMenuVoList = (List<CognosMenuVo>)cognosConfigCacheService.getCache(menuKey);				//取出缓存中的数据
		if(cognosMenuVoList==null||cognosMenuVoList.size()<1){
			initCognosConfigSet();					//初始化数据
			cognosMenuVoList = (List<CognosMenuVo>)cognosConfigCacheService.getCache(menuKey);				//取出缓存中的数据
		}
		if(cognosMenuVoList!=null){
			// 查询出当前用户所有的角色名称集合
			List<UmTRole> roleList = umTRoleService.findByUserCode(userCode);
			List<String> roleNameList = new ArrayList<String>();
	
			// 将角色名称包装成Cognos的格式
			for (int i = 0; i < roleList.size(); i++) {
				String roleName = "CAMID(\":"+model.getRoleFolder()+":"
						+ roleList.get(i).getRoleCName().trim() + "\")";
				roleNameList.add(roleName);
			}
		
			// 根据角色名称遍历菜单
			List<TreeNode> menuNodeList = findMenuListByRoleNameList(
					cognosMenuVoList, roleNameList, comId,netType);
			
			// 将菜单转成json格式字符串
			JSONArray jsonArray = JSONArray.fromObject(menuNodeList);
	
			String menuStr = jsonArray.toString();
			menuStr = menuStr.replace("\'", ""); // 去掉'，easy-ui
													// tree的data特性不支持除’外的'普通字符
			menuStr = menuStr.replace("\"", "\'");
			return menuStr;
		} else {
			throw new Exception("获取的所有Cognos菜单为空");
		}
	}

	private List<TreeNode> findMenuListByRoleNameList(
			List<CognosMenuVo> allCognosMenuVoList, List<String> roleNameList,String comId,String netType)
			throws Exception {
		// 根据角色获取菜单
		List<CognosMenuVo> menuByRoleList = new ArrayList<CognosMenuVo>();
		List<CognosMenuVo> parentMenuList = new ArrayList<CognosMenuVo>();
		for (CognosMenuVo cmv : allCognosMenuVoList) {
			if(cmv.getMenu().getPolicies().getValue()!=null){
				for (int i = 0; i < cmv.getMenu().getPolicies().getValue().length; i++) {
					if (roleNameList.contains(cmv.getMenu().getPolicies()
							.getValue()[i].getSecurityObject().getSearchPath()
							.getValue())) {
						// 将节点本身及时父节点都加入list当中
						// 遍历角色下的菜单，查出其所有父级节点
						menuByRoleList.addAll(expandParentMenu(cmv,
								allCognosMenuVoList, parentMenuList));
						// 菜单去重
						menuByRoleList = reBuildList(menuByRoleList);
						parentMenuList.clear();
						break;
					}
				}
			}
			
		}

		List<TreeNode> menuNodeList = new ArrayList<TreeNode>(); // 菜单List
		TreeNode node = null;
		if (menuByRoleList.size() > 0) {
			for (CognosMenuVo cmObj : menuByRoleList) {
				/** 首先找出所有的根菜单 **/
				if (cmObj.getUpperMenuId() == null
						|| "".equals(cmObj.getUpperMenuId())) {
					node = wrapCatalog(cmObj.getMenu(),comId,netType);
					if (node.isHasChildren()) {
						expandChildNode(node, menuByRoleList,comId,netType);
					}
					menuNodeList.add(node);
				}

			}
		}
		return menuNodeList;
	}

	/**
	 * 根据父节点找子节点
	 * 
	 * @param get_value
	 * @param allCognosMenuVoList
	 * @return
	 */
	private void expandChildNode(TreeNode parentMenuNode,
			List<CognosMenuVo> allCognosMenuVoList,String comId,String netType) throws Exception {
		if (parentMenuNode == null || allCognosMenuVoList == null) {
			return;
		} else {
			TreeNode node = null;
			for (CognosMenuVo cmObj : allCognosMenuVoList) {
				if (parentMenuNode.getId().equals(cmObj.getUpperMenuId())) {
					node = wrapCatalog(cmObj.getMenu(),comId,netType);
					if (node.isHasChildren()) {
						expandChildNode(node, allCognosMenuVoList,comId,netType);
					}
					if(node.getId()!=null && !"".equals(node.getId()))
					parentMenuNode.addChildNode(node);
				}
			}
			//加入排序功能
			sortTreeNode(parentMenuNode);
		}
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void sortTreeNode(TreeNode parentMenuNode){
		if(parentMenuNode!=null&&parentMenuNode.getChildren()!=null&&parentMenuNode.getChildren().size()>1){
			Comparator cmp = Collator.getInstance(java.util.Locale.CHINA);
			String [] nameArray = new String[parentMenuNode.getChildren().size()];
			int index = 0;
			List<TreeNode> childList = parentMenuNode.getChildren();
			for(TreeNode treeNode : childList){
				nameArray[index++] = treeNode.getText();
			}
			Arrays.sort(nameArray, cmp);
			List<TreeNode> tmpList = new ArrayList<TreeNode>();
			/**对于子节点进行排序**/
			for(String nodeName : nameArray){
				for(TreeNode treeNode : childList){
					if(treeNode.getText().equals(nodeName)){
						tmpList.add(treeNode);
					}
				}
			}
			parentMenuNode.setChildren(tmpList);
		}
	}
	
	

	/**
	 * 对List去重
	 * 
	 * @param menuByRoleList
	 * @return
	 */
	private List<CognosMenuVo> reBuildList(List<CognosMenuVo> menuByRoleList) {
		// TODO Auto-generated method stub
		if (menuByRoleList != null) {
			List<CognosMenuVo> list = new ArrayList<CognosMenuVo>();
			for (CognosMenuVo cmv : menuByRoleList) {
				if (!list.contains(cmv)) {
					list.add(cmv);
				}
			}
			return list;
		} else {
			return menuByRoleList;
		}
	}

	/**
	 * 根据子菜单递归找父菜单直找根节点
	 * 
	 * @param cmv
	 * @param allCognosMenuVoList
	 * @return
	 */
	private Collection<? extends CognosMenuVo> expandParentMenu(
			CognosMenuVo cmv, List<CognosMenuVo> allCognosMenuVoList,
			List<CognosMenuVo> parentMenuList) {
		// TODO Auto-generated method stub
		parentMenuList.add(cmv);
		if (cmv != null) {
			for (CognosMenuVo cm : allCognosMenuVoList) {
				if (cm.getMenu().getStoreID().getValue().get_value()
						.equals(cmv.getUpperMenuId())) {
					expandParentMenu(cm, allCognosMenuVoList, parentMenuList);
					break;
				}
			}
		}
		return parentMenuList;
	}

	/**
	 * 查询所有Cognos菜单，封装成树
	 */
	public List<TreeNode> getCognosCatalog(CRNConnect connection,
			String searchPath,String comId,String netType) throws Exception {
		// 权限过滤
		StringBuffer permission = new StringBuffer("/*");
		if (searchPath.lastIndexOf("/") == (searchPath.length() - 1)) {
			permission = new StringBuffer("*");
		}
		if (searchPath.lastIndexOf("*") == (searchPath.length() - 1)) {
			permission = new StringBuffer("");
		}
		permission
				.append("[permission('read') or permission('traverse') or permission('write') or permission('execute') or permission('setPolicy') ]");

		// 构造子目录的SeachPath
		SearchPathMultipleObject cmSearchPath = new SearchPathMultipleObject(
				searchPath + permission);

		// 获取子目录的属性列
		PropEnum[] properties = { PropEnum.defaultName, PropEnum.permissions,
				PropEnum.objectClass, PropEnum.policies, PropEnum.searchPath,
				PropEnum.hasChildren, PropEnum.uri,
				PropEnum.defaultPortalAction, PropEnum.storeID, PropEnum.link,
				PropEnum.searchPathForURL };

		// 返回对象的排序规则，按名字顺序排列
		Sort[] sortBy = { new Sort() };
		sortBy[0].setOrder(OrderEnum.descending);
		sortBy[0].setPropName(PropEnum.displaySequence);

		// 定义目录信息容器
		List<TreeNode> cognosCatalogs = new ArrayList<TreeNode>();

		// 获取当前目录的所有子节点
		BaseClass[] catalog = null;
		try {
			catalog = connection.getCMService().query(cmSearchPath, properties,
					sortBy, new QueryOptions());
		} catch (java.rmi.RemoteException remoteEx) {
			remoteEx.printStackTrace();
			return cognosCatalogs;
		}

		// 获取目录节点信息，并装载到目录容器cognosCatalogs
		TreeNode node = null;
		for (int i = 0; i < catalog.length; i++) {
			node = wrapCatalog(catalog[i],comId,netType);
			// if(!"数据包".equals(cmObj.getDefaultName().getValue())){
			if (node.isHasChildren()) {
				node.setChildren(getCognosCatalog(connection, (String) node
						.getAttributes().get("searchPath"),comId,netType));
			}
			if (node.getId() != null) {
				cognosCatalogs.add(node);
			}
		}
		return cognosCatalogs;
	}

	/**
	 * 将角色(及角色下的人员)同步至Cognos角色
	 * 
	 * @param connection
	 * @param umTRole
	 * @author shenyichan
	 */
	public void addCognosRole(CRNConnect connection, UmTRole umTRole,
			String comId) throws Exception {
		CognosEnvironmentModel model = (CognosEnvironmentModel) cognosConfigCacheService.getCache(cognosConfigCacheService.generateCacheKey("CONFIG",comId));
		GroupsAndRoles gr = new GroupsAndRoles();
		// 角色管理命名空间
//		String roleNameSpace = AppConfig.get("sysconst.COGNOSSPACE_FUNC");
		String roleNameSpace ="CAMID(\":\")/namespaceFolder[@name='" + model.getRoleFolder() + "']";

		// 更改营销系统中角色的标识为cognos角色
		umTRoleService.updateToCognosRole(umTRole);

		// 将角色同步至Cognos
		gr.createRole(connection, umTRole.getRoleCName(), roleNameSpace);

		// 查询出当前角色下的所有用户
		List<UmTUser> userList = umTUserService.findUserByRole(umTRole.getId()
				.getRoleId(), comId);
		// 将用户同步至Cognos角色
		addUserToCognosRole(connection, umTRole, userList,comId);

	}

	
	public void addCognosGroup(CRNConnect connection, String groupType, String groupCode,
			String comId) throws Exception {
		//获取Cognos变量数据
		CognosEnvironmentModel model = (CognosEnvironmentModel) cognosConfigCacheService.getCache(cognosConfigCacheService.generateCacheKey("CONFIG",comId));
		GroupsAndRoles gr = new GroupsAndRoles();
		String groupNameSpace ="CAMID(\":\")/namespaceFolder[@name='" + model.getGroupFolder() + "']";			//获取Group所在的名空间
		List<UmTUser> userList = null;
		List<UmTUserGroup> userGroupList = null;
		List<String> userCodeList = new ArrayList<String>();
		if(!ToolsUtils.isEmpty(groupCode)){
			BaseClass[] baseClass = null;
			if(groupCode.indexOf(",")!=-1){
				//同时传入多个GroupCode
				String[] groupArray = groupCode.split(",");
				for(String group : groupArray){
					baseClass = gr.getMemberInfo(connection, "CAMID(\":"+model.getGroupFolder()+":" + group + "\")");
					if(baseClass==null||baseClass.length<1){
						//没有对应组织目录才进行数据创建
						gr.createGroup(connection, group, groupNameSpace);			//cognos端创建群组信息
					}
					
					if("comcode".equals(groupType)){
						//根据机构同步用户信息
						userList  = umTUserService.findUserByAccountAndComcode(groupCode, comId);
						for(UmTUser user : userList){
							userCodeList.add(user.getId().getUserCode());
						}
						addUserToCognosGroup(connection,group,userCodeList,comId);				//将用户添加至创建的群组中
					}else if("monopolycode".equals(groupType)){
						//根据车行同步用户信息
//						userGroupList = umTUserGroupService.find4sUserByGroupCode(groupCode, comId);
//						for(UmTUserGroup user : userGroupList){
//							userCodeList.add(user.getUserCode());
//						}
						userCodeList = umTUserGroupService.find4sUserByGroupCode2(group, comId);
						addUserToCognosGroup(connection,group,userCodeList,comId);				//将用户添加至创建的群组中
					}
					userCodeList.clear();
				}
			}else {
				baseClass = gr.getMemberInfo(connection, "CAMID(\":"+model.getGroupFolder()+":" + groupCode + "\")");
				if(baseClass==null||baseClass.length<1){
					//没有对应组织目录才进行数据创建
					gr.createGroup(connection, groupCode, groupNameSpace);			//cognos端创建群组信息
				}
				if("comcode".equals(groupType)){
					//根据机构同步用户信息
					userList = umTUserService.findUserByAccountAndComcode(groupCode, comId);
					for(UmTUser user : userList){
						userCodeList.add(user.getId().getUserCode());
					}
					addUserToCognosGroup(connection,groupCode,userCodeList,comId);				//将用户添加至创建的群组中
				}else if("monopolycode".equals(groupType)){
					//根据车行同步用户信息
					userGroupList = umTUserGroupService.find4sUserByGroupCode(groupCode, comId);
					for(UmTUserGroup user : userGroupList){
						userCodeList.add(user.getUserCode());
					}
					addUserToCognosGroup(connection,groupCode,userCodeList,comId);				//将用户添加至创建的群组中
				}
			}
		}
	}
	
	
	
	public void addCognosGroup(CRNConnect connection, String groupType, String groupCode,
			String comId,String userCode) throws Exception {
		//获取Cognos变量数据
		CognosEnvironmentModel model = (CognosEnvironmentModel) cognosConfigCacheService.getCache(cognosConfigCacheService.generateCacheKey("CONFIG",comId));
		GroupsAndRoles gr = new GroupsAndRoles();
		String groupNameSpace ="CAMID(\":\")/namespaceFolder[@name='" + model.getGroupFolder() + "']";			//获取Group所在的名空间
		List<String> userCodeList = new ArrayList<String>();
		if(!ToolsUtils.isEmpty(groupCode)){
			if(groupCode.indexOf(",")!=-1){
				//同时传入多个GroupCode
				String[] groupArray = groupCode.split(",");
				BaseClass[] baseClass = null;
				for(String group : groupArray){
					baseClass = gr.getMemberInfo(connection, "CAMID(\":"+model.getGroupFolder()+":" + groupCode + "\")");
					if(baseClass==null||baseClass.length<1){
						//没有对应组织目录才进行数据创建
						gr.createGroup(connection, groupCode, groupNameSpace);			//cognos端创建群组信息
					}
					if("comcode".equals(groupType)){
						//根据机构同步用户信息
						userCodeList.add(userCode);
						addUserToCognosGroup(connection,group,userCodeList,comId);				//将用户添加至创建的群组中
					}else if("monopolycode".equals(groupType)){
						//根据车行同步用户信息
						addUserToCognosGroup(connection,group,userCodeList,comId);				//将用户添加至创建的群组中
					}
					userCodeList.clear();
				}
			}else {
				BaseClass[] baseClass = gr.getMemberInfo(connection, "CAMID(\":"+model.getGroupFolder()+":" + groupCode + "\")");
				if(baseClass==null||baseClass.length<1){
					//没有对应组织目录才进行数据创建
					gr.createGroup(connection, groupCode, groupNameSpace);			//cognos端创建群组信息
				}
				if("comcode".equals(groupType)){
					//根据机构同步用户信息
					userCodeList.add(userCode);
					addUserToCognosGroup(connection,groupCode,userCodeList,comId);				//将用户添加至创建的群组中
				}else if("monopolycode".equals(groupType)){
					//根据车行同步用户信息
					userCodeList.add(userCode);
					addUserToCognosGroup(connection,groupCode,userCodeList,comId);				//将用户添加至创建的群组中
				}
			}
		}
	}
	
	
	
	

	/**
	 * 将用户添加至cognos组
	 */
	public void addUserToCognosGroup(CRNConnect connection, String groupCode,
			List<String> userList,String comId) throws Exception {
		CognosEnvironmentModel model = (CognosEnvironmentModel) cognosConfigCacheService.getCache(cognosConfigCacheService.generateCacheKey("CONFIG",comId));
		GroupsAndRoles gr = new GroupsAndRoles();
		// 角色搜索路径
		String pathOfRole = null;
		// 成员搜索路径
		String member = null;
		pathOfRole = "CAMID(\":"+model.getGroupFolder()+":" + groupCode + "\")";
		//System.out.println(pathOfRole);
		for (String userCode : userList) {
			member = "CAMID(\"dbAuth:u:" + userCode + "\")";
			// 把成员添加到角色中
			gr.addUserToGroup(connection, member, pathOfRole);
		}
	}
	
	

	/**
	 * 将用户全部添加至Cognos角色
	 * @param connection
	 * @param role
	 * @param userList
	 * @throws Exception
	 */
	public void addUserToCognosRole(CRNConnect connection, UmTRole role,
			List<UmTUser> userList,String comId) throws Exception {
		CognosEnvironmentModel model = (CognosEnvironmentModel) cognosConfigCacheService.getCache(cognosConfigCacheService.generateCacheKey("CONFIG",comId));
		GroupsAndRoles gr = new GroupsAndRoles();

		// 角色搜索路径
		String pathOfRole = null;
		// 成员搜索路径
		String member = null;
		pathOfRole = "CAMID(\":"+model.getRoleFolder()+":" + role.getRoleCName() + "\")";
		//System.out.println(pathOfRole);
		for (UmTUser user : userList) {
			member = "CAMID(\"dbAuth:u:" + user.getId().getUserCode() + "\")";

			// 把成员添加到角色中
			gr.addUserToRole(connection, member, pathOfRole);
		}
	}

	public String findAllMenu(CRNConnect connection, String searchPath,
			String roleName,String comId,String netType) throws Exception {
		List<TreeNode> menuNodeList = getCognosCatalog(connection, searchPath,comId,netType);
		boolean isChecked = false;
		if (menuNodeList != null && menuNodeList.size() > 0) {
			// 循环设置头节点的选中状态，并循环迭代各子节点，设置其选中状态
			for (TreeNode node : menuNodeList) {
				isChecked = setCheckedStatus(connection, roleName,
						(String) node.getAttributes().get("searchPath"),comId);
				node.setChecked(isChecked);
				node.putAttributes("initChecked", isChecked);
				setCheckedStatus(node, connection, roleName,comId);
			}

			JSONArray jsonArray = JSONArray.fromObject(menuNodeList);

			String menuStr = jsonArray.toString();
			// menuStr = menuStr.replace("\'", ""); // 去掉'，easy-ui
			// tree的data特性不支持除’外的'普通字符
			// menuStr = menuStr.replace("\"", "\'");

			return menuStr;
		} else {
			return "";
		}
	}

	/**
	 * 循环迭代遍历各节点，设置其选中状态
	 * 
	 * @param node
	 * @param connection
	 * @param roleName
	 */
	private void setCheckedStatus(TreeNode node, CRNConnect connection,
			String roleName,String comId) throws Exception {
		boolean isChecked = false;
		List<TreeNode> treeList = node.getChildren();
		if (treeList != null && treeList.size() > 0) {
			for (TreeNode curNode : treeList) {

				if (curNode.getAttributes() != null) {
					isChecked = setCheckedStatus(connection, roleName,
							(String) curNode.getAttributes().get("searchPath"),comId);
				}
				curNode.setChecked(isChecked);
				curNode.putAttributes("initChecked", isChecked);
				setCheckedStatus(curNode, connection, roleName,comId);
			}
		}

	}

	/**
	 * 根据所在角色和菜单的命名路径获取菜单的选中状态
	 * 
	 * @param connection
	 * @param roleName
	 * @param roleName2
	 * @return
	 */
	private boolean setCheckedStatus(CRNConnect connection, String roleName,
			String searchPath,String comId) throws Exception {
		CognosEnvironmentModel model = (CognosEnvironmentModel) cognosConfigCacheService.getCache(cognosConfigCacheService.generateCacheKey("CONFIG",comId));
		Permission[] permissions;
		Role role;
		boolean flag = false;
		if (searchPath != null) {
			String folder = searchPath;
			BaseClass results[] = new BaseClass[] {};
			PropEnum[] props = { PropEnum.defaultName, PropEnum.permissions,
					PropEnum.objectClass, PropEnum.searchPath,
					PropEnum.hasChildren, PropEnum.uri,
					PropEnum.defaultPortalAction, PropEnum.storeID,
					PropEnum.policies };

			// Cognos角色路径
			String pathOfRole = "CAMID(\":"+model.getRoleFolder()+":" + roleName + "\")";
			// 获取数据包节点对象
			results = connection.getCMService().query(
					new SearchPathMultipleObject(folder),
					new PropEnum[] { PropEnum.defaultName,
							PropEnum.permissions, PropEnum.objectClass,
							PropEnum.searchPath, PropEnum.hasChildren,
							PropEnum.uri, PropEnum.defaultPortalAction,
							PropEnum.storeID, PropEnum.policies },
					new Sort[] {}, new QueryOptions());

			if (results.length > 0) {
				String type = results[0].getObjectClass().getValue().getValue();
				if(results[0].getPolicies().getValue()!=null){
					for (int c = 0; c < results[0].getPolicies().getValue().length; c++) {
						if (pathOfRole
								.equals(results[0].getPolicies().getValue()[c]
										.getSecurityObject().getSearchPath()
										.getValue()))
							flag = true;
					}
				}
				
			}
		}
		return flag;
	}

	/**
	 * 检查当前角色的状态标识是否是Cognos角色
	 * 
	 * @param connection
	 * @param umTRole
	 * @return
	 * @author shenyichan
	 */
	public boolean checkCognosRole(CRNConnect connection, UmTRole umTRole, String comId)
			throws Exception {
		CognosEnvironmentModel model = (CognosEnvironmentModel) cognosConfigCacheService.getCache(cognosConfigCacheService.generateCacheKey("CONFIG",comId));
		boolean isCognosRole = false;
		GroupsAndRoles gr = new GroupsAndRoles();
		BaseClass[] cognosRole = gr.getAvailableRoles(connection, "CAMID(\":\")/namespaceFolder[@name='" + model.getRoleFolder() + "']");
		for (int i = 0; i < cognosRole.length; i++) {
			if (umTRole.getRoleCName().equals(
					cognosRole[i].getDefaultName().getValue())) {
				isCognosRole = true;
				break;
			}
		}

		if (AppConfig.get("sysconst.VALID").equals(umTRole.getValidStatus())) {
			String roleFlag = umTRole.getFlag();
			if (roleFlag != null && roleFlag.length() >= 1
					&& "1".equals(roleFlag.substring(0, 1)) && isCognosRole) {
				return true;
			} else {
				return false;
			}
		} else {
			throw new RuntimeException("当前不是有效的角色！");
		}
	}

	/**
	 * 将菜单添加至Cognos角色
	 * 
	 * @param connection
	 * @param roleName
	 * @param addSearchPath
	 * @param oc
	 * @throws Exception
	 */
	public void addMenuToCognosRole(CRNConnect connection, String roleName,
			List<String> addSearchPath, List<String> oc,String comId) throws Exception {
		CognosEnvironmentModel model = (CognosEnvironmentModel) cognosConfigCacheService.getCache(cognosConfigCacheService.generateCacheKey("CONFIG",comId));
		CSHandlers csHandler = new CSHandlers();
		int ocLen = oc.size();
		Permission[] permissions;
		Role role;
		// String[] CognosMenu= new String[addSearchPath.length];
		if (addSearchPath != null) {
			for (String folder : addSearchPath) {
				BaseClass results[] = new BaseClass[] {};
				PropEnum[] props = { PropEnum.defaultName,
						PropEnum.permissions, PropEnum.objectClass,
						PropEnum.searchPath, PropEnum.hasChildren,
						PropEnum.uri, PropEnum.defaultPortalAction,
						PropEnum.storeID, PropEnum.policies };

				// 角色路径
				String pathOfRole = "CAMID(\":"+model.getRoleFolder()+":" + roleName + "\")";
				// 获取数据包节点对象
				results = connection.getCMService().query(
						new SearchPathMultipleObject(folder),
						new PropEnum[] { PropEnum.defaultName,
								PropEnum.permissions, PropEnum.objectClass,
								PropEnum.searchPath, PropEnum.hasChildren,
								PropEnum.uri, PropEnum.defaultPortalAction,
								PropEnum.storeID, PropEnum.policies },
						new Sort[] {}, new QueryOptions());

				// BaseClass csFolder
				if (results.length > 0) {
					String type = results[0].getObjectClass().getValue()
							.getValue();
					if ("folder".equals(type)) {
						Folder csFolder = null;
						csFolder = (Folder) results[0];
						// 获取数据包节点中的人员权限对象
						//int l = 0;
						if(csFolder.getPolicies()
								.getValue()!=null){
							
						
						Policy[] pol1 = new Policy[csFolder.getPolicies()
													.getValue().length + 1];
						// 获取数据包节点中已存在的人员权限对象
						for (int c = 0; c < csFolder.getPolicies().getValue().length; c++) {
							pol1[c] = csFolder.getPolicies().getValue()[c];

						}
						// 获取对应路径的角色。
						role = (Role) csHandler.queryObjectInCS(connection,
								pathOfRole, props)[0];
						// 设置权限
						permissions = new Permission[ocLen];
						for (int k = 0; k < ocLen; k++) {
							Permission newPermission = new Permission();
							newPermission.setName(oc.get(k));
							newPermission.setAccess(AccessEnum.grant);
							permissions[k] = newPermission;
						}
						// 添加权限对象
						Policy pol = new Policy();

						pol1[csFolder.getPolicies().getValue().length] = pol;
						pol1[csFolder.getPolicies().getValue().length]
								.setPermissions(permissions);
						pol1[csFolder.getPolicies().getValue().length]
								.setSecurityObject(role);

						PolicyArrayProp prop = new PolicyArrayProp();
						prop.setValue(pol1);
						csFolder.setPolicies(prop);
						// 更新数据包
						BaseClass[] updatedItems = connection.getCMService()
								.update(new BaseClass[] { csFolder },
										new UpdateOptions());
						}
					} else if ("URL".equals(type)) {
						URL csFolder = null;
						csFolder = (URL) results[0];
						// 获取数据包节点中的人员权限对象
						if(csFolder.getPolicies()
								.getValue()!=null){
							Policy[] pol1 = new Policy[csFolder.getPolicies()
														.getValue().length + 1];
												// 获取数据包节点中已存在的人员权限对象
												for (int c = 0; c < csFolder.getPolicies().getValue().length; c++) {
													pol1[c] = csFolder.getPolicies().getValue()[c];

												}
												// 获取对应路径的角色。
												role = (Role) csHandler.queryObjectInCS(connection,
														pathOfRole, props)[0];
												// 设置权限
												permissions = new Permission[ocLen];
												for (int k = 0; k < ocLen; k++) {
													Permission newPermission = new Permission();
													newPermission.setName(oc.get(k));
													newPermission.setAccess(AccessEnum.grant);
													permissions[k] = newPermission;
												}
												// 添加权限对象
												Policy pol = new Policy();

												pol1[csFolder.getPolicies().getValue().length] = pol;
												pol1[csFolder.getPolicies().getValue().length]
														.setPermissions(permissions);
												pol1[csFolder.getPolicies().getValue().length]
														.setSecurityObject(role);

												PolicyArrayProp prop = new PolicyArrayProp();
												prop.setValue(pol1);
												csFolder.setPolicies(prop);
												// 更新数据包
												BaseClass[] updatedItems = connection.getCMService()
														.update(new BaseClass[] { csFolder },
																new UpdateOptions());
						}
						
					} else if ("report".equals(type)) {
						Report csFolder = null;
						csFolder = (Report) results[0];
						// 获取数据包节点中的人员权限对象
						if(csFolder.getPolicies()
								.getValue()!=null){
							Policy[] pol1 = new Policy[csFolder.getPolicies()
														.getValue().length + 1];
												// 获取数据包节点中已存在的人员权限对象
												for (int c = 0; c < csFolder.getPolicies().getValue().length; c++) {
													pol1[c] = csFolder.getPolicies().getValue()[c];

												}
												// 获取对应路径的角色。
												role = (Role) csHandler.queryObjectInCS(connection,
														pathOfRole, props)[0];
												// 设置权限
												permissions = new Permission[ocLen];
												for (int k = 0; k < ocLen; k++) {
													Permission newPermission = new Permission();
													newPermission.setName(oc.get(k));
													newPermission.setAccess(AccessEnum.grant);
													permissions[k] = newPermission;
												}
												// 添加权限对象
												Policy pol = new Policy();

												pol1[csFolder.getPolicies().getValue().length] = pol;
												pol1[csFolder.getPolicies().getValue().length]
														.setPermissions(permissions);
												pol1[csFolder.getPolicies().getValue().length]
														.setSecurityObject(role);

												PolicyArrayProp prop = new PolicyArrayProp();
												prop.setValue(pol1);
												csFolder.setPolicies(prop);
												// 更新数据包
												BaseClass[] updatedItems = connection.getCMService()
														.update(new BaseClass[] { csFolder },
																new UpdateOptions());
						}
						
					} else if ("report".equals(type)) {
						Report csFolder = null;
						csFolder = (Report) results[0];
						// 获取数据包节点中的人员权限对象
						if(csFolder.getPolicies()
								.getValue()!=null){
							Policy[] pol1 = new Policy[csFolder.getPolicies()
														.getValue().length + 1];
												// 获取数据包节点中已存在的人员权限对象
												for (int c = 0; c < csFolder.getPolicies().getValue().length; c++) {
													pol1[c] = csFolder.getPolicies().getValue()[c];

												}
												// 获取对应路径的角色。
												role = (Role) csHandler.queryObjectInCS(connection,
														pathOfRole, props)[0];
												// 设置权限
												permissions = new Permission[ocLen];
												for (int k = 0; k < ocLen; k++) {
													Permission newPermission = new Permission();
													newPermission.setName(oc.get(k));
													newPermission.setAccess(AccessEnum.grant);
													permissions[k] = newPermission;
												}
												// 添加权限对象
												Policy pol = new Policy();

												pol1[csFolder.getPolicies().getValue().length] = pol;
												pol1[csFolder.getPolicies().getValue().length]
														.setPermissions(permissions);
												pol1[csFolder.getPolicies().getValue().length]
														.setSecurityObject(role);

												PolicyArrayProp prop = new PolicyArrayProp();
												prop.setValue(pol1);
												csFolder.setPolicies(prop);
												// 更新数据包
												BaseClass[] updatedItems = connection.getCMService()
														.update(new BaseClass[] { csFolder },
																new UpdateOptions());
						}
						
					} else if ("analysis".equals(type)) {
						Analysis csFolder = null;
						csFolder = (Analysis) results[0];
						// 获取数据包节点中的人员权限对象
						if(csFolder.getPolicies()
								.getValue()!=null){
							Policy[] pol1 = new Policy[csFolder.getPolicies()
														.getValue().length + 1];
												// 获取数据包节点中已存在的人员权限对象
												for (int c = 0; c < csFolder.getPolicies().getValue().length; c++) {
													pol1[c] = csFolder.getPolicies().getValue()[c];

												}
												// 获取对应路径的角色。
												role = (Role) csHandler.queryObjectInCS(connection,
														pathOfRole, props)[0];
												// 设置权限
												permissions = new Permission[ocLen];
												for (int k = 0; k < ocLen; k++) {
													Permission newPermission = new Permission();
													newPermission.setName(oc.get(k));
													newPermission.setAccess(AccessEnum.grant);
													permissions[k] = newPermission;
												}
												// 添加权限对象
												Policy pol = new Policy();

												pol1[csFolder.getPolicies().getValue().length] = pol;
												pol1[csFolder.getPolicies().getValue().length]
														.setPermissions(permissions);
												pol1[csFolder.getPolicies().getValue().length]
														.setSecurityObject(role);

												PolicyArrayProp prop = new PolicyArrayProp();
												prop.setValue(pol1);
												csFolder.setPolicies(prop);
												// 更新数据包
												BaseClass[] updatedItems = connection.getCMService()
														.update(new BaseClass[] { csFolder },
																new UpdateOptions());
						}
						
					} else if ("interactiveReport".equals(type)) {
						InteractiveReport csFolder = null;
						csFolder = (InteractiveReport) results[0];
						// 获取数据包节点中的人员权限对象
						if(csFolder.getPolicies()
								.getValue()!=null){
							Policy[] pol1 = new Policy[csFolder.getPolicies()
														.getValue().length + 1];
												// 获取数据包节点中已存在的人员权限对象
												for (int c = 0; c < csFolder.getPolicies().getValue().length; c++) {
													pol1[c] = csFolder.getPolicies().getValue()[c];

												}
												// 获取对应路径的角色。
												role = (Role) csHandler.queryObjectInCS(connection,
														pathOfRole, props)[0];
												// 设置权限
												permissions = new Permission[ocLen];
												for (int k = 0; k < ocLen; k++) {
													Permission newPermission = new Permission();
													newPermission.setName(oc.get(k));
													newPermission.setAccess(AccessEnum.grant);
													permissions[k] = newPermission;
												}
												// 添加权限对象
												Policy pol = new Policy();

												pol1[csFolder.getPolicies().getValue().length] = pol;
												pol1[csFolder.getPolicies().getValue().length]
														.setPermissions(permissions);
												pol1[csFolder.getPolicies().getValue().length]
														.setSecurityObject(role);

												PolicyArrayProp prop = new PolicyArrayProp();
												prop.setValue(pol1);
												csFolder.setPolicies(prop);
												// 更新数据包
												BaseClass[] updatedItems = connection.getCMService()
														.update(new BaseClass[] { csFolder },
																new UpdateOptions());
						}
						
					} else if (PACKAGE.equals(type)) {
						BaseClass csFolder = null;
						csFolder = (BaseClass) results[0];
						// 获取数据包节点中的人员权限对象
						if(csFolder.getPolicies()
								.getValue()!=null){
							Policy[] pol1 = new Policy[csFolder.getPolicies()
														.getValue().length + 1];
												// 获取数据包节点中已存在的人员权限对象
												for (int c = 0; c < csFolder.getPolicies().getValue().length; c++) {
													pol1[c] = csFolder.getPolicies().getValue()[c];

												}
												// 获取对应路径的角色。
												role = (Role) csHandler.queryObjectInCS(connection,
														pathOfRole, props)[0];
												// 设置权限
												permissions = new Permission[ocLen];
												for (int k = 0; k < ocLen; k++) {
													Permission newPermission = new Permission();
													newPermission.setName(oc.get(k));
													newPermission.setAccess(AccessEnum.grant);
													permissions[k] = newPermission;
												}
												// 添加权限对象
												Policy pol = new Policy();

												pol1[csFolder.getPolicies().getValue().length] = pol;
												pol1[csFolder.getPolicies().getValue().length]
														.setPermissions(permissions);
												pol1[csFolder.getPolicies().getValue().length]
														.setSecurityObject(role);

												PolicyArrayProp prop = new PolicyArrayProp();
												prop.setValue(pol1);
												csFolder.setPolicies(prop);
												// 更新数据包
												BaseClass[] updatedItems = connection.getCMService()
														.update(new BaseClass[] { csFolder },
																new UpdateOptions());
						}
						
					}
				}

			}
		}
	}

	/**
	 * 从Cognos角色中删除菜单
	 * 
	 * @param connection
	 * @param roleName
	 * @param parentSearchPath
	 * @Param delSearchPath
	 * @param oc
	 */
	public void delMenuFromCognosRole(CRNConnect connection, String roleName,
			List<String> delSearchPath, List<String> parentSearchPath,
			List<String> oc,String comId) throws Exception {
		CognosEnvironmentModel model = (CognosEnvironmentModel) cognosConfigCacheService.getCache(cognosConfigCacheService.generateCacheKey("CONFIG",comId));
		Permission[] permissions;
		Role role;
		boolean flag;
		boolean flag1;
		if (delSearchPath != null) {
			for (String folder : delSearchPath) {
				BaseClass results[] = new BaseClass[] {};
				PropEnum[] props = { PropEnum.defaultName,
						PropEnum.permissions, PropEnum.objectClass,
						PropEnum.searchPath, PropEnum.uri,
						PropEnum.defaultPortalAction, PropEnum.policies,
						PropEnum.storeID };

				// 角色路径
				String pathOfRole = "CAMID(\":"+model.getRoleFolder()+":" + roleName + "\")";
				// 获取数据包节点对象
				results = connection.getCMService().query(
						new SearchPathMultipleObject(folder),
						new PropEnum[] { PropEnum.defaultName,
								PropEnum.permissions, PropEnum.objectClass,
								PropEnum.searchPath, PropEnum.uri,
								PropEnum.defaultPortalAction,
								PropEnum.policies, PropEnum.storeID },
						new Sort[] {}, new QueryOptions());
				flag = false;
				if (results.length > 0) {
					String type = results[0].getObjectClass().getValue()
							.getValue();
					
					//此段的作用为：如果当前要删除的对象是最外层时，则不实行删除
//					for (int j = 0; j < parentSearchPath.size(); j++) {
//						if (folder.equals(parentSearchPath.get(j))) {
//							flag = true;
//						}
//					}
					
					if (!flag) {
						if ("folder".equals(type)) {
							if (menuBelongToRole(connection, roleName, folder, comId)) {
								int d = 0;
								Folder csFolder = null;
								csFolder = (Folder) results[0];

								Policy[] pol1 = new Policy[csFolder
										.getPolicies().getValue().length - 1];
								// 获取数据包节点中已存在的人员权限对象
								for (int c = 0; c < csFolder.getPolicies()
										.getValue().length; c++) {
									String path = csFolder.getPolicies()
											.getValue()[c].getSecurityObject()
											.getSearchPath().getValue();
									if (pathOfRole.equals(path)) {
										continue;
									}
									pol1[d] = csFolder.getPolicies().getValue()[c];
									d++;
								}
								PolicyArrayProp prop = new PolicyArrayProp();
								prop.setValue(pol1);
								csFolder.setPolicies(prop);
								// 更新数据包
								BaseClass[] updatedItems = connection
										.getCMService().update(
												new BaseClass[] { csFolder },
												new UpdateOptions());
								List<String> menu = findSubMenuSearchPath(
										connection, folder);
								// this.addmenuBelongToRole(connection,roleName,menu,jurisdiction);
								this.delMenuFromCognosRole(connection,
										roleName, delSearchPath,
										parentSearchPath, oc,comId);
							}
						} else if ("URL".equals(type)) {
							if (menuBelongToRole(connection, roleName, folder, comId)) {
								int d = 0;
								URL csFolder = null;
								csFolder = (URL) results[0];
								// 获取数据包节点中的人员权限对象
								Policy[] pol1 = new Policy[csFolder
										.getPolicies().getValue().length - 1];
								// 获取数据包节点中已存在的人员权限对象
								for (int c = 0; c < csFolder.getPolicies()
										.getValue().length; c++) {
									String path = csFolder.getPolicies()
											.getValue()[c].getSecurityObject()
											.getSearchPath().getValue();
									if (pathOfRole.equals(path)) {
										continue;
									}
									pol1[d] = csFolder.getPolicies().getValue()[c];
									d++;
								}

								PolicyArrayProp prop = new PolicyArrayProp();
								prop.setValue(pol1);
								csFolder.setPolicies(prop);

								// 更新数据包
								BaseClass[] updatedItems = connection
										.getCMService().update(
												new BaseClass[] { csFolder },
												new UpdateOptions());
							}
						} else if ("report".equals(type)) {
							if (menuBelongToRole(connection, roleName, folder, comId)) {
								int d = 0;
								Report csFolder = null;
								csFolder = (Report) results[0];

								Policy[] pol1 = new Policy[csFolder
										.getPolicies().getValue().length - 1];
								// 获取数据包节点中已存在的人员权限对象
								for (int c = 0; c < csFolder.getPolicies()
										.getValue().length; c++) {
									String path = csFolder.getPolicies()
											.getValue()[c].getSecurityObject()
											.getSearchPath().getValue();
									if (pathOfRole.equals(path)) {
										continue;
									}
									pol1[d] = csFolder.getPolicies().getValue()[c];
									d++;
								}
								PolicyArrayProp prop = new PolicyArrayProp();
								prop.setValue(pol1);
								csFolder.setPolicies(prop);
								// 更新数据包
								BaseClass[] updatedItems = connection
										.getCMService().update(
												new BaseClass[] { csFolder },
												new UpdateOptions());
							}
						} else if ("analysis".equals(type)) {
							if (menuBelongToRole(connection, roleName, folder, comId)) {
								int d = 0;
								Analysis csFolder = null;
								csFolder = (Analysis) results[0];
								// 获取数据包节点中的人员权限对象
								Policy[] pol1 = new Policy[csFolder
										.getPolicies().getValue().length - 1];
								// 获取数据包节点中已存在的人员权限对象
								for (int c = 0; c < csFolder.getPolicies()
										.getValue().length; c++) {
									String path = csFolder.getPolicies()
											.getValue()[c].getSecurityObject()
											.getSearchPath().getValue();
									if (pathOfRole.equals(path)) {
										continue;
									}
									pol1[d] = csFolder.getPolicies().getValue()[c];
									d++;
								}
								PolicyArrayProp prop = new PolicyArrayProp();
								prop.setValue(pol1);
								csFolder.setPolicies(prop);

								// 更新数据包
								BaseClass[] updatedItems = connection
										.getCMService().update(
												new BaseClass[] { csFolder },
												new UpdateOptions());
							}
						} else if ("interactiveReport".equals(type)) {
							if (menuBelongToRole(connection, roleName, folder, comId)) {
								int d = 0;
								InteractiveReport csFolder = null;
								csFolder = (InteractiveReport) results[0];
								// 获取数据包节点中的人员权限对象
								Policy[] pol1 = new Policy[csFolder
										.getPolicies().getValue().length - 1];
								// 获取数据包节点中已存在的人员权限对象
								for (int c = 0; c < csFolder.getPolicies()
										.getValue().length; c++) {
									String path = csFolder.getPolicies()
											.getValue()[c].getSecurityObject()
											.getSearchPath().getValue();
									if (pathOfRole.equals(path)) {
										continue;
									}
									pol1[d] = csFolder.getPolicies().getValue()[c];
									d++;
								}
								PolicyArrayProp prop = new PolicyArrayProp();
								prop.setValue(pol1);
								csFolder.setPolicies(prop);

								// 更新数据包
								BaseClass[] updatedItems = connection
										.getCMService().update(
												new BaseClass[] { csFolder },
												new UpdateOptions());
							}
						} else if ("folder".equals(type)) {

							if (menuBelongToRole(connection, roleName, folder, comId)) {
								int d = 0;
								Folder csFolder = null;
								csFolder = (Folder) results[0];

								Policy[] pol1 = new Policy[csFolder
										.getPolicies().getValue().length - 1];
								// 获取数据包节点中已存在的人员权限对象
								for (int c = 0; c < csFolder.getPolicies()
										.getValue().length; c++) {
									String path = csFolder.getPolicies()
											.getValue()[c].getSecurityObject()
											.getSearchPath().getValue();
									if (pathOfRole.equals(path)) {
										continue;
									}
									pol1[d] = csFolder.getPolicies().getValue()[c];
									d++;
								}
								PolicyArrayProp prop = new PolicyArrayProp();
								prop.setValue(pol1);
								csFolder.setPolicies(prop);
								// 更新数据包
								BaseClass[] updatedItems = connection
										.getCMService().update(
												new BaseClass[] { csFolder },
												new UpdateOptions());
								List<String> menu = findSubMenuSearchPath(
										connection, folder);
								// this.addmenuBelongToRole(connection,roleName,menu,jurisdiction);
								this.delMenuFromCognosRole(connection,
										roleName, menu, parentSearchPath, oc,comId);
							}
						} else if ("package".equals(type)) {
							if (menuBelongToRole(connection, roleName, folder,comId)) {
								int d = 0;
								BaseClass csFolder = null;
								csFolder = (BaseClass) results[0];

								Policy[] pol1 = new Policy[csFolder
										.getPolicies().getValue().length - 1];
								// 获取数据包节点中已存在的人员权限对象
								for (int c = 0; c < csFolder.getPolicies()
										.getValue().length; c++) {
									String path = csFolder.getPolicies()
											.getValue()[c].getSecurityObject()
											.getSearchPath().getValue();
									if (pathOfRole.equals(path)) {
										continue;
									}
									pol1[d] = csFolder.getPolicies().getValue()[c];
									d++;
								}
								PolicyArrayProp prop = new PolicyArrayProp();
								prop.setValue(pol1);
								csFolder.setPolicies(prop);
								// 更新数据包
								BaseClass[] updatedItems = connection
										.getCMService().update(
												new BaseClass[] { csFolder },
												new UpdateOptions());
								List<String> menu = findSubMenuSearchPath(
										connection, folder);
								// this.addmenuBelongToRole(connection,roleName,menu,jurisdiction);
								this.delMenuFromCognosRole(connection,
										roleName, menu, parentSearchPath, oc,comId);
							}
						}
					}
				}
				flag = false;
			}
		}
	}

	/**
	 * @param connection
	 * @param pathFolder
	 * @return
	 * @throws Exception
	 */
	public List<String> findSubMenuSearchPath(CRNConnect connection,
			String pathFolder) throws Exception {
		StringBuffer permission = new StringBuffer("/*");
		if (pathFolder.lastIndexOf("/") == (pathFolder.length() - 1)) {
			permission = new StringBuffer("*");
		}
		if (pathFolder.lastIndexOf("*") == (pathFolder.length() - 1)) {
			permission = new StringBuffer("");
		}
		ArrayList cognosCatalogs = new ArrayList();
		ArrayList cognosCatalog = new ArrayList();
		// folder=folder+superFolder;
		// 连接服务器
		if (connection.getCMService() != null) {

			Sort s[] = { new Sort() };
			s[0].setOrder(OrderEnum.ascending);
			s[0].setPropName(PropEnum.defaultName);
			PropEnum[] props = { PropEnum.defaultName, PropEnum.permissions,
					PropEnum.objectClass, PropEnum.searchPath,
					PropEnum.hasChildren, PropEnum.uri,
					PropEnum.defaultPortalAction, PropEnum.storeID };
			// 获取路径下的各节点对象
			BaseClass bc[] = connection.getCMService().query(
					new SearchPathMultipleObject(pathFolder + permission),
					props, s, new QueryOptions());
			List<String> menu = new ArrayList<String>();
			int a = 0;
			for (int i = 0; i < bc.length; i++) {
				if (pathFolder.equals(bc[i].getSearchPath().getValue())) {
					continue;
				}
				menu.add(bc[i].getSearchPath().getValue());
				a++;
			}
			return menu;
		}
		return null;
	}

	/**
	 * 判断菜单是否在Cognos角色中
	 * 
	 * @param connection
	 * @param roleName
	 * @param folder2
	 * @return
	 */
	public boolean menuBelongToRole(CRNConnect connection, String roleName,
			String menuPath,String comId) throws Exception {
		CognosEnvironmentModel model = (CognosEnvironmentModel) cognosConfigCacheService.getCache(cognosConfigCacheService.generateCacheKey("CONFIG",comId));
		Permission[] permissions;
		Role role;
		boolean flag = false;
		// String[] CognosMenu= new String[menuPath.length];
		if (menuPath != null) {
			String folder = menuPath;
			BaseClass results[] = new BaseClass[] {};
			PropEnum[] props = { PropEnum.defaultName, PropEnum.permissions,
					PropEnum.objectClass, PropEnum.searchPath,
					PropEnum.hasChildren, PropEnum.uri,
					PropEnum.defaultPortalAction, PropEnum.storeID,
					PropEnum.policies };

			// 角色路径
			String pathOfRole = "CAMID(\":"+model.getRoleFolder()+":" + roleName + "\")";
			// 获取数据包节点对象
			results = connection.getCMService().query(
					new SearchPathMultipleObject(folder),
					new PropEnum[] { PropEnum.defaultName,
							PropEnum.permissions, PropEnum.objectClass,
							PropEnum.searchPath, PropEnum.hasChildren,
							PropEnum.uri, PropEnum.defaultPortalAction,
							PropEnum.storeID, PropEnum.policies },
					new Sort[] {}, new QueryOptions());

			// BaseClass csFolder
			if (results.length > 0) {
				String type = results[0].getObjectClass().getValue().getValue();
				for (int c = 0; c < results[0].getPolicies().getValue().length; c++) {
					//System.out.println(results[0].getPolicies().getValue()[c]
					//		.getSecurityObject().getSearchPath().getValue());
					if (pathOfRole
							.equals(results[0].getPolicies().getValue()[c]
									.getSecurityObject().getSearchPath()
									.getValue()))
						flag = true;
				}
			}
		}
		return flag;
	}

	/**
	 * 查询出所有的Cognos菜单，封装成列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public void findAllCognosMenu(CRNConnect connection, String searchPath,
			String upperMenuId, List<CognosMenuVo> cognosMenuVoList)
			throws Exception {
		// 权限过滤
		StringBuffer permission = new StringBuffer("/*");
		if (searchPath.lastIndexOf("/") == (searchPath.length() - 1)) {
			permission = new StringBuffer("*");
		}
		if (searchPath.lastIndexOf("*") == (searchPath.length() - 1)) {
			permission = new StringBuffer("");
		}
		permission
				.append("[permission('read') or permission('traverse') or permission('write') or permission('execute') or permission('setPolicy') ]");

		// 构造子目录的SeachPath
		SearchPathMultipleObject cmSearchPath = new SearchPathMultipleObject(
				searchPath + permission);

		// 获取子目录的属性列
		PropEnum[] properties = { PropEnum.defaultName, PropEnum.permissions,
				PropEnum.objectClass, PropEnum.policies, PropEnum.searchPath,
				PropEnum.hasChildren, PropEnum.uri,
				PropEnum.defaultPortalAction, PropEnum.storeID, PropEnum.link,
				PropEnum.searchPathForURL };

		// 返回对象的排序规则，按名字顺序排列
		Sort[] sortBy = { new Sort() };
		sortBy[0].setOrder(OrderEnum.descending);
		sortBy[0].setPropName(PropEnum.displaySequence);

		// 获取当前目录的所有子节点
		BaseClass[] catalog = null;
		catalog = connection.getCMService().query(cmSearchPath, properties,
				sortBy, new QueryOptions());
		CognosMenuVo cognosMenuVo = null;
		for (BaseClass bc : catalog) {
			cognosMenuVo = new CognosMenuVo();
			cognosMenuVo.setUpperMenuId(upperMenuId);
			cognosMenuVo.setMenu(bc);
			cognosMenuVoList.add(cognosMenuVo);
			if (bc.getHasChildren().isValue()) {
				findAllCognosMenu(connection, bc.getSearchPath().getValue(), bc
						.getStoreID().getValue().get_value(), cognosMenuVoList);
			}
		}

	}

	/**
	 * 将Cognos的BaseClass对象封装成TreeNode对象
	 * 
	 * @param cmObj
	 * @return
	 * @throws Exception
	 */
	private TreeNode wrapCatalog(BaseClass cmObj,String comId,String netType) throws Exception {
		CognosEnvironmentModel model = (CognosEnvironmentModel) cognosConfigCacheService.getCache(cognosConfigCacheService.generateCacheKey("CONFIG",comId));
		//暂时写死
		model.setCognosUiGateWay("/p2pd/servlet/dispatch");
		String uiName = "";
		String action = "";
		String url = "";
		TreeNode node = new TreeNode();
		String nodeType = cmObj.getObjectClass().getValue().getValue();
		if (FOLDER.equalsIgnoreCase(nodeType)) {
			node.setId(cmObj.getStoreID().getValue().get_value());
			node.setText(cmObj.getDefaultName().getValue());
			node.putAttributes("nodeType", nodeType);
			node.putAttributes("searchPath", cmObj.getSearchPath().getValue());
			node.setHasChildren(cmObj.getHasChildren().isValue());
		} else if (PACKAGE.equalsIgnoreCase(nodeType)) {
			node.setId(cmObj.getStoreID().getValue().get_value());
			node.setText(cmObj.getDefaultName().getValue());
			node.putAttributes("nodeType", nodeType);
			node.putAttributes("searchPath", cmObj.getSearchPath().getValue());
			node.setHasChildren(cmObj.getHasChildren().isValue());
		} else if ("packageConfiguration".equalsIgnoreCase(nodeType)) {
			uiName = ((PackageConfiguration) cmObj).getDefaultName().getValue();
		} else if (ANALYSIS.equalsIgnoreCase(nodeType)) {
			Analysis ana = (Analysis) cmObj;
			action = ana.getDefaultPortalAction().getValue().getValue();
			if ("run".equalsIgnoreCase(action)) {
				url = model.getCognosUrl()
						+ "?b_action=cognosViewer&ui.action=run&ui.object="
						+ URLEncoder.encode(ana.getSearchPath().getValue(),
								"UTF-8") + "&ui.name="
						+ URLEncoder.encode(uiName, "UTF-8")
						+ "&run.outputFormat=&run.prompt=true&cv.header=false";
			} else if ("viewOutput".equalsIgnoreCase(action)) {
				url = model.getCognosUrl()
						+ "?b_action=xts.run&m=portal/launch.xts&ui.gateway="
						+ URLEncoder.encode(
								model.getCognosUiGateWay(),
								"UTF-8")
						+ "&ui.tool=AnalysisStudio&ui.action=edit&launch.openJSStudioInFrame=true&ui.object="
						+ URLEncoder.encode(ana.getSearchPath().getValue(),
								"UTF-8") + "&ui.name="
						+ URLEncoder.encode(uiName, "UTF-8")
						+ "&ui.drillThroughTargetParameterValues=";
			} else if ("edit".equalsIgnoreCase(action)) {
				url = model.getCognosUrl()
						+ "?b_action=xts.run&m=portal/launch.xts&ui.gateway="
						+ URLEncoder.encode(
								model.getCognosUiGateWay(),
								"UTF-8")
						+ "&ui.tool=AnalysisStudio&ui.action=edit&launch.openJSStudioInFrame=true&ui.object="
						+ URLEncoder.encode(ana.getSearchPath().getValue(),
								"UTF-8")
						+ "&ui.drillThroughTargetParameterValues=";
			}
			node.setId(ana.getStoreID().getValue().get_value());
			node.setText(ana.getDefaultName().getValue());
			node.putAttributes("nodeType", nodeType);
			node.putAttributes("searchPath", ana.getSearchPath().getValue());
			if(Struts2Action.NET_INNER.equals(netType)){
				node.putAttributes("url", url);
			}else {
				if(url!=null){
					url = url.substring(7);
					url = url.substring(url.indexOf("/"));
					node.putAttributes("url", "http://".concat(model.getOuterUrl()).concat(url));
				}
			}
		} else if (REPORT.equalsIgnoreCase(nodeType)) {
			Report rep = (Report) cmObj;
			action = rep.getDefaultPortalAction().getValue().getValue();
			// String url1 = rep.getSearchPathForURL().getValue();
			if ("run".equalsIgnoreCase(action)) {
				url = model.getCognosUrl()
						+ "?b_action=cognosViewer&ui.action=run&ui.object="
						+ URLEncoder.encode(rep.getSearchPath().getValue(),
								"UTF-8") + "&ui.name="
						+ URLEncoder.encode(uiName, "UTF-8")
						+ "&run.outputFormat=&run.prompt=true&cv.header=false";
			} else if ("viewOutput".equalsIgnoreCase(action)) {
				url = model.getCognosUrl()
						+ "?b_action=cognosViewer&ui.action=run&ui.object="
						+ URLEncoder.encode(rep.getSearchPath().getValue(),
								"UTF-8") + "&ui.name="
						+ URLEncoder.encode(uiName, "UTF-8")
						+ "&run.outputFormat=&run.prompt=true&cv.header=false";
			} else if ("edit".equalsIgnoreCase(action)) {
				url = model.getCognosUrl()
						+ "?b_action=xts.run&m=portal/launch.xts&ui.gateway="
						+ URLEncoder.encode(
								model.getCognosUiGateWay(),
								"UTF-8")
						+ "&ui.tool=ReportStudio&ui.action=edit&launch.openJSStudioInFrame=true&ui.object="
						+ URLEncoder.encode(rep.getSearchPath().getValue(),
								"UTF-8")
						+ "&ui.drillThroughTargetParameterValues=";
			}
			node.setId(rep.getStoreID().getValue().get_value());
			node.setText(rep.getDefaultName().getValue());
			node.putAttributes("nodeType", nodeType);
			node.putAttributes("searchPath", rep.getSearchPath().getValue());
			if(Struts2Action.NET_INNER.equals(netType)){
				node.putAttributes("url", url);
			}else {
				if(url!=null){
					url = url.substring(7);
					url = url.substring(url.indexOf("/"));
					node.putAttributes("url", "http://".concat(model.getOuterUrl()).concat(url));
				}
			}
		} else if (URL.equalsIgnoreCase(nodeType)) {
			URL urlOb = (URL) cmObj;
			url = urlOb.getUri().getValue();
			node.setId(urlOb.getStoreID().getValue().get_value());
			node.setText(urlOb.getDefaultName().getValue());
			node.putAttributes("nodeType", nodeType);
			node.putAttributes("searchPath", urlOb.getSearchPath().getValue());
			if(Struts2Action.NET_INNER.equals(netType)){
				node.putAttributes("url", url);
			}else {
				if(url!=null){
					url = url.substring(7);
					url = url.substring(url.indexOf("/"));
					node.putAttributes("url", "http://".concat(model.getOuterUrl()).concat(url));
				}
			}
		} else if (PAGELET.equalsIgnoreCase(nodeType)) {
			Pagelet pageletOb = (Pagelet) cmObj;
			url = model.getCognosUrl()
					+ "?b_action=dashboard&pathinfo=/cm&frag-header=false&path=storeID(%22"
					+ pageletOb.getStoreID().getValue() + "%22)";
			node.setId(pageletOb.getStoreID().getValue().get_value());
			node.setText(pageletOb.getDefaultName().getValue());
			node.putAttributes("nodeType", nodeType);
			node.putAttributes("searchPath", pageletOb.getSearchPath()
					.getValue());
			if(Struts2Action.NET_INNER.equals(netType)){
				node.putAttributes("url", url);
			}else {
				if(url!=null){
					url = url.substring(7);
					url = url.substring(url.indexOf("/"));
					node.putAttributes("url", "http://".concat(model.getOuterUrl()).concat(url));
				}
			}
		} else if (REPORTVIEW.equalsIgnoreCase(nodeType)) {
			ReportView reportView = (ReportView) cmObj;
			action = reportView.getDefaultPortalAction().getValue().getValue();
			if ("run".equalsIgnoreCase(action)) {
				url = model.getCognosUrl()
						+ "?b_action=cognosViewer&ui.action=run&ui.object="
						+ URLEncoder.encode(reportView.getSearchPath()
								.getValue(), "UTF-8") + "&ui.name="
						+ URLEncoder.encode(uiName, "UTF-8")
						+ "&run.outputFormat=&run.prompt=true&cv.header=false";
			} else if ("viewOutput".equalsIgnoreCase(action)) {
				url = model.getCognosUrl()
						+ "?b_action=cognosViewer&ui.action=run&ui.object="
						+ URLEncoder.encode(reportView.getSearchPath()
								.getValue(), "UTF-8") + "&ui.name="
						+ URLEncoder.encode(uiName, "UTF-8")
						+ "&run.outputFormat=&run.prompt=true&cv.header=false";
			}
			node.setId(reportView.getStoreID().getValue().get_value());
			node.setText(reportView.getDefaultName().getValue());
			node.putAttributes("nodeType", nodeType);
			node.putAttributes("searchPath", reportView.getSearchPath()
					.getValue());
			if(Struts2Action.NET_INNER.equals(netType)){
				node.putAttributes("url", url);
			}else {
				if(url!=null){
					url = url.substring(7);
					url = url.substring(url.indexOf("/"));
					node.putAttributes("url", "http://".concat(model.getOuterUrl()).concat(url));
				}
			}
		} else if (DASHBOARD.equalsIgnoreCase(nodeType)) {
			Dashboard dashBoard = (Dashboard) cmObj;
			url = model.getCognosUrl()
					+ "?b_action=dashboard&pathinfo=/cm&frag-header=false&path=storeID(%22"
					+ dashBoard.getStoreID().getValue() + "%22)";
			node.setId(dashBoard.getStoreID().getValue().get_value());
			node.setText(dashBoard.getDefaultName().getValue());
			node.putAttributes("nodeType", nodeType);
			node.putAttributes("searchPath", dashBoard.getSearchPath()
					.getValue());
			if(Struts2Action.NET_INNER.equals(netType)){
				node.putAttributes("url", url);
			}else {
				if(url!=null){
					url = url.substring(7);
					url = url.substring(url.indexOf("/"));
					node.putAttributes("url", "http://".concat(model.getOuterUrl()).concat(url));
				}
			}
		} else if (INTERACTIVEREPORT.equalsIgnoreCase(nodeType)) {
			InteractiveReport interactiveReport = (InteractiveReport) cmObj;
			action = interactiveReport.getDefaultPortalAction().getValue()
					.getValue();
			if ("run".equalsIgnoreCase(action)) {
				url = model.getCognosUrl()
						+ "?b_action=cognosViewer&ui.action=run&ui.object="
						+ URLEncoder.encode(interactiveReport.getSearchPath()
								.getValue(), "UTF-8") + "&ui.name="
						+ URLEncoder.encode(uiName, "UTF-8")
						+ "&run.outputFormat=&run.prompt=true&cv.header=false";
			} else if ("viewOutput".equalsIgnoreCase(action)) {
				url = model.getCognosUrl()
						+ "?b_action=cognosViewer&ui.action=run&ui.object="
						+ URLEncoder.encode(interactiveReport.getSearchPath()
								.getValue(), "UTF-8") + "&ui.name="
						+ URLEncoder.encode(uiName, "UTF-8")
						+ "&run.outputFormat=&run.prompt=true&cv.header=false";
			} else if ("edit".equalsIgnoreCase(action)) {
				url = model.getCognosUrl()  
						+ "?b_action=xts.run&m=portal/launch.xts&ui.gateway="
						+ URLEncoder.encode(
								model.getCognosUiGateWay(),
								"UTF-8")
						+ "&ui.tool=ReportStudio&ui.action=edit&launch.openJSStudioInFrame=true&ui.object="
						+ URLEncoder.encode(interactiveReport.getSearchPath()
								.getValue(), "UTF-8")
						+ "&ui.drillThroughTargetParameterValues=";
			}
			node.setId(interactiveReport.getStoreID().getValue().get_value());
			node.setText(interactiveReport.getDefaultName().getValue());
			node.putAttributes("nodeType", nodeType);
			node.putAttributes("searchPath", interactiveReport.getSearchPath()
					.getValue());
			if(Struts2Action.NET_INNER.equals(netType)){
				node.putAttributes("url", url);
			}else {
				if(url!=null){
					url = url.substring(7);
					url = url.substring(url.indexOf("/"));
					node.putAttributes("url", "http://".concat(model.getOuterUrl()).concat(url));
				}
			}
		}
		return node;
	}

	public void addSomeUserToCognosRole(CRNConnect connection, UmTRole role,
			List<UmTUser> userList,String comId) throws Exception {
		CognosEnvironmentModel model = (CognosEnvironmentModel) cognosConfigCacheService.getCache(cognosConfigCacheService.generateCacheKey("CONFIG",comId));
		if(userList != null && userList.size()>0){
			//查询出Cognos角色下所有的成员
			String pathOfRole = "CAMID(\":"+model.getRoleFolder()+":" + role.getRoleCName() + "\")";
			GroupsAndRoles gr = new GroupsAndRoles();
			List<Account> account = gr.getAvailableUsers(connection, pathOfRole);
			
			//将Account列表转化成搜索路径列表
			List<String> pathOfUser = new ArrayList<String>();
			if(account !=null && account.size()>0){
				//当Cognos角色中已存在部分成员时，剔除此部分成员，将剩余的成员加入Cognos
				for(Account ac : account){
					pathOfUser.add(ac.getSearchPath().getValue());
				}
				
				String userSearchPath = null;
				for(UmTUser u : userList){
					userSearchPath = "CAMID(\"dbAuth:u:" + u.getId().getUserCode() + "\")";
					if(!pathOfUser.contains(userSearchPath)){
						// 把成员添加到角色中
						gr.addUserToRole(connection, userSearchPath, pathOfRole);
					}
				}
				
			}else{
				//当Cognos角色中不存在任何成员时，将所有的成员都加入Cognos角色
				this.addUserToCognosRole(connection, role, userList,comId);
			}
		}
	}

	
	public void updateCognosRole(CRNConnect connection,UmTRole initRole, UmTRole role,String comId) {
		CognosEnvironmentModel model = (CognosEnvironmentModel) cognosConfigCacheService.getCache(cognosConfigCacheService.generateCacheKey("CONFIG",comId));
		GroupsAndRoles gr = new GroupsAndRoles();
		gr.updateCognosRole(connection, "CAMID(\":"+model.getRoleFolder()+":" + initRole.getRoleCName() + "\")", role);
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
	public void getCognosMaxMenu(UmTAccount account) throws Exception {
		// 根据当前用户Comcode获取Cognos的URL
		String comCode = umTUserService.findUmTUserByUserCode(account.getId().getUserCode()).getComCode();
		comCode = SessionUser.getComIdByComCode(comCode);														//进行comId转换处理
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
							account.getId().getUserCode(), account.getPassword());						//登录后台
				}
				if (account.getId().getUserCode().equals(result)) {
					// 登录成功后，返回用户的userName,通过Connection获取最大菜单集合
					List<CognosMenuVo> cognosMenuVoList = new ArrayList<CognosMenuVo>();
					String menuKey = cognosConfigCacheService.generateCacheKey("MENU",comCode);
					//if(cognosConfigCacheService.getCache(menuKey)==null){
						//如果缓存中没有数据,则从数据库中提取数据
							findAllCognosMenu(connection,
									AppConfig.get("sysconst.COGNOSSPACE_MENU"), null,
									cognosMenuVoList);
						cognosConfigCacheService.putCache(menuKey, cognosMenuVoList);														//加入缓存
//				} else {
//					System.err.println("COGNOS_ERROR:"+result);
//					throw new RuntimeException("Cognos用户或密码不正确！");
//				}
			} else {
				throw new RuntimeException("Cognos参数不匹配！请检查所在机构或Cognos系统参数配置！");
			}
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void initCognosConfigSet() throws Exception {
		TmTSystemConfig systemConfig = new TmTSystemConfig();
		systemConfig.setValidStatus(AppConfig.get("sysconst.VALID"));
		systemConfig.setConfigName("COGNOS");
		try {
			//	1.获取配置的Cognos菜单信息
			cognosConfigCacheService.clearCacheManager("COGNOS_CONFIG");
			List<TmTSystemConfig> systemConfigList = tmTSystemConfigService
					.findByTmTSystemConfig(systemConfig, 1, Integer.MAX_VALUE)
					.getResult();
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
			
			//2.根据最大权限的用户获取所有Cognos菜单集合
			if(model!=null&&model.getDefaultUserCode()!=null){
				//已配置最大权限用户代码
				IUmTAccountService accountService = (IUmTAccountService) ServiceFactory
						.getService("umTAccountService");
				UmTAccount  account = accountService.findUmTAccountByUserCode(model.getDefaultUserCode());
				ICognosService cognosService = (ICognosService)ServiceFactory.getService("cognosService");
				cognosService.getCognosMaxMenu(account);						//获取最大菜单集合,并将该菜单信息保存至缓存
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("", e);
		}
	}
	
	
	

}
