package com.shanghui.call.Ser;

import android.database.ContentObserver;
import android.os.Handler;
/**
 * 通讯录修改监听事件
 * @author shanghui-1
 *
 */
public class SerContents extends ContentObserver {
    private static final int MSG_CONTENT = 1;


	private Handler mHandler; // 更新UI线程

	public SerContents( Handler handler) {
		super(handler);
		mHandler = handler;
	}

	/**
	 * 当所监听的Uri发生改变时，就会回调此方法 
	 * @param selfChange此值意义不大 一般情况下该回调值false
	 */
	@Override
	public void onChange(boolean selfChange) {
		mHandler.obtainMessage(MSG_CONTENT, "contents changed").sendToTarget();

	}
}
