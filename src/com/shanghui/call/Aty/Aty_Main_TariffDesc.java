package com.shanghui.call.Aty;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shanghui.call.R;
/**
 * 资费说明
 * @author shanghui
 *
 */
public class Aty_Main_TariffDesc extends BaseActivity {
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
