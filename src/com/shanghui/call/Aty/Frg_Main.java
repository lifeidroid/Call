package com.shanghui.call.Aty;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.shanghui.call.Config;
import com.shanghui.call.R;
import com.shanghui.call.R.color;
import com.shanghui.call.Net.NetGetCustomer;
import com.shanghui.call.Tools.AutoScrollTextView;
/**
 * 主页模块
 * @author shanghui
 *
 */
public class Frg_Main extends Fragment {
	private View view;
	private AutoScrollTextView tv_text;
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
	
	private boolean scrol;

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
		scrol = true;
	}

	private void initViews() {
		tv_text = (AutoScrollTextView) view.findViewById(R.id.tv_frgmain_text);
		tv_text.init(getActivity().getWindowManager());
		tv_text.startScroll();
		tv_text.setMovementMethod(LinkMovementMethod.getInstance()); 
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
	}

	private void initListeners() {		
		// 滚动条
		tv_text.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (scrol) {
					tv_text.stopScroll();
					scrol = false;
				}else {
					tv_text.startScroll();
					scrol = true;
				}
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
				showShare();

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
				final ProgressDialog pg = new ProgressDialog(getActivity()).show(getActivity(), null, "正在查询请稍等..");
				new NetGetCustomer(Config.getCaChePhoneNum(getActivity()), new NetGetCustomer.SuccessCallback() {
					@Override
					public void onSuccess(String AccountNum, String AccountName,
							String CurrentBalance, String validity, String overdraft) {
						pg.dismiss();
						final View DialogView = LayoutInflater.from(getActivity()).inflate(
								R.layout.dlg_query_call, null);
						final Dialog dialog = new Dialog(getActivity(),R.style.transparentFrameWindowStyle);
						dialog.setContentView(DialogView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
						dialog.setCanceledOnTouchOutside(true);
						TextView tv_account_num = (TextView)DialogView.findViewById(R.id.tv_dlgQueryCall_account_num);
						TextView tv_CurrentBalance = (TextView)DialogView.findViewById(R.id.tv_dlgQueryCall_account_CurrentBalance);
						TextView tv_validity = (TextView)DialogView.findViewById(R.id.tv_dlgQueryCall_account_validity);
						TextView tv_overdraft = (TextView)DialogView.findViewById(R.id.tv_dlgQueryCall_account_overdraft);
						Button btn_cancel = (Button)DialogView.findViewById(R.id.btn_dlgQueryCall_cancel);
						tv_account_num.setText("账号："+Config.getCaChePhoneNum(getActivity()));
						tv_CurrentBalance.setText("当前余额："+CurrentBalance);
						tv_validity.setText("过期时间："+validity);
						tv_overdraft.setText("透支限额："+overdraft);
						btn_cancel.setOnClickListener(new View.OnClickListener() {
							
							@Override
							public void onClick(View arg0) {
								dialog.dismiss();
								
							}
						});
						dialog.show();
					}
				}, new NetGetCustomer.FailCallback() {
					
					@Override
					public void onFail(String error) {
						pg.dismiss();
						showToast(getActivity(), error, Toast.LENGTH_LONG);
						
					}
				});

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
	 private void showShare() {
	        ShareSDK.initSDK(getActivity());
	        OnekeyShare oks = new OnekeyShare();
	        //关闭sso授权
	        oks.disableSSOWhenAuthorize();
	        
	        // 分享时Notification的图标和文字
	        oks.setNotification(R.drawable.imglogo, getString(R.string.app_name));
	        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
	        oks.setTitle(getString(R.string.share));
	        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
	        oks.setTitleUrl("http://sharesdk.cn");
	        // text是分享文本，所有平台都需要这个字段
	        oks.setText("嗨！我正在使用打电话软件，这是商家免费赠送的。请先下载软件，Andriod下载地址：http://www.360shanghui.com/ddh.apk；苹果系统请去官方网站 http://www.360shanghui.com 下载专区下载，如想使用免费电话，请向当地商家索取电话充值卡。 ");
	        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
	        // oks.setImagePath("/sdcard/test.jpg");
	        // url仅在微信（包括好友和朋友圈）中使用
	        oks.setUrl("http://sharesdk.cn");
	        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
	        oks.setComment("我是测试评论文本");
	        // site是分享此内容的网站名称，仅在QQ空间使用
	        oks.setSite(getString(R.string.app_name));
	        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
	        oks.setSiteUrl("http://sharesdk.cn");

	        // 启动分享GUI
	        oks.show(getActivity());
	   }
}
