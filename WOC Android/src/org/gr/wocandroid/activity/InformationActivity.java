package org.gr.wocandroid.activity;


import org.gr.wocandroid.design.FunctionPopupWindow;
import org.gr.wocandroid.design.MyScrollLayout;
import org.gr.wocandroid.design.OnViewChangeListener;
import org.gr.wocandroid.design.OperationPopupWindow;

import android.os.Bundle;
import android.os.StrictMode;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class InformationActivity extends Activity implements OnViewChangeListener, OnClickListener{

	private View vFunctionPopupWindow;
	private View vOperationPopupWindow;
	private MyScrollLayout mScrollLayout;
	private LinearLayout[] mImageViews;
	private int mViewCount;
	private int mCursorPoint;
	private ImageView operation;
	private ImageView function;

	private TextView person_information;
	private TextView store_information;
	private TextView forum_information;
	private TextView detail_information;
	private TextView update_information;
	private TextView update_password;
	private TextView friend_information;
	private TextView sell_produces;
	private TextView exchange_produces;
	private TextView float_produces;
	private TextView attention_produces;
	private TextView orders;
	private TextView release_posts;
	private TextView read_posts;	
	private TextView attention_posts;	
	private TextView function_chat;
	private TextView function_search_friends;
	private TextView function_search_position;
	private TextView function_finish_order;
	private Button function_share;
	private TextView operation_return_index;
	private TextView operation_return_store;
	private TextView operation_return_forum;
	private TextView operation_setting;
	private TextView operation_cancel;

	// 还未用到
	
	// private boolean isOpen = false;
	// 自定义的弹出框类
	
	OperationPopupWindow menuWindow; // 弹出框
	FunctionPopupWindow menuWindow2; // 弹出框

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 由于访问互联网，因此将网络访问放入到子线程中进行
		StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.information_main);		
		LayoutInflater information = LayoutInflater.from(this);
		vFunctionPopupWindow = information.inflate(R.layout.popup_window_function, null);
		vOperationPopupWindow = information.inflate(R.layout.popup_window_operation, null);
		this.person_information = (TextView) findViewById(R.id.information_person);
		this.store_information = (TextView) findViewById(R.id.information_store);
		this.forum_information = (TextView) findViewById(R.id.information_forum);
		this.person_information = (TextView) findViewById(R.id.information_person);
		this.store_information = (TextView) findViewById(R.id.information_store);
		this.forum_information = (TextView) findViewById(R.id.information_forum);
		this.detail_information = (TextView) findViewById(R.id.detail_information);
		this.update_information = (TextView) findViewById(R.id.update_information);
		this.update_password = (TextView) findViewById(R.id.update_password);
		this.friend_information = (TextView) findViewById(R.id.friend_information);		
		this.sell_produces = (TextView) findViewById(R.id.sell_produces);
		this.exchange_produces = (TextView) findViewById(R.id.exchange_produces);
		this.float_produces = (TextView) findViewById(R.id.float_produces);
		this.attention_produces = (TextView) findViewById(R.id.attention_produces);
		this.orders = (TextView) findViewById(R.id.orders);
		this.release_posts = (TextView) findViewById(R.id.release_posts);
		this.read_posts = (TextView) findViewById(R.id.read_posts);
		this.attention_posts = (TextView) findViewById(R.id.attention_posts);		
		
		this.function_chat = (TextView)vFunctionPopupWindow.findViewById(R.id.function_chat);
		this.function_search_friends = (TextView) vFunctionPopupWindow.findViewById(R.id.function_search_friends);
		this.function_search_position = (TextView) vFunctionPopupWindow.findViewById(R.id.function_search_position);
		this.function_finish_order = (TextView) vFunctionPopupWindow.findViewById(R.id.function_finish_order);
		this.function_share = (Button) vFunctionPopupWindow.findViewById(R.id.function_share);		
		this.operation_return_index = (TextView) vOperationPopupWindow.findViewById(R.id.operation_return_index);
		this.operation_return_store = (TextView) vOperationPopupWindow.findViewById(R.id.operation_return_store);
		this.operation_return_forum = (TextView) vOperationPopupWindow.findViewById(R.id.operation_return_forum);
		this.operation_setting = (TextView) vOperationPopupWindow.findViewById(R.id.operation_setting);
		this.operation_cancel = (Button) vOperationPopupWindow.findViewById(R.id.operation_cancel);				
		this.detail_information.setOnClickListener(new ViewOnClickListener() );
		this.update_information.setOnClickListener(new ViewOnClickListener() );
		this.update_password.setOnClickListener(new ViewOnClickListener() );
		this.friend_information.setOnClickListener(new ViewOnClickListener() );
		this.sell_produces.setOnClickListener(new ViewOnClickListener() );
		this.exchange_produces.setOnClickListener(new ViewOnClickListener() );
		this.float_produces.setOnClickListener(new ViewOnClickListener() );
		this.attention_produces.setOnClickListener(new ViewOnClickListener() );
		this.orders.setOnClickListener(new ViewOnClickListener() );
		this.release_posts.setOnClickListener(new ViewOnClickListener() );
		this.read_posts.setOnClickListener(new ViewOnClickListener() );
		this.attention_posts.setOnClickListener(new ViewOnClickListener() );			
		this.function_chat.setOnClickListener(new ViewOnClickListener() );
		this.function_search_friends.setOnClickListener(new ViewOnClickListener() );
		this.function_search_position.setOnClickListener(new ViewOnClickListener() );
		this.function_finish_order.setOnClickListener(new ViewOnClickListener() );
		this.function_share.setOnClickListener(new ViewOnClickListener() );
		this.operation_return_index.setOnClickListener(new ViewOnClickListener() );
		this.operation_return_store.setOnClickListener(new ViewOnClickListener() );
		this.operation_return_forum.setOnClickListener(new ViewOnClickListener() );
		this.operation_setting.setOnClickListener(new ViewOnClickListener() );
		this.operation_cancel.setOnClickListener(new ViewOnClickListener() );

		initial();
	}

	private void initial() {
		mScrollLayout = (MyScrollLayout) findViewById(R.id.ScrollLayout);
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.information_second_layout);
		mViewCount = mScrollLayout.getChildCount();
		mImageViews = new LinearLayout[mViewCount];
		for (int i = 0; i < mViewCount; i++) {
			mImageViews[i] = (LinearLayout) linearLayout.getChildAt(i);
			mImageViews[i].setEnabled(true);
			mImageViews[i].setOnClickListener(this);
			mImageViews[i].setTag(i);
		}
		mCursorPoint = 0;
		mImageViews[mCursorPoint].setEnabled(false);
		mScrollLayout.SetOnViewChangeListener(this);
		operation = (ImageView) findViewById(R.id.operation);
		function = (ImageView) findViewById(R.id.function);

		operation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
			uploadImage(InformationActivity.this);
			}
		});
		function.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
			uploadImage2(InformationActivity.this);
			}
		});
	}

	


	public void uploadImage(final Activity context) {
		menuWindow = new OperationPopupWindow(InformationActivity.this, itemsOnClick);
		// 显示窗口
		menuWindow.showAtLocation(InformationActivity.this.findViewById(R.id.operation),
				Gravity.TOP | Gravity.RIGHT, 10, 230); // 设置layout在PopupWindow中显示的位置
	}

	public void uploadImage2(final Activity context) {
		menuWindow2 = new FunctionPopupWindow(InformationActivity.this, itemsOnClick2);
		// 显示窗口
		menuWindow2.showAtLocation(InformationActivity.this.findViewById(R.id.function),
				Gravity.TOP | Gravity.RIGHT, 10, 230); // 设置layout在PopupWindow中显示的位置
	}

	// 为弹出窗口实现监听类
	private OnClickListener itemsOnClick = new OnClickListener() {

		public void onClick(View view) {
			switch(view.getId())
			{			
			case R.id.operation_return_index:
			Toast.makeText(getApplicationContext(), "已点击菜单", Toast.LENGTH_LONG).show();
			break;
		    case R.id.operation_return_store:
			Toast.makeText(getApplicationContext(), "已点击菜单", Toast.LENGTH_LONG).show();
			break;
		    case R.id.operation_return_forum:
				Toast.makeText(getApplicationContext(), "已点击菜单", Toast.LENGTH_LONG).show();
				break;
		    case R.id.operation_setting:
				Toast.makeText(getApplicationContext(), "已点击菜单", Toast.LENGTH_LONG).show();
				break;
		    case R.id.operation_cancel:
				Toast.makeText(getApplicationContext(), "已点击菜单", Toast.LENGTH_LONG).show();
				break;
				}
			menuWindow.dismiss();
		}
	};

	// 为弹出窗口实现监听类
	private OnClickListener itemsOnClick2 = new OnClickListener() {

		public void onClick(View view) {
			switch(view.getId())
			{
			case R.id.function_chat:
			Toast.makeText(getApplicationContext(), "已点击菜单", Toast.LENGTH_LONG).show();
			break;
		    case R.id.function_search_friends:
			Toast.makeText(getApplicationContext(), "已点击菜单", Toast.LENGTH_LONG).show();
			break;
		    case R.id.function_search_position:
				Toast.makeText(getApplicationContext(), "已点击菜单", Toast.LENGTH_LONG).show();
				break;
		    case R.id.function_finish_order:
				Toast.makeText(getApplicationContext(), "已点击菜单", Toast.LENGTH_LONG).show();
				break;
		    case R.id.function_share:
				Toast.makeText(getApplicationContext(), "已点击菜单", Toast.LENGTH_LONG).show();
				break;
			}
			menuWindow2.dismiss();
		}
	};

	private void setCursorPoint(int index) {
		if (index < 0 || index > mViewCount - 1 || mCursorPoint == index) {
			return;
		}
		mImageViews[mCursorPoint].setEnabled(true);
		mImageViews[index].setEnabled(false);
		mCursorPoint = index;

		if (index == 0) {
			person_information.setTextColor(0xffFFFFFF);
			store_information.setTextColor(Color.BLACK);
			forum_information.setTextColor(Color.BLACK);
			person_information.setBackgroundColor(0xffFF4500);
			store_information.setBackgroundColor(Color.WHITE);
			forum_information.setBackgroundColor(Color.WHITE);
		} else if (index == 1) {
			person_information.setTextColor(Color.BLACK);
			store_information.setTextColor(0xffFFFFFF);
			forum_information.setTextColor(Color.BLACK);
			person_information.setBackgroundColor(Color.WHITE);
			store_information.setBackgroundColor(0xffFF4500);
			forum_information.setBackgroundColor(Color.WHITE);
		} else {
			person_information.setTextColor(Color.BLACK);
			store_information.setTextColor(Color.BLACK);
			forum_information.setTextColor(0xffFFFFFF);
			person_information.setBackgroundColor(Color.WHITE);
			store_information.setBackgroundColor(Color.WHITE);
			forum_information.setBackgroundColor(0xffFF4500);
		}
	}

	@Override
	public void OnViewChange(int view) {
		// TODO Auto-generated method stub
		setCursorPoint(view);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		int position = (Integer) (view.getTag());
		setCursorPoint(position);
		mScrollLayout.snapToScreen(position);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_MENU)) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	
	
	private class ViewOnClickListener implements View.OnClickListener{
		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			switch (view.getId()) {
			case R.id.detail_information:
				Toast.makeText(getApplicationContext(), "已点击个人详细信息", Toast.LENGTH_SHORT).show();		
				intent.setClass(InformationActivity.this, PersonDetailInformationActivity.class);
				startActivity(intent);
				break;
			case R.id.update_information:
				Toast.makeText(getApplicationContext(), "已点击个人更新信息", Toast.LENGTH_SHORT).show();
				intent.setClass(InformationActivity.this, ChangeDetailInformationActivity.class);
				startActivity(intent);
				break;
			case R.id.update_password:
				intent.setClass(InformationActivity.this, ChangePasswordActivity.class);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "已点击个人更新密码", Toast.LENGTH_SHORT).show();
				break;
			case R.id.friend_information:
				Toast.makeText(getApplicationContext(), "已点击个人好友信息", Toast.LENGTH_SHORT).show();
				intent.setClass(InformationActivity.this, PersonFriendsInformationActivity.class);
				startActivity(intent);
				break;
			case R.id.sell_produces:
				intent.setClass(InformationActivity.this, PersonSellCommoditiesActivity.class);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "已点击交易商品信息", Toast.LENGTH_SHORT).show();
				break;
			case R.id.exchange_produces:
				intent.setClass(InformationActivity.this, PersonExchangeCommoditiesActivity.class);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "已点击交换商品信息", Toast.LENGTH_SHORT).show();
				break;
			case R.id.float_produces:
				intent.setClass(InformationActivity.this, PersonFloatCommoditiesActivity.class);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "已点击漂流商品信息", Toast.LENGTH_SHORT).show();
				break;
			case R.id.attention_produces:
				intent.setClass(InformationActivity.this, PersonAttentionCommoditiesActivity.class);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "已点击关注商品信息", Toast.LENGTH_SHORT).show();
				break;
			case R.id.orders:
				intent.setClass(InformationActivity.this, PersonOrdersActivity.class);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "已点击订单信息", Toast.LENGTH_SHORT).show();
				break;
			case R.id.release_posts:
				intent.setClass(InformationActivity.this, PersonReleasePostActivity.class);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "已点击已发帖子信息", Toast.LENGTH_SHORT).show();
				break;
			case R.id.read_posts:
				intent.setClass(InformationActivity.this, PersonReadPostActivity.class);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "已点击已读帖子信息", Toast.LENGTH_SHORT).show();
				break;
			case R.id.attention_posts:
				intent.setClass(InformationActivity.this, PersonAttentionPostActivity.class);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "已点击关注帖子信息", Toast.LENGTH_SHORT).show();
				break;
			case R.id.function_chat:
				intent.setClass(InformationActivity.this, PersonFriendsInformationActivity.class);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "已点击个人详细信息", Toast.LENGTH_SHORT).show();
				break;
			case R.id.function_search_friends:
				intent.setClass(InformationActivity.this, PersonFriendsInformationActivity.class);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "已点击个人更新信息", Toast.LENGTH_SHORT).show();
				break;
			case R.id.function_search_position:
				intent.setClass(InformationActivity.this, PersonFriendsInformationActivity.class);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "已点击个人更新密码", Toast.LENGTH_SHORT).show();
				break;
			case R.id.function_finish_order:
				intent.setClass(InformationActivity.this, PersonFriendsInformationActivity.class);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "已点击个人好友信息", Toast.LENGTH_SHORT).show();
				break;
			case R.id.function_share:
				intent.setClass(InformationActivity.this, PersonFriendsInformationActivity.class);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "已点击个人详细信息", Toast.LENGTH_SHORT).show();
				break;
			case R.id.operation_return_index:
				intent.setClass(InformationActivity.this, PersonFriendsInformationActivity.class);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "已点击个人更新信息", Toast.LENGTH_SHORT).show();
				break;
			case R.id.operation_return_store:
				Toast.makeText(getApplicationContext(), "已点击个人更新密码", Toast.LENGTH_SHORT).show();
				break;
			case R.id.operation_return_forum:
				intent.setClass(InformationActivity.this, PersonFriendsInformationActivity.class);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "已点击个人好友信息", Toast.LENGTH_SHORT).show();
				break;
			case R.id.operation_setting:
				intent.setClass(InformationActivity.this, PersonFriendsInformationActivity.class);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "已点击个人好友信息", Toast.LENGTH_SHORT).show();
				break;
			case R.id.operation_cancel:
				intent.setClass(InformationActivity.this, PersonFriendsInformationActivity.class);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "已点击个人更新密码", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
		}
		
	}
		
}
