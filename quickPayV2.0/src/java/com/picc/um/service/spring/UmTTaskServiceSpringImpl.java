/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.dao.CommonDaoHibernate;
import com.picc.common.utils.AppConfig;
import com.picc.common.utils.SqlUtils;
import com.picc.common.utils.ToolsUtils;
import com.picc.common.vo.TreeNode;
import com.picc.portal.schema.model.WfTPortletclassfy;
import com.picc.portal.service.facade.IWfTPortletclassfyService;
import com.picc.tm.common.schema.model.TmTAppServiceConfig;
import com.picc.um.dao.UmTMethodTaskDaoHibernate;
import com.picc.um.dao.UmTTaskDaoHibernate;
import com.picc.um.schema.model.UmTMENU;
import com.picc.um.schema.model.UmTMethodTask;
import com.picc.um.schema.model.UmTMethodTaskId;
import com.picc.um.schema.model.UmTRole;
import com.picc.um.schema.model.UmTTask;
import com.picc.um.schema.model.UmTTaskId;
import com.picc.um.service.facade.IUmTMENUService;
import com.picc.um.service.facade.IUmTMethodTaskService;
import com.picc.um.service.facade.IUmTRoleService;
import com.picc.um.service.facade.IUmTTaskService;

/**
 * 功能接口实现类
 * @author 姜卫洋
 */
@Service("umTTaskService")
public class UmTTaskServiceSpringImpl implements IUmTTaskService {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UmTTaskDaoHibernate umTTaskDao;

	@Autowired
	private CommonDaoHibernate commonDao;

	@Autowired
	private UmTMethodTaskDaoHibernate umTMethodTaskDao;

	@Autowired
	private IUmTRoleService umTRoleService;

	@Autowired
	private IWfTPortletclassfyService wfTPortletclassfyService;

	@Autowired
	private IUmTMethodTaskService umTMethodTaskService;

	@Autowired
	private IUmTMENUService umTMENUService;

	private static final String query_self = "0";
	private static final String query_sub = "1";

	/**
	 * 根据主键对象UmTTaskId获取UmTTask信息
	 * 
	 * @param UmTTaskId
	 * @return UmTTask
	 */
	public UmTTask findUmTTaskByPK(UmTTaskId id) throws Exception {

		return umTTaskDao.get(UmTTask.class, id);
	}

	/**
	 * 根据umTTask和页码信息，获取Page对象
	 * 
	 * @param umTTask
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTTask的Page对象
	 */
	public Page findByUmTTask(UmTTask umTTask, int pageNo, int pageSize)
			throws Exception {
		String upperTaskId = umTTask.getUpperTaskId();
		String taskCode = umTTask.getTaskCode();
		String taskName = umTTask.getTaskName();
		String svrCode = umTTask.getSvrCode();
		String taskType = umTTask.getTaskType();
		String userType = umTTask.getUserType();
		String isOpen = umTTask.getIsOpen();
		String openLevel = umTTask.getOpenLevel();
		String validStatus = umTTask.getValidStatus();
		String upperTaskCode = umTTask.getUpperTaskCode();
		String upperTaskName = umTTask.getUpperTaskName();
		String methodCode = umTTask.getMethodCode();
		Date expireDate = umTTask.getExpireDate();
		StringBuffer sql = new StringBuffer(
				"select a.*,b.methodcode from um_t_task a,um_t_methodtask b where a.taskid=b.taskid and 1=1 ");

		if (upperTaskId != null && !("".equals(upperTaskId.trim()))) {
			sql.append(SqlUtils.convertString("a.upperTaskId",
					upperTaskId.trim()));
		}
		if (taskCode != null && !("".equals(taskCode.trim()))) {
			sql.append(SqlUtils.convertString("a.taskCode", taskCode.trim()));
		}
		if (taskName != null && !("".equals(taskName.trim()))) {
			sql.append(SqlUtils.convertString("a.taskName", taskName.trim()));
		}
		if (svrCode != null && !("".equals(svrCode.trim()))) {
			sql.append(SqlUtils.convertString("a.svrCode", svrCode.trim()));
		}
		if (taskType != null && !("".equals(taskType.trim()))) {
			sql.append(SqlUtils.convertString("a.taskType", taskType.trim()));
		}
		if (userType != null && !("".equals(userType.trim()))) {
			sql.append(SqlUtils.convertString("a.userType", userType.trim()));
		}
		if (isOpen != null && !("".equals(isOpen.trim()))) {
			sql.append(SqlUtils.convertString("a.isOpen", isOpen.trim()));
		}
		if (openLevel != null && !("".equals(openLevel.trim()))) {
			sql.append(SqlUtils.convertString("a.openLevel", openLevel.trim()));
		}
		if (expireDate != null) {
			String expireDateStr = (new SimpleDateFormat("yyyy-MM-dd"))
					.format(umTTask.getExpireDate());
			sql.append(SqlUtils.convertDate("a.expireDate",
					expireDateStr.trim()));
		}
		if (validStatus != null && !("".equals(validStatus.trim()))) {
			sql.append(SqlUtils.convertString("a.validStatus",
					validStatus.trim()));
		}
		if (upperTaskCode != null && !("".equals(upperTaskCode.trim()))) {
			sql.append(SqlUtils.convertString("a.upperTaskCode",
					upperTaskCode.trim()));
		}
		if (upperTaskName != null && !("".equals(upperTaskName.trim()))) {
			sql.append(SqlUtils.convertString("a.upperTaskName",
					upperTaskName.trim()));
		}
		if (methodCode != null && !("".equals(methodCode.trim()))) {
			sql.append(SqlUtils.convertString("b.methodCode", methodCode.trim()));
		}

		logger.info(sql.toString());
		return commonDao.findObjectPageBySql(sql.toString(), UmTTask.class,
				pageNo, pageSize);
	}

