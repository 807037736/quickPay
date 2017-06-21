package ins.framework.web;

import ins.framework.cache.CacheManager;
import ins.framework.cache.CacheService;
import ins.framework.common.Page;
import ins.framework.utils.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.opensymphony.xwork2.ActionSupport;
import com.picc.common.utils.ToolsUtils;
import com.picc.tm.schema.model.TmTApplicationConfig;
import com.picc.tm.service.facade.ITmTApplicationConfigService;
import com.picc.um.schema.vo.SessionUser;
import com.picc.um.schema.vo.UmTUserBindVo;
import com.picc.um.service.facade.IUmTUserBindService;

/**
 * 封装Struts 2的ActionSupport的基类. 提供一些基本的简化函数,将不断增强.
 * 
 * @author zhouxianli
 * @modify diyvan
 */
public class Struts2Action extends ActionSupport {
	private static final long serialVersionUID = 1L;

	// protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	protected final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	/** 页号 */
	protected int pageNo;

	/** 每页每页的记录数 */
	protected int pageSize;

	/** 页号 easy-ui 使用 */
	protected int page;

	/** 每页的记录数 easy-ui 使用 */
	protected int rows;

	/** 排序方式 easy-ui使用：asc desc **/
	protected String order;
	/** 排序字段 easy-ui传过来的排序字段 **/
	protected String sort;
	/** 返回Url **/
	protected String returnUrl;
	
	//内网服务标识
	public static String NET_INNER = "inner";
	
	//外网服务标识
	public static String NET_OUTER = "outer";
	
	private static CacheService cognosConfigCacheService = CacheManager.getInstance("COGNOS_CONFIG");
	
	protected String serverCode;
	
	protected String serverName;

	private ITmTApplicationConfigService tmTApplicationConfigService;
	
	private IUmTUserBindService umTUserBindService;
	
	public String getServerCode() {
		return serverCode;
	}

