package com.shanghui.call.Aty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shanghui.call.Mdl.Mdl_CallLog;

import android.app.Application;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;

public class App_Main extends Application {
	private getDataThread getDataThread;
	private List<String> contentName = new ArrayList<String>();
	private List<Map<String, String>> contentMapList = new ArrayList<Map<String,String>>() ;
	private Map<String, String> contentMap ;
	@Override
	public void onCreate() {
		super.onCreate();
		getDataThread = new getDataThread();
		getDataThread.start();
	}
	public List<String> getContentName() {
		return contentName;
	}
	public List<Map<String, String>> getContentMapList() {
		return contentMapList;
	}
	private class getDataThread extends Thread{
		@Override
		public void run() {
			int j = 0;
			ContentResolver cr = getContentResolver();
			Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null,null, null, null);
			while (cursor.moveToNext()) {
				// get name
				int nameFiledColumnIndex = cursor.getColumnIndex(PhoneLookup.DISPLAY_NAME);
				String contact = cursor.getString(nameFiledColumnIndex);
				String[] PHONES_PROJECTION = new String[] { "display_name","data1" };
				String contactId = cursor.getString(cursor.getColumnIndex(PhoneLookup._ID));
				//(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
				Cursor phone = cr.query(
						ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
						PHONES_PROJECTION,ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "="+ contactId, null, null);
				// name type ..
				contentMap = new HashMap<String , String>();
				while (phone.moveToNext()) {
					contentMap.put(phone.getString(0), phone.getString(1));
					contentName.add(phone.getString(0));
					contentMapList.add(contentMap);
					System.out.println("----->"+phone.getString(0));
				}
				phone.close();
				++j;
			}
			cursor.close();
			super.run();
		}
	}
/*	private void getListData(){
		int j = 0;
		ContentResolver cr = getContentResolver();
		Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null,null, null, null);
		while (cursor.moveToNext()) {
			// get name
			int nameFiledColumnIndex = cursor.getColumnIndex(PhoneLookup.DISPLAY_NAME);
			String contact = cursor.getString(nameFiledColumnIndex);
			String[] PHONES_PROJECTION = new String[] { "display_name","data1" };
			String contactId = cursor.getString(cursor.getColumnIndex(PhoneLookup._ID));
			//(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
			Cursor phone = cr.query(
					ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
					PHONES_PROJECTION,ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "="+ contactId, null, null);
			// name type ..
			contentMap = new HashMap<String , String>();
			while (phone.moveToNext()) {
				contentMap.put(phone.getString(0), phone.getString(1));
				contentName.add(phone.getString(0));
				contentMapList.add(contentMap);
				System.out.println("----->"+phone.getString(0));
			}
			phone.close();
			++j;
		}
		cursor.close();
	}*/
	
	
}
