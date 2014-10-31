package com.shanghui.call.Aty;

import com.shanghui.call.Config;
import com.shanghui.call.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
/**
 * 企业定制
 * @author shanghui
 *
 */
public class Aty_Main_Top extends Activity {
	private WebView wb_main;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_main_top);
		initValues();
		initViews();
		initListener();
	}
	private void initValues(){
		
	}
	private void initViews(){
		wb_main = (WebView)findViewById(R.id.wb_atymaintop);
	}
	private void initListener(){
		wb_main.getSettings().setJavaScriptEnabled(true);
		wb_main.loadUrl(Config.SHANGHUI_URL);
		wb_main.setWebViewClient(new MyWebViewClient());		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (wb_main.getUrl().equals(Config.SHANGHUI_URL) ) {
				finish();
				return true;
			}
			wb_main.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
		
	}
	
	public class MyWebViewClient extends WebViewClient{
		public boolean shouldOverviewUrlLoading(WebView view,String url){
			view.loadUrl(url);
			return true;
		}
	}
	
}
