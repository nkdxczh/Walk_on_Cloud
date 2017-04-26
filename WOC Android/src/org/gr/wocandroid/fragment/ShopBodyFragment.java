package org.gr.wocandroid.fragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.gr.wocandroid.activity.DetailActivity;
import org.gr.wocandroid.activity.R;
import org.gr.wocandroid.activity.ReleaseActivity;
import org.gr.wocandroid.internet.WebAccessUtils;
import org.gr.wocandroid.vo.Commodities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

@SuppressLint("NewApi")
public class ShopBodyFragment extends Fragment implements OnPageChangeListener{

	
	private View view;
	private ViewPager newviewpager;
	private ViewPager topviewpager;
	private List<View> newviews;
	private List<View> topviews;
	private LinearLayout newcontainer;
	private LinearLayout topcontainer;
	private LayoutInflater layoutinflater;
	private ArrayList<Commodities>newest;
	private List<Commodities>top10;
	private Button mystore;
	private Button release;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
		// TODO Auto-generated method stub
		return super.onCreateAnimator(transit, enter, nextAnim);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		this.layoutinflater=inflater;
		view = inflater.inflate(R.layout.frag_shop, container, false);
		this.newviewpager=(ViewPager)view.findViewById(R.id.newviewpager);
		this.topviewpager=(ViewPager)view.findViewById(R.id.topviewpager);
		this.newcontainer=(LinearLayout) view.findViewById(R.id.newcontainer);
		this.topcontainer=(LinearLayout) view.findViewById(R.id.topcontainer);
		this.mystore=(Button) view.findViewById(R.id.mystore);
		this.release=(Button) view.findViewById(R.id.release);
		mystore.setOnClickListener(new BtnClk());
		release.setOnClickListener(new BtnClk());
		newviews=new ArrayList<View>();
		topviews=new ArrayList<View>();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		List<NameValuePair> lstNameValuePairs = new ArrayList<NameValuePair>();
		lstNameValuePairs.add(new BasicNameValuePair("key", "0"));
		String response1=WebAccessUtils.httpRequest("StoreIndexNewestServletA", lstNameValuePairs);
		Type ll=new TypeToken <ArrayList<Commodities>>(){}.getType();
		newest=gson.fromJson(response1, ll);
		String response2=WebAccessUtils.httpRequest("StoreIndexHotServletA", lstNameValuePairs);
		top10=gson.fromJson(response2, ll);
		for(int i=0;i<newest.size();i++){
			View view=inflater.inflate(R.layout.list_horizantal, container, false);
			System.out.println(view);
			TextView t;
			t=(TextView) view.findViewById(R.id.comname_list);
			t.setText(""+newest.get(i).getComName());
			t=(TextView) view.findViewById(R.id.price_list);
			t.setText(""+"$"+newest.get(i).getPrice());
			t=(TextView) view.findViewById(R.id.location_list);
			t.setText(""+newest.get(i).getComRegion());
			t=(TextView) view.findViewById(R.id.describe_list);
			t.setText(""+newest.get(i).getDescribe());
			view.setOnClickListener(new ItemClk(newest.get(i).getComId()));
			newviews.add(view);
		}
		for(int i=0;i<top10.size();i++){
			View view=inflater.inflate(R.layout.list_horizantal, container, false);
			TextView t;
			t=(TextView) view.findViewById(R.id.comname_list);
			t.setText(""+top10.get(i).getComName());
			t=(TextView) view.findViewById(R.id.price_list);
			t.setText(""+"$"+top10.get(i).getPrice());
			t=(TextView) view.findViewById(R.id.location_list);
			t.setText(""+top10.get(i).getComRegion());
			t=(TextView) view.findViewById(R.id.describe_list);
			t.setText(""+top10.get(i).getDescribe());
			view.setOnClickListener(new ItemClk(newest.get(i).getComId()));
			topviews.add(view);
		}
		newviewpager.setOffscreenPageLimit(3);
		newviewpager.setPageMargin(10);
		topviewpager.setOffscreenPageLimit(3);
		topviewpager.setPageMargin(10);
		newcontainer.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				return newviewpager.dispatchTouchEvent(arg1);
			}
		});
		newviewpager.setAdapter(new RollAdapter(newviews, newest));
		topviewpager.setAdapter(new RollAdapter(topviews, top10));
		newviewpager.setOnPageChangeListener(this);
		topviewpager.setOnPageChangeListener(this);
		return view;
	}
	class RollAdapter extends PagerAdapter{

		private List<View> views;
		private List<Commodities> commodities;
		public RollAdapter(List<View> views, List<Commodities> commodities) {
			super();
			this.views = views;
			this.commodities=commodities;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return views.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0==arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View)object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			System.out.println("pos"+position);
			View view=views.get(position);
			
			//TextView txt=(TextView)view.findViewById(R.id.forum_index_txtpost);
			//System.out.println(txt.getText());
			//ImageView img=(ImageView)view.findViewById(R.id.forum_index_imgpost);
			//txt.setText("测试数据");
			//img.setImageBitmap(BitmapFactory.decodeFile(dir+"/img1.png"));
			container.addView(view);
			return views.get(position);
		}
		
		
	}
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		if(newviewpager!=null){
			newviewpager.invalidate();
		}
		if(topviewpager!=null){
			topviewpager.invalidate();
		}
		
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
	}
	class BtnClk implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			switch(arg0.getId()){
			case R.id.mystore:
				break;
			case R.id.release:
				Intent intent = new Intent();
				intent.setClass(ShopBodyFragment.this.getActivity(),
						ReleaseActivity.class);
				startActivity(intent);
			}
		}
		
	}
	
	class ItemClk implements OnClickListener{

		private int comId;
		
		public ItemClk(int comId) {
			super();
			this.comId = comId;
		}

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.putExtra("comId", comId);
			intent.setClass(ShopBodyFragment.this.getActivity(),
					DetailActivity.class);
			startActivity(intent);
		}	
	}

	

}
