package com.shanghui.call.Aty;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.shanghui.call.R;

public class Aty_Main extends FragmentActivity {
	private RadioGroup rg_aty_main_selecte;
	private FragmentManager fManager;
	private FragmentTransaction fTransaction;
	private Frg_Call frg_Call;
	private Frg_records frg_records;
	private Frg_Message frg_Message;
	private Frg_Friends frg_Friends;
	private Frg_Main frg_Main;
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
	}
	private void initViews(){
		rg_aty_main_selecte =(RadioGroup)findViewById(R.id.rg_aty_main_switch);
	}
	private void initLiteners(){
		rg_aty_main_selecte.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {		
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				fTransaction = fManager.beginTransaction();
				switch (checkedId) {
				case R.id.rb_aty_main_call:
					frg_Call = new Frg_Call();
					System.out.println("------>call");
					fTransaction.replace(R.id.lay_aty_main_container, frg_Call);
					break;
				case R.id.rb_aty_main_records:
					frg_records = new Frg_records();
					System.out.println("------>rb_aty_main_records");
					fTransaction.replace(R.id.lay_aty_main_container, frg_records);
					break;
				case R.id.rb_aty_main_message:
					frg_Message = new Frg_Message();
					fTransaction.replace(R.id.lay_aty_main_container, frg_Message);
					break;
				case R.id.rb_aty_main_friend:
					frg_Friends = new Frg_Friends();
					fTransaction.replace(R.id.lay_aty_main_container, frg_Friends);
					break;
				case R.id.rb_aty_main_main:
					frg_Main = new Frg_Main();
					fTransaction.replace(R.id.lay_aty_main_container, frg_Main);
					break;

				default:
					break;
				}
				fTransaction.commit();
			}
		});
	}
}
