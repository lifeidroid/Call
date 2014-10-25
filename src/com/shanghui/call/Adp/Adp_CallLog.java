package com.shanghui.call.Adp;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.provider.CallLog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.shanghui.call.R;
import com.shanghui.call.Mdl.Mdl_CallLog;

public class Adp_CallLog extends BaseAdapter {
	private List<Mdl_CallLog> list = new ArrayList<Mdl_CallLog>();
	private Context context;
	private Mdl_CallLog mdl_CallLog;

	public Adp_CallLog(Context context) {
		this.context = context;
	}

	public Context getContext() {
		return context;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.cell_calllog, null);
			viewHolder = new ViewHolder(
					(ImageView) convertView
							.findViewById(R.id.iv_cellCallLog_type),
					(TextView) convertView
							.findViewById(R.id.tv_cellCallLog_name),
					(TextView) convertView
							.findViewById(R.id.tv_cellCallLog_num),
					(TextView) convertView
							.findViewById(R.id.tv_cellCallLog_time));
			convertView.setTag(viewHolder);
		}
		viewHolder = (ViewHolder) convertView.getTag();
		mdl_CallLog = list.get(position);
		if (TextUtils.isEmpty(mdl_CallLog.getUserName())) {
			viewHolder.getTv_name().setText("未知");
		}else {
			viewHolder.getTv_name().setText(mdl_CallLog.getUserName());
		}
		viewHolder.getTv_num().setText(mdl_CallLog.getPhoneNum());
		viewHolder.getTv_date().setText(mdl_CallLog.getCallDate());
		switch (mdl_CallLog.getCallType()) {
		case CallLog.Calls.INCOMING_TYPE://来电
			viewHolder.getIv_type().setImageResource(R.drawable.call_coming);
			break;
		case CallLog.Calls.OUTGOING_TYPE://已拨
			viewHolder.getIv_type().setImageResource(R.drawable.call_ougoing);
			break;
		case CallLog.Calls.MISSED_TYPE://未接
			viewHolder.getIv_type().setImageResource(R.drawable.call_missing);
			break;
		default:
			break;
		}
		return convertView;
	}

	private class ViewHolder {
		private ImageView iv_type;
		private TextView tv_name;
		private TextView tv_num;
		private TextView tv_date;

		public ViewHolder(ImageView iv_type, TextView tv_name, TextView tv_num,
				TextView tv_date) {
			super();
			this.iv_type = iv_type;
			this.tv_name = tv_name;
			this.tv_num = tv_num;
			this.tv_date = tv_date;
		}

		public ImageView getIv_type() {
			return iv_type;
		}

		public TextView getTv_date() {
			return tv_date;
		}

		public TextView getTv_name() {
			return tv_name;
		}

		public TextView getTv_num() {
			return tv_num;
		}
	}

	public void addAll(List<Mdl_CallLog> list) {
		this.list.addAll(list);
		notifyDataSetChanged();
	}

	public void clear() {
		this.list.clear();
		notifyDataSetChanged();
	}
}
