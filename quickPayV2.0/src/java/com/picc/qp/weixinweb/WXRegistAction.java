package com.picc.qp.weixinweb;

import ins.framework.web.Struts2Action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.picc.common.utils.ToolsUtils;
import com.picc.qp.util.SmsUtil;
import com.picc.um.schema.model.UmTRegistuser;
import com.picc.um.schema.model.UmTUserRelation;
import com.picc.um.schema.vo.UmTUserBindVo;
import com.picc.um.service.facade.IUmTRegistuserService;
import com.picc.um.service.facade.IUmTUserBindService;
import com.picc.um.service.facade.IUmTUserRelationService;

@SuppressWarnings("serial")
public class WXRegistAction extends Struts2Action {
	private IUmTUserBindService umTUserBindService;
	private IUmTRegistuserService umTRegistuserService;
	private UmTRegistuser umTRegistuser;
	private UmTUserBindVo umTUserBindVo;
	private IUmTUserRelationService umTUserRelationService;

	private String param;
	private String mobile;
	private String licenseNo;
	private String identityNumber;
	private String userName;
	private String yzm;
	private String operateType;
	private String userCode;
	private String postAddress;

	public IUmTUserRelationService getUmTUserRelationService() {
		return umTUserRelationService;
	}

	public void setUmTUserRelationService(
			IUmTUserRelationService umTUserRelationService) {
		this.umTUserRelationService = umTUserRelationService;
	}

	public IUmTUserBindService getUmTUserBindService() {
		return umTUserBindService;
	}

	public void setUmTUserBindService(IUmTUserBindService umTUserBindService) {
		this.umTUserBindService = umTUserBindService;
	}

	public IUmTRegistuserService getUmTRegistuserService() {
		return umTRegistuserService;
	}

	public void setUmTRegistuserService(
			IUmTRegistuserService umTRegistuserService) {
		this.umTRegistuserService = umTRegistuserService;
	}

	public UmTRegistuser getUmTRegistuser() {
		return umTRegistuser;
	}

	public void setUmTRegistuser(UmTRegistuser umTRegistuser) {
		this.umTRegistuser = umTRegistuser;
	}

	public UmTUserBindVo getUmTUserBindVo() {
		return umTUserBindVo;
	}

