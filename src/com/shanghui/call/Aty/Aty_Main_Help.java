package com.shanghui.call.Aty;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shanghui.call.R;

public class Aty_Main_Help extends BaseActivity {
	private Button btn_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_main_help);
		initValues();
		initViews();
		initListeners();
	}
	private void initValues(){
		
	}
	private void initViews(){
		btn_back = (Button)findViewById(R.id.btn_atymainhelp_back);
	}
	private void initListeners(){
		btn_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
