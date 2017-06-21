/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.facade;

import com.picc.qp.schema.model.QpTIcCompanyGarage;
import com.picc.qp.schema.model.QpTIcCompanyGarageId;

public interface IQpTIcCompanyGarageService{

	/**
	 * 根据主键对象QpTTPCaseId获取QpTTPCase信息
	 * @param QpTTPCaseId
	 * @return QpTTPCase
	 */
	public QpTIcCompanyGarage findQpTIcCompanyGarageByPK(QpTIcCompanyGarageId id) throws Exception;

	public QpTIcCompanyGarage findQpTIcCompanyGarageByCoId(String coId) throws Exception;

}
