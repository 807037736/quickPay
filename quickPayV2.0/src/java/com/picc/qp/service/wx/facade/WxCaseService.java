package com.picc.qp.service.wx.facade;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.picc.qp.schema.model.WxAccident;
import com.picc.qp.schema.model.WxCase;
import com.picc.qp.schema.model.WxCaseId;
import com.picc.qp.schema.vo.Pair;

import ins.framework.common.Page;

public interface WxCaseService {

	/**
	 * 查询临时案件列表
	 * 
	 * @param wxCase
	 * @return
	 * @throws Exception
	 * @author obba
	 */
	public List<WxCase> list(WxCase wxCase) throws Exception;

	/**
	 * 查询临时案件列表分页数据
	 * 
	 * @param wxCase
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 * @author obba
	 */
	public Page page(WxCase wxCase, Integer pageNo, Integer pageSize)
			throws Exception;

	/**
	 * 根据临时案件ID查询临时案件
	 * 
	 * @param wxCaseId
	 * @return
	 * @throws Exception
	 * @author obba
	 */
	public WxCase findById(WxCaseId wxCaseId) throws Exception;

	/**
	 * 保存临时案件(若有ID更新，无ID新增)
	 * 
	 * @param wxCase
	 * @throws Exception
	 * @author obba
	 */
	public void save(WxCase wxCase) throws Exception;

	/**
	 * 删除临时案件
	 * 
	 * @param wxCase
	 * @throws Exception
	 * @author obba
	 */
	public void delete(WxCase wxCase) throws Exception;

	/**
	 * 生成临时案件ID
	 * 
	 * @return
	 * @author obba
	 */
	public String getWxCaseId();

	/**
	 * 查询进行中的临时案件
	 * 
	 * @throws Exception
	 * @author obba
	 */
	public WxCase findCurrentCaseByUser(String userCode) throws Exception;

	/**
	 * 保存临时案件和相关临时当事人
	 * 
	 * @param caseinfo
	 * @param accidents
	 * @return
	 * @throws Exception
	 * @author obba
	 */
	public Pair<Boolean, String> saveWxCaseAndWxAccident(WxCase caseinfo,
			List<WxAccident> accidents) throws Exception;

	/**
	 * 保存真实案件和相关当事人
	 * 
	 * @param caseinfo
	 * @return
	 * @throws Exception
	 * @author obba
	 */
	public Pair<Boolean, String> saveCaseAndAccident(WxCase caseinfo)
			throws Exception;
	
	/**
	 * 根据真实案件ID查询临时案件
	 * @param realID
	 * @return
	 * @throws Exception
	 */
	public WxCase findCaseBuRealID(String realID) throws Exception;
	/**
	 * 根据caseID,accID,图片url,模板url,是否打印底图（全打/套打）
	 * @param caseID,accID,imgUrl,templateUrl,isPrint
	 * @return InputStream
	 * @throws Exception
	 */
	public JSONObject getLossSourceJson(String caseId,String accId,String imgUrl,String templateUrl,boolean isPrint,String lossStampUrl,boolean lossStampIsPrint) throws Exception ;

}
