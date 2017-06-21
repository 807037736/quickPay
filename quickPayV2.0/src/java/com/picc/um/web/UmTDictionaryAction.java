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

import com.picc.um.schema.model.UmTDictionary;
import com.picc.um.schema.model.UmTDictionaryId;
import com.picc.um.service.facade.IUmTDictionaryDetailService;
import com.picc.um.service.facade.IUmTDictionaryService;

/**
 * 数据字典Action处理层
 * @author 姜卫洋
 */
@SuppressWarnings("serial")
public class UmTDictionaryAction extends Struts2Action {

	private IUmTDictionaryService umTDictionaryService;
	
	private IUmTDictionaryDetailService umTDictionaryDetailService;

	public void setUmTDictionaryService(
			IUmTDictionaryService umTDictionaryService) {
		this.umTDictionaryService = umTDictionaryService;
	}

	public IUmTDictionaryService getUmTDictionaryService() {
		return umTDictionaryService;
	}

	private UmTDictionary umTDictionary;

	private UmTDictionaryId id;

	/** 操作类型 **/
	private String operateType;

	/** UmTDictionary getter/setter **/
	public UmTDictionary getUmTDictionary() {
		return this.umTDictionary;
	}

	public void setUmTDictionary(UmTDictionary umTDictionary) {
		this.umTDictionary = umTDictionary;
	}

	/** UmTDictionaryId getter/setter **/
	public UmTDictionaryId getId() {
		return this.id;
	}

	public void setId(UmTDictionaryId id) {
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
	private String dictionaryId;

	public String getDictionaryId() {
		return this.dictionaryId;
	}

	public void setDictionaryId(String dictionaryId) {
		this.dictionaryId = dictionaryId;
	}
	
	
	public void setUmTDictionaryDetailService(
			IUmTDictionaryDetailService umTDictionaryDetailService) {
		this.umTDictionaryDetailService = umTDictionaryDetailService;
	}
	
	public IUmTDictionaryDetailService getUmTDictionaryDetailService() {
		return umTDictionaryDetailService;
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
	 * UmTDictionary查询，根据umTDictionary带过来的值为查询条件进行查询。
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
			umTDictionary.setComId(getUserFromSession().getComId()); // 加入对于ComID的限制
			Page resultPage = umTDictionaryService.findByUmTDictionary(
					umTDictionary, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String comboValidQuery() throws Exception {
		umTDictionary.setComId(getUserFromSession().getComId());
		Page resultPage = umTDictionaryService.findByUmTDictionary(
				umTDictionary, 1, 50);
		List list = resultPage.getResult();
		Iterator<UmTDictionary> it = list.iterator();
		UmTDictionary dict = null;
		Map<String, String> map = null;
		List<Map<String, String>> arrayList = new ArrayList<Map<String, String>>();
		while (it.hasNext()) {
			dict = it.next();
			map = new HashMap<String, String>();
			map.put("id", dict.getId().getDictionaryId());
			map.put("text", dict.getDitionaryName());
			arrayList.add(map);
		}
		JSONArray array = JSONArray.fromObject(arrayList);
		this.renderHtml(array.toString());
		return null;
	}

	/**
	 * 准备更新UmTDictionary信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		operateType = "update";
		umTDictionary = umTDictionaryService.findUmTDictionaryByPK(id);
		return SUCCESS;
	}

	/**
	 * 更新UmTDictionary信息
	 * 
	 * @return
	 */
	public String update() throws Exception {
		umTDictionary.setId(id); // 设置获取的ID属性
		umTDictionary.setComId(getUserFromSession().getComId());
		umTDictionary.setUpdaterCode(getUserFromSession().getUserCode());
		umTDictionaryService.updateUmTDictionary(umTDictionary);
		return SUCCESS;
	}

	/**
	 * 准备增加UmTDictionary信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		operateType = "add";
		umTDictionary  = new UmTDictionary();
		umTDictionary.setComCode(getUserFromSession().getComId());
		return SUCCESS;
	}

	/**
	 * 新增UmTDictionary信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		try {
			umTDictionary.setComId(getUserFromSession().getComId());
			umTDictionary.setCreatorCode(getUserFromSession().getUserCode());
			umTDictionaryService.saveUmTDictionary(umTDictionary);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 删除UmTDictionary信息
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String delete() throws Exception {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			String dictionaryId = id.getDictionaryId();
			//判断该字典是否存在数据关联
			List list = umTDictionaryDetailService.findUmTDictionaryDetailListByDictCode(getUserFromSession().getComId(), dictionaryId);
			if(list!=null&&list.size()>=0){
				resultMap.put("errorTitle", "错误信息(ERROR)：");
				resultMap.put("errorMsg", "该数据权限字典存在明细配置,无法直接删除");
				this.writeAjaxErrorByMap(resultMap);
				return NONE;
			}else {
				if (id != null)
					umTDictionaryService.deleteByPK(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			resultMap.put("errorTitle", "错误信息(ERROR)：");
			resultMap.put("errorMsg", "删除数据时发生异常！");
			this.writeAjaxErrorByMap(resultMap);
		}
		return NONE;
	}

	/**
	 * 查看UmTDictionary信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		operateType = "view";
		umTDictionary = umTDictionaryService.findUmTDictionaryByPK(id);
		return SUCCESS;
	}
}
