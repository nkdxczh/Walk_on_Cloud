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
import org.gr.wocandroid.activity.MainActivity;
import org.gr.wocandroid.activity.R;
import org.gr.wocandroid.activity.UserManager;
import org.gr.wocandroid.internet.WebAccessUtils;
import org.gr.wocandroid.po.Post;
import org.gr.wocandroid.po.Post_Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ForumReleaseFragment extends Fragment{
	
	private Spinner spnType;
	private Spinner spnPart;
	private View view;
	private ArrayAdapter<String> adapterType;
	private ArrayAdapter<String> adapterPart;
	private TextView postName;
	private Button btnRelease;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_forum_release, container, false);
		postName=(TextView) view.findViewById(R.id.txtPostName);
		btnRelease=(Button) view.findViewById(R.id.btnReleasePost);
		btnRelease.setOnClickListener(new ReleaseOcl());
		ArrayList<String> lstPart=new ArrayList<>();
		lstPart.add("电影");
		lstPart.add("教学");
		lstPart.add("音乐");
		lstPart.add("读书");
		lstPart.add("畅谈");
		lstPart.add("活动");
		final ArrayList<String> lstMovie=fetchPostType(100);
		final ArrayList<String> lstEducation=fetchPostType(200);
		final ArrayList<String> lstMusic=fetchPostType(300);
		final ArrayList<String> lstBook=fetchPostType(400);
		final ArrayList<String> lstChat=fetchPostType(500);
		final ArrayList<String> lstActivity=fetchPostType(600);
		
		adapterPart=new ArrayAdapter<String>(getActivity(),R.layout.listitem_spinner,lstPart);
		adapterPart.setDropDownViewResource(R.layout.listitem_spinner);
		
		adapterType=new ArrayAdapter<String>(getActivity(),R.layout.listitem_spinner,lstMovie);
		adapterType.setDropDownViewResource(R.layout.listitem_spinner);
		
		spnType = (Spinner) view.findViewById(R.id.spnType);
		spnPart = (Spinner) view.findViewById(R.id.spnPart);
		
		spnPart.setAdapter(adapterPart);
		spnType.setAdapter(adapterType);
		spnPart.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0){
				arg0.setVisibility(View.VISIBLE);
			}

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				ArrayList<String> list;
				switch(arg2){
				case 0:
					list=lstMovie;
					break;
				case 1:
					list=lstEducation;
					break;
				case 2:
					list=lstMusic;
					break;
				case 3:
					list=lstBook;
					break;
				case 4:
					list=lstChat;
					break;
				case 5:
					list=lstActivity;
					break;
				default:
					list=new ArrayList<String>();
				}
				adapterType=new ArrayAdapter<String>(getActivity(),R.layout.listitem_spinner,list);
				spnType.setAdapter(adapterType);
				arg0.setVisibility(View.VISIBLE);
			}
			
		});
		spnPart.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		spnPart.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				// TODO Auto-generated method stub
				
			}
		});
		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	private ArrayList<String> fetchPostType(int ii) {
		// TODO Auto-generated method stub// ����4-1������һ���ռ��϶���

		// ����4-2�����÷���ʵ�ֶ�������������
		String response = WebAccessUtils.httpRequest("AGetPostTypesByBankuaiServlet?bankuaiid="+ii);

		// ����4-3������һ��ȫ�µ�����Type
		Type Message = new TypeToken<List<Post_Type>>() {}.getType();

		// ����4-4��������ʵ��һ��Gson����
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		// ����4-5������JSon���
		List<Post_Type> types = gson.fromJson(response, Message);
		ArrayList<String> t=new ArrayList<>();
		for(int i=0;i<types.size();i++){
			t.add(types.get(i).getPostTypeName());
		}
		return t;
	}
	
	private class ReleaseOcl implements OnClickListener {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method 

			// ����1����ȡ��ݲ���װ���󣬽�����������л���JSON��
			// ����1-1����ȡ�˺ź�����
			//WebAccessUtils.uploadFile("1.jpg");
			String name=postName.getText().toString().trim();
			int part=spnPart.getSelectedItemPosition();
			int type=spnType.getSelectedItemPosition();
			int t=(part+1)*100+type;
			// ����1-2�������װ
			Post post = new Post();
			post.setPostName(name);
			post.setPostProperty(t);
			// ����1-3�����л�
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-ddhh:mm:ss").create();
			String p = gson.toJson(post);
			
			// ����2�������������ϲ����÷��������緢���������			
			
			// ����2-1������һ�������
			List<NameValuePair> lstNameValuePairs = new ArrayList<NameValuePair>();
			lstNameValuePairs.add(new BasicNameValuePair("post_data", p));
			
			
			// ����2-2�����÷�ʽʵ������ķ���
			String response = WebAccessUtils.httpRequest("AReleasePostServlet?userid="+UserManager.getUserId(), lstNameValuePairs);
			Intent intent = new Intent();
			intent.setClass(getActivity(),
					MainActivity.class);
			startActivity(intent);
		}
	}
}