package org.gr.wocandroid.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class FunctionShareActivity extends Activity {
	@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
	        this.shareInformation();
		}
	public void shareInformation(){
		System.out.println("1111111111111111111111111");
		Intent shareIntent=new Intent(Intent.ACTION_SEND);
		shareIntent.setType("text/plain");
		shareIntent.putExtra(Intent.EXTRA_SUBJECT, "漫步云端");
		shareIntent.putExtra(Intent.EXTRA_TEXT, "云平台");
		startActivity(shareIntent.createChooser(shareIntent, "漫步云端"));
		Intent intent=new Intent();
		intent.setClass(FunctionShareActivity.this, InformationActivity.class);
		startActivity(intent);			
	}

}
