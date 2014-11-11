package com.shanghui.call.Aty;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shanghui.call.Config;
import com.shanghui.call.R;
/**
 * 更多设置
 * @author shanghui
 *
 */
public class Aty_Main_More extends BaseActivity {
	private LinearLayout btn_back;
	private RelativeLayout lay_title;
	private TextView tv_title;
	private Button btn_exit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_main_more);
		initValues();
		initViews();
		initListeners();
	}
	private void initValues(){
		
	}
	private void initViews(){
		btn_back = (LinearLayout)findViewById(R.id.btn_commonBack);
		btn_exit = (Button)findViewById(R.id.btn_atyMainMore_exit);
		lay_title = (RelativeLayout)findViewById(R.id.lay_title);
		lay_title.setBackgroundColor(getResources().getColor(R.color.bule));
		tv_title = (TextView)findViewById(R.id.tv_commonTatle);
		tv_title.setText(R.string.more_setting);
	}
	private void initListeners(){
		btn_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		btn_exit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final View DialogView = LayoutInflater.from(Aty_Main_More.this).inflate(
						R.layout.dlg_exist, null);
				final Dialog dialog = new Dialog(Aty_Main_More.this,R.style.transparentFrameWindowStyle);
				dialog.setContentView(DialogView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
				dialog.setCanceledOnTouchOutside(true);
				Button btnok = (Button)DialogView.findViewById(R.id.btn_dlgExist_OK);
				Button btnCancel = (Button)DialogView.findViewById(R.id.btn_dlgExist_Cancel);
				btnok.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Editor editor =  getSharedPreferences(Config.APPID, Context.MODE_PRIVATE).edit();
						editor.clear();
						editor.commit();
						exit();
						startActivity(new Intent(Aty_Main_More.this,Aty_BindPhone.class));
					}
				});
				btnCancel.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						dialog.dismiss();
						
					}
				});
				dialog.show();
			}
		});
	}
}