	public void setUmTUserBindVo(UmTUserBindVo umTUserBindVo) {
		this.umTUserBindVo = umTUserBindVo;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getYzm() {
		return yzm;
	}

	public void setYzm(String yzm) {
		this.yzm = yzm;
	}
	
	public String getPostAddress() {
		return postAddress;
	}

	public void setPostAddress(String postAddress) {
		this.postAddress = postAddress;
	}

	public String checkOpenId() throws UnsupportedEncodingException, Exception {
		String platId = "";
		String openId = "";
		Map<String, String> resultMap = new HashMap<String, String>();
		if (ToolsUtils.notEmpty(param)) {
			String[] params = param.split("-");
			platId = params[0];
			openId = params[1];
			this.getRequest().setAttribute("param1", param);
		} else {
			resultMap.put("code", "1");//0.代表param值可用。 1.代表无param值。 2.代表该param已注册过。3.代表该param格式不正确
			this.writeJson(resultMap);
			return NONE;
		}
		List<UmTUserBindVo> userList = new ArrayList<UmTUserBindVo>();
		if (ToolsUtils.notEmpty(platId) && ToolsUtils.notEmpty(openId)) {
			userList = umTUserBindService.findRegistUserByOpenid(
					ToolsUtils.toStringHex(platId),
					ToolsUtils.toStringHex(openId));
		}
		if (ToolsUtils.isEmpty(userList)) {
			resultMap.put("code", "0");
		} else {
			umTUserBindVo = userList.get(0);
			userCode = umTUserBindVo.getUserCode();
			userName = umTUserBindVo.getUserName();
			identityNumber = umTUserBindVo.getIdentityNumber();
			licenseNo = umTUserBindVo.getLicenseNo();
			mobile = umTUserBindVo.getMobileNo();
			
			identityNumber = identityNumber.replaceAll("(\\d{3})(\\d{11})([0-9A-Za-z]{4})","$1**********$3");
			mobile = mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
			licenseNo = licenseNo.replaceAll("([\u4e00-\u9fa5]{1}\\w{1})[0-9A-Za-z]{3}([0-9A-Za-z]{2})","$1***$2");
			userName = userName.substring(0,1) + "**" ;

			resultMap.put("userName", userName);
			resultMap.put("identityNumber", identityNumber);
			resultMap.put("licenseNo", licenseNo);
			resultMap.put("mobile", mobile);
			resultMap.put("param1", param);
			
			resultMap.put("code", "2");
		}
		this.writeJson(resultMap);
		return NONE;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	/**
	 * 进入注册页面，根据param值判断是否已注册，如果注册则取出数据展示
	 * 
	 * @return
	 */
	public String personCenter() throws Exception {
		

		String platId = "";
		String openId = "";
		// umTUserBindVo = new UmTUserBindVo();
		if (ToolsUtils.notEmpty(param)) {
			String[] params = param.split("-");
			platId = params[0];
			openId = params[1];
			this.getRequest().setAttribute("param1", param);
		} else {
			return NONE;
		}
		List<UmTUserBindVo> userList = new ArrayList<UmTUserBindVo>();
		if (ToolsUtils.notEmpty(platId) && ToolsUtils.notEmpty(openId)) {
			userList = umTUserBindService.findRegistUserByOpenid(
					ToolsUtils.toStringHex(platId),
					ToolsUtils.toStringHex(openId));
		}
		if (!ToolsUtils.isEmpty(userList)) {
			umTUserBindVo = userList.get(0);
			userCode = umTUserBindVo.getUserCode();
			userName = umTUserBindVo.getUserName();
			identityNumber = umTUserBindVo.getIdentityNumber();
			licenseNo = umTUserBindVo.getLicenseNo();
			mobile = umTUserBindVo.getMobileNo();
			postAddress = umTUserBindVo.getPostAddress();
			
		} else {
			return "zhuce";
		}
		return SUCCESS;
	}
	
	
	/**
	 * 二维码信息界面
	 * 
	 * @return
	 */
	public String myQrcode() throws Exception {
		
		HttpServletRequest request = this.getRequest();
		String platId = "";
		String openId = "";
		// umTUserBindVo = new UmTUserBindVo();
		if (ToolsUtils.notEmpty(param)) {
			String[] params = param.split("-");
			platId = params[0];
			openId = params[1];
			request.setAttribute("param1", param);
		} else {
			return NONE;
		}
		List<UmTUserBindVo> userList = new ArrayList<UmTUserBindVo>();
		if (ToolsUtils.notEmpty(platId) && ToolsUtils.notEmpty(openId)) {
			userList = umTUserBindService.findRegistUserByOpenid(
					ToolsUtils.toStringHex(platId),
					ToolsUtils.toStringHex(openId));
		}
		if (!ToolsUtils.isEmpty(userList)) {
			umTUserBindVo = userList.get(0);
			userCode = umTUserBindVo.getUserCode();
			request.setAttribute("openId", umTUserBindVo.getUserId());
			request.setAttribute("platId", umTUserBindVo.getPlatId());
		} else {
			return "zhuce";
		}
		return SUCCESS;
	}
	
	
	/**
	 * 根据openId和platId获取用户信息
	 * 
	 * @return
	 */
	public String getInfo() throws Exception {
		JSONObject jsonObject = new JSONObject();
		HttpServletRequest request = this.getRequest();
		String platId = request.getParameter("platId");
		String openId = request.getParameter("openId");
		// umTUserBindVo = new UmTUserBindVo();
		if (ToolsUtils.isEmpty(platId) || ToolsUtils.isEmpty(openId)) {
			jsonObject.put("code", -1);
			jsonObject.put("msg", "请返回微信主界面，重进进入公众号。");
			this.writeJson(jsonObject);
			return NONE;
		}
		List<UmTUserBindVo> userList = new ArrayList<UmTUserBindVo>();
		if (ToolsUtils.notEmpty(platId) && ToolsUtils.notEmpty(openId)) {
			userList = umTUserBindService.findRegistUserByOpenid(platId,openId);
		}
		if (!ToolsUtils.isEmpty(userList)) {
			umTUserBindVo = userList.get(0);
			jsonObject.put("code", 0);
			jsonObject.put("data", umTUserBindVo);
			this.writeJson(jsonObject);
			return NONE;
		} else {
			jsonObject.put("code", -1);
			jsonObject.put("msg", "用户信息不存在");
			this.writeJson(jsonObject);
			return NONE;
		}
	}
	
	/**
	 * 发送验证码
	 * 
	 * @return
	 */
	public String sendCode() {
		try {
			Map<String, String> resultMap = umTRegistuserService
					.checkMobileno(mobile);//false:未注册 true:已注册
			String msg = resultMap.get("msg");
			if (msg.equals("false")) {
				String rCode = SmsUtil.sendRegistCode(mobile);
				String info = "";
				JSONObject json = JSONObject.fromObject(rCode);
				JSONObject infos = (JSONObject) json.get("info");
				if (null != infos.getString("code")) {
					info = infos.getString("code");
				}
				if ("200".equals(json.getString("code"))) {
					if ("0".equals(info)) {
						// info: 0.成功   1.每天20条短信上限  999.内部错误
						resultMap.put("state", "0");
						resultMap.put("msg", "发送码获取成功");
					} else if ("1".equals(info)) {
						resultMap.put("state", "4");
						resultMap.put("msg", "此手机号今天无法再发送验证码!");
					} else if ("999".equals(info)) {
						resultMap.put("state", "999");
						resultMap.put("msg", "验证码获取失败，请稍后再试!");
					}
				}
			}else{
				resultMap.put("state", "-1");
				resultMap.put("msg", "该手机号已被注册！");
			}
			this.writeJson(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}
		return NONE;
	}

	/**
	 * 修改UmTRegistuser信息
	 * 
	 * @return
	 */
	public void update() throws Exception {
		UmTUserRelation umTUserRelation = new UmTUserRelation();
		Map<String, String> resultMap = new HashMap<String, String>();
		String platId = "";
		String openId = "";
		// umTUserBindVo = new UmTUserBindVo();
		if (ToolsUtils.notEmpty(param)) {
			String[] params = param.split("-");
			platId = params[0];
			openId = params[1];
		}

		openId = ToolsUtils.toStringHex(openId);
		platId = ToolsUtils.toStringHex(platId);
		if (null != umTUserRelationService
				.getUmTUserRelationByUserId(openId)) {
			umTUserRelation = umTUserRelationService
					.getUmTUserRelationByUserId(openId).get(0);
			userCode = umTUserRelation.getUserCode();
		}
		try {
			umTRegistuserService.updateWxUser(userCode, openId, platId,
					userName, identityNumber, licenseNo,mobile,postAddress);
		} catch (Exception e) {
			resultMap.put("code", "-1");
			resultMap.put("msg", "服务器繁忙，请稍后再试!");
			this.writeJson(resultMap);
		}
		resultMap.put("code", "200");
		resultMap.put("msg", "修改成功");
		this.writeJson(resultMap);
	}

	/**
	 * 新增UmTRegistuser信息
	 * 
	 * @return
	 */
	public void wxRegist() throws Exception {
		Map<String, String> resultMap = new HashMap<String, String>();
		if (param.isEmpty()) {
			resultMap.put("state", "100");// state: 4012  验证码不一致。200.注册通过。204.未知错误。100.param为空
			resultMap.put("msg", "请返回微信主页面重新进入注册!");
			this.writeJson(resultMap);
		} else if (null == licenseNo || "".equals(licenseNo) || null == mobile
				|| "".equals(mobile) || null == identityNumber
				|| "".equals(identityNumber) || null == userName
				|| "".equals(userName) || null == yzm || "".equals(yzm)) {
			this.getResponse().sendRedirect("/weixin/regist/personCenter.do");
		} else {
			String msg = umTRegistuserService.checkMobileno(mobile).get("msg");//msg: false未注册  success已注册
			// 校验验证码
			String vr = SmsUtil.verify(mobile, yzm, 1);
			
			JSONObject json = JSONObject.fromObject(vr);
			JSONObject infos = (JSONObject) json.get("info");

			String code = json.getString("code");
			String info = infos.getString("code");
			
			if (!"200".equals(code) || "1".equals(info)) {
				resultMap.put("state", "4012");
				resultMap.put("msg", "验证码输入错误!");

			} else if (msg.equals("success")
					&& "0".equals(info)) {
				/* 根据mobile搜索个人信息 */
				List<UmTUserBindVo> userList = umTUserBindService.findRegistUserByMobile(mobile);
				umTUserBindVo = userList.get(0);
				
				identityNumber = umTUserBindVo.getIdentityNumber().replaceAll("(\\d{3})(\\d{11})([0-9A-Za-z]{4})","$1**********$3");
				mobile = umTUserBindVo.getMobileNo().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
				licenseNo = umTUserBindVo.getLicenseNo().replaceAll("([\u4e00-\u9fa5]{1}\\w{1})[0-9A-Za-z]{3}([0-9A-Za-z]{2})","$1***$2");
				userName = umTUserBindVo.getUserName().substring(0,1) + "**" ;
				postAddress = umTUserBindVo.getPostAddress();
				
				resultMap.put("userName", userName);
				resultMap.put("identityNumber", identityNumber);
				resultMap.put("licenseNo", licenseNo);
				resultMap.put("mobile", mobile );
				resultMap.put("postAddress", postAddress );
				resultMap.put("state", "4010");
			}
//			 else if (!umTRegistuserService.getUmTRegistuserByLicenseNo(
//				 licenseNo).isEmpty()) {
//				 /* 根据licenseNo搜索个人信息 */
//				 List<UmTRegistuser> list =
//				 umTRegistuserService.getUmTRegistuserByLicenseNo(licenseNo);
//				 UmTRegistuser umTRegistuser = list.get(0);
//				 System.out.println(list.get(0));
//				 resultMap.put("userName",umTRegistuser.getUserName() );
//				 resultMap.put("identityNumber",umTRegistuser.getIdentityNumber()
//				 );
//				 resultMap.put("licenseNo", umTRegistuser.getLicenseNo());
//				 resultMap.put("mobile", umTRegistuser.getMobile());
//				 resultMap.put("code", "4011");
//			 }
			else if ("0".equals(info) && msg.equals("false")) {
				umTRegistuser = new UmTRegistuser();
				umTRegistuser.setUserName(userName);
				umTRegistuser.setMobile(mobile);
				umTRegistuser.setIdentityNumber(identityNumber);
				umTRegistuser.setLicenseNo(licenseNo);
				umTRegistuser.setUserType("00");
				umTRegistuserService.registNewUser(umTRegistuser, param,
						identityNumber, licenseNo, postAddress);
				resultMap.put("state", "200");
				resultMap.put("msg", "注册成功");
			} else {
				resultMap.put("state", "204");
				resultMap.put("msg", "服务器繁忙，请稍后再试。");
			}
			this.writeJson(resultMap);

		}
	}
	
	public boolean paramIsRegist(String param) throws UnsupportedEncodingException, Exception{
		String platId = "";
		String openId = "";
		// umTUserBindVo = new UmTUserBindVo();
		if (ToolsUtils.notEmpty(param)) {
			String[] params = param.split("-");
			platId = params[0];
			openId = params[1];
		} else {
			return true;
		}
		List<UmTUserBindVo> userList = new ArrayList<UmTUserBindVo>();
		if (ToolsUtils.notEmpty(platId) && ToolsUtils.notEmpty(openId)) {
			userList = umTUserBindService.findRegistUserByOpenid(
					ToolsUtils.toStringHex(platId),
					ToolsUtils.toStringHex(openId));
		}
		if (!ToolsUtils.isEmpty(userList)) {
			return true;
		}
		return false;
	}

}
