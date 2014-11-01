package com.shanghui.call.Aty;

import android.app.Application;
import android.content.Intent;

import com.shanghui.call.Ser.SearchService;

public class App_Main extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		startService(new Intent(this, SearchService.class));
	}
}
