/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.picc.um.schema.model.UmTDictionary;
import com.picc.um.schema.model.UmTDictionaryId;
import com.picc.um.schema.model.UmTUser;
import com.picc.um.schema.model.UmTUserPower;
import com.picc.um.schema.model.UmTUserPowerId;
import com.picc.um.schema.vo.UmTUserPowerDictName;
import com.picc.um.service.facade.IUmTDictionaryService;
import com.picc.um.service.facade.IUmTUserPowerService;
import com.picc.um.service.facade.IUmTUserService;

/**
 * 用户数据权限处理Action层
 * @author 姜卫洋
 */
@SuppressWarnings("serial")
public class UmTUserPowerAction extends Struts2Action{
	
	private IUmTUserPowerService umTUserPowerService;	
	
	private IUmTUserService umTUserService;						//用户信息查询接口
	
	private UmTUser umTUser;
	
	private IUmTDictionaryService umTDictionaryService;
	
	private String dictionaryName;
	
	public void setUmTUser(UmTUser umTUser) {
		this.umTUser = umTUser;
	}
	
	public UmTUser getUmTUser() {
		return umTUser;
	}
	
	public void setUmTUserService(IUmTUserService umTUserService) {
		this.umTUserService = umTUserService;
	}
	
	public IUmTUserService getUmTUserService() {
		return umTUserService;
	}
	
	public void setUmTUserPowerService(IUmTUserPowerService umTUserPowerService) {
		this.umTUserPowerService = umTUserPowerService;
	}

	public IUmTUserPowerService getUmTUserPowerService() {
		return umTUserPowerService;
	}
	
	public IUmTDictionaryService getUmTDictionaryService() {
		return umTDictionaryService;
	}

	public void setUmTDictionaryService(IUmTDictionaryService umTDictionaryService) {
		this.umTDictionaryService = umTDictionaryService;
	}
	
	public void setDictionaryName(String dictionaryName) {
		this.dictionaryName = dictionaryName;
	}
	
	public String getDictionaryName() {
		return dictionaryName;
	}

	private UmTUserPower umTUserPower;
	
	private List<UmTUserPowerDictName> umTUserPowerList;
	
	private UmTUserPowerId id;
	
	private String userCode;					//用户代码
	
	public void setUmTUserPowerList(List<UmTUserPowerDictName> umTUserPowerList) {
		this.umTUserPowerList = umTUserPowerList;
	}
	
	public List<UmTUserPowerDictName> getUmTUserPowerList() {
		return umTUserPowerList;
	}
	
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	public String getUserCode() {
		return userCode;
	}
	
	/** 操作类型 **/
	private String operateType;
	/** UmTUserPower getter/setter **/
	public UmTUserPower getUmTUserPower() {
		return this.umTUserPower;
	}
	
	public void setUmTUserPower(UmTUserPower umTUserPower) {
		this.umTUserPower = umTUserPower;
	}
	/** UmTUserPowerId getter/setter **/
	public UmTUserPowerId getId() {
		return this.id;
	}
	
	public void setId(UmTUserPowerId id) {
		this.id = id;
	}
	/** operateType getter/setter **/
	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	
	/** 主键对象 */
	private String userPowerId;
	public String getUserPowerId() {
		return this.userPowerId;
	}
	
	public void setUserPowerId(String userPowerId) {
		this.userPowerId = userPowerId;
	}


	
	/****************************Function Start*******************************/
	
	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 */
	public String prepareQuery() throws Exception {		
		
		
		return SUCCESS;
	}
	
