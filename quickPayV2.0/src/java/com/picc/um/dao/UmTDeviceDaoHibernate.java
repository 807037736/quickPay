/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.dao;
import ins.framework.dao.GenericDaoHibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.picc.um.schema.model.UmTDevice;

/**
 * 用户设备对应数据层操作DAO
 * @author 邸杰
 */
@Repository("umTDeviceDao")
public class UmTDeviceDaoHibernate extends GenericDaoHibernate<UmTDevice,String> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

}
