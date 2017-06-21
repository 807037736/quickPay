package com.picc.report.picc.schema.upload.model;

import com.ymt.annotation.ClassConvert;

@ClassConvert(name="photoInfo")
public class PhotoInfo {
	/**
	 * 车牌号
	 */
	String licenseNo;
	/**
	 * 照片详细名称
	 */
	String photoName;
	/**
	 * 照片详细代码
	 */
	String photoCode;
	/**
	 * 照片
	 */
	String photoData64;
	/**
	 * 照片详细描述
	 */
	String photoDes;
	/**
	 * 损失车辆id
	 */
	String lossItemId;
	/**
	 * 上传机器mac地址
	 */
	String macAddr;
	/**
	 * 相机型号
	 */
	String cameraType;
	/**
	 * 制造厂商
	 */
	String manufacturer;
	/**
	 * 影像分辨率x,y
	 */
	String photoResolution;
	/**
	 * 影像拍摄时间
	 */
	String photoTime;
	/**
	 * 影像尺寸
	 */
	String photoSize;
	/**
	 * 上传人员名称
	 */
	String operatorName;
	/**
	 * 上传人员代码
	 */
	String operatorCode;
	
	/**
	 * 车牌号
	 */
	public String getLicenseNo() {
		return licenseNo;
	}
	/**
	 * 车牌号
	 */
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	/**
	 * 照片详细名称
	 */
	public String getPhotoName() {
		return photoName;
	}
	/**
	 * 照片详细名称
	 */
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	/**
	 * 获取:照片详细代码
	 */
	public String getPhotoCode() {
		return photoCode;
	}
	/**
	 * 设置:照片详细代码
	 */
	public void setPhotoCode(String photoCode) {
		this.photoCode = photoCode;
	}
	/**
	 * 获取:照片 base64格式
	 */
	public String getPhotoData64() {
		return photoData64;
	}
	/**
	 * 设置:照片 base64格式
	 */
	public void setPhotoData64(String photoData64) {
		this.photoData64 = photoData64;
	}
	/**
	 * 获取:照片详细描述
	 */
	public String getPhotoDes() {
		return photoDes;
	}
	/**
	 * 设置:照片详细描述
	 */
	public void setPhotoDes(String photoDes) {
		this.photoDes = photoDes;
	}
	/**
	 * 获取:损失车辆id
	 */
	public String getLossItemId() {
		return lossItemId;
	}
	/**
	 * 设置:损失车辆id
	 */
	public void setLossItemId(String lossItemId) {
		this.lossItemId = lossItemId;
	}
	/**
	 * 获取:上传机器mac地址
	 */
	public String getMacAddr() {
		return macAddr;
	}
	/**
	 * 设置:上传机器mac地址
	 */
	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}
	/**
	 * 获取:相机型号
	 */
	public String getCameraType() {
		return cameraType;
	}
	/**
	 * 设置:相机型号
	 */
	public void setCameraType(String cameraType) {
		this.cameraType = cameraType;
	}
	/**
	 * 获取:制造厂商
	 */
	public String getManufacturer() {
		return manufacturer;
	}
	/**
	 * 设置:制造厂商
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	/**
	 * 获取:影像分辨率x,y
	 */
	public String getPhotoResolution() {
		return photoResolution;
	}
	/**
	 * 设置:影像分辨率x,y
	 */
	public void setPhotoResolution(String photoResolution) {
		this.photoResolution = photoResolution;
	}
	/**
	 * 获取:影像拍摄时间
	 */
	public String getPhotoTime() {
		return photoTime;
	}
	/**
	 * 设置:影像拍摄时间
	 */
	public void setPhotoTime(String photoTime) {
		this.photoTime = photoTime;
	}
	/**
	 * 获取:影像尺寸
	 */
	public String getPhotoSize() {
		return photoSize;
	}
	/**
	 * 设置:影像尺寸
	 */
	public void setPhotoSize(String photoSize) {
		this.photoSize = photoSize;
	}
	/**
	 * 获取:上传人员名称
	 */
	public String getOperatorName() {
		return operatorName;
	}
	/**
	 * 设置:上传人员名称
	 */
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	/**
	 * 获取:上传人员代码
	 */
	public String getOperatorCode() {
		return operatorCode;
	}
	/**
	 * 设置:上传人员代码
	 */
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	@Override
	public String toString() {
		return "PhotoInfo [licenseNo=" + licenseNo + ", photoName=" + photoName + ", photoCode=" + photoCode
				+ ", photoData64=" + photoData64 + ", photoDes=" + photoDes + ", lossItemId=" + lossItemId
				+ ", macAddr=" + macAddr + ", cameraType=" + cameraType + ", manufacturer=" + manufacturer
				+ ", photoResolution=" + photoResolution + ", photoTime=" + photoTime + ", photoSize=" + photoSize
				+ ", operatorName=" + operatorName + ", operatorCode=" + operatorCode + "]";
	}
	
}