	/**
	 * UmTUserPower查询，根据umTUserPower带过来的值为查询条件进行查询。
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
			if(umTUser!=null){
				umTUserPower = new UmTUserPower();
				umTUserPower.setUserCode(umTUser.getId().getUserCode());
			}
			umTUserPower.setComId(getUserFromSession().getComId());
			Page resultPage = umTUserPowerService.findByUmTUserPower(umTUserPower, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新UmTUserPower信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		operateType = "update";
		umTUserPower = umTUserPowerService.findUmTUserPowerByPK(id);
		UmTDictionary dictionary = null;
		if(umTUserPower!=null){
			dictionary = umTDictionaryService.findUmTDictionaryByPK(new UmTDictionaryId(umTUserPower.getDictionaryId()));
			if(dictionary!=null){
				dictionaryName = dictionary.getDitionaryName();
			}
			umTUserPower.setOperatorType("个人");
		}
		return SUCCESS;
	}
	
	/**
	 * 更新UmTUserPower信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		umTUserPower.setComCode(getUserFromSession().getComId());
		umTUserPower.setUpdaterCode(getUserFromSession().getUserCode());
		umTUserPower.setComId(getUserFromSession().getComId());
		umTUserPowerService.updateUmTUserPower(umTUserPower);
		return SUCCESS;
	}


	/**
	 * 准备增加UmTUserPower信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		
		operateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增UmTUserPower信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		umTUserPower.setComCode(getUserFromSession().getComId());
		umTUserPower.setComId(getUserFromSession().getComId());
		umTUserPower.setCreatorCode(getUserFromSession().getUserCode());
		umTUserPowerService.saveUmTUserPower(umTUserPower);
		return SUCCESS;
	}



	/**
	 * 删除UmTUserPower信息
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String delete() throws Exception {
		try{
			if(id!=null){
				
				umTUserPowerService.deleteByPK(id);
			}
		}catch(Exception e){
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
	 * 查看UmTUserPower信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		
		operateType = "view";
		umTUserPower = umTUserPowerService.findUmTUserPowerByPK(id);
		return SUCCESS;
	}
	
	
	public String addUserPower() throws Exception {
		if(userCode==null){
			throw new Exception("没有传值用户代码");
		}
		//查询需要配置数据权限的用户对象
		umTUser = umTUserService.findUmTUserByUserCode(userCode);
		//根据该用户对象查询已经配置给该用户的数据权限字典列表
		umTUserPowerList = umTUserPowerService.findUserPowerByUserCode(userCode,getUserFromSession().getComId());
		return SUCCESS;
	}
	
	@SuppressWarnings("rawtypes")
	public void rowEdit() throws Exception {
		//对表格中的数据进行自定义添加处理
		
		String deleted = getRequest().getParameter("deleted");					//获取删除数据
		String inserted = getRequest().getParameter("inserted");				//获取插入数据
		String updated = getRequest().getParameter("updated");				//获取更新数据
		
		JSONArray array = null;
		List<Map<String,String>> arrayList = null;
		Map<String,String> map = null;
		Iterator it = null;
		JSONObject obj = null;
		Iterator itkey = null;
		String key = null;
		Map<String,String> resultMap = new HashMap<String,String>();
		if(inserted!=null&&!"".equals(inserted)&&!"[]".equals(inserted)){
			//添加数据
			array = JSONArray.fromObject(inserted);
			if(array.size()>1){
				arrayList = new ArrayList<Map<String,String>>();
				it = array.iterator();
				while(it.hasNext()){
					obj = (JSONObject)it.next();
					itkey = obj.keys();
					map = new HashMap<String,String>();
					while(itkey.hasNext()){
						key = String.valueOf(itkey.next());
						map.put(key, String.valueOf(obj.get(key)));
					}
					arrayList.add(map);
				}
			}else {
				obj = (JSONObject)array.get(0);
				itkey = obj.keys();
				map = new HashMap<String,String>();
				while(itkey.hasNext()){
					key = String.valueOf(itkey.next());
					map.put(key, String.valueOf(obj.get(key)));
				}
			}
			if(arrayList!=null&&arrayList.size()>0){
				//传进来不止一条数据
				List<UmTUserPower> list = new ArrayList<UmTUserPower>();
				UmTUserPower userPower = null;
				for(Map keymap : arrayList){
					userPower = new UmTUserPower();
					userPower.setComCode(getUserFromSession().getComId());
					userPower.setUserCode(String.valueOf(keymap.get("userCode")));
					userPower.setOperatorType(String.valueOf(keymap.get("operatorType")));
					userPower.setCreatorCode(getUserFromSession().getUserCode());
					userPower.setDictionaryId(String.valueOf(keymap.get("dictionaryId")));
					userPower.setOperationSymbol(String.valueOf(keymap.get("operationSymbol")));
					userPower.setPowerValue(String.valueOf(keymap.get("powerValue")));
					userPower.setValidStatus(String.valueOf(keymap.get("validStatus")));
					userPower.setComId(getUserFromSession().getComId());
					list.add(userPower);
				}
				umTUserPowerService.insertUmTUserPowerList(list);
			}else {
				UmTUserPower userPower = new UmTUserPower();
				userPower.setComCode(getUserFromSession().getComId());
				userPower.setUserCode(String.valueOf(map.get("userCode")));
				userPower.setCreatorCode(getUserFromSession().getUserCode());
				userPower.setOperatorType(String.valueOf(map.get("operatorType")));
				userPower.setDictionaryId(String.valueOf(map.get("dictionaryId")));
				userPower.setOperationSymbol(String.valueOf(map.get("operationSymbol")));
				userPower.setPowerValue(String.valueOf(map.get("powerValue")));
				userPower.setValidStatus(String.valueOf(map.get("validStatus")));
				userPower.setComId(getUserFromSession().getComId());
				umTUserPowerService.saveUmTUserPower(userPower);
			}
			resultMap.put("status", "true");	
		}
		if(updated!=null&&!"".equals(updated)&&!"[]".equals(updated)){
			array = JSONArray.fromObject(updated);
			if(array.size()>1){
				arrayList = new ArrayList<Map<String,String>>();
				it = array.iterator();
				while(it.hasNext()){
					obj = (JSONObject)it.next();
					itkey = obj.keys();
					map = new HashMap<String,String>();
					while(itkey.hasNext()){
						key = String.valueOf(itkey.next());
						map.put(key, String.valueOf(obj.get(key)));
					}
					arrayList.add(map);
				}
			}else {
				obj = (JSONObject)array.get(0);
				itkey = obj.keys();
				map = new HashMap<String,String>();
				while(itkey.hasNext()){
					key = String.valueOf(itkey.next());
					map.put(key, String.valueOf(obj.get(key)));
				}
			}
			if(arrayList!=null&&arrayList.size()>0){
				//传进来不止一条数据
				List<UmTUserPower> list = new ArrayList<UmTUserPower>();
				UmTUserPower userPower = null;
				UmTUserPowerId  id = null;
				String userpowerId = null;
				for(Map keymap : arrayList){
					id = new UmTUserPowerId();
					userpowerId =  String.valueOf(keymap.get("userPowerId"));
					id.setUserPowerId(userpowerId);
					userPower = new UmTUserPower();
					userPower.setComCode(getUserFromSession().getComId());
					userPower.setUserCode(String.valueOf(keymap.get("userCode")));
					userPower.setOperatorType(String.valueOf(keymap.get("operatorType")));
					userPower.setUpdaterCode(getUserFromSession().getUserCode());
					userPower.setId(id);
					userPower.setDictionaryId(String.valueOf(keymap.get("dictionaryId")));
					userPower.setOperationSymbol(String.valueOf(keymap.get("operationSymbol")));
					userPower.setPowerValue(String.valueOf(keymap.get("powerValue")));
					userPower.setValidStatus(String.valueOf(keymap.get("validStatus")));
					userPower.setComId(getUserFromSession().getComId());
					list.add(userPower);
				}
				umTUserPowerService.updateUmTUserPowerList(list);
			}else {
				UmTUserPower userPower = new UmTUserPower();
				UmTUserPowerId  id = new UmTUserPowerId();
				String userpowerId = String.valueOf(map.get("userPowerId"));
				id.setUserPowerId(userpowerId);
				userPower.setId(id);
				userPower.setComCode(getUserFromSession().getComId());
				userPower.setUserCode(String.valueOf(map.get("userCode")));
				userPower.setOperatorType(String.valueOf(map.get("operatorType")));
				userPower.setUpdaterCode(getUserFromSession().getUserCode());
				userPower.setDictionaryId(String.valueOf(map.get("dictionaryId")));
				userPower.setOperationSymbol(String.valueOf(map.get("operationSymbol")));
				userPower.setPowerValue(String.valueOf(map.get("powerValue")));
				userPower.setValidStatus(String.valueOf(map.get("validStatus")));
				userPower.setComId(getUserFromSession().getComId());
				umTUserPowerService.updateUmTUserPower(userPower);
			}
			resultMap.put("status", "true");	
		}
		if(deleted!=null&&!"".equals(deleted)&&!"[]".equals(deleted)){
			array = JSONArray.fromObject(deleted);
			if(array.size()>1){
				arrayList = new ArrayList<Map<String,String>>();
				it = array.iterator();
				while(it.hasNext()){
					obj = (JSONObject)it.next();
					itkey = obj.keys();
					map = new HashMap<String,String>();
					while(itkey.hasNext()){
						key = String.valueOf(itkey.next());
						map.put(key, String.valueOf(obj.get(key)));
					}
					arrayList.add(map);
				}
			}else {
				obj = (JSONObject)array.get(0);
				itkey = obj.keys();
				map = new HashMap<String,String>();
				while(itkey.hasNext()){
					key = String.valueOf(itkey.next());
					map.put(key, String.valueOf(obj.get(key)));
				}
			}
			if(arrayList!=null&&arrayList.size()>0){
				//传进来不止一条数据
				List<UmTUserPower> list = new ArrayList<UmTUserPower>();
				UmTUserPowerId  id = null;
				String userpowerId = null;
				
				for(Map keymap : arrayList){
					id = new UmTUserPowerId();
					userpowerId =  String.valueOf(keymap.get("userPowerId"));
					id.setUserPowerId(userpowerId);
					if(umTUserPowerService.findUmTUserPowerByPK(id).getRolePowerId()!=null){
						resultMap.put("status","false");
						resultMap.put("msg","用户权限"+userpowerId+"为角色附加的数据权限,无法自行删除");
						break;
					}else {
						list.add(new UmTUserPower(id));
					}
				}
				if(list.size()==arrayList.size()){
					umTUserPowerService.deleteUmTUserPowerList(list);
					resultMap.put("status", "true");
				}
			}else {
				UmTUserPowerId  id = new UmTUserPowerId();
				String userpowerId =  String.valueOf(map.get("userPowerId"));
				id.setUserPowerId(userpowerId);
				if(umTUserPowerService.findUmTUserPowerByPK(id).getRolePowerId()!=null){
					resultMap.put("status","false");
					resultMap.put("msg","用户权限"+userpowerId+"为角色附加的数据权限,无法自行删除");
				}else {
					umTUserPowerService.deleteUmTUserPower(new UmTUserPower(id));
					resultMap.put("status", "true");
				}
			}
		}
		if(resultMap.size()==0){
			resultMap.put("status", "false");
			resultMap.put("msg", "没有实际数据提交,请提交修改数据");
		}
		
		super.writeEasyUiData(JSONObject.fromObject(resultMap));
	}
	
}
