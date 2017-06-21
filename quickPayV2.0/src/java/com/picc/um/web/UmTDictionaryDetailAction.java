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
import com.picc.um.schema.model.UmTDictionaryDetail;
import com.picc.um.schema.model.UmTDictionaryDetailId;
import com.picc.um.schema.model.UmTDictionaryId;
import com.picc.um.service.facade.IUmTDictionaryDetailService;
import com.picc.um.service.facade.IUmTDictionaryService;

/**
 * 数据字典明细Action处理层
 * @author 姜卫洋
 */
@SuppressWarnings("serial")
public class UmTDictionaryDetailAction extends Struts2Action {

	private IUmTDictionaryDetailService umTDictionaryDetailService;
	private IUmTDictionaryService umTDictionaryService;

	public void setUmTDictionaryDetailService(
			IUmTDictionaryDetailService umTDictionaryDetailService) {
		this.umTDictionaryDetailService = umTDictionaryDetailService;
	}

	public IUmTDictionaryDetailService getUmTDictionaryDetailService() {
		return umTDictionaryDetailService;
	}

	public void setUmTDictionaryService(
			IUmTDictionaryService umTDictionaryService) {
		this.umTDictionaryService = umTDictionaryService;
	}

	public IUmTDictionaryService getUmTDictionaryService() {
		return umTDictionaryService;
	}

	private UmTDictionaryDetail umTDictionaryDetail;

	private UmTDictionaryDetailId id;

	private UmTDictionaryId id2;

	private UmTDictionary umTDictionary;

	private List<UmTDictionaryDetail> umTDictionaryDetailList;
	
	private String dictionaryName;

	public void setUmTDictionaryDetailList(
			List<UmTDictionaryDetail> umTDictionaryDetailList) {
		this.umTDictionaryDetailList = umTDictionaryDetailList;
	}

	public List<UmTDictionaryDetail> getUmTDictionaryDetailList() {
		return umTDictionaryDetailList;
	}

	/** 操作类型 **/
	private String operateType;

	/** UmTDictionaryDetail getter/setter **/
	public UmTDictionaryDetail getUmTDictionaryDetail() {
		return this.umTDictionaryDetail;
	}

	public void setUmTDictionaryDetail(UmTDictionaryDetail umTDictionaryDetail) {
		this.umTDictionaryDetail = umTDictionaryDetail;
	}

	/** UmTDictionaryDetailId getter/setter **/
	public UmTDictionaryDetailId getId() {
		return this.id;
	}

	public void setId(UmTDictionaryDetailId id) {
		this.id = id;
	}

	/** operateType getter/setter **/
	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public void setId2(UmTDictionaryId id2) {
		this.id2 = id2;
	}

	public UmTDictionaryId getId2() {
		return id2;
	}

	public void setUmTDictionary(UmTDictionary umTDictionary) {
		this.umTDictionary = umTDictionary;
	}

	public UmTDictionary getUmTDictionary() {
		return umTDictionary;
	}

	/** 主键对象 */
	private String dictionaryDetailId;

	public String getDictionaryDetailId() {
		return this.dictionaryDetailId;
	}

	public void setDictionaryDetailId(String dictionaryDetailId) {
		this.dictionaryDetailId = dictionaryDetailId;
	}
	
	public void setDictionaryName(String dictionaryName) {
		this.dictionaryName = dictionaryName;
	}
	
	public String getDictionaryName() {
		return dictionaryName;
	}

	/**************************** Function Start *******************************/

	/**
	 * 准备查询方法，可以根据需要对需要初始化的文本赋值
	 * 
	 * @return
	 */
	public String prepareQuery() throws Exception {
		return SUCCESS;
	}

