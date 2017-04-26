package org.gr.wocandroid.activity;

import org.gr.wocandroid.fragment.ForumIndexFragment;
import org.gr.wocandroid.fragment.ForumMenuFragment;
import org.gr.wocandroid.fragment.ShopBodyFragment;
import org.gr.wocandroid.fragment.ShopHeaderFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Window;

public class MainActivity extends Activity {

	private FragmentManager FM;
	private FragmentTransaction FT;
	private ShopBodyFragment index;
	private ShopHeaderFragment top;
	public int key;
	public int sort;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity);
		index=new ShopBodyFragment();
		top=new ShopHeaderFragment();
		FM=getFragmentManager();
		FT=FM.beginTransaction();
		FT.add(R.id.topfragment, top);
		FT.add(R.id.mainfragment, index);
		FT.commit();
	}
	
	public void setTopFragment(Fragment fragment){
		FT=FM.beginTransaction();
		FT.replace(R.id.topfragment, fragment);
		FT.commit();
	}
	
	public void setNewFragment(Fragment fragment){
		FT=FM.beginTransaction();
		FT.replace(R.id.mainfragment, fragment);
		FT.commit();
	}
}
