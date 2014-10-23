package com.shanghui.call.Aty;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shanghui.call.R;
public class Frg_Call extends Fragment {
	private View view;
@Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
}
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.frg_call, null);
		return view;
	}
}
