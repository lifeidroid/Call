package com.shanghui.call.Mdl;

public class Mdl_CallLog {
	private String phoneNum;
	private String userName;
	private int callType;
	private String callDate;
	public Mdl_CallLog(String phoneNum, String userName, int callType,
			String callDate) {
		super();
		this.phoneNum = phoneNum;
		this.userName = userName;
		this.callType = callType;
		this.callDate = callDate;
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
	
}
