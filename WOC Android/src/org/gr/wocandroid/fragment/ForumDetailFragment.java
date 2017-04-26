package org.gr.wocandroid.fragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.gr.wocandroid.activity.ForumDetailActivity;
import org.gr.wocandroid.activity.ForumMovieActivity;
import org.gr.wocandroid.activity.MainActivity;
import org.gr.wocandroid.activity.R;
import org.gr.wocandroid.activity.UserManager;
import org.gr.wocandroid.internet.WebAccessUtils;
import org.gr.wocandroid.po.Post;
import org.gr.wocandroid.po.Post_Comment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ForumDetailFragment extends Fragment implements OnPageChangeListener{

	private List<Map<String, ?>> lstComment;
	private ListView lstComments;
	private View view;
	private int pid;
	private List<Post> lstRelatedPostsData;
	
	private ViewPager viewPager;
	private List<View> views;
	private LinearLayout rollContainer;
	private String dir;
	
	private Button btnLike;
	private Button btnComment;
	private Button btnRelease;
	private LinearLayout commentl;
	private EditText edtComment;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		pid=getActivity().getIntent().getIntExtra("pid", 1);
		Post post=fetchPostInf();
		view = inflater.inflate(R.layout.fragment_forum_detail, container, false);
		
		commentl=(LinearLayout) view.findViewById(R.id.llComment);
		lstComments = (ListView) view.findViewById(R.id.lstComments);
		
		btnLike=(Button)view.findViewById(R.id.btnExpress);
		btnLike.setOnClickListener(new LikeOcl());
		
		btnComment=(Button)view.findViewById(R.id.btnReleaseComment);
		btnComment.setOnClickListener(new CommentOcl());
		
		btnRelease=(Button)view.findViewById(R.id.btnRelease11);
		btnRelease.setOnClickListener(new ReleaseCommentOcl());
		
		edtComment=(EditText) view.findViewById(R.id.edtComment11);

		((TextView)view.findViewById(R.id.forum_detail_title)).setText(post.getPostName());
		((TextView)view.findViewById(R.id.forum_detail_likenum)).setText(post.getScoreCount()+"");
		((TextView)view.findViewById(R.id.forum_detail_commentnum)).setText(post.getComCount()+"");
		((ImageView)view.findViewById(R.id.forum_detail_postPhoto)).setImageBitmap(WebAccessUtils.getBitmapFromServer("jsp/forum/post_images/"+post.getResPath()));
		
		lstComments.setAdapter(new CommentAdapter(getActivity(),fetchComments()));
		ForumIndexFragment.setListViewHeight(lstComments);
		lstComments.setOnItemClickListener(new ItemOcl());
		lstComments.setOnItemLongClickListener(new ItemLongOcl());

		rollContainer=(LinearLayout)view.findViewById(R.id.detailcontainer);
		viewPager=(ViewPager)view.findViewById(R.id.detailviewpager);
		dir=Environment.getExternalStorageDirectory().getAbsolutePath();
		views=new ArrayList<View>();
		lstRelatedPostsData=fetchRelatedPosts();
		for(int i=0;i<lstRelatedPostsData.size();i++){
			View view = inflater.inflate(R.layout.listitem_roll, container,
					false);
			TextView bestPostTitle=(TextView) view.findViewById(R.id.forum_index_txtpost);
			bestPostTitle.setText(lstRelatedPostsData.get(i).getPostName());
			
			ImageView picPost=(ImageView) view.findViewById(R.id.forum_index_imgpost);
			Bitmap bitmap=WebAccessUtils.getBitmapFromServer("jsp/forum/post_images/"+lstRelatedPostsData.get(i).getResPath());
			picPost.setImageBitmap(bitmap);
			
			view.setOnClickListener(new RollItemOcl(lstRelatedPostsData.get(i).getPostId()));
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

	private Post fetchPostInf() {
		// TODO Auto-generated method stub// ����4-1������һ���ռ��϶���

		// ����4-2�����÷���ʵ�ֶ�������������
		String response = WebAccessUtils.httpRequest("AShowPostServelet?postid="+pid);

		// ����4-3������һ��ȫ�µ�����Type
		Type Message = new TypeToken<Post>() {}.getType();

		// ����4-4��������ʵ��һ��Gson����
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		// ����4-5������JSon���
		Post post = gson.fromJson(response, Message);
		return post;
	}
	
	private List<Post> fetchRelatedPosts() {
		// TODO Auto-generated method stub// ����4-1������һ���ռ��϶���

		// ����4-2�����÷���ʵ�ֶ�������������
		String response = WebAccessUtils.httpRequest("AShowRelatedPostServelet?postid="+pid);
		System.out.println(response);

		// ����4-3������һ��ȫ�µ�����Type
		Type ListMessages = new TypeToken<ArrayList<Post>>() {}.getType();

		// ����4-4��������ʵ��һ��Gson����
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		// ����4-5������JSon���
		List<Post> lstRelatedPosts = gson.fromJson(response, ListMessages);
		return lstRelatedPosts;
	}

	private List<Map<String, ?>> fetchComments() {
		// TODO Auto-generated method stub// ����4-1������һ���ռ��϶���

		// ����4-2�����÷���ʵ�ֶ�������������
		String response = WebAccessUtils.httpRequest("AShowPostCommentsServelet?postid="+pid);
		System.out.println(response);

		// ����4-3������һ��ȫ�µ�����Type
		Type Message = new TypeToken<List<Post_Comment>>() {}.getType();

		// ����4-4��������ʵ��һ��Gson����
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		// ����4-5������JSon���
		List<Post_Comment> comments = gson.fromJson(response, Message);
		List<Map<String, ?>> lst = new ArrayList<Map<String, ?>>();
		for(Post_Comment comment:comments){
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("commentId", comment.getPostComId());
			item.put("userName", comment.getReleaseUserName());
			item.put("commentTime",comment.getPostComTime());
			item.put("commentContent", comment.getPostComContent());
			// ����4-3���������õ�ѡ�������ӵ�������
			lst.add(item);
		}
		return lst;
	}
	
	private List<Map<String, ?>> fetchInnerComment(int postcomid) {
		// TODO Auto-generated method stub
		// ����4-1������һ���ռ��϶���
		String response = WebAccessUtils.httpRequest("AGetInnerCommentServlet?postcomid="+postcomid+"&postid="+pid);
		System.out.println(response);

		// ����4-3������һ��ȫ�µ�����Type
		Type Message = new TypeToken<List<Post_Comment>>() {}.getType();

		// ����4-4��������ʵ��һ��Gson����
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		// ����4-5������JSon���
		List<Post_Comment> comments = gson.fromJson(response, Message);
		List<Map<String, ?>> lst = new ArrayList<Map<String, ?>>();
		for(Post_Comment comment:comments){
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("innercomment_id", comment.getPostComId());
			item.put("innercomment_sender", comment.getReleaseUserName());
			item.put("innercomment_receiver", comment.getReplyUserName());
			item.put("innercomment_time",comment.getPostComTime());
			item.put("innercomment_content", comment.getPostComContent());
			// ����4-3���������õ�ѡ�������ӵ�������
			lst.add(item);
		}
		return lst;
	}
	
	private class LikeOcl implements View.OnClickListener {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			String response = WebAccessUtils.httpRequest("AExpressLikeServlet?postid="+pid+"&userid="+UserManager.getUserId());
			Intent intent = new Intent();
			intent.putExtra("pid",pid);
			intent.setClass(getActivity(),
					ForumDetailActivity.class);
			startActivity(intent);
		}

	}
	
	private class CommentOcl implements View.OnClickListener {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			edtComment.requestFocus();
		}

	}
	
	private class InnerCommentOcl implements View.OnClickListener {

		private int replyId;
		
		public InnerCommentOcl(int replyId) {
			super();
			this.replyId = replyId;
		}

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			edtComment.requestFocus();
			btnRelease.setOnClickListener(new ReleaseInnerCommentOcl(replyId));
		}

	}
	
	private class ReleaseCommentOcl implements View.OnClickListener {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			String content=edtComment.getText().toString().trim();
			// ����1-2�������װ
			Post_Comment comment = new Post_Comment();
			comment.setPostComContent(content);
			comment.setPostId(pid);
			comment.setPostReplyId(0);
			// ����1-3�����л�
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-ddhh:mm:ss").create();
			String c = gson.toJson(comment);
			
			// ����2�������������ϲ����÷��������緢���������			
			
			// ����2-1������һ�������
			List<NameValuePair> lstNameValuePairs = new ArrayList<NameValuePair>();
			lstNameValuePairs.add(new BasicNameValuePair("comment_data", c));
			
			// ����2-2�����÷�ʽʵ������ķ���
			String response = WebAccessUtils.httpRequest("AReleaseCommentServelet?userid="+UserManager.getUserId(), lstNameValuePairs);
			Intent intent = new Intent();
			intent.putExtra("pid",pid);
			intent.setClass(getActivity(),
					ForumDetailActivity.class);
			startActivity(intent);
		}

	}
	
	private class ReleaseInnerCommentOcl implements View.OnClickListener {

		private int replyId;
		
		public ReleaseInnerCommentOcl(int replyId) {
			super();
			this.replyId = replyId;
		}

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			String content=edtComment.getText().toString().trim();
			// ����1-2�������װ
			Post_Comment comment = new Post_Comment();
			comment.setPostComContent(content);
			comment.setPostId(pid);
			comment.setPostReplyId(replyId);
			// ����1-3�����л�
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-ddhh:mm:ss").create();
			String c = gson.toJson(comment);
			
			// ����2�������������ϲ����÷��������緢���������			
			
			// ����2-1������һ�������
			List<NameValuePair> lstNameValuePairs = new ArrayList<NameValuePair>();
			lstNameValuePairs.add(new BasicNameValuePair("comment_data", c));
			
			// ����2-2�����÷�ʽʵ������ķ���
			String response = WebAccessUtils.httpRequest("AReleaseCommentServelet?userid="+UserManager.getUserId(), lstNameValuePairs);
			Intent intent = new Intent();
			intent.putExtra("pid",pid);
			intent.setClass(getActivity(),
					ForumDetailActivity.class);
			startActivity(intent);
		}

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
	
	final class commentView{
		TextView userName;
		TextView commentTime;
		TextView commentContent;
		ListView innerComments;
		ImageButton btnRelease;
	}

	final class innerCommentView{
		TextView innercomment_sender;
		TextView innercomment_receiver;
		TextView innercomment_content;
		TextView innercomment_time;
	}
	
	class CommentAdapter extends BaseAdapter{
		
		private Context context;
		private List<Map<String, ?>> comments;
		
		public CommentAdapter(Context context, List<Map<String, ?>> comments) {
			super();
			this.context = context;
			this.comments = comments;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return comments.size();
		}
		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public View getView(int position, View convertview, ViewGroup parent) {
			// TODO Auto-generated method stub
			commentView cv=null;
			if(convertview==null){
				cv=new commentView();
				LayoutInflater inflater=LayoutInflater.from(context);
				convertview=inflater.inflate(R.layout.listitem_comment, null);
				cv.commentContent=(TextView)convertview.findViewById(R.id.commentContent);
				cv.commentTime=(TextView)convertview.findViewById(R.id.commentTime);
				cv.userName=(TextView)convertview.findViewById(R.id.userName);
				cv.innerComments=(ListView)convertview.findViewById(R.id.innerComment);
				cv.btnRelease=(ImageButton)convertview.findViewById(R.id.btnReleaseInnerComment);
				
				cv.commentContent.setText((String)comments.get(position).get("commentContent"));
				Date date=(Date)comments.get(position).get("commentTime");
				cv.commentTime.setText(date.toLocaleString());
				cv.userName.setText((String)comments.get(position).get("userName"));
				cv.innerComments.setAdapter(new innerCommentAdapter(context, fetchInnerComment((Integer)comments.get(position).get("commentId"))));
				ForumIndexFragment.setListViewHeight(cv.innerComments);
				cv.btnRelease.setOnClickListener(new InnerCommentOcl((Integer)comments.get(position).get("commentId")) );
			}
			else{
				cv=(commentView)convertview.getTag();
			}
			return convertview;
		}

	}
	
	class innerCommentAdapter extends BaseAdapter{

		private Context context;
		private List<Map<String, ?>> innerComments;
		
		public innerCommentAdapter(Context context,
				List<Map<String, ?>> innerComments) {
			super();
			this.context = context;
			this.innerComments = innerComments;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return innerComments.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertview, ViewGroup parent) {
			// TODO Auto-generated method stub
			innerCommentView cv=null;
			if(convertview==null){
				cv=new innerCommentView();
				LayoutInflater inflater=LayoutInflater.from(context);
				convertview=inflater.inflate(R.layout.listitem_innercomment, null);
				convertview.setOnClickListener(new InnerCommentOcl((Integer)innerComments.get(position).get("innercomment_id")));
				cv.innercomment_content=(TextView)convertview.findViewById(R.id.innercomment_content);
				cv.innercomment_receiver=(TextView)convertview.findViewById(R.id.innercomment_receiver);
				cv.innercomment_sender=(TextView)convertview.findViewById(R.id.innercomment_sender);
				cv.innercomment_time=(TextView)convertview.findViewById(R.id.innercomment_time);
				
				cv.innercomment_content.setText((String)innerComments.get(position).get("innercomment_content"));
				cv.innercomment_receiver.setText((String)innerComments.get(position).get("innercomment_receiver"));
				cv.innercomment_sender.setText((String)innerComments.get(position).get("innercomment_sender"));
				Date date=(Date)innerComments.get(position).get("innercomment_time");
				cv.innercomment_time.setText(date.getYear()+1900+"-"+(date.getMonth()+1)+"-"+date.getDate());
			}
			else{
				cv=(innerCommentView)convertview.getTag();
			}
			return convertview;
		}
		
	}


	// ����6���Զ����б�ѡ����¼�����
	private class ItemOcl implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> adapter, View view,
				int position, long arg3) {
			// TODO Auto-generated method stub
			// ����6-1��ʹ�ø÷�����position�����ȡѡ�е�ѡ����󲢸�ֵ��Map������
			Map<String, ?> selectedItem = lstComment.get(position);
			// ����
			Toast.makeText(getActivity().getApplicationContext(),
					"��ѡ�е��Ǳ��Ϊ:" + selectedItem.get("mid"), Toast.LENGTH_LONG)
					.show();
		}
	}

	// ����7���Զ����б�ѡ�����¼�����
	private class ItemLongOcl implements OnItemLongClickListener {

		@Override
		public boolean onItemLongClick(AdapterView<?> adapter, View view,
				int position, long arg3) {
			// TODO Auto-generated method stub
			// ����7-1��ʹ�ø÷�����position�����ȡѡ�е�ѡ����󲢸�ֵ��Map������
			Map<String, ?> selectedItem = lstComment.get(position);
			// ����
			Toast.makeText(getActivity().getApplicationContext(),
					"�ſ��ң�" + selectedItem.get("mid"), Toast.LENGTH_LONG).show();
			return true;
		}

	}
	
	class RollAdapter extends PagerAdapter{

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
		if(viewPager!=null){
			viewPager.invalidate();
		}
		
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		
	}
}