package org.gr.wocandroid.assist;

import java.util.List;

import org.gr.wocandroid.activity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MenuAdapter extends BaseAdapter {
	private Context  context;
	private List<String> list;
	

	public MenuAdapter(Context context, List<String> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup viewGroup) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(R.layout.listitem_menu, null);
			holder=new ViewHolder();
			convertView.setTag(holder);
			holder.groupItem=(TextView)convertView.findViewById(R.id.txtmenu);
		}
		else{
			holder=(ViewHolder)convertView.getTag();
		}
		holder.groupItem.setText(list.get(position));
		return convertView;
	}
	
	static class ViewHolder{
		TextView groupItem;
	}

}
