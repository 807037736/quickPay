package com.picc.qp.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Star.L
 *
 */
public class Constants {
	
	// 管理员角色
	public static String  SYS_MANAGER= "系统管理岗";

	public  static HashMap<String,String> STAT_TYPE_MAP = null;
	
	/**
	 * wx token
	 */
	public static HashMap<String, Object> WX_ACCESS_TOKEN = new HashMap<String, Object>(); 
	/**
	 * 保险公司接口地址
	 */
	public static HashMap<String, Object> COMPANY_URL = new HashMap<String, Object>(); 
	// 短信服务地址
	private static String SMS_URL = null;
	
	// 微信rest webService服务地址
	private static String WX_URL = null;
	
	private static String IMG_USER = null;
	
	private static String IMG_USER_KEY = null;
	
	private static String IMG_PLAT = null;
	
	public static HashMap<String, Object> SEALS = new HashMap<String, Object>(); 
	
	
	private static HashMap<String, Object> params = new HashMap<String, Object>();
	
	private static HashMap<String, String> changshaAreaMap = new HashMap<String, String>();
	
	// 推送模板消息的 url
	private static String TEMPLATEID_URL = null;
	// 受理推送 模板ID
	private static String ACCEPT_TEMPLATEID = null;
	// 定责完成推送 模板ID
	private static String FINISH_TEMPLATEID = null;
	// 定责完成推送 模板ID
	private static String ACCEPTTEMPLATE_URL = null;
	// 定责完成推送 模板ID
	private static String FINISHTEMPLATE_URL = null;
	
	// 一路通同步案件地址
	private static String ASYNC_URL = "";
	
	//报案对接保险公司所有参数
	private static Map<String, String> REPORT_INTERFACE = new HashMap<String, String>();
	
//	//平安对接报案  地址 用户名 密码
//	private static String PAIC_URL = "";
//	private static String PAIC_USERNAME = "";
//	private static String PAIC_PASSWORD = "";
//	//人保对接报案  地址 用户名 密码
//	private static String PICC_URL = "";
//	private static String PICC_USERNAME = "";
//	private static String PICC_PASSWORD = "";
	
//	private static HashMap<String, Object> REPORT_CHANNEL = new HashMap<String, Object>();
	
	static{
		STAT_TYPE_MAP =new HashMap<String,String>();
		STAT_TYPE_MAP.put("ByDate","日期");
		STAT_TYPE_MAP.put("ByCity","市");
		STAT_TYPE_MAP.put("ByDistrict","区");
		STAT_TYPE_MAP.put("ByTimeSpan","时间段");
		STAT_TYPE_MAP.put("ByDriverSex","性别");
		STAT_TYPE_MAP.put("ByWeather","天气状况");
		STAT_TYPE_MAP.put("ByfastCenter","受理点");
		STAT_TYPE_MAP.put("ByCompany","保险公司");
		STAT_TYPE_MAP.put("ByLaw","违反法律法规");
		STAT_TYPE_MAP.put("ByRoad","主干道");
		STAT_TYPE_MAP.put("ByStreet","街道");
		params.put("centerID", "26");// 远程定责默认快赔点
		params.put("city", "430100");// 远程定责默认城市
	}
//	public  static String IMAGE_REAL_PATH = "D:/images";
	
	public static Map<String, Object> IMAGE_PATH = new HashMap<String, Object>();
	
	public static String getIMAGEPATH(){
	    return (String)Constants.IMAGE_PATH.get("imagePath");
	}
	
	public static String getIMAGEHTTPPATH(){
		return (String)Constants.IMAGE_PATH.get("imageHttpPath");
	}
	
	public static String getIMAGEHTTPQUERY(){
		return (String)Constants.IMAGE_PATH.get("imageHttpQuery");
	}

	public static HashMap<String, Object> getWX_ACCESS_TOKEN() {
	    return WX_ACCESS_TOKEN;
	}

	public static void setWX_ACCESS_TOKEN(HashMap<String, Object> wX_ACCESS_TOKEN) {
	    WX_ACCESS_TOKEN = wX_ACCESS_TOKEN;
	}
	
	public static String getJSSDK_APPID() {
//		return "wxb45676f77463f5cb";
	    return WX_ACCESS_TOKEN.get("JSSDK_APPID").toString();
	}
	
	public static String getJSSDK_APPSECRET() {
//		return "5d159a729e3701a2daa3e72f52264fc4";
	    return WX_ACCESS_TOKEN.get("JSSDK_APPSECRET").toString();
	}

