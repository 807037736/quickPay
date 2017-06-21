/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.portal.web;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.web.Struts2Action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.picc.portal.schema.model.WfTPortletclassfy;
import com.picc.portal.schema.model.WfTPortletclassfyId;
import com.picc.portal.schema.model.WfTPortletinfo;
import com.picc.portal.schema.model.WfTPortletinfoId;
import com.picc.portal.service.facade.IWfTPortletclassfyService;
import com.picc.portal.service.facade.IWfTPortletinfoService;
import com.picc.qp.schema.model.QpTCOMInform;
import com.picc.qp.service.facade.IQpTCOMInformService;
import com.picc.um.service.facade.IUmTMethodTaskService;


@SuppressWarnings("serial")
public class WfTPortletclassfyAction extends Struts2Action{
	
	private IWfTPortletinfoService wfTPortletinfoService;	
	
	private IWfTPortletclassfyService wfTPortletclassfyService;	
	
	private IUmTMethodTaskService umTMethodTaskService;
	
	private IQpTCOMInformService qpTCOMInformService;
	
	private QpTCOMInform qpTCOMInform;
	
	private String serverCode;
	
	private String serverName;

/* 系统公告  */
	private int informId;
	
	private String title;
	
	private String content;
	
	private String state;
	
	private String type;
	
	private String endTime;
	
	List<String> contents;
	
	
	
	public List<String> getContents() {
		return contents;
	}

	public void setContents(List<String> contents) {
		this.contents = contents;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getServerCode() {
		return serverCode;
	}

	public void setServerCode(String serverCode) {
		this.serverCode = serverCode;
	}

	public IUmTMethodTaskService getUmTMethodTaskService() {
		return umTMethodTaskService;
	}

	public void setUmTMethodTaskService(IUmTMethodTaskService umTMethodTaskService) {
		this.umTMethodTaskService = umTMethodTaskService;
	}

	public void setWfTPortletclassfyService(IWfTPortletclassfyService wfTPortletclassfyService) {
		this.wfTPortletclassfyService = wfTPortletclassfyService;
	}

	public IWfTPortletclassfyService getWfTPortletclassfyService() {
		return wfTPortletclassfyService;
	}
	
	public IQpTCOMInformService getQpTCOMInformService() {
		return qpTCOMInformService;
	}

	public void setQpTCOMInformService(IQpTCOMInformService qpTCOMInformService) {
		this.qpTCOMInformService = qpTCOMInformService;
	}
	
	public QpTCOMInform getQpTCOMInform() {
		return qpTCOMInform;
	}

	public void setQpTCOMInform(QpTCOMInform qpTCOMInform) {
		this.qpTCOMInform = qpTCOMInform;
	}
	public int getInformId() {
		return informId;
	}

	public void setInformId(int informId) {
		this.informId = informId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}



	private WfTPortletclassfy wfTPortletclassfy;
	
	private WfTPortletinfo wfTPortletinfo;
	
	private WfTPortletinfoId wfTPortletinfoId;
	
	public WfTPortletinfoId getWfTPortletinfoId() {
		return wfTPortletinfoId;
	}

	public void setWfTPortletinfoId(WfTPortletinfoId wfTPortletinfoId) {
		this.wfTPortletinfoId = wfTPortletinfoId;
	}

	public WfTPortletinfo getWfTPortletinfo() {
		return wfTPortletinfo;
	}

	public void setWfTPortletinfo(WfTPortletinfo wfTPortletinfo) {
		this.wfTPortletinfo = wfTPortletinfo;
	}

	private WfTPortletclassfyId id;
	
	private JSONObject result;
	
	private String portalTopResult;
	
	public String getPortalTopResult() {
		return portalTopResult;
	}

	public void setPortalTopResult(String portalTopResult) {
		this.portalTopResult = portalTopResult;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}
	
	private String portletName;//模块名

	
	public String getPortletName() {
		return portletName;
	}

	public void setPortletName(String portletName) {
		this.portletName = portletName;
	}
	
	private JSONArray jsonArr;
	
	public JSONArray getJsonArr() {
		return jsonArr;
	}

	public void setJsonArr(JSONArray jsonArr) {
		this.jsonArr = jsonArr;
	}

	private String url;//actionURL
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	private String layout;

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}
	
