package com.shanghui.call.Aty;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.Intents;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.shanghui.call.Config;
import com.shanghui.call.R;
import com.shanghui.call.Adp.Adp_SimCallLog;
import com.shanghui.call.Mdl.Dfine;
import com.shanghui.call.Mdl.Mdl_CallLog;
import com.shanghui.call.Mdl.Mdl_Contact;
import com.shanghui.call.Tools.RoundImageView;

/**
 * 联系人详情
 * 
 * @author shanghui
 */
public class Aty_ContentInfo extends Activity {
	private RoundImageView iv_head;
	private TextView tv_name;
	private TextView tv_title_edit;
	private TextView tv_num;
	private Button btn_call;
	private Button btn_back;
	private Intent intent;
	private String name;
	private String num;
	private long userId;
	private Bitmap head;
	private ListView lv_content;
	private RoundImageView iv_call;
	private RoundImageView iv_message;
	private RoundImageView iv_share;
	private List<Mdl_CallLog> listCallLogs;
	private List<Mdl_Contact> listContacts;
	private List<Mdl_CallLog> mCallLogs = new ArrayList<Mdl_CallLog>();
	private Adp_SimCallLog adapter;
	private SerchId searchId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_content_info);
		initValues();
		initViews();
		initListeners();
	}

	private void initValues() {
		intent = getIntent();
		name = intent.getExtras().getString(Config.KEY_NAME);
		num = intent.getExtras().getString(Config.KEY_NUM);
		head = intent.getParcelableExtra(Config.KEY_HEAD);
		userId = intent.getExtras().getLong(Config.KEY_ID);
		listCallLogs = Dfine.callLogs;
		listContacts = Dfine.contacts;
		adapter = new Adp_SimCallLog(Aty_ContentInfo.this);
		getmCallLog();
		System.out.println("-------->usrId" + userId);

	}

	private void initViews() {
		tv_title_edit = (TextView) findViewById(R.id.tv_commonEdit);
		if (userId == Config.NO_EXIST) {
			searchId = new SerchId();
			searchId.start();
		} else {
			tv_title_edit.setText("编辑");
		}
		iv_head = (RoundImageView) findViewById(R.id.iv_atyContentInfo_Head);
		tv_name = (TextView) findViewById(R.id.tv_atyContentInfo_Name);
		tv_num = (TextView) findViewById(R.id.tv_atyContentInfo_Num);
		btn_back = (Button) findViewById(R.id.btn_commonBack);
		lv_content = (ListView) findViewById(R.id.lv_atyContentInfo_list);
		iv_call = (RoundImageView) findViewById(R.id.riv_atyContentInfo_call);
		iv_message = (RoundImageView) findViewById(R.id.riv_atyContentInfo_message);
		iv_share = (RoundImageView) findViewById(R.id.riv_atyContentInfo_share);
		lv_content.setAdapter(adapter);
		adapter.clear();
		adapter.addAll(mCallLogs);
		tv_name.setText(name);
		tv_num.setText(num);
		if (head != null) {
			iv_head.setImageBitmap(head);
		} else {
			iv_head.setImageResource(R.drawable.img_people);
		}
	}

	private void initListeners() {
		btn_back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();

			}
		});
		tv_title_edit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (userId == Config.NO_EXIST) {//不存在联系人进行添加
					intent = new Intent(
							Intent.ACTION_INSERT,
							Uri.withAppendedPath(Uri.parse("content://com.android.contacts"),"contacts"));
							intent.putExtra(Intents.Insert.PHONE, num);
							startActivity(intent);
				} else {//存在联系人进行编辑
						intent = new Intent(Intent.ACTION_EDIT, Uri.withAppendedPath(
						ContactsContract.Contacts.CONTENT_URI,
						String.valueOf(userId)));
						startActivity(intent);
				}
			}
		});
		iv_call.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

					intent = new Intent(Aty_ContentInfo.this, Aty_Calling.class);
					intent.putExtra(Config.KEY_HEAD, head);
					intent.putExtra(Config.KEY_NAME, name);
					intent.putExtra(Config.KEY_NUM, num);
					startActivity(intent);
				
			}
		});
		iv_message.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		iv_share.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse("smsto:" + num);
				intent = new Intent(Intent.ACTION_SENDTO, uri);
				intent.putExtra(
						"sms_body",
						"嗨！我正在使用打电话软件，这是商家免费赠送的。请先下载软件，Andriod下载地址：http://www.360shanghui.com/ddh.apk ；苹果系统请去官方网站 http://www.360shanghui.com 下载专区下载，如想使用免费电话，请向当地商家索取电话充值卡。 ");
				startActivity(intent);
			}
		});
	}

	/**
	 * 获取有关联系人的通话记录
	 */
	private void getmCallLog() {
		for (int i = 0; i < listCallLogs.size(); i++) {
			if (num.equals(listCallLogs.get(i).getPhoneNum())) {

				mCallLogs.add(listCallLogs.get(i));
			}
		}
	}

	/**
	 * 获取联系人的ID
	 * 
	 * @author shanghui
	 * 
	 */
	private class SerchId extends Thread {
		@Override
		public void run() {
			for (int i = 0; i < listContacts.size(); i++) {
				if (num.equals(listContacts.get(i).getPhoneNum())) {
					userId = listContacts.get(i).getPeopleId();
				}
			}
			if (userId == Config.NO_EXIST) {
				tv_title_edit.setText("添加");
			} else {
				tv_title_edit.setText("编辑");
			}
			super.run();
		}
	}
}
