package com.shanghui.call.Aty;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shanghui.call.Config;
import com.shanghui.call.R;
import com.shanghui.call.Adp.SearchListAdapter;
import com.shanghui.call.Mdl.Dfine;
import com.shanghui.call.Mdl.Mdl_Contact;
/**
 * 打电话界面
 * @author shanghui
 *
 */
public class Frg_Call extends Fragment implements View.OnClickListener {
	private View view;
	private TextView tv_num;
	private ImageView iv_key_main;
	private ListView lv_content;
	private ImageView iv_del;
	private ImageView iv_call;
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
	private SearchListAdapter adapter;
	private Intent intent;
	private Mdl_Contact mdl_Contact;
	private View dlgView;
	private Dialog dlgCall;
	private Button btn_DlgwifiCall;
	private Button btn_DlgshanghuiCall;
	private Button btn_Dlgcancel;
	private StringBuffer nun_Buffer = new StringBuffer();
	private Bitmap head;
	private String name;

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
		adapter = new SearchListAdapter(getActivity());
	}

	private void initView() {
		iv_key_main = (ImageView) view.findViewById(R.id.iv_frg_call_keymain);
		lay_keyboard = (RelativeLayout) view.findViewById(R.id.lay_keyboard);
		iv_keyboard_down = (ImageView) view.findViewById(R.id.iv_keyboard_down);
		iv_call = (ImageView)view.findViewById(R.id.iv_key_call);
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
		lv_content = (ListView)view.findViewById(R.id.lv_frgCall_Content);
		lv_content.setAdapter(adapter);
		lay_keyboard.setVisibility(View.GONE);
	}

	private void initListener() {
		lv_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				tv_num.setText(((Mdl_Contact)adapter.getItem(arg2)).getPhoneNum());
				nun_Buffer = new StringBuffer(((Mdl_Contact)adapter.getItem(arg2)).getPhoneNum());
				mdl_Contact = (Mdl_Contact)adapter.getItem(arg2);
				name = mdl_Contact.getName();
				head = mdl_Contact.getHead();
				//showDialog(mdl_Contact.getName(), mdl_Contact.getPhoneNum(), mdl_Contact.getHead());
			}
		});
		iv_key_main.setOnClickListener(this);
		iv_keyboard_down.setOnClickListener(this);
		iv_call.setOnClickListener(this);
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
		
		
		tv_num.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				if (s.length() > 0) {
					tv_num.setTextSize(26.0f);
					adapter.getFilter().filter(s);
				} else {
					adapter.clear();
					adapter.notifyDataSetChanged();
					tv_num.setTextSize(20.0f);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});

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
			getActivity().findViewById(R.id.lay_aty_main_bottom).setVisibility(View.VISIBLE);
			break;
		case R.id.iv_key_call:
			intent = new Intent(getActivity(),Aty_Calling.class);
			if (head != null) {
				intent.putExtra(Config.KEY_HEAD,head);
			}else {
				intent.putExtra(Config.KEY_HEAD, BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.img_people));
			}
			if (name != null) {
				intent.putExtra(Config.KEY_NAME, name);
			}else {
				intent.putExtra(Config.KEY_NAME, "未知");
			}
			intent.putExtra(Config.KEY_NUM, nun_Buffer.toString());
			startActivity(intent);
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
	
/*	private void showDialog(String name,String num,Bitmap head){
		dlgView = getActivity().getLayoutInflater().inflate(R.layout.dlg_call, null);
		dlgCall = new Dialog(getActivity(), R.style.transparentFrameWindowStyle);
		dlgCall.setContentView(dlgView, new LayoutParams(
		LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		Window window = dlgCall.getWindow();// 设置显示动画
		window.setWindowAnimations(R.style.main_menu_animstyle);
		WindowManager.LayoutParams wl = window.getAttributes();
		wl.x = 0;
		wl.y = getActivity().getWindowManager().getDefaultDisplay().getHeight();// 以下这两句是为了保证按钮可以水平满屏
		wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
		wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
		// 设置显示位置
		dlgCall.onWindowAttributesChanged(wl);
		// 设置点击外围解散
		dlgCall.setCanceledOnTouchOutside(true);
		btn_DlgshanghuiCall = (Button)dlgView.findViewById(R.id.btn_dlg_shanghui_call);
		btn_DlgwifiCall = (Button)dlgView.findViewById(R.id.btn_dlg_call_wifi_call);
		btn_Dlgcancel = (Button)dlgView.findViewById(R.id.btn_dlg_call_cancel);
		dialogInitLitener(name,num,head);
		dlgCall.show();
	}
	private void dialogInitLitener(final String name,final String num,final Bitmap head){
		btn_Dlgcancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dlgCall.dismiss();
				
			}
		});
		btn_DlgshanghuiCall.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent(getActivity(),Aty_Calling.class);
				intent.putExtra(Config.KEY_NAME,name);
				intent.putExtra(Config.KEY_NUM, num);
				intent.putExtra(Config.KEY_HEAD,head);
				startActivity(intent);
				dlgCall.dismiss();
				
			}
		});
		btn_DlgwifiCall.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}*/
}
