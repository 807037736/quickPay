/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.QueryRuleHelper;
import com.picc.common.dao.CommonDaoHibernate;
import com.picc.um.dao.UmTDeviceDaoHibernate;
import com.picc.um.schema.model.UmTDevice;
import com.picc.um.schema.model.UmTDeviceId;
import com.picc.um.service.facade.IUmTDeviceService;


/** 
* @ClassName: UmTDeviceServiceSpringImpl 
* @Description: TODO 用户设备管理Service层实现类
* @author dijie
* @date 2013-12-3 
*  
*/
@Service("umTDeviceService")
public class UmTDeviceServiceSpringImpl implements IUmTDeviceService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private UmTDeviceDaoHibernate umTDeviceDao;
	
	@Autowired
	private CommonDaoHibernate commonDao;
	/**
	 * 根据主键对象UmTDeviceId获取UmTDevice信息
	 * @param UmTDeviceId
	 * @return UmTDevice
	 */
	public UmTDevice findUmTDeviceByPK(UmTDeviceId id) throws Exception{
			
			return umTDeviceDao.get(UmTDevice.class,id);
	}

	/**
	 * 根据umTDevice和页码信息，获取Page对象
	 * @param umTDevice
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTDevice的Page对象
	 */
	public Page findByUmTDevice(UmTDevice umTDevice, int pageNo, int pageSize) throws Exception{
		
//		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		QueryRule queryRule = null;
		queryRule = QueryRuleHelper.generateQueryRule(umTDevice);
		//根据umTDevice生成queryRule
		
		return umTDeviceDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 更新UmTDevice信息
	 * @param UmTDevice
	 */
	public void updateUmTDevice(UmTDevice umTDevice) throws Exception{
			
			umTDeviceDao.update(umTDevice);
	}

	/**
	 * 保存UmTDevice信息
	 * @param UmTDevice
	 */
	public void saveUmTDevice(UmTDevice umTDevice) throws Exception{
			
			UmTDeviceId id = new UmTDeviceId();
			id.setDeviceId(commonDao.generateID("UMDV", "UM_SEQ_DEVICE", 25));
			
			umTDevice.setId(id);
			umTDeviceDao.save(umTDevice);
	}

	/**
	 * 根据主键对象，删除UmTDevice信息
	 * @param UmTDeviceId
	 */
	public void deleteByPK(UmTDeviceId id) throws Exception{
			
			umTDeviceDao.deleteByPK(UmTDevice.class,id);
	}
}
