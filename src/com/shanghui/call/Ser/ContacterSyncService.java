package com.shanghui.call.Ser;

import com.shanghui.call.Mdl.Dfine;
import com.shanghui.call.Tools.Util;

import android.app.Service;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.util.Log;

public class ContacterSyncService extends Service {
         
        //延时处理同步联系人，若在延时期间，通话记录数据库未改变，即判断为联系人被改变了
        private final static int ELAPSE_TIME = 2000;
         
         
        private Handler mHandler = null;
        // 当联系人数据库发生更改时触发此操作
        public ContentObserver mObserver = new ContentObserver(new Handler()) {

                @Override
                public void onChange(boolean selfChange) {
                        // 当系统联系人数据库发生更改时触发此操作
                		System.out.println("----->联系人发生变化");
                        //去掉多余的或者重复的同步
                        mHandler.removeMessages(0);
                        //延时ELAPSE_TIME(10秒）发送同步信号“0”
                        mHandler.sendEmptyMessageDelayed(0, ELAPSE_TIME);
                }

        };

        // 当通话记录数据库发生更改时触发此操作
        private ContentObserver mCallLogObserver = new ContentObserver(new Handler()) {
                @Override
                public void onChange(boolean selfChange) {
                        // 当通话记录数据库发生更改时触发此操作
                		System.out.println("------>通话记录发生变化");
                        //如果延时期间发现通话记录数据库也改变了，即判断为联系人未被改变，取消前面的同步
                        mHandler.removeMessages(1);
                        mHandler.removeMessages(0);
                        mHandler.sendEmptyMessageDelayed(1, ELAPSE_TIME);
                }

        };
         
         

        //在此处处理联系人被修改后应该执行的操作
        public void updataContact() {
                //DO SOMETHING HERE...
        	System.out.println("------->@联系人发生改变@");
			Dfine.contacts.clear();
			System.out.println("----->Dfine.contacts.size"+Dfine.contacts.size());
			Util.getContacts(getApplicationContext());
        }
        //在此处处理通话记录被修改后应该执行的操作
        public void updataCallLogs() {
        	System.out.println("------->@通话记录发生改变@");
			Dfine.callLogs.clear();
			System.out.println("----->Dfine.Calllogs.size"+Dfine.callLogs.size());
			Util.getCallLogs(getApplicationContext());
        }

        @Override
        public IBinder onBind(Intent arg0) {
                return null;
        }

        @Override
        public void onCreate() {
                super.onCreate();
                //注册监听通话记录数据库
                getContentResolver().registerContentObserver(CallLog.Calls.CONTENT_URI, true, mCallLogObserver);
                //注册监听联系人数据库
                getContentResolver().registerContentObserver(ContactsContract.Contacts.CONTENT_URI, true, mObserver);
                 
                //为了避免同步联系人时阻塞主线程，此处获取一个子线程的handler
                new Thread(new Runnable() {
                        public void run() {
                                Looper.prepare();
                                mHandler = new Handler() {
                                        @Override
                                        public void handleMessage(Message msg) {
                                                switch (msg.what) {
                                                case 0:
                                                        updataContact();   
                                                        break;
                                                case 1:
                                                		updataCallLogs();
                                                	break;
                                                default:
                                                        break;
                                                }
                                        }
                                };
                                Looper.loop();
                        }
                }).start();
        }

        @Override
        public void onStart(Intent intent, int startId) {
                super.onStart(intent, startId);

        }

        @Override
        public void onDestroy() {
                super.onDestroy();
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
                return flags;
        }

}
