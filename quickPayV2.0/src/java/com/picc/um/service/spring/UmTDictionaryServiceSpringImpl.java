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
import com.picc.um.dao.UmTDictionaryDaoHibernate;
import com.picc.um.schema.model.UmTDictionary;
import com.picc.um.schema.model.UmTDictionaryId;
import com.picc.um.service.facade.IUmTDictionaryService;

/**
 * 用户数据字典接口实现类
 * @author 姜卫洋
 */
@Service("umTDictionaryService")
public class UmTDictionaryServiceSpringImpl implements IUmTDictionaryService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private UmTDictionaryDaoHibernate umTDictionaryDao;
	
	@Autowired
	private CommonDaoHibernate commonDao;

	/**
	 * 根据主键对象UmTDictionaryId获取UmTDictionary信息
	 * @param UmTDictionaryId
	 * @return UmTDictionary
	 */
	public UmTDictionary findUmTDictionaryByPK(UmTDictionaryId id) throws Exception{
			return umTDictionaryDao.get(UmTDictionary.class,id);
	}

	/**
	 * 根据umTDictionary和页码信息，获取Page对象
	 * @param umTDictionary
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTDictionary的Page对象
	 */
	public Page findByUmTDictionary(UmTDictionary umTDictionary, int pageNo, int pageSize) throws Exception{
		QueryRule queryRule = QueryRuleHelper.generateQueryRule(umTDictionary);
		return umTDictionaryDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 更新UmTDictionary信息
	 * @param UmTDictionary
	 */
	public void updateUmTDictionary(UmTDictionary umTDictionary) throws Exception{
			umTDictionaryDao.update(umTDictionary);
	}

	/**
	 * 保存UmTDictionary信息
	 * @param UmTDictionary
	 */
	public void saveUmTDictionary(UmTDictionary umTDictionary) throws Exception{
			if(umTDictionary.getId()==null){
				//需要自动生成一个ID对象
				UmTDictionaryId id = new UmTDictionaryId();
				id.setDictionaryId(commonDao.generateID("UMDT", "UM_SEQ_DICTIONARY", 26));
				umTDictionary.setId(id);
			}
			umTDictionaryDao.save(umTDictionary);
	}

	/**
	 * 根据主键对象，删除UmTDictionary信息
	 * @param UmTDictionaryId
	 */
	public void deleteByPK(UmTDictionaryId id) throws Exception{
			umTDictionaryDao.deleteByPK(UmTDictionary.class,id);
	}
	
	
	/**
	 * 获取所有有效的权限字典集合
	 */
	public Page findValidDictionary(String comId,int pageNo,int pageSize) throws Exception {
		UmTDictionary dict = new UmTDictionary();
		dict.setValidStatus("1");							//设置为有效位
		dict.setComId(comId);
		return findByUmTDictionary(dict,pageNo,pageSize);
	}
	
}
