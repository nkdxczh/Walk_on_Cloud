package org.gr.wocandroid.design;

import org.gr.wocandroid.activity.InformationActivity;
import org.gr.wocandroid.activity.MainActivity;
import org.gr.wocandroid.activity.R;

import com.tencent.weibo.oauthv2.OAuthV2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

public class OperationPopupWindow extends PopupWindow {


	private Button  operation_cancel;
	TextView operation_return_index;
	TextView operation_return_store;
	TextView operation_return_forum;
	TextView operation_setting;
	
	private View mMenuView;

	public OperationPopupWindow(final Activity context,OnClickListener itemsOnClick) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.popup_window_operation, null);
		
		//int height = context.getWindowManager().getDefaultDisplay().getHeight();
		@SuppressWarnings("deprecation")
		int width = context.getWindowManager().getDefaultDisplay().getWidth();
		operation_cancel = (Button) mMenuView.findViewById(R.id.operation_cancel);
		operation_return_index=(TextView) mMenuView.findViewById(R.id.operation_return_index);
		operation_return_store=(TextView) mMenuView.findViewById(R.id.operation_return_store);
		operation_return_forum=(TextView) mMenuView.findViewById(R.id.operation_return_forum);
		operation_setting=(TextView) mMenuView.findViewById(R.id.operation_setting);
		operation_cancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				SaveDate.saveDate(context, new OAuthV2()); 
				context.finish();
			}
		});
		this.setContentView(mMenuView);
		this.setWidth(width/2+50);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setFocusable(true);
		this.setAnimationStyle(R.style.mystyle);
		ColorDrawable dw = new ColorDrawable(0000000000);
		this.setBackgroundDrawable(dw);
		mMenuView.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				
				int height = mMenuView.findViewById(R.id.pop_layout).getTop();
				int y=(int) event.getY();
				if(event.getAction()==MotionEvent.ACTION_UP){
					if(y<height){
						dismiss();
					}
				}				
				return true;
			}
		});
		operation_return_index.setOnClickListener(new  View.OnClickListener(){
			@Override
			public void onClick(View view) {
				
				Intent intent=new Intent();
				// TODO Auto-generated method stub
				switch(view.getId())
				{
				case R.id.operation_return_index:
					System.out.println("返回主页");
					intent.setClass(context, MainActivity.class);
					context.startActivity(intent);
				break;
			    case R.id.operation_return_store:
			    	System.out.println("返回商城主页");
					intent.setClass(context, MainActivity.class);
					context.startActivity(intent);
				break;
			    case R.id.operation_return_forum:
			    	System.out.println("1111111111111111111111111");
			    	intent.setClass(context, MainActivity.class);
					context.startActivity(intent);
					break;
			    case R.id.operation_setting:
			    	System.out.println("1111111111111111111111111");
					intent.setClass(context,InformationActivity.class);
					context.startActivity(intent);
					break;
				}
			}
		});
		operation_return_store.setOnClickListener(new  View.OnClickListener(){
			@Override
			public void onClick(View view) {
				
				Intent intent=new Intent();
				// TODO Auto-generated method stub
				switch(view.getId())
				{
				case R.id.operation_return_index:
					System.out.println("返回主页");
					intent.setClass(context, MainActivity.class);
					context.startActivity(intent);
				break;
			    case R.id.operation_return_store:
			    	System.out.println("返回商城主页");
					intent.setClass(context, MainActivity.class);
					context.startActivity(intent);
				break;
			    case R.id.operation_return_forum:
			    	System.out.println("1111111111111111111111111");
			    	intent.setClass(context, MainActivity.class);
					context.startActivity(intent);
					break;
			    case R.id.operation_setting:
			    	System.out.println("1111111111111111111111111");
					intent.setClass(context,InformationActivity.class);
					context.startActivity(intent);
					break;
				}
			}
		});
		operation_return_forum.setOnClickListener(new  View.OnClickListener(){
			@Override
			public void onClick(View view) {
				
				Intent intent=new Intent();
				// TODO Auto-generated method stub
				switch(view.getId())
				{
				case R.id.operation_return_index:
					System.out.println("返回主页");
					intent.setClass(context, MainActivity.class);
					context.startActivity(intent);
				break;
			    case R.id.operation_return_store:
			    	System.out.println("返回商城主页");
					intent.setClass(context, MainActivity.class);
					context.startActivity(intent);
				break;
			    case R.id.operation_return_forum:
			    	System.out.println("1111111111111111111111111");
			    	intent.setClass(context, MainActivity.class);
					context.startActivity(intent);
					break;
			    case R.id.operation_setting:
			    	System.out.println("1111111111111111111111111");
					intent.setClass(context,InformationActivity.class);
					context.startActivity(intent);
					break;
				}
			}
		});
		operation_setting.setOnClickListener(new  View.OnClickListener(){
			@Override
			public void onClick(View view) {
				
				Intent intent=new Intent();
				// TODO Auto-generated method stub
				switch(view.getId())
				{
				case R.id.operation_return_index:
					System.out.println("返回主页");
					intent.setClass(context, MainActivity.class);
					context.startActivity(intent);
				break;
			    case R.id.operation_return_store:
			    	System.out.println("返回商城主页");
					intent.setClass(context, MainActivity.class);
					context.startActivity(intent);
				break;
			    case R.id.operation_return_forum:
			    	System.out.println("1111111111111111111111111");
			    	intent.setClass(context, MainActivity.class);
					context.startActivity(intent);
					break;
			    case R.id.operation_setting:
			    	System.out.println("1111111111111111111111111");
					intent.setClass(context,InformationActivity.class);
					context.startActivity(intent);
					break;
				}
			}
		});
		

	}

}
