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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.QueryRuleHelper;
import com.picc.common.dao.CommonDaoHibernate;
import com.picc.common.dao.SysCommonDaoHibernate;
import com.picc.common.utils.ToolsUtils;
import com.picc.um.dao.UmTAccountDaoHibernate;
import com.picc.um.dao.UmTUserDaoHibernate;
import com.picc.um.schema.model.UmTAccount;
import com.picc.um.schema.model.UmTUser;
import com.picc.um.schema.model.UmTUserId;
import com.picc.um.service.facade.IUmTUserService;

/**
 * @ClassName: UmTUserServiceSpringImpl
 * @Description: TODO 用户管理、我的信息Service层实现类
 * @author dijie
 * @date 2013-12-3
 * 
 */
@Service("umTUserService")
public class UmTUserServiceSpringImpl implements IUmTUserService {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	protected static CacheService cacheManager = CacheManager
			.getInstance("codeSelectCache");

	@Autowired
	private UmTUserDaoHibernate umTUserDao;
	@Autowired
	private UmTAccountDaoHibernate umTAccountDao;

	@Autowired
	private CommonDaoHibernate commonDao;
	
	@Autowired
    private SysCommonDaoHibernate sysCommonDao;

	/**
	 * 根据主键对象UmTUserId获取UmTUser信息
	 * 
	 * @param UmTUserId
	 * @return UmTUser
	 */
	public UmTUser findUmTUserByPK(UmTUserId id) throws Exception {

		return umTUserDao.get(UmTUser.class, id);
	}

	/**
	 * 根据主键对象userCode获取UmTUser信息
	 * 
	 * @param userCode
	 * @return UmTUser
	 */
	public UmTUser findUmTUserByUserCode(String userCode) throws Exception {
		/**
		 * 增加缓存处理
		 * 
		 * @modify by yaowenfeng
		 */

		String key = cacheManager.generateCacheKey("getUserNameByUserCode",
				userCode);
		UmTUser umTUser = null;
		Object result = cacheManager.getCache(key);
		if (result != null) {
			umTUser = (UmTUser) result;
		} else {
			UmTUserId umTUserId = new UmTUserId(userCode);
			umTUser = umTUserDao.get(UmTUser.class, umTUserId);
			cacheManager.putCache(key, umTUser);
		}

		return umTUser;

		/*
		 * String hql = "from UmTUser u where u.id.userCode=?";
		 * System.out.println(hql);
		 */

		/* return (UmTUser) umTUserDao.findByHql(hql, userCode).get(0); */
	}

	/**
	 * 根据主键对象comcode获取UmTUser信息
	 * 
	 * @param userCode
	 * @return UmTUser
	 */
	public List<UmTUser> findUmTUserByComCode(String comCode, String comId)
			throws Exception {

		return umTUserDao.findUserListByComCode(comCode, comId);
	}

	/**
	 * added by Jay 2013-8-21
	 * 
	 * @Title: findUserPageByComCode
	 * @Description: 根据comCode获取userPage
	 * @return Page
	 * @throws
	 */
	public Page findUserPageByComCode(String comCode, String comId, int pageNo,
			int pageSize) {
		String hql = "select ur from UmTUser ur where ur.comCode=? and ur.comId=? and ur.validStatus='1'";
		return umTUserDao.findByHqlNoLimit(hql, pageNo, pageSize, comCode,
				comId);

	}

	/**
	 * added by Jay 2013-10-17
	 * 
	 * @Title: findUserByAccountAndComcode
	 * @Description: 根据机构查询user、account联合表的用户list
	 * @return List<UmTUser>
	 * @throws
	 */
	public List<UmTUser> findUserByAccountAndComcode(String comCode,
			String comId) {
		String hql = "select ur from UmTUser ur,UmTAccount utr where ur.id.userCode=utr.id.userCode and ur.comCode=? and ur.comId=? and utr.comId=? and ur.validStatus='1' and utr.validStatus='1'";
		return umTUserDao.findByHql(hql, comCode, comId, comId);
	}

