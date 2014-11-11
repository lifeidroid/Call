package com.shanghui.call.Aty;

import android.content.Intent;
import android.os.Bundle;

import com.shanghui.call.Config;

public class Aty_Judge extends BaseActivity {
	private String phoneNum;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		phoneNum = Config.getCaChePhoneNum(Aty_Judge.this);
		if (phoneNum.isEmpty() || "".equals(phoneNum)) {
			startActivity(new Intent(Aty_Judge.this,Aty_BindPhone.class));
		}else {
			startActivity(new Intent(Aty_Judge.this,Aty_Main.class));
		}
		finish();
		super.onCreate(savedInstanceState);
	}
}