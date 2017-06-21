package com.picc.common.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 1:将JavaBean转换成Map、JSONObject
 * 2:将Map转换成Javabean
 * 3:将JSONObject转换成Map、Javabean
 * 
 * @author Alexia
 */

public class JsonHelper {
	
	protected static final Logger logger = LoggerFactory.getLogger(JsonHelper.class);
    
    /**
     * 将Javabean转换为Map
     * 
     * @param javaBean
     *            javaBean
     * @return Map对象
     */
    public static Map toMap(Object javaBean) {

        Map result = new HashMap();
        Method[] methods = javaBean.getClass().getDeclaredMethods();

        for (Method method : methods) {

            try {

                if (method.getName().startsWith("get")) {

                    String field = method.getName();
                    field = field.substring(field.indexOf("get") + 3);
                    field = field.toLowerCase().charAt(0) + field.substring(1);

                    Object value = method.invoke(javaBean, (Object[]) null);
                    result.put(field, null == value ? "" : value.toString());

                }

            } catch (Exception e) {
                e.printStackTrace();
				logger.error("", e);
            }

        }

        return result;

    }

    /**
     * 将Json对象转换成Map
     * 
     * @param jsonObject
     *            json对象
     * @return Map对象
     * @throws JSONException
     */
    public static Map toMap(String jsonString) throws JSONException {

        JSONObject jsonObject = new JSONObject(jsonString);
        
        Map result = new HashMap();
        Iterator iterator = jsonObject.keys();
        String key = null;
        String value = null;
        
        while (iterator.hasNext()) {

            key = (String) iterator.next();
            value = jsonObject.getString(key);
            result.put(key, value);

        }
        return result;

    }

    /**
     * 将JavaBean转换成JSONObject（通过Map中转）
     * 
     * @param bean
     *            javaBean
     * @return json对象
     */
    public static JSONObject toJSON(Object bean) {

        return new JSONObject(toMap(bean));

    }

    /**
     * 将Map转换成Javabean
     * 
     * @param javabean
     *            javaBean
     * @param data
     *            Map数据
     */
    public static Object toJavaBean(Object javabean, Map data) {

        Method[] methods = javabean.getClass().getDeclaredMethods();
        for (Method method : methods) {

            try {
                if (method.getName().startsWith("set")) {

                    String field = method.getName();
                    field = field.substring(field.indexOf("set") + 3);
                    field = field.toLowerCase().charAt(0) + field.substring(1);
                    method.invoke(javabean, new Object[] {

                    data.get(field)

                    });

                }
            } catch (Exception e) {
            }

        }

        return javabean;

    }

    /**
     * JSONObject到JavaBean
     * 
     * @param bean
     *            javaBean
     * @return json对象
     * @throws ParseException
     *             json解析异常
     * @throws JSONException
     */
    public static void toJavaBean(Object javabean, String jsonString)
            throws ParseException, JSONException {

        JSONObject jsonObject = new JSONObject(jsonString);
    
        Map map = toMap(jsonObject.toString());
        
        toJavaBean(javabean, map);

    }
    
    public static void main(String[] args) {
    	/*PrpMcbcMainRequest mcbc=new PrpMcbcMainRequest();
		List list =new ArrayList();
		CMBCCustomerInfoVo vo =new CMBCCustomerInfoVo();
		vo.setAddressCName("111");
		CMBCCustomerInfoVo vo1 =new CMBCCustomerInfoVo();
		vo1.setAddressCName("222");
		list.add(vo1);
		list.add(vo);
		mcbc.setBody(list);
		String a=JSONUtil.formJSON(mcbc);
		a=a.replaceAll("addressCName", "addresscname");
		a=a.replaceAll("body", "BOdy");
		  try {
		    PrpMcbcMainRequest toBean=new PrpMcbcMainRequest();
			toJavaBean(toBean,a);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();*/
    	JsonHelper h =new JsonHelper();
//    	h.fromJson("a",CMBCCustomerInfoVo.class);
	}
    
    public <T> T fromJson(String json, Class<T> classOfT){
    	    System.out.println("classoft");
    	    fromJson(json,classOfT);
    	    return null;
     }

     public <T> T fromJson(String json, Type typeOfT){
    	 System.out.println("Type");
    	 return null;
     }

}