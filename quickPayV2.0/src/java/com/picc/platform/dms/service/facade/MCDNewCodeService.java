package com.picc.platform.dms.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import com.picc.platform.dms.schema.model.MCDNewCode;
import com.picc.platform.dms.schema.model.MCDNewCodeId;
import com.picc.platform.dms.schema.model.MCDType;
/**
 * 数据字典表不同类型信息的获取
 * @author Administrator
 *
 */
public interface MCDNewCodeService {
	
	public Page findNewCodeInfo(QueryRule queryRule, int pageNo, int pageSize);
	/**
	 * 根据主键对象NewCodeId获取NewCode信息
	 * @param QpTCOMDictionaryId
	 * @return NewCode
	 */
	public MCDNewCode findNewCodeByPK(MCDNewCodeId id) throws Exception;

	/**
	 * 根据NewCode和页码信息，获取Page对象
	 * @param MCDNewCode
	 * @param pageNo
	 * @param pageSize
	 * @return 包含NewCode的Page对象
	 */
	public Page findByNewCode(QueryRule queryRule, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新NewCode信息
	 * @param MCDNewCode
	 */
	public void updateNewCode(MCDNewCode newcode) throws Exception;

	/**
	 * 保存NewCode信息
	 * @param MCDNewCode
	 */
	public void saveNewCode(MCDNewCode newcode) throws Exception;

	/**
	 * 根据主键对象，删除NewCode信息
	 * @param MCDNewCodeId
	 */
	public void deleteNewCodeByPK(MCDNewCodeId id) throws Exception;
	
	/** codetype的增删改查操作 **/
	
	public Page findTypeInfo(QueryRule queryRule, int pageNo, int pageSize);
	/**
	 * 根据主键对象TypeId获取Type信息
	 * @param MCDTypeId
	 * @return Type
	 */
	public MCDType findTypeByPK(String codeType) throws Exception;
	/**
	 * 根据Type和页码信息，获取Page对象
	 * @param MCDType
	 * @param pageNo
	 * @param pageSize
	 * @return 包含Type的Page对象
	 */
	public Page findByType(String codetype, int pageNo, int pageSize) throws Exception;

	/**
	 * 更新Type信息
	 * @param MCDType
	 */
	public void updateType(MCDType type) throws Exception;
	/**
	 * 保存Type信息
	 * @param MCDType
	 */
	public void saveType(MCDType type) throws Exception;
	/**
	 * 根据主键对象，删除Type信息
	 * @param MCDTypeId
	 */
	public void deleteTypeByPK(String codeType) throws Exception;
}
