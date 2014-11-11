package com.shanghui.call.Net;

import com.shanghui.call.Config;
import com.shanghui.call.Net.NetConnection;
public class NetGetCustomer {
	public NetGetCustomer(String name,final SuccessCallback successCallback,final FailCallback failCallback) {
		new NetConnection(Config.ADDRESS+Config.URL_GETCUSTOMER, HttpMethod.POST, new NetConnection.SuccessCallback() {
			
			@Override
			public void onSuccess(String result) {
				if (result != null) {
					String a[] = result.split("\\|");
					if (Config.SUCCESS.equals(a[0])) {
						if (successCallback != null) {
							String b[] = a[1].split(";");
							successCallback.onSuccess(b[0], b[1], b[2], b[3], b[4]);
						}
					}else {
						if (failCallback!= null) {
							failCallback.onFail(a[1]);
						}
					}
				}else {
					if (failCallback != null) {
						failCallback.onFail("查询失败，请稍后重试！");
					}
				}
				
			}
		}, new NetConnection.FailCallback() {
			
			@Override
			public void onFail() {
				if (failCallback != null) {
					failCallback.onFail("查询失败，请稍后重试！");
				}
			}
		},Config.KEY_NAME,name
		,Config.KEY_LOGINNAME,Config.LOGINNAME
		,Config.KEY_TYPE,Config.TYPE_BIND_PHONE+""
		,Config.KEY_LOGINPASSWORD,Config.LOGINPASSWORD);
	}
	public static interface SuccessCallback{
		/**
		 * 
		 * @param AccountNum 账户账号
		 * @param AccountName 账户名称
		 * @param CurrentBalance 当前余额
		 * @param validity 有效期
		 * @param overdraft 透支限额
		 */
		void onSuccess(String AccountNum,String AccountName,String CurrentBalance,String validity,String overdraft);
	}
	public static interface FailCallback{
		void onFail(String error);
	}
}
