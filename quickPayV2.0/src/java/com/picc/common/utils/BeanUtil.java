/**
 * 作者 卢斌晖
 * 2014-04-21
 */
package com.picc.common.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import ognl.Ognl;
import ognl.OgnlException;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
public class BeanUtil 
{
    private static Logger logger = LoggerFactory.getLogger(BeanUtil.class);
    private static Gson gson = new GsonBuilder().serializeNulls().create();
    public BeanUtil()
    {
    }

    public static void copyProperties(Object dest, Object orig) throws Exception
    {
        try
        {
            org.apache.commons.beanutils.BeanUtils.copyProperties(dest, orig);
        }
        catch(IllegalAccessException e)
        {
            logger.error("", e);
            throw new Exception(e);
        }
        catch(InvocationTargetException e)
        {
            logger.error("", e);
            throw new Exception(e);
        }
    }

    public static JSONObject createJSONObject(Object entity)
        throws Exception
    {
        return createJSONObject(entity, false, false, null);
    }

    public static JSONObject createJSONObjectWithNestedSymbol(Object entity, String nestedSymbol)
        throws Exception
    {
        return createJSONObject(null, entity, false, false, null, false, nestedSymbol);
    }

    public static JSONObject createJSONObjectWithNestedSymbol(Object entity, boolean nullStringToEmpty, String nestedSymbol)
        throws Exception
    {
        return createJSONObject(null, entity, nullStringToEmpty, false, null, true, nestedSymbol);
    }

    public static JSONObject createJSONObject(Object entity, String excludePropertyNames[])
        throws Exception
    {
        return createJSONObject(entity, false, false, excludePropertyNames);
    }

    public static JSONObject createJSONObject(Object entity, boolean nullStringToEmpty, boolean nullNestedObjectToEmptyJSONObject, String excludePropertyNames[])
        throws Exception
    {
        return createJSONObject(entity, nullStringToEmpty, nullNestedObjectToEmptyJSONObject, excludePropertyNames, true);
    }

    public static JSONObject createJSONObject(Object entity, boolean nullStringToEmpty, boolean nullNestedObjectToEmptyJSONObject, String propertyNames[], boolean isExclude)
        throws Exception
    {
        return createJSONObject("", entity, nullStringToEmpty, nullNestedObjectToEmptyJSONObject, propertyNames, isExclude, null);
    }

    private static JSONObject createJSONObject(String parentName, Object entity, boolean nullStringToEmpty, boolean nullNestedObjectToEmptyJSONObject, String propertyNames[], boolean isExclude, String nestedSymbol)
        throws Exception
    {
        if(entity == null)
            return null;
        if(parentName == null)
            parentName = "";
        if(nestedSymbol == null)
            nestedSymbol = ".";
        try
        {
            JSONObject json = new JSONObject();
            PropertyDescriptor origDescriptors[] = org.springframework.beans.BeanUtils.getPropertyDescriptors(entity.getClass());
            for(int i = 0; i < origDescriptors.length; i++)
            {
                String name = origDescriptors[i].getName();
                if((isExclude ? "class".equals(name) || "new".equals(name) || isContainName(propertyNames, (parentName.equals("") ? parentName : parentName + nestedSymbol) + name) : !isContainName(propertyNames, (parentName.equals("") ? parentName : parentName + nestedSymbol) + name)) || origDescriptors[i].getReadMethod() == null)
                    continue;
                Object value = origDescriptors[i].getReadMethod().invoke(entity, null);
                Class clazz = origDescriptors[i].getPropertyType();
                boolean isSimpleProperty = org.springframework.beans.BeanUtils.isSimpleProperty(clazz);
                if(isSimpleProperty)
                {
                    if(value == null)
                    {
                        if(nullStringToEmpty)
                            json.put(name, "");
                    } else
                    {
                        json.put(name, value);
                    }
                    continue;
                }
                JSONObject nestedJson = createJSONObject((parentName.equals("") ? parentName : parentName + nestedSymbol) + name, value, nullStringToEmpty, nullNestedObjectToEmptyJSONObject, propertyNames, isExclude, nestedSymbol);
                if(nestedJson == null)
                {
                    if(nullNestedObjectToEmptyJSONObject)
                        json.put(name, new JSONObject());
                } else
                {
                    json.put(name, nestedJson);
                }
            }

            return json;
        }
        catch(IllegalArgumentException e)
        {
            logger.error("", e);
            throw new Exception(e);
        }
        catch(IllegalAccessException e)
        {
            logger.error("", e);
            throw new Exception(e);
        }
        catch(InvocationTargetException e)
        {
            logger.error("", e);
            throw new Exception(e);
        }
        catch(JSONException e)
        {
            logger.error("", e);
            throw new Exception(e);
        }
        catch(BeansException e)
        {
            logger.error("", e);
            throw new Exception(e);
        }
    }

    private static boolean isContainName(String propertyNames[], String name)
    {
        if(propertyNames == null)
            return false;
        for(int i = 0; i < propertyNames.length; i++)
            if(name.equals(propertyNames[i]))
                return true;
        return false;
    }

    public static Object getValue(Object bean, String methodName, String argName, Object argValue)
        throws Exception
    {
        return getValue(bean, methodName, argName != null ? (new String[] {
            argName
        }) : (String[])null, argValue != null ? (new Object[] {
            argValue
        }) : (Object[])null);
    }

    public static Object getValue(Object bean, String methodName, String argNames[], Object argValues[])
        throws Exception
    {
        Map map = new HashMap();
        StringBuffer methodStr = new StringBuffer(methodName);
        int i;
        try
        {
            if(null == argNames || argNames.length == 0)
            {
                methodStr.append("()");
                return Ognl.getValue(methodStr.toString(), map, bean);
            }
        }
        catch(OgnlException e)
        {
            throw new Exception(e);
        }
        map.put(argNames[0], argValues[0]);
        methodStr.append("(#");
        methodStr.append(argNames[0]);
        for(i = 1; i < argNames.length; i++)
        {
            map.put(argNames[i], argValues[i]);
            methodStr.append(",#");
            methodStr.append(argNames[i]);
        }

        methodStr.append(")");
        if(logger.isDebugEnabled())
        {
            logger.debug(methodStr.toString());
            System.out.println("methodStr=" + methodStr.toString());
        }
        return Ognl.getValue(methodStr.toString(), map, bean);
    }

    public static Object getValue(Object bean, String methodName, Object argValue)
        throws Exception
    {
        return getValue(bean, methodName, argValue != null ? (new Object[] {
            argValue
        }) : (Object[])null);
    }

    public static Object getValue(Object bean, String methodName, Object argValues[])
        throws Exception
    {
        if(argValues == null)
            return getValue(bean, methodName, (String[])null, argValues);
        String argNames[] = new String[argValues.length];
        for(int i = 0; i < argNames.length; i++)
            argNames[i] = "arg" + i;

        return getValue(bean, methodName, argNames, argValues);
    }

    public static String getClassName(Class clazz)
    {
        String fullName = clazz.getName();
        return fullName.substring(fullName.lastIndexOf('.') + 1);
    }
    
    public static Object populate(Object object,Map map)
    {
    	try
    	{
    		BeanUtils.populate(object, map);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return object;
    }
}
