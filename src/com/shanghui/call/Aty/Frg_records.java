package com.shanghui.call.Aty;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.shanghui.call.R;
import com.shanghui.call.Adp.Adp_CallLog;
import com.shanghui.call.Mdl.Mdl_CallLog;

import android.app.Dialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

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
		adapter = new Adp_CallLog(getActivity());
		adapter.clear();
		adapter.addAll(getRecordList());
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
					showDialog();

			}
		});
	}

	private List<Mdl_CallLog> getRecordList() {
		Date date;
		ContentResolver cr = getActivity().getContentResolver();
		final Cursor cursor = cr.query(CallLog.Calls.CONTENT_URI, new String[] {
				CallLog.Calls.NUMBER, CallLog.Calls.CACHED_NAME,
				CallLog.Calls.TYPE, CallLog.Calls.DATE }, null, null,
				CallLog.Calls.DEFAULT_SORT_ORDER);
		for (int i = 0; i < cursor.getCount(); i++) {
			cursor.moveToPosition(i);
			mdl_CallLog = new Mdl_CallLog();
			mdl_CallLog.setPhoneNum(cursor.getString(0));
			mdl_CallLog.setUserName(cursor.getString(1));
			mdl_CallLog.setCallType(cursor.getInt(2));
			SimpleDateFormat sfd = new SimpleDateFormat("MM-dd hh:mm");
			date = new Date(Long.parseLong(cursor.getString(3)));
			mdl_CallLog.setCallDate(sfd.format(date));
			recordList.add(mdl_CallLog);
		}
		return recordList;
		/**
		 * CallLog.Calls.CONTENT_URI （通话记录数据库） CallLog.Calls.NUMBER （通话号码）
		 * CallLog.Calls.CACHED_NAME （通话人姓名） CallLog.Calls.TYPE （通话类型）
		 * 来电：CallLog.Calls.INCOMING_TYPE （常量值：1）
		 * 已拨：CallLog.Calls.OUTGOING_TYPE（常量值：2）
		 * 未接：CallLog.Calls.MISSED_TYPE（常量值：3）
		 */
	}

	private void showDialog() {
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
		dialogInitLitener();
		dlgCall.show();
	}
	private void dialogInitLitener(){
		btn_Dlgcancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dlgCall.dismiss();
				
			}
		});
		btn_DlgshanghuiCall.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
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