	public static String getSMS_URL() {
//		return "http://192.168.1.22:8081";
		return SMS_URL;
	}

	public static void setSMS_URL(String URL) {
		SMS_URL = URL;
	}
	
	public static String getWX_URL() {

	    return WX_URL;
	}

	public static void setWX_URL(String wX_URL) {
	    WX_URL = wX_URL;
	}

	public static HashMap<String, Object> getSEALS() {
		return SEALS;
	}

	public static void setSEALS(HashMap<String, Object> sEALS) {
		SEALS = sEALS;
	}
	
	public static String getIMG_USER() {
	    return IMG_USER;
	}

	public static void setIMG_USER(String iMG_USER) {
	    IMG_USER = iMG_USER;
	}

	public static String getIMG_USER_KEY() {
	    return IMG_USER_KEY;
	}

	public static void setIMG_USER_KEY(String iMG_USER_KEY) {
	    IMG_USER_KEY = iMG_USER_KEY;
	}

	public static String getIMG_PLAT() {
	    return IMG_PLAT;
	}

	public static void setIMG_PLAT(String iMG_PLAT) {
	    IMG_PLAT = iMG_PLAT;
	}

	public static String getSEAL_ROOTPATH(){
		return SEALS.get("rootPath").toString();
	}
	public static String getSEAL_CERTPATH(){
		return SEALS.get("certPath").toString();
	}
	public static String getSEAL_SIGNIMAGE(){
		return SEALS.get("signImage").toString();
	}
	public static String getSEAL_CERTPWD(){
		return SEALS.get("certPwd").toString();
	}
	public static String getSEAL_SERVERTYPE(){
		return SEALS.get("serverType").toString();
	}
	public static String getSEAL_RULEINFO(){
		return SEALS.get("ruleInfo").toString();
	}

	public static String getASYNC_URL() {
		return ASYNC_URL;
	}

	public static void setASYNC_URL(String aSYNC_URL) {
		ASYNC_URL = aSYNC_URL;
	}

	public static HashMap<String, Object> getParams() {
		return params;
	}

	public static String getACCEPT_TEMPLATEID() {
		return ACCEPT_TEMPLATEID;
	}

	public static void setACCEPT_TEMPLATEID(String aCCEPT_TEMPLATEID) {
		ACCEPT_TEMPLATEID = aCCEPT_TEMPLATEID;
	}

	public static String getFINISH_TEMPLATEID() {
		return FINISH_TEMPLATEID;
	}

	public static void setFINISH_TEMPLATEID(String fINISH_TEMPLATEID) {
		FINISH_TEMPLATEID = fINISH_TEMPLATEID;
	}

	public static String getTEMPLATEID_URL() {
		return TEMPLATEID_URL;
	}

	public static void setTEMPLATEID_URL(String tEMPLATEID_URL) {
		TEMPLATEID_URL = tEMPLATEID_URL;
	}

	public static String getACCEPTTEMPLATE_URL() {
		return ACCEPTTEMPLATE_URL;
	}

	public static void setACCEPTTEMPLATE_URL(String aCCEPTTEMPLATE_URL) {
		ACCEPTTEMPLATE_URL = aCCEPTTEMPLATE_URL;
	}

	public static String getFINISHTEMPLATE_URL() {
		return FINISHTEMPLATE_URL;
	}

	public static void setFINISHTEMPLATE_URL(String fINISHTEMPLATE_URL) {
		FINISHTEMPLATE_URL = fINISHTEMPLATE_URL;
	}

	public static HashMap<String, String> getChangshaAreaMap() {
		return changshaAreaMap;
	}

	public static void setChangshaAreaMap(HashMap<String, String> changshaAreaMap) {
		Constants.changshaAreaMap = changshaAreaMap;
	}

	

	public static HashMap<String, Object> getCOMPANY_URL() {
		return COMPANY_URL;
	}

	public static void setCOMPANY_URL(HashMap<String, Object> cOMPANY_URL) {
		COMPANY_URL = cOMPANY_URL;
	}

	public static Map<String, String> getREPORT_INTERFACE() {
		return REPORT_INTERFACE;
	}

	public static void setREPORT_INTERFACE(Map<String, String> rEPORT_INTERFACE) {
		REPORT_INTERFACE = rEPORT_INTERFACE;
	}
	
	
}


