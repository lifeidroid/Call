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
		Util.getContacts(this);
		Util.getCallLogs(this);
		TelephonyManager phoneMgr=(TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
		/*		if ("".equals(phoneMgr.getLine1Number()) || phoneMgr.getLine1Number().isEmpty()) {
			return;
		}
		if ("+86".equals(phoneMgr.getLine1Number().subSequence(0, 3))) {
			Dfine.phoneNum = new StringBuffer(phoneMgr.getLine1Number().replace("+86", "")); // 用于显示手机号
		}else {
			Dfine.phoneNum = new StringBuffer(phoneMgr.getLine1Number()); // 用于显示手机号
		}*/
	}

}
