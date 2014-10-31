package com.shanghui.call.Aty;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.shanghui.call.R;
import com.shanghui.call.Mdl.Mdl_CallLog;
import com.shanghui.call.Mdl.Mdl_Contact;

import android.app.Application;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.Photo;
import android.text.TextUtils;

public class App_Main extends Application {
	private GetContentThread getContentThread;
	private GetRecordList getRecordList;
	private List<Mdl_Contact> contactList = new ArrayList<Mdl_Contact>();
	private List<Mdl_CallLog> callLogList = new ArrayList<Mdl_CallLog>();
	private Mdl_CallLog mdl_CallLog;
	/** 获取库Phon表字段 **/
	private static final String[] PHONES_PROJECTION = new String[] {Phone.DISPLAY_NAME, Phone.NUMBER, Photo.PHOTO_ID, Phone.CONTACT_ID };
	/** 联系人显示名称 **/
	private static final int PHONES_DISPLAY_NAME_INDEX = 0;
	/** 电话号码 **/
	private static final int PHONES_NUMBER_INDEX = 1;
	/** 头像ID **/
	private static final int PHONES_PHOTO_ID_INDEX = 2;
	/** 联系人的ID **/
	private static final int PHONES_CONTACT_ID_INDEX = 3;
	
	@Override
	public void onCreate() {
		super.onCreate();
		getContentThread = new GetContentThread();
		getContentThread.start();
		getRecordList = new GetRecordList();
		getRecordList.start();
		
	}
	public List<Mdl_Contact> getContactList() {
		return contactList;
	}
	public List<Mdl_CallLog> getCallLogList() {
		return callLogList;
	}
	/**
	 * 获取通讯录列表
	 * @author shanghui
	 *
	 */
	private class GetContentThread extends Thread{
		@Override
		public void run() {
			ContentResolver resolver = getContentResolver();

			// 获取手机联系人
			Cursor phoneCursor = resolver.query(Phone.CONTENT_URI,PHONES_PROJECTION, null, null, null);

			if (phoneCursor != null) {
				while (phoneCursor.moveToNext()) {

					// 得到手机号码
					String phoneNumber = phoneCursor.getString(PHONES_NUMBER_INDEX);
					// 当手机号码为空的或者为空字段 跳过当前循环
					if (TextUtils.isEmpty(phoneNumber))
						continue;

					// 得到联系人名称
					String contactName = phoneCursor.getString(PHONES_DISPLAY_NAME_INDEX);

					// 得到联系人ID
					Long contactid = phoneCursor.getLong(PHONES_CONTACT_ID_INDEX);

					// 得到联系人头像ID
					Long photoid = phoneCursor.getLong(PHONES_PHOTO_ID_INDEX);

					// 得到联系人头像Bitamp
					Bitmap contactPhoto = null;

					// photoid 大于0 表示联系人有头像 如果没有给此人设置头像则给他一个默认的
					if (photoid > 0) {
						Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactid);
						InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(resolver, uri);
						contactPhoto = BitmapFactory.decodeStream(input);
					} else {
						contactPhoto = BitmapFactory.decodeResource(getResources(),R.drawable.img_user);
					}
					contactList.add(new Mdl_Contact(contactid,contactName, phoneNumber, contactPhoto));
				}

				phoneCursor.close();
			}
			super.run();
		}
	}
	/**
	 * 获取最近通话记录
	 * @author shanghui
	 *
	 */
	private class GetRecordList extends Thread{
		@Override
		public void run() {
				Date date;
				ContentResolver cr = getContentResolver();
				final Cursor cursor = cr.query(CallLog.Calls.CONTENT_URI, new String[] {
						CallLog.Calls.NUMBER, CallLog.Calls.CACHED_NAME,
						CallLog.Calls.TYPE, CallLog.Calls.DATE ,
						CallLog.Calls.DURATION }, null, null,
						CallLog.Calls.DEFAULT_SORT_ORDER);
				for (int i = 0; i < cursor.getCount(); i++) {
					cursor.moveToPosition(i);
					mdl_CallLog = new Mdl_CallLog();
					mdl_CallLog.setPhoneNum(cursor.getString(0));//获取手机号
					if (TextUtils.isEmpty(cursor.getString(1))) {	//获取姓名		
						mdl_CallLog.setUserName("未知");
					}else {
						mdl_CallLog.setUserName(cursor.getString(1));
					}
					mdl_CallLog.setCallType(cursor.getInt(2));//获取通话类型
					SimpleDateFormat sfd = new SimpleDateFormat("MM-dd hh:mm");
					date = new Date(cursor.getLong(3));//获取通话时间
					mdl_CallLog.setCallDate(sfd.format(date));
					SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");//初始化Formatter的转换格式。
					String hms = formatter.format(cursor.getLong(4)*1000);  
					String[] my =hms.split(":");    
					int min =Integer.parseInt(my[0]);  
					int sec =Integer.parseInt(my[1]); 
					if (min == 0) {
						if (sec == 0) {
							mdl_CallLog.setDuring(null);
						}else {
							mdl_CallLog.setDuring(sec+"秒");						
						}
					}else {
						mdl_CallLog.setDuring(min+"分"+sec+"秒");
					}
				    Uri uri = Uri.parse("content://com.android.contacts/data/phones/filter/"+cursor.getString(1));
				    Cursor cursor2=cr.query(uri, new String[]{"photo_id"}, null, null, null);
				   if(cursor2.moveToFirst()){
					    String phot_IDo=cursor2.getString(0);
					    if(phot_IDo!=null){
						     Cursor cursor3=cr.query(ContactsContract.Data.CONTENT_URI, new String[]{"data15"}, "ContactsContract.Data._ID="+phot_IDo, null, null);
						    if(cursor3.moveToFirst()){
							     byte [] photoicon=cursor3.getBlob(0);
							     System.out.println(photoicon);
							     ByteArrayInputStream inputStream=new ByteArrayInputStream(photoicon);
							     mdl_CallLog.setHead(BitmapFactory.decodeStream(inputStream));
						    }
						    cursor3.close();
					   }  
				   }
				   	cursor2.close();
					callLogList.add(mdl_CallLog);
				}
				cursor.close();
				/**
				 * CallLog.Calls.CONTENT_URI （通话记录数据库） CallLog.Calls.NUMBER （通话号码）
				 * CallLog.Calls.CACHED_NAME （通话人姓名） CallLog.Calls.TYPE （通话类型）
				 * 来电：CallLog.Calls.INCOMING_TYPE （常量值：1）
				 * 已拨：CallLog.Calls.OUTGOING_TYPE（常量值：2）
				 * 未接：CallLog.Calls.MISSED_TYPE（常量值：3）
				 */
			super.run();
		}
	}

	
	
}
