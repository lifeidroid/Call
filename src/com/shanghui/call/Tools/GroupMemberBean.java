package com.shanghui.call.Tools;

import com.shanghui.call.Mdl.Mdl_Contact;

public class GroupMemberBean {

	private Mdl_Contact contact;
	private String sortLetters;  //显示数据拼音的首字母
	
	public Mdl_Contact getContact() {
		return contact;
	}
	public void setContact(Mdl_Contact contact) {
		this.contact = contact;
	}
	public String getSortLetters() {
		return sortLetters;
	}
	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}
}
