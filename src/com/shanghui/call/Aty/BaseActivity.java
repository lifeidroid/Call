package com.shanghui.call.Aty;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.shanghui.call.ActivityManager;

public class BaseActivity extends FragmentActivity {
         
        private ActivityManager manager = ActivityManager.getActivityManager(this);
         
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                manager.putActivity(this);
        }

        @Override
        protected void onDestroy() {
                super.onDestroy();
                manager.removeActivity(this);
        }
         
        public void exit(){
                manager.exit();
        }
         
}
