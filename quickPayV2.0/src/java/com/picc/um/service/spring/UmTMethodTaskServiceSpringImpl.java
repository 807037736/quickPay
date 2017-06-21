/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.spring;

import ins.framework.cache.CacheManager;
import ins.framework.cache.CacheService;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.dao.CommonDaoHibernate;
import com.picc.portal.schema.model.WfTPortletclassfy;
import com.picc.um.dao.UmTMethodTaskDaoHibernate;
import com.picc.um.schema.model.UmTMethodTask;
import com.picc.um.schema.model.UmTMethodTaskId;
import com.picc.um.schema.model.UmTTask;
import com.picc.um.schema.model.UmTTaskId;
import com.picc.um.service.facade.ICacheService;
import com.picc.um.service.facade.IUmTMENUService;
import com.picc.um.service.facade.IUmTMethodTaskService;
import com.picc.um.service.facade.IUmTTaskService;

/**
 * 功能方法接口实现类
 * @author 姜卫洋
 */
@Service("umTMethodTaskService")
public class UmTMethodTaskServiceSpringImpl implements IUmTMethodTaskService {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UmTMethodTaskDaoHibernate umTMethodTaskDao;

	@Autowired
	private CommonDaoHibernate commonDao;

	@Autowired
	private IUmTTaskService umTTaskService;

	@Autowired
	private IUmTMENUService umTMENUService;

	@Autowired
	private ICacheService cacheService;

	/** 角色与URL对应的映射关系 **/
	private static CacheService roleCacheService = CacheManager
			.getInstance("ROLE_URL_MATCHER");

	/**
	 * 根据主键对象UmTMethodTaskId获取UmTMethodTask信息
	 * 
	 * @param UmTMethodTaskId
	 * @return UmTMethodTask
	 */
	public UmTMethodTask findUmTMethodTaskByPK(UmTMethodTaskId id)
			throws Exception {

		return umTMethodTaskDao.get(UmTMethodTask.class, id);
	}

