package ins.framework.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonValueFormat implements JsonValueProcessor {
	
	private String pattern = "yyyy-MM-dd HH:mm:ss";
	
	public JsonValueFormat() {
		super();
	}
	
	public JsonValueFormat(String datePattern) {
		super();
		pattern = datePattern;
	}

	public Object processArrayValue(Object value, JsonConfig config) {
		if(value instanceof Date){
			String format=new SimpleDateFormat(pattern).format(new Date(((Date) value).getTime()));
			return format;
		}
		return null;
	}

	public Object processObjectValue(String key, Object value, JsonConfig config) {
		if(value instanceof Date){
			String format=new SimpleDateFormat(pattern).format(new Date(((Date) value).getTime()));
			return format;
		}
		return null;
	}

}
