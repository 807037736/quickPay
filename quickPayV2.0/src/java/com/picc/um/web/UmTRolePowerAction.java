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
import com.picc.um.schema.model.UmTRole;
import com.picc.um.schema.model.UmTRoleId;
import com.picc.um.schema.model.UmTRolePower;
import com.picc.um.schema.model.UmTRolePowerId;
import com.picc.um.schema.vo.UmTRolePowerDictionary;
import com.picc.um.service.facade.IUmTDictionaryService;
import com.picc.um.service.facade.IUmTRolePowerService;
import com.picc.um.service.facade.IUmTRoleService;

/**
 * 角色附加数据权限处理Action
 * @author 姜卫洋
 *
 */
@SuppressWarnings("serial")
public class UmTRolePowerAction extends Struts2Action{
	
	
	
	private IUmTRolePowerService umTRolePowerService;	
	
	private IUmTRoleService umTRoleService;
	
	public void setUmTRoleService(IUmTRoleService umTRoleService) {
		this.umTRoleService = umTRoleService;
	}
	
	public IUmTRoleService getUmTRoleService() {
		return umTRoleService;
	}
	
	public void setUmTRolePowerService(IUmTRolePowerService umTRolePowerService) {
		this.umTRolePowerService = umTRolePowerService;
	}

	public IUmTRolePowerService getUmTRolePowerService() {
		return umTRolePowerService;
	}
	
	private UmTRolePower umTRolePower;
	
	private UmTRolePowerId id;
	
	private UmTRole umTRole;
	
	private List<UmTRolePower> umTRolePowerList;
	
	private UmTDictionary umTDictionary;								//数据权限字典对象
	
	private IUmTDictionaryService umTDictionaryService;			//数据权限字典查询Service
	
	private List<UmTRolePowerDictionary> rolePowerList;
	
	/** 操作类型 **/
	private String operateType;
	/** UmTRolePower getter/setter **/
	public UmTRolePower getUmTRolePower() {
		return this.umTRolePower;
	}
	
	public void setUmTRolePower(UmTRolePower umTRolePower) {
		this.umTRolePower = umTRolePower;
	}
	/** UmTRolePowerId getter/setter **/
	public UmTRolePowerId getId() {
		return this.id;
	}
	
