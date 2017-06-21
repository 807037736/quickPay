package com.picc.qp.enums;

public enum SelectTypeEnum {
	
	DIRECTION("DirectionType", "行驶方向类型"),
	IDENTIFY("IdentifyType", "证件类型"),
	RESPONSIBILITY("ResponsibilityType", "当事人责任类型"),
	SYSTEM_PARAM("systemParam", "系统设置"),
	TIME_SECTION("timeSection", "时间区间"),
	VEHICLE("VehicleType", "车辆类型"),
	WEATHER("WeatherType", "天气情况"),
	PERMISSION_DRIVE("PermissionDriveType", "准驾车型");

	private String code;
	private String message;

	private SelectTypeEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getMessage(Integer code) {
		for (SelectTypeEnum w : SelectTypeEnum.values()) {
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
