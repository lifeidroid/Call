package com.shanghui.call.Net;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.shanghui.call.Config;
import com.shanghui.call.Mdl.Mdl_data;
/**
 * 工具类 ：用来获取主页的图片和链接
 * @author shanghui-1
 *
 */
public class NetGetData {
	private Mdl_data mdl_data;

	public NetGetData(final SuccessCallback successCallback,
			final FailCallback failCallback) {
		new NetConnection(Config.URL_GETDATA, HttpMethod.POST,
				new NetConnection.SuccessCallback() {

					@Override
					public void onSuccess(String result) {
						if (result != null) {
							try {
								JSONObject jobj = new JSONObject(result);
								if (Config.SUCCESS_RESULT == jobj.getInt(Config.KEY_ERRCODE)) {
									String data = jobj.getString(Config.KEY_DATA);
									Gson gson = new Gson();
									mdl_data = gson.fromJson(data,Mdl_data.class);
									if (successCallback != null) {
										successCallback.onSuccess(mdl_data);
									}
								} else {
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
						} else {
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
				}, Config.KEY_ACTION, Config.KEY_GETDATA);
	}

	public static interface SuccessCallback {
		void onSuccess(Mdl_data data);
	}

	public static interface FailCallback {
		void onFail();
	}
}
