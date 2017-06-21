package com.picc.cxf.schema.model;

public enum CommonEnum {
	
	SUCCESS("00", "请求成功"),
	FAIL("01", "请求失败"),
	NONE_PARAM("10001", "缺少参数"),
	EMPTY_PARAM("10002", "参数内容不能为空"),
	ERROR_PARAM("10003", "参数类型错误"),
	ERROR_APP("10004", "程序异常"),
	ERROR_APP2("10005", "提交异常"),
	INVALID_TOKEN("10005", "无效的签名"),
	ERROR_REPORTNO("10006", "无效的案件编号");//理赔回调

	private String code;
	private String message;

	private CommonEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getMessage(String code) {
		for (CommonEnum w : CommonEnum.values()) {
			if (code.equals(w.getCode())) {
				return w.getMessage();
			}
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
