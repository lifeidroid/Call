package com.shanghui.call.Aty;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shanghui.call.R;
/**
 * 打电话界面
 * @author shanghui
 *
 */
public class Frg_Call extends Fragment implements View.OnClickListener {
	private View view;
	private TextView tv_num;
	private ImageView iv_key_main;
	private ImageView iv_del;
	private Dialog keyboard_dlg;
	private RelativeLayout lay_keyboard;
	private Animation animation;
	private ImageView iv_keyboard_down;
	private Button btn_num1;
	private Button btn_num2;
	private Button btn_num3;
	private Button btn_num4;
	private Button btn_num5;
	private Button btn_num6;
	private Button btn_num7;
	private Button btn_num8;
	private Button btn_num9;
	private Button btn_num0;
	private Button btn_numj;
	private Button btn_nums;

	private StringBuffer nun_Buffer = new StringBuffer();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.frg_call, null);
		initValues();
		initView();
		initListener();
		return view;
	}

	private void initValues() {

	}

	private void initView() {
		iv_key_main = (ImageView) view.findViewById(R.id.iv_frg_call_keymain);
		lay_keyboard = (RelativeLayout) view.findViewById(R.id.lay_keyboard);
		iv_keyboard_down = (ImageView) view.findViewById(R.id.iv_keyboard_down);
		iv_del = (ImageView)view.findViewById(R.id.iv_key_del);
		tv_num = (TextView) view.findViewById(R.id.tv_num);
		btn_num0 = (Button) view.findViewById(R.id.btn_keyboard_0);
		btn_num1 = (Button) view.findViewById(R.id.btn_keyboard_1);
		btn_num2 = (Button) view.findViewById(R.id.btn_keyboard_2);
		btn_num3 = (Button) view.findViewById(R.id.btn_keyboard_3);
		btn_num4 = (Button) view.findViewById(R.id.btn_keyboard_4);
		btn_num5 = (Button) view.findViewById(R.id.btn_keyboard_5);
		btn_num6 = (Button) view.findViewById(R.id.btn_keyboard_6);
		btn_num7 = (Button) view.findViewById(R.id.btn_keyboard_7);
		btn_num8 = (Button) view.findViewById(R.id.btn_keyboard_8);
		btn_num9 = (Button) view.findViewById(R.id.btn_keyboard_9);
		btn_nums = (Button) view.findViewById(R.id.btn_keyboard_star);
		btn_numj = (Button) view.findViewById(R.id.btn_keyboard_j);

		lay_keyboard.setVisibility(View.GONE);
	}

	private void initListener() {
		iv_key_main.setOnClickListener(this);
		iv_keyboard_down.setOnClickListener(this);
		btn_num0.setOnClickListener(this);
		btn_num1.setOnClickListener(this);
		btn_num2.setOnClickListener(this);
		btn_num3.setOnClickListener(this);
		btn_num4.setOnClickListener(this);
		btn_num5.setOnClickListener(this);
		btn_num6.setOnClickListener(this);
		btn_num7.setOnClickListener(this);
		btn_num8.setOnClickListener(this);
		btn_num9.setOnClickListener(this);
		btn_nums.setOnClickListener(this);
		btn_numj.setOnClickListener(this);
		iv_del.setOnClickListener(this);

	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_frg_call_keymain:
			animation = AnimationUtils.loadAnimation(getActivity(),
					R.anim.photo_dialog_in_anim);
			lay_keyboard.setAnimation(animation);
			lay_keyboard.setVisibility(View.VISIBLE);
			getActivity().findViewById(R.id.lay_aty_main_bottom).setVisibility(View.GONE);
			break;
		case R.id.iv_keyboard_down:
			animation = AnimationUtils.loadAnimation(getActivity(),
					R.anim.photo_dialog_out_anim);
			lay_keyboard.setAnimation(animation);
			lay_keyboard.setVisibility(View.GONE);
			tv_num.setText(R.string.call);
			getActivity().findViewById(R.id.lay_aty_main_bottom).setVisibility(View.VISIBLE);
			break;
		case R.id.btn_keyboard_0:
			nun_Buffer.append("0");
			tv_num.setText(nun_Buffer.toString());
			break;
		case R.id.btn_keyboard_1:
			nun_Buffer.append("1");
			tv_num.setText(nun_Buffer.toString());
			break;
		case R.id.btn_keyboard_2:
			nun_Buffer.append("2");
			tv_num.setText(nun_Buffer.toString());
			break;
		case R.id.btn_keyboard_3:
			nun_Buffer.append("3");
			tv_num.setText(nun_Buffer.toString());
			break;
		case R.id.btn_keyboard_4:
			nun_Buffer.append("4");
			tv_num.setText(nun_Buffer.toString());
			break;
		case R.id.btn_keyboard_5:
			nun_Buffer.append("5");
			tv_num.setText(nun_Buffer.toString());
			break;
		case R.id.btn_keyboard_6:
			nun_Buffer.append("6");
			tv_num.setText(nun_Buffer.toString());
			break;
		case R.id.btn_keyboard_7:
			nun_Buffer.append("7");
			tv_num.setText(nun_Buffer.toString());
			break;
		case R.id.btn_keyboard_8:
			nun_Buffer.append("8");
			tv_num.setText(nun_Buffer.toString());
			break;
		case R.id.btn_keyboard_9:
			nun_Buffer.append("9");
			tv_num.setText(nun_Buffer.toString());
			break;
		case R.id.btn_keyboard_star:
			nun_Buffer.append("*");
			tv_num.setText(nun_Buffer.toString());
			break;
		case R.id.btn_keyboard_j:
			nun_Buffer.append("#");
			tv_num.setText(nun_Buffer.toString());
			break;
		case R.id.iv_key_del:
			if (nun_Buffer.length() > 0) {
				nun_Buffer.deleteCharAt(nun_Buffer.length()-1);
			}
			if (nun_Buffer.length() == 0) {
				tv_num.setText(R.string.call);
			}else {
				tv_num.setText(nun_Buffer.toString());
			}
			break;

		default:
			break;
		}
	}
}
