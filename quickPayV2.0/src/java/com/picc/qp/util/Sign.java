package com.picc.qp.util;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.activiti.engine.impl.util.json.JSONObject;
import org.activiti.engine.impl.util.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Sign {
	
	protected static final Logger logger = (Logger) LoggerFactory.getLogger(Sign.class);
	
    public static void main(String[] args) throws Exception {
        String jsapi_ticket = "jsapi_ticket";

        // 注意 URL 一定要动态获取，不能 hardcode
        String url = "http://plter.com";
        Map<String, Object> ret = sign("wxb844d812934c2f47", "c1da42d92446032d4ea1bb17aa78c720", url);
        for (Map.Entry entry : ret.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
    };

    public static Map<String, Object> sign(String appId, String appSecret, String url) throws Exception {
    	//String accessToken = getAccessToken(appId, appSecret);
    	//String jsapi_ticket = getJsApiTicket(accessToken);
    	//String accessToken = "TXrpW_ltYMIfQwCzttwujnMzTmN9Oohj7ztnAE9EPKqv8n3tBpFpHkPm2e7uUUpV74N093e3l6nYYtDuAvLhdmrKxq4EwnA6AgfpOnXgKycRIIgAHANEE";
    	//String jsapi_ticket = "sM4AOVdWfPE4DxkXGEs8VKnAn5htinzaaCHdP8vay-k8zjCadBYwQKxaLaavcgBeCzy8norxg9MJUtY_JirFzg";
    	JSONObject accessTokenJsonObj = getAccessToken(appId, appSecret);
    	JSONObject jsapi_ticketJsonObj = getJsApiTicket(accessTokenJsonObj.getString("access_token"));
        Map<String, Object> ret = new HashMap<String, Object>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp() + 7200;
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticketJsonObj.getString("ticket") +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            logger.error("", e);
            
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            logger.error("", e);
        }

        ret.put("url", url);
        ret.put("appId", appId);
        ret.put("jsapi_ticketJsonObj", jsapi_ticketJsonObj);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        ret.put("accessTokenJsonObj", accessTokenJsonObj);

        return ret;
    }
    
    /**
     * 根据access_token 获取jsapiticket
     * @param appId
     * @param appSecret
     * @param url
     * @param accessToken
     * @return
     * @throws Exception
     */
    public static Map<String, Object> signWithToken(String appId, String appSecret, String url, JSONObject accessToken) throws Exception  {
    	Map<String, Object> ret = new HashMap<String, Object>();
    	JSONObject jsapi_ticketJsonObj = getJsApiTicket(accessToken.getString("access_token"));
    	
    	String nonce_str = create_nonce_str();
        String timestamp = create_timestamp() + 7200;
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticketJsonObj.getString("ticket") +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            logger.error("", e);
            
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            logger.error("", e);
        }

        ret.put("url", url);
        ret.put("appId", appId);
        ret.put("jsapi_ticketJsonObj", jsapi_ticketJsonObj);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        ret.put("accessTokenJsonObj", accessToken);
    	
    	return ret;
    }
    
    /**
     * 从内存中获取已缓存的token和ticket进行签名
     * @param appId
     * @param appSecret
     * @param url
     * @return
     * @throws Exception
     */
    public static Map<String, Object> signWithConstants(String appId, String appSecret, String url) throws Exception  {
    	Map<String, Object> ret = new HashMap<String, Object>();
    	
    	String nonce_str = create_nonce_str();
    	String timestamp = create_timestamp() + 7200;
    	String string1;
    	String signature = "";
    	
    	//注意这里参数名必须全部小写，且必须有序
    	string1 = "jsapi_ticket=" + ((JSONObject)Constants.getWX_ACCESS_TOKEN().get("ticket")).getString("ticket") +
    			"&noncestr=" + nonce_str +
    			"&timestamp=" + timestamp +
    			"&url=" + url;
    	
    	try
    	{
    		MessageDigest crypt = MessageDigest.getInstance("SHA-1");
    		crypt.reset();
    		crypt.update(string1.getBytes("UTF-8"));
    		signature = byteToHex(crypt.digest());
    	}
    	catch (NoSuchAlgorithmException e)
    	{
    		e.printStackTrace();
    		logger.error("", e);
    		
    	}
    	catch (UnsupportedEncodingException e)
    	{
    		e.printStackTrace();
    		logger.error("", e);
    	}
    	
    	ret.put("url", url);
    	ret.put("appId", appId);
    	ret.put("jsapi_ticketJsonObj", Constants.getWX_ACCESS_TOKEN().get("ticket"));
    	ret.put("nonceStr", nonce_str);
    	ret.put("timestamp", timestamp);
    	ret.put("signature", signature);
    	ret.put("accessTokenJsonObj", Constants.getWX_ACCESS_TOKEN().get("token"));
    	
    	return ret;
    }

    public static JSONObject getJsApiTicket(String accessToken) throws Exception {
    	URL url = new URL("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken + "&type=jsapi");
    	JSONObject json = getConnection(url);
    	//String jsapi_ticket = json.getString("ticket");
    	return json;
	}

	public static JSONObject getAccessToken(String appId, String appSecret) throws Exception {
		URL url = new URL("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
		        + appId + "&secret=" + appSecret);
	    JSONObject json = getConnection(url);
//	    String access_token = (String) json.getString("access_token");
    	return json;
	}
	
	public static InputStream downloadPicture(String accessToken, String mediaId) throws Exception {
		URL url = new URL("https://api.weixin.qq.com/cgi-bin/media/get?access_token="+ accessToken +"&media_id=" + mediaId );
		System.out.println(url);
    	return getConnection2(url);
	}
    
    public static  JSONObject getConnection(URL url) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("GET");
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
 
        connection.connect();
        JSONObject jsono = new JSONObject(new JSONTokener(
                new InputStreamReader(connection.getInputStream())));
        connection.disconnect();
        return jsono;
    }
    
    public static  InputStream getConnection2(URL url) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //设置请求方式为"GET"  
        connection.setRequestMethod("GET");  
        //超时响应时间为5秒  
        connection.setConnectTimeout(5 * 1000);  
 
        InputStream inputStream = connection.getInputStream();
//		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//		String inputLine = "";
//		StringBuffer inputLines = new StringBuffer();
//		while ((inputLine = bufferedReader.readLine()) != null) {
//			inputLines.append(inputLine);
//		}
        return inputStream;
    }

	private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
    
}
