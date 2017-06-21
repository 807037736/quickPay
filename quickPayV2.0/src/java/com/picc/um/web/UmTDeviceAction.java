/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.web;

import ins.framework.common.Page;
import ins.framework.web.Struts2Action;

import java.util.HashMap;
import java.util.Map;

import com.picc.um.schema.model.UmTDevice;
import com.picc.um.schema.model.UmTDeviceId;
import com.picc.um.service.facade.IUmTDeviceService;


/** 
* @ClassName: UmTDeviceAction 
* @Description: TODO 用户设备管理Action
* @author dijie
* @date 2013-12-3 
*  
*/
public class UmTDeviceAction extends Struts2Action{
	
	private IUmTDeviceService umTDeviceService;	
	public void setUmTDeviceService(IUmTDeviceService umTDeviceService) {
		this.umTDeviceService = umTDeviceService;
	}

	public IUmTDeviceService getUmTDeviceService() {
		return umTDeviceService;
	}
	
	private UmTDevice umTDevice;
	
	private UmTDeviceId id;
	
	/** 操作类型 **/
	private String opreateType;
	/** UmTDevice getter/setter **/
	public UmTDevice getUmTDevice() {
		return this.umTDevice;
	}
	
	public void setUmTDevice(UmTDevice umTDevice) {
		this.umTDevice = umTDevice;
	}
	/** UmTDeviceId getter/setter **/
	public UmTDeviceId getId() {
		return this.id;
	}
	
	public void setId(UmTDeviceId id) {
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
	private String deviceId;
	public String getDeviceId() {
		return this.deviceId;
	}
	
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
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
	 * UmTDevice查询，根据umTDevice带过来的值为查询条件进行查询。
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
			umTDevice.setComId(getUserFromSession().getComId());
			Page resultPage = umTDeviceService.findByUmTDevice(umTDevice, page, rows);
			this.writeEasyUiData(resultPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			this.writeAjaxErrorByMap(null);
		}
		return NONE;
	}	


	/**
	 * 准备更新UmTDevice信息
	 * 
	 * @return
	 */
	public String prepareUpdate() throws Exception {
		
		opreateType = "update";
		umTDevice = umTDeviceService.findUmTDeviceByPK(id);
		return SUCCESS;
	}
	
	/**
	 * 更新UmTDevice信息
	 * 
	 * @return
	 */
	public String update() throws Exception { 
		umTDevice.setUpdaterCode(getUserFromSession().getUserCode());
		umTDevice.setComId(getUserFromSession().getComId());
		umTDeviceService.updateUmTDevice(umTDevice);
		return SUCCESS;
	}


	/**
	 * 准备增加UmTDevice信息
	 * 
	 * @return
	 */
	public String prepareAdd() throws Exception {
		
		opreateType = "add";
		return SUCCESS;
	}
	
	/**
	 * 新增UmTDevice信息
	 * 
	 * @return
	 */
	public String add() throws Exception {
		
		umTDevice.setComId(getUserFromSession().getComId());
		umTDeviceService.saveUmTDevice(umTDevice);
		return SUCCESS;
	}



	/**
	 * 删除UmTDevice信息
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		try{
			if(id!=null){
				
				umTDeviceService.deleteByPK(id);
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
	 * 查看UmTDevice信息方法
	 * 
	 * @return
	 */
	public String view() throws Exception {
		
		opreateType = "view";
		umTDevice = umTDeviceService.findUmTDeviceByPK(id);
		return SUCCESS;
	}
}
