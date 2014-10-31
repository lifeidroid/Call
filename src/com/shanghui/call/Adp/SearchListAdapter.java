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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.shanghui.call.R;
import com.shanghui.call.Mdl.Dfine;
import com.shanghui.call.Mdl.Mdl_Contact;
import com.shanghui.call.Tools.Util;

/**
 *类名：SearchListAdapter
 *功能：继承BaseAdapter自定义Adapter
 *实现Filterable（过滤器）来实现联系人智能搜索
 * @author liumengqiang
 *2013-3-15
 */ 
public class SearchListAdapter extends BaseAdapter implements Filterable {
	private List<Mdl_Contact> dataList = new ArrayList<Mdl_Contact>();
	private Context mContext ;
	public SearchListAdapter(Context mC){
		mContext = mC;
	}
	
	@Override
	public int getCount() {
		return dataList.size();
	}

	@Override
	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return dataList.get(index);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int index, View view, ViewGroup parent) {
		view = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
		HolderView hv = new HolderView();
		hv.nameView = (TextView)view.findViewById(R.id.contact_name);
		hv.bodyView = (TextView)view.findViewById(R.id.contact_body);
		hv.phoneView = (TextView)view.findViewById(R.id.contact_phone);
		
		Mdl_Contact item = (Mdl_Contact)getItem(index);
		view.setTag(item.getPhoneNum());
		hv.nameView.setText(item.getName());		
		switch(item.index){
		case 0:
		case 1:{
			hv.phoneView.setText(item.getPhoneNum());
			hv.bodyView.setText(Util.formatHtml(item.getLastNamePy(), item.getNamePy(),item.input, item.getLastNameToNumber(), item.index));
			break;
		}
		case 2:{
			hv.phoneView.setText(item.getPhoneNum()); 
			hv.bodyView.setText(Util.formatHtml(item.getNamePy(), null,item.input, item.getNameToNumber(), item.index));
			break;
		} 
		case 3:{
			hv.phoneView.setText(Util.formatHtml(item.getPhoneNum(),null,item.input,null,item.index));
			hv.bodyView.setText(item.getNamePy());
			break;
		}
		default :{
			hv.phoneView.setText(item.getPhoneNum());
			hv.bodyView.setText(item.getNamePy());
			break;
		}
		}
		
		return view;
	}

	/**
	 *类名：HolderView
	 *功能： 将 联系人名字，全拼，电话封装到HolderView
	 * @author liumengqiang
	 *2013-3-15
	 */ 
	class HolderView{
		public TextView nameView;
		public TextView bodyView ;
		public TextView phoneView ;
		public ImageView headView;
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
				dataList.clear();
				ArrayList<Mdl_Contact> list = (ArrayList<Mdl_Contact>)results.values;
				if(list != null && list.size() > 0){
					dataList.addAll(list);
					notifyDataSetChanged();
				}else{
					notifyDataSetInvalidated();
				}
			}
	
		};
		return filter;
	}
	public void addAll(List<Mdl_Contact> dataList){
		this.dataList.addAll(dataList);
		notifyDataSetChanged();
	}
	public void clear(){
		this.dataList.clear();
		notifyDataSetChanged();
	}

}
