package com.shanghui.call.Aty;

import com.shanghui.call.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/**
 * 意见反馈
 * @author shanghui
 *
 */
public class Aty_Main_FeedBack extends Activity {
	private Button btn_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_main_feedback);
		initValues();
		initViews();
		initListeners();
	}
	private void initValues(){
		
	}
	private void initViews(){
		btn_back = (Button)findViewById(R.id.btn_atymainfeedback_back);
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