	/**
	 * 更新UmTTask信息
	 * 
	 * @param UmTTask
	 */
	public void updateUmTTask(UmTTask umTTask) throws Exception {
		umTTaskDao.update(umTTask);
	}

	/**
	 * 保存UmTTask信息
	 * 
	 * @param UmTTask
	 */
	public void saveUmTTaskAndMethod(UmTTask umTTask) throws Exception {

		// 保存功能
		UmTTaskId umTTaskId = new UmTTaskId();
		String taskId = commonDao.generateID("UMTA", "UM_SEQ_TASK", 26);
		umTTaskId.setTaskId(taskId);
		umTTask.setId(umTTaskId);
		umTTask.setValidStatus("1");
		umTTaskDao.save(umTTask);

		// 保存方法
		UmTMethodTask umTMethodTask = new UmTMethodTask();
		UmTMethodTaskId umTMethodTaskId = new UmTMethodTaskId();
		umTMethodTaskId.setMethodId(commonDao.generateID("UMMT",
				"UM_SEQ_METHODTASK", 26));
		umTMethodTask.setId(umTMethodTaskId);
		umTMethodTask.setCreatorCode(umTTask.getCreatorCode());
		umTMethodTask.setTaskId(taskId);
		umTMethodTask.setMethodCode(umTTask.getMethodCode());
		umTMethodTask.setValidStatus("1");
		umTMethodTaskDao.save(umTMethodTask);

		// 保存菜单
		if (AppConfig.get("um.MENU").equals(umTTask.getTaskType())) {
			// 如果功能的类型是菜单则要将该功能保存到菜单表中
			umTMENUService.saveUmTMENUasUmTTask(umTTask,
					umTTask.getCreatorCode(), umTTask.getMethodCode());
		}

	}

	/**
	 * 根据主键对象，删除UmTTask信息
	 * 
	 * @param UmTTaskId
	 */
	public void deleteByPK(UmTTaskId id) throws Exception {

		umTTaskDao.deleteByPK(UmTTask.class, id);
	}

	/**
	 * 查询出所有的功能对象
	 */
	public Page findAll(int page, int rows) {
		return (Page) umTTaskDao.findByHqlNoLimit("from UmTTask", page, rows);
	}

	/**
	 * 获取配置在用户个人名下的功能权限
	 */
	public List<UmTTask> findTaskByUserCode(String userCode) {
		QueryRule rule = QueryRule.getInstance();
		rule.addEqual("userCode", userCode);
		return umTTaskDao.find(rule);
	}
	
	public List<UmTTask> findTaskByOpenStatus(String serverCode) {
		QueryRule rule = QueryRule.getInstance();
		rule.addEqual("svrCode", serverCode);
		rule.addEqual("isOpen", "1");
		return umTTaskDao.find(rule);
	}

	public String findTaskTreeJsonByQueryType(String userCode, String comId) {
		QueryRule rule = QueryRule.getInstance();
		rule.addEqual("validStatus", "1"); // 有效控制 List<String> selectTaskIdList
											// = new ArrayList<String>();
		try {
			List<UmTTask> userTask = new ArrayList<UmTTask>();
			List<UmTTask> roleTask = new ArrayList<UmTTask>();
			List<String> roleTaskIdList = new ArrayList<String>();
			List<String> userTaskIdList = new ArrayList<String>();

			// 从UmTUserTask表中获取功能列表
			userTask = findByUserCode(userCode);

			// 获取用户对应角色的功能列表
			List<UmTRole> roleList = umTRoleService.findByUserCode(userCode); // 取用户角色
			for (UmTRole role : roleList) { // 取用户角色下的功能
				String roleId = role.getId().getRoleId();
				roleTask.addAll(findByRoleId(roleId, comId));
			}

			// 去掉有可能重复的功能
			Set<UmTTask> comTask = new HashSet<UmTTask>(); // 当前用户所有的功能列表并集
			// comTask.addAll(userTask);
			comTask.addAll(roleTask); // 对各角色所配置的功能信息进行去重处理

			// 从用户的功能列表中提取出功能ID
			Iterator<UmTTask> it = comTask.iterator();
			while (it.hasNext()) {
				roleTaskIdList.add(it.next().getId().getTaskId());
			}

			if (userTask != null && userTask.size() > 0) {
				for (UmTTask task : userTask) {
					userTaskIdList.add(task.getId().getTaskId());
				}
			}

			// 获取所有有效的功能列表
			List<UmTTask> taskList = umTTaskDao.find(rule);

			List<TreeNode> menuNodeList = creatTreeList(taskList,
					roleTaskIdList, userTaskIdList);
			JSONArray jsonArray = JSONArray.fromObject(menuNodeList);
			return jsonArray.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("", e);
		}
		return null;
	}

	private List<TreeNode> creatTreeList(List<UmTTask> taskList,
			List<String> roleTaskIdList, List<String> userTaskIdList) {
		List<TreeNode> nodeList = new ArrayList<TreeNode>();
		TreeNode treeNode = null;
		for (UmTTask task : taskList) {
			// 构建节点数据
			/** 首先找出所有的根节点 **/
			if (task.getUpperTaskId() == null
					|| "".equals(task.getUpperTaskId())) {
				treeNode = new TreeNode();
				treeNode.setId(task.getId().getTaskId());
				treeNode.setText(task.getTaskName());
				if (roleTaskIdList.contains(treeNode.getId())) {
					treeNode.setChecked(true);
					treeNode.setEditable(false);
				} else if (userTaskIdList.contains(treeNode.getId())) {
					treeNode.setChecked(true);
					treeNode.setEditable(true);
				} else {
					treeNode.setChecked(false);
				}
				nodeList.add(treeNode); // List中添加Node节点
			}
		}
		for (TreeNode upperMenuNode : nodeList) {
			expandChildNode(upperMenuNode, taskList, roleTaskIdList,
					userTaskIdList);
		}
		return nodeList;
	}

