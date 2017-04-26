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
		// TODO Auto-generated method stub// ����4-1������һ���ռ��϶���

		// ����4-2�����÷���ʵ�ֶ�������������
		String content=getActivity().getIntent().getStringExtra("SearchContent");
		// ����1-2�������װ
		Post_Comment comment = new Post_Comment();
		comment.setPostComContent(content);
		// ����1-3�����л�
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-ddhh:mm:ss").create();
		String c = gson.toJson(comment);
		
		// ����2����������������ϲ����÷��������緢����������			
		
		// ����2-1������һ����������
		List<NameValuePair> lstNameValuePairs = new ArrayList<NameValuePair>();
		lstNameValuePairs.add(new BasicNameValuePair("comment_data", c));
		
		// ����2-2�����÷�ʽʵ������ķ���
		String response = WebAccessUtils.httpRequest("ASearchPostsServlet", lstNameValuePairs);
		System.out.println(response);

		// ����4-3������һ��ȫ�µ�����Type
		Type ListMessages = new TypeToken<ArrayList<Post>>() {
		}.getType();

		// ����4-4��������ʵ����һ��Gson����
		// ����4-5������JSon����
		List<Post> lstPosts = gson.fromJson(response, ListMessages);
		// ����4-1������һ���ռ��϶���
		List<Map<String, ?>> lst = new ArrayList<Map<String, ?>>();
		// ����4-2������һ���б���ѡ�����ʵ����
		for (Post hotPost : lstPosts) {
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

	// ����6���Զ����б�ѡ����¼�����
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