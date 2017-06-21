package com.picc.report.paic;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Encoder;

import com.alibaba.fastjson.JSONObject;
import com.picc.qp.util.Constants;
import com.picc.qp.util.HttpClientUtils;

public class PaicReportUtil {
	
	protected static final Logger logger = LoggerFactory.getLogger(PaicReportUtil.class);
	
	/**
	 * 获取平安对接token
	 * @return
	 */
	public static JSONObject getPaicToken(){
		JSONObject jsonObject = new JSONObject();
		try {
			
			String userName = Constants.getREPORT_INTERFACE().get("PAIC_userName");
			String passWord = Constants.getREPORT_INTERFACE().get("PAIC_passWord");
			String getTokenURL = Constants.getREPORT_INTERFACE().get("PAIC_token");
			if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(passWord)){
				jsonObject.put("code", -1);
				jsonObject.put("msg", "初始化获取token参数失败");
				return jsonObject;
			}
			Long time = new Date().getTime();
			String cipherText = PaicReportUtil.signData(userName, passWord, time);
			if(StringUtils.isEmpty(cipherText)){
				jsonObject.put("code", -1);
				jsonObject.put("msg", "获取密文失败,请稍后在试");
				return jsonObject;
			}
			
			Map<String,String> param = new HashMap<String,String>();
			param.put("userName", userName);
			param.put("timeStamp", String.valueOf(time));
			param.put("cipherText", cipherText);
			logger.info("----平安toekn获取请求参数::" + param);
			HttpResponse response = HttpClientUtils.sendPiccHttpRequest(getTokenURL, com.alibaba.fastjson.JSONObject.toJSONString(param));
			String tokenResponse = EntityUtils.toString(response.getEntity());
			logger.info("----平安toekn获取结果Str:" + tokenResponse);
			JSONObject resultDate = JSONObject.parseObject(tokenResponse);
			logger.info("----平安toekn获取结果:" + resultDate);
			if(resultDate == null || resultDate.isEmpty()){
				jsonObject.put("code", -1);
				jsonObject.put("msg", "token获取失败,请稍后在试");
				return jsonObject;
			}

			JSONObject result = resultDate.getJSONObject("result");
			if(result.isEmpty()){
				jsonObject.put("code", -1);
				jsonObject.put("msg", "token获取失败,无返回信息");
				return jsonObject;
			}

			if("999999".equals(result.getString("code"))){
				//成功
				jsonObject.put("code", 0);
				jsonObject.put("msg", "OK");
				jsonObject.put("data", resultDate.getJSONObject("data").getString("token"));
			}else {
				jsonObject.put("code", result.getString("code"));
				jsonObject.put("msg", result.getString("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("code", 999);
			jsonObject.put("msg", "token获取异常,请稍后在试");
		}
		return jsonObject;
	}

	
	public static HttpResponse sendPiccHttpRequest(String httpUrl, String sendData) {
		HttpResponse response = null;
		HttpClient httpClient = null;
		HttpPost postMethod = null;
		try {
			HttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, 25000);//链接超时时间
			HttpConnectionParams.setSoTimeout(httpParams, 20000);//读取超时时间
			httpClient = new DefaultHttpClient(httpParams);
			postMethod = new HttpPost(httpUrl);
			postMethod.addHeader("Content-Type", "application/json;charset=UTF-8");
			postMethod.setEntity(new StringEntity(sendData, "UTF-8"));
			response = httpClient.execute(postMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
		return response;
	}

	/**
	 * 获取用户的密码，如果为失效状态，会抛异常返回
	 * @param userName
	 * @return password
	 */
	public static String signData(String userName,String passWord,Long timeStamp) throws Exception
	{
		String signValue="";
		String data = "";
		data += userName+passWord +timeStamp;
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA"); 
			byte[] srcBytes = data.getBytes(); 
			//使用srcBytes更新摘要 
			sha.update(srcBytes); 
			//完成哈希计算，得到result 
			byte[] resultBytes = sha.digest(); 
			BASE64Encoder encoder=new BASE64Encoder();
			signValue=encoder.encodeBuffer(resultBytes);//BASE64编码
		}catch(Exception e){
			throw new Exception(e);
		}
		System.out.println("密文:"+signValue);
		return signValue;
	}
	
	
	public static String MD5Sign(String filedata){
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("md5");  
			//使用filedata的字节数组更新摘要  
			digest.update(filedata.getBytes());  
		}catch(Exception e){
			e.printStackTrace();
		}
		return new BASE64Encoder().encodeBuffer(digest.digest());////完成哈希计算，并BASE64编码
	}

	//获取base64编码后的图片字符串
	public static String GetImageStr(String imgFilePath) {
		byte[] data = null;
		InputStream inStr = null;
		try {
			// 读取图片字节数组
			inStr = new FileInputStream(imgFilePath);
			data = new byte[inStr.available()];
			inStr.read(data);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(inStr != null){
				try {
					inStr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return new BASE64Encoder().encode(data);// 返回Base64编码过的字节数组字符串
	}

	/** 
     * 获取网络图片并转为Base64编码 
     *  
     * @param url  网络图片路径 
     * @return base64 编码 
     * @throws Exception 
     */  
    public static String GetUrlImageToBase64(String url) throws Exception {  
        if (url == null || "".equals(url.trim()))  
            return null;  
        URL u = new URL(url);  
        // 打开图片路径  
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();  
        // 设置请求方式为GET  
        conn.setRequestMethod("GET");  
        // 设置超时响应时间为5秒  
        conn.setConnectTimeout(5000);  
        // 通过输入流获取图片数据  
        InputStream inStream = conn.getInputStream();  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
        	// 读取图片字节数组  
        	byte[] buffer = new byte[1024]; 
        	int size = 0;
        	while ((size = inStream.read(buffer)) != -1) {  
        		bos.write(buffer,0,size);
        	} 
        	bos.flush();
        	byte[] data = bos.toByteArray();
        	// 对字节数组Base64编码  
        	BASE64Encoder encoder = new BASE64Encoder();  
        	// 返回Base64编码过的字节数组字符串  
        	return encoder.encode(data);  
        	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			bos.close();
        	inStream.close();  
		}
    }  
}