	private void expandChildNode(TreeNode menuNodeParam,
			List<UmTTask> menuList, List<String> roleTaskList,
			List<String> userTaskList) {
		if (menuNodeParam == null || menuList == null) {
			return;
		} else {
			TreeNode menuNode = null;
			for (UmTTask task : menuList) {
				if (task.getUpperTaskId() != null
						&& task.getUpperTaskId().equals(menuNodeParam.getId())) {
					menuNode = new TreeNode();
					menuNode.setId(task.getId().getTaskId());
					menuNode.setText(task.getTaskName());
					if (roleTaskList.contains(menuNode.getId())) {
						menuNode.setChecked(true);
						menuNode.setEditable(false);
					} else if (userTaskList.contains(menuNode.getId())) {
						menuNode.setChecked(true);
						menuNode.setEditable(true);
					} else {
						menuNode.setChecked(false);
					}
					expandChildNode(menuNode, menuList, roleTaskList,
							userTaskList);
					menuNodeParam.addChildNode(menuNode);
				}
			}
		}
	}

	/**
	 * add by shenyichan
	 */
	public String findTaskTreeJsonByQueryType(String taskId, String queryType,
			List<String> taskIds) {
		List<UmTTask> taskList = new ArrayList<UmTTask>();
		List<TreeNode> taskTreeNodeList = new ArrayList<TreeNode>();

		if (query_self.equals(queryType)) {
			// 查询出所有根级的功能对象
			String hql = "from UmTTask where (upperTaskId is null or upperTaskId='') and validStatus='1'";
			taskList = umTTaskDao.findByHql(hql);
		} else if (query_sub.equals(queryType)) {
			String hql = "from UmTTask where upperTaskId='" + taskId
					+ "' and validStatus='1'";
			taskList = umTTaskDao.findByHql(hql);
		}

		boolean checked = false;
		String state = "closed";
		if (taskIds != null && taskIds.size() > 0) {
			for (UmTTask t : taskList) {
				String id = t.getId().getTaskId();
				String text;
				if (t.getTaskType().equals("menu")) {
					text = "[" + t.getTaskName() + "]";
				} else {
					text = t.getTaskName();
				}
				if (taskIds.contains(id)) {
					checked = true;
				}
				Map map = new HashMap<String, Object>();
				map.put("initChecked", checked);// 每个节点都一个初始状态（即选中true或未选中false）
				map.put("taskCode", t.getTaskCode());
				taskTreeNodeList
						.add(new TreeNode(id, text, state, checked, map));

				checked = false;
			}
		} else {
			for (UmTTask t : taskList) {
				String id = t.getId().getTaskId();
				String text;
				if (t.getTaskType().equals("menu")) {
					text = "[" + t.getTaskName() + "]";
				} else {
					text = t.getTaskName();
				}
				Map map = new HashMap<String, Object>();
				map.put("initChecked", checked);
				map.put("taskCode", t.getTaskCode());
				taskTreeNodeList
						.add(new TreeNode(id, text, state, checked, map));
			}
		}

		JSONArray jsonArray = JSONArray.fromObject(taskTreeNodeList);

		return jsonArray.toString();
	}
	/*重载2014年8月1日*/
	public String findTaskTreeJsonByQueryType(String taskId, String queryType,
			List<String> taskIds,String serverCode) {
		List<UmTTask> taskList = new ArrayList<UmTTask>();
		List<UmTTask> subTaskList = new ArrayList<UmTTask>();
		List<TreeNode> taskTreeNodeList = new ArrayList<TreeNode>();

		if (query_self.equals(queryType)) {
			// 查询出所有根级的功能对象
			String hql = "from UmTTask where (upperTaskId is null or upperTaskId='') and validStatus='1' and (svrcode='PUB' or svrcode='"+serverCode+"')";
			taskList = umTTaskDao.findByHql(hql);
		} else if (query_sub.equals(queryType)) {
			String hql = "from UmTTask where upperTaskId='" + taskId
					+ "' and validStatus='1'";
			taskList = umTTaskDao.findByHql(hql);
		}
		String subTaskType = "";
		if(ToolsUtils.notEmpty(taskList)){
			//判断下级任务是否为button类型
			String hql = "from UmTTask where upperTaskId='" + taskList.get(0).getId().getTaskId()
			+ "' and validStatus='1'";
			subTaskList = umTTaskDao.findByHql(hql);
			if(ToolsUtils.notEmpty(subTaskList)){
				subTaskType = subTaskList.get(0).getTaskType();
			}
		}

		boolean checked = false;
		String state = "closed";
		if (taskIds != null && taskIds.size() > 0) {
			for (UmTTask t : taskList) {
				String id = t.getId().getTaskId();
				String text;
				if (t.getTaskType().equals("menu")) {
					text = "[" + t.getTaskName() + "]";
				} else {
					text = "<font color='#gray'>"+t.getTaskName()+"</font>";
				}
				if (taskIds.contains(id)) {
					checked = true;
				}
				Map map = new HashMap<String, Object>();
				map.put("initChecked", checked);// 每个节点都一个初始状态（即选中true或未选中false）
				map.put("taskCode", t.getTaskCode());
				//add by liuyatao 2014年8月5日 加入节点类型判断（菜单or按钮）
				map.put("taskType", t.getTaskType());
				map.put("userType", t.getUserType());
				map.put("subTaskType", subTaskType);
				map.put("serverCode", t.getSvrCode());
				taskTreeNodeList
						.add(new TreeNode(id, text, state, checked, map));

				checked = false;
			}
		} else {
			for (UmTTask t : taskList) {
				String id = t.getId().getTaskId();
				String text;
				if (t.getTaskType().equals("menu")) {
					text = "[" + t.getTaskName() + "]";
				} else {
					text = "<font color='#gray'>"+t.getTaskName()+"</font>";
				}
				Map map = new HashMap<String, Object>();
				map.put("initChecked", checked);
				map.put("taskCode", t.getTaskCode());
				map.put("taskType", t.getTaskType());
				map.put("userType", t.getUserType());
				map.put("subTaskType", subTaskType);
				map.put("serverCode", t.getSvrCode());
				taskTreeNodeList
						.add(new TreeNode(id, text, state, checked, map));
			}
		}

		JSONArray jsonArray = JSONArray.fromObject(taskTreeNodeList);

		return jsonArray.toString();
	}

