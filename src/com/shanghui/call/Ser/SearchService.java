package com.shanghui.call.Ser;


import com.shanghui.call.Tools.Util;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

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
	}

}
