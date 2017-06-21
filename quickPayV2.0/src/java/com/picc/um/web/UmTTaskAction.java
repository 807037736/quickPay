/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.web;

import ins.framework.common.Page;
import ins.framework.web.JsonValueFormat;
import ins.framework.web.Struts2Action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.picc.tm.schema.model.TmTApplicationConfig;
import com.picc.tm.service.facade.ITmTApplicationConfigService;
import com.picc.um.schema.model.UmTMethodTask;
import com.picc.um.schema.model.UmTTask;
import com.picc.um.schema.model.UmTTaskId;
import com.picc.um.service.facade.IUmTMENUService;
import com.picc.um.service.facade.IUmTMethodTaskService;
import com.picc.um.service.facade.IUmTTaskService;

/**
 * 功能处理Action层
 * @author 姜卫洋
 */
@SuppressWarnings("serial")
public class UmTTaskAction extends Struts2Action {

	private IUmTTaskService umTTaskService;

	private IUmTMethodTaskService umTMethodTaskService;

	private IUmTMENUService umTMENUService;

	private UmTTask umTTask;

	private UmTTaskId id;

	private String taskStr;

	private List<String> taskIds; // 当前角色已有的功能

	private String userCode;

	private String curTaskId; // 当前功能的ID

	private String upperTaskId; // 上级功能的ID
	
	private String taskCode;
	
	private String initTaskCode;
	
	private String serverCode;

	private ITmTApplicationConfigService tmTApplicationConfigService;
	
	public ITmTApplicationConfigService getTmTApplicationConfigService() {
		return tmTApplicationConfigService;
	}

	public void setTmTApplicationConfigService(
			ITmTApplicationConfigService tmTApplicationConfigService) {
		this.tmTApplicationConfigService = tmTApplicationConfigService;
	}

	public String getInitTaskCode() {
		return initTaskCode;
	}

