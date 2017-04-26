package com.example.client;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.gr.wocandroid.activity.R;

import com.example.data.Config;
import com.example.data.Data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class FriendsActivity extends Activity {

	ListView listView;
	private List<Map<String, ?>> lstfriends=Data.getLstFriends();
	SimpleAdapter adapter;
	Handler handler;
	int oldsize=Data.getLstFriends().size();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friends);
		System.out.println("已经跳到好友页面");
		handler=new Handler();
		listView=(ListView) findViewById(R.id.lstFriends);
		 adapter = new SimpleAdapter(this, this.lstfriends,
					R.layout.friends_item, new String[] { "friendPhoto",
							"friendNickName", "friendPublish", "friendMessage" }, new int[] {
							R.id.friendPhoto, R.id.friendNickName, R.id.friendPublish,
							R.id.friendMessage });
	
			this.listView.setAdapter(adapter);
	
			new Thread() {
				@Override
				public void run() {
					while (true) {
						if(Data.getLstFriends().size()!=oldsize)
						{
							handler.post(runnableUi);
							oldsize=Data.getLstFriends().size();
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					}

					// handler.sendEmptyMessage(0);
				}
			}.start();
		
			new Thread() {
				@Override
				public void run() {
					DataOutputStream dos=null;
					try {
						dos = new DataOutputStream(new BufferedOutputStream(
								Data.getSocket().getOutputStream()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//DataInputStream dis=Data.getDis();
					try {
						dos.writeInt(Config.REQUEST_GET_FRIENDS);
						dos.flush();
						//Data.getLstFriends().clear();
						System.out.println("请求获得好友"); 
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
						
					}

					// handler.sendEmptyMessage(0);
				
			}.start();
			System.out.println("请求获得好友执行完毕");
			
			 this.listView.setOnItemClickListener(new ItemOcl());

	}
	
	Runnable   runnableUi=new  Runnable(){
		@Override
		public void run() {
			adapter.notifyDataSetChanged();
		}
    	
    };
	private class ItemOcl implements AdapterView.OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> adapter, View view, int position,
				long arg3) {
		
			Map<String, ?> selectedItem = lstfriends.get(position);
			int firendid=(Integer) selectedItem.get("mid");
			Intent intent=new Intent();
			intent.setClass(FriendsActivity.this,TalkActivity.class);
			intent.putExtra("friendid", firendid);
			System.out.println(firendid);
			DataOutputStream dos=Data.getDos();
			try {
				dos.writeInt(Config.RESULT_UPDATE_HEAD);
				dos.writeInt(firendid);
				dos.flush();//更改用户状态
				dos.writeInt(Config.REQUEST_GET_OFFLINE_MSG);//获取离线消息
				dos.flush();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			startActivity(intent);

		}
		
	}
	

}
