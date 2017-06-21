/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.facade;

import java.util.List;

import com.picc.qp.schema.model.QpTIcCompanyGarageDetail;
import com.picc.qp.schema.model.QpTIcCompanyGarageDetailId;

public interface IQpTIcCompanyGarageDetailService{

	/**
	 * 根据主键对象QpTTPCaseId获取QpTTPCase信息
	 * @param QpTTPCaseId
	 * @return QpTTPCase
	 */
	public QpTIcCompanyGarageDetail findQpTIcCompanyGarageDetailByPK(QpTIcCompanyGarageDetailId id) throws Exception;

	
	public List<QpTIcCompanyGarageDetail> findQpTIcCompanyGarageDetailByCompanyGarageId(int companyGarageId) throws Exception;

}
