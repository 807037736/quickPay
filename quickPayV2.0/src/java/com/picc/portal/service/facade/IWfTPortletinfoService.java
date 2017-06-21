/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.portal.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import com.picc.portal.schema.model.WfTPortletinfo;
import com.picc.portal.schema.model.WfTPortletinfoId;

public interface IWfTPortletinfoService{

	/**
	 * 根据主键对象WfTPortletinfoId获取WfTPortletinfo信息
	 * @param WfTPortletinfoId
	 * @return WfTPortletinfo
	 */
	public WfTPortletinfo findWfTPortletinfoByPK(WfTPortletinfoId id) throws Exception;

	/**
	 * 根据wfTPortletinfo和页码信息，获取Page对象
	 * @param wfTPortletinfo
	 * @param pageNo
	 * @param pageSize
	 * @return 包含WfTPortletinfo的Page对象
	 */
	public Page findByWfTPortletinfo(WfTPortletinfo wfTPortletinfo, int pageNo, int pageSize) throws Exception;
	public Page find(QueryRule rule, int pageNo, int pageSize);
	/**
	 * 更新WfTPortletinfo信息
	 * @param WfTPortletinfo
	 */
	public void updateWfTPortletinfo(WfTPortletinfo wfTPortletinfo) throws Exception;

	/**
	 * 保存WfTPortletinfo信息
	 * @param WfTPortletinfo
	 */
	public void saveWfTPortletinfo(WfTPortletinfo wfTPortletinfo) throws Exception;

	/**
	 * 根据主键对象，删除WfTPortletinfo信息
	 * @param WfTPortletinfoId
	 */
	public void deleteByPK(WfTPortletinfoId id) throws Exception;
	
	/**
	 * 获得页面portlet信息
	 * author zhou
	 */
	public List<WfTPortletinfo> findWfTPortletinfoByUserCode(String userCode,String comId,String comCode,String svrCode) throws Exception;
	
	/**
	 * 更新porletinfo的isclose全为1
	 * author zhou
	 */
	public void updatePorletIsClose(String userCode,String comId,String comCode);
	
	
	/**
	 * 保存portlet位置
	 * author  zhou
	 */
	public void savePortletPosition(String left,String middle,String right,String userCode,String comId,String comCode);

//	public void addWfTPortletinfo(String userCode,String portletId,String comId);
}
