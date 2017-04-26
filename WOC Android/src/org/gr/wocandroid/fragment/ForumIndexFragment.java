package org.gr.wocandroid.fragment;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.gr.wocandroid.activity.ForumActivityActivity;
import org.gr.wocandroid.activity.ForumBookActivity;
import org.gr.wocandroid.activity.ForumChatActivity;
import org.gr.wocandroid.activity.ForumDetailActivity;
import org.gr.wocandroid.activity.ForumEducationActivity;
import org.gr.wocandroid.activity.ForumMovieActivity;
import org.gr.wocandroid.activity.ForumMusicActivity;
import org.gr.wocandroid.activity.R;
import org.gr.wocandroid.internet.WebAccessUtils;
import org.gr.wocandroid.po.Post;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ForumIndexFragment extends Fragment implements
		OnPageChangeListener {

	private Button btnMovie;
	private Button btnMusic;
	private Button btnEducation;
	private Button btnChat;
	private Button btnBook;
	private Button btnActivity;
	private List<Map<String, ?>> lstHotPostsData;
	private ListView lstHotPosts;
	private List<Post> lstBestPostsData;
	private View view;
	private ImageView picPost;

	private ViewPager viewPager;
	private List<View> views;
	private LinearLayout rollContainer;
	private LayoutInflater inflater;
	private String dir;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// ���ڷ��ʻ�������˽�������ʷ��뵽���߳��н���
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		// TODO Auto-generated method stub
		view = inflater
				.inflate(R.layout.fragment_forum_index, container, false);

		this.btnMovie = (Button) view.findViewById(R.id.forum_index_btnmovie);
		this.btnActivity = (Button) view
				.findViewById(R.id.forum_index_btnactivity);
		this.btnBook = (Button) view.findViewById(R.id.forum_index_btnbook);
		this.btnChat = (Button) view.findViewById(R.id.forum_index_btnchat);
		this.btnEducation = (Button) view
				.findViewById(R.id.forum_index_btneducation);
		this.btnMusic = (Button) view.findViewById(R.id.forum_index_btnmusic);
		this.btnMovie.setOnClickListener(new ViewOcl());
		this.btnMusic.setOnClickListener(new ViewOcl());
		this.btnActivity.setOnClickListener(new ViewOcl());
		this.btnEducation.setOnClickListener(new ViewOcl());
		this.btnBook.setOnClickListener(new ViewOcl());
		this.btnChat.setOnClickListener(new ViewOcl());

		this.lstHotPosts = (ListView) view.findViewById(R.id.lstHotPosts);

		lstHotPostsData = fetchHotPosts();
		SimpleAdapter adapter = new SimpleAdapter(this.getActivity(),
				this.lstHotPostsData, R.layout.listitem_post, new String[] {
						"post_title", "post_type", "txtlikenum",
						"txtcommentnum", "post_content" }, new int[] {
						R.id.post_title, R.id.post_type, R.id.txtlikenum,
						R.id.txtcommentnum, R.id.post_content });
		this.lstHotPosts.setAdapter(adapter);
		this.lstHotPosts.setOnItemClickListener(new ItemOcl());
		setListViewHeight(lstHotPosts);

		this.inflater = inflater;
		rollContainer = (LinearLayout) view.findViewById(R.id.container);
		viewPager = (ViewPager) view.findViewById(R.id.viewpager);
		dir = Environment.getExternalStorageDirectory().getAbsolutePath();
		views = new ArrayList<View>();
		lstBestPostsData=fetchBestPosts();
		for (int i = 0; i <lstBestPostsData.size(); i++) {
			View view = inflater.inflate(R.layout.listitem_roll, container,
					false);
			TextView bestPostTitle=(TextView) view.findViewById(R.id.forum_index_txtpost);
			bestPostTitle.setText(lstBestPostsData.get(i).getPostName());
			
			ImageView picPost=(ImageView) view.findViewById(R.id.forum_index_imgpost);
			Bitmap bitmap=WebAccessUtils.getBitmapFromServer("jsp/forum/post_images/"+lstBestPostsData.get(i).getResPath());
			picPost.setImageBitmap(bitmap);
			
			view.setOnClickListener(new RollItemOcl(lstBestPostsData.get(i).getPostId()));
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
		// TODO Auto-generated method stub// ����4-1������һ���ռ��϶���

		// ����4-2�����÷���ʵ�ֶ�������������
		String response = WebAccessUtils.httpRequest("AGetBestPostsServlet");
		System.out.println(response);

		// ����4-3������һ��ȫ�µ�����Type
		Type ListMessages = new TypeToken<ArrayList<Post>>() {}.getType();

		// ����4-4��������ʵ��һ��Gson����
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		// ����4-5������JSon���
		List<Post> lstBestPosts = gson.fromJson(response, ListMessages);
		return lstBestPosts;
	}

	private List<Map<String, ?>> fetchHotPosts() {
		// TODO Auto-generated method stub// ����4-1������һ���ռ��϶���

		// ����4-2�����÷���ʵ�ֶ�������������
		String response = WebAccessUtils.httpRequest("AGetHotPostsServlet");
		System.out.println(response);

		// ����4-3������һ��ȫ�µ�����Type
		Type ListMessages = new TypeToken<ArrayList<Post>>() {}.getType();

		// ����4-4��������ʵ��һ��Gson����
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		// ����4-5������JSon���
		List<Post> lstHotPosts = gson.fromJson(response, ListMessages);
		// ����4-1������һ���ռ��϶���
		List<Map<String, ?>> lst = new ArrayList<Map<String, ?>>();
		// ����4-2������һ���б���ѡ�����ʵ��
		for (Post hotPost : lstHotPosts) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("pid", hotPost.getPostId());
			item.put("post_title", hotPost.getPostName());
			item.put("txtlikenum", hotPost.getScoreCount());
			item.put("txtcommentnum", hotPost.getPostEnterNum());
			item.put("post_content", hotPost.getPostName());
			item.put("post_type", hotPost.getPropertyName());
			// ����4-7���������õ�ѡ�������ӵ�������
			lst.add(item);
		}
		return lst;
	}
	
	private class RollItemOcl implements OnClickListener {

		int postid;
		public RollItemOcl(int postid) {
			super();
			this.postid = postid;
		}
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method 
			Intent intent = new Intent();
			intent.putExtra("pid",postid);

			intent.setClass(getActivity(),
					ForumDetailActivity.class);
			startActivity(intent);
		}
	}

	private class ViewOcl implements OnClickListener {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			switch (view.getId()) {
			case R.id.forum_index_btnmovie:
				intent.setClass(getActivity(), ForumMovieActivity.class);
				startActivity(intent);
				break;
			case R.id.forum_index_btnmusic:
				intent.setClass(getActivity(), ForumMusicActivity.class);
				startActivity(intent);
				break;
			case R.id.forum_index_btneducation:
				intent.setClass(getActivity(), ForumEducationActivity.class);
				startActivity(intent);
				break;
			case R.id.forum_index_btnactivity:
				intent.setClass(getActivity(), ForumActivityActivity.class);
				startActivity(intent);
				break;
			case R.id.forum_index_btnbook:
				intent.setClass(getActivity(), ForumBookActivity.class);
				startActivity(intent);
				break;
			case R.id.forum_index_btnchat:
				intent.setClass(getActivity(), ForumChatActivity.class);
				startActivity(intent);
				break;
			}
		}

	}

	// ����6���Զ����б�ѡ����¼�����
	private class ItemOcl implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> adapter, View view,
				int position, long arg3) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.putExtra("pid",
					(Integer) lstHotPostsData.get(position).get("pid"));

			intent.setClass(getActivity(),
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

	public static void setListViewHeight(ListView listView) {
		if (listView == null)
			return;
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null)
			return;

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View lisItem = listAdapter.getView(i, null, listView);
			lisItem.measure(0, 0);
			totalHeight += lisItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}
	
}