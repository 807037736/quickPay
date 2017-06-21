/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.picc.common.utils.AppConfig;
import com.picc.platform.dms.service.facade.ICompanyService;
import com.picc.tm.schema.model.TmTApplicationConfig;
import com.picc.tm.service.facade.ITmTApplicationConfigService;
import com.picc.um.cognos.service.facade.ICognosService;
import com.picc.um.cognos.utils.CRNConnect;
import com.picc.um.schema.model.UmTRole;
import com.picc.um.schema.model.UmTRoleCom;
import com.picc.um.schema.model.UmTRoleId;
import com.picc.um.schema.model.UmTRoleTask;
import com.picc.um.service.facade.IUmTRoleComService;
import com.picc.um.service.facade.IUmTRoleDimeService;
import com.picc.um.service.facade.IUmTRoleService;
import com.picc.um.service.facade.IUmTRoleTaskService;
import com.picc.um.service.facade.IUmTTaskService;

/**
 * 角色Action处理层
 * @author 沈一婵
 */
@SuppressWarnings("serial")
public class UmTRoleAction extends Struts2Action {

	private IUmTRoleService umTRoleService;

	private IUmTRoleComService umTRoleComService;

	private IUmTRoleTaskService umTRoleTaskService;

	private IUmTTaskService umTTaskService;

	private ICompanyService companyService;

	private IUmTRoleDimeService umTRoleDimeService;

	private ICognosService cognosService;

	private UmTRole umTRole;

	private UmTRoleId id;

	private List<String> roleIds; // 角色ID集合

	private String comStr; // 拼出一个机构Code的字符串，如comCodes=XX&comCodes=XX

	private String taskStr; // 拼出一个TaskID的字符串，如taskIds=XX&taskIds=XX

	private List<UmTRoleCom> roleComList; // 做修改时用

	private List<UmTRoleTask> roleTaskList; // 做修改用

	private String comTree = ""; // 机构树

	private String updatable = "yes"; // 是否是总公司角色

	private String roleCode;

	private String initRoleCode;

	private UmTRole initRole;
	
	private String serverCode;

	private ITmTApplicationConfigService tmTApplicationConfigService;
	
	public ITmTApplicationConfigService getTmTApplicationConfigService() {
		return tmTApplicationConfigService;
	}

	public void setTmTApplicationConfigService(
			ITmTApplicationConfigService tmTApplicationConfigService) {
		this.tmTApplicationConfigService = tmTApplicationConfigService;
	}
	/**
	 * 做角色与机构一对多的保存时用到
	 */
	private List<String> comCodes = new ArrayList<String>();
	private List<String> comNames = new ArrayList<String>();

	/**
	 * 做角色与功能一对多的保存时用到
	 */
	private List<String> taskIds = new ArrayList<String>();
	private List<String> taskNames = new ArrayList<String>();

	public UmTRole getInitRole() {
		return initRole;
	}

	public void setInitRole(UmTRole initRole) {
		this.initRole = initRole;
	}

	public ICognosService getCognosService() {
		return cognosService;
	}

	public void setCognosService(ICognosService cognosService) {
		this.cognosService = cognosService;
	}

	public String getInitRoleCode() {
		return initRoleCode;
	}

	public void setInitRoleCode(String initRoleCode) {
		this.initRoleCode = initRoleCode;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getUpdatable() {
		return updatable;
	}

	public void setUpdatable(String updatable) {
		this.updatable = updatable;
	}

	public String getComTree() {
		return comTree;
	}

	public void setComTree(String comTree) {
		this.comTree = comTree;
	}

	public ICompanyService getCompanyService() {
		return companyService;
	}

	public void setCompanyService(ICompanyService companyService) {
		this.companyService = companyService;
	}

	public IUmTTaskService getUmTTaskService() {
		return umTTaskService;
	}

	public void setUmTTaskService(IUmTTaskService umTTaskService) {
		this.umTTaskService = umTTaskService;
	}

	public void setUmTRoleService(IUmTRoleService umTRoleService) {
		this.umTRoleService = umTRoleService;
	}

	public IUmTRoleService getUmTRoleService() {
		return umTRoleService;
	}

	public List<String> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<String> roleIds) {
		this.roleIds = roleIds;
	}

	public String getComStr() {
		return comStr;
	}

	public void setComStr(String comStr) {
		this.comStr = comStr;
	}

	public List<String> getComCodes() {
		return comCodes;
	}

	public void setComCodes(List<String> comCodes) {
		this.comCodes = comCodes;
	}

	public List<String> getComNames() {
		return comNames;
	}

	public void setComNames(List<String> comNames) {
		this.comNames = comNames;
	}

	public String getTaskStr() {
		return taskStr;
	}

	public void setTaskStr(String taskStr) {
		this.taskStr = taskStr;
	}

	public List<String> getTaskIds() {
		return taskIds;
	}

	public void setTaskIds(List<String> taskIds) {
		this.taskIds = taskIds;
	}

