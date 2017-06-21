/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.QueryRuleHelper;
import com.picc.common.dao.CommonDaoHibernate;
import com.picc.um.dao.UmTDictionaryDetailDaoHibernate;
import com.picc.um.schema.model.UmTDictionaryDetail;
import com.picc.um.schema.model.UmTDictionaryDetailId;
import com.picc.um.schema.vo.FieldOperateValue;
import com.picc.um.service.facade.IUmTDictionaryDetailService;

/**
 * 用户数据字典明细接口实现类
 * @author 姜卫洋
 */
@Service("umTDictionaryDetailService")
public class UmTDictionaryDetailServiceSpringImpl implements
		IUmTDictionaryDetailService {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UmTDictionaryDetailDaoHibernate umTDictionaryDetailDao;

	@Autowired
	private CommonDaoHibernate commonDao;

	/**
	 * 根据主键对象UmTDictionaryDetailId获取UmTDictionaryDetail信息
	 * 
	 * @param UmTDictionaryDetailId
	 * @return UmTDictionaryDetail
	 */
	public UmTDictionaryDetail findUmTDictionaryDetailByPK(
			UmTDictionaryDetailId id) throws Exception {

		return umTDictionaryDetailDao.get(UmTDictionaryDetail.class, id);
	}

	/**
	 * 根据umTDictionaryDetail和页码信息，获取Page对象
	 * 
	 * @param umTDictionaryDetail
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTDictionaryDetail的Page对象
	 */
	public Page findByUmTDictionaryDetail(
			UmTDictionaryDetail umTDictionaryDetail, int pageNo, int pageSize)
			throws Exception {
		QueryRule queryRule = QueryRuleHelper
				.generateQueryRule(umTDictionaryDetail);
		queryRule.addAscOrder("serialNo");
		return umTDictionaryDetailDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 更新UmTDictionaryDetail信息
	 * 
	 * @param UmTDictionaryDetail
	 */
	public synchronized void updateUmTDictionaryDetail(
			UmTDictionaryDetail umTDictionaryDetail) throws Exception {
		umTDictionaryDetailDao.update(umTDictionaryDetail);
	}

	/**
	 * 保存UmTDictionaryDetail信息
	 * 
	 * @param UmTDictionaryDetail
	 */
	@SuppressWarnings("rawtypes")
	public synchronized void saveUmTDictionaryDetail(UmTDictionaryDetail umTDictionaryDetail) throws Exception {
		QueryRule rule = QueryRule.getInstance();
		rule.addEqual("dictionaryId", umTDictionaryDetail.getDictionaryId());
		rule.addEqual("targetName", umTDictionaryDetail.getTargetName());
		rule.addEqual("targetField", umTDictionaryDetail.getTargetField());
		List list = umTDictionaryDetailDao.find(rule);
		if(list==null||list.size()<1){
			//没有数据
			UmTDictionaryDetailId id = new UmTDictionaryDetailId();
			id.setDictionaryDetailId(commonDao.generateID("UMDD",
					"UM_SEQ_DICTIONARYDETAIL", 26));
			umTDictionaryDetail.setId(id);
			if (umTDictionaryDetail.getSerialNo() == null) {
				String serialNo = String.valueOf(umTDictionaryDetailDao
						.getMax(umTDictionaryDetail.getDictionaryId()));
				if (serialNo == null || "".equals(serialNo)
						|| "null".equals(serialNo)) {
					serialNo = "1";
				}
				umTDictionaryDetail.setSerialNo(Integer.parseInt(serialNo));
			}
			umTDictionaryDetailDao.save(umTDictionaryDetail);
		}else {
			throw new Exception("已经存在字典明细信息,无须重复添加");
		}
	}

	/**
	 * 根据主键对象，删除UmTDictionaryDetail信息
	 * 
	 * @param UmTDictionaryDetailId
	 */
	public void deleteByPK(UmTDictionaryDetailId id) throws Exception {

		umTDictionaryDetailDao.deleteByPK(UmTDictionaryDetail.class, id);
	}

	/**
	 * 批量插入权限字典明细数据
	 */
	public synchronized void insertUmTDictionaryDetailList(
			List<UmTDictionaryDetail> list) throws Exception {
		int index = 0;
		for (UmTDictionaryDetail detail : list) {
			if (index != 0) {
				detail.setSerialNo(list.get(index - 1).getSerialNo() + 1);
			}
			saveUmTDictionaryDetail(detail);
			index++;
		}
	}

	/**
	 * 批量更新权限字典明细数据
	 */
	public void updateUmTDictionaryDetailList(List<UmTDictionaryDetail> list)
			throws Exception {
		for (UmTDictionaryDetail detail : list) {
			updateUmTDictionaryDetail(detail);
		}
	}

	/**
	 * 批量删除权限字典明细数据
	 */
	public void deleteUmTDictionaryList(List<UmTDictionaryDetailId> list)
			throws Exception {
		for (UmTDictionaryDetailId id : list) {
			deleteByPK(id); // 根据主键删除对象
		}
	}

	@SuppressWarnings("unchecked")
	public List<UmTDictionaryDetail> findUmTDictionaryDetailListByDictCode(
			String comId, String dictionaryId) throws Exception {
		UmTDictionaryDetail dictDetail = new UmTDictionaryDetail();
		dictDetail.setDictionaryId(dictionaryId);
		dictDetail.setComId(comId);
		Page page = findByUmTDictionaryDetail(dictDetail, 1, Integer.MAX_VALUE);
		if (page != null && page.getTotalCount() < 1) {
			return null;
		} else {
			return page.getResult();
		}
	}
	
	
	
	@SuppressWarnings("rawtypes")
	public List<String> findTargetFieldByDictName(String dictName,String userCode) throws Exception {
		String executeSQL = "select * from um_t_dictionarydetail a where exists " +
				"(select 1 from um_t_dictionary b where a.dictionaryid = b.dictionaryid and b.dictionaryname = ? " +
				"and a.validstatus = '1' and b.validstatus = '1' and exists (select 1 from um_t_userpower c " +
				"where b.dictionaryid = c.dictionaryid and c.usercode = ?))";
		Page page = commonDao.findObjectPageBySql(executeSQL, FieldOperateValue.class, 1, Integer.MAX_VALUE, new Object[]{dictName,userCode});
		List list = page.getResult();
		List<String> resultList = new ArrayList<String>();
		Iterator it = list.iterator();
		FieldOperateValue fieldOperateValue = null;
		while(it.hasNext()){
			fieldOperateValue = (FieldOperateValue)it.next();
			if(!resultList.contains(fieldOperateValue.getTargetField().toLowerCase())){
				resultList.add(fieldOperateValue.getTargetField().toLowerCase());
			}
		}
		return resultList;
	}

}
