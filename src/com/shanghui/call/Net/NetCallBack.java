package com.shanghui.call.Net;

import com.shanghui.call.Config;
/**
 * 回拨网络通讯
 * @author shanghui
 *
 */

public class NetCallBack{
	public NetCallBack(String caller,String callees,String JoinNum,String JoinPassword,final SuccessCallback successCallback,final FailCallback failCallback) {
		new NetConnection(Config.ADDRESS+Config.URL_CALLBACK, HttpMethod.POST, new NetConnection.SuccessCallback() {
			
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
				}else {
					if (failCallback != null) {
						failCallback.onFail("连接服务器失败，请检查网络！");
					}
				}
				
			}
		}, new NetConnection.FailCallback() {
			
			@Override
			public void onFail() {
				if (failCallback != null) {
					failCallback.onFail("连接服务器失败，请检查网络！");
				}		
			}
		},Config.KEY_CALLER,caller
		,Config.KEY_CALLEES,callees
		,Config.KEY_NUMBER,JoinNum
		,Config.KEY_ASSWORD,JoinPassword);
	}
	public static interface SuccessCallback{
		void onSuccess();
	}
	public static interface FailCallback{
		void onFail(String error);
	}
}


