package com.shanghui.call.Aty;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.shanghui.call.Config;
import com.shanghui.call.R;
import com.shanghui.call.Adp.Adp_CallLog;
import com.shanghui.call.Mdl.Dfine;
import com.shanghui.call.Mdl.Mdl_CallLog;
/**
 * 最近通话记录
 * @author shanghui
 *
 */
public class Frg_records extends Fragment {
	private View view;
	private ListView lv_record;
	private Adp_CallLog adapter;
	private Mdl_CallLog mdl_CallLog;
	private List<Mdl_CallLog> recordList = new ArrayList<Mdl_CallLog>();
	private View dlgView;
	private Dialog dlgCall;
	private Button btn_DlgwifiCall;
	private Button btn_DlgshanghuiCall;
	private Button btn_Dlgcancel;
	private App_Main app_Main;
	private Intent mIntent;
	private Mdl_CallLog mCallLog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initValues();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.frg_records, null);
		initViews();
		initListener();
		return view;
	}

	private void initValues() {
		//app_Main = (App_Main) getActivity().getApplication();
		adapter = new Adp_CallLog(getActivity());
		adapter.clear();
		System.out.println("------CalllogsSize:"+Dfine.callLogs.size());
		adapter.addAll(Dfine.callLogs);
	}

	private void initViews() {
		lv_record = (ListView) view.findViewById(R.id.lv_frg_records);
		lv_record.setAdapter(adapter);
	}

	private void initListener() {
		lv_record.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
					mCallLog = (Mdl_CallLog)adapter.getItem(position);
					showDialog(mCallLog.getUserName(),mCallLog.getPhoneNum(),mCallLog.getHead());

			}
		});
	}


	private void showDialog(String name,String num,Bitmap head) {
		dlgView = getActivity().getLayoutInflater().inflate(R.layout.dlg_call,
				null);
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
				mIntent = new Intent(getActivity(),Aty_Calling.class);
				mIntent.putExtra(Config.KEY_NAME, name);
				mIntent.putExtra(Config.KEY_NUM, num);
				mIntent.putExtra(Config.KEY_HEAD, head);
				startActivity(mIntent);
				dlgCall.dismiss();
			}
		});
		btn_DlgwifiCall.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
