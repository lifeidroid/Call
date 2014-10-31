package com.shanghui.call.Mdl;

import android.R.integer;
import android.graphics.Bitmap;
/**
 * 通讯录联系人模型
 * @author shanghui
 *
 */
public class Mdl_Contact {
	private long peopleId;
	private String Name;
	private String PhoneNum;
	private Bitmap Head;
	public Mdl_Contact(long peopleId,String name, String phoneNum, Bitmap head) {
		super();
		this.peopleId = peopleId;
		Name = name;
		PhoneNum = phoneNum;
		Head = head;
	}
	public Mdl_Contact() {
	}
	public long getPeopleId() {
		return peopleId;
	}
	public void setPeopleId(long peopleId) {
		this.peopleId = peopleId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPhoneNum() {
		return PhoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		PhoneNum = phoneNum;
	}
	public Bitmap getHead() {
		return Head;
	}
	public void setHead(Bitmap head) {
		Head = head;
	}
	
}
