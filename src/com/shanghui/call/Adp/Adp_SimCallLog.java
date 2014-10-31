package com.shanghui.call.Adp;

import java.util.ArrayList;
import java.util.List;

import com.shanghui.call.R;
import com.shanghui.call.Mdl.Mdl_CallLog;

import android.content.Context;
import android.provider.CallLog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adp_SimCallLog extends BaseAdapter {
	private List<Mdl_CallLog> mList = new ArrayList<Mdl_CallLog>();
	private Context context;
	public Adp_SimCallLog(Context context) {
		this.context = context;
	}
	public Context getContext() {
		return context;
	}
	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.cell_simcalllog, null);
			viewHolder = new ViewHolder((TextView)convertView.findViewById(R.id.tv_cellSimCallLog_time),
					(TextView) convertView.findViewById(R.id.tv_cellSimCallLog_num),
					(ImageView) convertView.findViewById(R.id.tv_cellSimCallLog_type),
					(TextView) convertView.findViewById(R.id.tv_cellSimCallLog_during));
			convertView.setTag(viewHolder);
		}
		viewHolder = (ViewHolder)convertView.getTag();
		viewHolder.getTv_time().setText(mList.get(position).getCallDate());
		System.out.println("------>getCallDate"+mList.get(position).getCallDate());
		viewHolder.getTv_num().setText(mList.get(position).getPhoneNum());
		switch (mList.get(position).getCallType()) {
		case CallLog.Calls.INCOMING_TYPE://来电
			viewHolder.getIv_type().setImageResource(R.drawable.img_arror_in);
			if (TextUtils.isEmpty(mList.get(position).getDuring())) {
				viewHolder.getTv_during().setText("未接通");
			}else {
				viewHolder.getTv_during().setText("呼入"+mList.get(position).getDuring());
			}
			break;
		case CallLog.Calls.OUTGOING_TYPE://已拨
			viewHolder.getIv_type().setImageResource(R.drawable.img_arror_out);
			if (TextUtils.isEmpty(mList.get(position).getDuring())) {
				viewHolder.getTv_during().setText("未接通");
			}else {
				viewHolder.getTv_during().setText("呼出"+mList.get(position).getDuring());
			}
			break;
		case CallLog.Calls.MISSED_TYPE://未接
			viewHolder.getIv_type().setImageResource(R.drawable.img_arror_in);
			viewHolder.getTv_during().setText("未接通");
			break;
		default:
			break;
		}
		return convertView;
	}
	private class ViewHolder{
		private TextView tv_time;
		private TextView tv_num;
		private ImageView iv_type;
		private TextView tv_during;
		public ViewHolder(TextView tv_time,TextView tv_num,ImageView iv_type,TextView tv_during) {
			this.tv_time = tv_time;
			this.tv_num = tv_num;
			this.tv_during = tv_during;
			this.iv_type = iv_type;
		}
		public ImageView getIv_type() {
			return iv_type;
		}
		public TextView getTv_during() {
			return tv_during;
		}
		public TextView getTv_num() {
			return tv_num;
		}
		public TextView getTv_time() {
			return tv_time;
		}
	}
	public void addAll(List<Mdl_CallLog> mList){
		this.mList.addAll(mList);
		notifyDataSetChanged();
	}
	public void clear(){
		this.mList.clear();
		notifyDataSetChanged();
	}

}
