package com.picc.common.web;

import ins.framework.web.Struts2Action;

import java.util.ArrayList;
import java.util.List;

import com.picc.common.Constants;
import com.picc.platform.dms.service.facade.ICompanyService;
import com.picc.um.schema.model.UmTTask;
import com.picc.um.schema.model.UmTUserRole;
import com.picc.um.schema.model.UmTUserTask;
import com.picc.um.service.facade.IUmTRoleComService;
import com.picc.um.service.facade.IUmTTaskService;
import com.picc.um.service.facade.IUmTUserGroupService;
import com.picc.um.service.facade.IUmTUserPowerService;
import com.picc.um.service.facade.IUmTUserRoleService;
import com.picc.um.service.facade.IUmTUserService;
import com.picc.um.service.facade.IUmTUserTaskService;
/*import com.picc.wxAdmin.service.facade.IWxMpCommandService;
import com.picc.wxAdmin.service.facade.IWxMpMenuService;*/

@SuppressWarnings("serial")
public class TreeAction extends Struts2Action {
	
	/**机构服务*/
	private ICompanyService companyService; 
	private IUmTTaskService umTTaskService;	 
	private IUmTRoleComService umTRoleComService;
	private IUmTUserPowerService umTUserPowerService;
	private IUmTUserService umTUserService;
	private IUmTUserGroupService umTUserGroupService;
	private IUmTUserRoleService umTUserRoleService;
	private IUmTUserTaskService umTUserTaskService;
	/*private IWxMpMenuService wxMpMenuService;
	private IWxMpCommandService wxMpCommandService;*/
	
	private String treeType;   //树的类型
	private String queryType;  //查询类型
	private String treeNodeType="";//树节点的类型
	private String comCode="";  //所属机构数据
	private Boolean haveSpend = false;  //已经展开过
	private String requestUrl = "";//访问路径请求URL
	private String objectId;
	private Boolean onUser = false; //根据当前登录人的权限查询

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public IUmTUserPowerService getUmTUserPowerService() {
		return umTUserPowerService;
	}

	public void setUmTUserPowerService(IUmTUserPowerService umTUserPowerService) {
		this.umTUserPowerService = umTUserPowerService;
	}

	public IUmTUserService getUmTUserService() {
		return umTUserService;
	}

	public void setUmTUserService(IUmTUserService umTUserService) {
		this.umTUserService = umTUserService;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	public void setHaveSpend(Boolean haveSpend) {
		this.haveSpend = haveSpend;
	}

	public Boolean getHaveSpend() {
		return haveSpend;
	}

	public void setOnUser(Boolean onUser) {
		this.onUser = onUser;
	}

	public Boolean getOnUser() {
		return onUser;
	}

	public IUmTUserGroupService getUmTUserGroupService() {
		return umTUserGroupService;
	}

	public void setUmTUserGroupService(IUmTUserGroupService umTUserGroupService) {
		this.umTUserGroupService = umTUserGroupService;
	}

	public IUmTUserRoleService getUmTUserRoleService() {
		return umTUserRoleService;
	}

	public void setUmTUserRoleService(IUmTUserRoleService umTUserRoleService) {
		this.umTUserRoleService = umTUserRoleService;
	}
	
	
	public IUmTUserTaskService getUmTUserTaskService() {
		return umTUserTaskService;
	}

	public void setUmTUserTaskService(IUmTUserTaskService umTUserTaskService) {
		this.umTUserTaskService = umTUserTaskService;
	}
	
	

	/*public IWxMpMenuService getWxMpMenuService() {
		return wxMpMenuService;
	}

	public void setWxMpMenuService(IWxMpMenuService wxMpMenuService) {
		this.wxMpMenuService = wxMpMenuService;
	}
	


	public IWxMpCommandService getWxMpCommandService() {
		return wxMpCommandService;
	}

	public void setWxMpCommandService(IWxMpCommandService wxMpCommandService) {
		this.wxMpCommandService = wxMpCommandService;
	}
*/


	private List<String> checkedNodes;  //被选中的id集合
	
	private String nodeId;      //树节点的ID
	private String nodeChecked;
	private String activityID; 
	
	private String processID;
	private String processNodeID;
	private String groupID;
	private String groupCode;//记录车行送修码
	private String taskClaimStatus;
	/** 任务ID数组 **/
	private List<String> taskIdArray;
	
	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public List<String> getTaskIdArray() {
		return taskIdArray;
	}

	public void setTaskIdArray(List<String> taskIdArray) {
		this.taskIdArray = taskIdArray;
	}

	public String getTaskClaimStatus() {
		return taskClaimStatus;
	}

	public void setTaskClaimStatus(String taskClaimStatus) {
		this.taskClaimStatus = taskClaimStatus;
	}

	public String getGroupID() {
		return groupID;
	}

	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}

