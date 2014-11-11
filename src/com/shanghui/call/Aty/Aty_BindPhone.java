package com.shanghui.call.Aty;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shanghui.call.Config;
import com.shanghui.call.R;
/**
 * 绑定手机号界面
 * @author shanghui-1
 *
 */
public class Aty_BindPhone extends BaseActivity {
	private TextView tv_Prompt;
	private TextView tv_agreement;
	private EditText et_content;
	private Button btn_bind;
	private static Toast mToast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_bindphone);
		initViews();
		initListener();
	}

	private void initViews() {
		tv_Prompt = (TextView) findViewById(R.id.tv_atyBindPhone_Prompt);
		tv_agreement = (TextView) findViewById(R.id.tv_atyBindPhone_agreement);
		et_content = (EditText) findViewById(R.id.et_atyBindPhone_num);
		btn_bind = (Button) findViewById(R.id.btn_atyBindPhone);
		tv_Prompt
				.setText(Html
						.fromHtml("<p><h4><font color=#4584f6>★</font><font color=#434343>温馨提示</font><font color=#4584f6>★</font></p></h4>1、请使用您的真实手机号码进行绑定，否则可能无法完成绑定<br><br>2、本软件话费仅限于在软件中使用，与您手机的运营商话费无关",imgGetter, null));
		tv_agreement.setText(Html.fromHtml("确认绑定代表您已接受商惠通软件"+ "<a href=\"http://www.360shanghui.com\">使用协议</a> "));
		tv_agreement.setMovementMethod(LinkMovementMethod.getInstance()); 
	}

	private void initListener() {
		btn_bind.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (TextUtils.isEmpty(et_content.getText())) {
					showToast(Aty_BindPhone.this, "手机号不能为空", Toast.LENGTH_LONG);
					return;
				}
				if (!(et_content.getText().length() == 11)) {
					showToast(Aty_BindPhone.this, "请输入11位手机号",Toast.LENGTH_LONG);
					return;
				}
				Config.cachePhoneNum(Aty_BindPhone.this, et_content.getText().toString());
				startActivity(new Intent(Aty_BindPhone.this,Aty_Main.class));
				finish();
				showToast(Aty_BindPhone.this, "成功绑定手机号："+ et_content.getText().toString(), Toast.LENGTH_LONG);			
			}
		});
	}
	/**
	 * 获取本地图片
	 */
	ImageGetter imgGetter = new Html.ImageGetter() {
		public Drawable getDrawable(String source) {
			Drawable drawable = null;
			drawable = getResources().getDrawable(R.drawable.img_star); // 显示本地图片
			drawable.setBounds(0, 0, 40, 40);
			return drawable;

		}
	};
	/**
	 * 吐司
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
