/**
 * JSON的帮助类
 * @author 卢斌晖
 * @since 2014-04-21 15:30
 */
package com.picc.common.utils.gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.picc.common.service.spring.YpSmsServiceImpl;

public class JsonUtil 
{
	protected static final Logger logger = (Logger) LoggerFactory.getLogger(YpSmsServiceImpl.class);
	private static GsonBuilder gsonBuilder;
	private static Gson gson;
	
	static
	{
		gsonBuilder=new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		gsonBuilder.registerTypeAdapter(Date.class, new DateSerializer());
		gsonBuilder.registerTypeAdapter(Map.class, new MapSerializer());
		gson=gsonBuilder.create();
	}
	
	public static String toJson(Object object)
	{
		return gson.toJson(object);
	}
	
	public static String toJson(Object[] arr)
	{
		return gson.toJson(arr);
	}
	
	public static String toJson(char[] arr)
	{
		return gson.toJson(arr);
	}
	
	public static String toJson(String[] arr)
	{
		return gson.toJson(arr);
	}
	
	public static String toJson(short[] arr)
	{
		return gson.toJson(arr);
	}
	
	public static String toJson(int[] arr)
	{
		return gson.toJson(arr);
	}
	
	public static String toJson(long[] arr)
	{
		return gson.toJson(arr);
	}
	
	public static String toJson(float[] arr)
	{
		return gson.toJson(arr);
	}
	
	public static String toJson(double[] arr)
	{
		return gson.toJson(arr);
	}
	
	public static JSONObject getJSONObject(String jsonString)
	{
		JSONObject o=null;
		try
		{
			o=JSONObject.fromObject(jsonString);
		}
		catch(Exception e)
		{
			logger.error("", e);
		}
		return o;
	}
	
	public static JSONArray getJSONArray(String jsonString)
	{
		JSONArray o=null;
		try
		{
			o=JSONArray.fromObject(jsonString);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return o;
	}
	
	public static Object fromJson(String jsonString,Class _class)
	{
		return gson.fromJson(jsonString,_class);
	}

	/**
	 * 将JSONObject填充到以HashMap为顶层对象的java容器
	 * @Description 
	 * @param jo
	 * @return
	 * @author 卢斌晖
	 * @since 2014-4-21 下午10:51:08
	 * @throws
	 */
	public static Map JsonToJavaContainer(JSONObject jo)
	{
		if(null == jo)
			return null;
		Iterator iterator = jo.keys();
		Object key = null;
		Object value = null;
		Map map = new HashMap();
		while(iterator.hasNext())
		{
			key = iterator.next();
			value = jo.get(key);
			if(null != value)
			{
				if(value instanceof JSONObject)
				{
					map.put(key, JsonToJavaContainer((JSONObject)value));
					continue;
				}
				if(value instanceof JSONArray)
				{
					map.put(key, JsonToJavaContainer((JSONArray)value));
					continue;
				}
			}
			map.put(key,value);
		}
		return map;
	}
	
	/**
	 * 将JSONArray填充到以ArrayList为顶层对象的java容器
	 * @Description 
	 * @param ja
	 * @return
	 * @author 卢斌晖
	 * @since 2014-4-21 下午11:01:47
	 * @throws
	 */
	public static List JsonToJavaContainer(JSONArray ja)
	{
		if(null == ja)
			return null;
		Iterator iterator = ja.iterator();
		Object value = null;
		List list = new ArrayList();
		while(iterator.hasNext())
		{
			value = iterator.next();
			if(null != value)
			{
				if(value instanceof JSONObject)
				{
					list.add(JsonToJavaContainer((JSONObject)value));
					continue;
				}
				if(value instanceof JSONArray)
				{
					list.add(JsonToJavaContainer((JSONArray)value));
					continue;
				}
			}
			list.add(value);
		}
		return list;
	}
	
	public static void main(String[] args)
	{
		List testList=new ArrayList();
		testList.add("aaaassaaaaaa");
		testList.add(null);
		String str=JsonUtil.toJson(testList);
		System.out.println(str);
		
		String json="[{\"bod\":[{\"sendId\":\"1065730184951517\",\"phoneNo\":\"13539417001\","+
"\"smsContent\":\"测试短信\",\"needReply\":0,\"serviceId\":7500,\"company\":\"75000000\","+
"\"sendTime\":\"2014-04-21 16:43:41\",\"comId\":\"44030000\"}],"+
"\"ruleparam\":{\"operatetype\":\"send\",\"comcode\":\"44030000\",\"suppliercode\":\"szsms\"}},{name:\"卢斌晖\",age:30}]";
		
		JSONArray j = JSONArray.fromObject(json);
		List m = JsonUtil.JsonToJavaContainer(j);
		System.out.println(JsonUtil.toJson(m));
	}
}