	public List<String> getTaskNames() {
		return taskNames;
	}

	public void setTaskNames(List<String> taskNames) {
		this.taskNames = taskNames;
	}

	public IUmTRoleComService getUmTRoleComService() {
		return umTRoleComService;
	}

	public void setUmTRoleComService(IUmTRoleComService umTRoleComService) {
		this.umTRoleComService = umTRoleComService;
	}

	public IUmTRoleTaskService getUmTRoleTaskService() {
		return umTRoleTaskService;
	}

	public void setUmTRoleTaskService(IUmTRoleTaskService umTRoleTaskService) {
		this.umTRoleTaskService = umTRoleTaskService;
	}

	public List<UmTRoleCom> getRoleComList() {
		return roleComList;
	}

	public void setRoleComList(List<UmTRoleCom> roleComList) {
		this.roleComList = roleComList;
	}

	public List<UmTRoleTask> getRoleTaskList() {
		return roleTaskList;
	}

	public void setRoleTaskList(List<UmTRoleTask> roleTaskList) {
		this.roleTaskList = roleTaskList;
	}

	/** 操作类型 **/
	private String opreateType;

	/** UmTRole getter/setter **/
	public UmTRole getUmTRole() {
		return this.umTRole;
	}

	public void setUmTRole(UmTRole umTRole) {
		this.umTRole = umTRole;
	}

	/** UmTRoleId getter/setter **/
	public UmTRoleId getId() {
		return this.id;
	}

	public void setId(UmTRoleId id) {
		this.id = id;
	}

	/** opreateType getter/setter **/
	public String getOpreateType() {
		return opreateType;
	}

	public void setOpreateType(String opreateType) {
		this.opreateType = opreateType;
	}

	/** 主键对象 */
	private String roleId;

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public void setUmTRoleDimeService(IUmTRoleDimeService umTRoleDimeService) {
		this.umTRoleDimeService = umTRoleDimeService;
	}

	public IUmTRoleDimeService getUmTRoleDimeService() {
		return umTRoleDimeService;
	}

	/**************************** Function Start *******************************/

	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 */
	public String prepareQuery() throws Exception {
		TmTApplicationConfig tmTApplicationConfig = tmTApplicationConfigService.getApplicationByContext(this.getRequest().getContextPath().replaceFirst("/", ""));
		serverCode = tmTApplicationConfig.getId().getServerCode();
		return SUCCESS;
	}

