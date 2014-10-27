package com.shanghui.call.Aty;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shanghui.call.R;
import com.shanghui.call.Tools.AlwaysMarqueeTextView;
public class Frg_Main extends Fragment {
	private View view;
	private AlwaysMarqueeTextView tv_text;
	private ImageView iv_top;
	private ImageView iv_call_cast;//话费充值
	private ImageView iv_outlet;//折扣店
	private ImageView iv_chat;//聊聊
	private ImageView iv_qiye_manager;//企业管理
	private ImageView iv_local_area_code;//本地区号
	private ImageView iv_load_address;//下载地址
	private ImageView iv_auto_update;//自动更新
	private ImageView iv_tariff_desc;//资费说明
	private ImageView iv_help;//使用指南
	private ImageView iv_query_call;//查询余额
	private ImageView iv_shanghui_league;//商会联盟
	private ImageView iv_shanghui_gva;//商会官网
	private ImageView iv_sever_call;//服务电话
	private ImageView iv_feedback;//意见反馈
	private ImageView iv_more;//更多设置
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initValues();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.frg_main, null);
		initViews();
		initListeners();
		return view;
	}
	private void initValues(){
		
	}
	private void initViews(){
		tv_text = (AlwaysMarqueeTextView)view.findViewById(R.id.tv_frgmain_text);
		tv_text.setFocusable(true);
	}
	private void initListeners(){
		
	}
}
