package org.gr.wocandroid.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.gr.wocandroid.internet.WebAccessUtils;
import org.gr.wocandroid.vo.Commodities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class SearchResultActivity extends Activity {

	private List<Map<String, ?>> lstData;
	private ListView lstcommodity;
	List<Commodities> commodities;
	private View view;
	private SimpleAdapter adapter;
	private int option;
	String key="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_searchresult);
		lstcommodity=(ListView) findViewById(R.id.lstcommodity_result);
		key=getIntent().getStringExtra("key");
		option=getIntent().getIntExtra("option", 0);
		fetchData();
		adapter = new SimpleAdapter(this, this.lstData,
				R.layout.list_commodities, new String[] { 
						"comname", "describe", "price", "location" }, new int[] {
						 R.id.comname, R.id.describe,
						R.id.price, R.id.location });
		this.lstcommodity.setAdapter(adapter);
		this.lstcommodity.setOnItemClickListener(new ItemOcl());
	}
	private void fetchData() {
		// TODO Auto-generated method stub
		// 步骤4-1：创建一个空集合对象
		List<Map<String, ?>> lst = new ArrayList<Map<String, ?>>();
		Gson gson = new GsonBuilder().setDateFormat(
				"yyyy-MM-ddhh:mm:ss").create();
		List<NameValuePair> lstNameValuePairs = new ArrayList<NameValuePair>();
		lstNameValuePairs.add(new BasicNameValuePair("key", ""+key));
		lstNameValuePairs.add(new BasicNameValuePair("option", ""+option));
		String response = WebAccessUtils.httpRequest("SearchCommodityServletA", lstNameValuePairs);
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
	class ItemOcl implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			int comId=commodities.get(arg2).getComId();
			Intent intent = new Intent();
			intent.putExtra("comId", comId);
			intent.setClass(SearchResultActivity.this,
					DetailActivity.class);
			startActivity(intent);
		}
		
	}
	
	
	
}
