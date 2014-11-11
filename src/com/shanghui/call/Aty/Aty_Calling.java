package com.shanghui.call.Aty;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.CallLog;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shanghui.call.Config;
import com.shanghui.call.R;
import com.shanghui.call.Net.NetCallBack;
import com.shanghui.call.Tools.RoundImageView;

/**
 * 打电话界面
 * 
 * @author shanghui
 * 
 */
public class Aty_Calling extends BaseActivity {
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
	private MyPhoneStateListener phoneListener;
	private String PhoneNum;
	private static Toast mToast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_calling);
		initValues();
		initViews();
		initListener();
		if (PhoneNum.isEmpty()) {
			showToast(Aty_Calling.this, "请先绑定手机号！", Toast.LENGTH_LONG);
			finish();
		} else {
			System.out.println("------>num:"+num);
			new NetCallBack(PhoneNum, num, Config.NUMBER,
					Config.PASSWORD, new NetCallBack.SuccessCallback() {

						@Override
						public void onSuccess() {
							// TODO
							insertCallLog(num, "3", CallLog.Calls.OUTGOING_TYPE, 1l);
						}
					}, new NetCallBack.FailCallback() {

						@Override
						public void onFail(String error) {
							Toast.makeText(Aty_Calling.this, "连接服务器失败，请稍后重试！",
									Toast.LENGTH_LONG).show();

						}
					});
		}
	}

	private void initValues() {
		mIntent = getIntent();
		name = mIntent.getExtras().getString(Config.KEY_NAME);
		String callnum = mIntent.getExtras().getString(Config.KEY_NUM);
		String mnum = callnum.replace("+", "");
		if ("86".equals(mnum.substring(0, 2))) {
			num = mnum.substring(2, 13);
		}else {
			num = mnum;
		}
		head = mIntent.getParcelableExtra(Config.KEY_HEAD);
		phoneListener = new MyPhoneStateListener(); // 我们派生的类
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		telephonyManager.listen(phoneListener,
				PhoneStateListener.LISTEN_CALL_STATE);
		PhoneNum = Config.getCaChePhoneNum(Aty_Calling.this);
	}

	private void initViews() {
		tv_name = (TextView) findViewById(R.id.tv_atyCalling_name);
		tv_num = (TextView) findViewById(R.id.tv_atyCalling_num);
		riv_head = (RoundImageView) findViewById(R.id.riv_atyCalling_head);
		iv_handfree = (ImageView) findViewById(R.id.iv_atyCallling_handfree);
		tv_offCallling = (TextView) findViewById(R.id.tv_atyCallling_offCalling);
		iv_mute = (ImageView) findViewById(R.id.iv_atyCallling_mute);
		tv_name.setText(name);
		tv_num.setText(num);
		if (head != null) {
			riv_head.setImageBitmap(head);
		} else {
			riv_head.setImageResource(R.drawable.img_people);
		}
	}

	private void initListener() {
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
	/**
	 * 监听来电，拨通后关闭本页面
	 * @author shanghui-1
	 *
	 */
	private class MyPhoneStateListener extends PhoneStateListener {
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			if ("4006320708".equals(incomingNumber)) {
				finish();
			}
		}
	}
	/**
	 * 拨打电话向通话记录中增加一条数据
	 * @param number
	 * @param during
	 * @param type
	 * @param i
	 */
	private void insertCallLog(String number,String during, int type, long i)
	{
		/*
		 * 来电：CallLog.Calls.INCOMING_TYPE (常量值：1)
		 * 已拨：CallLog.Calls.OUTGOING_TYPE (常量值：2)
		 * 未接：CallLog.Calls.MISSED_TYPE (常量值：3)
		 */
	    ContentValues values = new ContentValues();
	    values.put(CallLog.Calls.NUMBER, number);
	    values.put(CallLog.Calls.DATE, System.currentTimeMillis()+i);
	    values.put(CallLog.Calls.DURATION, during);
	    values.put(CallLog.Calls.TYPE,type);//拨出
	    values.put(CallLog.Calls.NEW, 0);//0已看1未看	    
	    getContentResolver().insert(CallLog.Calls.CONTENT_URI, values);
	}
	/**
	 * 优化吐司
	 * @param context
	 * @param text
	 * @param duration
	 */
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
