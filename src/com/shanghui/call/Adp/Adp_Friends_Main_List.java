package com.shanghui.call.Adp;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.shanghui.call.R;
import com.shanghui.call.Mdl.Dfine;
import com.shanghui.call.Mdl.Mdl_Contact;
import com.shanghui.call.Tools.RoundImageView;

public class Adp_Friends_Main_List extends BaseAdapter implements Filterable{
	private List<Mdl_Contact> DataList = new ArrayList<Mdl_Contact>();
	private Context mContext;
	private boolean showTitle;

	public Adp_Friends_Main_List(Context mContext) {
		this.mContext = mContext;
	}

	/**
	 * 当ListView数据发生变化时,调用此方法来更新ListView
	 * 
	 * @param list
	 */
	
	public void addAll(List<Mdl_Contact> list,boolean showTitle){
		DataList.clear();
		DataList.addAll(list);
		notifyDataSetChanged();
		this.showTitle = true;
	}

	public int getCount() {
		return this.DataList.size();
	}

	public Object getItem(int position) {
		return DataList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		final Mdl_Contact mContent = DataList.get(position);
		if (view == null) {
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(mContext).inflate(R.layout.cell_friendsmail_list, null);
			viewHolder.tvTitle = (TextView) view.findViewById(R.id.tv_cellFriendsMail_name);
			viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
			viewHolder.tv_num = (TextView)view.findViewById(R.id.tv_cellFriendsMail_num);
			viewHolder.ivHead = (RoundImageView)view.findViewById(R.id.iv_cellFriendsMail_head);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}

		// 根据position获取分类的首字母的Char ascii值
		int section = getSectionForPosition(position);

		// 如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
		if (showTitle) {
			if (position == getPositionForSection(section)) {
				viewHolder.tvLetter.setVisibility(View.VISIBLE);
				viewHolder.tvLetter.setText(mContent.getFirstNamePy());
			} else {
				viewHolder.tvLetter.setVisibility(View.GONE);
			}
		}else {
			viewHolder.tvLetter.setVisibility(View.GONE);
		}
		viewHolder.tvTitle.setText(this.DataList.get(position).getName());
		viewHolder.tv_num.setText(this.DataList.get(position).getPhoneNum());		
		if (DataList.get(position).getHead() != null) {
			viewHolder.ivHead.setImageBitmap(this.DataList.get(position).getHead());
		}

		return view;

	}

	final static class ViewHolder {
		TextView tvLetter;
		TextView tvTitle;
		RoundImageView ivHead;
		TextView tv_num;
	}

	/**
	 * 根据ListView的当前位置获取分类的首字母的Char ascii值
	 */
	public int getSectionForPosition(int position) {
		return DataList.get(position).getFirstNamePy().charAt(0);
	}

	/**
	 * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
	 */
	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = DataList.get(i).getFirstNamePy();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}
		return -1;
	}
	
	@Override
	public Filter getFilter() {
		Filter filter = new Filter() {
			
			//执行过滤方法 
			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				String input = constraint.toString();
				FilterResults results = new FilterResults();
				ArrayList<Mdl_Contact> list = new ArrayList<Mdl_Contact>();
				boolean py = false;
				for(Mdl_Contact item : Dfine.contacts){
					item.input = input;
					if((item.getName().equals(input) || item.getName().contains(input)) && !list.contains(item)){
						list.add(item);
					}else if(item.getLastNamePy().contains(input) && !list.contains(item)){
						list.add(item);
					}else if(item.getNamePy().contains(input) && !list.contains(item)){
						list.add(item);
					}else if(item.getLastNameToNumber().equals(input) && !list.contains(item)){
						item.index = 0;
						list.add(item);
					}else if(item.getLastNameToNumber().contains(input) && !list.contains(item)){
						item.index = 1;
						list.add(item);
					}else if(item.getNameToNumber().contains(input) && !list.contains(item)){
						char[] chars = item.getLastNameToNumber().toCharArray();
						for(char c : chars){
							if(c == input.toCharArray()[0] && !list.contains(item)){
								item.index = 2;
								item.matchIndex = item.getNameToNumber().indexOf(input);
								list.add(item);
								py = true;
							}
						}
					}else if(item.getPhoneNum().contains(input) && !list.contains(item)){
						item.index = 3;
						list.add(item);
					}
				}
				//按搜索顺序排序
				for (int i = 0; i < list.size(); ++i) {
					for (int j = 0; j < list.size() - i - 1; ++j) {
						if (list.get(j).index > list.get(j + 1).index) {
							Mdl_Contact temp = list.get(j + 1);
							list.set(j + 1 , list.get(j));
							list.set(j,temp);
						}
					}
				}
				//全拼按匹配顺序排序
				if(py){
					for (int i = 0; i < list.size(); ++i) {
						for (int j = 0; j < list.size() - i - 1; ++j) {
							if(list.get(j).index == 2 && list.get(j + 1).index == 2){
								if (list.get(j).matchIndex > list.get(j + 1).matchIndex) {
									Mdl_Contact temp = list.get(j + 1);
									list.set(j + 1 , list.get(j));
									list.set(j,temp);
								}
							}
						}
					}
				}
				
				results.count = list.size();
				results.values = list;
				return results;
			}
			//筛选过后得到的数据同时更新Adapter更新 
			@Override
			protected void publishResults(CharSequence constraint, FilterResults results) {
				//Dfine.searchUser.clear();
				showTitle = false;
				DataList.clear();
				ArrayList<Mdl_Contact> list = (ArrayList<Mdl_Contact>)results.values;
				if(list != null && list.size() > 0){
					DataList.addAll(list);
					notifyDataSetChanged();
				}else{
					notifyDataSetInvalidated();
				}
			}
	
		};
		return filter;
	}

	

}