	public String getTreeNodeType() {
		return treeNodeType;
	}

	public void setTreeNodeType(String treeNodeType) {
		this.treeNodeType = treeNodeType;
	}

	public String getActivityID() {
		return activityID;
	}

	public void setActivityID(String activityID) {
		this.activityID = activityID;
	}
	
	public String getProcessID() {
		return processID;
	}

	public void setProcessID(String processID) {
		this.processID = processID;
	}

	public String getProcessNodeID() {
		return processNodeID;
	}

	public void setProcessNodeID(String processNodeID) {
		this.processNodeID = processNodeID;
	}

	public ICompanyService getCompanyService() {
		return companyService;
	}

	public void setCompanyService(ICompanyService companyService) {
		this.companyService = companyService;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	
	public List<String> getCheckedNodes() {
		return checkedNodes;
	}

	public void setCheckedNodes(List<String> checkedNodes) {
		this.checkedNodes = checkedNodes;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public IUmTTaskService getUmTTaskService() {
		return umTTaskService;
	}

	public void setUmTTaskService(IUmTTaskService umTTaskService) {
		this.umTTaskService = umTTaskService;
	}

	public IUmTRoleComService getUmTRoleComService() {
		return umTRoleComService;
	}

	public void setUmTRoleComService(IUmTRoleComService umTRoleComService) {
		this.umTRoleComService = umTRoleComService;
	}

	/**
	 * 查询树
	 */
	public void query() throws Exception{
		logger.info("the current serverCode:{}",serverCode);
		logger.info("the current node check state:{}",nodeChecked);
		if(treeType==null||"".equals(treeType)){
			this.writeJSONMsg("找不到相应的树！");
		}else{
			if(Constants.COMPANY_TREE.equals(treeType)){
				//机构树
				queryCompanyTree(nodeId, queryType);
			}else if(Constants.COMPANY_COMID_TREE.equals(treeType)){
				//机构树-根据comId
				queryCompanyComIdTree(nodeId, queryType);
			}else if(Constants.COMPANY_ROLE_TREE.equals(treeType)){
				//机构——角色树
				queryCompanyRoleTree(nodeId, queryType);
			}else if(Constants.TASK_TREE.equals(treeType)){
				//功能树
				queryTaskTree(nodeId, queryType,serverCode);
			}else if(Constants.MENU_TREE.equals(treeType)) {
				//菜单树
				queryMenuTree(nodeId, queryType,serverCode);
			}else if(Constants.PORTAL_TREE.equals(treeType)) {
				//门户树
				queryPortalTree(nodeId, queryType,serverCode);
			}else if(Constants.USERTASK_TREE.equals(treeType)){
				queryUserTaskTree(nodeId, queryType,serverCode);
			}/*else if(Constants.COMMAND_TREE_WX.equals(treeType)){
				queryWxCommandTree(nodeId, queryType);
			}else if(Constants.MENU_TREE_WX.equals(treeType)){
				queryWxMenuTree(nodeId, queryType);
			}*/
		}
	}
	

	/*逐级分配 找流程岗*/
	//该方法已弃用
//	private void queryRoleGroupNode(){
//		System.out.println("==========TreeAction===queryRoleGroupNode========");
//		String ComCode = getSession().getAttribute("ComCode").toString();
//		this.renderText(wfTTaskService.queryAllGroupNode(ComCode));
//	}
	/*逐级分配 找某机构的流程管理岗人员   弃用*/
//	private void queryRoleUserNode(String nodeId){
//		
//		System.out.println("==========TreeAction===queryRoleUserNode========");
//		String ComCode = getSession().getAttribute("ComCode").toString();
//		this.renderText(wfTTaskService.queryRoleUserNode(ComCode,nodeId));
//	}
	private void queryPortalTree(String nodeId, String queryType) {
//		System.out.println("------------查询门户树------------");
		this.renderText(umTTaskService.findPortalTreeJsonByQueryType(nodeId, queryType, checkedNodes));
	}
	
	private void queryPortalTree(String nodeId, String queryType,String serverCode) {
//		System.out.println("------------查询门户树------------");
		this.renderText(umTTaskService.findPortalTreeJsonByQueryType(nodeId, queryType, checkedNodes,serverCode));
	}

	private void queryMenuTree(String nodeId, String queryType) {
//		System.out.println("------------查询菜单树------------");
		this.renderText(umTTaskService.findMenuTreeJsonByQueryType(nodeId, queryType, checkedNodes));
	}
	/**查询菜单树,只能查到上下文环境所在系统的菜单**
	 * add by liuytao 2014年8月1日*/
	private void queryMenuTree(String nodeId, String queryType,String context) {
//		System.out.println("------------查询菜单树------------");
		//context = this.getCurrentServerCode();
		this.renderText(umTTaskService.findMenuTreeJsonByQueryType(nodeId, queryType, checkedNodes,context));
	}
	/*//查询微信的关键字树
	private void queryWxCommandTree(String nodeId, String queryType) {
//		System.out.println("------------查询菜单树------------");
		this.renderText(wxMpCommandService.findCommandTreeJsonByQueryType(nodeId, queryType, checkedNodes));
	}
	
	//查询微信的关键字树
		private void queryWxMenuTree(String nodeId, String queryType) {
//			System.out.println("------------查询菜单树------------");
			this.renderText(wxMpMenuService.findMenuTreeJsonByQueryType(nodeId, queryType, checkedNodes));
		}
*/

	/**
	 * 查询机构树
	 * @param nodeId   	   页面上点击的节点的id
	 * @param queryType  查询类型，是查本身还是查下级
	 */
	private void queryCompanyTree(String nodeId, String queryType){
		System.out.println("------------查询机构树------------");
		if(nodeId==null || "".equals(nodeId)){
			//如果comCode是空值或查询类型为0时，则表示取当前用户的所在机构
			nodeId = getSession().getAttribute("ComCode").toString();
		}
		
		this.renderText(companyService.findComTreeJsonByQueryType(nodeId,queryType,checkedNodes));
	}
	/**
	 * 根据comid查询机构树
	 * @param nodeId   	   页面上点击的节点的id
	 * @param queryType  查询类型，是查本身还是查下级
	 */
	private void queryCompanyComIdTree(String nodeId, String queryType){
		System.out.println("------------查询机构树------------");
		if(nodeId==null || "".equals(nodeId)){
			nodeId = getUserFromSession().getComId();
		}
		this.renderText(companyService.findComTreeJsonByQueryType(nodeId,queryType,checkedNodes));
	}
	/**
	 * 查询功能树
	 * @param nodeId       页面上点击的节点的id
	 * @param queryType    查询类型，是查本身还是查下级
	 * @throws Exception 
	 */
	private void queryTaskTree(String nodeId, String queryType) throws Exception{
		System.out.println("------------查询功能树------------");
		
		List<UmTTask> taskList = umTTaskService.findByRoleId(objectId,getUserFromSession().getComId());
		checkedNodes = new ArrayList<String>();
		for(UmTTask t:taskList){
			checkedNodes.add(t.getId().getTaskId());
		}
		 
		this.renderText(umTTaskService.findTaskTreeJsonByQueryType(nodeId, queryType, checkedNodes));
	}
	/**带有上下文参数的重载。add by liuyatao 2014年8月1日*/
	private void queryTaskTree(String nodeId, String queryType,String serverCode) throws Exception{
		System.out.println("------------查询功能树------------");
		
		List<UmTTask> taskList = umTTaskService.findByRoleId(objectId,getUserFromSession().getComId());
		checkedNodes = new ArrayList<String>();
		for(UmTTask t:taskList){
			checkedNodes.add(t.getId().getTaskId());
		}
		 
		this.renderText(umTTaskService.findTaskTreeJsonByQueryType(nodeId, queryType, checkedNodes,serverCode));
	}
	
	/**
	 * 查询用户功能树
	 * @param nodeId
	 * @param queryType
	 * @throws Exception
	 */
	private void queryUserTaskTree(String nodeId, String queryType) throws Exception{
		System.out.println("------------查询用户功能树------------");
		List<UmTUserRole> umTUserRoleList = umTUserRoleService.findUserRoleByUserCode(objectId);
		List<UmTTask> taskList =new ArrayList<UmTTask>();
		for(UmTUserRole umTUserRole:umTUserRoleList){
			taskList.addAll(umTTaskService.findByRoleId(umTUserRole.getRoleId(),getUserFromSession().getComId()));
		}
		//List<UmTTask> taskList = umTTaskService.findByRoleId(objectId,getUserFromSession().getComId());
		checkedNodes = new ArrayList<String>();
		for(UmTTask t:taskList){
			checkedNodes.add(t.getId().getTaskId());
			
		}
		
		List<UmTUserTask> umTUserTaskList=umTUserTaskService.findUserTaskByUserCode(objectId);
		List<String> checkedNodes2 = new ArrayList<String>();
		for(UmTUserTask umTUserTask:umTUserTaskList){
			checkedNodes2.add(umTUserTask.getTaskId());
		}
		this.renderText(umTTaskService.findTaskTreeJsonByQueryType(nodeId, queryType, checkedNodes,checkedNodes2));
	}
	/**overload by liuyatao 2014-08-01**/
	private void queryUserTaskTree(String nodeId, String queryType,String serverCode) throws Exception{
		System.out.println("------------查询用户功能树------------");
		List<UmTUserRole> umTUserRoleList = umTUserRoleService.findUserRoleByUserCode(objectId);
		List<UmTTask> taskList =new ArrayList<UmTTask>();
		for(UmTUserRole umTUserRole:umTUserRoleList){
			taskList.addAll(umTTaskService.findByRoleId(umTUserRole.getRoleId(),getUserFromSession().getComId()));
		}
		//List<UmTTask> taskList = umTTaskService.findByRoleId(objectId,getUserFromSession().getComId());
		checkedNodes = new ArrayList<String>();
		for(UmTTask t:taskList){
			checkedNodes.add(t.getId().getTaskId());
			
		}
		
		List<UmTUserTask> umTUserTaskList=umTUserTaskService.findUserTaskByUserCode(objectId);
		List<String> checkedNodes2 = new ArrayList<String>();
		for(UmTUserTask umTUserTask:umTUserTaskList){
			checkedNodes2.add(umTUserTask.getTaskId());
		}
		this.renderText(umTTaskService.findTaskTreeJsonByQueryType(nodeId, queryType, checkedNodes,checkedNodes2,serverCode));
	}
	
	/**
	 * 查询机构-角色树
	 * @param nodeId
	 * @param queryType
	 */
	private void queryCompanyRoleTree(String nodeId, String queryType){
		System.out.println("------------查询机构—角色树------------");
		if(nodeId==null || "".equals(nodeId)){
			//如果comCode是空值或查询类型为0时，则表示取当前用户的所在机构
			nodeId = getSession().getAttribute("ComCode").toString();
		}
		
		this.renderText(umTRoleComService.getRoleListByComCode(nodeId,queryType,0,0));
	}

	/**
	 * @param nodeChecked the nodeChecked to set
	 */
	public void setNodeChecked(String nodeChecked) {
		this.nodeChecked = nodeChecked;
	}

	/**
	 * @return the nodeChecked
	 */
	public String getNodeChecked() {
		return nodeChecked;
	}
}
