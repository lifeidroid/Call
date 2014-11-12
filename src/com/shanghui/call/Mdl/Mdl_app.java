package com.shanghui.call.Mdl;

public class Mdl_app {
	private String charges_url;
	private String lottery_url;
	private String manage_url;
	private String hot_call;
	private String discount_url;
	private String union_url;
	private String website_url;
	private String notice;
	public Mdl_app(String charges_url,String lottery_url,String manage_url,String hot_call,String discount_url,String union_url,String website_url,String notice) {
		this.charges_url = charges_url;
		this.lottery_url = lottery_url;
		this.manage_url = manage_url;
		this.hot_call = hot_call;
		this.discount_url = discount_url;
		this.union_url = union_url;
		this.website_url = website_url;
		this.notice = notice;
	}
	public Mdl_app() {
		// TODO Auto-generated constructor stub
	}
	public String getCharges_url() {
		return charges_url;
	}
	public void setCharges_url(String charges_url) {
		this.charges_url = charges_url;
	}
	public String getLottery_url() {
		return lottery_url;
	}
	public void setLottery_url(String lottery_url) {
		this.lottery_url = lottery_url;
	}
	public String getManage_url() {
		return manage_url;
	}
	public void setManage_url(String manage_url) {
		this.manage_url = manage_url;
	}
	public String getHot_call() {
		return hot_call;
	}
	public void setHot_call(String hot_call) {
		this.hot_call = hot_call;
	}
	public String getDiscount_url() {
		return discount_url;
	}
	public void setDiscount_url(String discount_url) {
		this.discount_url = discount_url;
	}
	public String getUnion_url() {
		return union_url;
	}
	public void setUnion_url(String union_url) {
		this.union_url = union_url;
	}
	public String getWebsite_url() {
		return website_url;
	}
	public void setWebsite_url(String website_url) {
		this.website_url = website_url;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}

	
}
