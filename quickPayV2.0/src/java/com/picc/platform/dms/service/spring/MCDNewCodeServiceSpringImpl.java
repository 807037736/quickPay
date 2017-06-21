package com.picc.platform.dms.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.platform.dms.dao.MCDNewCodeDaoHibernate;
import com.picc.platform.dms.dao.MCDTypeDaoHibernate;
import com.picc.platform.dms.schema.model.MCDNewCode;
import com.picc.platform.dms.schema.model.MCDNewCodeId;
import com.picc.platform.dms.schema.model.MCDType;
import com.picc.platform.dms.service.facade.MCDNewCodeService;

@Service(value = "mcDNewCodeService")
public class MCDNewCodeServiceSpringImpl implements MCDNewCodeService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MCDNewCodeDaoHibernate mcDNewCodeDao;
	@Autowired
	private MCDTypeDaoHibernate mcDTypeDao;
	public Page findNewCodeInfo(QueryRule queryRule, int pageNo, int pageSize) {
		return mcDNewCodeDao.find(queryRule, pageNo, pageSize);
	}
	/**
	 * 根据主键对象NewCodeId获取NewCode信息
	 * @param QpTCOMDictionaryId
	 * @return NewCode
	 */
	public MCDNewCode findNewCodeByPK(MCDNewCodeId id) throws Exception{
			
			return mcDNewCodeDao.get(MCDNewCode.class,id);
	}

	/**
	 * 根据NewCode和页码信息，获取Page对象
	 * @param MCDNewCode
	 * @param pageNo
	 * @param pageSize
	 * @return 包含NewCode的Page对象
	 */
	public Page findByNewCode(QueryRule queryRule, int pageNo, int pageSize) throws Exception{
		//根据NewCode生成queryRule
		return mcDNewCodeDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 更新NewCode信息
	 * @param MCDNewCode
	 */
	public void updateNewCode(MCDNewCode newcode) throws Exception{
			
			mcDNewCodeDao.update(newcode);
	}

	/**
	 * 保存NewCode信息
	 * @param MCDNewCode
	 */
	public void saveNewCode(MCDNewCode newcode) throws Exception{
			
			mcDNewCodeDao.save(newcode);
	}

	/**
	 * 根据主键对象，删除NewCode信息
	 * @param MCDNewCodeId
	 */
	public void deleteNewCodeByPK(MCDNewCodeId id) throws Exception{
			
			mcDNewCodeDao.deleteByPK(MCDNewCode.class,id);
	}
	
	/** codetype的增删改查操作 **/
	
	
	public Page findTypeInfo(QueryRule queryRule, int pageNo, int pageSize) {
		
		return mcDTypeDao.find(queryRule, pageNo, pageSize);
	}
	/**
	 * 根据主键对象TypeId获取Type信息
	 * @param MCDTypeId
	 * @return Type
	 */
	public MCDType findTypeByPK(String codeType) throws Exception{
			
			return mcDTypeDao.get(MCDType.class,codeType);
	}

	/**
	 * 根据Type和页码信息，获取Page对象
	 * @param MCDType
	 * @param pageNo
	 * @param pageSize
	 * @return 包含Type的Page对象
	 */
	public Page findByType(String codetype, int pageNo, int pageSize) throws Exception{
		
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		if(codetype!=null&&!"".equals(codetype)){
			queryRule.addEqual("id.codeType", codetype);
		}
			
		//根据Type生成queryRule
		return mcDTypeDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 更新Type信息
	 * @param MCDType
	 */
	public void updateType(MCDType type) throws Exception{
			
			mcDTypeDao.update(type);
	}

	/**
	 * 保存Type信息
	 * @param MCDType
	 */
	public void saveType(MCDType type) throws Exception{
			
			mcDTypeDao.save(type);
	}

	/**
	 * 根据主键对象，删除Type信息
	 * @param MCDTypeId
	 */
	public void deleteTypeByPK(String codeType) throws Exception{
			
			mcDTypeDao.deleteByPK(MCDType.class,codeType);
	}	
}
