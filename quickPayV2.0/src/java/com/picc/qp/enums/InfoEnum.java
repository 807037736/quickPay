package com.picc.qp.enums;

/**
 * 系统提示信息
 * @author obba
 *
 */
public enum InfoEnum {

	SAVE_ACCIDENT_REPEAT(1001, "系统检测到48小时内有同样身份证号的出险记录，请确认是否重复");

	private Integer code;
	private String message;

	private InfoEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getMessage(Integer code) {
		for (InfoEnum w : InfoEnum.values()) {
			if (code.equals(w.getCode())) {
				return w.getMessage();
			}
		}
		return null;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