	/**
	 * @Title: findUserByRole
	 * @Description: 根据角色获取UmTUser列表
	 * @param @param roleId
	 * @return List<UmTUser> 返回类型
	 * @throws
	 */
	public List<UmTUser> findUserByRole(String roleId, String comId) {
		String hql = "select ur from UmTUser ur,UmTUserRole utr where ur.id.userCode=utr.userCode and utr.roleId=? and ur.comId=? and utr.comId=? and ur.validStatus='1'";
		return umTUserDao.findByHql(hql, roleId, comId, comId);
	}

	/**
	 * added by Jay 2013-8-20
	 * 
	 * @Title: findUserByGroup
	 * @Description: 根据流程角色查找用户列表
	 * @return List<UmTUser>
	 * @throws
	 */
	public List<UmTUser> findUserByGroup(String groupId, String comId) {
		String hql = "select ur from UmTUser ur,UmTUserGroup utg where ur.id.userCode=utg.userCode and utg.groupId=? and ur.comId=? and utg.comId=? and ur.validStatus='1'";
		return umTUserDao.findByHql(hql, groupId, comId, comId);
	}

	/**
	 * added by Jay 2013-8-20
	 * 
	 * @Title: findUserPageByGroup
	 * @Description: 根据流程角色查找用户Page
	 * @return Page
	 * @throws
	 */
	public Page findUserPageByGroup(String groupId, int pageNo, int pageSize,
			String comId) {
		String hql = "select ur from UmTUser ur,UmTUserGroup utg where ur.id.userCode=utg.userCode and utg.groupId=? and ur.validStatus='1' and ur.comId=? and utg.comId=? ";
		return umTUserDao.findByHqlNoLimit(hql, pageNo, pageSize, groupId,
				comId, comId);

	}

	/**
	 * @Title: findUserByRoleAndCom
	 * @Description: 根据角色和机构获取UmTUser列表
	 * @param @param roleCode
	 * @return List<UmTUser> 返回类型
	 * @throws
	 */
	public List<UmTUser> findUserByRoleAndCom(String comCode, String roleId,
			String comId) {
		String hql = "select ur from UmTUser ur,UmTUserRole utr where ur.id.userCode=utr.userCode and ur.comCode=? and utr.roleId=? and ur.validStatus='1' and ur.comId=? and utr.comId=? ";
		return umTUserDao.findByHql(hql, comCode, roleId, comId, comId);
	}

	/**
	 * 根据umTUser和页码信息，获取Page对象
	 * 
	 * @param umTUser
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTUser的Page对象
	 */
	public Page findByUmTUser(UmTUser umTUser, int pageNo, int pageSize)
			throws Exception {

		QueryRule queryRule = null;
		queryRule = QueryRuleHelper.generateQueryRule(umTUser);
		if (!("").equals(umTUser.getId().getUserCode())
				&& !(umTUser.getId().getUserCode() == null)) {
			queryRule.addEqual("id.userCode", umTUser.getId().getUserCode()
					.trim());
		}

		return umTUserDao.find(queryRule, pageNo, pageSize);
		// throw new Exception("无法查询数据");
	}
	