	public void setInitTaskCode(String initTaskCode) {
		this.initTaskCode = initTaskCode;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public String getCurTaskId() {
		return curTaskId;
	}

	public void setCurTaskId(String curTaskId) {
		this.curTaskId = curTaskId;
	}

	public String getUpperTaskId() {
		return upperTaskId;
	}

	public void setUpperTaskId(String upperTaskId) {
		this.upperTaskId = upperTaskId;
	}

	public IUmTMENUService getUmTMENUService() {
		return umTMENUService;
	}

	public void setUmTMENUService(IUmTMENUService umTMENUService) {
		this.umTMENUService = umTMENUService;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public IUmTMethodTaskService getUmTMethodTaskService() {
		return umTMethodTaskService;
	}

	public void setUmTMethodTaskService(
			IUmTMethodTaskService umTMethodTaskService) {
		this.umTMethodTaskService = umTMethodTaskService;
	}

	public void setUmTTaskService(IUmTTaskService umTTaskService) {
		this.umTTaskService = umTTaskService;
	}

	public IUmTTaskService getUmTTaskService() {
		return umTTaskService;
	}

	/** 操作类型 **/
	private String opreateType;

	/** UmTTask getter/setter **/
	public UmTTask getUmTTask() {
		return this.umTTask;
	}

	public void setUmTTask(UmTTask umTTask) {
		this.umTTask = umTTask;
	}

	/** UmTTaskId getter/setter **/
	public UmTTaskId getId() {
		return this.id;
	}

	public void setId(UmTTaskId id) {
		this.id = id;
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

	/** opreateType getter/setter **/
	public String getOpreateType() {
		return opreateType;
	}

	public void setOpreateType(String opreateType) {
		this.opreateType = opreateType;
	}

	/** 主键对象 */
	private String taskId;

	public String getTaskId() {
		return this.taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
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
	 * UmTTask查询，根据umTTask带过来的值为查询条件进行查询。
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
			Page resultPage = umTTaskService.findByUmTTask(umTTask, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}

	/**
	 * 查询出所有的功能对象
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryAll() throws Exception {

		if (pageNo == 0) {
			pageNo = 1;
		}
		if (pageSize == 0) {
			pageSize = 20;
		}

		try {
			Page resultPage = umTTaskService.findAll(pageNo, pageSize);
			this.writeJSONData(resultPage, "taskCode", "taskName", "id.taskId");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}

	/**
	 * 准备更新UmTTask信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		opreateType = "update";

		// 查询出功能
		umTTask = umTTaskService.findUmTTaskByPK(id);

		// 查询出功能方法，并将url放入功能对象的methodCode字段中
		UmTMethodTask umTMethodTask = umTMethodTaskService.findByTaskId(id
				.getTaskId());
		if (umTMethodTask != null) {
			umTTask.setMethodCode(umTMethodTask.getMethodCode());
		}
		return SUCCESS;
	}

	/**
	 * 更新UmTTask信息
	 * 
	 * @return
	 */
	public String update(){
		Map<String,String> resultMap = new HashMap<String,String>();
		try {
			umTTask.setUpdaterCode(getUserFromSession().getUserCode());
			umTTask.setOperateTimeForHis(new Date());

			// 同时修改功能、菜单、方法
			umTTaskService.updateUmTTaskVSMethodVSMenu(umTTask);
			resultMap.put("status", "success");
			writeMsgByMap(resultMap);
		} catch (Exception e) {
			resultMap.put("status", "fail");
			resultMap.put("content", e.getMessage());
			writeMsgByMap(resultMap);
		}
		return NONE;
	}

	/**
	 * 准备增加UmTTask信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		opreateType = "add";
		TmTApplicationConfig tmTApplicationConfig = tmTApplicationConfigService.getApplicationByContext(this.getRequest().getContextPath().replaceFirst("/", ""));
		serverCode = tmTApplicationConfig.getId().getServerCode();
		return SUCCESS;
	}

	/**
	 * 新增UmTTask信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		// 加入创建人代码
		umTTask.setCreatorCode((String) getSession().getAttribute("UserCode"));

		// 功能类型不能为空
		if (umTTask.getTaskType() != null && !"".equals(umTTask.getTaskType())) {
			// 同时增加功能、菜单、方法
			umTTaskService.saveUmTTaskVSMethodVSMenu(umTTask);
		} else {
			getRequest().setAttribute("exception",
					new RuntimeException("功能类型不能为空！"));
			return ERROR;
		}

		return SUCCESS;
	}

	/**
	 * 删除UmTTask信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try {
			if (id != null) {
				umTTaskService.deleteByPK(id);
			}
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
	 * 查看UmTTask信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {

		opreateType = "view";
		umTTask = umTTaskService.findUmTTaskByPK(id);
		return SUCCESS;
	}

	public String findTaskTree() throws Exception {
		super.renderText(umTTaskService.findTaskTreeJsonByQueryType(userCode,
				getUserFromSession().getComId()));
		return NONE;
	}

	/**
	 * 选择上级功能时，要做一个验证控制， 判断选中的功能是不是当前功能的子级
	 * @return
	 */
	public String checkUpperTask() {
		String msg = "success"; // 错误标识

		if (curTaskId.equals(upperTaskId)) { // 判断是否选择自己作为上级功能，如是，则报错
			msg = "fail";
		} else {
			// 判断是否选择子级或孙级作为上级功能，如是，则报错
			List<String> taskIdList = new ArrayList<String>();
			umTTaskService.findAllSubByUpperTaskId(curTaskId, taskIdList);
			if (taskIdList != null && taskIdList.size() > 0
					&& taskIdList.contains(upperTaskId)) {
				// 当所选的上级功能是 子级功能时，则返回错误信息
				msg = "fail";
			}
		}
		this.writeString(msg);
		return NONE;
		
	}

	public void writeMsgByMap(Map map) {
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
	 * 验证重复的功能代码
	 * @return
	 */
	public String checkTaskCode(){
		try{
			if(umTTask.getTaskCode().length()<=0 || umTTask.getTaskCode().length()>50){
				this.writeString("false");
				return NONE;
			}
			//查询出当前所有的功能代码
			Set<String> taskCodeSet = umTTaskService.findAllTaskCode();
			if("update".equals(opreateType)){
				taskCodeSet.remove(initTaskCode);
			}
			if(taskCodeSet.contains(umTTask.getTaskCode())){
				this.writeString("false");
			}else{
				this.writeString("true");
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("", e);
		}
		return NONE;
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
