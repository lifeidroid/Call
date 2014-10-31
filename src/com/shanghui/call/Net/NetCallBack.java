package com.shanghui.call.Net;

import org.json.JSONException;
import org.json.JSONObject;

import com.shanghui.call.Config;
/**
 * 回拨网络通讯
 * @author shanghui
 *
 */

public class NetCallBack{
	public NetCallBack(String Calling,String Called,String JoinNum,String JoinPassword,final SuccessCallback successCallback,final FailCallback failCallback) {
		new NetConnection(Config.ADDRESS+Config.URL_CALLBACK, HttpMethod.POST, new NetConnection.SuccessCallback() {
			
			@Override
			public void onSuccess(String result) {
				try {
					JSONObject jobj = new JSONObject(result);
					if (Config.SUCCESS == jobj.getInt(Config.KEY_RETCODE)) {
						if (successCallback != null) {
							successCallback.onSuccess();
						}
					}else {
						if (failCallback != null) {
							failCallback.onFail();
						}
					}
				} catch (JSONException e) {
					if (failCallback != null) {
						failCallback.onFail();
					}
					e.printStackTrace();
				}
				
			}
		}, new NetConnection.FailCallback() {
			
			@Override
			public void onFail() {
				if (failCallback != null) {
					failCallback.onFail();
				}
				
			}
		}, Config.KEY_CALLERE164,Calling,
		Config.KEY_CALLEEE164S,Called,
		Config.KEY_ACCESSE164,JoinNum,
		Config.KEY_ACCESSE164PASSWORD_,JoinPassword);
	}
	public static interface SuccessCallback{
		void onSuccess();
	}
	public static interface FailCallback{
		void onFail();
	}
}


