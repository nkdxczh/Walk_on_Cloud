package org.gr.wocandroid.fragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.gr.wocandroid.activity.ForumDetailActivity;
import org.gr.wocandroid.activity.ForumMovieActivity;
import org.gr.wocandroid.activity.R;
import org.gr.wocandroid.internet.WebAccessUtils;
import org.gr.wocandroid.po.Post;
import org.gr.wocandroid.po.Post_Comment;

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

public class ForumSearchFragment extends Fragment{

	private List<Map<String, ?>> lstPostsData;
	private ListView lstPosts;
	private View view;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater
				.inflate(R.layout.fragment_forum_search, container, false);
		this.lstPosts = (ListView) view.findViewById(R.id.lstSearchPosts);

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

		
		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	private List<Map<String, ?>> fetchPosts() {
		// TODO Auto-generated method stub// 步骤4-1：创建一个空集合对象

		// 步骤4-2：调用方法实现对网络服务的请求
		String content=getActivity().getIntent().getStringExtra("SearchContent");
		// 步骤1-2：对象封装
		Post_Comment comment = new Post_Comment();
		comment.setPostComContent(content);
		// 步骤1-3：序列化
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-ddhh:mm:ss").create();
		String c = gson.toJson(comment);
		
		// 步骤2：设置请求参数集合并调用方法向网络发送请求数据			
		
		// 步骤2-1：创建一个参数集合
		List<NameValuePair> lstNameValuePairs = new ArrayList<NameValuePair>();
		lstNameValuePairs.add(new BasicNameValuePair("comment_data", c));
		
		// 步骤2-2：调用方式实现请求的发送
		String response = WebAccessUtils.httpRequest("ASearchPostsServlet", lstNameValuePairs);
		System.out.println(response);

		// 步骤4-3：设置一个全新的类型Type
		Type ListMessages = new TypeToken<ArrayList<Post>>() {
		}.getType();

		// 步骤4-4：创建并实例化一个Gson对象
		// 步骤4-5：解析JSon数据
		List<Post> lstPosts = gson.fromJson(response, ListMessages);
		// 步骤4-1：创建一个空集合对象
		List<Map<String, ?>> lst = new ArrayList<Map<String, ?>>();
		// 步骤4-2：创建一个列表中选项对象并实例化
		for (Post hotPost : lstPosts) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("pid", hotPost.getPostId());
			item.put("post_title", hotPost.getPostName());
			item.put("txtlikenum", hotPost.getScoreCount());
			item.put("txtcommentnum", hotPost.getPostEnterNum());
			item.put("post_content", hotPost.getPostName());
			item.put("post_type", hotPost.getPropertyName());
			// 步骤4-7：将创建好的选项对象添加到集合中
			lst.add(item);
		}
		return lst;
	}

	// 步骤6：自定义列表选项单击事件处理
	private class ItemOcl implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> adapter, View view,
				int position, long arg3) {
			Intent intent = new Intent();
			System.out.println(position);
			intent.putExtra("pid", (Integer) lstPostsData.get(position).get("pid"));
			System.out.println(lstPostsData.get(position).get("pid"));
			intent.setClass(ForumSearchFragment.this.getActivity(),
					ForumDetailActivity.class);
			startActivity(intent);
		}
	}

}