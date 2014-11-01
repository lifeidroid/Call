package com.shanghui.call.Aty;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shanghui.call.R;
import com.shanghui.call.R.color;

public class Aty_Main extends FragmentActivity {
	private FragmentManager fManager;
	private FragmentTransaction fTransaction;
	private Frg_Call frg_Call;
	private Frg_records frg_records;
	private Frg_Message frg_Message;
	private Frg_Friends frg_Friends;
	private Frg_Main frg_Main;
	private LinearLayout lay_call,lay_contanct,tv,lay_msg,lay_friends,lay_setting;
	private ImageView iv_call,iv_contanct,iv_msg,iv_friends,iv_setting;
	private TextView tv_call,tv_contanct,tv_msg,tv_friends,tv_setting;
	private int swi;
	private static int call = 0;
	private static int content = 1;
	private static int msg = 2;
	private static int friends = 3;
	private static int setting = 4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_main);
		initValues();
		initViews();
		initLiteners();
	}
	private void initValues(){
		fManager = getSupportFragmentManager();
		swi = call;
	}
	private void initViews(){
		lay_call = (LinearLayout)findViewById(R.id.lay_atymain_call);
		lay_contanct = (LinearLayout)findViewById(R.id.lay_atymain_contact);
		lay_msg = (LinearLayout)findViewById(R.id.lay_atymain_msg);
		lay_friends = (LinearLayout)findViewById(R.id.lay_atymain_friends);
		lay_setting = (LinearLayout)findViewById(R.id.lay_atymain_setting);
		tv_call = (TextView)findViewById(R.id.tv_atymain_call);
		tv_contanct = (TextView)findViewById(R.id.tv_atymain_contact);
		tv_msg = (TextView)findViewById(R.id.tv_atymain_msg);
		tv_friends = (TextView)findViewById(R.id.tv_atymain_friends);
		tv_setting = (TextView)findViewById(R.id.tv_atymain_setting);
		iv_call = (ImageView)findViewById(R.id.iv_atymain_call);
		iv_contanct = (ImageView)findViewById(R.id.iv_atymain_contact);
		iv_msg = (ImageView)findViewById(R.id.iv_atymain_msg);
		iv_friends = (ImageView)findViewById(R.id.iv_atymain_friends);
		iv_setting = (ImageView)findViewById(R.id.iv_atymain_setting);
		fTransaction = fManager.beginTransaction();
		frg_Call = new Frg_Call();
		fTransaction.replace(R.id.lay_aty_main_container, frg_Call);
		fTransaction.commit();
	}
	private void initLiteners(){
		//拨号点击事件
		lay_call.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if (swi == call) {
					return;
				}
				swi = call;
				iv_call.setImageResource(R.drawable.img_call_press);
				tv_call.setTextColor(getResources().getColor(color.bule));
				iv_contanct.setImageResource(R.drawable.img_contact);
				tv_contanct.setTextColor(getResources().getColor(color.DimGray));
				iv_msg.setImageResource(R.drawable.img_msg);
				tv_msg.setTextColor(getResources().getColor(color.DimGray));
				iv_friends.setImageResource(R.drawable.img_friends);
				tv_friends.setTextColor(getResources().getColor(color.DimGray));
				iv_setting.setImageResource(R.drawable.img_setting);
				tv_setting.setTextColor(getResources().getColor(color.DimGray));	
				fTransaction = fManager.beginTransaction();
				frg_Call = new Frg_Call();
				System.out.println("------>call");
				fTransaction.replace(R.id.lay_aty_main_container, frg_Call);
				fTransaction.commit();
			}
		});
		//通话记录点击事件
		lay_contanct.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if (swi == content) {
					return;
				}
				swi = content;
				iv_call.setImageResource(R.drawable.img_call);
				tv_call.setTextColor(getResources().getColor(color.DimGray));
				iv_contanct.setImageResource(R.drawable.img_contact_press);
				tv_contanct.setTextColor(getResources().getColor(color.bule));
				iv_msg.setImageResource(R.drawable.img_msg);
				tv_msg.setTextColor(getResources().getColor(color.DimGray));
				iv_friends.setImageResource(R.drawable.img_friends);
				tv_friends.setTextColor(getResources().getColor(color.DimGray));
				iv_setting.setImageResource(R.drawable.img_setting);
				tv_setting.setTextColor(getResources().getColor(color.DimGray));
				fTransaction = fManager.beginTransaction();
				frg_records = new Frg_records();
				System.out.println("------>rb_aty_main_records");
				fTransaction.replace(R.id.lay_aty_main_container, frg_records);
				fTransaction.commit();
			}
		});
		//短信点击事件
		lay_msg.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if (swi == msg) {
					return;
				}
				swi = msg;
				iv_call.setImageResource(R.drawable.img_call);
				tv_call.setTextColor(getResources().getColor(color.DimGray));
				iv_contanct.setImageResource(R.drawable.img_contact);
				tv_contanct.setTextColor(getResources().getColor(color.DimGray));
				iv_msg.setImageResource(R.drawable.img_msg_press);
				tv_msg.setTextColor(getResources().getColor(color.bule));
				iv_friends.setImageResource(R.drawable.img_friends);
				tv_friends.setTextColor(getResources().getColor(color.DimGray));
				iv_setting.setImageResource(R.drawable.img_setting);
				tv_setting.setTextColor(getResources().getColor(color.DimGray));
				fTransaction = fManager.beginTransaction();
				frg_Message = new Frg_Message();
				fTransaction.replace(R.id.lay_aty_main_container, frg_Message);
				fTransaction.commit();
			}
		});
		//好友点击事件
		lay_friends.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if (swi == friends) {
					return;
				}
				swi = friends;
				iv_call.setImageResource(R.drawable.img_call);
				tv_call.setTextColor(getResources().getColor(color.DimGray));
				iv_contanct.setImageResource(R.drawable.img_contact);
				tv_contanct.setTextColor(getResources().getColor(color.DimGray));
				iv_msg.setImageResource(R.drawable.img_msg);
				tv_msg.setTextColor(getResources().getColor(color.DimGray));
				iv_friends.setImageResource(R.drawable.img_friends_press);
				tv_friends.setTextColor(getResources().getColor(color.bule));
				iv_setting.setImageResource(R.drawable.img_setting);
				tv_setting.setTextColor(getResources().getColor(color.DimGray));	
				fTransaction = fManager.beginTransaction();
				frg_Friends = new Frg_Friends();
				fTransaction.replace(R.id.lay_aty_main_container, frg_Friends);
				fTransaction.commit();
			}
		});
		//主页点击事件
		lay_setting.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if (swi == setting) {
					return;
				}
				swi = setting;
				iv_call.setImageResource(R.drawable.img_call);
				tv_call.setTextColor(getResources().getColor(color.DimGray));
				iv_contanct.setImageResource(R.drawable.img_contact);
				tv_contanct.setTextColor(getResources().getColor(color.DimGray));
				iv_msg.setImageResource(R.drawable.img_msg);
				tv_msg.setTextColor(getResources().getColor(color.DimGray));
				iv_friends.setImageResource(R.drawable.img_friends);
				tv_friends.setTextColor(getResources().getColor(color.DimGray));
				iv_setting.setImageResource(R.drawable.img_setting_press);
				tv_setting.setTextColor(getResources().getColor(color.bule));
				fTransaction = fManager.beginTransaction();
				frg_Main = new Frg_Main();
				fTransaction.replace(R.id.lay_aty_main_container, frg_Main);
				fTransaction.commit();
			}
		});
	}
}