	public void setServerCode(String serverCode) {
		this.serverCode = serverCode;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public ITmTApplicationConfigService getTmTApplicationConfigService() {
		return tmTApplicationConfigService;
	}

	public void setTmTApplicationConfigService(
			ITmTApplicationConfigService tmTApplicationConfigService) {
		this.tmTApplicationConfigService = tmTApplicationConfigService;
	}
	
	public IUmTUserBindService getUmTUserBindService() {
		return umTUserBindService;
	}

	public void setUmTUserBindService(IUmTUserBindService umTUserBindService) {
		this.umTUserBindService = umTUserBindService;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	/**
	 * 直接输出.
	 * 
	 * @param contentType
	 *            内容的类型.html,text,xml的值见后，json为"text/x-json;charset=UTF-8"
	 */
	protected void render(String text, String contentType) {
		try {
			HttpServletResponse response = getResponse();
			response.setContentType(contentType);
			response.getWriter().write(text);
			response.getWriter().flush();
		} catch (IOException e) {
			//logger.error(e.getMessage(), e);
			throw new IllegalStateException(e);
		}
	}

	/**
	 * 直接输出纯字符串.
	 */
	protected void renderText(String text) {
		render(text, "text/plain;charset=UTF-8");
	}

	/**
	 * 直接输出纯HTML.
	 */
	protected void renderHtml(String text) {
		render(text, "text/html;charset=UTF-8");
	}

	/**
	 * 直接输出纯XML.
	 */
	protected void renderXML(String text) {
		render(text, "text/xml;charset=UTF-8");
	}

	@SuppressWarnings("unchecked")
	protected void saveMessage(String msg) {
		List messages = (List) getRequest().getSession().getAttribute(
				"messages");
		if (messages == null) {
			messages = new ArrayList();
		}
		messages.add(msg);
		getSession().setAttribute("messages", messages);
	}

	/**
	 * 获取request的便利方法
	 * 
	 * @return 当前request
	 */
	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 获取servletContext的便利方法
	 * 
	 * @return 当前ServletContext
	 */
	protected ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	/**
	 * 获取response的便利方法
	 * 
	 * @return 当前response
	 */
	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 获取session的便利方法,如果当前不存在将自动新建一个。
	 * 
	 * @return 从request中获取(request.getSession())
	 */
	protected HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * 获取session的便利方法,如果当前不存在且flag为true将自动新建一个。
	 * 
	 * @return 从request中获取(request.getSession(flag))
	 */
	protected HttpSession getSession(boolean flag) {
		return getRequest().getSession(flag);
	}

	/**
	 * 写JSON对象
	 * 
	 * @param page
	 *            要写的对象
	 * @param args
	 *            需要显示的属性，支持变参,至少要有1个
	 */
	@SuppressWarnings("u6nchecked")
	public void writeJSONData(Page page, String... args) {
		try {
			Assert.notEmpty(args);
			List dataList = new ArrayList();
			List list = (List) page.getResult();
			int size = args.length;

			for (Iterator iter = list.iterator(); iter.hasNext();) {
				Object element = (Object) iter.next();
				Map dataMap = new LinkedHashMap(size);
				for (int i = 0; i < size; i++) {
					Object value = null;
					if (args[i].indexOf('.') > -1) {
						String[] arrArg = StringUtils.split(args[i], '.');
						value = element;
						for (int j = 0; j < arrArg.length - 1; j++) {
							value = PropertyUtils.getProperty(value, arrArg[j]);
							value = PropertyUtils.getProperty(value,
									arrArg[j + 1]);
						}// end for
					} else {
						value = PropertyUtils.getProperty(element, args[i]);
					}// end if
					dataMap.put(args[i], fixValueForJSON(value));
				}
				dataList.add(dataMap);
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("recordsReturned", page.getPageSize());
			jsonObject.put("startIndex", page.getStart());
			jsonObject.put("totalRecords", page.getTotalCount());
			jsonObject.put("data", JSONArray.fromObject(dataList));
			renderHtml(jsonObject.toString());
		} catch (Exception e) {
			logger.error("", e);
			writeJSONMsg(e.getMessage());
		}

	}

	/**
	 * 写JSON对象
	 * 
	 * @param list
	 *            要写的对象
	 * @param args
	 *            需要显示的属性，支持变参,至少要有1个
	 */
	@SuppressWarnings("unchecked")
	public void writeJSONData(List list, String... args) {
		try {
			Assert.notEmpty(args);
			List dataList = new ArrayList();
			int size = args.length;

			for (Iterator iter = list.iterator(); iter.hasNext();) {
				Object element = (Object) iter.next();
				Map dataMap = new HashMap(size);
				for (int i = 0; i < size; i++) {
					Object value = null;
					if (args[i].indexOf('.') > -1) {
						String[] arrArg = StringUtils.split(args[i], '.');
						value = element;
						for (int j = 0; j < arrArg.length - 1; j++) {
							value = PropertyUtils.getProperty(value, arrArg[j]);
							value = PropertyUtils.getProperty(value,
									arrArg[j + 1]);
						}// end for
					} else {
						value = PropertyUtils.getProperty(element, args[i]);
					}// end if
					dataMap.put(args[i], fixValueForJSON(value));
				}
				dataList.add(dataMap);
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("total", list.size());
			jsonObject.put("rows", JSONArray.fromObject(dataList));
			renderHtml(jsonObject.toString());
		} catch (Exception e) {
			writeJSONMsg(e.getMessage());
		}
	}

	/**
	 * 修正对象值(限用于writeJSON)
	 * 
	 * @param value
	 *            对象值
	 * @return 修正后的值
	 */
	private Object fixValueForJSON(Object value) {
		Object retObject = value;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if (value instanceof java.util.Date) {
			retObject = format.format(value);
		}
		return retObject;
	}

	/**
	 * 报出异常信息
	 * @param msg
	 */
	public void writeJSONMsg(String msg) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", msg);
		jsonObject.put("totalRecords", 0);
		jsonObject.put("data", JSONArray.fromObject(new String[] {}));
		renderHtml(jsonObject.toString());
	}
	
	/**
	 * 报出异常信息
	 * 
	 * @param msg
	 */
	public void writeJSONStateMsg(String msg,boolean processReslutState) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", msg);
		jsonObject.put("state", processReslutState);
		jsonObject.put("totalRecords", 0);
		jsonObject.put("data", JSONArray.fromObject(new String[] {}));
		renderHtml(jsonObject.toString());
	}

	public void writeString(String str) {
		renderHtml(str);
	}
	
	/**
	 * 根据map，将map中的信息写到前台,直接返回501（未被使用异常代码）错误
	 * @param map
	 */
	public void writeAjaxErrorByMap(Map map) {
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Date.class, new JsonValueFormat());
		JSONObject jsonObject = JSONObject.fromObject(map, config);
		try {
			HttpServletResponse response = getResponse();
			response.setContentType("text/html;charset=UTF-8");
			response.setStatus(501);//直接返回501异常
			response.getWriter().write(jsonObject.toString());
			response.getWriter().flush();
		} catch (IOException e) {
			//logger.error(e.getMessage(), e);
			throw new IllegalStateException(e);
		}
	}
	
	/**
	 * 根据map，将map中的信息写到前台
	 * @param map
	 */
	public void writeAjaxByMap(Map map) {
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Date.class, new JsonValueFormat());
		JSONObject jsonObject = JSONObject.fromObject(map, config);
		try {
			HttpServletResponse response = getResponse();
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(jsonObject.toString());
			response.getWriter().flush();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
	
	/**
	* @Title: writeErrorMessage
	* @Description: 返回错误信息
	* @param @param text					错误信息
	* @param @throws Exception    设定文件
	* @return void    返回类型
	* @throws
	* @author jiangweiyang
	* @date 2014/01/13
	 */
	public void writeErrorMessage(String text) throws Exception  {
		HttpServletResponse response = getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setStatus(501);//直接返回501异常
		response.getWriter().write(text);
		response.getWriter().flush();
	}
	
	

	/**
	 * 写JSON对象
	 * 
	 * @param page
	 *            要写的对象
	 * @param args
	 *            需要显示的属性
	 */
	@SuppressWarnings("unchecked")
	public void writeEasyUiData(Page page) {
		try {
			List dataList = (List) page.getResult();
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(Date.class, new JsonValueFormat());
			Map map = new HashMap();
			map.put("recordsReturned", page.getPageSize());
			map.put("startIndex", page.getStart());
			map.put("total", page.getTotalCount());
			map.put("rows", dataList);
			
			JSONObject jsonObject = JSONObject.fromObject(map, config);
			renderHtml(jsonObject.toString());
		} catch (Exception e) {
			writeJSONMsg(e.getMessage());
		}

	}
	/**
	 * BigDecimal类型在值为null时不做转换，保留null
	 * @param page
	 */
	public void writeEasyUiDataBigDecimal(Page page) {
		try {
			List dataList = (List) page.getResult();
			JsonConfig config = new JsonConfig();
			config.registerDefaultValueProcessor(BigDecimal.class,
					new DefaultValueProcessor(){
				public Object getDefaultValue(Class type){
					return null;
				}
			});
			config.registerJsonValueProcessor(Date.class, new JsonValueFormat());
			Map map = new HashMap();
			map.put("recordsReturned", page.getPageSize());
			map.put("startIndex", page.getStart());
			map.put("total", page.getTotalCount());
			map.put("rows", dataList);
			
			JSONObject jsonObject = JSONObject.fromObject(map, config);
			renderHtml(jsonObject.toString());
		} catch (Exception e) {
			writeJSONMsg(e.getMessage());
		}

	}

	/**
	 * 修正了某些主键字段无法在前台显示的问题
	 * 
	 * @author liuyatao 2013-7-24
	 * @param page
	 */
	@SuppressWarnings("unchecked")
	public void writeJSONData(Page page) {
		List dataList = (List) page.getResult();
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Date.class, new JsonValueFormat());
		JSONArray jsonArr = JSONArray.fromObject(dataList, config);
		JSONObject json = null;
		JSONObject child = null;
		JSONObject cc = null;
		String key = "";
		String value = "";
		Iterator iter = null;
		for (int i = 0; i < jsonArr.size(); i++) {
			json = jsonArr.getJSONObject(i);
			child = (JSONObject) json.get("id");
			iter = child.keys();
			while (iter.hasNext()) {
				key = iter.next().toString();
				value = child.getString(key);
				json.put(key, value);
			}
			json.remove("id");
		}
		renderHtml(jsonArr.toString());
	}

	
	/**
	 * 写JSON对象
	 * 用于带出原角色数据和经过修改的Page对象的值的List,Page传过来为了参记录数大小和页数 
	 * @param page
	 *            要写的对象
	 * @param args
	 *            需要显示的属性
	 *            YangLiu  2013 - 10 - 29
	 */
	@SuppressWarnings("unchecked")
	public void writeEasyUiData(Page page, List list) {
		try {
			List dataList = list;
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(Date.class, new JsonValueFormat());
			Map map = new HashMap();
			map.put("recordsReturned", page.getPageSize());
			map.put("startIndex", page.getStart());
			map.put("total", page.getTotalCount());
			map.put("rows", dataList);
			JSONObject jsonObject = JSONObject.fromObject(map, config);
			renderHtml(jsonObject.toString());
		} catch (Exception e) {
			writeJSONMsg(e.getMessage());
		}
	}
	
	
	/**
	 * 重载list转换成json显示的方法。调用只需list参数
	 * 
	 * @param list
	 */
	public void writeEasyUiData(List list) {
		try {
			List dataList = list;
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(Date.class, new JsonValueFormat());
			Map map = new HashMap();
			map.put("total", list.size());
			map.put("rows", dataList);
			JSONObject jsonObject = JSONObject.fromObject(map, config);
			renderHtml(jsonObject.toString());
		} catch (Exception e) {
			writeJSONMsg(e.getMessage());
		}

	}

	/**
	 * 写JSON对象 by liuyatao2013年7月12日
	 * 
	 * @param String
	 *            要写的对象
	 */
	@SuppressWarnings("unchecked")
	public void writeEasyUiData(String result) {
		try {
			JSONArray jsonArray = JSONArray.fromObject(result);
			JSONObject json = new JSONObject();
			json.put("total", jsonArray.size());
			json.put("rows", jsonArray);
			writeEasyUiData(json);
		} catch (JSONException e) {
			writeJSONMsg(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public void writeEasyUiData(JSONArray result) {
		try {
			Map map = new HashMap();
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(Date.class, new JsonValueFormat());
			map.put("total", result.size());
			map.put("rows", result);
			JSONObject jsonObject = JSONObject.fromObject(map, config);
			renderHtml(jsonObject.toString());
		} catch (Exception e) {
			writeJSONMsg(e.getMessage());
		}

	}
	
	/**
	 * @author lishuchen01
	 * 将后台list写入Json，装入responson返回前台,比较适合easyui
	 * @param object
	 */
	protected void writeJsonObjectArray(Object object) {
		try {
			// 处理对象的嵌套引用
			// 设置JSON-LIB的setCycleDetectionStrategy属性让其自己处理循环
			JsonConfig config = new JsonConfig();
			config.setIgnoreDefaultExcludes(false);
			config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);

			JSONArray json = JSONArray.fromObject(object, config);
			renderHtml(json.toString());
		} catch (Exception e) {
			writeJSONMsg(e.getMessage());
		}
	}
	
	/**
	 * @author lishuchen01
	 * 将后台对象写入Json，装入responson返回前台,比较适合easyui
	 * @param object
	 */
	protected void writeJsonObject(Object object) {
		try {
			// 处理对象的嵌套引用
			// 设置JSON-LIB的setCycleDetectionStrategy属性让其自己处理循环
			JsonConfig config = new JsonConfig();
			config.setIgnoreDefaultExcludes(false);
			config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			config.registerJsonValueProcessor(Date.class, new JsonValueFormat());
			JSONObject json = JSONObject.fromObject(object, config);
			renderHtml(json.toString());
		} catch (Exception e) {
			writeJSONMsg(e.getMessage());
		}
	}
	
	private boolean isCollection(Object o) {
		if (o == null)
			return false;
		@SuppressWarnings("rawtypes")
		Class c = o.getClass();
		if ((c.isInterface())
				&& ((Set.class.isAssignableFrom(c)) || (List.class.isAssignableFrom(c)))) {
			return true;
		}
		return (c.isArray()) || (Collection.class.isAssignableFrom(c));
	}
	
	/**
	 * @author lishuchen01
	 * 将后台对象写入Json，装入responson返回前台,比较适合easyui
	 * @param object
	 */
	public void writeJson(Object object){
		if(isCollection(object)){
			//是集合对象：如list之类的
			writeJsonObjectArray(object);
		}else{
			//对象
			writeJsonObject(object);
		}
	}

	@SuppressWarnings("unchecked")
	public void writeEasyUiData(JSONObject result) {
		try {
			renderHtml(result.toString());
		} catch (Exception e) {
			writeJSONMsg(e.getMessage());
		}

	}
	
	
	/**
	* @Title: checkRequestNet
	* @Description: 		判断当前请求来源于内网/外网
	* @return String    内外网识别字段
	* @throws
	 */
	@SuppressWarnings("unchecked")
	protected String checkRequestNet(String requestHost) throws Exception {
		if(requestHost!=null){
			String netCacheKey = cognosConfigCacheService.generateCacheKey("NET",getUserFromSession().getComId());
			List<String> netIpList = (List<String>)cognosConfigCacheService.getCache(netCacheKey);
			//截取请求的主机地址
			String ipHost = null;
			if(requestHost.indexOf(":")!=-1){
				ipHost = requestHost.substring(0, requestHost.indexOf(":"));
			}else {
				ipHost =  requestHost;
			}
			if(netIpList!=null && netIpList.contains(ipHost)){
				return NET_OUTER;
			}else {
				return NET_INNER;
			}
		}else {
			return NET_INNER;
		}
	}
	
	
	/**
	 * 从Session中获取用户对象
	 * @return							用户对象
	 * 2013-8-23上午10:37:08
	 * jiangweiyang
	 */
	public SessionUser getUserFromSession(){
		return (SessionUser)getRequest().getSession().getAttribute("SessionUser");
	}
	
	

	/**
	 * 设置页号
	 * 
	 * @param pageNo
	 *            页号
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * 设置每页每页的记录数
	 * 
	 * @param pageSize
	 *            每页每页的记录数
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获取页号
	 * 
	 * @return 页号
	 */

	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 获取每页的记录数
	 * 
	 * @return 每页的记录数
	 */
	public int getPageSize() {
		return pageSize;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	
	protected String getCurrentServerCode() {
		TmTApplicationConfig tmTApplicationConfig = null;
		try {
			tmTApplicationConfig = tmTApplicationConfigService.getApplicationByContext(this.getRequest().getContextPath().replaceFirst("/", ""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("", e);
		}
		if(tmTApplicationConfig != null)
			return tmTApplicationConfig.getId().getServerCode();
		else
			return null;
	}
	
	protected boolean weiXinLogin(String param){
		boolean flag = false;
		try {
			if (ToolsUtils.notEmpty(param)) {
			    this.getSession().setAttribute("WX_USER_PARAM", param);
			} else {
			    param = (String) this.getSession().getAttribute("WX_USER_PARAM");
			}

			String platId = "";
			String openId = "";
			if (ToolsUtils.notEmpty(param)) {
			    String[] params = param.split("-");
			    platId = params[0];
			    openId = params[1];
			}
			List<UmTUserBindVo> userList = new ArrayList<UmTUserBindVo>();
			if (ToolsUtils.notEmpty(platId) && ToolsUtils.notEmpty(openId)) {
//				userList = umTUserBindService.findCustIdByOpenid(ToolsUtils.toStringHex(platId), ToolsUtils.toStringHex(openId));
				userList = umTUserBindService.findRegistUserByOpenid(ToolsUtils.toStringHex(platId), ToolsUtils.toStringHex(openId));
				if (!ToolsUtils.isEmpty(userList)) {
					UmTUserBindVo umTUserBindVo = userList.get(0);
					String userCode = umTUserBindVo.getUserCode();
					this.getRequest().getSession().setAttribute("userCode_weixin", userCode);
					flag = true;
				} 
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
}