	/**
	 * UmTDictionaryDetail查询，根据umTDictionaryDetail带过来的值为查询条件进行查询。
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
			if (umTDictionary != null) {
				umTDictionaryDetail = new UmTDictionaryDetail();
				umTDictionaryDetail.setDictionaryId(umTDictionary.getId()
						.getDictionaryId());
			}
			umTDictionaryDetail.setComId(getUserFromSession().getComId());
			Map<String,String> dictionaryNameMap = new HashMap<String,String>();
			Page resultPage = umTDictionaryDetailService
					.findByUmTDictionaryDetail(umTDictionaryDetail, page, rows);
			/**对相应的字典代码进行翻译**/
			List dictionaryDetailList = resultPage.getResult();
			Iterator it = dictionaryDetailList.iterator();
			UmTDictionaryDetail detail = null;
			while(it.hasNext()){
				detail = (UmTDictionaryDetail)it.next();
				
				if(!dictionaryNameMap.containsKey(detail.getDictionaryId())){
					dictionaryNameMap.put(detail.getDictionaryId(), umTDictionaryService.findUmTDictionaryByPK(new UmTDictionaryId(detail.getDictionaryId())).getDitionaryName());
				}
				detail.setDictionaryId(dictionaryNameMap.get(detail.getDictionaryId()));
			}
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}

	/**
	 * 准备更新UmTDictionaryDetail信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		operateType = "update";
		umTDictionaryDetail = umTDictionaryDetailService.findUmTDictionaryDetailByPK(id);
		dictionaryName = umTDictionaryService.findUmTDictionaryByPK(new UmTDictionaryId(umTDictionaryDetail.getDictionaryId())).getDitionaryName();
		return SUCCESS;
	}

	/**
	 * 更新UmTDictionaryDetail信息
	 * 
	 * @return
	 */
	public String update() throws Exception {
		umTDictionaryDetail.setComCode(getUserFromSession().getComId());
		umTDictionaryDetail.setUpdaterCode(getUserFromSession().getUserCode());
		umTDictionaryDetail.setComId(getUserFromSession().getComId());
		umTDictionaryDetailService.updateUmTDictionaryDetail(umTDictionaryDetail);
		return SUCCESS;
	}

	/**
	 * 准备增加UmTDictionaryDetail信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		operateType = "add";
		return SUCCESS;
	}

	/**
	 * 新增UmTDictionaryDetail信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		umTDictionaryDetail.setComCode(getUserFromSession().getComId());
		umTDictionaryDetail.setComId(getUserFromSession().getComId());
		umTDictionaryDetail.setCreatorCode(getUserFromSession().getUserCode());
		umTDictionaryDetailService.saveUmTDictionaryDetail(umTDictionaryDetail);
		return SUCCESS;
	}

	/**
	 * 删除UmTDictionaryDetail信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try {
			if (id != null) {
				umTDictionaryDetailService.deleteByPK(id);
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
	 * 查看UmTDictionaryDetail信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		operateType = "view";
		umTDictionaryDetail = umTDictionaryDetailService.findUmTDictionaryDetailByPK(id);
		dictionaryName = umTDictionaryService.findUmTDictionaryByPK(new UmTDictionaryId(umTDictionaryDetail.getDictionaryId())).getDitionaryName();
		return SUCCESS;
	}

	/**
	 * 从数据字典处直接跳转数据字典明细处进行数据添加处理
	 * 
	 * @return
	 * @throws Exception
	 *             2013-8-6上午10:44:31 jiangweiyang
	 */
	public String addFromDict() throws Exception {
		// 获取权限字典ID
		umTDictionary = umTDictionaryService.findUmTDictionaryByPK(id2); // 查询权限字典对象
		// 根据权限字典ID查询权限字典明细信息
		umTDictionaryDetailList = umTDictionaryDetailService.findUmTDictionaryDetailListByDictCode(getUserFromSession().getComId(),id2.getDictionaryId()); // 查询该数据字典下各明细字典信息
		return SUCCESS;
	}

	@SuppressWarnings("rawtypes")
	public void rowEdit() throws Exception {
		// 对表格中的数据进行自定义添加处理
		Map<String, String> resultMap = new HashMap<String, String>();
		try{
			String deleted = getRequest().getParameter("deleted"); // 获取删除数据
			String inserted = getRequest().getParameter("inserted"); // 获取插入数据
			String updated = getRequest().getParameter("updated"); // 获取更新数据
	
			JSONArray array = null;
			List<Map<String, String>> arrayList = null;
			Map<String, String> map = null;
			Iterator it = null;
			JSONObject obj = null;
			Iterator itkey = null;
			String key = null;
			if (inserted != null && !"".equals(inserted) && !"[]".equals(inserted)) {
				// 添加数据
				array = JSONArray.fromObject(inserted);
				if (array.size() > 1) {
					arrayList = new ArrayList<Map<String, String>>();
					it = array.iterator();
					while (it.hasNext()) {
						obj = (JSONObject) it.next();
						itkey = obj.keys();
						map = new HashMap<String, String>();
						while (itkey.hasNext()) {
							key = String.valueOf(itkey.next());
							map.put(key, String.valueOf(obj.get(key)));
						}
						arrayList.add(map);
					}
				} else {
					obj = (JSONObject) array.get(0);
					itkey = obj.keys();
					map = new HashMap<String, String>();
					while (itkey.hasNext()) {
						key = String.valueOf(itkey.next());
						map.put(key, String.valueOf(obj.get(key)));
					}
				}
				if (arrayList != null && arrayList.size() > 0) {
					// 传进来不止一条数据
					List<UmTDictionaryDetail> list = new ArrayList<UmTDictionaryDetail>();
					UmTDictionaryDetail detail = null;
					for (Map keymap : arrayList) {
						detail = new UmTDictionaryDetail();
						detail.setDictionaryId(String.valueOf(keymap.get("dictionaryId")));
						detail.setTargetName(String.valueOf(keymap.get("targetName")));
						detail.setTargetField(String.valueOf(keymap.get("targetField")));
						detail.setValidStatus(String.valueOf(keymap.get("validStatus")));
						detail.setComCode(getUserFromSession().getComId());
						detail.setCreatorCode(getUserFromSession().getUserCode());
						detail.setComId(getUserFromSession().getComId());
						list.add(detail);
					}
					umTDictionaryDetailService.insertUmTDictionaryDetailList(list);
				} else {
					// 保存单条数据
					UmTDictionaryDetail detail = new UmTDictionaryDetail();
					detail.setDictionaryId(String.valueOf(map.get("dictionaryId")));
					detail.setTargetName(String.valueOf(map.get("targetName")));
					detail.setTargetField(String.valueOf(map.get("targetField")));
					detail.setValidStatus(String.valueOf(map.get("validStatus")));
					detail.setComCode(getUserFromSession().getComId());
					detail.setCreatorCode(getUserFromSession().getUserCode());
					detail.setComId(getUserFromSession().getComId());
					umTDictionaryDetailService.saveUmTDictionaryDetail(detail);
				}
				resultMap.put("status", "true");
			}
			if (updated != null && !"".equals(updated) && !"[]".equals(updated)) {
				array = JSONArray.fromObject(updated);
				if (array.size() > 1) {
					arrayList = new ArrayList<Map<String, String>>();
					it = array.iterator();
					while (it.hasNext()) {
						obj = (JSONObject) it.next();
						itkey = obj.keys();
						map = new HashMap<String, String>();
						while (itkey.hasNext()) {
							key = String.valueOf(itkey.next());
							map.put(key, String.valueOf(obj.get(key)));
						}
						arrayList.add(map);
					}
				} else {
					try {
						obj = (JSONObject) array.get(0);
						itkey = obj.keys();
						map = new HashMap<String, String>();
						while (itkey.hasNext()) {
							key = String.valueOf(itkey.next());
							map.put(key, String.valueOf(obj.get(key)));
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				if (arrayList != null && arrayList.size() > 0) {
					// 传进来不止一条数据
					List<UmTDictionaryDetail> list = new ArrayList<UmTDictionaryDetail>();
					UmTDictionaryDetail detail = null;
					UmTDictionaryDetailId id = null;
					for (Map keymap : arrayList) {
						id = new UmTDictionaryDetailId(String.valueOf(keymap.get("dictionaryDetailId")));
						detail = new UmTDictionaryDetail();
						detail.setId(id);
						detail.setDictionaryId(String.valueOf(keymap.get("dictionaryId")));
						detail.setSerialNo(Integer.parseInt(String.valueOf(keymap.get("serialNo"))));
						detail.setComCode(getUserFromSession().getComId());
						detail.setTargetName(String.valueOf(keymap.get("targetName")));
						detail.setTargetField(String.valueOf(keymap.get("targetField")));
						detail.setValidStatus(String.valueOf(keymap.get("validStatus")));
						detail.setUpdaterCode(getUserFromSession().getUserCode());
						detail.setComId(getUserFromSession().getComId());
						list.add(detail);
					}
					umTDictionaryDetailService.updateUmTDictionaryDetailList(list);
				} else {
					String dictDetailId = String.valueOf(map.get("dictionaryDetailId"));
					UmTDictionaryDetail detail = new UmTDictionaryDetail();
					detail.setId(new UmTDictionaryDetailId(dictDetailId));
					detail.setSerialNo(Integer.parseInt(String.valueOf(map.get("serialNo"))));
					detail.setComCode(getUserFromSession().getComId());
					detail.setDictionaryId(String.valueOf(map.get("dictionaryId")));
					detail.setTargetName(String.valueOf(map.get("targetName")));
					detail.setTargetField(String.valueOf(map.get("targetField")));
					detail.setValidStatus(String.valueOf(map.get("validStatus")));
					detail.setUpdaterCode(String.valueOf(getUserFromSession().getUserCode()));
					detail.setComId(getUserFromSession().getComId());
					umTDictionaryDetailService.updateUmTDictionaryDetail(detail);
				}
				resultMap.put("status", "true");
			}
			if (deleted != null && !"".equals(deleted) && !"[]".equals(deleted)) {
				array = JSONArray.fromObject(deleted);
				if (array.size() > 1) {
					arrayList = new ArrayList<Map<String, String>>();
					it = array.iterator();
					while (it.hasNext()) {
						obj = (JSONObject) it.next();
						itkey = obj.keys();
						map = new HashMap<String, String>();
						while (itkey.hasNext()) {
							key = String.valueOf(itkey.next());
							map.put(key, String.valueOf(obj.get(key)));
						}
						arrayList.add(map);
					}
				} else {
					try {
						obj = (JSONObject) array.get(0);
						itkey = obj.keys();
						map = new HashMap<String, String>();
						while (itkey.hasNext()) {
							key = String.valueOf(itkey.next());
							map.put(key, String.valueOf(obj.get(key)));
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				if (arrayList != null && arrayList.size() > 0) {
					// 传进来不止一条数据
					List<UmTDictionaryDetailId> list = new ArrayList<UmTDictionaryDetailId>();
					String dictDetailId = null;
					for (Map keymap : arrayList) {
						dictDetailId = String.valueOf(keymap.get("dictionaryDetailId"));
						list.add(new UmTDictionaryDetailId(dictDetailId));
					}
					umTDictionaryDetailService.deleteUmTDictionaryList(list);
				} else {
					String dictDetailId = String.valueOf(map.get("dictionaryDetailId"));
					UmTDictionaryDetailId id = new UmTDictionaryDetailId(dictDetailId);
					umTDictionaryDetailService.deleteByPK(id);
				}
				resultMap.put("status", "true");
			}
		}catch(Exception ex){
			resultMap.put("status", "false");
			resultMap.put("errormsg", ex.getMessage());
		}
		super.writeEasyUiData(JSONObject.fromObject(resultMap));
	}
}