	/**
	 * by yanglian
	 */
	public String findTaskTreeJsonByQueryType(String taskId, String queryType,
			List<String> taskIds, List<String> taskIds2) {
		List<UmTTask> taskList = new ArrayList<UmTTask>();
		List<TreeNode> taskTreeNodeList = new ArrayList<TreeNode>();

		if (query_self.equals(queryType)) {
			// 查询出所有根级的功能对象
			String hql = "from UmTTask where (upperTaskId is null or upperTaskId='') and validStatus='1'";
			taskList = umTTaskDao.findByHql(hql);
		} else if (query_sub.equals(queryType)) {
			String hql = "from UmTTask where upperTaskId='" + taskId
					+ "' and validStatus='1'";
			taskList = umTTaskDao.findByHql(hql);
		}

		boolean checked = false;
		boolean flag = false;
		String state = "closed";
		if ((taskIds != null && taskIds.size() > 0) ||( taskIds2 != null
				&& taskIds2.size() > 0)) {
			for (UmTTask t : taskList) {
				String id = t.getId().getTaskId();
				String text;
				if (t.getTaskType().equals("menu")) {
					text = "[" + t.getTaskName() + "]";
				} else {
					text = t.getTaskName();
				}
				if (taskIds2.contains(id)&&!taskIds.contains(id)) {
					checked = true;
				}
				Map map = new HashMap<String, Object>();
				if (taskIds.contains(id)) {
					flag = true;
				}
				map.put("initChecked", flag);// 每个节点都一个初始状态（即选中true或未选中false）
				map.put("taskCode", t.getTaskCode());
				taskTreeNodeList
						.add(new TreeNode(id, text, state, checked, map));
				flag = false;
				checked = false;
			}
		} else {
			for (UmTTask t : taskList) {
				String id = t.getId().getTaskId();
				String text;
				if (t.getTaskType().equals("menu")) {
					text = "[" + t.getTaskName() + "]";
				} else {
					text = t.getTaskName();
				}
				Map map = new HashMap<String, Object>();
				map.put("initChecked", checked);
				map.put("taskCode", t.getTaskCode());
				taskTreeNodeList
						.add(new TreeNode(id, text, state, checked, map));
			}
		}

		JSONArray jsonArray = JSONArray.fromObject(taskTreeNodeList);

		return jsonArray.toString();
	}
	/**overload by liuyatao **/
	public String findTaskTreeJsonByQueryType(String taskId, String queryType,
			List<String> taskIds, List<String> taskIds2,String serverCode) {
		List<UmTTask> taskList = new ArrayList<UmTTask>();
		List<UmTTask> subTaskList = new ArrayList<UmTTask>();
		List<TreeNode> taskTreeNodeList = new ArrayList<TreeNode>();

		if (query_self.equals(queryType)) {
			// 查询出所有根级的功能对象
			String hql = "from UmTTask where (upperTaskId is null or upperTaskId='') and validStatus='1' and (svrcode='PUB' or svrcode='"+serverCode+"')";
			taskList = umTTaskDao.findByHql(hql);
		} else if (query_sub.equals(queryType)) {
			String hql = "from UmTTask where upperTaskId='" + taskId
					+ "' and validStatus='1'";
			taskList = umTTaskDao.findByHql(hql);
		}
		String subTaskType = "";
		if(ToolsUtils.notEmpty(taskList)){
			//判断下级任务是否为button类型
			String hql = "from UmTTask where upperTaskId='" + taskList.get(0).getId().getTaskId()
			+ "' and validStatus='1'";
			subTaskList = umTTaskDao.findByHql(hql);
			if(ToolsUtils.notEmpty(subTaskList)){
				subTaskType = subTaskList.get(0).getTaskType();
			}
		}
		
		boolean checked = false;
		boolean flag = false;
		String state = "closed";
		if ((taskIds != null && taskIds.size() > 0) ||( taskIds2 != null
				&& taskIds2.size() > 0)) {
			for (UmTTask t : taskList) {
				String id = t.getId().getTaskId();
				String text;
				if (t.getTaskType().equals("menu")) {
					text = "[" + t.getTaskName() + "]";
				} else {
					text = t.getTaskName();
				}
				if (taskIds2.contains(id)&&!taskIds.contains(id)) {
					checked = true;
				}
				Map map = new HashMap<String, Object>();
				if (taskIds.contains(id)) {
					flag = true;
				}
				map.put("initChecked", flag);// 每个节点都一个初始状态（即选中true或未选中false）
				map.put("taskCode", t.getTaskCode());
				//add by liuyatao 2014年8月5日 加入节点类型判断（菜单or按钮）
				map.put("taskType", t.getTaskType());
				map.put("userType", t.getUserType());
				map.put("subTaskType", subTaskType);
				taskTreeNodeList
						.add(new TreeNode(id, text, state, checked, map));
				flag = false;
				checked = false;
			}
		} else {
			for (UmTTask t : taskList) {
				String id = t.getId().getTaskId();
				String text;
				if (t.getTaskType().equals("menu")) {
					text = "[" + t.getTaskName() + "]";
				} else {
					text = t.getTaskName();
				}
				Map map = new HashMap<String, Object>();
				map.put("initChecked", checked);
				map.put("taskCode", t.getTaskCode());
				//add by liuyatao 2014年8月5日 加入节点类型判断（菜单or按钮）
				map.put("taskType", t.getTaskType());
				map.put("userType", t.getUserType());
				map.put("subTaskType", subTaskType);
				taskTreeNodeList
						.add(new TreeNode(id, text, state, checked, map));
			}
		}

		JSONArray jsonArray = JSONArray.fromObject(taskTreeNodeList);

		return jsonArray.toString();
	}

