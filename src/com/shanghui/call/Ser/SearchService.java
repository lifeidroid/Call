package com.shanghui.call.Ser;


import java.io.File;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;

import com.shanghui.call.Mdl.Dfine;
import com.shanghui.call.Mdl.Mdl_data;
import com.shanghui.call.Net.NetGetData;
import com.shanghui.call.Tools.Util;

public class SearchService extends Service {
	private String path;
	private File dirFile;
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		getCachePath();
		Util.getContacts(this);
		Util.getCallLogs(this);
		new NetGetData(new NetGetData.SuccessCallback() {
			
			@Override
			public void onSuccess(Mdl_data data) {
				Dfine.mData = data;
			}
		}, new NetGetData.FailCallback() {
			
			@Override
			public void onFail() {
				System.out.println("------>getdata获取信息失败！");
				
			}
		});
	}	
	
	
	private void getCachePath(){
		path = getSDPath() + "/ShangHuiTong/";
		dirFile = new File(path);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		Dfine.cachePath = dirFile.toString();
	}
	
	private String getSDPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
		}
		return sdDir.toString();
	}

}
