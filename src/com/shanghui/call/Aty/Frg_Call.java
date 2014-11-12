package com.shanghui.call.Aty;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.shanghui.call.Config;
import com.shanghui.call.R;
import com.shanghui.call.Adp.SearchListAdapter;
import com.shanghui.call.Mdl.Dfine;
import com.shanghui.call.Mdl.Mdl_Contact;
import com.shanghui.call.Net.NetGetImage;
/**
 * 打电话界面
 * @author shanghui
 *
 */
public class Frg_Call extends Fragment implements View.OnClickListener {
	private Notification mNotification;
	private NotificationManager mNotificationManager;
	private long[] vib = { 0, 10 };
	private View view;
	private TextView tv_num;
	private ListView lv_content;
	private ImageView iv_del;
	private ImageView iv_call;
	private LinearLayout lay_keyboard;
	private LinearLayout lay_bottom;
	private FrameLayout lay_ads;
	private Animation animation;
	private ImageView iv_keyboard_down;
	private ImageView iv_keyboard_up;
	private Button btn_num1;
	private Button btn_num2;
	private Button btn_num3;
	private Button btn_num4;
	private Button btn_num5;
	private Button btn_num6;
	private Button btn_num7;
	private Button btn_num8;
	private Button btn_num9;
	private Button btn_num0;
	private Button btn_numj;
	private Button btn_nums;
	private SearchListAdapter adapter;
	private Intent intent;
	private Mdl_Contact mdl_Contact;
	private View dlgView;
	private Dialog dlgCall;
	private Button btn_DlgwifiCall;
	private Button btn_DlgshanghuiCall;
	private Button btn_Dlgcancel;
	private StringBuffer nun_Buffer = new StringBuffer();
	private Bitmap head;
	private String name;
	private static Toast mToast;
	
