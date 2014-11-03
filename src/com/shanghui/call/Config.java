package com.shanghui.call;

import android.content.Context;
import android.content.SharedPreferences.Editor;

public class Config {
	public static final String SHANGHUI_URL = "http://www.360shanghui.com/";
	public static final String E_BOSS_URL = "http://www.e-boss.net/";
	public static final String CHARSET = "UTF-8";
	
	
	public static final String URL_CALLBACK = "thirdparty/callback.jsp?";
	public static final String URL_GETCUSTOMER = "thirdparty/getcustomer.jsp?";
	
	public static final String URL_PAYBYPHONECARD = "thirdparty/paybyphonecard.jsp?";
	public static final String APPID = "com.shanghui.call";
	public static final String KEY_LOCALCODE = "localcode";
	public static final String ADDRESS = "http://42.96.133.95:9088/";
	public static final String LOGINNAME = "chaxun";
	public static final String LOGINPASSWORD = "chaxun18277626";
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
	public static final String KEY_ACCOUNT = "account";
	public static final String KEY_AGENTACCOUNT = "agentAccount";
	public static final String KEY_QUERYSONMODE = "querySonMode";
	public static final String KEY_MONEY = "money";
	public static final String KEY_LIMITMONEY = "limitMoney";
	public static final String KEY_FEERATEGROUP = "feeRateGroup";
	public static final String KEY_TYPE = "type";
	public static final String KEY_LOCKTYPE = "lockType";
	public static final String KEY_PHONEBOOKLIMIT = "phoneBookLimit";
	public static final String KEY_CANCELED = "canceled";
	public static final String KEY_STARTTIME = "startTime";
	public static final String KEY_VALIDTIME = "validTime";
	public static final String KEY_TODAYCONSUMPTION = "todayConsumption";
	public static final String KEY_CTDBILLINGTYPE = "ctdBillingType";
	public static final String KEY_GATEGORY = "category";
	public static final String KEY_BINDEDE164S = "bindedE164s";
	public static final String KEY_MEMO = "memo";
	public static final String KEY_INFOCUSTOMERADDITIONAL = "infoCustomerAdditional";
	public static final String KEY_PASSWORD = "password";
	public static final String KEY_CALLER = "caller";
	public static final String KEY_CALLEES = "callees";
	
	public static final String SUCCESS = "0";
	public static final long NO_EXIST = 0l;
	public static final int TYPE_BIND_PHONE = 2;
	public static final String KEY_NUMBER = "number";
	public static final String KEY_ASSWORD = "password";
	public static final String KEY_LOGINNAME = "loginName";
	public static final String KEY_LOGINPASSWORD = "loginPassword";
	
	
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
