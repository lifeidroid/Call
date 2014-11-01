package com.shanghui.call.Aty;

import com.shanghui.call.Config;
import com.shanghui.call.R;
import com.shanghui.call.Tools.RoundImageView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 打电话界面
 * @author shanghui
 *
 */
public class Aty_Calling extends Activity {
	private Intent mIntent;
	private String name;
	private String num;
	private Bitmap head;
	private TextView tv_name;
	private TextView tv_num;
	private RoundImageView riv_head;
	private ImageView iv_handfree;
	private TextView tv_offCallling;
	private ImageView iv_mute;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_calling);
		initValues();
		initViews();
		initListener();
	}
	private void initValues(){
		mIntent = getIntent();
		name = mIntent.getExtras().getString(Config.KEY_NAME);
		num = mIntent.getExtras().getString(Config.KEY_NUM);
		head = mIntent.getParcelableExtra(Config.KEY_HEAD);
	}
	private void initViews(){
		tv_name = (TextView)findViewById(R.id.tv_atyCalling_name);
		tv_num = (TextView)findViewById(R.id.tv_atyCalling_num);
		riv_head = (RoundImageView)findViewById(R.id.riv_atyCalling_head);
		iv_handfree = (ImageView)findViewById(R.id.iv_atyCallling_handfree);
		tv_offCallling = (TextView)findViewById(R.id.tv_atyCallling_offCalling);
		iv_mute = (ImageView)findViewById(R.id.iv_atyCallling_mute);
		tv_name.setText(name);
		tv_num.setText(num);
		if (head != null) {
			riv_head.setImageBitmap(head);
		}else {
			riv_head.setImageResource(R.drawable.img_people);
		}
	}
	private void initListener(){
		iv_handfree.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		tv_offCallling.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
				
			}
		});
		iv_mute.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
}
