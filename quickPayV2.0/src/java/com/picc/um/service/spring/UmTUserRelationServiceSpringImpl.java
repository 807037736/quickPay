/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2013
 */

package com.picc.um.service.spring;

import ins.framework.cache.CacheManager;
import ins.framework.cache.CacheService;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.dao.CommonDaoHibernate;
import com.picc.common.utils.ToolsUtils;
import com.picc.um.dao.UmTUserRelationDaoHibernate;
import com.picc.um.schema.model.UmTUser;
import com.picc.um.schema.model.UmTUserRelation;
import com.picc.um.schema.model.UmTUserRelationId;
import com.picc.um.service.facade.IUmTUserRelationService;




/** 
* @ClassName: UmTUserServiceSpringImpl 
* @Description: TODO 用户管理、我的信息Service层实现类
* @author dijie
* @date 2013-12-3 
*  
*/
@Service("umTUserRelationService")
public class UmTUserRelationServiceSpringImpl implements IUmTUserRelationService{
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	protected static CacheService cacheManager = CacheManager.getInstance("codeSelectCache");
	
	@Autowired
	private UmTUserRelationDaoHibernate umTUserRelationDao;
	@Autowired
	private CommonDaoHibernate commonDao;
	/**
	 * 根据主键对象UmTUserId获取UmTUser信息
	 * @param UmTUserId
	 * @return UmTUser
	 */
	public UmTUserRelation findUmTUserRelationByPK(UmTUserRelationId id) throws Exception{
			
			return umTUserRelationDao.get(UmTUserRelation.class,id);
	}
	/**
	 * 根据主键对象userCode获取UmTUserRelation信息
	 * @param userCode
	 * @return UmTUser
	 */
	public UmTUserRelation findUmTUserRelationByUserId(String openid,String userid) throws Exception{
			/**
			 * 增加缓存处理
			 * @modify by yaowenfeng
			 */
		
			String key = cacheManager.generateCacheKey("getUserRelationNameByUserId", openid+userid);
			UmTUserRelation umTUserRelation = null;
			Object result = cacheManager.getCache(key);
			if (result != null) {
				umTUserRelation = (UmTUserRelation) result;
			}else{
				UmTUserRelationId umTUserRelationId = new UmTUserRelationId(openid,userid);  
				umTUserRelation = umTUserRelationDao.get(UmTUserRelation.class,umTUserRelationId);
				cacheManager.putCache(key, umTUserRelation);
			}
			
			return umTUserRelation;
			
			/*String hql = "from UmTUser u where u.id.userCode=?";
			System.out.println(hql);*/
			
			/*return (UmTUser) umTUserDao.findByHql(hql, userCode).get(0);*/
	}
	/**
	 * 根据主键对象userCode获取UmTUserRelation信息
	 * @param userCode
	 * @return UmTUser
	 */
	public List<UmTUserRelation> findUmTUserRelationByUsercode(String usercode)
			throws Exception {
		String hql = "select ur from UmTUserRelation ur where usercode=? and validstatus='1' ";
		return umTUserRelationDao.findByHql(hql, usercode);
	}
	 
	public Page findByUmTUserRelation(UmTUserRelation umTUserRelation,
			int pageNo, int pageSize) throws Exception {
		QueryRule queryRule = null;
		if(umTUserRelation==null){
			return null;
		}else {
//			queryRule = QueryRuleHelper.generateQueryRule(umTUserRelation);
			queryRule = QueryRule.getInstance();
			if (!("").equals(umTUserRelation.getUserCode())
					&& !(umTUserRelation.getUserCode() == null)) {
				queryRule.addEqual("userCode", umTUserRelation.getUserCode().trim());
			}
			if (!("").equals(umTUserRelation.getUserName())
					&& !(umTUserRelation.getUserName() == null)) {
				queryRule.addEqual("userName", umTUserRelation.getUserName().trim());
			}
			if (!("").equals(umTUserRelation.getValidStatus())
					&& !(umTUserRelation.getValidStatus() == null)) {
				queryRule.addEqual("validStatus", umTUserRelation.getValidStatus().trim());
			}
			if (!("").equals(umTUserRelation.getComCode())
					&& !(umTUserRelation.getComCode() == null)) {
				queryRule.addLike("comCode",umTUserRelation.getComCode().trim());
			}
			
		}
		return umTUserRelationDao.find(queryRule, pageNo, pageSize);
	}
	public void updateUmTUserRelation(UmTUserRelation umTUserRelation)
			throws Exception {
		umTUserRelationDao.update(umTUserRelation);
		// TODO Auto-generated method stub
		
	}
	public void saveUmTUserRelation(UmTUserRelation umTUserRelation)
			throws Exception {
		umTUserRelationDao.save(umTUserRelation);
		
	}
	public void deleteByPK(UmTUserRelationId id) throws Exception {
		// TODO Auto-generated method stub
		umTUserRelationDao.deleteByPK(UmTUserRelationId.class,id);
	}
	/**
	 * 校验内部员工号
	 * @param umTUserRelation
	 * @return
	 * @throws Exception
	 */
	public Map validInnerUser(UmTUserRelation umTUserRelation) throws Exception{
		Map map = new HashMap();
		StringBuffer userSql= new StringBuffer("select * from um_t_user a where a.usercode =? and a.identitynumber =? and a.validstatus ='1'");
		List<String> params =new ArrayList<String>();
		params.add(umTUserRelation.getUserCode());
		params.add(umTUserRelation.getIdentityNum());
		List<UmTUser> list =(List<UmTUser>) commonDao.findBySql(userSql.toString(), UmTUser.class, params.toArray());
		if(ToolsUtils.isEmpty(list)){
			map.put("resultCode", "00");
			map.put("result", "不存在用户");
			return map;
		}else{
			UmTUser user =list.get(0);
			if(user!=null){
				umTUserRelation.setUserName(user.getUserName());
				umTUserRelation.setComCode(user.getComCode());
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	String currentDate = ToolsUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss");
			Date insertTimeForHis = format.parse(currentDate);//获取当前时间
			umTUserRelation.setValidStatus("1");
			UmTUserRelationId id = new UmTUserRelationId();
			id.setPlatId("gh_c41ebb73a072");
			id.setUserId(umTUserRelation.getUserId());
			umTUserRelation.setId(id);
			umTUserRelation.setInsertTimeForHis(insertTimeForHis);
			umTUserRelation.setToolType("innerWX");
			commonDao.save(umTUserRelation);
			map.put("resultCode", "01");
			map.put("result", "保存成功");
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UmTUser> findUserRelationByOpenID(String openid) throws Exception {
		StringBuffer SQL = new StringBuffer("select u.* from um_t_user u, um_t_userrelation r where r.userid = ? AND u.usercode = r.usercode ORDER BY r.inserttimeforhis DESC limit 1");
		List<String> params =new ArrayList<String>();
		params.add(openid);
		List<UmTUser> list =(List<UmTUser>) commonDao.findBySql(SQL.toString(), UmTUser.class, params.toArray());
		return list;
	}
	
	public List<UmTUserRelation> getUmTUserRelationByUserId(String userId)throws Exception {
		String hql = "select ur from UmTUserRelation ur where userId=?";
		return umTUserRelationDao.findByHql(hql, userId);
	}
	
}
