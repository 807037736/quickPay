/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.QueryRuleHelper;
import com.picc.common.dao.CommonDaoHibernate;
import com.picc.common.utils.AppConfig;
import com.picc.common.utils.SqlUtils;
import com.picc.common.utils.ToolsUtils;
import com.picc.um.dao.UmTRoleDaoHibernate;
import com.picc.um.schema.model.UmTRole;
import com.picc.um.schema.model.UmTRoleCom;
import com.picc.um.schema.model.UmTRoleComId;
import com.picc.um.schema.model.UmTRoleId;
import com.picc.um.schema.model.UmTRoleTask;
import com.picc.um.schema.model.UmTRoleTaskId;
import com.picc.um.schema.model.UmTUserRole;
import com.picc.um.schema.vo.UmTRoleVo;
import com.picc.um.service.facade.ICacheService;
import com.picc.um.service.facade.IUmTRoleComService;
import com.picc.um.service.facade.IUmTRoleDimeService;
import com.picc.um.service.facade.IUmTRoleService;
import com.picc.um.service.facade.IUmTRoleTaskService;

/**
 * 角色对象接口实现类
 * @author 沈一婵
 */
@Service("umTRoleService")
public class UmTRoleServiceSpringImpl implements IUmTRoleService {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UmTRoleDaoHibernate umTRoleDao;

	@Autowired
	private CommonDaoHibernate commonDao;

	@Autowired
	private IUmTRoleComService umTRoleComService;

	@Autowired
	private IUmTRoleTaskService umTRoleTaskService;

	@Autowired
	private ICacheService cacheService; // 缓存服务对象

	@Autowired
	private IUmTRoleDimeService umTRoleDimeService;

	/**
	 * 根据主键对象UmTRoleId获取UmTRole信息
	 * 
	 * @param UmTRoleId
	 * @return UmTRole
	 */
	public UmTRole findUmTRoleByPK(UmTRoleId id) throws Exception {
		return umTRoleDao.get(UmTRole.class, id);
	}

	/**
	 * 根据umTRole和页码信息，获取Page对象
	 * 
	 * @param umTRole
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTRole的Page对象
	 */
	@SuppressWarnings("unchecked")
	public Page findByUmTRole(UmTRole umTRole, int pageNo, int pageSize,
			String comId) throws Exception {
		// 查询语句
		StringBuffer sql = new StringBuffer(
				"select * from (select distinct a.* from um_t_role a,um_t_rolecom b where a.roleid=b.roleid and 1=1 ");
		// 拼机构条件
		if (!AppConfig.get("um.HEAD_COMID").equals(comId)) {
			// 如果不是总公司的管理员，则拼上限制条件
			sql.append(SqlUtils.convertString("b.comCode", comId));
		}

		// 查询条件
		String roleCName = umTRole.getRoleCName();
		String validStatus = umTRole.getValidStatus();
		//String flag = umTRole.getFlag();
		String userType = umTRole.getUserType();
		String serverCode = umTRole.getServerCode();
		String roleCode = umTRole.getRoleCode();
		if (!ToolsUtils.isEmpty(roleCName)) {
			sql.append(SqlUtils.convertString("a.roleCName", roleCName.trim()));
		}
		if (!ToolsUtils.isEmpty(validStatus)) {
			sql.append(SqlUtils.convertString("a.validStatus",
					validStatus.trim()));
		}
		if (!ToolsUtils.isEmpty(userType)) {
			sql.append(SqlUtils.convertString("a.userType", userType.trim()));
		}
		if (!ToolsUtils.isEmpty(serverCode)) {
			sql.append(SqlUtils.convertString("a.serverCode", serverCode.trim()));
		}
		if (!ToolsUtils.isEmpty(roleCode)) {
			sql.append(SqlUtils.convertString("a.roleCode", roleCode.trim()));
		}
		sql.append(" ) tt");
		
//		Page page = commonDao.findObjectPageBySql(sql.toString(), UmTRole.class, pageNo, pageSize);
//		List<UmTRole> list = page.getResult();
//		List<UmTRole> cronList = new ArrayList<UmTRole>(list);  //浅度克隆列表，否则无法在list循环中做删除
//		Set<String> set = new HashSet<String>();
//		
//		//元素去重
//		for(UmTRole r:cronList){
//			if(!set.add(r.getId().getRoleId())){
//				//如果set没加进去则表示是重复了的元素，删除之
//				list.remove(r);  //删除的不是对象本身，而是对对象的引用
//			}
//		}
		// System.out.println(list.size());
		// System.out.println(cronList.size());
		return commonDao.findObjectPageBySql(sql.toString(), UmTRole.class, pageNo, pageSize);
	}

