package com.picc.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.picc.qp.schema.model.QpTCOMCity;
import com.picc.qp.schema.model.QpTCOMDistrict;
import com.picc.qp.schema.model.QpTCOMProvince;

/**
 * 
 * @author hp
 * 存放系统要用到的常量
 *
 */
public class Constants {
	
	/*****************有关树的常量start*******************/
	//树的类型
	//add by shenyichan  2013-08-07
	public static final String COMPANY_TREE = "0";        //机构树
	public static final String COMPANY_USER_TREE = "1";   //机构—人员树
	public static final String ROLE_TREE = "2";           //角色树
	public static final String COMPANY_ROLE_TREE = "3";   //机构角色树
	public static final String TASK_TREE = "4";           //功能树
	public static final String PROCESS_GROUP_TREE = "5";  //流程角色树
	public static final String GROUP_USER_TREE = "6";     //流程角色人员树
	public static final String HEADCOM_TREE = "7";        //只到省公司的机构树
	public static final String MENU_TREE = "8";           //菜单树
	public static final String PORTAL_TREE = "9";         //门户树
	public static final String USERTASK_TREE = "10";		//用户功能树
	public static final String COMPANY_COMID_TREE = "11";        //机构树--根据comid查
	public static final String MONOPOLY_COMPANY_TREE = "12";     //车行树--根据comCode和userCode查
	public static final String MONOPOLY_USER_TREE = "13"; 
	public static final String COMMAND_TREE_WX="14";//微信关键字树
	//车行-人员树
	public static final String MENU_TREE_WX="15";
	//树的查询类型
	//add by shenyichan 2013-08-07
	public static final String QUERY_SELF = "0";         //查询本身
	public static final String QUERY_SUB = "1";          //查询子节点
	
	//树的节点类型，页面上勾选节点时需作判断勾选的节点的类型
	//add by shenyichan 2013-08-07
	public static final String COMPANY_NODE = "1";   //机构节点
	public static final String ROLE_NODE = "2";      //角色节点
	public static final String Group_NODE = "3";     //自定义角色节点
	public static final String USER_NODE = "4";      //人员节点
	public static final String ROLE_GROUP_NODE = "5";//流程管理岗
	public static final String ROLE_USER_NODE = "6"; //流程管理岗 所有角色人员
	public static final String MONOPOLY_NODE = "7";  //车行节点
	
	//add by liuyatao 2013-8-23 记录树节点的关闭或打开状态
	public static final String OPEN = "open";     //打开
	public static final String CLOSE = "closed";      //关闭
	/***************有关树的常量end**********************/
	
	/*****************有关流程操作的常量******************/
	//定义任务对象的类型 liuyatao 2013-8-27
	public static final String POLICY_TYPE = "1";
	public static final String CAR_TYPE = "2";
	public static final String CUSTOMER_TYPE = "3";
	/*****************有关流程操作的常量end******************/
	
	/***************有关dwr的常量start**********************/
	//add by:shenyichan
	//key:JSP页面的请求名
	//value:转发路径的值
	public static Map<String, String> PAGE_SET = new HashMap<String, String>();
	public static Map<String, String> TYPE_SET = new HashMap<String, String>();
	public static final Map<String,String> LOG_TYPE = new HashMap<String, String>();
	static{
		PAGE_SET.put("head", "/pdfbpoc/pages/head.do");
		PAGE_SET.put("showMenu", "/pdfbpoc/menu/showMenu.do");
		PAGE_SET.put("main", "/pdfbpoc/menu/main.jsp");
		
		TYPE_SET.put("0", "组织机构");
		TYPE_SET.put("1", "除外机构");
		TYPE_SET.put("2", "岗位");
		TYPE_SET.put("3", "人员");
		TYPE_SET.put("4", "其它");
		
		LOG_TYPE.put("login", "10");
		LOG_TYPE.put("user_edit", "11");
	}
	/***************有关dwr的常量end**********************/
	
	//pageSize最大查询数量
	public static final int MaxPageSize = 50000;
	
	
	
	public static final String USERCODE = "userCode";
	public static final String SUBMITFLAG = "submitFlag";
	public static final String TASKID = "taskId";
	public static final String LOGIN_USER = "UserCode";
	public static final String FIELD_SEPARATOR = "_FIELD_SEPARATOR_";
	
	public static final String SERVERCODE = "serverCode";
	public static final String SERVERTYPE = "serverType";
	public static final String SERVERNAME = "serverName";
	
	//微信公众号
	//piccsz
	public static final String MPNUM = "gh_f40ac7bbce86";

	public class PROCESS {
	}
	

	public static Map<String, String> COMPANY_SET = new HashMap<String, String>();
	static{
		COMPANY_SET.put("440300", "分公司");
		COMPANY_SET.put("440301", "车营");
		COMPANY_SET.put("440302", "罗湖");
		COMPANY_SET.put("440303", "西湖");
		COMPANY_SET.put("440306", "华强");
		COMPANY_SET.put("440307", "梅林");
		COMPANY_SET.put("440308", "福田");
		COMPANY_SET.put("440309", "新洲");
		COMPANY_SET.put("440310", "沙河");
		COMPANY_SET.put("440311", "南山");
		COMPANY_SET.put("440312", "宝安");
		COMPANY_SET.put("440313", "龙岗");
		COMPANY_SET.put("440314", "龙华");
		COMPANY_SET.put("440315", "布吉");
		COMPANY_SET.put("440316", "盐田");
		COMPANY_SET.put("440317", "松岗");
		COMPANY_SET.put("440323", "电销");
		COMPANY_SET.put("440324", "国际");
		COMPANY_SET.put("440393", "产营");
	}
	
	// 省份列表
	private static List<QpTCOMProvince> provinces = new ArrayList<QpTCOMProvince>();
	// 城市列表
	private static Map<String, List<QpTCOMCity>> citys = new HashMap<String, List<QpTCOMCity>>();
	// 区域列表
	private static Map<String, List<QpTCOMDistrict>> areas = new HashMap<String, List<QpTCOMDistrict>>();

	public static List<QpTCOMProvince> getProvinces() {
		return provinces;
	}

	public static void setProvinces(List<QpTCOMProvince> provinces) {
		Constants.provinces = provinces;
	}

	public static Map<String, List<QpTCOMCity>> getCitys() {
		return citys;
	}

	public static void setCitys(Map<String, List<QpTCOMCity>> citys) {
		Constants.citys = citys;
	}

	public static Map<String, List<QpTCOMDistrict>> getAreas() {
		return areas;
	}

	public static void setAreas(Map<String, List<QpTCOMDistrict>> areas) {
		Constants.areas = areas;
	}
	
}
