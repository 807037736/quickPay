/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.QueryRuleHelper;
import com.picc.um.dao.UmTAccountDaoHibernate;
import com.picc.um.schema.model.UmTAccount;
import com.picc.um.schema.model.UmTAccountId;
import com.picc.um.service.facade.IUmTAccountService;


/** 
* @ClassName: UmTAccountServiceSpringImpl 
* @Description: TODO 账户管理Service层实现类
* @author dijie
* @date 2013-12-3 
*  
*/
@Service("umTAccountService")
public class UmTAccountServiceSpringImpl implements IUmTAccountService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private UmTAccountDaoHibernate umTAccountDao;

	/**
	 * 根据主键对象UmTAccountId获取UmTAccount信息
	 * @param UmTAccountId
	 * @return UmTAccount
	 */
	public UmTAccount findUmTAccountByPK(UmTAccountId id) throws Exception{
			
			return umTAccountDao.get(UmTAccount.class,id);
	}
	
	
	/**
	 * 根据userCode获取UmTAccount信息
	 * @param userCode
	 * @return UmTAccount
	 */
	public UmTAccount findUmTAccountByUserCode(String userCode) throws Exception{
			
			UmTAccountId umTAccountId = new UmTAccountId(userCode);
			return umTAccountDao.get(UmTAccount.class,umTAccountId);
	}
	
	
	/**
	 * 根据umTAccount和页码信息，获取Page对象
	 * @param umTAccount
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTAccount的Page对象
	 */
	public Page findByUmTAccount(UmTAccount umTAccount, int pageNo, int pageSize) throws Exception{
		
		QueryRule queryRule = null;
		queryRule = QueryRuleHelper.generateQueryRule(umTAccount);
		if (!("").equals(umTAccount.getId().getUserCode())
				&& !(umTAccount.getId().getUserCode() == null)) {
			queryRule.addEqual("id", umTAccount.getId());
		}
		
		return umTAccountDao.find(queryRule, pageNo, pageSize);
	}

	/**
	 * 更新UmTAccount信息
	 * @param UmTAccount
	 */
	public void updateUmTAccount(UmTAccount umTAccount) throws Exception{
			
			umTAccountDao.update(umTAccount);
	}

	/**
	 * 更新UmTAccount密码
	 * @param usercode,password
	 */
	public void updateUmTAccount(String userCode,String password) throws Exception{
			
			UmTAccount umTAccount= this.findUmTAccountByUserCode(userCode);
			umTAccount.setPassword(password);
			umTAccountDao.update(umTAccount);
	}

	/**
	 * 保存UmTAccount信息
	 * @param UmTAccount
	 */
	public void saveUmTAccount(UmTAccount umTAccount) throws Exception{
			
			umTAccountDao.save(umTAccount);
	}

	/**
	 * 根据主键对象，删除UmTAccount信息
	 * @param UmTAccountId
	 */
	public void deleteByPK(UmTAccountId id) throws Exception{
			
			umTAccountDao.deleteByPK(UmTAccount.class,id);
	}

	/**
	 * 按规则查找
	 * @param rule
	 * @return
	 * @author xiehui 20140723
	 */
	public List<UmTAccount> findByRule(QueryRule rule) {
		// TODO Auto-generated method stub
		return umTAccountDao.find(rule);
	}

	/**
	 * 验证密码是否符合规范
	 */
	public boolean validatePassword(String password) {
		// TODO Auto-generated method stub
		boolean isValid = true;
		if(password.trim().length() < 6 ) {
			isValid = false;
		}
		return isValid;
	}
}
