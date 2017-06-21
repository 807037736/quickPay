/*
 * Powered By diyvan(yaowenfeng)
 * Email: yaowenfeng@shenz.picc.com.cn
 * Since 2013 - 2014
 */

package com.picc.um.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.common.dao.CommonDaoHibernate;
import com.picc.common.utils.CodeUtil;
import com.picc.common.utils.SqlUtils;
import com.picc.common.utils.ToolsUtils;
import com.picc.um.dao.UmTUserBindDaoHibernate;
import com.picc.um.schema.model.UmTUserBind;
import com.picc.um.schema.model.UmTUserBindId;
import com.picc.um.schema.model.UmTUserRelation;
import com.picc.um.schema.vo.SessionUser;
import com.picc.um.schema.vo.UmTUserBindVo;
import com.picc.um.schema.vo.UmTWxUser;
import com.picc.um.service.facade.IUmTUserBindService;


@Service("umTUserBindService")
public class UmTUserBindServiceSpringImpl implements IUmTUserBindService{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private UmTUserBindDaoHibernate umTUserBindDao;
//	@Autowired
//	private UmTUserRelationDaoHibernate umTUserRelationDao;

	
	
	@Autowired
	private CommonDaoHibernate commonDao;
	
	


	/**
	 * 根据主键对象UmTUserBindId获取UmTUserBind信息
	 * @param UmTUserBindId
	 * @return UmTUserBind
	 */
	public UmTUserBind findUmTUserBindByPK(UmTUserBindId id) throws Exception{
			return umTUserBindDao.get(UmTUserBind.class,id);
	}

	/**
	 * 根据umTUserBind和页码信息，获取Page对象
	 * @param umTUserBind
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTUserBind的Page对象
	 */
	public Page findByUmTUserBind(UmTUserBind umTUserBind, int pageNo, int pageSize) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
	
		//根据umTUserBind生成queryRule
		
		return umTUserBindDao.find(queryRule, pageNo, pageSize);
	}
	/**
	 * 根据usercode获取bind
	 * @param umTUserBind
	 * @param pageNo
	 * @param pageSize
	 * @return 包含UmTUserBind的Page对象
	 */
	public UmTUserBind findListByUserCode(String usercode) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();// 获取QueryRule对象的Instance
		queryRule.addEqual("userCode", usercode);
		queryRule.addEqual("validStatus", "1");
		List<UmTUserBind> list = umTUserBindDao.find(queryRule);
		if(ToolsUtils.isEmpty(list)){
			return null;
		}else{
			return list.get(0);
		}
	}

	/**
	 * 更新UmTUserBind信息
	 * @param UmTUserBind
	 */
	public void updateUmTUserBind(UmTUserBind umTUserBind) throws Exception{
			umTUserBindDao.update(umTUserBind);
	}

	/**
	 * 保存UmTUserBind信息
	 * @param UmTUserBind
	 */
	public void saveUmTUserBind(UmTUserBind umTUserBind) throws Exception{
			umTUserBindDao.save(umTUserBind);
	}

	/**
	 * 根据主键对象，删除UmTUserBind信息
	 * @param UmTUserBindId
	 */
	public void deleteByPK(UmTUserBindId id) throws Exception{
			umTUserBindDao.deleteByPK(UmTUserBind.class,id);
	}
	/**
	 * 根据主键对象，删除UmTUserBind信息
	 * @param UmTUserBindId
	 */
	public void deleteByParam(String platid, String openid) throws Exception{
		String sql = "delete from um_t_userbind where " +
				"usercode in(select usercode from um_t_userrelation where 1=1";
		sql += SqlUtils.convertString("platid", platid);
		sql += SqlUtils.convertString("userid", openid);
		sql += ")";
		commonDao.execute(sql, null);
	}
	/**
	 * 根据微信号查询客户id
	 * @date 2014年12月4日
	 * @user juzongyi
	 * @param platid
	 * @param openid
	 * @return
	 * @throws Exception
	 */
	public List<UmTUserBindVo> findCustIdByOpenid(String platid, String openid)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select a.userCode,a.userName,b.bindValue,b.custId,a.platId,a.userId,a.mobileNo" +
				" from um_t_userrelation a  left outer join um_t_userbind b on  a.usercode=b.usercode" +
				" where 1=1 ")
				.append(SqlUtils.convertString("a.platid", platid))
				.append(SqlUtils.convertString("a.userid", openid));

		List<UmTUserBindVo> list = (List<UmTUserBindVo>) commonDao.findListBySql(
				sql.toString(), UmTUserBindVo.class, null);
		return list;
	}
	
	/**
	 * 根据微信号查询客户id
	 * @date 2015年12月10日
	 * @param platid
	 * @param openid
	 * @return
	 * @throws Exception
	 */
	public List<UmTUserBindVo> findRegistUserByOpenid(String platid, String openid) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select a.userCode,a.userName,a.platId,a.userId,a.mobileNo,u.postaddress," +
		        " u.identityNumber,u.licenseNo " +
				" from um_t_user u, um_t_userrelation a " +
				" where u.usercode = a.usercode ")
				.append(SqlUtils.convertString("a.platid", platid))
				.append(SqlUtils.convertString("a.userid", openid));

		List<UmTUserBindVo> list = (List<UmTUserBindVo>) commonDao.findListBySql(
				sql.toString(), UmTUserBindVo.class, null);
		return list;
	}
	
