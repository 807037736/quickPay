/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.facade;

import ins.framework.common.Page;

import com.picc.um.schema.model.UmTDevice;
import com.picc.um.schema.model.UmTDeviceId;

/** 
* @ClassName: IUmTDeviceService 
* @Description: TODO 用户设备管理Service层接口类
* @author dijie
* @date 2013-12-3 
*  
*/
public interface IUmTDeviceService{

	/**
	 * 根据主键对象UmTDeviceId获取UmTDevice信息
	 * @param UmTDeviceId
	 * @return UmTDevice
	 */
	public UmTDevice findUmTDeviceByPK(UmTDeviceId id) throws Exception;

	/**
	 * 根据umTDevice和页码信息，获取Page对象
	 * @param umTDevice
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTDevice的Page对象
	 */
	public Page findByUmTDevice(UmTDevice umTDevice, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新UmTDevice信息
	 * @param UmTDevice
	 */
	public void updateUmTDevice(UmTDevice umTDevice) throws Exception;

	/**
	 * 保存UmTDevice信息
	 * @param UmTDevice
	 */
	public void saveUmTDevice(UmTDevice umTDevice) throws Exception;

	/**
	 * 根据主键对象，删除UmTDevice信息
	 * @param UmTDeviceId
	 */
	public void deleteByPK(UmTDeviceId id) throws Exception;
	
}
