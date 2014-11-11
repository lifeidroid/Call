package com.shanghui.call.Aty;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.shanghui.call.Config;
import com.shanghui.call.R;
/**
 * 商会官网
 * @author shanghui
 *
 */
public class Aty_Main_ShanghuiGva extends BaseActivity {
	private Button btn_back;
	private WebView wb_content;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_main_shanghui_gva);
		initValues();
		initViews();
		initListeners();
	}
	private void initValues(){
		
	}
	private void initViews(){
		btn_back = (Button)findViewById(R.id.btn_atymainShanghuiGva_back);
		wb_content = (WebView)findViewById(R.id.wb_atyMainShanghuiGva);
	}
	private void initListeners(){
		btn_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		wb_content.getSettings().setJavaScriptEnabled(true);
		wb_content.loadUrl(Config.SHANGHUI_URL);
		wb_content.setWebViewClient(new MyWebViewClient());	
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (wb_content.getUrl().equals(Config.SHANGHUI_URL)) {
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
