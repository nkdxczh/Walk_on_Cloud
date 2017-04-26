package org.gr.wocandroid.assist;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;

public class SliderPostsAdapter extends PagerAdapter {
	private List<View> views;

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return views.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return false;
	}

}
