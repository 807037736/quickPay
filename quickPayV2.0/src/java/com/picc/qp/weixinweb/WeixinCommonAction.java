package com.picc.qp.weixinweb;

import ins.framework.web.Struts2Action;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 微信获取通用数据接口
 * 
 * @author obba
 * 
 */
@SuppressWarnings("serial")
public class WeixinCommonAction extends Struts2Action {

	/**
	 * 获取省份
	 * 
	 * @return
	 */
	public String getProvince() {
		JSONObject json = new JSONObject();
		json.put("test", "123");
		JSONArray arr = new JSONArray();
		arr.add(json);
		this.writeJson(arr);
		return NONE;
	}

	/**
	 * 获取城市
	 * 
	 * @return
	 */
	public String getCity() {
		JSONObject json = new JSONObject();
		json.put("test", "123");
		this.writeJson(json);
		return null;
	}

	/**
	 * 获取区域
	 * 
	 * @return
	 */
	public String getDist() {
		JSONObject json = new JSONObject();
		json.put("test", "123");
		this.writeJson(json);
		return null;
	}

	/**
	 * 获取保险公司
	 * 
	 * @return
	 */
	public String getCo() {
		JSONObject json = new JSONObject();
		json.put("test", "123");
		this.writeJson(json);
		return null;
	}

	/**
	 * 上传图片
	 * 
	 * @return
	 */
	public String uploadImg() {
		JSONObject json = new JSONObject();
		json.put("test", "123");
		this.writeJson(json);
		return null;
	}

	/**
	 * 上传案件
	 * 
	 * @return
	 */
	public String uploadCase() {
		JSONObject json = new JSONObject();
		json.put("test", "123");
		this.writeJson(json);
		return null;
	}

}
