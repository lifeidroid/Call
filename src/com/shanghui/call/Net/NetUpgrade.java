package com.shanghui.call.Net;

import org.json.JSONException;
import org.json.JSONObject;

import com.shanghui.call.Config;

public class NetUpgrade {
	public NetUpgrade(String message,final SuccessCallback successCallback,final FailCallback failCallback) {
		new NetConnection(Config.ADDRESS+Config.URL_UPGRADE, HttpMethod.POST, new NetConnection.SuccessCallback() {
			
			@Override
			public void onSuccess(String result) {
				if (result != null) {
					try {
						JSONObject jobj = new JSONObject(result);
						switch (jobj.getInt(Config.KEY_ERRCODE)) {
						case Config.SUCCESS_RESULT:
							JSONObject dataObj = jobj.getJSONObject(Config.KEY_DATA);
							if (successCallback != null) {
								successCallback.onSuccess(dataObj.getInt(Config.KEY_VERSION),
										dataObj.getString(Config.KEY_MSG),
										dataObj.getString(Config.KEY_URL),
										dataObj.getString(Config.KEY_SIGNATURE));
							}
							break;

						default:
							if (failCallback != null) {
								failCallback.onFail();
							}
							break;
						}
					} catch (JSONException e) {
						if (failCallback != null) {
							failCallback.onFail();
						}
					}
				}else {
					if (failCallback != null) {
						failCallback.onFail();
					}
				}
				
			}
		}, new NetConnection.FailCallback() {
			
			@Override
			public void onFail() {
				if (failCallback != null) {
					failCallback.onFail();
				}
				
			}
		});
	}
	public static interface SuccessCallback{
		void onSuccess(int version,String msg,String downloadUrl,String signature);
	}
	public static interface FailCallback{
		void onFail();
	}
}
