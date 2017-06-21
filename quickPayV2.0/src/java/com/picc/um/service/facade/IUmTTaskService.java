/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.facade;

import ins.framework.common.Page;

import java.util.List;
import java.util.Set;

import com.picc.tm.common.schema.model.TmTAppServiceConfig;
import com.picc.um.schema.model.UmTTask;
import com.picc.um.schema.model.UmTTaskId;

/**
 * 功能服务自定义接口
 * @author 姜卫洋
 */
public interface IUmTTaskService{

	/**
	 * 根据主键对象UmTTaskId获取UmTTask信息
	 * @param UmTTaskId
	 * @return UmTTask
	 */
	public UmTTask findUmTTaskByPK(UmTTaskId id) throws Exception;
	public List<UmTTask> findTaskByOpenStatus(String serverCode);
	public String findTaskTreeJsonByQueryType(String taskId, String queryType,
			List<String> taskIds,String serverCode) ;
	public String findPortalTreeJsonByQueryType(String taskId,
			String queryType, List<String> taskIds,String serverCode) ;
	public String findTaskTreeJsonByQueryType(String taskId, String queryType,
			List<String> taskIds, List<String> taskIds2,String serverCode);
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public UmTTask findUmTTaskByTaskCode(String TaskCode) throws Exception;
	
	public List<UmTTask> getTaskObjectListByUserCode(String userCode, String comId,String serverCode,
			String serverType,String taskType) throws Exception ;

	/**
	 * 根据umTTask和页码信息，获取Page对象
	 * @param umTTask
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTTask的Page对象
	 */
	public Page findByUmTTask(UmTTask umTTask, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新UmTTask信息
	 * @param UmTTask
	 */
	public void updateUmTTask(UmTTask umTTask) throws Exception;

	/**
	 * 保存UmTTask信息
	 * @param UmTTask
	 */
	public void saveUmTTaskAndMethod(UmTTask umTTask) throws Exception;

	/**
	 * 根据主键对象，删除UmTTask信息
	 * @param UmTTaskId
	 */
	public void deleteByPK(UmTTaskId id) throws Exception;


	/**
	 * 查询出所有的功能
	 * @return
	 */
	public Page findAll(int page, int rows);
	
	/**
	 * 根据用户代码获取该用户的功能集合
	 * @return
	 */
	public List<UmTTask> findTaskByUserCode(String userCode);
	
	/**
	 * 根据条件查询功能集合的JSON
	 * @return
	 */
	public String findTaskTreeJsonByQueryType(String userCode,String comId);
	/**
	 * 根据条件查询功能集合的JSON,带有上下文
	 * @return
	 */
	public String findMenuTreeJsonByQueryType(String taskId, String queryType,
			List<String> taskIds,String serverCode);
	
	/**
	 * 根据不同的查询类型返回对应的树结构数据
	 * @param taskId
	 * @param queryType
	 * @param taskIds
	 * @return
	 * shenyichan
	 */
	public String findTaskTreeJsonByQueryType(String taskId, String queryType, List<String> taskIds);
	
	public String findTaskTreeJsonByQueryType(String taskId, String queryType, List<String> taskIds, List<String> taskIds2);
	
	
	/**
	 * 根据用户代码返回其所对应的Task功能对象
	 * @param userCode				用户代码
	 * @param taskType             "ALL","portal","menu","button"
	 * @return	 							TaskID List对象
	 * @throws Exception			程序运行中抛出的异常信息
	 * 2013-8-12下午4:08:44
	 * jiangweiyang
	 */
	public List<String> getTaskListByUserCode(String userCode,String comId,String serverCode,String taskType) throws Exception;
	
	
	public List<String> getPorletidByUserCode(String userCode,String comId,String serverCode) throws Exception;
	
	/**
	 * 查询与角色关联且有效的功能
	 * @param roleId
	 * @return
	 * @author shenyichan
	 */
	public List<UmTTask> findByRoleId(String roleId,String comId) throws Exception;

	/**
	 * 根据用户代码查询与该用户直接关联的功能
	 * @param userCode
	 * @return
	 */
	public List<UmTTask> findByUserCode(String userCode);

	/**
	 * 修改功能、菜单、方法
	 * @param umTTask
	 * 2013-9-3
	 * @author shenyichan
	 */
	public void updateUmTTaskVSMethodVSMenu(UmTTask umTTask) throws Exception;

	/**
	 * 增加功能、菜单、方法
	 * @param umTTask
	 * 2013-9-3
	 * @author shenyichan
	 */
	public void saveUmTTaskVSMethodVSMenu(UmTTask umTTask) throws Exception;

	/**
	 * 根据上级ID查所有下级功能
	 * @param curTaskId
	 * @author shenyichan
	 */
	public List<UmTTask> findByUpperTaskId(String curTaskId);

	/**
	 *  递归根据ID查询所有其下的子节点和孙节点
	 * @param curTaskId
	 * @param subTaskList
	 * @return
	 */
	public List<String> findAllSubByUpperTaskId(String curTaskId,List<String> subTaskList);

	/**
	 * 查询菜单树
	 * @param nodeId
	 * @param queryType
	 * @param checkedNodes
	 * @return
	 */
	public String findMenuTreeJsonByQueryType(String nodeId, String queryType,
			List<String> checkedNodes);

	/**
	 * 查询门户树
	 * @param nodeId
	 * @param queryType
	 * @param checkedNodes
	 * @return
	 */
	public String findPortalTreeJsonByQueryType(String nodeId,
			String queryType, List<String> checkedNodes);

	/**
	 * 查询出所的功能代码
	 * @return
	 */
	public Set<String> findAllTaskCode();
	/**
	 * 查出所有用户可操作的系统代码
	 * @param userCode
	 * @param comId
	 * @param taskType
	 * @return
	 * @throws Exception
	 */
	public List<String> getSystemListByUserCode(String userCode, String comId,
			String taskType) throws Exception;
	
	public List<TmTAppServiceConfig> findAppByUser(String userCode, String comId,
			String taskType) throws Exception;
}