	public String jsonResult;
	
	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}
	
	private  List<WfTPortletclassfy> wfTPortletclassfyList;

	public List<WfTPortletclassfy> getWfTPortletclassfyList() {
		return wfTPortletclassfyList;
	}

	public void setWfTPortletclassfyList(
			List<WfTPortletclassfy> wfTPortletclassfyList) {
		this.wfTPortletclassfyList = wfTPortletclassfyList;
	}
	
	private  List<String> wfTPortletinfoList;

	public List<String> getWfTPortletinfoList() {
		return wfTPortletinfoList;
	}

	public void setWfTPortletinfoList(List<String> wfTPortletinfoList) {
		this.wfTPortletinfoList = wfTPortletinfoList;
	}

	public IWfTPortletinfoService getWfTPortletinfoService() {
		return wfTPortletinfoService;
	}

	public void setWfTPortletinfoService(
			IWfTPortletinfoService wfTPortletinfoService) {
		this.wfTPortletinfoService = wfTPortletinfoService;
	}

	/** 操作类型 **/
	private String opreateType;
	/** WfTPortletclassfy getter/setter **/
	public WfTPortletclassfy getWfTPortletclassfy() {
		return this.wfTPortletclassfy;
	}
	
	public void setWfTPortletclassfy(WfTPortletclassfy wfTPortletclassfy) {
		this.wfTPortletclassfy = wfTPortletclassfy;
	}
	/** WfTPortletclassfyId getter/setter **/
	public WfTPortletclassfyId getId() {
		return this.id;
	}
	
	public void setId(WfTPortletclassfyId id) {
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
	private String portletid;
	public String getPortletid() {
		return this.portletid;
	}
	
	public void setPortletid(String portletid) {
		this.portletid = portletid;
	}

	
	/****************************Function Start*******************************/
	
	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * @return
	 */
	public String prepareQuery() throws Exception {		
		
		
		return SUCCESS;
	}
	
	/**
	 * WfTPortletclassfy查询，根据wfTPortletclassfy带过来的值为查询条件进行查询。
	 * @author ding
	 * date 2013-8-26
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
			QueryRule queryRule = QueryRule.getInstance();
			
			if (wfTPortletclassfy.getPortletname() != null
					&& !"".equals(wfTPortletclassfy.getPortletname())) {
				queryRule.addLike("portletname",
						"%" + wfTPortletclassfy.getPortletname() + "%");
			}// 增加模块名称的查询条件
			
			if (wfTPortletclassfy.getActionurl() != null
					&& !"".equals(wfTPortletclassfy.getActionurl())) {
				queryRule.addLike("actionurl",
						"%" + wfTPortletclassfy.getActionurl() + "%");
			}// 增加模块地址的查询条件
			
			if (wfTPortletclassfy.getComcode() != null
					&& !"".equals(wfTPortletclassfy.getComcode())) {
				queryRule.addLike("comcode",
						"%" + wfTPortletclassfy.getComcode() + "%");
			}// 增加机构代码的查询条件
			
			if (!wfTPortletclassfy.getValidstatus().equals(BigDecimal.valueOf(2))||wfTPortletclassfy.getValidstatus()==null) {
				queryRule.addEqual("validstatus", wfTPortletclassfy.getValidstatus());
			}// 增加有效性的查询条件
			
			Page resultPage = wfTPortletclassfyService.findWfTPortletclassfy(queryRule, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeJSONMsg(e.getMessage());
		}
		return NONE;
	}	


	/**
	 * 准备更新WfTPortletclassfy信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		
		opreateType = "update";
		wfTPortletclassfy = wfTPortletclassfyService.findWfTPortletclassfyByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新WfTPortletclassfy信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		String userCode = getUserFromSession().getUserCode();
		umTMethodTaskService.saveUmTMethodTask4portal(wfTPortletclassfy, userCode);
		wfTPortletclassfyService.updateWfTPortletclassfy(wfTPortletclassfy);
		return SUCCESS;
	}

	/**
	 * 准备增加WfTPortletclassfy信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		
		opreateType = "add";
		return SUCCESS;
	}

	 /** 
	  * 准备增加WfTPortletclassfy信息,json获取添加显示模块的下拉列表
	 * @author ding
	 * date 2013-08-21
	 * @return
	 */
	public String userAdd() throws Exception {
		String userCode = getUserFromSession().getUserCode();
		String comId = getUserFromSession().getComId();
		String comCode = getUserFromSession().getComCode();
		WfTPortletclassfyId wfTPortletclassfyId = new WfTPortletclassfyId();
		wfTPortletinfoList = wfTPortletclassfyService.findWfTPortletinfoByUserCode(userCode,comId,comCode,serverCode);
		if(wfTPortletinfoList!=null){
			//List<WfTPortletclassfy> wfTPortletclassfyList = wfTPortletclassfyService.findPortletClassfyAll();
			List<WfTPortletclassfy> wfTPortletclassfyList = new ArrayList<WfTPortletclassfy>();
			for(int i=0;i<wfTPortletinfoList.size();i++){	
				String portletid = wfTPortletinfoList.get(i);
				wfTPortletclassfyId.setPortletid(portletid);
				WfTPortletclassfy wfTPortletclassfy = wfTPortletclassfyService.findWfTPortletclassfyByPK(wfTPortletclassfyId);
				wfTPortletclassfyList.add(wfTPortletclassfy);
				//wfTPortletclassfyId.setPortletid(wfTPortletinfoList.get(i).getPortletid());
				//wfTPortletclassfyList.get(i).setId(wfTPortletclassfyId);
			}
			
			jsonArr=new JSONArray();
			for (int i=0 ; i<wfTPortletclassfyList.size() ; i++){	
				wfTPortletclassfy = wfTPortletclassfyList.get(i);
				String id = wfTPortletclassfy.getId().getPortletid();
				String name = wfTPortletclassfy.getPortletname();
				String url = wfTPortletclassfy.getActionurl();
				JSONObject json = new JSONObject();
				json.put("id", id);
				json.put("title", name);
				json.put("url", url);
				jsonArr.add(json);
			}
		}
//		result=jsonArr.toJSONObject(jsonArr);
//		this.writeEasyUiData(result);
		return SUCCESS;
	}
	
	/**
	 * 新增WfTPortletclassfy信息
	 * @author ding
	 * date 2013-8-27
	 * @return
	 */
	public String add() throws Exception {
		
		String userCode = getUserFromSession().getUserCode();
		String comId = getUserFromSession().getComId();
		String comCode = getUserFromSession().getComCode();
		wfTPortletclassfyService.saveWfTPortletclassfy(wfTPortletclassfy, comId, comCode);
		umTMethodTaskService.saveUmTMethodTask4portal(wfTPortletclassfy, userCode);
		return SUCCESS;
	}



	/**
	 * 删除WfTPortletclassfy信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				String userCode = getUserFromSession().getUserCode();
				QueryRule rule = QueryRule.getInstance();
				rule.addEqual("portletid", id.getPortletid());
				Page page = wfTPortletinfoService.find(rule, 1, Integer.MAX_VALUE);
				//Page page = wfTPortletinfoService.findByWfTPortletinfo(wfTPortletinfo, 1, 20);
				List<WfTPortletinfo> wfTPortletinfoList = page.getResult();
				
				if(wfTPortletinfoList.size()>0){
					for(int i =0 ;i<wfTPortletinfoList.size();i++){
						wfTPortletinfo = new WfTPortletinfo();
						wfTPortletinfo = wfTPortletinfoList.get(i);
						String wfTPortletinfoid = wfTPortletinfo.getId().getId();
						
						wfTPortletinfoId = new WfTPortletinfoId();
						wfTPortletinfoId.setId(wfTPortletinfoid);
						wfTPortletinfoService.deleteByPK(wfTPortletinfoId);
					}
				}
				wfTPortletclassfy  = new WfTPortletclassfy();
				wfTPortletclassfy = wfTPortletclassfyService.findWfTPortletclassfyByPK(id);
				wfTPortletclassfy.setValidstatus(BigDecimal.valueOf(0));
			    umTMethodTaskService.saveUmTMethodTask4portal(wfTPortletclassfy, userCode);							
				wfTPortletclassfyService.deleteByPK(id);
				this.writeJSONMsg(SUCCESS);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("", e);
			this.writeJSONMsg(e.getMessage());
		}
		return NONE;
	}



	/**
	 * 查看WfTPortletclassfy信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		
		opreateType = "view";
		wfTPortletclassfy = wfTPortletclassfyService.findWfTPortletclassfyByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 在网页上展示portlet
	 * author zhou
	 * 
	 * 在页面展示公告
	 * author CKY
	 */
	public String display() throws Exception{
		String userCode = getUserFromSession().getUserCode();
		String comId = getUserFromSession().getComId();
		String comCode = getUserFromSession().getComCode();
		serverName=new String(serverName.getBytes("ISO-8859-1"),"UTF-8");
		/*判断状态为3的公告结束时间是否已过，如是，改为状态2*/
		List<QpTCOMInform> qpTCOMInforms = qpTCOMInformService.findQpTCOMInformByState("3");
		List<QpTCOMInform> informs = new ArrayList<QpTCOMInform>();
		
		if(null != qpTCOMInforms){
			for(QpTCOMInform inform : qpTCOMInforms){
				if(null == inform.getStartTime() || "".equals(inform.getStartTime())){
					
				}else if(qpTCOMInformService.isTimeOut(new Date(),inform.getEndTime())){
					inform.setState("2");
					qpTCOMInformService.updateQpTCOMInform(inform);
				}else{
					informs.add(inform);
				}
			}
		}
		/*状态为3的公告开始时间未到，选择忽略*/
		if(null != informs ){
			for (int i = 0; i < informs.size(); i++) {
				QpTCOMInform ff = informs.get(i);
				if(!qpTCOMInformService.isTimeOut(new Date(), ff.getStartTime())){
					informs.remove(i);
				}
			}
		}
		
		/*如有预发布公告满足上面条件，则查找状态为1的公告改为2，把满足上述条件且离当前时间最近的公告状态改为1，其他改为2。*/
		if(null != informs && !informs.isEmpty() && informs.size() >0){
			QpTCOMInform preparePublishInform = qpTCOMInformService.getNearTimeInform(informs).get(0);
			List<QpTCOMInform> outInforms = qpTCOMInformService.findQpTCOMInformByState("1");
			
			SimpleDateFormat toDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date preparePublishInformStartTime = toDate.parse(preparePublishInform.getStartTime());
			
			if(informs.size()>1){
				for (int i = 1; i < informs.size(); i++) {
					QpTCOMInform outInform = informs.get(i);
					outInform.setState("2");
					qpTCOMInformService.updateQpTCOMInform(outInform);
				}
			}
			
			if(null != outInforms && !outInforms.isEmpty() && outInforms.size() >0){
				for (int i = 0; i < outInforms.size(); i++) {
					QpTCOMInform outQpTCOMInform = outInforms.get(i);
					if(qpTCOMInformService.isTimeOut(preparePublishInformStartTime, outQpTCOMInform.getStartTime())){
						outQpTCOMInform.setState("2");
						qpTCOMInformService.updateQpTCOMInform(outQpTCOMInform);
						preparePublishInform.setState("1");
						qpTCOMInformService.updateQpTCOMInform(preparePublishInform);
					}else{
						preparePublishInform.setState("2");
						qpTCOMInformService.updateQpTCOMInform(preparePublishInform);
					}
				}
			}else{
				preparePublishInform.setState("1");
				qpTCOMInformService.updateQpTCOMInform(preparePublishInform);
			}
		}
		
		/*查找状态为1的公告，如果已过时则改变其状态为2，如未过时则返回其公告*/
		qpTCOMInforms = qpTCOMInformService.findQpTCOMInformByState("1");
		if(null != qpTCOMInforms && !qpTCOMInforms.isEmpty() && qpTCOMInforms.size() > 0){
			qpTCOMInform = qpTCOMInforms.get(0);
		}
		if(null == qpTCOMInform || "".equals(qpTCOMInform)){
			
		}else if(qpTCOMInformService.isTimeOut(new Date(),qpTCOMInform.getEndTime())){
			qpTCOMInform.setState("2");
			qpTCOMInformService.updateQpTCOMInform(qpTCOMInform);
			qpTCOMInform = null;
		}else{
			title = qpTCOMInform.getTitle();
			
			/* 字符串换行分组  */
			content = qpTCOMInform.getContent();
		    contents = new ArrayList<String>();
			String[] cs = new String[20];
			cs = content.split("\r\n");
			for (int i = 0; i < cs.length; i++){ 
				contents.add(cs[i]);
		    }
			endTime = qpTCOMInform.getEndTime();
			state = qpTCOMInform.getState();
			type = qpTCOMInform.getType();
		}
		
		result=wfTPortletclassfyService.getPortletInfo(userCode,comId,comCode,serverCode);
		portalTopResult=wfTPortletclassfyService.findPortalTop(userCode, comCode);
		return SUCCESS;
	}
	
	/** 
	 * 添加Portlet模块
	 * @author ding
	 * @return
	 * @throws Exception
	 */
	public String savePortletclassfy() throws Exception{
		
		wfTPortletclassfyService.savePortletclassfy(portletName,url);
		return NONE;
		
	}
	
	/**
	 * 调用WfTPortletclassfy的list
	 * @author ding
	 * @return
	 * date:2013-8-12
	 */
	public String findPortletClassfyAll() {
		wfTPortletclassfyService.findPortletClassfyAll();
		return NONE;
		
	}

//	/**
//	 * json获取添加显示模块的下拉列表
//	 * @author ding
//	 */
//	public String getwfTPortletclassfyList(){
//		
//		WfTPortletclassfy wfTPortletclassfy = new WfTPortletclassfy();
//		wfTPortletclassfyList = wfTPortletclassfyService.findPortletClassfyAll();
//		for (int i=0 ; i<wfTPortletclassfyList.size() ; i++){	
//			wfTPortletclassfy = wfTPortletclassfyList.get(i);
//			String id = wfTPortletclassfy.getId().getPortletid();
//			String name = wfTPortletclassfy.getPortletname();
//			JSONObject json = new JSONObject();
//			json.put("id", id);
//			json.put("text", name);
//			jsonArr.add(json);		
//		}
//		this.writeEasyUiData(jsonArr);
//		return SUCCESS;
//	}
	
	public String messageList(){
		return SUCCESS;
	}
	public String reservationTask(){
		return SUCCESS;
	}
	public String taskDealingToday(){
		return SUCCESS;
	}
	public String taskToDo(){
		return SUCCESS;
	}
	public String toClaimTask(){
		return SUCCESS;
	}
	
	
}
