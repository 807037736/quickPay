package com.picc.cxf.schema.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * json结果集
 * 
 * @author obba
 * 
 */
public class JsonResult {

	private String state;
	private String msg;
	private Object data;

	public String getState() {
		return state;
	}

	public JsonResult setState(String state) {
		this.state = state;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public JsonResult setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public Object getData() {
		return data;
	}

	public JsonResult setData(Object data) {
		this.data = data;
		return this;
	}
	
	public JsonResult setJsonResult(CommonEnum e, Object data) {
		this.setState(e.getCode()).setMsg(e.getMessage()).setData(data);
		return this;
	}
	
	/**
	 * 输出结果
	 * @return
	 */
	public String toJsonString() {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, processor);
		jsonConfig.registerJsonValueProcessor(Timestamp.class, processor);
		return JSONObject.fromObject(this, jsonConfig).toString();
	}
	
	private static final JsonDateValueProcessor processor = new JsonDateValueProcessor();
	
	/**
	 * jsonObject 日期处理
	 * @author obba
	 *
	 */
	private static class JsonDateValueProcessor implements JsonValueProcessor {
		
		private static DateFormat dateFormat;
		
		public JsonDateValueProcessor() {
			dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		
		@SuppressWarnings("unused")
		public JsonDateValueProcessor(String parrent) {
			try {
				dateFormat = new SimpleDateFormat(parrent);
			} catch(Exception e) {
				dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			}
		}
		
		@Override
		public Object processArrayValue(Object value, JsonConfig jsonConfig) {
			return process(value);
		}

		@Override
		public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
			return process(value);
		}

		private static Object process(Object value) {
			try {
				return value == null ? "" : dateFormat.format((Date) value);
			} catch (Exception e) {
				return "";
			}
		}
	}

}
