package com.shanghui.call.Net;

import com.shanghui.call.Config;
import com.shanghui.call.Net.NetConnection;

public class Net_PayByPhoneCard {
	public Net_PayByPhoneCard(String name,String pin,String password,final SuccessCallback successCallback,final FailCallback failCallback) {
		new NetConnection(Config.ADDRESS+Config.URL_PAYBYPHONECARD, HttpMethod.POST, new NetConnection.SuccessCallback() {
			
			@Override
			public void onSuccess(String result) {
				if (result != null) {
					 String a[] = result.split("\\|");  
					 if (a[0].equals(Config.SUCCESS)) {
						if (successCallback != null) {
							successCallback.onSuccess();
						}
					 }else {
						if (failCallback != null) {
							failCallback.onFail(a[1]);
						}
					}
				}
				
			}
		}, new NetConnection.FailCallback() {
			
			@Override
			public void onFail() {
				if (failCallback != null) {
					failCallback.onFail("充值失败，请检查网络！");
				}
				
			}
		}, Config.KEY_NAME,name
		,Config.KEY_TYPE,Config.TYPE_BIND_PHONE+""
		,Config.KEY_PIN,pin
		,Config.KEY_PASSWORD,password);
	}
	public static interface SuccessCallback{
		void onSuccess();
	}
	
	public static interface FailCallback{
		void onFail(String error);
	}
}