	/**
	 * 更新UmTRole信息
	 * 
	 * @param UmTRole
	 */
	public void updateUmTRole(UmTRole umTRole) throws Exception {
		umTRoleDao.update(umTRole);
	}

	/**
	 * 保存UmTRole信息
	 * 
	 * @param UmTRole
	 */
	public void saveUmTRole(UmTRole umTRole) throws Exception {

		umTRoleDao.save(umTRole);
	}

	/**
	 * 根据主键对象，删除UmTRole信息
	 * 
	 * @param UmTRoleId
	 */
	public void deleteByPK(UmTRoleId id) throws Exception {
		umTRoleDao.deleteByPK(UmTRole.class, id);
	}

	public void deleteByPKList(List<String> roleIds) {

		for (String roleId : roleIds) {
			UmTRole umTRole = umTRoleDao.get(roleId);
			umTRoleDao.delete(umTRole);
		}
	}

	public void saveRoleComTask(UmTRole umTRole, List<String> comCodes,
			List<String> comNames, List<String> taskIds,
			List<String> taskNames, String comId) throws Exception {
		if (comCodes == null || comNames == null || comCodes.size() == 0
				|| comNames.size() == 0) {
			throw new RuntimeException("添加失败，角色必须某个属于机构！");
		}

		// 保存角色
		String roleId = commonDao.generateID("UMRO", "UM_SEQ_ROLE", 26);
		UmTRoleId umTRoleId = new UmTRoleId(roleId);
		umTRole.setId(umTRoleId);
		umTRole.setValidStatus(AppConfig.get("sysconst.VALID"));
		umTRoleDao.save(umTRole);

		// 保存角色-机构
		int len = comCodes.size();
		UmTRoleCom umTRoleCom = null;
		UmTRoleComId umTRoleComId = null;
		for (int i = 0; i < len; i++) {
			umTRoleComId = new UmTRoleComId(commonDao.generateID("UMRC","UM_SEQ_ROLECOM", 26));
			umTRoleCom = new UmTRoleCom();
			umTRoleCom.setId(umTRoleComId);
			umTRoleCom.setRoleId(roleId);
			umTRoleCom.setComCode(comCodes.get(i));
			umTRoleCom.setComName(comNames.get(i));
			umTRoleCom.setRoleCode(umTRole.getRoleCode());
			umTRoleCom.setCreatorCode(umTRole.getCreatorCode());
			umTRoleComService.saveUmTRoleCom(umTRoleCom);
		}

		try{
			// 保存角色-功能
			if (taskIds != null && taskNames != null && taskIds.size() > 0
					&& taskNames.size() > 0) {
				int l = taskIds.size();
				UmTRoleTask umTRoleTask;
				UmTRoleTaskId umTRoleTaskId;
				for (int j = 0; j < l; j++) {
					if (AppConfig.get("um.ZGSCOMID").equals(comId)) {
						// 总公司管理员添加共有的角色
						umTRoleTaskId = new UmTRoleTaskId(commonDao.generateID("UMRT", "UM_SEQ_ROLETASK", 26));
						umTRoleTask = new UmTRoleTask();
						umTRoleTask.setId(umTRoleTaskId);
						umTRoleTask.setRoleId(umTRole.getId().getRoleId());
						umTRoleTask.setTaskID(taskIds.get(j));
						// 设置为公有属性
						umTRoleTask.setScope(AppConfig.get("um.COMMON"));
						umTRoleTask.setCreatorCode(umTRole.getCreatorCode());
						umTRoleTaskService.saveUmTRoleTask(umTRoleTask);
					} else {
						// 分公司管理员添加独自的角色
						umTRoleTaskId = new UmTRoleTaskId(commonDao.generateID("UMRT", "UM_SEQ_ROLETASK", 26));
						umTRoleTask = new UmTRoleTask();
						umTRoleTask.setId(umTRoleTaskId);
						umTRoleTask.setRoleId(umTRole.getId().getRoleId());
						umTRoleTask.setTaskID(taskIds.get(j));
						// 设置为公有属性
						umTRoleTask.setScope(AppConfig.get("um.CITY"));
						umTRoleTask.setCreatorCode(umTRole.getCreatorCode());
						umTRoleTaskService.saveUmTRoleTask(umTRoleTask);
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void updateRoleComTask(UmTRole umTRole,
			List<UmTRoleCom> roleComList, List<UmTRoleTask> roleTaskList,
			String comId) throws Exception {
		// 修改角色本身的信息
		this.updateUmTRole(umTRole);
		
		if ("0".equals(umTRole.getValidStatus())) {
			// 将角色的有效状态置为无效(去除所有与该角色有关的访问URL)
			cacheService.updateRole(umTRole.getId().getRoleId(),
					umTRole.getRoleCode(), -1);
		} else if ("1".equals(umTRole.getValidStatus())) {
			// 将角色的有效状态置为有效(将与该角色所有有效的URL)
			cacheService.updateRole(umTRole.getId().getRoleId(),
					umTRole.getRoleCode(), 1);
		}
		String roleId = umTRole.getId().getRoleId();
		List<UmTRoleCom> roleCom = umTRoleComService
				.findRoleComByRoleId(roleId);
		List<UmTRoleTask> roleTask = umTRoleTaskService
				.findRoleTaskByRoleId(roleId);

		List<String> comCodeList = new ArrayList<String>();
		List<String> taskIdList = new ArrayList<String>();
		Map<String, UmTRoleCom> roleComMap = new HashMap<String, UmTRoleCom>();
		Map<String, UmTRoleTask> roleTaskMap = new HashMap<String, UmTRoleTask>();

		for (UmTRoleCom c : roleCom) {
			comCodeList.add(c.getComCode());
			roleComMap.put(c.getComCode(), c);
		}
		for (UmTRoleTask t : roleTask) {
			taskIdList.add(t.getTaskID());
			roleTaskMap.put(t.getTaskID(), t);
		}

		// 修改角色-机构关联信息
		UmTRoleCom umTRoleCom = null;
		UmTRoleComId umTRoleComId = null;
		if (roleComList != null && roleComList.size() > 0) {
			for (UmTRoleCom rc : roleComList) {
				if (comCodeList.contains(rc.getComCode())) {
					// 如果该机构已与角色关联，则修改它
					umTRoleCom = roleComMap.get(rc.getComCode());
					umTRoleCom.setValidStatus(rc.getValidStatus());
					umTRoleCom.setUpdaterCode(umTRole.getUpdaterCode());
					umTRoleComService.updateUmTRoleCom(umTRoleCom);
				} else {
					// 如果该机构没有与角色关联过，则入库
					umTRoleComId = new UmTRoleComId(commonDao.generateID("UMRC", "UM_SEQ_ROLECOM", 26));
					rc.setId(umTRoleComId);
					rc.setCreatorCode(umTRole.getUpdaterCode());
					rc.setValidStatus(AppConfig.get("sysconst.VALID"));
					umTRoleComService.saveUmTRoleCom(rc);
				}
			}
		}

		// 修改角色-功能关联信息
		UmTRoleTask umTRoleTask = null;
		UmTRoleTaskId umTRoleTaskId = null;
		if (roleTaskList != null && roleTaskList.size() > 0) {
			for (UmTRoleTask ut : roleTaskList) {
				if (taskIdList.contains(ut.getTaskID())) {
					// 如果该功能已与角色关联，则修改它
					umTRoleTask = roleTaskMap.get(ut.getTaskID());

					umTRoleTask.setUpdaterCode(umTRole.getUpdaterCode());
					if ("1".equals(ut.getValidStatus())) {
						// 往角色Role中添加新功能
						cacheService.updateUrlRoleMatcher(ut.getRoleId(),
								ut.getTaskID(), 1);
					} else if ("0".equals(ut.getValidStatus())) {
						// 从角色中去除功能
						try{
						cacheService.updateUrlRoleMatcher(ut.getRoleId(),
								ut.getTaskID(), -1);
						}catch(Exception ex){
							logger.error(ex.getMessage());
						}
					}

					if (AppConfig.get("um.ZGSCOMID").equals(comId)) {
						umTRoleTask.setValidStatus(ut.getValidStatus());
						umTRoleTaskService.updateUmTRoleTask(umTRoleTask);
					} else {
						// 分公司管理员操作
						if (AppConfig.get("um.COMMON").equals(
								umTRoleTask.getScope())) {
							// 如果是总版角色功能
							if ("1".equals(ut.getValidStatus())
									&& "0".equals(umTRoleTask.getValidStatus())) {
								// 在总版角色功能中新增功能
								umTRoleDimeService.updateRoleTaskByRoleDimeCom(
										ut, 1, comId,
										umTRoleTask.getUpdaterCode());
							} else if ("0".equals(ut.getValidStatus())
									&& "1".equals(umTRoleTask.getValidStatus())) {
								// 在总版角色功能中删除模块功能
								umTRoleDimeService.updateRoleTaskByRoleDimeCom(
										ut, 2, comId,
										umTRoleTask.getUpdaterCode());
							} else {
								// 还原总版本角色功能维度处理
								umTRoleDimeService.updateRoleTaskByRoleDimeCom(
										ut, 3, comId,
										umTRoleTask.getUpdaterCode());
							}
						} else {
							umTRoleTask.setValidStatus(ut.getValidStatus());
							umTRoleTaskService.updateUmTRoleTask(umTRoleTask);
						}
					}
				} else {
					// 如果该功能没有与角色关联过，则入库
					umTRoleTaskId = new UmTRoleTaskId(commonDao.generateID("UMRT", "UM_SEQ_ROLETASK", 26));
					ut.setId(umTRoleTaskId);
					ut.setCreatorCode(umTRole.getUpdaterCode());
					ut.setValidStatus(AppConfig.get("sysconst.VALID"));
					ut.setScope(AppConfig.get("um.CITY"));
					umTRoleTaskService.saveUmTRoleTask(ut);
					cacheService.updateUrlRoleMatcher(ut.getRoleId(),
							ut.getTaskID(), 1);
				}
			}
		}
		
	}

	/**
	 * 查询有效的角色信息
	 */
	public List<UmTRole> findValidUmTRoleList() {
		/** 构造Role查询对象实例 **/
		UmTRole roleQuery = new UmTRole();
		roleQuery.setValidStatus("1"); // 只获取有效的角色信息
		QueryRule rule = QueryRuleHelper.generateQueryRule(roleQuery);
		return umTRoleDao.find(rule);
	}

	/**
	 * 根据用户代码查询人员角色
	 */
	@SuppressWarnings("unchecked")
	public List<UmTRole> findByUserCode(String userCode) {
		String hql = "select ur from UmTRole ur,UmTUserRole uur where ur.id.roleId=uur.roleId and uur.userCode=? and ur.validStatus = '1' and uur.validStatus = '1'";
		return umTRoleDao.findByHql(hql, userCode);
	}

	public Page findByComCode(String comCode, int page, int rows) {
		String hql = "select ur from UmTRole ur,UmTRoleCom urc where ur.id.roleId=urc.roleId and ur.validStatus='1' and urc.validStatus='1' and comCode=?";
		return umTRoleDao.findByHqlNoLimit(hql, page, rows, comCode);
	}

	/**
	 * 更改角色的标识为 cognos角色
	 * 
	 * @author shenyichan
	 */
	public void updateToCognosRole(UmTRole role) {
		umTRoleDao.updateToCognosRole(role.getId().getRoleId());
	}

	/**
	 * 返回有效的角色列表对象
	 */
	public Page findValidUmTRole(int page, int rows, String comCode) {
		UmTRole umTRole = new UmTRole();
		umTRole.setValidStatus("1");
		try {
			return findByUmTRole(umTRole, page, rows, comCode);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}
		return null;
	}

	/**
	 * 判断当前角色是否为总公司角色
	 * 
	 * @author shenyichan
	 */
	@SuppressWarnings("unchecked")
	public boolean isHeadRole(UmTRoleId id) {
		String hql = "from UmTRoleCom where roleId=? and comCode='00000000'";
		List<UmTRole> roleList = umTRoleDao.findByHql(hql, id.getRoleId());
		if (roleList != null && roleList.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 查询当前省级机构下所有角色名称
	 * @param comId
	 * @return
	 */
	public List<Object[]> findAllRole(String comId) {
		// 查询语句
		StringBuffer sql = new StringBuffer(
				"select roleCode,roleCName from (select distinct a.* from um_t_role a,um_t_rolecom b where a.roleid=b.roleid and 1=1 ");
		// 拼机构条件
		if (!AppConfig.get("um.HEAD_COMID").equals(comId)) {
			// 如果不是总公司的管理员，则拼上限制条件
			sql.append(SqlUtils.convertString("b.comCode", comId));
		}
		sql.append(SqlUtils.convertString("a.validStatus","1")+" ) tt");
		List<Object[]> list = umTRoleDao.findBySql(sql.toString());
		return list;
	}
	/***
	 * 查询所有的角色代码
	 */
	public List<UmTRoleVo> findRoleByServercode(String serverCode) throws Exception{
		StringBuffer sql =new StringBuffer("select a.roleid,a.rolecname from um_t_role a where a.servercode =?");
		List<String> params =new ArrayList<String>();
		params.add(serverCode.substring(1));
		return commonDao.findBySql(UmTRoleVo.class, sql.toString(), 0, Integer.MAX_VALUE, params.toArray()).getResult();
	}

	@Override
	public List<UmTUserRole> findRoleByRoleCode(String roleCode) throws Exception {
	    String hql = "select uur from UmTRole ur,UmTUserRole uur where ur.id.roleId=uur.roleId and ur.roleCode=? and ur.validStatus = '1' and uur.validStatus = '1'";
	    return umTRoleDao.findByHql(hql, roleCode);
	}
}