	/**
	 * 根据umTMethodTask和页码信息，获取Page对象
	 * 
	 * @param umTMethodTask
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTMethodTask的Page对象
	 */
	public Page findByUmTMethodTask(UmTMethodTask umTMethodTask, int pageNo,
			int pageSize) throws Exception {

		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance

		// 根据umTMethodTask生成queryRule

		return umTMethodTaskDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 更新UmTMethodTask信息
	 * 
	 * @param UmTMethodTask
	 */
	public void updateUmTMethodTask(UmTMethodTask umTMethodTask)
			throws Exception {

		umTMethodTaskDao.update(umTMethodTask);
		UmTTask task = umTTaskService.findUmTTaskByPK(new UmTTaskId(
				umTMethodTask.getTaskId())); // 获取功能对象
		if ("menu".equals(task.getTaskType())) {
			// 如果是菜单类型,则需要同步至菜单处理进行处理
			System.err.println("更新数据同步至菜单项");
			umTMENUService.updateUmTMENUasUmTTask(task,
					umTMethodTask.getUpdaterCode(),
					umTMethodTask.getMethodCode());
		}
	}

	/**
	 * 保存UmTMethodTask信息
	 * 
	 * @param UmTMethodTask
	 */
	public void saveUmTMethodTask(UmTMethodTask umTMethodTask) throws Exception {

		String methodId = commonDao.generateID("UMMT", "UM_SEQ_METHODTASK", 26);
		UmTMethodTaskId id = new UmTMethodTaskId(methodId);
		umTMethodTask.setId(id);
		umTMethodTaskDao.save(umTMethodTask);
		UmTTask task = umTTaskService.findUmTTaskByPK(new UmTTaskId(
				umTMethodTask.getTaskId()));
		if ("menu".equals(task.getTaskType())) {
			// 如果是菜单类型,则需要同步至菜单处理进行处理
			System.err.println("添加数据同步至菜单项");
			umTMENUService.saveUmTMENUasUmTTask(task,
					umTMethodTask.getCreatorCode(),
					umTMethodTask.getMethodCode());
		}
	}

	/**
	 * 当portal页面添加portal链接时在task表methodtask表同步添加
	 * 
	 * @param url
	 * @throws Exception
	 */
	public void saveUmTMethodTask4portal(WfTPortletclassfy wfTPortletclassfy,
			String userCode) throws Exception {

		UmTTask umtTask = umTTaskService
				.findUmTTaskByTaskCode(wfTPortletclassfy.getId().getPortletid());
		if (umtTask == null) {

			umtTask = new UmTTask();
			umtTask.setCreatorCode(userCode);
			umtTask.setInsertTimeForHis(wfTPortletclassfy.getInserttimeforhis());
			umtTask.setOperateTimeForHis(wfTPortletclassfy
					.getOperatetimeforhis());
			umtTask.setSvrCode("khyx");
			umtTask.setTaskCode(wfTPortletclassfy.getId().getPortletid());
			umtTask.setTaskName(wfTPortletclassfy.getPortletname());
			umtTask.setTaskType("portal");
			umtTask.setUpperTaskId(umTTaskService
					.findUmTTaskByTaskCode("um_portal").getId().getTaskId());
			umtTask.setUpperTaskCode("um_portal");
			umtTask.setUpperTaskName("门户集");
			umtTask.setValidStatus(wfTPortletclassfy.getValidstatus()
					.toString());
			umtTask.setMethodCode(wfTPortletclassfy.getActionurl());

			umTTaskService.saveUmTTaskAndMethod(umtTask);

		} else {
			umtTask.setCreatorCode(userCode);
			umtTask.setInsertTimeForHis(wfTPortletclassfy.getInserttimeforhis());
			umtTask.setOperateTimeForHis(wfTPortletclassfy
					.getOperatetimeforhis());
			umtTask.setSvrCode("khyx");
			umtTask.setTaskCode(wfTPortletclassfy.getId().getPortletid());
			umtTask.setTaskName(wfTPortletclassfy.getPortletname());
			umtTask.setTaskType("portal");
			umtTask.setUpperTaskId(umTTaskService
					.findUmTTaskByTaskCode("um_portal").getId().getTaskId());
			umtTask.setUpperTaskCode("um_portal");
			umtTask.setUpperTaskName("门户集");
			umtTask.setValidStatus(wfTPortletclassfy.getValidstatus().toString());
			umtTask.setMethodCode(wfTPortletclassfy.getActionurl());
			this.updateUmTMethodTaskasUmTTask(umtTask);

		}

	}

	/**
	 * 根据主键对象，删除UmTMethodTask信息
	 * 
	 * @param UmTMethodTaskId
	 */
	public void deleteByPK(UmTMethodTaskId id) throws Exception {

		umTMethodTaskDao.deleteByPK(UmTMethodTask.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<String> getMethodCodeByRoleCode(String roleId) throws Exception {
		String executeSQL = "select a.methodcode from um_t_methodtask a where exists ("
				+ "select 1 from um_t_role b where exists (select 1 from um_t_roletask c where  b.roleid = ? and "
				+ "b.roleid = c.roleid and a.taskid = c.taskid "
				+ "and b.validstatus = '1' and a.validstatus = '1' and c.validstatus = '1'))";
		Page page = commonDao.findObjectPageBySql(executeSQL, String.class, 1,
				Integer.MAX_VALUE, new Object[] { roleId });
		return page.getResult();
	}

/*	@SuppressWarnings("unchecked")
	public List<String> getMethodCodeByUserCode(String userCode)
			throws Exception {
//		String executeSQL = "select a.methodcode from um_t_methodtask a where exists (select 1 from um_t_usertask b "
//				+ "where a.taskid = b.taskid and b.usercode = ? and a.validstatus = '1' and b.validstatus = '1' and a.methodcode is not null)";
		String executeSQL = "select a.methodcode from um_t_methodtask a,um_t_usertask b " +
											"where a.taskid = b.taskid and b.usercode = ? and a.validstatus = '1' " +
											"and b.validstatus = '1' and a.methodcode is not null";
		List<String> list = commonDao.findBySql(executeSQL,
				new Object[] { userCode });
		return list;
	}*/
	
	@SuppressWarnings("unchecked")
	public HashMap<String,UmTMethodTask> getMethodTaskMapByUserCode(String userCode)
			throws Exception {
		String executeSQL = "select a.methodcode,c.isopen,c.openlevel,c.usertype from um_t_methodtask a, um_t_usertask b,um_t_task c " +
			"where a.taskid = b.taskid  and b.taskid = c.taskid and b.usercode = ? and a.validstatus = '1' " +
			"and b.validstatus = '1' and c.validstatus = '1' and a.methodcode is not null";
		
		List<UmTMethodTask> taskList = (List<UmTMethodTask>)commonDao.findListBySql(executeSQL, UmTMethodTask.class, new Object[]{userCode});
		
		HashMap<String,UmTMethodTask> taskMap = null;
		String url = null;
		if(taskList!=null&&taskList.size()>0){
			taskMap = new HashMap<String,UmTMethodTask>();
			for(UmTMethodTask methodTask:taskList){
				url = methodTask.getMethodCode();
				if(url.indexOf("?")>-1){
					taskMap.put(url.substring(0, url.indexOf("?")), methodTask);
				}else{
					taskMap.put(url, methodTask);
				}
			}
		}
		return taskMap;
	}

	/**
	 * 查询所有有效的功能方法集合
	 */
	@SuppressWarnings("unchecked")
	public List<UmTMethodTask> getValidMethodTaskList() {
		String executeSQL = "select a.roleid,c.methodcode,b.taskid,d.userType,d.isOpen,d.openLevel from um_t_role a,um_t_methodtask c,um_t_task d,um_t_roletask b where "
				+ "a.roleid = b.roleid and b.taskid = c.taskid and c.taskid = d.taskid and a.validstatus = '1' and b.validstatus = '1' and c.validstatus = '1' and d.validstatus = '1' and c.methodcode  is not null";
//		
//		String executeSQL = "select taskid,methodcode from um_t_methodtask a where a.validstatus = '1' and a.methodcode is not null and exists ( "+
//				"select 1 from um_t_roletask b where a.taskid = b.taskid and  b.validstatus = '1' and "+
//				"exists (select 1 from um_t_role c where b.roleid = c.roleid and c.validstatus = '1'))";
		try {
//			Page page = commonDao.findObjectPageBySql(executeSQL,
//					UmTMethodTask.class, 1, Integer.MAX_VALUE, new Object[] {});
			return (List<UmTMethodTask>)commonDao.findListBySql(executeSQL, UmTMethodTask.class, new Object[]{});
//			return page.getResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("", e);
		}
		return null;
	}
	
	//add by liuyatao 获取所有可访问的资源列表
	public List<UmTMethodTask> getOpenedMethodTaskList() {
		String executeSQL = "select a.methodcode,b.taskid,b.userType,b.isOpen,b.openLevel from um_t_methodtask a,um_t_task b where "
				+ "a.taskid = b.taskid and a.validstatus = '1' and b.validstatus = '1' and a.methodcode is not null and b.isOpen='1' ";
//		
//		String executeSQL = "select taskid,methodcode from um_t_methodtask a where a.validstatus = '1' and a.methodcode is not null and exists ( "+
//				"select 1 from um_t_roletask b where a.taskid = b.taskid and  b.validstatus = '1' and "+
//				"exists (select 1 from um_t_role c where b.roleid = c.roleid and c.validstatus = '1'))";
		try {
//			Page page = commonDao.findObjectPageBySql(executeSQL,
//					UmTMethodTask.class, 1, Integer.MAX_VALUE, new Object[] {});
			return (List<UmTMethodTask>)commonDao.findListBySql(executeSQL, UmTMethodTask.class, new Object[]{});
//			return page.getResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("", e);
		}
		return null;
	}

	// public void initPowerCache() {
	//
	// }

	public List<UmTMethodTask> findByUmTMethodTaskByTaskId(
			UmTMethodTask umTMethodTask) throws Exception {
		// TODO Auto-generated method stub
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("taskId", umTMethodTask.getTaskId());

		return umTMethodTaskDao.find(queryRule);
	}

	/**
	 * 根据功能增加方法
	 */
	public void saveUmTMethodTaskasUmTTask(UmTTask umTTask) {
		UmTMethodTask umTMethodTask = findByTaskId(umTTask.getId().getTaskId());
		if (umTMethodTask == null) {
			umTMethodTask = UmTTask2UmTMethodTask(umTTask, umTMethodTask);
			umTMethodTaskDao.save(umTMethodTask);
		}
	}

	/**
	 * 更新功能的时候同时更新方法
	 * 
	 * @param umTTask
	 */
	public void updateUmTMethodTaskasUmTTask(UmTTask umTTask) {
		UmTMethodTask umTMethodTask = findByTaskId(umTTask.getId().getTaskId());
		String methodCode = umTMethodTask.getMethodCode();
		if (methodCode != null && methodCode.equals(umTTask.getMethodCode())) {
			// 已经修改MethodCode
			cacheService.updateModifyUrl(umTMethodTask.getMethodCode(), -1); // 删除对原methodCode缓存对象
		}
		if ("0".equals(umTTask.getValidStatus())) {
			cacheService.updateModifyUrl(umTMethodTask.getMethodCode(), -1); // 如果将当前功能的有效状态置为无效
																				// 则清除缓存中对于此访问URL的缓存数据
		}
		if (umTMethodTask != null) {
			// 如果能查询到要修改的方法则做修改
			umTMethodTask = UmTTask2UmTMethodTask(umTTask, umTMethodTask);
			umTMethodTaskDao.update(umTMethodTask);
		}
	}

	/**
	 * 根据功能ID查询功能的URL
	 */
	public UmTMethodTask findByTaskId(String taskId) {
		String hql = "select utt from UmTMethodTask utt where utt.taskId = ?";
		List<UmTMethodTask> umTMethodTaskList = umTMethodTaskDao.findByHql(hql,
				taskId);
		if (umTMethodTaskList != null && umTMethodTaskList.size() > 0) {
			if (umTMethodTaskList.size() == 1) {
				return umTMethodTaskList.get(0);
			} else {
				throw new RuntimeException("查询出的功能URL多于一个！");
			}
		} else {
			return null;
		}
	}

	/**
	 * 由功能生成功能方法
	 * 
	 * @param umTTask
	 * @param umTMethodTask
	 * @return shenyichan
	 */
	private UmTMethodTask UmTTask2UmTMethodTask(UmTTask umTTask,
			UmTMethodTask umTMethodTask) {
		if (umTMethodTask == null) {
			umTMethodTask = new UmTMethodTask();
			// 如果方法为null，则当前动作为增加，要为其生成相应的主键和添加人等
			umTMethodTask.setId(new UmTMethodTaskId(commonDao.generateID(
					"UMMT", "UM_SEQ_METHODTASK", 26)));
			umTMethodTask.setTaskId(umTTask.getId().getTaskId());
			umTMethodTask.setCreatorCode(umTTask.getCreatorCode());
		}
		Date now = new Date();
		umTMethodTask.setUpdaterCode(umTTask.getUpdaterCode());
		umTMethodTask.setOperateTimeForHis(now);
		umTMethodTask.setValidStatus(umTTask.getValidStatus());
		umTMethodTask.setMethodCode(umTTask.getMethodCode());

		return umTMethodTask;
	}

	@SuppressWarnings("unchecked")
	public List<String> getRoleCodeListByMethodCode(String methodCode) {
		String executeSQL = "select rolecode from um_t_role where roleid in ("
				+ "select roleid from um_t_roletask where taskid in (select taskid from um_t_methodtask where methodcode = ? and validstatus = '1') "
				+ "and validstatus = '1') and validstatus = '1'";
		List<String> roleCodeList = commonDao.findBySql(executeSQL, methodCode);
		return roleCodeList;
	}

}
