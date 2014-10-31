package com.shanghui.call.Mdl;

import android.graphics.Bitmap;
/**
 * 通讯录联系人模型
 * @author shanghui
 *
 */
public class Mdl_Contact {
	public int index = 8;
	public String input ;
	public int matchIndex = 10;
	private long peopleId;
	private String Name;
	private String PhoneNum;
	private Bitmap Head;
	private String firstNamePy;
	private String lastNamePy;			//名字每一个字的首字母
	private String lastNameToNumber;		//名字首字母转成数字	
	private String namePy;				//名字全拼
	private String nameToNumber;			//名字全拼转成数字
	
	public Mdl_Contact(long peopleId,String name, String phoneNum, Bitmap head,String firstNamePy,String lastNamePy,String lastNameToNumber,String namePy,String nameToNumber) {
		super();
		this.peopleId = peopleId;
		Name = name;
		PhoneNum = phoneNum;
		Head = head;
		this.firstNamePy = firstNamePy;
		this.lastNamePy = lastNamePy;
		this.lastNameToNumber = lastNameToNumber;
		this.namePy = namePy;
		this.nameToNumber = nameToNumber;
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
	public String getFirstNamePy() {
		return firstNamePy;
	}
	public void setFirstNamePy(String firstNamePy) {
		this.firstNamePy = firstNamePy;
	}
	public String getLastNamePy() {
		return lastNamePy;
	}
	public void setLastNamePy(String lastNamePy) {
		this.lastNamePy = lastNamePy;
	}
	public String getLastNameToNumber() {
		return lastNameToNumber;
	}
	public void setLastNameToNumber(String lastNameToNumber) {
		this.lastNameToNumber = lastNameToNumber;
	}
	public String getNamePy() {
		return namePy;
	}
	public void setNamePy(String namePy) {
		this.namePy = namePy;
	}
	public String getNameToNumber() {
		return nameToNumber;
	}
	public void setNameToNumber(String nameToNumber) {
		this.nameToNumber = nameToNumber;
	}
	
}
