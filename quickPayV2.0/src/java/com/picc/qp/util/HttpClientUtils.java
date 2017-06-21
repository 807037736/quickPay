package com.picc.qp.util;


import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import com.picc.common.utils.StringUtils;


public class HttpClientUtils {
	/**
	 * @Desc
	 * @param url
	 * @param content
	 * @param encoding
	 * @return 
	 * @author xiaozhiqiang
	 * @Date 2015-12-2
	 */
	public static Map<String,Object> HttpClientXMLPost(String url,String content,String encoding){
		String contentType = "text/xml";
		HttpClient client = new HttpClient();
		return HttpClientPostByContent(contentType,client,url,content,encoding);
	}
	/**
	 * @Desc
	 * @param url
	 * @param content
	 * @param encoding
	 * @return 
	 * @author xiaozhiqiang
	 * @Date 2015-12-2
	 */
	public static Map<String,Object> HttpClientJsonPost(String url,String content,String encoding){
		String contentType = "application/json";
		HttpClient client = new HttpClient();
		return HttpClientPostByContent(contentType,client,url,content,encoding);
	}
	
	/**
	 * @Desc
	 * @param url
	 * @param content
	 * @param encoding
	 * @param connectionTimeout  链接超时时间
	 * @param soTimeout		数据读取超时时间
	 * @return 
	 * @author xiaozhiqiang
	 * @Date 2015-12-2
	 */
	public static Map<String,Object> HttpClientJsonPost(String url,String content, Integer connectionTimeout, Integer soTimeout){
		String contentType = "application/json";
		HttpClient client = new HttpClient();
		return HttpClientPostByContent(contentType,client,url,content,"UTF-8", connectionTimeout, soTimeout);
	}
	/**
	 * @Desc
	 * @param client
	 * @param url
	 * @param content
	 * @param encoding
	 * @return 
	 * @author xiaozhiqiang
	 * @Date 2015-11-24
	 */
	public static Map<String,Object> HttpClientPostByContent(String contentType,HttpClient client,String url,String content,String encoding, Integer connectionTimeout, Integer soTimeout){
		HttpConnectionManagerParams managerParams = client
				.getHttpConnectionManager().getParams();
		// 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(connectionTimeout);
		// 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(soTimeout);
		PostMethod method = null;
		Map<String,Object> map = new HashMap<String,Object>();
		String info = "";
		int code = 0;
		try{
			method = new PostMethod(url); 
			RequestEntity entity = new StringRequestEntity(content,contentType,encoding);  
			method.setRequestEntity(entity);
			client.executeMethod(method);  
			code = method.getStatusCode();
			if (code == HttpStatus.SC_OK){
				InputStream responseIs = method.getResponseBodyAsStream();
				info = StreamUtils.convertStreamToString(responseIs,encoding,null);
			}
			map.put("code", code+"");
			map.put("info", info);
		}catch(Exception e){
			map.put("code","999");
			map.put("info", ExceptionUtils.getStackTraceMessage(e));
			return map;	
		}finally{
			//释放连接  
			method.releaseConnection();  
			client.getHttpConnectionManager().closeIdleConnections(0); 
		}
		return map;	
	}
	
	/**
	 * @Desc
	 * @param client
	 * @param url
	 * @param content
	 * @param encoding
	 * @return 
	 * @author xiaozhiqiang
	 * @Date 2015-11-24
	 */
	public static Map<String,Object> HttpClientPostByContent(String contentType,HttpClient client,String url,String content,String encoding){
		HttpConnectionManagerParams managerParams = client
				.getHttpConnectionManager().getParams();
		// 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(25000);
		// 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(20000);
		PostMethod method = null;
		Map<String,Object> map = new HashMap<String,Object>();
		String info = "";
		int code = 0;
		try{
			method = new PostMethod(url); 
			RequestEntity entity = new StringRequestEntity(content,contentType,encoding);  
			method.setRequestEntity(entity);
			client.executeMethod(method);  
			code = method.getStatusCode();
			if (code == HttpStatus.SC_OK){
				InputStream responseIs = method.getResponseBodyAsStream();
				info = StreamUtils.convertStreamToString(responseIs,encoding,null);
			}
			map.put("code", code+"");
			map.put("info", info);
		}catch(Exception e){
			map.put("code","999");
			map.put("info", ExceptionUtils.getStackTraceMessage(e));
			return map;	
		}finally{
			//释放连接  
			method.releaseConnection();  
			client.getHttpConnectionManager().closeIdleConnections(0); 
		}
		return map;	
	}
	
