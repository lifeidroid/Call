package com.shanghui.call.Aty;

import com.shanghui.call.R;
import com.shanghui.call.Mdl.Dfine;
import com.shanghui.call.Net.Net_PayByPhoneCard;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 话费充值
 * @author shanghui
 *
 */
public class Aty_Main_CallCast extends Activity {
	private Button btn_back;
	private TextView tv_phoneNum;
	private EditText et_castNum;
	private EditText et_castPassword;
	private Button btn_sure;
	private String phoneNum;
	private static Toast mToast;
	private TextView tv_hint;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_main_call_cast);
		initValues();
		initViews();
		initListeners();
	}
	private void initValues(){
		phoneNum = Dfine.phoneNum.toString();
	}
	private void initViews(){
		btn_back = (Button)findViewById(R.id.btn_atymaincallcast_back);
		tv_phoneNum = (TextView)findViewById(R.id.tv_atyMainCallCast_num);
		tv_phoneNum.setText("被充值的手机号："+phoneNum);
		et_castNum = (EditText)findViewById(R.id.et_atyMainCallCast_Cardnum);
		et_castPassword = (EditText)findViewById(R.id.et_atyMainCallCast_Cardpassword);
		btn_sure = (Button)findViewById(R.id.btn_atyMainCallCast_Sure);
		tv_hint = (TextView)findViewById(R.id.tv_atyMainCallCast_hint);
		tv_hint.setText(Html.fromHtml("<p>1、使用此软件前，需打开数据网络、确保您的手机话费余额不为零，否则会影响软件使用。<br>2、在漫游地区使用时，请确保您的手机无漫游费用，否则使用本软件时运营商将收取漫游费用。<br>3、确认充值成功后，才可丢弃充值卡。<p>"));
	}
	private void initListeners(){
		btn_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		btn_sure.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if (TextUtils.isEmpty(et_castNum.getText())) {
					showToast(Aty_Main_CallCast.this, "卡号不能为空！", Toast.LENGTH_SHORT);
					return;
				}
				if (TextUtils.isEmpty(et_castPassword.getText())) {
					showToast(Aty_Main_CallCast.this, "密码不能为空！", Toast.LENGTH_SHORT);
					return;
				}
				final ProgressDialog pg = new ProgressDialog(Aty_Main_CallCast.this).show(Aty_Main_CallCast.this, null, "正在充值请稍后...");
				new Net_PayByPhoneCard(phoneNum, et_castNum.getText().toString(), et_castPassword.getText().toString(), new Net_PayByPhoneCard.SuccessCallback() {
					
					@Override
					public void onSuccess() {
						pg.dismiss();
						showToast(Aty_Main_CallCast.this,"充值成功！" , Toast.LENGTH_SHORT);
					}
				}, new Net_PayByPhoneCard.FailCallback() {
					
					@Override
					public void onFail(String error) {
						pg.dismiss();
						showToast(Aty_Main_CallCast.this,error , Toast.LENGTH_SHORT);
						
					}
				});
			}
		});
		
	}
	
	public static void showToast(Context context, String text, int duration) {
		if (mToast == null) {
			mToast = Toast.makeText(context, text, duration);
		} else {
			mToast.setText(text);
			mToast.setDuration(duration);
		}

		mToast.show();
	}
}
