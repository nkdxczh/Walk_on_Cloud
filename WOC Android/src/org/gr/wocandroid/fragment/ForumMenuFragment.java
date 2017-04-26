package org.gr.wocandroid.fragment;

import java.util.ArrayList;
import java.util.List;

import org.gr.wocandroid.activity.*;
import org.gr.wocandroid.assist.MenuAdapter;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class ForumMenuFragment extends Fragment {

	private PopupWindow popupMenu;
	private View view;
	private View menuView;
	private TextView title;
	private ListView menuGroup;
	private EditText searchcontent;
	private List<String> groups;
	private Button btnSearch;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_forum_top, container, false);
		searchcontent=(EditText) view.findViewById(R.id.searchcontent);
		btnSearch=(Button)view.findViewById(R.id.searchbutton);
		btnSearch.setOnClickListener(new searchclick());
		title = (TextView) view.findViewById(R.id.txtForumTitle);
		title.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showMenu(v);
			}
		});
		return view;
	}

	@SuppressLint("ServiceCast")
	private void showMenu(View parent) {
		LayoutInflater layoutInflater = (LayoutInflater) getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		menuView = layoutInflater.inflate(R.layout.group_list, null);
		menuGroup = (ListView) menuView.findViewById(R.id.lvGroup);
		groups = new ArrayList<String>();
		groups.add("首页");
		groups.add("电影版块");
		groups.add("教学版块");
		groups.add("音乐版块");
		groups.add("读书版块");
		groups.add("畅谈版块");
		groups.add("活动版块");
		groups.add("发帖");
		MenuAdapter menuAdapter = new MenuAdapter(getActivity(), groups);
		menuGroup.setAdapter(menuAdapter);
		popupMenu = new PopupWindow(menuView, 500, 710);
		popupMenu.setFocusable(true);
		popupMenu.setOutsideTouchable(true);
		popupMenu.setBackgroundDrawable(new BitmapDrawable());
		WindowManager windowManager = (WindowManager) getActivity()
				.getSystemService(Context.WINDOW_SERVICE);
		int xPos = windowManager.getDefaultDisplay().getWidth() / 2
				- popupMenu.getWidth() / 2;
		Log.i("coder", "xPos:" + xPos);
		popupMenu.showAsDropDown(parent, xPos, 0);

		menuGroup.setOnItemClickListener(new myclick());
	}

	class myclick implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> adapterView, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			Toast.makeText(ForumMenuFragment.this.getActivity(),
					groups.get(position), 1000).show();
			Intent intent = new Intent();
			switch (position) {
			case 0:
				intent.setClass(getActivity(), MainActivity.class);
				startActivity(intent);
				break;
			case 1:
				intent.setClass(getActivity(), ForumMovieActivity.class);
				startActivity(intent);
				break;
			case 2:
				intent.setClass(getActivity(), ForumMusicActivity.class);
				startActivity(intent);
				break;
			case 3:
				intent.setClass(getActivity(), ForumBookActivity.class);
				startActivity(intent);
				break;
			case 4:
				intent.setClass(getActivity(), ForumEducationActivity.class);
				startActivity(intent);
				break;
			case 5:
				intent.setClass(getActivity(), ForumActivityActivity.class);
				startActivity(intent);
				break;
			case 6:
				intent.setClass(getActivity(), ForumChatActivity.class);
				startActivity(intent);
				break;
			case 7:
				intent.setClass(getActivity(), ForumReleaseActivity.class);
				startActivity(intent);
				break;
			default:
				break;
			}
			if (popupMenu != null) {
				popupMenu.dismiss();
			}
		}
	}

	class searchclick implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			String content=searchcontent.getText().toString().trim();
			// ����1-2�������װ
			Intent intent = new Intent();
			intent.putExtra("SearchContent", content);
			intent.setClass(getActivity(),
					ForumSearchActivity.class);
			startActivity(intent);
		}
	}

}
