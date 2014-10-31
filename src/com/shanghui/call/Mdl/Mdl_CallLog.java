package com.shanghui.call.Mdl;

import android.graphics.Bitmap;
/**
 * 通话记录模型
 * @author shanghui
 *
 */
public class Mdl_CallLog {
	private String phoneNum;
	private String userName;
	private int callType;
	private String callDate;
	private Bitmap head;
	private String during;
	public Mdl_CallLog(String phoneNum, String userName, int callType,
			String callDate,Bitmap head,String during) {
		super();
		this.phoneNum = phoneNum;
		this.userName = userName;
		this.callType = callType;
		this.callDate = callDate;
		this.head = head;
		this.during = during;
	}
	public Mdl_CallLog() {
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getCallType() {
		return callType;
	}
	public void setCallType(int callType) {
		this.callType = callType;
	}
	public String getCallDate() {
		return callDate;
	}
	public void setCallDate(String callDate) {
		this.callDate = callDate;
	}
	public void setHead(Bitmap head) {
		this.head = head;
	}
	public Bitmap getHead() {
		return head;
	}
	public String getDuring() {
		return during;
	}
	public void setDuring(String during) {
		this.during = during;
	}
}
