package com.shanghui.call.Adp;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.provider.CallLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shanghui.call.Config;
import com.shanghui.call.R;
import com.shanghui.call.Aty.Aty_ContentInfo;
import com.shanghui.call.Mdl.Mdl_CallLog;

public class Adp_CallLog extends BaseAdapter {
	private List<Mdl_CallLog> list = new ArrayList<Mdl_CallLog>();
	private Context context;
	private Mdl_CallLog mdl_CallLog;
	private Intent intent;

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
	public View getView(final int position, View convertView, ViewGroup parent) {
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
							.findViewById(R.id.tv_cellCallLog_time),
					(ImageView) convertView
							.findViewById(R.id.iv_cellCallLog_more));
			convertView.setTag(viewHolder);
		}
		viewHolder = (ViewHolder) convertView.getTag();
		viewHolder.getTv_name().setText(list.get(position).getUserName());
		viewHolder.getTv_num().setText(list.get(position).getPhoneNum());
		viewHolder.getTv_date().setText(list.get(position).getCallDate());
		viewHolder.getIv_more().setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				intent = new Intent(getContext(),Aty_ContentInfo.class);
				intent.putExtra(Config.KEY_HEAD, list.get(position).getHead());
				intent.putExtra(Config.KEY_NAME, list.get(position).getUserName());
				intent.putExtra(Config.KEY_NUM, list.get(position).getPhoneNum());
				intent.putExtra(Config.KEY_ID, Config.NO_EXIST);
				getContext().startActivity(intent);				
			}
		});
		switch (list.get(position).getCallType()) {
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
			viewHolder.getIv_type().setImageBitmap(null);
			break;
		}
		return convertView;
	}

	private class ViewHolder {
		private ImageView iv_type;
		private TextView tv_name;
		private TextView tv_num;
		private TextView tv_date;
		private ImageView iv_more;

		public ViewHolder(ImageView iv_type, TextView tv_name, TextView tv_num,
				TextView tv_date,ImageView iv_more) {
			super();
			this.iv_type = iv_type;
			this.tv_name = tv_name;
			this.tv_num = tv_num;
			this.tv_date = tv_date;
			this.iv_more = iv_more;
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
		public ImageView getIv_more() {
			return iv_more;
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
