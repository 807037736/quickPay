package com.picc.qp.enums;

public enum CaseHandleStatusEnum {
	
	FOR_SUBMIT("0", "待提交"),
	FOR_JUDGE("1", "待定责"),
	FOR_VERIFY("2", "待查勘"),
	FOR_RETURN("3", "已退回"),
	VERIFYING("4", "查勘处理中"),
	FINISH("5", "已受理");

	private String code;
	private String message;

	private CaseHandleStatusEnum(String code, String message) {
		this.code = code;
		this.message = message;
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
