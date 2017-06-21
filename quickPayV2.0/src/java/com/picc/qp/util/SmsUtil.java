package com.picc.qp.util;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 短信接口
 * @author obba
 *
 */
public class SmsUtil {
	private final static String DOMAIN = Constants.getSMS_URL();
	private final static String ENCODING = "UTF-8"; 
//	SEND1_MODEL(1, "微信注册验证码"), 
//	SEND2_MODEL(2, "当事人责任确认验证码"), 
//	SEND3_MODEL(3, "交警定责提醒通知"), 
//	SEND4_MODEL(4, "交警定责结果-推送当事人");
	
	/**
	 * 发送注册验证码
	 * @param phone
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String sendRegistCode(String phone) {
		String sendurl = DOMAIN+"/rest/sms/send";
		JSONObject sendJsonObject = new JSONObject();
		JSONObject sendJsonData = new JSONObject();
		sendJsonData.put("phone", phone);
		sendJsonData.put("smsModelNo", 1);
		sendJsonData.put("serverCode", "QuickPay");
		sendJsonData.put("type", 1);
		sendJsonObject.put("data", sendJsonData);
		String sendMac = CodeUtils.getSign("bxfanqizha.com", sendJsonData);
		sendJsonObject.put("mac", sendMac);
		Map<String, Object> sendMap = HttpClientUtils.HttpClientJsonPost(sendurl, sendJsonObject.toString(), ENCODING);
		return sendMap.toString();
	}
	
	/**
	 * 发送有争议案件当事人确认信息验证码
	 * @param phone
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String sendCode(String phone) {
		String sendurl = DOMAIN+"/rest/sms/send";
		JSONObject sendJsonObject = new JSONObject();
		JSONObject sendJsonData = new JSONObject();
		sendJsonData.put("phone", phone);
		sendJsonData.put("smsModelNo", 5);
		sendJsonData.put("serverCode", "QuickPay");
		sendJsonData.put("type", 1);
		sendJsonObject.put("data", sendJsonData);
		String sendMac = CodeUtils.getSign("bxfanqizha.com", sendJsonData);
		sendJsonObject.put("mac", sendMac);
		Map<String, Object> sendMap = HttpClientUtils.HttpClientJsonPost(sendurl, sendJsonObject.toString(), ENCODING);
		return sendMap.toString();
	}

	/**
	 * 发送定责结果给当事人
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String sendCaseResultToAccident(String phone, String date, String name_1, String number_1, String rep_1, String name_2, String number_2, String rep_2) {
		String sendurl = DOMAIN+"/rest/sms/send";
		JSONObject sendJsonObject = new JSONObject();
		JSONObject sendJsonData = new JSONObject();
		sendJsonData.put("phone", phone);
		sendJsonData.put("smsModelNo", 4);
		sendJsonData.put("serverCode", "QuickPay");
		sendJsonData.put("accidentDate", date);
		sendJsonData.put("name1", name_1);
		sendJsonData.put("drivervehicleNumber1", number_1);
		sendJsonData.put("responsibility1", rep_1);
		sendJsonData.put("name2", name_2);
		sendJsonData.put("drivervehicleNumber2", number_2);
		sendJsonData.put("responsibility2", rep_2);
		sendJsonData.put("type", 4);
		sendJsonObject.put("data", sendJsonData);
		String sendMac = CodeUtils.getSign("bxfanqizha.com", sendJsonData);
		sendJsonObject.put("mac", sendMac);
		Map<String, Object> sendMap = HttpClientUtils.HttpClientJsonPost(sendurl, sendJsonObject.toString(), ENCODING);
		return sendMap.toString();
	}
	
	/**
	 * @param phone
	 * @param date
	 * @param name_1
	 * @param number_1
	 * @param rep_1
	 * @param name_2
	 * @param number_2
	 * @param rep_2
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String sendResultToAccident(String phone, String date, String name_1, String number_1, String rep_1, String name_2, String number_2, String rep_2) {
		String sendurl = DOMAIN+"/rest/sms/send";
		JSONObject sendJsonObject = new JSONObject();
		JSONObject sendJsonData = new JSONObject();
		sendJsonData.put("phone", phone);
		sendJsonData.put("smsModelNo", 6);
		sendJsonData.put("serverCode", "QuickPay");
		sendJsonData.put("accidentDate", date);
		sendJsonData.put("name1", name_1);
		sendJsonData.put("drivervehicleNumber1", number_1);
		sendJsonData.put("responsibility1", rep_1);
		sendJsonData.put("name2", name_2);
		sendJsonData.put("drivervehicleNumber2", number_2);
		sendJsonData.put("responsibility2", rep_2);
		sendJsonData.put("type", 4);
		sendJsonObject.put("data", sendJsonData);
		String sendMac = CodeUtils.getSign("bxfanqizha.com", sendJsonData);
		sendJsonObject.put("mac", sendMac);
		Map<String, Object> sendMap = HttpClientUtils.HttpClientJsonPost(sendurl, sendJsonObject.toString(), ENCODING);
		return sendMap.toString();
	}
	
	/**
	 * 校验验证码   单手机号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String verify(String phone, String code, Integer modelNo) {
		String sendurl = DOMAIN+"/rest/sms/verify";
		JSONObject sendJsonObject = new JSONObject();
		JSONObject sendJsonData = new JSONObject();
		sendJsonData.put("phone", phone);
		sendJsonData.put("smsCode", code);
		sendJsonData.put("smsModelNo", modelNo);
		sendJsonObject.put("data", sendJsonData);
		String sendMac = CodeUtils.getSign("bxfanqizha.com", sendJsonData);
		sendJsonObject.put("mac", sendMac);
		Map<String, Object> sendMap = HttpClientUtils.HttpClientJsonPost(sendurl, sendJsonObject.toString(), ENCODING);
		return sendMap.toString();
	}
	
	/**
	 * 校验验证码  多手机号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String verifys(JSONArray smsDatas, int modelNo) {
		String sendurl = DOMAIN+"/rest/sms/verifys";
		JSONObject sendJsonObject = new JSONObject();
		JSONObject sendJsonData = new JSONObject();
		sendJsonData.put("smsDatas", smsDatas);
		sendJsonData.put("smsModelNo", modelNo);
		sendJsonObject.put("data", sendJsonData);
		String sendMac = CodeUtils.getSign("bxfanqizha.com", sendJsonData);
		sendJsonObject.put("mac", sendMac);
		Map<String, Object> sendMap = HttpClientUtils.HttpClientJsonPost(sendurl, sendJsonObject.toString(), ENCODING);
		return sendMap.toString();
	}
	
	/**
	 * 
	 * @param phone
	 * @param date
	 * @param smsModelNo
	 * @param serverCode
	 * @param privateKey
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String sendSMS(String phone, String data,String smsModelNo,String serverCode,String privateKey)  throws Exception{
		//暂时固定
		String sendurl = DOMAIN+"/rest/general/sms/send";
		//String sendurl = "http://192.168.1.92:9080/rest/general/sms/send";
		JSONObject sendJsonObject = new JSONObject();
		JSONObject sendJsonData = new JSONObject();
		sendJsonData.put("phone", phone);
		sendJsonData.put("smsModelNo", smsModelNo);
		sendJsonData.put("serverCode", serverCode);
		sendJsonData.put("data", data);
		sendJsonObject.put("data", sendJsonData);
		String sendMac = CodeUtils.getSign(privateKey, sendJsonData);
		sendJsonObject.put("mac", sendMac);
		Map<String, Object> sendMap = HttpClientUtils.HttpClientJsonPost(sendurl, sendJsonObject.toString(), ENCODING);
		return sendMap.toString();
	}

}
