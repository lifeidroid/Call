package com.shanghui.call.Aty;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.shanghui.call.Config;
import com.shanghui.call.R;
/**
 * 企业管理
 * @author shanghui
 *
 */
public class Aty_Main_QiyeManager extends Activity {
	private Button btn_back;
	private WebView wb_content;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_main_qiye_manager);
		initValues();
		initViews();
		initListeners();
	}
	private void initValues(){
		
	}
	private void initViews(){
		btn_back = (Button)findViewById(R.id.btn_atymainQiyeManager_back);
		wb_content = (WebView)findViewById(R.id.wb_atyMainQiyeManager);
	}
	private void initListeners(){
		btn_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		wb_content.getSettings().setJavaScriptEnabled(true);
		wb_content.loadUrl(Config.E_BOSS_URL);
		wb_content.setWebViewClient(new MyWebViewClient());	
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (wb_content.getUrl().equals(Config.E_BOSS_URL)) {
				finish();
				return true;
			}
			wb_content.goBack();
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
