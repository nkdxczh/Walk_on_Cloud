package org.gr.wocandroid.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.gr.wocandroid.internet.WebAccessUtils;
import org.gr.wocandroid.po.User;
import org.gr.wocandroid.vo.Friends;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class PersonFriendsInformationActivity extends Activity {
	// 步骤1：声明集合对象以及ListView组件对象
		private List<Map<String, ?>> lstData;
		private ListView lstInformation;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			this.setContentView(R.layout.activity_main);
			// 窗体绑定布局文件
			this.setContentView(R.layout.listview_friends);
			// 步骤2：实例化列表视图组件
			this.lstInformation = (ListView) this.findViewById(R.id.lstFriends);
			// 步骤3：获取自定义列表组件中的数据
			this.lstData = fetchData();
			Intent intent = new Intent();
			if(lstData.size()==0)
			{
				intent.setClass(PersonFriendsInformationActivity.this, InformationActivity.class);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "您的好友信息为空", Toast.LENGTH_SHORT).show();
			}
			// 步骤4：将自定义的布局与获取到的列表数据进行绑定
			SimpleAdapter adapter = new SimpleAdapter(this, this.lstData,
					R.layout.listitem_friends, new String[] {
							"friend_nickName", "friend_account" }, new int[] {
							R.id.friend_nickname, R.id.friend_account});
			// 步骤5：列表组件绑定适配器
			this.lstInformation.setAdapter(adapter);
		}

		// 步骤4：自定义一个获取列表数据的方法
		private List<Map<String, ?>> fetchData() {
			// TODO Auto-generated method stub
			User user = new User();
			user.setUserId(UserManager.getUserId());
			// 步骤1-3：序列化
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-ddhh:mm:ss").create();
			String sUser = gson.toJson(user);
			
			// 步骤2：设置请求参数集合并调用方法向网络发送请求数据			
			
			// 步骤2-1：创建一个参数集合
			List<NameValuePair> lstNameValuePairs = new ArrayList<NameValuePair>();
			lstNameValuePairs.add(new BasicNameValuePair("user", sUser));
			// 步骤4-1：创建一个空集合对象
			List<Map<String, ?>> lstData = new ArrayList<Map<String, ?>>();

			// 步骤4-2：调用方法实现对网络服务的请求
			String response = WebAccessUtils
					.httpRequest("AShowFriendsInfServlet",lstNameValuePairs).trim();

			// 步骤4-3：设置一个全新的类型Type
			Type ListInformation = new TypeToken<ArrayList<Friends>>() {
			}.getType();
			// 步骤4-4：创建并实例化一个Gson对象

			// 步骤4-5：解析JSon数据
			List<Friends> lstInformation = gson.fromJson(response,
					ListInformation);

			// 步骤4-6：使用循环遍历集合对象
			for (Friends friend : lstInformation) {
				Map<String, Object> item = new HashMap<String, Object>();
				item.put("friend_nickName",friend.getNickName());
				item.put("friend_account", friend.getFriendName());
				// 步骤4-7：将创建好的选项对象添加到集合中
				lstData.add(item);
			}
			return lstData;
		}
}