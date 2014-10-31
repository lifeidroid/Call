package com.shanghui.call.Aty;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.Photo;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.Toast;

import com.shanghui.call.Config;
import com.shanghui.call.R;
import com.shanghui.call.Adp.Adp_Friends_Main_List;
import com.shanghui.call.Mdl.Mdl_Contact;
import com.shanghui.call.Tools.CharacterParser;
import com.shanghui.call.Tools.ClearEditText;
import com.shanghui.call.Tools.GroupMemberBean;
import com.shanghui.call.Tools.PinyinComparator;
import com.shanghui.call.Tools.SideBar;
import com.shanghui.call.Tools.SideBar.OnTouchingLetterChangedListener;
/**
 * 联系人模块
 * @author shanghui
 *
 */
public class Frg_Friends_Mail_list extends Fragment implements SectionIndexer {
	private ListView sortListView;
	private SideBar sideBar;
	private TextView dialog;
	private Adp_Friends_Main_List adapter;
	private ClearEditText mClearEditText;
	private View view;
	private View dlgView;
	private Dialog dlgCall;
	private LinearLayout titleLayout;
	private TextView title;
	private TextView tvNofriends;
	private Button btn_DlgwifiCall;
	private Button btn_DlgshanghuiCall;
	private Button btn_Dlgcancel;
	private GroupMemberBean mGroupMemberBean;
	private static Toast mToast;
	private static View toastView;
	private static TextView tv_toastText;
	private boolean showDialog = false;
	private List<Mdl_Contact> contactList;
	private App_Main app_Main;
	private Intent intent;
	/**
	 * 上次第一个可见元素，用于滚动时记录标识。
	 */
	private int lastFirstVisibleItem = -1;
	/**
	 * 汉字转换成拼音的类
	 */
	private CharacterParser characterParser;
	private List<GroupMemberBean> SourceDateList;

	/**
	 * 根据拼音来排列ListView里面的数据类
	 */
	private PinyinComparator pinyinComparator;

	
	
	
	/** 获取库Phon表字段 **/
	private static final String[] PHONES_PROJECTION = new String[] {
			Phone.DISPLAY_NAME, Phone.NUMBER, Photo.PHOTO_ID, Phone.CONTACT_ID };

	/** 联系人显示名称 **/
	private static final int PHONES_DISPLAY_NAME_INDEX = 0;

	/** 电话号码 **/
	private static final int PHONES_NUMBER_INDEX = 1;

	/** 头像ID **/
	private static final int PHONES_PHOTO_ID_INDEX = 2;