	@SuppressWarnings({ "rawtypes" })
	public List<String> getTaskListByUserCode(String userCode, String comId,String serverCode,
			String taskType) throws Exception {
		// 1.根据用户代码返回该用户所配置的角色信息与功列表之间的对应关系
		// 2.根据用户代码返回直接配置给该用户的功能列表
		String executeSQL = "select * from um_t_task where taskid in (select distinct taskid from um_t_roletask where "
				+ "roleid in (select roleid from um_t_userrole where usercode = ? and validstatus = '1') and "
				+ "taskid not in (select taskid from um_t_roledime where roleid in (select roleid from um_t_userrole where usercode = ? and validstatus = '1') "
				+ "and dimensioncode = 'comcode' and dimensionvalue = ? and operatetype = 'sub' and validstatus = '1') and validstatus = '1' "
				+ "union select distinct taskid from um_t_roledime where roleid in (select roleid from um_t_userrole where usercode = ? and validstatus = '1') "
				+ "and dimensioncode = 'comcode' and dimensionvalue = ? and operatetype = 'add' and validstatus = '1' "
				+ "union select distinct taskid from um_t_usertask where usercode = ? and validstatus = '1') and tasktype = ? and validstatus = '1' and validstatus = '1' and (svrcode=? "
				+" or svrcode='PUB')";

		Page page = commonDao.findObjectPageBySql(executeSQL,
				UmTMethodTask.class, 1, Integer.MAX_VALUE, new Object[] {
						userCode, userCode, comId, userCode, comId, userCode,
						taskType, serverCode});
		List list = page.getResult();
		List<String> resultList = new ArrayList<String>();
		Iterator it = list.iterator();
		UmTMethodTask task = null;
		while (it.hasNext()) {
			task = (UmTMethodTask) it.next();
			resultList.add(task.getTaskId());
		}
		return resultList;
	}
	
	public List<UmTTask> getTaskObjectListByUserCode(String userCode, String comId,String serverCode,
			String serverType,String taskType) throws Exception {
		// 1.根据用户代码返回该用户所配置的角色信息与功列表之间的对应关系
		// 2.根据用户代码返回直接配置给该用户的功能列表
		String executeSQL = null;
		if("01".equals(serverType)){
			executeSQL = "select * from um_t_task where taskid in (select distinct taskid from um_t_roletask where "
				+ "roleid in (select roleid from um_t_userrole where usercode = ? and validstatus = '1') and "
				+ "taskid not in (select taskid from um_t_roledime where roleid in (select roleid from um_t_userrole where usercode = ? and validstatus = '1') "
				+ "and dimensioncode = 'comcode' and dimensionvalue = ? and operatetype = 'sub' and validstatus = '1') and validstatus = '1' "
				+ "union select distinct taskid from um_t_roledime where roleid in (select roleid from um_t_userrole where usercode = ? and validstatus = '1') "
				+ "and dimensioncode = 'comcode' and dimensionvalue = ? and operatetype = 'add' and validstatus = '1' "
				+ "union select distinct taskid from um_t_usertask where usercode = ? and validstatus = '1') and tasktype = ? and validstatus = '1' and (svrcode=? "
				+" or svrcode='PUB')";
		}else{
			//说明是外部系统，此时加载菜单时需要考虑公开开放的菜单链接
			executeSQL = "select * from um_t_task where (isOpen = '1' or taskid in (select distinct taskid from um_t_roletask where "
				+ "roleid in (select roleid from um_t_userrole where usercode = ? and validstatus = '1') and "
				+ "taskid not in (select taskid from um_t_roledime where roleid in (select roleid from um_t_userrole where usercode = ? and validstatus = '1') "
				+ "and dimensioncode = 'comcode' and dimensionvalue = ? and operatetype = 'sub' and validstatus = '1') and validstatus = '1' "
				+ "union select distinct taskid from um_t_roledime where roleid in (select roleid from um_t_userrole where usercode = ? and validstatus = '1') "
				+ "and dimensioncode = 'comcode' and dimensionvalue = ? and operatetype = 'add' and validstatus = '1' "
				+ "union select distinct taskid from um_t_usertask where usercode = ? and validstatus = '1') ) and tasktype = ? and validstatus = '1' and (svrcode=? "
				+" or svrcode='PUB')";
		}
		Page page = commonDao.findObjectPageBySql(executeSQL,
				UmTTask.class, 1, Integer.MAX_VALUE, new Object[] {
						userCode, userCode, comId, userCode, comId, userCode,
						taskType, serverCode});
		List<UmTTask> list = (List<UmTTask>) page.getResult();
		return list;
	}
	/**
	 * 返回用户可操作的系统列表
	 * add by liuyatao 2014年3月18日 18:00
	 * @param userCode
	 * @param comId
	 * @param taskType
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes" })
	public List<String> getSystemListByUserCode(String userCode, String comId,
			String taskType) throws Exception {
		// 1.根据用户代码返回该用户所配置的角色信息与功列表之间的对应关系
		// 2.根据用户代码返回直接配置给该用户的功能列表
		String executeSQL = "select distinct systemCode from um_T_menu where taskID in( select distinct taskid from um_t_roletask where "
				+ "roleid in (select roleid from um_t_userrole where usercode = ? and validstatus = '1') and "
				+ "taskid not in (select taskid from um_t_roledime where roleid in (select roleid from um_t_userrole where usercode = ? and validstatus = '1') "
				+ "and dimensioncode = 'comcode' and dimensionvalue = ? and operatetype = 'sub' and validstatus = '1') and validstatus = '1' "
				+ "union select taskid from um_t_roledime where roleid in (select roleid from um_t_userrole where usercode = ? and validstatus = '1') "
				+ "and dimensioncode = 'comcode' and dimensionvalue = ? and operatetype = 'add' and validstatus = '1' "
				+ "union select taskid from um_t_usertask where usercode = ? and validstatus = '1') and tasktype = ? and validstatus = '1'";

		Page page = commonDao.findObjectPageBySql(executeSQL,
				UmTMENU.class, 1, Integer.MAX_VALUE, new Object[] {
						userCode, userCode, comId, userCode, comId, userCode,
						taskType });
		List list = page.getResult();
		List<String> resultList = new ArrayList<String>();
		Iterator it = list.iterator();
		UmTMENU menu = null;
		while (it.hasNext()) {
			menu = (UmTMENU) it.next();
			System.out.println("systemcode:"+menu.getSystemCode());
			if(!resultList.contains(menu))
				resultList.add(menu.getSystemCode().trim());
		}
		return resultList;
		
	}
	/**
	 * 根据usercode判断用户角色，功能对应的方法url，于portal里的方法url全量比对， 返回用户权限范围内的portalid
	 */
	public List<String> getPorletidByUserCode(String userCode, String comId,String serverCode)
			throws Exception {
		List<String> taskIdList = this.getTaskListByUserCode(userCode, comId,serverCode,
				"portal");
		List<String> protelIdList = new ArrayList<String>();
		if (taskIdList != null) {
			UmTMethodTask umtMethodTask = new UmTMethodTask();
			List<UmTMethodTask> umtMethodTaskList = new ArrayList<UmTMethodTask>();
			for (int i = 0; i < taskIdList.size(); i++) {
				umtMethodTask.setTaskId(taskIdList.get(i));
				umtMethodTaskList.addAll(umTMethodTaskService
						.findByUmTMethodTaskByTaskId(umtMethodTask));
			}

			List<WfTPortletclassfy> wftPortletList = wfTPortletclassfyService
					.findPortletClassfyAll();

			for (int i = 0; i < umtMethodTaskList.size(); i++)
				for (int j = 0; j < wftPortletList.size(); j++) {
					if (umtMethodTaskList.get(i).getMethodCode() != null
							&& wftPortletList.get(j).getActionurl() != null) {
						if (umtMethodTaskList.get(i).getMethodCode()
								.equals(wftPortletList.get(j).getActionurl())) {
							protelIdList.add(wftPortletList.get(j).getId()
									.getPortletid());
						}
					}
				}
		}

		return protelIdList;
	}

