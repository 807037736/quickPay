/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.portal.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import net.sf.json.JSONObject;

import com.picc.portal.schema.model.WfTPortletclassfy;
import com.picc.portal.schema.model.WfTPortletclassfyId;

public interface IWfTPortletclassfyService{

	/**
	 * 根据主键对象WfTPortletclassfyId获取WfTPortletclassfy信息
	 * @param WfTPortletclassfyId
	 * @return WfTPortletclassfy
	 */
	public WfTPortletclassfy findWfTPortletclassfyByPK(WfTPortletclassfyId id) throws Exception;

	/**
	 * 根据wfTPortletclassfy和页码信息，获取Page对象
	 * @param wfTPortletclassfy
	 * @param pageNo
	 * @param pageSize
	 * @return 包含WfTPortletclassfy的Page对象
	 */
	public Page findByWfTPortletclassfy(WfTPortletclassfy wfTPortletclassfy, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新WfTPortletclassfy信息
	 * @param WfTPortletclassfy
	 */
	public void updateWfTPortletclassfy(WfTPortletclassfy wfTPortletclassfy) throws Exception;

	/**
	 * 保存WfTPortletclassfy信息
	 * @param WfTPortletclassfy
	 */
	public void saveWfTPortletclassfy(WfTPortletclassfy wfTPortletclassfy,String comId,String comCode) throws Exception;

	/**
	 * 根据主键对象，删除WfTPortletclassfy信息
	 * @param WfTPortletclassfyId
	 */
	public void deleteByPK(WfTPortletclassfyId id) throws Exception;
	
	public JSONObject getPortletInfo(String userCode,String comId,String comCode,String svrCode) throws Exception;
	
	public List<WfTPortletclassfy> findPortletClassfyAll();
	
	public List<String> findWfTPortletinfoByUserCode(String userCode,String comId,String comCode,String svrCode) throws Exception;

	public void savePortletclassfy(String portletName , String url);

	public Page findWfTPortletclassfy(QueryRule queryRule, int pageNo, int pageSize);
	
	public String findPortalTop(String userCode, String comCode) throws Exception;
}