//	public List<UmTUserBind> findCustIdByOpenid(String platid, String openid)
//			throws Exception {
//		StringBuffer sql = new StringBuffer();
//		sql.append(
//				"select a.* from um_t_userbind a,um_t_userrelation b ")
//				.append("where a.usercode=b.usercode and a.VALIDSTATUS='1' ")
//				.append(SqlUtils.convertString("b.platid", platid))
//				.append(SqlUtils.convertString("b.userid", openid));
//		
//		List<UmTUserBind> list = (List<UmTUserBind>) commonDao.findListBySql(
//				sql.toString(), UmTUserBind.class, null);
//		return list;
//	}
	
	public UmTUserBind fdBindCust(Map paramMap) throws Exception {
		UmTUserBind umTUserBind = null;
		UmTUserBindId umTUserBindId = new UmTUserBindId();
		SessionUser sessionUser = null;
		String customerName = null;
		String bindValue = null;
		String userCode = "10061657";
		String mobileno = "";
		String licenseno = "";
		String resultJsonStr = null;	//识别的接口返回的接口
		String recognizedStatus = null;	//识别的接口body
		
		if(paramMap != null && paramMap.get("umTUserBind") != null){
			umTUserBind = (UmTUserBind)paramMap.get("umTUserBind");
		}
		if(paramMap != null && paramMap.get("mobileno") != null){
			mobileno = paramMap.get("mobileno").toString();
		}
//		if(paramMap != null && paramMap.get("licenseno") != null){
//			licenseno = paramMap.get("licenseno").toString();
//		}
		if(paramMap != null && paramMap.get("sessionUser") != null){
			sessionUser = (SessionUser)paramMap.get("sessionUser");
		}
		if(umTUserBind!=null){
			customerName = umTUserBind.getCustomerName();
			bindValue = umTUserBind.getBindValue();
			licenseno = umTUserBind.getLicenseNo();
//			userCode = umTUserBind.getUserCode();
		}
//		if(sessionUser != null){
//			userCode = sessionUser.getUserCode();
//		}
		//设定默认的绑定方式:身份证
		umTUserBindId.setBindId(commonDao.generateID("UMB","UM_SEQ_BIND", 27));
		umTUserBind.setId(umTUserBindId);
		umTUserBind.setBindType("身份证");
//		umTUserBind.setBindValue(bindValue);
		umTUserBind.setValidStatus("1");
		umTUserBind.setCreatorCode(userCode);
		umTUserBind.setBindTime(new Date());
		umTUserBind.setUpdaterCode(userCode);
//		umTUserBind.setLicenseNo(licenseno);
//		umTUserBind.setUserCode(userCode);
		
		StringBuffer customerJson = new StringBuffer();
		customerJson.append("{'head':{userCode:'10061657',password:'111',sourceType:'weixinBangding'},body:[");
		customerJson.append("{'insuredName':'").append(customerName)
		.append("','reportPhone':'").append(mobileno)
		.append("','identifyNumber':'").append(bindValue)
		.append("','licenseNo':'").append(licenseno)
		.append("','userCode':'").append(userCode)
		.append("'}");
		customerJson.append("]}");
		//调用统一加入活动的方法：客户识别，加入活动
		//resultJsonStr = interactiveHKYXService.recognizedCustomer(customerJson.toString());
		//响应报文解析
		JSONObject jsonResponse = JSONObject.fromObject(resultJsonStr);
		JSONArray obbody=(JSONArray)jsonResponse.get("body");
		JSONObject bodycustID =(JSONObject)obbody.get(0);
		String custId=(String) bodycustID.get("custId");
		String resultCode = jsonResponse.getJSONObject("head").getString("resultCode");
		
//		 String customerJson = "{'head':{userCode:'',password:''},'body':[{'customerCName':'" +customerName
//									+"','identifyNumber':'" + bindValue + "'}]}";
//		
//		HashMap ruleParamMap = new HashMap();
//		ruleParamMap.put("comcode", "44030000");
//		ruleParamMap.put("interfaceName", "khyx_recognizeCustomer");
//		resultJsonStr = interfaceService.callKHYXCOMMONInterface(ruleParamMap, customerJson); 
//		
//		//接口返回结果:{"head":{"resultCode":"02","resultDesc":"该客户不存在或不在您名下"},"body":[{"comId":"","custId":"","monopolyCode":"","owner":""}]}
//		 if(resultJsonStr!= null){
//			JSONObject jsonObject = JSONObject.fromObject(resultJsonStr);
//			recognizedStatus = jsonObject.getJSONObject("body").getString("resultCode");
//		}
//		if(recognizedStatus!=null){
//			
//		} 
		//去khyx查询绑定客户
//		StringBuffer sql = new StringBuffer();
//		sql.append("select a.custid from mc_t_custmain a where a.customercname = '").append(customerName).append("' ")
//			.append("and exists (select 1 from mc_t_baseidcard b where a.custid = b.custid ")
//            .append("and b.identifytype = '01' and b.identifynumber = '")
//            .append(bindValue).append("' and b.validstatus = '1') ")
//            .append("and exists (select 1 from mc_t_tradpolicy t  where a.custid = t.custid ")
//            .append("and t.validstatus = '1' and t.systemflag = '1') ");
//		//去khyx看是否可以匹配到已保的客户
//		List bindResultList = khyxCommonDao.findListBySql(sql.toString(), UmTUserBind.class, null);
//		
		//现根据customerName和bindValue去查询是否已经绑定成功
		StringBuffer hasExists = new StringBuffer();
		hasExists.append("select * from um_t_userbind a where a.validstatus = '1' ")
					.append(SqlUtils.convertString("a.customername", customerName))
					.append(SqlUtils.convertString("a.bindvalue", bindValue));
		
		List hasBind = commonDao.findListBySql(hasExists.toString(), UmTUserBind.class, null);
		
		//分情况返回结果
		UmTUserBind u = null;
		if(hasBind != null && hasBind.size()>0){	//曾经有过绑定
			boolean bindSuccess = false;
			for (int i = 0; i < hasBind.size(); i ++){	//先判断是否成功过
				u = (UmTUserBind)hasBind.get(0);
				if("01".equals(u.getBindResult())){		
					bindSuccess = true;
					umTUserBind.setReturnPageBindResult("hasBindSuccess");
				}
			}
			//曾经绑定没有绑定成功过继续进行绑定
			if(!bindSuccess){
				if(!CodeUtil.nullOrBlank(custId)){	//本次成功绑定
					umTUserBind.setBindResult("01");
					umTUserBind.setCustId(custId);
					umTUserBind.setReturnPageBindResult("success");
					umTUserBindDao.save(umTUserBind);
				}else{						//本次不成功绑定,但是要记录轨迹
					umTUserBind.setReturnPageBindResult(resultCode);
					umTUserBindDao.save(umTUserBind);
				}
			}
		}else{							//曾未绑定过				
			if(!CodeUtil.nullOrBlank(custId)){	//本次成功绑定
				umTUserBind.setBindResult("01");
				umTUserBind.setCustId(custId);
				umTUserBind.setReturnPageBindResult("success");
				umTUserBindDao.save(umTUserBind);
			}else{						//本次不成功绑定,但是要记录轨迹
				umTUserBind.setReturnPageBindResult(resultCode);
				umTUserBindDao.save(umTUserBind);
			}
		}
		return umTUserBind;
	}
	
	/**
	 * update:是否需要重新交互获取custid
	 */
	public UmTUserBind savBindCust(UmTUserRelation userRelation, UmTUserBind userBind ) throws Exception {
		
		
		String customerName = null;
		String bindValue = null;
//		String userCode = "";
		if(userBind!=null){
			customerName = userBind.getCustomerName();
			bindValue = userBind.getBindValue();
//			userCode = userBind.getUserCode();
		}
		
		String mobileno = userRelation.getMobileNo();
		String licenseno = userBind.getLicenseNo();
		String resultJsonStr = null;	//识别的接口返回的接口
		UmTUserBindId umTUserBindId = new UmTUserBindId();
		StringBuffer customerJson = new StringBuffer();
		customerJson.append("{'head':{userCode:'10061657',password:'111',sourceType:'weixinBangding'},body:[");
		customerJson.append("{'insuredName':'").append(customerName)
		.append("','reportPhone':'").append(mobileno)
		.append("','identifyNumber':'").append(bindValue)
		.append("','licenseNo':'").append(licenseno)
		.append("','userCode':'10061657")
		.append("'}");
		customerJson.append("]}");
		//调用统一加入活动的方法：客户识别，加入活动
		//resultJsonStr = interactiveHKYXService.recognizedCustomer(customerJson.toString());
		//响应报文解析
		JSONObject jsonResponse = JSONObject.fromObject(resultJsonStr);
		JSONArray obbody=(JSONArray)jsonResponse.get("body");
		JSONObject bodycustID =(JSONObject)obbody.get(0);
		String custId=(String) bodycustID.get("custId");
		String resultCode = jsonResponse.getJSONObject("head").getString("resultCode");
		userBind.setReturnPageBindResult(resultCode);
		userBind.setCustId(custId);
		userBind.setBindResult("01");
		//是否返回custid
		if(userBind.getId()==null){
			umTUserBindId.setBindId(commonDao.generateID("UMB","UM_SEQ_BIND", 27));
			userBind.setId(umTUserBindId);
			umTUserBindDao.save(userBind);
		}else{
			umTUserBindDao.update(userBind);
		}
//		
//		//现根据customerName和bindValue去查询是否已经绑定成功
//		StringBuffer hasExists = new StringBuffer();
//		hasExists.append("select * from um_t_userbind a where a.validstatus = '1' ")
//		.append(SqlUtils.convertString("a.customername", customerName))
//		.append(SqlUtils.convertString("a.bindvalue", bindValue));
//		
//		List hasBind = commonDao.findListBySql(hasExists.toString(), UmTUserBind.class, null);
//		
//		//分情况返回结果
//		UmTUserBind u = null;
//		if(hasBind != null && hasBind.size()>0){	//曾经有过绑定
//			boolean bindSuccess = false;
//			for (int i = 0; i < hasBind.size(); i ++){	//先判断是否成功过
//				u = (UmTUserBind)hasBind.get(0);
//				if("01".equals(u.getBindResult())){		
//					bindSuccess = true;
//					umTUserBind.setReturnPageBindResult("hasBindSuccess");
//				}
//			}
//			//曾经绑定没有绑定成功过继续进行绑定
//			if(!bindSuccess){
//				if(!CodeUtil.nullOrBlank(custId)){	//本次成功绑定
//					umTUserBind.setBindResult("01");
//					umTUserBind.setCustId(custId);
//					umTUserBind.setReturnPageBindResult("success");
//					umTUserBindDao.save(umTUserBind);
//				}else{						//本次不成功绑定,但是要记录轨迹
//					umTUserBind.setReturnPageBindResult(resultCode);
//					umTUserBindDao.save(umTUserBind);
//				}
//			}
//		}else{							//曾未绑定过				
//			if(!CodeUtil.nullOrBlank(custId)){	//本次成功绑定
//				umTUserBind.setBindResult("01");
//				umTUserBind.setCustId(custId);
//				umTUserBind.setReturnPageBindResult("success");
//				umTUserBindDao.save(umTUserBind);
//			}else{						//本次不成功绑定,但是要记录轨迹
//				umTUserBind.setReturnPageBindResult(resultCode);
//				umTUserBindDao.save(umTUserBind);
//			}
//		}
		return userBind;
	}
	
	/**
	 * 根据openid查询客户简要信息
	 */
	@SuppressWarnings("unchecked")
	public UmTWxUser  findCustInfoByOpenid(String platid, String openid)
			throws Exception {
		String sql1 = "select nickName,headImgUrl headImg from WEIXIN_GZUSERINFO@TO_37 where openid='"+openid+"'";
		String sql2 = "select count(*) msgCount from  wx_message where userid='"+openid+"' and msgtype=1";
		UmTWxUser wxUser = new UmTWxUser();
		List<UmTWxUser> result1 = (List<UmTWxUser>)commonDao.findBySql(sql1, UmTWxUser.class, null);
		List<UmTWxUser> result2 = (List<UmTWxUser>)commonDao.findBySql(sql2, UmTWxUser.class, null);
		if(!ToolsUtils.isEmpty(result1)){
			wxUser.setHeadImg(result1.get(0).getHeadImg());
			wxUser.setNickName(result1.get(0).getNickName());
		}else{
			wxUser.setHeadImg("http://imgsrc.baidu.com/forum/pic/item/e0f950c2d56285353026f15d93ef76c6a6ef6335.jpg");
			wxUser.setNickName("未注册");
		}
		if(!ToolsUtils.isEmpty(result2)){
			wxUser.setMsgCount(result2.get(0).getMsgCount());
		}else{
			wxUser.setMsgCount(BigDecimal.valueOf(0));
		}
		return wxUser;
	}
	
	/**
	 * 根据手机号码查询个人信息
	 * @date 2016年7月10日
	 * @param mobile
	 * @return List<UmTUserBindVo>
	 * @throws Exception
	 */
	public List<UmTUserBindVo> findRegistUserByMobile(String mobile) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select a.userCode,a.userName,a.platId,a.userId,a.mobileNo,u.postaddress," +
		        " u.identityNumber,u.licenseNo " +
				" from um_t_user u, um_t_userrelation a " +
				" where u.usercode = a.usercode ")
				.append(SqlUtils.convertString("u.mobile", mobile));

		List<UmTUserBindVo> list = (List<UmTUserBindVo>) commonDao.findListBySql(
				sql.toString(), UmTUserBindVo.class, null);
		return list;
	}
}
