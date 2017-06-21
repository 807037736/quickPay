/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2015
 */

package com.picc.qp.service.facade;

import ins.framework.common.Page;

import java.util.List;

import com.picc.qp.schema.model.QpTICPicture;
import com.picc.qp.schema.model.QpTICPictureId;

public interface IQpTICPictureService{

	/**
	 * 根据主键对象QpTICPictureId获取QpTICPicture信息
	 * @param QpTICPictureId
	 * @return QpTICPicture
	 */
	public QpTICPicture findQpTICPictureByPK(QpTICPictureId id) throws Exception;

	/**
	 * 根据qpTICPicture和页码信息，获取Page对象
	 * @param qpTICPicture
	 * @param pageNo
	 * @param pageSize
	 * @return 包含QpTICPicture的Page对象
	 */
	public Page findByQpTICPicture(QpTICPicture qpTICPicture, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新QpTICPicture信息
	 * @param QpTICPicture
	 */
	public void updateQpTICPicture(QpTICPicture qpTICPicture) throws Exception;

	/**
	 * 保存QpTICPicture信息
	 * @param QpTICPicture
	 */
	public void saveQpTICPicture(QpTICPicture qpTICPicture) throws Exception;

	/**
	 * 根据主键对象，删除QpTICPicture信息
	 * @param QpTICPictureId
	 */
	public void deleteByPK(QpTICPictureId id) throws Exception;

	public void saveQpTICPicture(QpTICPicture qpTICPicture, String userCode);
	
	public String getGroupID();
	
	/**
	 * 更新图片地址
	 * @return List<QpTICPicture>
	 */
	public List<QpTICPicture> findQpTICPictureAll() throws Exception;
	
	/**
	 * 修改图片service
	 * @throws Exception 
	 */
	public void updateFileName(int pageNo , int pageSize) throws Exception;
	/**
	 * 通过组号找到对应的图片
	 * @param groupId
	 * @return
	 * @throws Exception
	 */
	public List<QpTICPicture> findQpTICPictureByGroupId(String groupId) throws Exception;
	
	/**
	 * 批量保存图片
	 * @param qpTICPictures
	 * @param userCode
	 */
	public void saveQpTICPictures(List<QpTICPicture> qpTICPictures, String userCode);
	
}
