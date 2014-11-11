package com.shanghui.call.Aty;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shanghui.call.R;
/**
 * 意见反馈
 * @author shanghui
 *
 */
public class Aty_Main_FeedBack extends BaseActivity {
	private LinearLayout btn_back;
	private TextView tv_title;
	private RelativeLayout lay_title;
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
		btn_back = (LinearLayout)findViewById(R.id.btn_commonBack);
		tv_title = (TextView)findViewById(R.id.tv_commonTatle);
		tv_title.setText(R.string.feed_back);
		lay_title = (RelativeLayout)findViewById(R.id.lay_title);
		lay_title.setBackgroundColor(getResources().getColor(R.color.bule));
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
