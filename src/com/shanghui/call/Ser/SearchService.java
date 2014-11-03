package com.shanghui.call.Ser;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.TelephonyManager;

import com.shanghui.call.Mdl.Dfine;
import com.shanghui.call.Tools.Util;

public class SearchService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		TelephonyManager phoneMgr=(TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
		Dfine.phoneNum = new StringBuffer(phoneMgr.getLine1Number()); // 用于显示手机号
		Util.getContacts(this);
		Util.getCallLogs(this);
	}

}
