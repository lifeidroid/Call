package com.shanghui.call.Aty;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.shanghui.call.Config;
import com.shanghui.call.R;
import com.shanghui.call.Tools.AlwaysMarqueeTextView;
/**
 * 主页模块
 * @author shanghui
 *
 */
public class Frg_Main extends Fragment {
	private View view;
	private AlwaysMarqueeTextView tv_text;
	private ImageView iv_top;
	private ImageView iv_call_cast;// 话费充值
	private ImageView iv_outlet;// 折扣店
	private ImageView iv_chat;// 聊聊
	private ImageView iv_qiye_manager;// 企业管理
	private ImageView iv_local_area_code;// 本地区号
	private ImageView iv_load_address;// 下载地址
	private ImageView iv_auto_update;// 自动更新
	private ImageView iv_tariff_desc;// 资费说明
	private ImageView iv_help;// 使用指南
	private ImageView iv_query_call;// 查询余额
	private ImageView iv_shanghui_league;// 商会联盟
	private ImageView iv_shanghui_gva;// 商会官网
	private ImageView iv_sever_call;// 服务电话
	private ImageView iv_feedback;// 意见反馈
	private ImageView iv_more;// 更多设置
	private static Toast mToast;

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

	private void initValues() {

	}

	private void initViews() {
		tv_text = (AlwaysMarqueeTextView) view
				.findViewById(R.id.tv_frgmain_text);
		tv_text.setHorizontallyScrolling(true);  
		tv_text.setFocusable(true); 
		iv_top = (ImageView) view.findViewById(R.id.iv_frgmain_top);
		iv_call_cast = (ImageView) view.findViewById(R.id.iv_frgmain_call_cast);
		iv_outlet = (ImageView) view.findViewById(R.id.iv_frgmain_outlet);
		iv_chat = (ImageView) view.findViewById(R.id.iv_frgmain_chat);
		iv_qiye_manager = (ImageView) view
				.findViewById(R.id.iv_frgmain_qiye_manager);
		iv_local_area_code = (ImageView) view
				.findViewById(R.id.iv_frgmain_local_area_code);
		iv_load_address = (ImageView) view
				.findViewById(R.id.iv_frgmain_load_address);
		iv_auto_update = (ImageView) view
				.findViewById(R.id.iv_frgmain_auto_update);
		iv_tariff_desc = (ImageView) view
				.findViewById(R.id.iv_frgmain_tariff_desc);
		iv_help = (ImageView) view.findViewById(R.id.iv_frgmain_help);
		iv_query_call = (ImageView) view
				.findViewById(R.id.iv_frgmain_query_call);
		iv_shanghui_league = (ImageView) view
				.findViewById(R.id.iv_frgmain_shanghui_league);
		iv_shanghui_gva = (ImageView) view
				.findViewById(R.id.iv_frgmain_shanghui_gva);
		iv_sever_call = (ImageView) view
				.findViewById(R.id.iv_frgmain_sever_call);
		iv_feedback = (ImageView) view.findViewById(R.id.iv_frgmain_feedback);
		iv_more = (ImageView) view.findViewById(R.id.iv_frgmain_more);
		tv_text.setFocusable(true);
	}

	private void initListeners() {
		// 滚动条
		tv_text.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		// 企业定制版本
		iv_top.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), Aty_Main_Top.class));

			}
		});
		// 话费充值
		iv_call_cast.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), Aty_Main_CallCast.class));

			}
		});
		// 折扣店
		iv_outlet.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), Aty_Main_Outlet.class));

			}
		});
		// 聊一聊
		iv_chat.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), Aty_Main_Chat.class));

			}
		});
		// 企业管理
		iv_qiye_manager.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), Aty_Main_QiyeManager.class));

			}
		});
		// 本地区号
		iv_local_area_code.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				final View DialogView = LayoutInflater.from(getActivity()).inflate(
						R.layout.dlg_set_local_code, null);
				final Dialog dialog = new Dialog(getActivity(),R.style.transparentFrameWindowStyle);
				dialog.setContentView(DialogView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
				dialog.setCanceledOnTouchOutside(true);
				final EditText et_content = (EditText)DialogView.findViewById(R.id.et_dlgSetLocalCode_text);
				et_content.setText(Config.getCaCheLocalCode(getActivity()));
				Button btnOk = (Button)DialogView.findViewById(R.id.btn_dlgSetLocalCode_OK);
				Button btnCancel = (Button)DialogView.findViewById(R.id.btn_dlgSetLocalCode_Cancel);
				btnOk.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						if (TextUtils.isEmpty(et_content.getText())) {
							showToast(getActivity(), getString(R.string.local_code_can_not_empty), Toast.LENGTH_SHORT);
							return;
						}
						Config.cacheLocalCode(getActivity(), et_content.getText().toString());
						showToast(getActivity(), "设置区号为："+et_content.getText().toString(), Toast.LENGTH_SHORT);
						dialog.dismiss();
					}
				});
				btnCancel.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						dialog.dismiss();
						
					}
				});
				dialog.show();
			}
		});
		// 下载更新
		iv_load_address.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		// 资费说明
		iv_tariff_desc.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(),Aty_Main_TariffDesc.class));

			}
		});
		// 使用指南
		iv_help.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(),Aty_Main_Help.class));

			}
		});
		// 查询余额
		iv_query_call.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		// 商会联盟
		iv_shanghui_league.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(),Aty_Main_ShanghuiLeague.class));

			}
		});
		// 商会官网
		iv_shanghui_gva.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(),Aty_Main_ShanghuiGva.class));

			}
		});
		// 服务电话
		iv_sever_call.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse("tel:4006320708");
				Intent intent = new Intent(Intent.ACTION_DIAL, uri);
				startActivity(intent);

			}
		});
		// 意见反馈
		iv_feedback.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(),Aty_Main_FeedBack.class));

			}
		});
		// 更多设置
		iv_more.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(),Aty_Main_More.class));

			}
		});
	}
	public static void showToast(Context context, String text, int duration) {
		if (mToast == null) {
			mToast = Toast.makeText(context, text, duration);
		} else {
			mToast.setText(text);
			mToast.setDuration(duration);
		}

		mToast.show();
	}
}
