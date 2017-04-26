package org.gr.wocandroid.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class ForumActivityActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_forum_activity);
	}
}