	/**
	 * @Desc
	 * @param client
	 * @param url
	 * @param content
	 * @param encoding
	 * @return 
	 * @author xiaozhiqiang
	 * @Date 2015-11-24
	 */
	public static Map<String,Object> HttpClientPostByParameter(String url,Map<String,Object> paramsMap,String encoding){
		HttpClient client = new HttpClient();
		HttpConnectionManagerParams managerParams = client
				.getHttpConnectionManager().getParams();
		// 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(30000);
		// 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(120000);
		PostMethod method = null;
		Map<String,Object> map = new HashMap<String,Object>();
		String info = "";
		int code = 0;
		try{
			method = new PostMethod(url); 
			method.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset="+encoding);    
			Set<String> set = paramsMap.keySet();
			int size = set.size();
			Iterator<String> iterator = set.iterator();
			NameValuePair[] params = new NameValuePair[size];
			int i = 0;
			while(iterator.hasNext()){
				String name = iterator.next();
				params[i] = new NameValuePair(name,(String)paramsMap.get(name));
				i++;
			}
			method.setRequestBody(params);
			client.executeMethod(method);
			code = method.getStatusCode();
			if (code == HttpStatus.SC_OK){
				InputStream responseIs = method.getResponseBodyAsStream();
				info = StreamUtils.convertStreamToString(responseIs,encoding,null);
			}
			map.put("code", code+"");
			map.put("info", info);
		}catch(Exception e){
			map.put("code","999");
			map.put("info", ExceptionUtils.getStackTraceMessage(e));
			return map;	
		}finally{
			//释放连接  
			method.releaseConnection();
			client.getHttpConnectionManager().closeIdleConnections(0);
		}
		return map;	
	}
	/**
	 * @Desc
	 * @param url
	 * @param paramsMap
	 * @param encoding
	 * @return 
	 * @author xiaozhiqiang
	 * @Date 2015-12-2
	 */
	public static Map<String,Object> HttpClientGetByParameter(String url,Map<String,Object> paramsMap,String encoding){
		HttpClient client = new HttpClient();
		HttpConnectionManagerParams managerParams = client
				.getHttpConnectionManager().getParams();
		// 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(30000);
		// 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(120000);
		GetMethod method = null;
		Map<String,Object> map = new HashMap<String,Object>();
		String info = "";
		int code = 0;
		try{
			Iterator<String> iterator = paramsMap.keySet().iterator();
			StringBuffer sBuffer = new StringBuffer();
			while(iterator.hasNext()){
				String name = iterator.next();
				if(StringUtils.isNotEmpty(sBuffer.toString())){
					sBuffer.append("&");
				}else{
					sBuffer.append("?");
				}
				sBuffer.append(name).append("=").append(URLEncoder.encode((String)paramsMap.get(name),encoding));
			}
			method = new GetMethod(url+sBuffer); 
			client.executeMethod(method);  
			code = method.getStatusCode();
			if (code == HttpStatus.SC_OK){
				InputStream responseIs = method.getResponseBodyAsStream();
				info = StreamUtils.convertStreamToString(responseIs,encoding,null);
			}
			map.put("code", code+"");
			map.put("info", info);
		}catch(Exception e){
			map.put("code","999");
			map.put("info", ExceptionUtils.getStackTraceMessage(e));
			return map;	
		}finally{
			//释放连接  
			method.releaseConnection();  
			client.getHttpConnectionManager().closeIdleConnections(0);
		}
		return map;	
	}
	
	
	/**
	 * 平安报价等接口提交
	 * @param httpUrl
	 * @param sendData
	 * @return
	 */
	public static HttpResponse sendPiccHttpRequest(String httpUrl, String sendData) {
		HttpResponse response = null;
		try {
			HttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, 15 * 100000);//链接超时时间
			HttpConnectionParams.setSoTimeout(httpParams, 15 * 100000);//读取超时时间
			org.apache.http.client.HttpClient httpClient = new DefaultHttpClient(httpParams);
			HttpPost postMethod = new HttpPost(httpUrl);
			postMethod.addHeader("Content-Type", "application/json;charset=UTF-8");
			postMethod.setEntity(new StringEntity(sendData, "UTF-8"));
			response = httpClient.execute(postMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