	private ViewPager viewPager; // android-support-v4中的滑动组件
	private List<ImageView> imageViews; // 滑动的图片集合
	private int[] imageResId; // 图片ID
	private List<View> dots; // 图片标题正文的那些点
	private int currentItem = 0; // 当前图片的索引号
	private ScheduledExecutorService scheduledExecutorService;
	// 切换当前显示的图片
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			viewPager.setCurrentItem(currentItem);// 切换当前显示的图片
		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.frg_call, null);
		initValues();
		initView();
		initListener();
		return view;
	}

	private void initValues() {
		adapter = new SearchListAdapter(getActivity(),view);
		mNotificationManager = (NotificationManager)getActivity().getSystemService(Service.NOTIFICATION_SERVICE);
		mNotification = new Notification();
		mNotification.vibrate = vib;
		
		imageResId = new int[] { R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e };

		imageViews = new ArrayList<ImageView>();

		// 初始化图片资源
		if (Dfine.mData != null) {
			for (int i = 0; i < Dfine.mData.getCall_banner().size(); i++) {
				ImageView imageView = new ImageView(getActivity());
				imageView.setTag(i);
				AsyncImageLoad(imageView, Dfine.mData.getCall_banner().get(i).getIco());
				imageView.setScaleType(ScaleType.CENTER_CROP);
				final int j = i;
				imageViews.add(imageView);
				imageView.setOnClickListener(new View.OnClickListener() {				
					public void onClick(View v) {
						Toast.makeText(getActivity(), Dfine.mData.getCall_banner().get(j).getUrl(), 1000).show();
						Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(Dfine.mData.getCall_banner().get(j).getUrl()));
						startActivity(intent);
					}
				});
			}
		}else {
			for (int i = 0; i < imageResId.length; i++) {
				ImageView imageView = new ImageView(getActivity());
				imageView.setImageResource(imageResId[i]);
				imageView.setScaleType(ScaleType.CENTER_CROP);
				imageViews.add(imageView);
			}
		}
	}

	private void initView() {
		lay_keyboard = (LinearLayout) view.findViewById(R.id.lay_keyboard);
		lay_ads = (FrameLayout) view.findViewById(R.id.lay_ads);
		iv_keyboard_down = (ImageView) view.findViewById(R.id.iv_keyboard_down);
		lay_bottom = (LinearLayout) view.findViewById(R.id.lay_frgCall_bottom);
		iv_keyboard_up = (ImageView)view.findViewById(R.id.iv_frgCall_showKeyBoard);
		iv_call = (ImageView)view.findViewById(R.id.iv_key_call);
		iv_del = (ImageView)view.findViewById(R.id.iv_key_del);
		tv_num = (TextView) view.findViewById(R.id.tv_num);
		btn_num0 = (Button) view.findViewById(R.id.btn_keyboard_0);
		btn_num1 = (Button) view.findViewById(R.id.btn_keyboard_1);
		btn_num2 = (Button) view.findViewById(R.id.btn_keyboard_2);
		btn_num3 = (Button) view.findViewById(R.id.btn_keyboard_3);
		btn_num4 = (Button) view.findViewById(R.id.btn_keyboard_4);
		btn_num5 = (Button) view.findViewById(R.id.btn_keyboard_5);
		btn_num6 = (Button) view.findViewById(R.id.btn_keyboard_6);
		btn_num7 = (Button) view.findViewById(R.id.btn_keyboard_7);
		btn_num8 = (Button) view.findViewById(R.id.btn_keyboard_8);
		btn_num9 = (Button) view.findViewById(R.id.btn_keyboard_9);
		btn_nums = (Button) view.findViewById(R.id.btn_keyboard_star);
		btn_numj = (Button) view.findViewById(R.id.btn_keyboard_j);
		lv_content = (ListView)view.findViewById(R.id.lv_frgCall_Content);
		lv_content.setAdapter(adapter);
		lay_keyboard.setVisibility(View.VISIBLE);
		lay_bottom.setVisibility(View.GONE);
		
		dots = new ArrayList<View>();
		dots.add(view.findViewById(R.id.v_dot0));
		dots.add(view.findViewById(R.id.v_dot1));
		dots.add(view.findViewById(R.id.v_dot2));
		dots.add(view.findViewById(R.id.v_dot3));
		dots.add(view.findViewById(R.id.v_dot4));
		viewPager = (ViewPager) view.findViewById(R.id.vp);
		viewPager.setAdapter(new MyAdapter());// 设置填充ViewPager页面的适配器
	}

	private void initListener() {
		

		// 设置一个监听器，当ViewPager中的页面改变时调用
		viewPager.setOnPageChangeListener(new MyPageChangeListener());
		viewPager.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
		
			}
		});
		
		
		
		iv_keyboard_up.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				animation = AnimationUtils.loadAnimation(getActivity(),R.anim.photo_dialog_in_anim);
				getActivity().findViewById(R.id.lay_aty_main_bottom).setVisibility(View.GONE);
				lay_keyboard.setAnimation(animation);
				lay_keyboard.setVisibility(View.VISIBLE);
				iv_keyboard_down.setImageResource(R.drawable.calldown);
				
			}
		});
		/**
		 * 列表滚动事件
		 */
		lv_content.setOnScrollListener(new AbsListView.OnScrollListener() {
			
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				
			}
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			     switch(scrollState){  
			        case 0://空闲状态  
			            break;        
			        case 2://滚动状态  
			            break;  
			        case 1://触摸后滚动  
			        	if (lay_keyboard.getVisibility() ==View.VISIBLE) {
							animation = AnimationUtils.loadAnimation(getActivity(),R.anim.photo_dialog_out_anim);
							lay_keyboard.setAnimation(animation);
							lay_keyboard.setVisibility(View.GONE);	
							getActivity().findViewById(R.id.lay_aty_main_bottom).setVisibility(View.VISIBLE);
						}

			            break;  
			        }
				
			}
		});
		/**
		 * 列表点击事件
		 */
		lv_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				mdl_Contact = (Mdl_Contact)adapter.getItem(arg2);
				tv_num.setText(mdl_Contact.getPhoneNum());
				nun_Buffer = new StringBuffer(mdl_Contact.getPhoneNum());
				name = mdl_Contact.getName();
				head = mdl_Contact.getHead();
				getActivity().findViewById(R.id.lay_aty_main_bottom).setVisibility(View.GONE);
				if(lay_keyboard.getVisibility() == View.GONE){
					animation = AnimationUtils.loadAnimation(getActivity(),R.anim.photo_dialog_in_anim);
					lay_keyboard.setAnimation(animation);
					lay_keyboard.setVisibility(View.VISIBLE);
				}
			}
		});
		iv_keyboard_down.setOnClickListener(this);
		iv_call.setOnClickListener(this);
		btn_num0.setOnClickListener(this);
		btn_num1.setOnClickListener(this);
		btn_num2.setOnClickListener(this);
		btn_num3.setOnClickListener(this);
		btn_num4.setOnClickListener(this);
		btn_num5.setOnClickListener(this);
		btn_num6.setOnClickListener(this);
		btn_num7.setOnClickListener(this);
		btn_num8.setOnClickListener(this);
		btn_num9.setOnClickListener(this);
		btn_nums.setOnClickListener(this);
		btn_numj.setOnClickListener(this);
		iv_del.setOnClickListener(this);
		
		/**
		 * 编辑框改变事件
		 */
		tv_num.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				//如果编辑框中有数字，而且不是提示字符
				if (s.length() >= 0 && !s.toString().equals(getString(R.string.call))) {
					adapter.getFilter().filter(s);
					lay_bottom.setVisibility(View.VISIBLE);
					getActivity().findViewById(R.id.lay_aty_main_bottom).setVisibility(View.GONE);
				} else {
					adapter.clear();
					lay_ads.setVisibility(View.VISIBLE);
					adapter.notifyDataSetChanged();
					getActivity().findViewById(R.id.lay_aty_main_bottom).setVisibility(View.VISIBLE);
					lay_bottom.setVisibility(View.GONE);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		

	}


	@Override
	public void onClick(View v) {
		mNotificationManager.notify(123, mNotification);
		switch (v.getId()) {
		case R.id.iv_keyboard_down:
				animation = AnimationUtils.loadAnimation(getActivity(),R.anim.photo_dialog_out_anim);
				getActivity().findViewById(R.id.lay_aty_main_bottom).setVisibility(View.VISIBLE);
				lay_keyboard.setAnimation(animation);
				lay_keyboard.setVisibility(View.GONE);
			break;
		case R.id.iv_key_call:
			if (tv_num.length()<11) {
				if ("".equals(Config.getCaCheLocalCode(getActivity()))) {
					//没有设置区号而且电话长度较短
					showToast(getActivity(), "打固话请加区号，打短号请用本机", Toast.LENGTH_LONG);
					return;
				}else {
					if (tv_num.length() < 7) {
					showToast(getActivity(), "请输入正确号码,打短号,请用本机", Toast.LENGTH_LONG);
					}
					nun_Buffer.insert(0, Config.getCaCheLocalCode(getActivity()));
				}
			}
			intent = new Intent(getActivity(),Aty_Calling.class);
			if (head != null) {
				intent.putExtra(Config.KEY_HEAD,head);
			}else {
				intent.putExtra(Config.KEY_HEAD, BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.img_people));
			}
			if (name != null) {
				intent.putExtra(Config.KEY_NAME, name);
			}else {
				intent.putExtra(Config.KEY_NAME, "未知");
			}
			intent.putExtra(Config.KEY_NUM, nun_Buffer.toString());
			startActivity(intent);
			break;
		case R.id.btn_keyboard_0:
			nun_Buffer.append("0");
			tv_num.setText(nun_Buffer.toString());
			break;
		case R.id.btn_keyboard_1:
			nun_Buffer.append("1");
			tv_num.setText(nun_Buffer.toString());
			break;
		case R.id.btn_keyboard_2:
			nun_Buffer.append("2");
			tv_num.setText(nun_Buffer.toString());
			break;
		case R.id.btn_keyboard_3:
			nun_Buffer.append("3");
			tv_num.setText(nun_Buffer.toString());
			break;
		case R.id.btn_keyboard_4:
			nun_Buffer.append("4");
			tv_num.setText(nun_Buffer.toString());
			break;
		case R.id.btn_keyboard_5:
			nun_Buffer.append("5");
			tv_num.setText(nun_Buffer.toString());
			break;
		case R.id.btn_keyboard_6:
			nun_Buffer.append("6");
			tv_num.setText(nun_Buffer.toString());
			break;
		case R.id.btn_keyboard_7:
			nun_Buffer.append("7");
			tv_num.setText(nun_Buffer.toString());
			break;
		case R.id.btn_keyboard_8:
			nun_Buffer.append("8");
			tv_num.setText(nun_Buffer.toString());
			break;
		case R.id.btn_keyboard_9:
			nun_Buffer.append("9");
			tv_num.setText(nun_Buffer.toString());
			break;
		case R.id.btn_keyboard_star:
			nun_Buffer.append("*");
			tv_num.setText(nun_Buffer.toString());
			break;
		case R.id.btn_keyboard_j:
			nun_Buffer.append("#");
			tv_num.setText(nun_Buffer.toString());
			break;
		case R.id.iv_key_del:
			if (nun_Buffer.length() > 0) {
				nun_Buffer.deleteCharAt(nun_Buffer.length()-1);
			}
			if (nun_Buffer.length() == 0) {
				tv_num.setText(R.string.call);
			}else {
				tv_num.setText(nun_Buffer.toString());
			}
			break;

		default:
			break;
		}
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
	
	/**
	 * 换行切换任务
	 * 
	 * @author Administrator
	 * 
	 */
	private class ScrollTask implements Runnable {

		public void run() {
			synchronized (viewPager) {
				System.out.println("currentItem: " + currentItem);
				currentItem = (currentItem + 1) % imageViews.size();
				handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
			}
		}

	}
	

	/**
	 * 当ViewPager中页面的状态发生改变时调用
	 * 
	 * @author Administrator
	 * 
	 */
	private class MyPageChangeListener implements OnPageChangeListener {
		private int oldPosition = 0;

		/**
		 * This method will be invoked when a new page becomes selected.
		 * position: Position index of the new selected page.
		 */
		public void onPageSelected(int position) {
			currentItem = position;
			dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
			dots.get(position).setBackgroundResource(R.drawable.dot_focused);
			oldPosition = position;
		}

		public void onPageScrollStateChanged(int arg0) {

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}
	}
	
	
	/**
	 * 填充ViewPager页面的适配器
	 * 
	 * @author Administrator
	 * 
	 */
	private class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return imageResId.length;
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(imageViews.get(arg1));
			return imageViews.get(arg1);
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView((View) arg2);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {

		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {

		}

		@Override
		public void finishUpdate(View arg0) {

		}
	}
	/**
	 * 异步加载图片
	 * @param ivHead 要加载的ImageView
	 * @param path 图片的地址
	 */
	
	private void AsyncImageLoad(ImageView ivHead, String path) {
		AsyncImageTask asyncImageTask = new AsyncImageTask(ivHead);
		asyncImageTask.execute(path);
	}
	private class AsyncImageTask extends AsyncTask<String, Integer, Uri>{
		private ImageView imageView;
		public AsyncImageTask(ImageView imageView) {
			this.imageView = imageView;
		}
		@Override
		protected Uri doInBackground(String... arg0) {	//运行在子线程中
			Uri uri = NetGetImage.getImage(arg0[0],Dfine.cachePath);
			return uri;
		}
		@Override
		protected void onPostExecute(Uri result) {		//运行在主线程中
			if (result != null && imageView != null) {
				imageView.setImageURI(result);
			}
		}
		
	}
	
	
	@Override
	public void onStart() {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		// 当Activity显示出来后，每两秒钟切换一次图片显示
		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 4, TimeUnit.SECONDS);
		super.onStart();
	}

	@Override
	public void onStop() {
		// 当Activity不可见的时候停止切换
		scheduledExecutorService.shutdown();
		super.onStop();
	}
	
}