	/**
	 * 查询与角色关联且有效的功能
	 * 
	 * @param roleId
	 * @return
	 * @author shenyichan
	 */
	@SuppressWarnings("unchecked")
	public List<UmTTask> findByRoleId(String roleId, String comId)
			throws Exception {

		try {
			String executeSQL = "select * from (select a.* from um_t_task a,um_t_roletask b where a.taskid = b.taskid and b.roleid = ? "
					+ "and a.validstatus = '1' and b.validstatus = '1' and a.taskid not in (select taskid from um_t_roledime where "
					+ "roleid = ? and operatetype = 'sub' and validstatus = '1' and "
					+ "dimensioncode = 'comcode' and dimensionvalue = ?) union select c.* from um_t_task c where "
					+ "c.taskid in (select taskid from um_t_roledime where roleid = ? "
					+ "and operatetype = 'add' and validstatus = '1' and dimensioncode = 'comcode' and dimensionvalue = ?)) tt";
			return (List<UmTTask>) commonDao.findObjectPageBySql(executeSQL,
					UmTTask.class, 1, Integer.MAX_VALUE,
					new Object[] { roleId, roleId, comId, roleId, comId })
					.getResult();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
		//
		// String hql =
		// "select ut from UmTTask ut, UmTRoleTask urt where ut.id.taskId=urt.taskID and urt.roleId=? and urt.validStatus = '1' and ut.validStatus = '1'";
		// return umTTaskDao.findByHql(hql, roleId);
	}

	public List<UmTTask> findByUserCode(String userCode) {
		String hql = "select uu from UmTTask uu,UmTUserTask uut where uu.id.taskId=uut.taskId and uut.userCode=? and uu.validStatus='1' and uut.validStatus= '1'";
		return umTTaskDao.findByHql(hql, userCode);

	}

	/**
	 * 修改功能、菜单、方法
	 */
	public void updateUmTTaskVSMethodVSMenu(UmTTask umTTask) throws Exception {
		List<UmTTask> subTaskList = findByUpperTaskId(umTTask.getId()
				.getTaskId());
		if ("0".equals(umTTask.getValidStatus()) && subTaskList != null
				&& subTaskList.size() > 0) {
			// 如果将当前功能置为无效且当前功能下有了节点时，则提示不可置为无效
			throw new RuntimeException("当前功能下有子功能，不能置无效!");
		}
		// 修改功能
		umTTaskDao.update(umTTask);

		// 修改方法
		umTMethodTaskService.updateUmTMethodTaskasUmTTask(umTTask);

		if (AppConfig.get("um.MENU").equals(umTTask.getTaskType())) {
			// 如果当前功能是菜单类型则修改菜单
			umTMENUService.updateUmTMENUasUmTTask(umTTask,
					umTTask.getUpdaterCode(), umTTask.getMethodCode());
		} else {
			// 如果当前功能不是菜单，则看它以前是不是菜单，如是，则将菜单置为无效,否则不做处理
			UmTMENU umTMenu = umTMENUService.findByTaskId(umTTask.getId()
					.getTaskId());
			if (umTMenu != null) {
				// 不等于NULL时，说明之前此功能是一个菜单,将此菜单致为无效
				umTMenu.setValidStauts("0");
				umTMENUService.updateUmTMENU(umTMenu, umTTask.getUpdaterCode());
			}
		}
	}

	/**
	 * 增加功能、菜单、方法
	 */
	public void saveUmTTaskVSMethodVSMenu(UmTTask umTTask) throws Exception {
		// 增加功能
		umTTask.setId(new UmTTaskId(commonDao.generateID("UMTA", "UM_SEQ_TASK",
				26)));
		umTTaskDao.save(umTTask);

		// 增加方法
		umTMethodTaskService.saveUmTMethodTaskasUmTTask(umTTask);

		if (AppConfig.get("um.MENU").equals(umTTask.getTaskType())) {
			// 如果当前功能是菜单类型，则增加菜单
			umTMENUService.saveUmTMENUasUmTTask(umTTask,
					umTTask.getCreatorCode(), umTTask.getMethodCode());
		}
	}

	/**
	 * 根据上级ID查所有有效的下级功能
	 * 
	 * @param curTaskId
	 * @author shenyichan
	 */
	public List<UmTTask> findByUpperTaskId(String curTaskId) {
		String hql = "from UmTTask task where task.upperTaskId=? and validStatus='1'";
		return umTTaskDao.findByHql(hql, curTaskId);
	}

	public List<String> findAllSubByUpperTaskId(String curTaskId,
			List<String> subTaskList) {
		String hql = "from UmTTask";
		List<UmTTask> allTaskList = umTTaskDao.findByHql(hql);
		return findAllSubByUpperTaskId(allTaskList, curTaskId, subTaskList);
	}

	/**
	 * 递归根据ID查询所有其下的子节点和孙节点
	 * 
	 * @param curTaskId
	 * @param subTaskList
	 * @return
	 */
	private List<String> findAllSubByUpperTaskId(List<UmTTask> allTaskList,
			String curTaskId, List<String> subTaskList) {
		subTaskList.add(curTaskId);
		for (UmTTask t : allTaskList) {
			if (curTaskId.equals(t.getUpperTaskId())) {
				findAllSubByUpperTaskId(allTaskList, t.getId().getTaskId(),
						subTaskList);
			}
		}
		return subTaskList;
	}

	/**
	 * 查询菜单树
	 */
	public String findMenuTreeJsonByQueryType(String taskId, String queryType,
			List<String> taskIds) {
		List<UmTTask> taskList = new ArrayList<UmTTask>();
		List<TreeNode> taskTreeNodeList = new ArrayList<TreeNode>();

		if (query_self.equals(queryType)) {
			// 查询出所有根级的功能对象
			String hql = "from UmTTask where (upperTaskId is null or upperTaskId='') and validStatus='1' and taskType='menu'";
			taskList = umTTaskDao.findByHql(hql);
		} else if (query_sub.equals(queryType)) {
			String hql = "from UmTTask where upperTaskId='" + taskId
					+ "' and validStatus='1' and taskType='menu'";
			taskList = umTTaskDao.findByHql(hql);
		}

		boolean checked = false;
		String state = "closed";
		if (taskIds != null && taskIds.size() > 0) {
			for (UmTTask t : taskList) {
				String id = t.getId().getTaskId();
				String text = t.getTaskName();
				if (taskIds.contains(id)) {
					checked = true;
				}
				Map map = new HashMap<String, Object>();
				map.put("initChecked", checked);// 每个节点都一个初始状态（即选中true或未选中false）
				map.put("taskCode", t.getTaskCode());
				taskTreeNodeList
						.add(new TreeNode(id, text, state, checked, map));

				checked = false;
			}
		} else {
			for (UmTTask t : taskList) {
				String id = t.getId().getTaskId();
				String text = t.getTaskName();
				Map map = new HashMap<String, Object>();
				map.put("initChecked", checked);
				map.put("taskCode", t.getTaskCode());
				taskTreeNodeList
						.add(new TreeNode(id, text, state, checked, map));
			}
		}

		JSONArray jsonArray = JSONArray.fromObject(taskTreeNodeList);
		return jsonArray.toString();
	}
	
	/**
	 * 查询菜单树
	 * 带有上下文环境
	 * add by liuyatao 2014年8月1日
	 */
	public String findMenuTreeJsonByQueryType(String taskId, String queryType,
			List<String> taskIds,String serverCode) {
		List<UmTTask> taskList = new ArrayList<UmTTask>();
		List<TreeNode> taskTreeNodeList = new ArrayList<TreeNode>();

		if (query_self.equals(queryType)) {
			// 查询出所有根级的功能对象
			String hql = "from UmTTask where (upperTaskId is null or upperTaskId='') and validStatus='1' and taskType='menu' and (svrcode='PUB' or svrcode='"+serverCode+"')";
			taskList = umTTaskDao.findByHql(hql);
		} else if (query_sub.equals(queryType)) {
			String hql = "from UmTTask where upperTaskId='" + taskId
					+ "' and validStatus='1' and taskType='menu'";
			taskList = umTTaskDao.findByHql(hql);
		}

		boolean checked = false;
		String state = "closed";
		if (taskIds != null && taskIds.size() > 0) {
			for (UmTTask t : taskList) {
				String id = t.getId().getTaskId();
				String text = t.getTaskName();
				if (taskIds.contains(id)) {
					checked = true;
				}
				Map map = new HashMap<String, Object>();
				map.put("initChecked", checked);// 每个节点都一个初始状态（即选中true或未选中false）
				map.put("taskCode", t.getTaskCode());
				taskTreeNodeList
						.add(new TreeNode(id, text, state, checked, map));

				checked = false;
			}
		} else {
			for (UmTTask t : taskList) {
				String id = t.getId().getTaskId();
				String text = t.getTaskName();
				Map map = new HashMap<String, Object>();
				map.put("initChecked", checked);
				map.put("taskCode", t.getTaskCode());
				taskTreeNodeList
						.add(new TreeNode(id, text, state, checked, map));
			}
		}

		JSONArray jsonArray = JSONArray.fromObject(taskTreeNodeList);
		return jsonArray.toString();
	}

	/**
	 * 查询门户树
	 */
	public String findPortalTreeJsonByQueryType(String taskId,
			String queryType, List<String> taskIds) {
		List<UmTTask> taskList = new ArrayList<UmTTask>();
		List<TreeNode> taskTreeNodeList = new ArrayList<TreeNode>();

		if (query_self.equals(queryType)) {
			// 查询出所有根级的功能对象
			String hql = "from UmTTask where (upperTaskId is null or upperTaskId='') and validStatus='1' and taskType='portal'";
			taskList = umTTaskDao.findByHql(hql);
		} else if (query_sub.equals(queryType)) {
			String hql = "from UmTTask where upperTaskId='" + taskId
					+ "' and validStatus='1' and taskType='portal'";
			taskList = umTTaskDao.findByHql(hql);
		}

		boolean checked = false;
		String state = "closed";
		if (taskIds != null && taskIds.size() > 0) {
			for (UmTTask t : taskList) {
				String id = t.getId().getTaskId();
				String text = t.getTaskName();
				if (taskIds.contains(id)) {
					checked = true;
				}
				Map map = new HashMap<String, Object>();
				map.put("initChecked", checked);// 每个节点都一个初始状态（即选中true或未选中false）
				map.put("taskCode", t.getTaskCode());
				taskTreeNodeList
						.add(new TreeNode(id, text, state, checked, map));

				checked = false;
			}
		} else {
			for (UmTTask t : taskList) {
				String id = t.getId().getTaskId();
				String text = t.getTaskName();
				Map map = new HashMap<String, Object>();
				map.put("initChecked", checked);
				map.put("taskCode", t.getTaskCode());
				taskTreeNodeList
						.add(new TreeNode(id, text, state, checked, map));
			}
		}

		JSONArray jsonArray = JSONArray.fromObject(taskTreeNodeList);
		return jsonArray.toString();
	}
	/**按照上下文进行重载** add by liuyatao 2014年8月1日*/
	public String findPortalTreeJsonByQueryType(String taskId,
			String queryType, List<String> taskIds,String serverCode) {
		List<UmTTask> taskList = new ArrayList<UmTTask>();
		List<TreeNode> taskTreeNodeList = new ArrayList<TreeNode>();

		if (query_self.equals(queryType)) {
			// 查询出所有根级的功能对象
			String hql = "from UmTTask where (upperTaskId is null or upperTaskId='') and validStatus='1' and taskType='portal' and (svrcode='PUB' or svrcode='"+serverCode+"')";
			taskList = umTTaskDao.findByHql(hql);
		} else if (query_sub.equals(queryType)) {
			String hql = "from UmTTask where upperTaskId='" + taskId
					+ "' and validStatus='1' and taskType='portal'";
			taskList = umTTaskDao.findByHql(hql);
		}

		boolean checked = false;
		String state = "closed";
		if (taskIds != null && taskIds.size() > 0) {
			for (UmTTask t : taskList) {
				String id = t.getId().getTaskId();
				String text = t.getTaskName();
				if (taskIds.contains(id)) {
					checked = true;
				}
				Map map = new HashMap<String, Object>();
				map.put("initChecked", checked);// 每个节点都一个初始状态（即选中true或未选中false）
				map.put("taskCode", t.getTaskCode());
				taskTreeNodeList
						.add(new TreeNode(id, text, state, checked, map));

				checked = false;
			}
		} else {
			for (UmTTask t : taskList) {
				String id = t.getId().getTaskId();
				String text = t.getTaskName();
				Map map = new HashMap<String, Object>();
				map.put("initChecked", checked);
				map.put("taskCode", t.getTaskCode());
				taskTreeNodeList
						.add(new TreeNode(id, text, state, checked, map));
			}
		}

		JSONArray jsonArray = JSONArray.fromObject(taskTreeNodeList);
		return jsonArray.toString();
	}
	/**
	 * 查询出所有的功能代码
	 */
	public Set<String> findAllTaskCode() {
		String hql = "from UmTTask where validStatus='1'";
		List<UmTTask> taskList = umTTaskDao.findByHql(hql);
		Set<String> taskCodeSet = new HashSet<String>();
		for (UmTTask t : taskList) {
			taskCodeSet.add(t.getTaskCode());
		}
		return taskCodeSet;
	}
	
	public UmTTask findUmTTaskByTaskCode(String TaskCode) throws Exception {
		// TODO Auto-generated method stub
		
		QueryRule rule = QueryRule.getInstance();
		rule.addEqual("taskCode", TaskCode);
		
		return umTTaskDao.findUnique(rule);
	}

	public List<TmTAppServiceConfig> findAppByUser(String userCode, String comId,
			String taskType) throws Exception {
		// 1.根据用户代码返回该用户所配置的角色信息与功列表之间的对应关系
		// 2.根据用户代码返回直接配置给该用户的功能列表
		String executeSQL = "select * from TM_T_APPSERVICECONFIG where servercode in (select distinct systemCode from um_t_menu where taskID in( select distinct taskid from um_t_roletask where "
				+ "roleid in (select roleid from um_t_userrole where usercode = ? and validstatus = '1') and "
				+ "taskid not in (select taskid from um_t_roledime where roleid in (select roleid from um_t_userrole where usercode = ? and validstatus = '1') "
				+ "and dimensioncode = 'comcode' and dimensionvalue = ? and operatetype = 'sub' and validstatus = '1') and validstatus = '1' "
				+ "union select taskid from um_t_roledime where roleid in (select roleid from um_t_userrole where usercode = ? and validstatus = '1') "
				+ "and dimensioncode = 'comcode' and dimensionvalue = ? and operatetype = 'add' and validstatus = '1' "
				+ "union select taskid from um_t_usertask where usercode = ? and validstatus = '1') and validstauts = '1')";

		Page page = commonDao.findObjectPageBySql(executeSQL,
				TmTAppServiceConfig.class, 1, Integer.MAX_VALUE, new Object[] {
						userCode, userCode, comId, userCode, comId, userCode});
	
		return page.getResult();
	}
}