	/**
	 * UmTRole查询，根据umTRole带过来的值为查询条件进行查询。
	 * 
	 * @return
	 */
	public String query() throws Exception {

		if (page == 0) {
			page = 1;
		}
		if (rows == 0) {
			rows = 20;
		}

		try {
			String comId = getUserFromSession().getComId();
			Page resultPage = umTRoleService.findByUmTRole(umTRole, page, rows,
					comId);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}

	/**
	 * 准备更新UmTRole信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		try {
			opreateType = "update";
			umTRole = umTRoleService.findUmTRoleByPK(id);

			// 拼comStr
			List<UmTRoleCom> comList = umTRoleComService.findRoleComByRoleId(id
					.getRoleId());
			List<String> coms = new ArrayList<String>();
			comStr = "";
			for (UmTRoleCom utc : comList) {
				if ("1".equals(utc.getValidStatus())) {
					// 只取有效的关联
					comStr += "&checkedNodes=" + utc.getComCode();
					coms.add(utc.getComCode());
				}

			}

			// 拼taskStr,
			// List<UmTTask> taskList =
			// umTTaskService.findByRoleId(id.getRoleId(),getUserFromSession().getComId());

			// taskStr = "";
			// for (UmTTask t : taskList) {
			// taskStr += "&checkedNodes=" + t.getId().getTaskId();
			// }

			// 判断是否是总公司的用户
			if (AppConfig.get("um.HEAD_COMID").equals(
					getUserFromSession().getComId())) {
				// 如果是总公司的用户则显示机构树,否则不显示机构树
				getRequest().setAttribute(AppConfig.get("um.IS_HEAD_OFFICE"),
						"yes");

				// 加载机构树
				comTree = companyService.findHeadComTree(coms);
			}

			// 判断当前用户是否可以更改角色的基本信息，如果当前角色是总公司角色且操作者不属于总公司
			// 则当前角色的基本信息不可修改
			boolean isHeadRole = umTRoleService.isHeadRole(id);
			if (!AppConfig.get("um.HEAD_COMID").equals(
					getUserFromSession().getComId())
					&& isHeadRole) {
				// 如果当前用户不属于总公司但当前角色是总公司角色，则不允许修改
				updatable = "no";
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}

		return SUCCESS;
	}

	/**
	 * 更新UmTRole信息
	 * 
	 * @return
	 */
	public String update() throws Exception {
		try {
			//查询出当前省级机构所有的角色
			List<Object[]> roleList = umTRoleService.findAllRole(getUserFromSession().getComId());
			for(Object[] o:roleList){
				if(!((String)o[0]).equals(initRole.getRoleCode()) && ((String)o[0]).equals(umTRole.getRoleCode())){
					throw new RuntimeException("角色代码不能重复!");
				}
				if(!((String)o[1]).equals(initRole.getRoleCName()) && ((String)o[1]).equals(umTRole.getRoleCName())){
					throw new RuntimeException("角色名称不能重复!");
				}
			}
			
			CRNConnect connection = (CRNConnect) getSession().getAttribute(
					AppConfig.get("um.COGNOS_CRN"));
			
			if(initRole.getFlag()!=null && initRole.getFlag().length()>0 && initRole.getFlag().substring(0, 1).equals("1")){
				//如果当前修改的角色是Cognos角色
				if(connection!=null){
					//修改Cognos角色
					cognosService.updateCognosRole(connection, initRole, umTRole,getUserFromSession().getComId());
				}else{
					throw new RuntimeException("当前角色是Cognos角色，需要Cognos角色方可修改!");
				}
			}
				// 修改角色信息、机构角色关联信息、功能角色关联信息
				umTRole.setUpdaterCode(getUserFromSession().getUserCode());
				umTRole.setOperateTimeForHis(new Date());
				umTRoleService
						.updateRoleComTask(umTRole, roleComList, roleTaskList,getUserFromSession().getComId());
				
				this.writeString("success");
		} catch (Exception e) {
			logger.error("", e);
			this.writeString(e.getMessage());
		}

		return NONE;
	}

	/**
	 * 同步RoleDime缓存信息
	 * 
	 * @return
	 * @throws Exception
	 *             2013-9-13上午11:09:59 jiangweiyang
	 */
	public String synCache() throws Exception {
		try {
			umTRoleDimeService.reBuildRoleDimeCache(getUserFromSession()
					.getComId());
			this.writeString("success");
		} catch (Exception e) {
			logger.error("", e);
			this.writeString(e.getMessage());
		}
		return NONE;
	}

	/**
	 * 准备增加UmTRole信息
	 * 
	 * @return
	 */
	public String prepareAdd() {
		try {
			opreateType = "add";
			TmTApplicationConfig tmTApplicationConfig = tmTApplicationConfigService.getApplicationByContext(this.getRequest().getContextPath().replaceFirst("/", ""));
			serverCode = tmTApplicationConfig.getId().getServerCode();
			// 判断是否是总公司的用户
			if (AppConfig.get("um.HEAD_COMID").equals(
					getUserFromSession().getComId())) {
				// 如果是总公司的用户则显示机构树,否则不显示机构树
				getRequest().setAttribute(AppConfig.get("um.IS_HEAD_OFFICE"),
						"yes");

				// 加载机构树
				comTree = companyService.findHeadComTree(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}

		return SUCCESS;
	}

	/**
	 * 新增UmTRole信息
	 * 
	 * @return
	 */
	public String add() throws Exception {

		// 关联保存机构、功能
		try {
			// 查询出当前省级机构所有的角色
			// 判断角色代码和角色名称不能重复
			List<Object[]> roleList = umTRoleService
					.findAllRole(getUserFromSession().getComId());
			for (Object[] o : roleList) {
				if (((String) o[0]).equals(umTRole.getRoleCode())) {
					throw new RuntimeException("角色代码不能重复!");
				}
				if (((String) o[1]).equals(umTRole.getRoleCName())) {
					throw new RuntimeException("角色名称不能重复!");
				}
			}

			umTRole.setCreatorCode(getUserFromSession().getUserCode());
			if (!AppConfig.get("um.HEAD_COMID").equals(
					getUserFromSession().getComId())) {
				// 如果当前用户不是总公司管理员
				comCodes.add(getUserFromSession().getComId());
				comNames.add(getUserFromSession().getComId());
			}
			umTRoleService.saveRoleComTask(umTRole, comCodes, comNames,
					taskIds, taskNames, getUserFromSession().getComId());
			this.writeString("success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeString(e.getMessage());
		}
		return NONE;
	}

	/**
	 * 删除UmTRole信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try {

			umTRoleService.deleteByPKList(roleIds);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			Map resultMap = new HashMap();
			resultMap.put("errorTitle", "错误信息(ERROR)：");
			resultMap.put("errorMsg", "删除数据时发生异常！");
			this.writeAjaxErrorByMap(resultMap);

		}
		return NONE;
	}

	/**
	 * 查看UmTRole信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {

		opreateType = "view";
		umTRole = umTRoleService.findUmTRoleByPK(id);
		return SUCCESS;
	}

	/**
	 * @param serverCode the serverCode to set
	 */
	public void setServerCode(String serverCode) {
		this.serverCode = serverCode;
	}

	/**
	 * @return the serverCode
	 */
	public String getServerCode() {
		return serverCode;
	}

}
