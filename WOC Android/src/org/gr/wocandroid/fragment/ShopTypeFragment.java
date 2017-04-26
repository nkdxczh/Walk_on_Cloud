package org.gr.wocandroid.fragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.gr.wocandroid.activity.DetailActivity;
import org.gr.wocandroid.activity.MainActivity;
import org.gr.wocandroid.activity.R;
import org.gr.wocandroid.internet.WebAccessUtils;
import org.gr.wocandroid.vo.Commodities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

@SuppressLint("NewApi")
public class ShopTypeFragment extends Fragment {
	private List<Map<String, ?>> lstData;
	private ListView lstcommodity;
	List<Commodities> commodities;
	private View view;
	private Button timesort;
	private Button pricesort;
	private Button noticesort;
	private SimpleAdapter adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view=inflater.inflate(R.layout.frag_typestore, container, false);
		lstcommodity=(ListView) view.findViewById(R.id.lstcommodity);
		timesort=(Button) view.findViewById(R.id.timesort);
		pricesort=(Button) view.findViewById(R.id.pricesort);
		noticesort=(Button) view.findViewById(R.id.noticesort);
		timesort.setOnClickListener(new ButtonCli());
		pricesort.setOnClickListener(new ButtonCli());
		noticesort.setOnClickListener(new ButtonCli());
		MainActivity parent=(MainActivity) getActivity();
		fetchData(parent.key, parent.sort);
		adapter = new SimpleAdapter(this.getActivity(), this.lstData,
				R.layout.list_commodities, new String[] { 
						"comname", "describe", "price", "location" }, new int[] {
						 R.id.comname, R.id.describe,
						R.id.price, R.id.location });
		this.lstcommodity.setAdapter(adapter);
		this.lstcommodity.setOnItemClickListener(new ItemOcl());
		return view;
	}
	private void fetchData(int key, int sort) {
		// TODO Auto-generated method stub
		// 步骤4-1：创建一个空集合对象
		List<Map<String, ?>> lst = new ArrayList<Map<String, ?>>();
		Gson gson = new GsonBuilder().setDateFormat(
				"yyyy-MM-ddhh:mm:ss").create();
		List<NameValuePair> lstNameValuePairs = new ArrayList<NameValuePair>();
		lstNameValuePairs.add(new BasicNameValuePair("key", ""+key));
		lstNameValuePairs.add(new BasicNameValuePair("sort", ""+sort));
		String response = WebAccessUtils.httpRequest("ShowTypeStoreServletA", lstNameValuePairs);
		Type ll=new TypeToken <ArrayList<Commodities>>(){}.getType();
		commodities=gson.fromJson(response, ll);
		for(int i=0;i<commodities.size();i++){
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("comname", commodities.get(i).getComName());
			item.put("describe", commodities.get(i).getDescribe());
			item.put("price", commodities.get(i).getPrice());
			item.put("location", commodities.get(i).getComRegion());
			lst.add(item);
		}
		lstData=lst;
	}
	public void updateData(int key, int sort){
		List<Map<String, ?>> lst = new ArrayList<Map<String, ?>>();
		Gson gson = new GsonBuilder().setDateFormat(
				"yyyy-MM-ddhh:mm:ss").create();
		List<NameValuePair> lstNameValuePairs = new ArrayList<NameValuePair>();
		lstNameValuePairs.add(new BasicNameValuePair("key", ""+key));
		lstNameValuePairs.add(new BasicNameValuePair("sort", ""+sort));
		String response = WebAccessUtils.httpRequest("ShowTypeStoreServletA", lstNameValuePairs);
		Type ll=new TypeToken <ArrayList<Commodities>>(){}.getType();
		commodities=gson.fromJson(response, ll);
		for(int i=0;i<commodities.size();i++){
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("comname", commodities.get(i).getComName());
			item.put("describe", commodities.get(i).getDescribe());
			item.put("price", commodities.get(i).getPrice());
			item.put("location", commodities.get(i).getComRegion());
			lst.add(item);
		}
		lstData.removeAll(lstData);
		lstData.addAll(lst);
		adapter.notifyDataSetChanged();
	}
	class ItemOcl implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			int comId=commodities.get(arg2).getComId();
			Intent intent = new Intent();
			intent.putExtra("comId", comId);
			intent.setClass(ShopTypeFragment.this.getActivity(),
					DetailActivity.class);
			startActivity(intent);
		}
		
	}
	class ButtonCli implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			MainActivity act=(MainActivity) getActivity();
			switch(arg0.getId()){
			case R.id.timesort:
				act.sort=1;
				updateData(act.key, act.sort);
				break;
			case R.id.pricesort:
				act.sort=2;
				updateData(act.key, act.sort);
				break;
			case R.id.noticesort:
				act.sort=4;
				updateData(act.key, act.sort);
				break;
			}
		}
		
	}

}
