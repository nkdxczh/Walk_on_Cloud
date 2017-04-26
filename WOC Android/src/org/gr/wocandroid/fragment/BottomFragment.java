package org.gr.wocandroid.fragment;

import org.gr.wocandroid.activity.ForumMovieActivity;
import org.gr.wocandroid.activity.InformationActivity;
import org.gr.wocandroid.activity.MainActivity;
import org.gr.wocandroid.activity.R;

import com.example.client.FirstActivity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class BottomFragment extends Fragment {

	private View view;
	private Button btnStore;
	private Button btnForum;
	private Button btnPerson;
	private Button btnTalk;
	private MainActivity ma;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.frag_bottom, container, false);
		btnStore=(Button) view.findViewById(R.id.bottom_btnStore1);
		btnForum=(Button) view.findViewById(R.id.bottom_btnForum1);
		btnPerson=(Button) view.findViewById(R.id.bottom_btnPerson1);
		btnTalk=(Button) view.findViewById(R.id.bottom_btnTalk1);
		btnStore.setOnClickListener(new btnOcl());
		btnForum.setOnClickListener(new btnOcl());
		btnPerson.setOnClickListener(new btnOcl());
		btnTalk.setOnClickListener(new btnOcl());
		ma=(MainActivity)getActivity();
		return view;
	}
	
	class btnOcl implements OnClickListener{

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			switch(view.getId()){
			case R.id.bottom_btnStore1:
				ma.setTopFragment(new ShopHeaderFragment());
				ma.setNewFragment(new ShopBodyFragment());
				break;
			case R.id.bottom_btnForum1:
				ma.setTopFragment(new ForumMenuFragment());
				ma.setNewFragment(new ForumIndexFragment());
				break;
			case R.id.bottom_btnPerson1:
				Intent intent = new Intent();
				intent.setClass(getActivity(),
						InformationActivity.class);
				startActivity(intent);
				break;
			case R.id.bottom_btnTalk1:
				Intent intent1 = new Intent();
				intent1.setClass(getActivity(),
						FirstActivity.class);
				startActivity(intent1);
				break;
				/*
				ma.setTopFragment(new ForumMenuFragment());
				ma.setNewFragment(new ForumIndexFragment());*/
			}
		}
	}
}

