package com.shanghui.call;

import android.content.Context;
import android.content.SharedPreferences.Editor;

public class Config {
	public static final String SHANGHUI_URL = "http://www.360shanghui.com/";
	public static final String E_BOSS_URL = "http://www.e-boss.net/";
	public static final String CHARSET = "UTF-8";
	
	
	public static final String APPID = "com.shanghui.call";
	public static final String KEY_LOCALCODE = "localcode";
	public static final String ADDRESS = "";
	public static final String URL_CALLBACK = "/external/server/CallBack";
	public static final String KEY_NAME = "name";
	public static final String KEY_NUM = "phoneNum";
	public static final String KEY_HEAD = "head";
	public static final String KEY_ID = "userId";
	public static final String KEY_CALLERE164 = "callerE164";
	public static final String KEY_CALLEEE164S = "calleeE164s";
	public static final String KEY_ACCESSE164 = "accessE164";
	public static final String KEY_ACCESSE164PASSWORD_ = "accessE164Password";
	public static final String KEY_OWNERNAME = "ownerName";
	public static final String KEY_OWNERTYPE = "ownerType";
	public static final String KEY_PIN = "pin";
	public static final String KEY_RETCODE = "retCode";
	public static final int SUCCESS = 0;
	public static final long NO_EXIST = 0l;
	
	
	//存取本地区号
	public static String getCaCheLocalCode(Context context){
		return context.getSharedPreferences(APPID, Context.MODE_PRIVATE).getString(KEY_LOCALCODE, null);
	}
	public static void cacheLocalCode(Context context ,String localCode){
		Editor editor = context.getSharedPreferences(APPID, Context.MODE_PRIVATE).edit();
		editor.putString(KEY_LOCALCODE, localCode);
		editor.commit();
	}
}