	public void setId(UmTRolePowerId id) {
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
	private String rpId;
	public String getRpId() {
		return this.rpId;
	}
	
	public void setRpId(String rpId) {
		this.rpId = rpId;
	}
	
	public void setUmTRole(UmTRole umTRole) {
		this.umTRole = umTRole;
	}
	
	public UmTRole getUmTRole() {
		return umTRole;
	}
	
	public void setUmTRolePowerList(List<UmTRolePower> umTRolePowerList) {
		this.umTRolePowerList = umTRolePowerList;
	}
	
	public List<UmTRolePower> getUmTRolePowerList() {
		return umTRolePowerList;
	}
	
	public void setUmTDictionary(UmTDictionary umTDictionary) {
		this.umTDictionary = umTDictionary;
	}
	
	public UmTDictionary getUmTDictionary() {
		return umTDictionary;
	}
	
	public void setUmTDictionaryService(
			IUmTDictionaryService umTDictionaryService) {
		this.umTDictionaryService = umTDictionaryService;
	}
	
	public IUmTDictionaryService getUmTDictionaryService() {
		return umTDictionaryService;
	}
	
	public void setRolePowerList(List<UmTRolePowerDictionary> rolePowerList) {
		this.rolePowerList = rolePowerList;
	}
	
	public List<UmTRolePowerDictionary> getRolePowerList() {
		return rolePowerList;
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
	 * UmTRolePower查询，根据umTRolePower带过来的值为查询条件进行查询。
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
			Page resultPage = umTRolePowerService.findByUmTRolePower(umTRolePower, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新UmTRolePower信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		operateType = "update";
		umTRolePower = umTRolePowerService.findUmTRolePowerByPK(id);
		umTRole = umTRoleService.findUmTRoleByPK(new UmTRoleId(umTRolePower.getRoleId()));				//查询对应角色的信息
		return SUCCESS;
	}
	
	/**
	 * 更新UmTRolePower信息
	 * 
	 * @return
	 */
	public String update() throws Exception {
		umTRolePower.setUpdaterCode(getUserFromSession().getUserCode());
		umTRolePowerService.updateUmTRolePower(umTRolePower);
		return SUCCESS;
	}
	
	/**
	 * 准备增加UmTRolePower信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		operateType = "add";
		if(umTRolePower!=null){
			String roleId = umTRolePower.getRoleId();						//获取提交角色ID代码
			if(roleId!=null&&!"".equals(roleId)){
				umTRolePowerList = this.umTRolePowerService.findUmTRolePowerByRoleID(roleId, getUserFromSession().getComId());					//获取当前roleId中所对应的附加数据权限
				umTRole = umTRoleService.findUmTRoleByPK(new UmTRoleId(roleId));				//查询对应角色的信息
			}
		}
		if(umTRolePower==null){
			umTRolePower = new UmTRolePower();
		}else {
			UmTRolePowerDictionary  rolePowerDict = null;
			if(rolePowerList==null){
				rolePowerList = new ArrayList<UmTRolePowerDictionary>();
			}
			for(UmTRolePower umTRolePower : umTRolePowerList){
				rolePowerDict = new UmTRolePowerDictionary(umTRolePower);
				if(umTRolePower.getDictionaryId()!=null&&!"".equals(umTRolePower.getDictionaryId())){
					umTDictionary = umTDictionaryService.findUmTDictionaryByPK(new UmTDictionaryId(umTRolePower.getDictionaryId()));
					rolePowerDict.setDictionaryName(umTDictionary.getDitionaryName());
					rolePowerList.add(rolePowerDict);
				}
			}
		}
		umTRolePower.setComId(getUserFromSession().getComCode());
		return SUCCESS;
	}
	
	/**
	 * 新增UmTRolePower信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		UmTRolePower rolePower = umTRolePowerService.findUmTRolePowerByRoleAndDict(umTRolePower.getRoleId(), umTRolePower.getDictionaryId());
		if(rolePower==null){
			//一个角色与字典ID只能关联一次
			umTRolePower.setCreatorCode(getUserFromSession().getUserCode());
			umTRolePower.setComId(getUserFromSession().getComId());
			umTRolePowerService.saveUmTRolePower(umTRolePower);
			return SUCCESS;
		}else {
			this.writeString("<script>alert('已经存在该角色与该字典的数据权限关联');window.close();</script>");
			return NONE;
		}
	}



	/**
	 * 删除UmTRolePower信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				umTRolePowerService.deleteByPK(id);
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
	 * 查看UmTRolePower信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		operateType = "view";
		umTRolePower = umTRolePowerService.findUmTRolePowerByPK(id);
		return SUCCESS;
	}
	
	
	
	@SuppressWarnings("rawtypes")
	public void rowEdit() throws Exception {
		//对表格中的数据进行自定义添加处理
		Map<String,String> resultMap = new HashMap<String,String>();

		try{
		
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
					List<UmTRolePower> list = new ArrayList<UmTRolePower>();
					UmTRolePower umTRolePower = null;
					for(Map keymap : arrayList){
						umTRolePower = new UmTRolePower();
						umTRolePower.setComId(getUserFromSession().getComId());
						umTRolePower.setCreatorCode(getUserFromSession().getUserCode());
						umTRolePower.setRoleId(String.valueOf(keymap.get("roleId")));
						umTRolePower.setDictionaryId(String.valueOf(keymap.get("dictionaryId")));
						umTRolePower.setPowerValue(String.valueOf(keymap.get("powerValue")));
						umTRolePower.setValidStatus("1");
						list.add(umTRolePower);
					}
					umTRolePowerService.addUmTRolePowerList(list);
				}else {
					UmTRolePower umTRolePower = new UmTRolePower();
					umTRolePower = new UmTRolePower();
					umTRolePower.setComId(getUserFromSession().getComId());
					umTRolePower.setCreatorCode(getUserFromSession().getUserCode());
					umTRolePower.setRoleId(String.valueOf(map.get("roleId")));
					umTRolePower.setDictionaryId(String.valueOf(map.get("dictionaryId")));
					umTRolePower.setPowerValue(String.valueOf(map.get("powerValue")));
					umTRolePower.setValidStatus("1");
					umTRolePowerService.saveUmTRolePower(umTRolePower);
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
					List<UmTRolePower> list = new ArrayList<UmTRolePower>();
					UmTRolePower umTRolePower = null;
					UmTRolePowerId  id = null;
					for(Map keymap : arrayList){
						umTRolePower = new UmTRolePower();
						id = new UmTRolePowerId(String.valueOf(map.get("rpId")));
						umTRolePower.setId(id);
						umTRolePower.setComId(getUserFromSession().getComId());
						umTRolePower.setCreatorCode(getUserFromSession().getUserCode());
						umTRolePower.setRoleId(String.valueOf(keymap.get("roleId")));
						umTRolePower.setDictionaryId(String.valueOf(keymap.get("dictionaryId")));
						umTRolePower.setPowerValue(String.valueOf(keymap.get("powerValue")));
						umTRolePower.setUpdaterCode(getUserFromSession().getUserCode());
						umTRolePower.setValidStatus("1");
						list.add(umTRolePower);
					}
					umTRolePowerService.updateUmTRolePowerList(list);
				}else {
					UmTRolePower umTRolePower = new UmTRolePower();
					UmTRolePowerId  id = new UmTRolePowerId(String.valueOf(map.get("rpId")));
					umTRolePower.setId(id);
					umTRolePower.setComId(getUserFromSession().getComId());
					umTRolePower.setCreatorCode(getUserFromSession().getUserCode());
					umTRolePower.setRoleId(String.valueOf(map.get("roleId")));
					umTRolePower.setDictionaryId(String.valueOf(map.get("dictionaryId")));
					umTRolePower.setPowerValue(String.valueOf(map.get("powerValue")));
					umTRolePower.setUpdaterCode(getUserFromSession().getUserCode());
					umTRolePower.setValidStatus("1");
					umTRolePowerService.updateUmTRolePower(umTRolePower);
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
					List<UmTRolePower> list = new ArrayList<UmTRolePower>();
					UmTRolePowerId  id = null;
					for(Map keymap : arrayList){
						id = new UmTRolePowerId(String.valueOf(keymap.get("rpId")));
						list.add(new UmTRolePower(id));
					}
					if(list.size()==arrayList.size()){
						umTRolePowerService.deleteUmTRolePowerList(list);
						resultMap.put("status", "true");
					}
				}else {
					UmTRolePowerId  id = new UmTRolePowerId(String.valueOf(map.get("rpId")));
					umTRolePowerService.deleteByPK(id);
					resultMap.put("status", "true");
				}
			}
			if(resultMap.size()==0){
				resultMap.put("status", "false");
				resultMap.put("msg", "没有实际数据提交,请提交修改数据");
			}
		}catch(Exception ex){
			resultMap.put("status", "false");
			resultMap.put("errormsg",ex.getMessage());
		}
		super.writeEasyUiData(JSONObject.fromObject(resultMap));
	}
	
	
	
	public static final String SBCchange(String QJstr)
	 {
	     String outStr="";
	     String Tstr="";
	     byte[] b=null;

	     for(int i=0;i<QJstr.length();i++)
	     {     
	      try
	      {
	       Tstr=QJstr.substring(i,i+1);
	       b=Tstr.getBytes("unicode");
	      }
	      catch(java.io.UnsupportedEncodingException e)
	      {
	       e.printStackTrace();
	      }     
	   
	      if (b[3]==-1)
	      {
	       b[2]=(byte)(b[2]+32);
	       b[3]=0;      
	        
	       try
	       {       
	        outStr=outStr+new String(b,"unicode");
	       }
	       catch(java.io.UnsupportedEncodingException e)
	       {
	        e.printStackTrace();
	       }      
	      }else outStr=outStr+Tstr;
	     }
	    
	     return outStr; 
	  }

}
