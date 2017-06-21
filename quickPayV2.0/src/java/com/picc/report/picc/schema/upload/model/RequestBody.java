package com.picc.report.picc.schema.upload.model;

import java.util.List;

import com.ymt.annotation.ClassConvert;

@ClassConvert(name="requestbody")
public class RequestBody {
	
	/*
	 * 请求唯一标识
	 */
	String uuid;
	/*
	 * 报案号
	 */
	String registNo;
	/*
	 * 任务类型
	 */
	String taskType;
	/*
	 * 责任类型
	 */
	String respType;
	/*
	 * 事故类型
	 */
	String accidType;
	/*
	 * 数据来源
	 */
	String soureFlag;
	/*
	 * 照片详细列表
	 */
	List<PhotoInfo> photoInfoList;
	
	/*
	 * 获取:随机id
	 */
	public String getUuid() {
		return uuid;
	}
	/*
	 * 随机id
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/*
	 * 获取:报案号
	 */
	public String getRegistNo() {
		return registNo;
	}
	/*
	 * 报案号
	 */
	public void setRegistNo(String registNo) {
		this.registNo = registNo;
	}
	/*
	 * 获取:任务类型 (1、查勘照片 2、单证照片 3、待分拣照片)
	 */
	public String getTaskType() {
		return taskType;
	}
	/*
	 * 任务类型 (1、查勘照片 2、单证照片 3、待分拣照片)
	 */
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	/*
	 * 获取:责任类型
	 * (1：全责 2：主责 3：同责 4：次责 5：无责 0：不确定)
	 */
	public String getRespType() {
		return respType;
	}
	/*
	 * 责任类型
	 * (1：全责 2：主责 3：同责 4：次责 5：无责 0：不确定)
	 */
	public void setRespType(String respType) {
		this.respType = respType;
	}
	/*
	 * 获取:事故类型(D:单车 S:双车 M：多车)
	 */
	public String getAccidType() {
		return accidType;
	}
	/*
	 * 事故类型(D:单车 S:双车 M：多车)

	 */
	public void setAccidType(String accidType) {
		this.accidType = accidType;
	}
	/*
	 * 获取:数据来源
	 * (01.快处快赔  02.支付宝   03.微信  04.嘉兴车联网  5200JDA0.贵州警保APP 06.人保e车险  07.集审录入  08.理赔辅助平台  0000KDA0.视频系统)
	 */
	public String getSoureFlag() {
		return soureFlag;
	}
	/*
	 * 数据来源
	 * (01.快处快赔  02.支付宝   03.微信  04.嘉兴车联网  5200JDA0.贵州警保APP 06.人保e车险  07.集审录入  08.理赔辅助平台  0000KDA0.视频系统)
	 */
	public void setSoureFlag(String soureFlag) {
		this.soureFlag = soureFlag;
	}
	/*
	 * 照片详细列表
	 */
	public List<PhotoInfo> getPhotoInfoList() {
		return photoInfoList;
	}
	/*
	 * 照片详细列表
	 */
	public void setPhotoInfoList(List<PhotoInfo> photoInfoList) {
		this.photoInfoList = photoInfoList;
	}
	@Override
	public String toString() {
		return "RequestBody [uuid=" + uuid + ", registNo=" + registNo + ", taskType=" + taskType + ", respType="
				+ respType + ", accidType=" + accidType + ", soureFlag=" + soureFlag + ", photoInfoList="
				+ photoInfoList + "]";
	}
}
