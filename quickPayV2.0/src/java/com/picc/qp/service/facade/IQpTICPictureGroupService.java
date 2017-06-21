/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.qp.schema.model.QpTICPictureGroup;
import com.picc.qp.schema.model.QpTICPictureGroupId;

public interface IQpTICPictureGroupService{

	/**
	 * 根据主键对象QpTICPictureGroupId获取QpTICPictureGroup信息
	 * @param QpTICPictureGroupId
	 * @return QpTICPictureGroup
	 */
	public QpTICPictureGroup findQpTICPictureGroupByPK(QpTICPictureGroupId id) throws Exception;

	/**
	 * 根据qpTICPictureGroup和页码信息，获取Page对象
	 * @param qpTICPictureGroup
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTICPictureGroup的Page对象
	 */
	public Page findByQpTICPictureGroup(QpTICPictureGroup qpTICPictureGroup, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新QpTICPictureGroup信息
	 * @param QpTICPictureGroup
	 */
	public void updateQpTICPictureGroup(QpTICPictureGroup qpTICPictureGroup) throws Exception;

	/**
	 * 保存QpTICPictureGroup信息
	 * @param QpTICPictureGroup
	 */
	public void saveQpTICPictureGroup(QpTICPictureGroup qpTICPictureGroup) throws Exception;

	/**
	 * 根据主键对象，删除QpTICPictureGroup信息
	 * @param QpTICPictureGroupId
	 */
	public void deleteByPK(QpTICPictureGroupId id) throws Exception;

	public List<QpTICPictureGroup> findQpTICPictureGroupByUserCode(
			String userCode);
	
	/**
     * 根据手机号/车牌号+48小时内提取上传照片
     * @param qpTICAccident
     * @return
     * @throws Exception
     */
    public Page findByQpTICPictureGroupDraw(QpTICPictureGroup qpTICPictureGroup, int pageNo, int pageSize) throws Exception;
}