	/**
     * 根据umTUser和页码信息，获取Page对象
     * 
     * @param umTUser
     * @param pageNo
     * @param pageSize
     * @return 包含UmTUser的Page对象
     */
    public Page findByUmTUserSec(UmTUser umTUser, int pageNo, int pageSize) throws Exception {
        
        StringBuffer sql = new StringBuffer();
        
        sql.append("        SELECT g. usercode usercode,                                 ");
        sql.append("        g. username userName,                                        ");
        sql.append("        g. comcode comCode,                                          ");
        sql.append("        g. telephone telePhone,                                      ");
        sql.append("        g. faxnumber faxNumber,                                      ");
        sql.append("        g. mobile mobile,                                            ");
        sql.append("        g. email email,                                              ");
        sql.append("        g. postaddress postAddress,                                  ");
        sql.append("        g. unit unit,                                                ");
        sql.append("        g. unitaddress unitAddress,                                  ");
        sql.append("        g. image image,                                              ");
        sql.append("        g. interests interests,                                      ");
        sql.append("        g. creatorcode creatorCode,                                  ");
        sql.append("        g. inserttimeforhis insertTimeForHis,                        ");
        sql.append("        g. updatercode updaterCode,                                  ");
        sql.append("        g. operatetimeforhis operateTimeForHis,                      ");
        sql.append("        g. remark remark,                                            ");
        sql.append("        g. flag flag,                                                ");
        sql.append("        g. usertype userType,                                        ");
        sql.append("        g. usersort userSort,                                        ");
        sql.append("        g. identitynumber identityNumber,                            ");
        sql.append("        g. validstatus validStatus,                                  ");
        sql.append("        g. sex sex,                                                  ");
        sql.append("        g. birthday birthday,                                        ");
        sql.append("        g. age age,                                                  ");
        sql.append("        g. comid comId,                                              ");
        sql.append("        g. bindstatus bindStatus,                                    ");
        sql.append("        g. sourceflag sourceFlag,                                    ");
        sql.append("        g. custid custId,                                            ");
        sql.append("        g. employeeid employeeId,                                    ");
        sql.append("        g. policeteamid policeTeamId,                                ");
        sql.append("        (SELECT a1.teamname                                          ");
        sql.append("           FROM qp_t_tp_team a1                                      ");
        sql.append("          WHERE a1.teamid = g.policeteamid) policeTeamName,          ");
        sql.append("        g. centerid centerId,                                        ");
        sql.append("        (SELECT a2.centername                                        ");
        sql.append("           FROM qp_t_tp_fast_center a2                               ");
        sql.append("          WHERE a2.centerid = g.centerid) centerName,                ");
        sql.append("        g. province province,                                        ");
        sql.append("        (SELECT a3.provname                                          ");
        sql.append("           FROM qp_t_com_province a3                                 ");
        sql.append("          WHERE a3.provid = g.province) provinceDesc,                ");
        sql.append("        g. city city,                                                ");
        sql.append("        (SELECT a4.cityname                                          ");
        sql.append("           FROM qp_t_com_city a4                                     ");
        sql.append("          WHERE a4.cityid = g.city) cityDesc,                        ");
        sql.append("        g. district district,                                        ");
        sql.append("        (SELECT a5.districtname                                      ");
        sql.append("           FROM qp_t_com_district a5                                 ");
        sql.append("          WHERE a5.districtid = g.district) districtDesc,            ");
        sql.append("        g. road road,                                                ");
        sql.append("        (SELECT a5.roadname                                          ");
        sql.append("           FROM qp_t_com_road a5                                     ");
        sql.append("          WHERE a5.roadid = g.road) roadDesc,                        ");
        sql.append("        g. street street,                                             ");
        
        sql.append("        g. coId coId,                                                ");
        sql.append("        (SELECT a6.coName                                          ");
        sql.append("           FROM qp_t_ic_company a6                                     ");
        sql.append("          WHERE a6.coId = g.coId) coName                        ");
        sql.append("   FROM um_t_user g where 1=1                                        ");
        
        // 用户代码
        if(ToolsUtils.notEmpty(umTUser.getId().getUserCode())) {
            sql.append(" AND g.usercode = '").append(umTUser.getId().getUserCode()).append("' ");
        }
        
        // 用户名称
        if(ToolsUtils.notEmpty(umTUser.getUserName())) {
            sql.append(" AND g.username = '").append(umTUser.getUserName()).append("' ");
        }
        
        // 手机号
        if(ToolsUtils.notEmpty(umTUser.getMobile())) {
            sql.append(" AND g.mobile = '").append(umTUser.getMobile()).append("' ");
        }
        
        // 身份证号
        if(ToolsUtils.notEmpty(umTUser.getIdentityNumber())) {
            sql.append(" AND g.identitynumber = '").append(umTUser.getIdentityNumber()).append("' ");
        }
        
        // 用户类型
        if(ToolsUtils.notEmpty(umTUser.getUserType()) && !"0".equals(umTUser.getUserType())) {
            sql.append(" AND g.usertype = '").append(umTUser.getUserType()).append("' ");
        }
        
        // 用户分类
        if(ToolsUtils.notEmpty(umTUser.getUserSort()) && !"0".equals(umTUser.getUserSort())) {
            sql.append(" AND g.usersort = '").append(umTUser.getUserSort()).append("' ");
        }
        
        // 所属交警大队
        if(ToolsUtils.notEmpty(umTUser.getPoliceTeamId()) && !"0".equals(umTUser.getPoliceTeamId())) {
            sql.append(" AND g.policeteamid = '").append(umTUser.getPoliceTeamId()).append("' ");
        }
        
        // 所属受理点
        if(ToolsUtils.notEmpty(umTUser.getCenterId()) && !"0".equals(umTUser.getCenterId())) {
            sql.append(" AND g.centerid = '").append(umTUser.getCenterId()).append("' ");
        }
        
        // 所属保险公司
        if(ToolsUtils.notEmpty(umTUser.getCoId()) && !"0".equals(umTUser.getCoId())) {
            sql.append(" AND g.coId = '").append(umTUser.getCoId()).append("' ");
        }

        // 有效标志
        if(ToolsUtils.notEmpty(umTUser.getValidStatus())) {
            sql.append(" AND g.validstatus = '").append(umTUser.getValidStatus()).append("' ");
        }
        // 机构代码
        if(ToolsUtils.notEmpty(umTUser.getComId())) {
        	sql.append(" AND g.comid = '").append(umTUser.getComId()).append("' ");
        }
        // 市
        if(ToolsUtils.notEmpty(umTUser.getCity())) {
        	sql.append(" AND g.city = '").append(umTUser.getCity()).append("' ");
        }
        
        return  sysCommonDao.findBySql(UmTUser.class,sql.toString(),  pageNo, pageSize, null);
    }

