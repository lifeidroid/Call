package com.shanghui.call.Aty;

import com.shanghui.call.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/**
 * 资费说明
 * @author shanghui
 *
 */
public class Aty_Main_TariffDesc extends Activity {
	private Button btn_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_main_tariff_desc);
		initValues();
		initViews();
		initListeners();
	}
	private void initValues(){
		
	}
	private void initViews(){
		btn_back = (Button)findViewById(R.id.btn_atymainTariffDesc_back);
	}
	private void initListeners(){
		btn_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
	}
}
