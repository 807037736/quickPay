/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.vo.TreeNode;
import com.picc.um.dao.UmTMENUDaoHibernate;
import com.picc.um.schema.model.UmTMENU;
import com.picc.um.schema.model.UmTMENUId;
import com.picc.um.schema.model.UmTTask;
import com.picc.um.service.facade.IUmTMENUService;
import com.picc.um.service.facade.IUmTTaskService;

/**
 * 菜单服务接口实现类
 * @author 杨联
 */
@Service("umTMENUService")
public class UmTMENUServiceSpringImpl implements IUmTMENUService {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UmTMENUDaoHibernate umTMENUDao;
	@Autowired
	private IUmTTaskService umTTaskService;

	/**
	 * 根据主键对象UmTMENUId获取UmTMENU信息
	 * 
	 * @param UmTMENUId
	 * @return UmTMENU
	 */
	public UmTMENU findUmTMENUByPK(UmTMENUId id) throws Exception {
		
		return umTMENUDao.get(UmTMENU.class, id);
	}

	/**
	 * 根据umTMENU和页码信息，获取Page对象
	 * 
	 * @param umTMENU
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTMENU的Page对象
	 */
	public Page findByUmTMENU(UmTMENU umTMENU, int pageNo, int pageSize)
			throws Exception {
		
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance

		if (!("").equals(umTMENU.getId().getMenuId())
				&& !(umTMENU.getId().getMenuId() == null)) {
			queryRule.addEqual("id", umTMENU.getId());
		}
		if (!("").equals(umTMENU.getMenuName())
				&& !(umTMENU.getMenuName() == null)) {
			queryRule.addLike("menuName", umTMENU.getMenuName());
		}
		if (!("").equals(umTMENU.getValidStauts())
				&& !(umTMENU.getValidStauts() == null)) {
			queryRule.addLike("validStauts", umTMENU.getValidStauts());
		}
		if (!("").equals(umTMENU.getTaskCode())
				&& !(umTMENU.getTaskCode() == null)) {
			queryRule.addLike("taskCode", umTMENU.getTaskCode());
		}
		if (!("").equals(umTMENU.getLevel()) && !(umTMENU.getLevel() == null)) {
			queryRule.addEqual("level", umTMENU.getLevel());
		}
		if (!("").equals(umTMENU.getActionUrl())
				&& !(umTMENU.getActionUrl() == null)) {
			queryRule.addLike("actionUrl", umTMENU.getActionUrl());
		}

		return umTMENUDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 通过taskid查找出umTMENU
	 */

	public UmTMENU findUmTMENUByTaskid(String taskid) {
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		queryRule.addEqual("taskId", taskid);
		UmTMENU umtmenu;

		if (umTMENUDao.find(queryRule).size() == 1) {
			umtmenu = (UmTMENU) umTMENUDao.find(queryRule).get(0);
		} else {
			return umtmenu = null;
		}
		return umtmenu;

	}

	/**
	 * 改造对象UmTTask成UmTMENU如果UmTMENU表中已经存在返回null
	 */
	@SuppressWarnings("null")
	public UmTMENU UmTTask2UmTMENU(UmTTask umttask, String url, String userCode) {
		UmTMENU umtmenu = new UmTMENU();
		UmTMENUId umtmenuid = new UmTMENUId();
		if (findUmTMENUByTaskid(umttask.getId().getTaskId()) == null) {
			umtmenu.setTaskId(umttask.getId().getTaskId());
			umtmenuid.setMenuId(umTMENUDao.getCommonDao().generateID("UMGR",
					"UM_SEQ_MENU", 25));
			umtmenu.setId(umtmenuid);
			umtmenu.setCreatorCode(userCode);
		} else {
			umtmenu = findUmTMENUByTaskid(umttask.getId().getTaskId());
		}
		umtmenu.setActionUrl(url);
		umtmenu.setTaskCode(umttask.getTaskCode());
		umtmenu.setMenuName(umttask.getTaskName());
		umtmenu.setValidStauts(umttask.getValidStatus());
		umtmenu.setSystemCode(umttask.getSvrCode());
		
		UmTMENU UpperMenu = findUmTMENUByTaskid(umttask.getUpperTaskId());

		if (UpperMenu == null) {
			umtmenu.setLevel(0);
			umtmenu.setUpperMenuId("0");
		} else {
			umtmenu.setLevel(UpperMenu.getLevel() + 1);
			umtmenu.setUpperMenuId(UpperMenu.getId().getMenuId());
		}

		Date now = new Date();
		umtmenu.setOperateTimeForHis(now);
		umtmenu.setInsertTimeForHis(now);
		umtmenu.setUpdaterCode(userCode);
		return umtmenu;

	}

	/**
	 * 查询出库里所有的菜单拼接成菜单树 flag=true 显示序号是否输出
	 * 
	 * @return
	 */
	public String findAllMenu(boolean flag) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("validStauts", "1");
		queryRule.addAscOrder("level");
		queryRule.addAscOrder("displayNo");
		List<UmTMENU> menuList = umTMENUDao.find(queryRule);
		List<TreeNode> menuNodeList = creatTreeList(menuList, flag);
		JSONArray _obj = JSONArray.fromObject(menuNodeList);
		String menuStr = _obj.toString().replace("\"", "\'");
		return menuStr;
	}

