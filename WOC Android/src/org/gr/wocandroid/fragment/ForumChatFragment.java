package org.gr.wocandroid.fragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gr.wocandroid.activity.ForumDetailActivity;
import org.gr.wocandroid.activity.ForumMovieActivity;
import org.gr.wocandroid.activity.R;
import org.gr.wocandroid.internet.WebAccessUtils;
import org.gr.wocandroid.po.Post;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ForumChatFragment extends Fragment implements
		OnPageChangeListener {

	private List<Map<String, ?>> lstPostsData;
	private ListView lstPosts;
	private View view;

	private ViewPager viewPager;
	private List<View> views;
	private LinearLayout rollContainer;
	private String dir;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater
				.inflate(R.layout.fragment_forum_chat, container, false);
		this.lstPosts = (ListView) view.findViewById(R.id.lstMoviePosts);

		lstPostsData = fetchPosts();
		SimpleAdapter adapter = new SimpleAdapter(this.getActivity(),
				this.lstPostsData, R.layout.listitem_post, new String[] {
						"post_title", "post_type", "txtlikenum",
						"txtcommentnum", "post_content" }, new int[] {
						R.id.post_title, R.id.post_type, R.id.txtlikenum,
						R.id.txtcommentnum, R.id.post_content });
		lstPosts.setAdapter(adapter);
		ForumIndexFragment.setListViewHeight(lstPosts);
		lstPosts.setOnItemClickListener(new ItemOcl());

		rollContainer = (LinearLayout) view.findViewById(R.id.moviecontainer);
		viewPager = (ViewPager) view.findViewById(R.id.movieviewpager);
		dir = Environment.getExternalStorageDirectory().getAbsolutePath();
		views = new ArrayList<View>();
		for (int i = 1; i <= 12; i++) {
			View view = inflater.inflate(R.layout.listitem_roll, container,
					false);
			views.add(view);
		}
		viewPager.setOffscreenPageLimit(3);
		viewPager.setPageMargin(10);
		rollContainer.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				return viewPager.dispatchTouchEvent(arg1);
			}
		});
		viewPager.setAdapter(new RollAdapter());
		viewPager.setOnPageChangeListener(this);

		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	private List<Post> fetchBestPosts() {
		// TODO Auto-generated method stub// 步骤4-1：创建一个空集合对象

		// 步骤4-2：调用方法实现对网络服务的请求
		String response = WebAccessUtils.httpRequest("AGetBestPostsServlet");
		System.out.println(response);

		// 步骤4-3：设置一个全新的类型Type
		Type ListMessages = new TypeToken<ArrayList<Post>>() {
		}.getType();

		// 步骤4-4：创建并实例化一个Gson对象
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss")
				.create();
		// 步骤4-5：解析JSon数据
		List<Post> lstBestPosts = gson.fromJson(response, ListMessages);
		return lstBestPosts;
	}

	private List<Map<String, ?>> fetchPosts() {
		// TODO Auto-generated method stub// 步骤4-1：创建一个空集合对象

		// 步骤4-2：调用方法实现对网络服务的请求
		String response = WebAccessUtils
				.httpRequest("AGetLatestPostsByLayoutServlet?posttype=500");
		System.out.println(response);

		// 步骤4-3：设置一个全新的类型Type
		Type ListMessages = new TypeToken<ArrayList<Post>>() {
		}.getType();

		// 步骤4-4：创建并实例化一个Gson对象
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss")
				.create();
		// 步骤4-5：解析JSon数据
		List<Post> lstHotPosts = gson.fromJson(response, ListMessages);
		// 步骤4-1：创建一个空集合对象
		List<Map<String, ?>> lst = new ArrayList<Map<String, ?>>();
		// 步骤4-2：创建一个列表中选项对象并实例化
		for (Post hotPost : lstHotPosts) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("pid", hotPost.getPostId());
			item.put("post_title", hotPost.getPostName());
			item.put("txtlikenum", hotPost.getPostScore());
			item.put("txtcommentnum", hotPost.getPostEnterNum());
			item.put("post_content", hotPost.getPostName());
			item.put("post_type", hotPost.getPropertyName());
			// 步骤4-7：将创建好的选项对象添加到集合中
			lst.add(item);
		}
		return lst;
	}

	private class ViewOcl implements View.OnClickListener {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			switch (view.getId()) {
			case R.id.llMovie:
				Intent intent = new Intent();
				intent.setClass(ForumChatFragment.this.getActivity(),
						ForumMovieActivity.class);
				startActivity(intent);
			}
		}

	}

	// 步骤6：自定义列表选项单击事件处理
	private class ItemOcl implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> adapter, View view,
				int position, long arg3) {
			Intent intent = new Intent();
			intent.getIntExtra("pid", (Integer) lstPostsData.get(position).get("pid"));
			intent.setClass(ForumChatFragment.this.getActivity(),
					ForumDetailActivity.class);
			startActivity(intent);
		}
	}

	class RollAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return views.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			System.out.println("pos" + position);
			View view = views.get(position);
			TextView txt = (TextView) view
					.findViewById(R.id.forum_index_txtpost);
			System.out.println(txt.getText());
			// ImageView
			// img=(ImageView)view.findViewById(R.id.forum_index_imgpost);
			txt.setText("测试数据");
			// img.setImageBitmap(BitmapFactory.decodeFile(dir+"/img1.png"));
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
		if (viewPager != null) {
			viewPager.invalidate();
		}

	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub

	}
}