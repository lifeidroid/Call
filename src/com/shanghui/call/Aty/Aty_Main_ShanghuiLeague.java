package com.shanghui.call.Aty;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shanghui.call.R;
/**
 * 商惠联盟
 * @author shanghui
 *
 */
public class Aty_Main_ShanghuiLeague extends BaseActivity {
	private Button btn_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_main_shanghui_league);
		initValues();
		initViews();
		initListeners();
	}
	private void initValues(){
		
	}
	private void initViews(){
		btn_back = (Button)findViewById(R.id.btn_atymainShanghuiLeague_back);
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
