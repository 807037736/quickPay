/*     */ package com.picc.common.utils;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;

/*     */ import net.sf.json.JSONArray;
/*     */ import net.sf.json.JSONObject;

/*     */ import org.apache.commons.beanutils.BeanUtils;
/*     */ 
/*     */ 
/*     */ public class JSONUtil
/*     */ {
/*     */   public static String formSimpleSuccessJSON()
/*     */   {
/*  29 */     Map jsonMap = new HashMap();
/*  30 */     jsonMap.put("success", Boolean.valueOf(true));
/*  31 */     jsonMap.put("error", "");
/*  32 */     JSONObject json = JSONObject.fromObject(jsonMap);
/*  33 */     return json.toString();
/*     */   }
/*     */ 
/*     */   public static String formSimpleErrorJSON(String errormsg)
/*     */   {
/*  42 */     Map jsonMap = new HashMap();
/*  43 */     jsonMap.put("success", Boolean.valueOf(false));
/*  44 */     jsonMap.put("error", errormsg);
/*  45 */     JSONObject json = JSONObject.fromObject(jsonMap);
/*  46 */     return json.toString();
/*     */   }
/*     */ 
/*     */   public static String formJSON(Map map)
/*     */   {
/*  55 */     JSONObject json = JSONObject.fromObject(map);
/*  56 */     return json.toString();
/*     */   }
/*     */ 
/*     */   public static String formJSON(Object object)
/*     */   {
/*  65 */     JSONObject json = JSONObject.fromObject(object);
/*  66 */     return json.toString();
/*     */   }
/*     */ 
/*     */   public static <T> String toJSONString(List<T> list)
/*     */   {
/*  74 */     JSONArray jsonArray = JSONArray.fromObject(list);
/*  75 */     return jsonArray.toString();
/*     */   }
/*     */ 
/*     */   public static String toJSONString(Object object)
/*     */   {
/*  85 */     JSONObject jsonObject = JSONObject.fromObject(object);
/*  86 */     return jsonObject.toString();
/*     */   }
/*     */ 
/*     */   public static String toJSONString(JSONArray jsonArray)
/*     */   {
/*  96 */     return jsonArray.toString();
/*     */   }
/*     */ 
/*     */   public static String toJSONString(JSONObject jsonObject)
/*     */   {
/* 106 */     return jsonObject.toString();
/*     */   }
/*     */ 
/*     */   public static List toArrayList(Object object)
/*     */   {
/* 116 */     List arrayList = new ArrayList();
/*     */ 
/* 118 */     JSONArray jsonArray = JSONArray.fromObject(object);
/*     */ 
/* 120 */     Iterator it = jsonArray.iterator();
/* 121 */     while (it.hasNext())
/*     */     {
/* 123 */       JSONObject jsonObject = (JSONObject)it.next();
/*     */ 
/* 125 */       Iterator keys = jsonObject.keys();
/* 126 */       while (keys.hasNext())
/*     */       {
/* 128 */         Object key = keys.next();
/* 129 */         Object value = jsonObject.get(key);
/* 130 */         arrayList.add(value);
/*     */       }
/*     */     }
/*     */ 
/* 134 */     return arrayList;
/*     */   }
/*     */ 
/*     */   public static List<Map<String, Object>> toMapList(Object object)
/*     */   {
/* 143 */     List arrayList = new ArrayList();
/* 144 */     JSONArray jsonArray = JSONArray.fromObject(object);
/* 145 */     Iterator it = jsonArray.iterator();
/* 146 */     while (it.hasNext())
/*     */     {
/* 148 */       JSONObject jsonObject = (JSONObject)it.next();
/* 149 */       Iterator keys = jsonObject.keys();
/* 150 */       Map map = new HashMap();
/* 151 */       while (keys.hasNext())
/*     */       {
/* 153 */         Object key = keys.next();
/* 154 */         Object value = jsonObject.get(key);
/* 155 */         map.put(String.valueOf(key), value);
/*     */       }
/* 157 */       arrayList.add(map);
/*     */     }
/*     */ 
/* 160 */     return arrayList;
/*     */   }
/*     */ 
/*     */   public static Collection toCollection(Object object)
/*     */   {
/* 169 */     JSONArray jsonArray = JSONArray.fromObject(object);
/* 170 */     return JSONArray.toCollection(jsonArray);
/*     */   }
/*     */ 
/*     */   public static JSONArray toJSONArray(Object object)
/*     */   {
/* 180 */     return JSONArray.fromObject(object);
/*     */   }
/*     */ 
/*     */   public static JSONObject toJSONObject(Object object)
/*     */   {
/* 190 */     return JSONObject.fromObject(object);
/*     */   }
/*     */ 
/*     */   public static HashMap toHashMap(Object object)
/*     */   {
/* 200 */     HashMap data = new HashMap();
/* 201 */     JSONObject jsonObject = toJSONObject(object);
/* 202 */     Iterator it = jsonObject.keys();
/* 203 */     while (it.hasNext())
/*     */     {
/* 205 */       String key = String.valueOf(it.next());
/* 206 */       Object value = jsonObject.get(key);
/* 207 */       data.put(key, value);
/*     */     }
/* 209 */     return data;
/*     */   }
/*     */ 
/*     */   public static List<Map<String, Object>> toList(Object object)
/*     */   {
/* 220 */     List list = new ArrayList();
/* 221 */     JSONArray jsonArray = JSONArray.fromObject(object);
/* 222 */     for (Iterator localIterator1 = jsonArray.iterator(); localIterator1.hasNext(); ) { Object obj = localIterator1.next();
/*     */ 
/* 224 */       JSONObject jsonObject = (JSONObject)obj;
/* 225 */       Map map = new HashMap();
/* 226 */       Iterator it = jsonObject.keys();
/* 227 */       while (it.hasNext())
/*     */       {
/* 229 */         String key = (String)it.next();
/* 230 */         Object value = jsonObject.get(key);
/* 231 */         map.put(key, value);
/*     */       }
/* 233 */       list.add(map);
/*     */     }
/* 235 */     return list;
/*     */   }
/*     */ 
/*     */   public static <T> List<T> toList(JSONArray jsonArray, Class<T> objectClass)
/*     */   {
/* 247 */     return JSONArray.toList(jsonArray, objectClass);
/*     */   }
/*     */ 
/*     */   public static <T> List<T> toList(Object object, Class<T> objectClass)
/*     */   {
/* 259 */     JSONArray jsonArray = JSONArray.fromObject(object);
/*     */ 
/* 261 */     return JSONArray.toList(jsonArray, objectClass);
/*     */   }
/*     */ 
/*     */   public static <T> T toBean(JSONObject jsonObject, Class<T> beanClass)
/*     */   {
/* 273 */     return (T)JSONObject.toBean(jsonObject, beanClass);
/*     */   }
/*     */ 
/*     */   public static <T> T toBean(Object object, Class<T> beanClass)
/*     */   {
/* 285 */     JSONObject jsonObject = JSONObject.fromObject(object);
/* 286 */     return (T)JSONObject.toBean(jsonObject, beanClass);
/*     */   }
/*     */ 
/*     */   public static <T, D> T toBean(String jsonString, Class<T> mainClass, String detailName, Class<D> detailClass)
/*     */   {
/* 302 */     JSONObject jsonObject = JSONObject.fromObject(jsonString);
/* 303 */     JSONArray jsonArray = (JSONArray)jsonObject.get(detailName);
/*     */ 
/* 305 */     Object mainEntity = toBean(jsonObject, mainClass);
/* 306 */     List detailList = toList(jsonArray, detailClass);
/*     */     try
/*     */     {
/* 310 */       BeanUtils.setProperty(mainEntity, detailName, detailList);
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 314 */       throw new RuntimeException("主从关系JSON反序列化实体失败！");
/*     */     }
/*     */ 
/* 317 */     return (T)mainEntity;
/*     */   }
/*     */ 
/*     */   public static <T, D1, D2> T toBean(String jsonString, Class<T> mainClass, String detailName1, Class<D1> detailClass1, String detailName2, Class<D2> detailClass2)
/*     */   {
/* 337 */     JSONObject jsonObject = JSONObject.fromObject(jsonString);
/* 338 */     JSONArray jsonArray1 = (JSONArray)jsonObject.get(detailName1);
/* 339 */     JSONArray jsonArray2 = (JSONArray)jsonObject.get(detailName2);
/*     */ 
/* 341 */     Object mainEntity = toBean(jsonObject, mainClass);
/* 342 */     List detailList1 = toList(jsonArray1, detailClass1);
/* 343 */     List detailList2 = toList(jsonArray2, detailClass2);
/*     */     try
/*     */     {
/* 347 */       BeanUtils.setProperty(mainEntity, detailName1, detailList1);
/* 348 */       BeanUtils.setProperty(mainEntity, detailName2, detailList2);
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 352 */       throw new RuntimeException("主从关系JSON反序列化实体失败！");
/*     */     }
/*     */ 
/* 355 */     return (T)mainEntity;
/*     */   }
/*     */ 
/*     */   public static <T, D1, D2, D3> T toBean(String jsonString, Class<T> mainClass, String detailName1, Class<D1> detailClass1, String detailName2, Class<D2> detailClass2, String detailName3, Class<D3> detailClass3)
/*     */   {
/* 378 */     JSONObject jsonObject = JSONObject.fromObject(jsonString);
/* 379 */     JSONArray jsonArray1 = (JSONArray)jsonObject.get(detailName1);
/* 380 */     JSONArray jsonArray2 = (JSONArray)jsonObject.get(detailName2);
/* 381 */     JSONArray jsonArray3 = (JSONArray)jsonObject.get(detailName3);
/*     */ 
/* 383 */     Object mainEntity = toBean(jsonObject, mainClass);
/* 384 */     List detailList1 = toList(jsonArray1, detailClass1);
/* 385 */     List detailList2 = toList(jsonArray2, detailClass2);
/* 386 */     List detailList3 = toList(jsonArray3, detailClass3);
/*     */     try
/*     */     {
/* 390 */       BeanUtils.setProperty(mainEntity, detailName1, detailList1);
/* 391 */       BeanUtils.setProperty(mainEntity, detailName2, detailList2);
/* 392 */       BeanUtils.setProperty(mainEntity, detailName3, detailList3);
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 396 */       throw new RuntimeException("主从关系JSON反序列化实体失败！");
/*     */     }
/*     */ 
/* 399 */     return (T)mainEntity;
/*     */   }
/*     */ 
/*     */   public static <T> T toBean(String jsonString, Class<T> mainClass, HashMap<String, Class> detailClass)
/*     */   {
/* 413 */     JSONObject jsonObject = JSONObject.fromObject(jsonString);
/* 414 */     Object mainEntity = toBean(jsonObject, mainClass);
/* 415 */     for (Iterator localIterator = detailClass.keySet().iterator(); localIterator.hasNext(); ) { Object key = localIterator.next();
/*     */       try
/*     */       {
/* 419 */         Class value = (Class)detailClass.get(key);
/* 420 */         BeanUtils.setProperty(mainEntity, key.toString(), value);
/*     */       }
/*     */       catch (Exception ex)
/*     */       {
/* 424 */         throw new RuntimeException("主从关系JSON反序列化实体失败！");
/*     */       }
/*     */     }
/* 427 */     return (T)mainEntity;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\
 * Qualified Name:     com.picc.mktg.common.util.JSONUtil
 * JD-Core Version:    0.6.0
 */