	/**
	 * 更新UmTUser信息
	 * 
	 * @param UmTUserVo
	 */
	public void updateUmTUser(UmTUser umTUser) throws Exception {

		umTUserDao.update(umTUser);
	}

	/**
	 * 保存UmTUser信息
	 * 
	 * @param UmTUserVo
	 */
	public void saveUmTUser(UmTUser umTUser) throws Exception {

		umTUserDao.save(umTUser);
	}

	/**
	 * 保存UmTUser、umTAccount信息
	 * 
	 * @param UmTUserVo
	 */
	public void saveUmTUserAndAccount(UmTUser umTUser, UmTAccount umTAccount)
			throws Exception {

		umTUserDao.save(umTUser);
		umTAccountDao.save(umTAccount);
	}

	/**
	 * 根据主键对象，删除UmTUser信息
	 * 
	 * @param UmTUserId
	 */
	public void deleteByPK(UmTUserId id) throws Exception {

		umTUserDao.deleteByPK(UmTUser.class, id);
	}

	public UmTUser recognizedUser(UmTUser umtUser) throws Exception {
		try {
			String idnum = umtUser.getIdentityNumber();
			String name = umtUser.getUserName();
			String phone = umtUser.getMobile();
			String usercode = commonDao.generateID("RU", "UM_SEQ_UMTUSER", 10);
			String comid = "44030000";
			String findsql = "select * from um_t_user where username =? and identitynumber=? and validstatus = '1' ";
			// String sql=
			// "INSERT INTO um_t_userhistory(username,comcode,telephone,faxnumber,mobile,email,postaddress,"
			// +
			// "unit,unitaddress,image,interests,creatorcode,identitynumber,telephone) VALUES ('"+name+"','"+idnum+"',"+phone+")";
			String insertsql = "insert into um_t_userhistory select * from um_t_user where usercode=? ";
			String updatesql = "update um_t_user a set a.mobile = ? where a.usercode = ? ";
			String updatesql2 = "update um_t_user a set a.identitynumber = ? where a.usercode = ? ";
			List params = new ArrayList();
			params.add(name);
			params.add(idnum);
			List<UmTUser> userList = (List<UmTUser>) commonDao.findBySql(
					findsql, UmTUser.class, params.toArray());
			// 根据身份证号和姓名查询为空时
			if (userList.isEmpty()) {
				String findsql2 = "select * from um_t_user where username =? and mobile=? and validstatus = '1' ";
				List a = new ArrayList();
				a.add(name);
				a.add(phone);
				List<UmTUser> userList2 = (List<UmTUser>) commonDao.findBySql(
						findsql2, UmTUser.class, a.toArray());
				// 根据手机号和姓名查询为空时
				if (userList2.isEmpty()) {
					UmTUserId id = new UmTUserId();
					id.setUserCode(usercode);
					umtUser.setId(id);// 保存usercode
					umtUser.setComId(comid);
					umtUser.setOperateTimeForHis(new Date());// 保存更新时间
					umTUserDao.save(umtUser);
					umtUser.setUsercode(usercode);//加入usercode信息
					return umtUser;
				} else {
					for (int y = 0; y < userList2.size(); y++) {
						UmTUser userTmp2 = userList2.get(y);
						// 比较原身份证号和现身份证号是否一样
						if (idnum.equals(userTmp2.getIdentityNumber())) {
							return umtUser;
						} else {
							List d = new ArrayList();
							d.add(userTmp2.getUsercode());
							commonDao.execute(insertsql, d.toArray());
							List e = new ArrayList();
							e.add(idnum);
							e.add(userTmp2.getUsercode());
							commonDao.execute(updatesql2, e.toArray());
							umtUser.setIdentityNumber(idnum);
							return umtUser;
						}
					}
					
				}
			} else {
				for (int i = 0; i < userList.size(); i++) {
					UmTUser userTmp = userList.get(i);
					// 比较原手机号和现手机号是否一样
					if (phone.equals(userTmp.getMobile())) {
						return userTmp;
					} else {
						List b = new ArrayList();
						b.add(userTmp.getUsercode());
						commonDao.execute(insertsql, b.toArray());
						List c = new ArrayList();
						c.add(phone);
						c.add(userTmp.getUsercode());
						commonDao.execute(updatesql, c.toArray());
						umtUser.setMobile(phone);
						return umtUser;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}
		return null;
	}
	
	public UmTUser findUserMcbc(String piid) throws Exception {
		StringBuffer sql =new StringBuffer();
		sql.append("select * from um_t_user a where exists( ")
		.append("select 1 from wx_t_main b where a.usercode=b.usercode ")
		.append("and exists(select 1 from Wx_t_Mainextmcbc c where b.quotationno=c.quotationno ")
				.append("and c.piid=?) and exists(select 1 from wx_t_risk d where b.quotationno=d.quotationno ")
						.append("and d.riskcode='mcbc') )");
		List<UmTUser> list =(List<UmTUser>) commonDao.findBySql(sql.toString(), UmTUser.class, new Object[]{piid});
		if(ToolsUtils.notEmpty(list)){
			return list.get(0);
		}
		return null;
	}

	public UmTUser findUserRecognized(String userName, String identityNumber ,
			String mobile) throws Exception {
		// TODO Auto-generated method stub
		
		String sql_i = "select * from um_t_user where username =? and identitynumber=? and validstatus = '1' ";
		List<UmTUser> list_i =(List<UmTUser>) 
				commonDao.findBySql(sql_i.toString(), UmTUser.class, new Object[]{userName, identityNumber});
		if(ToolsUtils.notEmpty(list_i)){
			return list_i.get(0);
		}
		
		String sql_m = "select * from um_t_user where username =? and mobile=? and validstatus = '1' ";
		List<UmTUser> list_m = (List<UmTUser>) 
				commonDao.findBySql(sql_m.toString(), UmTUser.class, new Object[]{userName, mobile});
		if(ToolsUtils.notEmpty(list_m)){
			return list_m.get(0);
		}
		
		return null;
	}

	public String generateUsercode() throws Exception {
		// TODO Auto-generated method stub
		return commonDao.generateID("RU", "UM_SEQ_UMTUSER", 10);
	}

	public List<UmTUser> findUserListByRule(QueryRule rule) throws Exception {
		// TODO Auto-generated method stub
		return umTUserDao.find(rule);
	}
}
