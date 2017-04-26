package org.gr.wocandroid.design;

import org.gr.wocandroid.activity.LocationActivity;
import org.gr.wocandroid.activity.PersonFriendsInformationActivity;
import org.gr.wocandroid.activity.PersonOrdersActivity;
import org.gr.wocandroid.activity.R;

import com.example.client.TalkActivity;

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

public class FunctionPopupWindow extends PopupWindow {

	private View mMenuView;
	private TextView function_chat;
	private TextView function_search_friends;
	private TextView function_search_position;
	private TextView function_finish_order;
	private Button function_share;

	public FunctionPopupWindow(final Activity context,
			OnClickListener itemsOnClick) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.popup_window_function, null);
		function_chat = (TextView) mMenuView.findViewById(R.id.function_chat);
		function_search_friends = (TextView) mMenuView
				.findViewById(R.id.function_search_friends);
		function_search_position = (TextView) mMenuView
				.findViewById(R.id.function_search_position);
		function_finish_order = (TextView) mMenuView
				.findViewById(R.id.function_finish_order);
		function_share = (Button) mMenuView.findViewById(R.id.function_share);

		// int height =
		// context.getWindowManager().getDefaultDispl=ay().getHeight();
		@SuppressWarnings("deprecation")
		int width = context.getWindowManager().getDefaultDisplay().getWidth();
		this.setContentView(mMenuView);
		this.setWidth(width / 2 + 50);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setFocusable(true);
		this.setAnimationStyle(R.style.mystyle);
		ColorDrawable dw = new ColorDrawable(0000000000);
		this.setBackgroundDrawable(dw);
		mMenuView.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {

				int height = mMenuView.findViewById(R.id.pop_layout2).getTop();
				int y = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (y < height) {
						dismiss();
					}
				}
				return true;
			}
		});
		function_search_friends.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Intent intent = new Intent();
				// TODO Auto-generated method stub
				switch (view.getId()) {
				case R.id.function_chat:
					intent.setClass(context, TalkActivity.class);
					context.startActivity(intent);
					break;
				case R.id.function_search_friends:
					intent.setClass(context,
							PersonFriendsInformationActivity.class);
					context.startActivity(intent);
					break;
				case R.id.function_search_position:
					intent.setClass(context, LocationActivity.class);
					context.startActivity(intent);
					break;
				case R.id.function_finish_order:
					System.out.println("条形码");
					intent.setClass(context, PersonOrdersActivity.class);
					context.startActivity(intent);
					break;
				case R.id.function_share:
					System.out.println("1111111111111111111111111");
					Intent shareIntent = new Intent(Intent.ACTION_SEND);
					shareIntent.setType("text/plain");
					shareIntent.putExtra(Intent.EXTRA_SUBJECT, "漫步云端");
					shareIntent.putExtra(Intent.EXTRA_TEXT, "漫步云端云平台");
					context.startActivity(shareIntent.createChooser(
							shareIntent, "漫步云端"));
					break;
				}
			}
		});

		function_finish_order.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Intent intent = new Intent();
				// TODO Auto-generated method stub
				switch (view.getId()) {
				case R.id.function_chat:
					intent.setClass(context, TalkActivity.class);
					context.startActivity(intent);
					break;
				case R.id.function_search_friends:
					intent.setClass(context,
							PersonFriendsInformationActivity.class);
					context.startActivity(intent);
					break;
				case R.id.function_search_position:
					intent.setClass(context, LocationActivity.class);
					context.startActivity(intent);
					break;
				case R.id.function_finish_order:
					System.out.println("条形码");
					intent.setClass(context, PersonOrdersActivity.class);
					context.startActivity(intent);
					break;
				case R.id.function_share:
					System.out.println("1111111111111111111111111");
					Intent shareIntent = new Intent(Intent.ACTION_SEND);
					shareIntent.setType("text/plain");
					shareIntent.putExtra(Intent.EXTRA_SUBJECT, "漫步云端");
					shareIntent.putExtra(Intent.EXTRA_TEXT, "漫步云端云平台");
					context.startActivity(shareIntent.createChooser(
							shareIntent, "漫步云端"));
					break;
				}
			}
		});

		function_search_position.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Intent intent = new Intent();
				// TODO Auto-generated method stub
				switch (view.getId()) {
				case R.id.function_chat:
					intent.setClass(context, TalkActivity.class);
					context.startActivity(intent);
					break;
				case R.id.function_search_friends:
					intent.setClass(context,
							PersonFriendsInformationActivity.class);
					context.startActivity(intent);
					break;
				case R.id.function_search_position:
					intent.setClass(context, LocationActivity.class);
					context.startActivity(intent);
					break;
				case R.id.function_finish_order:
					System.out.println("条形码");
					intent.setClass(context, PersonOrdersActivity.class);
					context.startActivity(intent);
					break;
				case R.id.function_share:
					System.out.println("1111111111111111111111111");
					Intent shareIntent = new Intent(Intent.ACTION_SEND);
					shareIntent.setType("text/plain");
					shareIntent.putExtra(Intent.EXTRA_SUBJECT, "漫步云端");
					shareIntent.putExtra(Intent.EXTRA_TEXT, "漫步云端云平台");
					context.startActivity(shareIntent.createChooser(
							shareIntent, "漫步云端"));
					break;
				}
			}
		});

		function_share.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Intent intent = new Intent();
				// TODO Auto-generated method stub
				switch (view.getId()) {
				case R.id.function_chat:
					intent.setClass(context, TalkActivity.class);
					context.startActivity(intent);
					break;
				case R.id.function_search_friends:
					intent.setClass(context,
							PersonFriendsInformationActivity.class);
					context.startActivity(intent);
					break;
				case R.id.function_search_position:
					intent.setClass(context, LocationActivity.class);
					context.startActivity(intent);
					break;
				case R.id.function_finish_order:
					System.out.println("条形码");
					intent.setClass(context, PersonOrdersActivity.class);
					context.startActivity(intent);
					break;
				case R.id.function_share:
					System.out.println("1111111111111111111111111");
					Intent shareIntent = new Intent(Intent.ACTION_SEND);
					shareIntent.setType("text/plain");
					shareIntent.putExtra(Intent.EXTRA_SUBJECT, "漫步云端");
					shareIntent.putExtra(Intent.EXTRA_TEXT, "漫步云端云平台");
					context.startActivity(shareIntent.createChooser(
							shareIntent, "漫步云端"));
					break;
				}
			}
		});

		function_chat.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Intent intent = new Intent();
				// TODO Auto-generated method stub
				switch (view.getId()) {
				case R.id.function_chat:
					intent.setClass(context, TalkActivity.class);
					context.startActivity(intent);
					break;
				case R.id.function_search_friends:
					intent.setClass(context,
							PersonFriendsInformationActivity.class);
					context.startActivity(intent);
					break;
				case R.id.function_search_position:
					intent.setClass(context, LocationActivity.class);
					context.startActivity(intent);
					break;
				case R.id.function_finish_order:
					System.out.println("条形码");
					intent.setClass(context, PersonOrdersActivity.class);
					context.startActivity(intent);
					break;
				case R.id.function_share:
					System.out.println("1111111111111111111111111");
					Intent shareIntent = new Intent(Intent.ACTION_SEND);
					shareIntent.setType("text/plain");
					shareIntent.putExtra(Intent.EXTRA_SUBJECT, "漫步云端");
					shareIntent.putExtra(Intent.EXTRA_TEXT, "漫步云端云平台");
					context.startActivity(shareIntent.createChooser(
							shareIntent, "漫步云端"));
					break;
				}
			}
		});

	}

}