	public List<UmTMENU> findAllMenuList(boolean flag) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("validStauts", "1");
		
		queryRule.addAscOrder("level");
		queryRule.addAscOrder("displayNo");
		
		List<UmTMENU> menuList = umTMENUDao.find(queryRule);

		return menuList;
	}

	/**
	 * 创建菜单树
	 * 
	 * @param menuList
	 * @param flag
	 * @return
	 */
	private List<TreeNode> creatTreeList(List<UmTMENU> menuList, boolean flag) {
		if (menuList.size() > 0) {
			List<TreeNode> menuNodeList = new ArrayList<TreeNode>();
			TreeNode menuNode = null;
			for (UmTMENU menu : menuList) {
				// 构建节点数据
				/** 首先找出所有的根节点菜单 **/
				if (menu.getLevel() == 0) {
					menuNode = new TreeNode();
					menuNode.setId(menu.getId().getMenuId());
					if (flag) {
						menuNode.setText(menu.getMenuName() + "("
								+ menu.getDisplayNo() + ")");
					} else {
						menuNode.setText(menu.getMenuName());
					}
					/** 添加额外属性字段 **/
					menuNode.putAttributes("url", menu.getActionUrl());
					menuNode.putAttributes("level", menu.getLevel());
					menuNode.putAttributes("taskid", menu.getTaskId());
					menuNodeList.add(menuNode); // List中添加Node节点
				}
			}
			for (TreeNode upperMenuNode : menuNodeList) {
				expandChildNode(upperMenuNode, menuList, flag);
			}
			return menuNodeList;
		}
		return null;
	}

	/*
	 * 通过根节点查询子集菜单
	 */
	private void expandChildNode(TreeNode menuNodeParam,
			List<UmTMENU> menuList, boolean flag) {
		if (menuNodeParam == null || menuList == null) {
			return;
		} else {
			TreeNode menuNode = null;
			for (UmTMENU menuResInfo : menuList) {
				if (menuResInfo.getUpperMenuId().equals(menuNodeParam.getId())) {
					menuNode = new TreeNode();
					menuNode.setId(menuResInfo.getId().getMenuId());
					if (flag) {
						menuNode.setText(menuResInfo.getMenuName() + "("
								+ menuResInfo.getDisplayNo() + ")");
					} else {
						menuNode.setText(menuResInfo.getMenuName());
					}
					/** 添加额外属性字段 **/
					menuNode.putAttributes("url", menuResInfo.getActionUrl());
					menuNode.putAttributes("level", menuResInfo.getLevel());
					menuNode.putAttributes("taskid", menuResInfo.getTaskId());
					// menuNode.putAttributes("target",menuResInfo.getTARGET());
					// 添加子节点
					expandChildNode(menuNode, menuList, flag); // 递归调用
					menuNodeParam.addChildNode(menuNode);
				}
			}
		}
	}

	/*
	 * 解析菜单的上级菜单返回上级菜单list一直到根节点 需传入一个空List
	 */
	private List<UmTMENU> expandParentMenu(UmTMENU menu, List<UmTMENU> menuList, List<UmTMENU> allMenuList)
			throws Exception {

		UmTMENUId id = new UmTMENUId();
		menuList.add(menu);
		boolean flag = false;
		if (menu.getUpperMenuId() != null && menu.getUpperMenuId() != ""
				&& !"0".equals(menu.getUpperMenuId())) {
			id.setMenuId(menu.getUpperMenuId());
			for(int i=0;i<allMenuList.size();i++){
				if(id.getMenuId().equals(allMenuList.get(i).getId().getMenuId())){
					menu = this.findUmTMENUByPK(id);
					flag = true;
				}
			}
			if(flag){
				expandParentMenu(menu, menuList,allMenuList);
			}else{
				return menuList;
			}
		}

		return menuList;

	}

	/**
	 * 更新UmTMENU信息
	 * 
	 * @param UmTMENU
	 */
	public void updateUmTMENU(UmTMENU umTMENU, String userCode)
			throws Exception {
		try{
			Date now = new Date();
			umTMENU.setOperateTimeForHis(now);
			umTMENU.setUpdaterCode(userCode);
			umTMENUDao.update(umTMENU);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("", e);
		}
		
	}

	/**
	 * 保存UmTMENU信息
	 * 
	 * @param UmTMENU
	 */
	public void saveUmTMENU(UmTMENU umTMENU, String userCode) throws Exception {
		
		UmTMENUId umtmenuid = new UmTMENUId();
		umtmenuid.setMenuId(umTMENUDao.getCommonDao().generateID("UMGR",
				"UM_SEQ_MENU", 25));
		umTMENU.setId(umtmenuid);
		Date now = new Date();
		umTMENU.setOperateTimeForHis(now);
		umTMENU.setInsertTimeForHis(now);
		umTMENU.setCreatorCode(userCode);
		umTMENUDao.save(umTMENU);
	}

	/**
	 * 根据主键对象，删除UmTMENU信息
	 * 
	 * @param UmTMENUId
	 */
	public void deleteByPK(UmTMENUId id) throws Exception {
		
		umTMENUDao.deleteByPK(UmTMENU.class, id);
	}



	/**
	 * 对List内容去重
	 */
	public static List<UmTMENU> reBuildList(List<UmTMENU> menuList) {
		if (menuList != null) {
			List<UmTMENU> newMenuList = new ArrayList<UmTMENU>();
			// newMenuList.add(menuList.get(0));
			for (UmTMENU umtmenu : menuList) {
				if (!newMenuList.contains(umtmenu)) {
					newMenuList.add(umtmenu);
				}

			}
			return newMenuList;
		} else {
			return menuList;
		}

	}

	/**
	 * 通过用户代码查询菜单信息返回页面
	 */
	@SuppressWarnings("static-access")
	public String findMnueByUsercode(String usercode,String comId,String serverCode, boolean flag)
			throws Exception {
		List<String> taskIdList = umTTaskService.getTaskListByUserCode(usercode,comId,serverCode, "menu");
		//List<UmTTask> taskList = umTTaskService.getTaskListByUserCode(usercode,comId,serverCode, "menu");
		UmTMENU menu = null;
		List<UmTMENU> menuList = new ArrayList<UmTMENU>();
		List<UmTMENU> resultmenuList = new ArrayList<UmTMENU>();
		List<UmTMENU> allMenuList = this.findAllMenuList(false);

		if (taskIdList.size() > 0) {
			for (int i = 0; i < allMenuList.size(); i++) {
				for (int j = 0; j < taskIdList.size(); j++) {
					if(allMenuList.get(i).getTaskId().equals(taskIdList.get(j)))
					menu = allMenuList.get(i);
					if (menu != null) {
						//menuList.add(menu);
						resultmenuList.addAll(this.expandParentMenu(menu,
								menuList,allMenuList));
						resultmenuList = this.reBuildList(resultmenuList);
						menuList.clear();

					}
				}
			}
			List<TreeNode> menuNodeList = creatTreeList(resultmenuList, flag);
			JSONArray _obj = JSONArray.fromObject(menuNodeList);
			String menuStr = _obj.toString().replace("\"", "\'");
			return menuStr;

		}
		return null;
	}
	
	/**
	 * overload by liuyatao
	 * 2014-7-31
	 * 通过用户代码查询菜单信息返回页面
	 */
	@SuppressWarnings("static-access")
	public String findMenuByUsercode(String usercode,String comId,String serverCode,String serverType,boolean flag)
			throws Exception {
		List<UmTTask> taskList = umTTaskService.getTaskObjectListByUserCode(usercode,comId,serverCode,serverType, "menu");
		UmTMENU menu = null;
		List<UmTMENU> menuList = new ArrayList<UmTMENU>();
		List<UmTMENU> resultmenuList = new ArrayList<UmTMENU>();
		List<UmTMENU> allMenuList = this.findAllMenuList(false);

		if (taskList.size() > 0) {
			for (int i = 0; i < allMenuList.size(); i++) {
				for (int j = 0; j < taskList.size(); j++) {
					if(allMenuList.get(i).getTaskId().equals(taskList.get(j).getId().getTaskId()))
					menu = allMenuList.get(i);
					if (menu != null) {
						//menuList.add(menu);
						//add this
						menu.setIsOpen(taskList.get(j).getIsOpen());
						menu.setOpenLevel(taskList.get(j).getOpenLevel());
						menu.setUserType(taskList.get(j).getUserType());
						//add end
						resultmenuList.addAll(this.expandParentMenu(menu,
								menuList,allMenuList));
						resultmenuList = this.reBuildList(resultmenuList);
						menuList.clear();

					}
				}
			}
			List<TreeNode> menuNodeList = creatTreeList(resultmenuList, flag);
			JSONArray _obj = JSONArray.fromObject(menuNodeList);
			String menuStr = _obj.toString().replace("\"", "\'");
			return menuStr;

		}
		return null;
	}


	/**
	 * 通过用户代码查询用户可操作的系统有哪些(2)
	 * add by liuyatao 2014年3月18日
	 */
	@SuppressWarnings("static-access")
	public String findSystemCodesByUsercode(String usercode,String comId, boolean flag)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> systemcodeList = umTTaskService.getSystemListByUserCode(
				usercode,comId, "menu");
		//返回json格式
		JSONArray _obj = JSONArray.fromObject(systemcodeList);
		String menuStr = _obj.toString().replace("\"", "\'");
		return menuStr;
	}
	
	@SuppressWarnings("unused")
	public void saveUmTMENUasUmTTask(UmTTask umttask, String userCode,
			String url) throws Exception {
		// TODO Auto-generated method stub
		if (findUmTMENUByTaskid(umttask.getId().getTaskId()) == null) {
			UmTMENU umtmenu = UmTTask2UmTMENU(umttask, url, userCode);
			umTMENUDao.save(umtmenu);
		}

	}

	public void updateUmTMENUasUmTTask(UmTTask umttask, String userCode,
			String url) throws Exception {
		if (findUmTMENUByTaskid(umttask.getId().getTaskId()) != null) {
			//菜单已经存在时    修改菜单
			UmTMENU umtmenu = UmTTask2UmTMENU(umttask, url, userCode);
			umTMENUDao.update(umtmenu);
		}else {
			//菜单不存在时    新增菜单
			saveUmTMENUasUmTTask(umttask,userCode,url);
		}
	}

	/**
	 * 根据功能ID查询出相应的菜单
	 * @author shenyichan
	 */
	public UmTMENU findByTaskId(String taskId) {
		String hql = "select um from UmTMENU um where um.taskId = ?";
		List<UmTMENU> menuList = umTMENUDao.findByHql(hql, taskId);
		if(menuList!=null&&menuList.size()>0){
			if(menuList.size() == 1){
				return menuList.get(0);
			}else{
				throw new RuntimeException("查询出的功能菜单多于一条！");
			}
		}else{
			return null;
		}
	}

}
