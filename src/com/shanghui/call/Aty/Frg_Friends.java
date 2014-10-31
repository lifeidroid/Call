package com.shanghui.call.Aty;

import com.shanghui.call.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
/**
 * 通讯录主界面
 * @author shanghui
 *
 */
public class Frg_Friends extends Fragment {
	private View view;
	private Frg_Friends_Friends_List friends_List;
	private Frg_Friends_Mail_list fMail_list;
	private FragmentManager fManager;
	private FragmentTransaction fTransaction;
	private RadioGroup rg_selector;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initValues();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.frg_friends, null);
		initViews();
		initListeners();
		return view;
	}

	private void initValues() {
		fManager = getActivity().getSupportFragmentManager();
	}

	private void initViews() {
		rg_selector = (RadioGroup) view.findViewById(R.id.rg_frgFriends);
		fTransaction = fManager.beginTransaction();
		fMail_list = new Frg_Friends_Mail_list();
		fTransaction.replace(R.id.lay_frgFriends_container, fMail_list);
		fTransaction.commit();
	}

	private void initListeners() {
		rg_selector
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						fTransaction = fManager.beginTransaction();
						switch (checkedId) {
						case R.id.rb_frgFriend_mailList:
							fMail_list = new Frg_Friends_Mail_list();
							fTransaction.replace(R.id.lay_frgFriends_container, fMail_list);
							break;
						case R.id.rb_frgFriend_friendList:
							friends_List = new Frg_Friends_Friends_List();
							fTransaction.replace(R.id.lay_frgFriends_container, friends_List);
							break;

						default:
							break;
						}
						fTransaction.commit();

					}
				});
	}
}
