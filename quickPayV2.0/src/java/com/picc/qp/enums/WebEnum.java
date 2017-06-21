package com.picc.qp.enums;

public enum WebEnum {

	DEL_CASE_SUCCESS(200, "删除成功"),
	DEL_CASE_EMPTY_CASE_ID(201, "案件ID不能为空"),
	DEL_CASE_ERROR_CASE_STATUS(202, "案件处理中,无法删除"),
	DEL_CASE_ERROR_DEL_USER(203, "非法操作,仅案件创建者能删除该案件"),
	DEL_CASE_ERROR(204,"程序错误");

	private Integer code;
	private String message;

	private WebEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getMessage(Integer code) {
		for (WebEnum w : WebEnum.values()) {
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