	/** 联系人的ID **/
	private static final int PHONES_CONTACT_ID_INDEX = 3;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		initValues();
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.activity_add_friends, null);
		initViews();
		return view;
	}
	private void initValues(){
		app_Main = (App_Main) getActivity().getApplication();
		contactList = app_Main.getContactList();
		//getPhoneContacts();
	}
	private void initViews() {
		sortListView = (ListView) view.findViewById(R.id.country_lvcountry);
		titleLayout = (LinearLayout) view.findViewById(R.id.title_layout);
		//顶部字母显示
		title = (TextView) view.findViewById(R.id.title_layout_catalog);
		//没有匹配的联系人
		tvNofriends = (TextView) view.findViewById(R.id.title_layout_no_friends);
		// 实例化汉字转拼音类
		characterParser = CharacterParser.getInstance();

		pinyinComparator = new PinyinComparator();

		sideBar = (SideBar) view.findViewById(R.id.sidrbar);
		dialog = (TextView) view.findViewById(R.id.dialog);
		sideBar.setTextView(dialog);

		// 设置右侧触摸监听
		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {

			@Override
			public void onTouchingLetterChanged(String s) {
				// 该字母首次出现的位置
				int position = adapter.getPositionForSection(s.charAt(0));
				if (position != -1) {
					sortListView.setSelection(position);
				}

			}
		});

	
		sortListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 这里要利用adapter.getItem(position)来获取当前position所对应的对象
					mGroupMemberBean = (GroupMemberBean)adapter.getItem(position);
					intent = new Intent(getActivity(),Aty_ContentInfo.class);
					intent.putExtra(Config.KEY_NAME,mGroupMemberBean.getContact().getName());
					intent.putExtra(Config.KEY_NUM, mGroupMemberBean.getContact().getPhoneNum());
					intent.putExtra(Config.KEY_HEAD, mGroupMemberBean.getContact().getHead());
					intent.putExtra(Config.KEY_ID, mGroupMemberBean.getContact().getPeopleId());
					startActivity(intent);
			}
		});
		sortListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				mGroupMemberBean = (GroupMemberBean)adapter.getItem(position);
				showDialog(mGroupMemberBean.getContact().getName(),
						mGroupMemberBean.getContact().getPhoneNum(),
						mGroupMemberBean.getContact().getHead());
				return false;
			}
		});
		//获取联系人数据
		SourceDateList = filledData(contactList);

		// 根据a-z进行排序源数据
		Collections.sort(SourceDateList, pinyinComparator);
		adapter = new Adp_Friends_Main_List(getActivity(), SourceDateList);
		sortListView.setAdapter(adapter);
		sortListView.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
					int section = getSectionForPosition(firstVisibleItem);//根据ListView的当前位置获取分类的首字母的Char ascii值
					int nextSection = getSectionForPosition(firstVisibleItem + 1);
					int nextSecPosition = getPositionForSection(+nextSection);//根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
					if (nextSecPosition == firstVisibleItem + 1) {
						View childView = view.getChildAt(0);
						if (childView != null) {
							int titleHeight = titleLayout.getHeight();
							int bottom = childView.getBottom();
							MarginLayoutParams params = (MarginLayoutParams) titleLayout.getLayoutParams();
							if (bottom < titleHeight) {
								float pushedDistance = bottom - titleHeight;
								params.topMargin = (int) pushedDistance;
								titleLayout.setLayoutParams(params);
							} else {
								if (params.topMargin != 0) {
									params.topMargin = 0;
									titleLayout.setLayoutParams(params);
								}
							}
						}
					}
				if (firstVisibleItem != lastFirstVisibleItem) {//如果列表不为空，设置顶部字母
					MarginLayoutParams params = (MarginLayoutParams) titleLayout.getLayoutParams();
					params.topMargin = 0;
					titleLayout.setLayoutParams(params);
					title.setText(SourceDateList.get(getPositionForSection(section)).getSortLetters());
				}
				if (adapter.getCount() != 0) {
					mGroupMemberBean = (GroupMemberBean)adapter.getItem(firstVisibleItem);
					if (showDialog) {
						dialog.setVisibility(View.VISIBLE);
						dialog.setText(mGroupMemberBean.getContact().getName().substring(0,1));
					}
				}
				lastFirstVisibleItem = firstVisibleItem;
			}
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			     switch(scrollState){  
			        case OnScrollListener.SCROLL_STATE_IDLE://空闲状态  
			        	System.out.println("---->空闲状态");
			        	showDialog = false;
			        	dialog.setVisibility(View.GONE);
			            break;        
			        case OnScrollListener.SCROLL_STATE_FLING://滚动状态  
			        	System.out.println("---->滚动状态");
			        	showDialog = true;
			            break;  
			        case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL://触摸后滚动  
			        	System.out.println("---->触摸后滚动");
			        	showDialog = true;
			            break;  
			        }
			}
		});
		mClearEditText = (ClearEditText) view.findViewById(R.id.filter_edit);
		
		// 根据输入框输入值的改变来过滤搜索
		mClearEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// 这个时候不需要挤压效果 就把他隐藏掉
				titleLayout.setVisibility(View.GONE);
				// 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
				filterData(s.toString());
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

	/**
	 * 为ListView填充数据
	 * 
	 * @param date
	 * @return
	 */
	private List<GroupMemberBean> filledData(List<Mdl_Contact> date) {
		List<GroupMemberBean> mSortList = new ArrayList<GroupMemberBean>();

		for (int i = 0; i < date.size(); i++) {
			GroupMemberBean sortModel = new GroupMemberBean();
			sortModel.setContact(date.get(i));
			// 汉字转换成拼音
			String pinyin = characterParser.getSelling(date.get(i).getName());
			String sortString = pinyin.substring(0, 1).toUpperCase();

			// 正则表达式，判断首字母是否是英文字母
			if (sortString.matches("[A-Z]")) {
				sortModel.setSortLetters(sortString.toUpperCase());
			} else {
				sortModel.setSortLetters("#");
			}

			mSortList.add(sortModel);
		}
		return mSortList;

	}

	/**
	 * 根据输入框中的值来过滤数据并更新ListView
	 * 
	 * @param filterStr
	 */
	private void filterData(String filterStr) {
		List<GroupMemberBean> filterDateList = new ArrayList<GroupMemberBean>();

		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = SourceDateList;
			tvNofriends.setVisibility(View.GONE);
		} else {
			filterDateList.clear();
			for (GroupMemberBean sortModel : SourceDateList) {
				String name = sortModel.getContact().getName();
				if (name.indexOf(filterStr.toString()) != -1|| characterParser.getSelling(name).startsWith(filterStr.toString())) {
					filterDateList.add(sortModel);
				}
			}
		}

		// 根据a-z进行排序
		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
		if (filterDateList.size() == 0) {
			tvNofriends.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public Object[] getSections() {
		return null;
	}

	/**
	 * 根据ListView的当前位置获取分类的首字母的Char ascii值
	 */
	public int getSectionForPosition(int position) {
		return SourceDateList.get(position).getSortLetters().charAt(0);
	}

	/**
	 * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
	 */
	public int getPositionForSection(int section) {
		for (int i = 0; i < SourceDateList.size(); i++) {
			String sortStr = SourceDateList.get(i).getSortLetters();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}
		return -1;
	}
	
	
	/** 得到手机通讯录联系人信息 **/
	/*	private void getPhoneContacts() {
		ContentResolver resolver = getActivity().getContentResolver();

		// 获取手机联系人
		Cursor phoneCursor = resolver.query(Phone.CONTENT_URI,
				PHONES_PROJECTION, null, null, null);

		if (phoneCursor != null) {
			while (phoneCursor.moveToNext()) {

				// 得到手机号码
				String phoneNumber = phoneCursor.getString(PHONES_NUMBER_INDEX);
				// 当手机号码为空的或者为空字段 跳过当前循环
				if (TextUtils.isEmpty(phoneNumber))
					continue;

				// 得到联系人名称
				String contactName = phoneCursor.getString(PHONES_DISPLAY_NAME_INDEX);

				// 得到联系人ID
				Long contactid = phoneCursor.getLong(PHONES_CONTACT_ID_INDEX);

				// 得到联系人头像ID
				Long photoid = phoneCursor.getLong(PHONES_PHOTO_ID_INDEX);

				// 得到联系人头像Bitamp
				Bitmap contactPhoto = null;

				// photoid 大于0 表示联系人有头像 如果没有给此人设置头像则给他一个默认的
				if (photoid > 0) {
					Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactid);
					InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(resolver, uri);
					contactPhoto = BitmapFactory.decodeStream(input);
				} else {
					contactPhoto = BitmapFactory.decodeResource(getResources(),R.drawable.img_user);
				}
				contactList.add(new Mdl_Contact(contactName, phoneNumber, contactPhoto));
			}

			phoneCursor.close();
		}
	}*/
	
	private void showDialog(String name,String num,Bitmap head){
		dlgView = getActivity().getLayoutInflater().inflate(R.layout.dlg_call, null);
		dlgCall = new Dialog(getActivity(), R.style.transparentFrameWindowStyle);
		dlgCall.setContentView(dlgView, new LayoutParams(
		LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		Window window = dlgCall.getWindow();// 设置显示动画
		window.setWindowAnimations(R.style.main_menu_animstyle);
		WindowManager.LayoutParams wl = window.getAttributes();
		wl.x = 0;
		wl.y = getActivity().getWindowManager().getDefaultDisplay().getHeight();// 以下这两句是为了保证按钮可以水平满屏
		wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
		wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
		// 设置显示位置
		dlgCall.onWindowAttributesChanged(wl);
		// 设置点击外围解散
		dlgCall.setCanceledOnTouchOutside(true);
		btn_DlgshanghuiCall = (Button)dlgView.findViewById(R.id.btn_dlg_shanghui_call);
		btn_DlgwifiCall = (Button)dlgView.findViewById(R.id.btn_dlg_call_wifi_call);
		btn_Dlgcancel = (Button)dlgView.findViewById(R.id.btn_dlg_call_cancel);
		dialogInitLitener(name,num,head);
		dlgCall.show();
	}
	private void dialogInitLitener(final String name,final String num,final Bitmap head){
		btn_Dlgcancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dlgCall.dismiss();
				
			}
		});
		btn_DlgshanghuiCall.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent(getActivity(),Aty_Calling.class);
				intent.putExtra(Config.KEY_NAME,name);
				intent.putExtra(Config.KEY_NUM, num);
				intent.putExtra(Config.KEY_HEAD,head);
				startActivity(intent);
				dlgCall.dismiss();
				
			}
		});
		btn_DlgwifiCall.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		//Toast.makeText(getActivity(), text, duration)
	}
	
	public static void showToast(Context context, String text, int duration) {		
		if (tv_toastText == null) {
			toastView = LayoutInflater.from(context).inflate(R.layout.toast_view,null);
			mToast = new Toast(context);
			tv_toastText = (TextView)toastView.findViewById(R.id.tv_toastView_Text);
			mToast.setGravity(Gravity.CENTER, 0, 0);
			mToast.setView(toastView);
			tv_toastText.setText(text);
			mToast.setDuration(duration);
		} else {
			tv_toastText.setText(text);
			mToast.setDuration(duration);
		}

		mToast.show();
	}
}
