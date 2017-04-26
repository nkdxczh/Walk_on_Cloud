package org.gr.wocandroid.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.gr.wocandroid.internet.WebAccessUtils;
import org.gr.wocandroid.po.Detail_Inf;
import org.gr.wocandroid.po.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ChangeDetailInformationActivity extends Activity {
	private EditText nickName;
	private EditText hobby;
	private EditText gender;
	private EditText major;
	private EditText location;
	private EditText phone;
	private EditText email;
	
	private Button change;
	private Button cancel;
	
	private List<Map<String, ?>> lstData;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			// 窗体绑定布局文件
			this.setContentView(R.layout.activity_main);
			this.setContentView(R.layout.change_detail_information);
			// 步骤2：实例化列表视图组
			this.nickName = (EditText) this.findViewById(R.id.change_nickname);
			this.hobby = (EditText) this.findViewById(R.id.change_hobby);
			this.gender = (EditText) this.findViewById(R.id.change_gender);
			this.major = (EditText) this.findViewById(R.id.change_major);
			this.phone = (EditText) this.findViewById(R.id.change_phone);
			this.location = (EditText) this.findViewById(R.id.change_location);
			this.email = (EditText) this.findViewById(R.id.change_email);
			this.cancel = (Button) this.findViewById(R.id.button_cancel_change_information);
			this.change = (Button) this.findViewById(R.id.button_change_detail_information);
			
			// 步骤3：获取自定义列表组件中的数据
			this.lstData = fetchData();
			nickName.setHint((CharSequence) lstData.get(0).get("nickName"));
			hobby.setHint((CharSequence) lstData.get(0).get("hobby"));
			gender.setHint((CharSequence) lstData.get(0).get("gender"));
			major.setHint((CharSequence) lstData.get(0).get("major"));
			phone.setHint((CharSequence) lstData.get(0).get("phone"));
			location.setHint((CharSequence) lstData.get(0).get("location"));
			email.setHint((CharSequence) lstData.get(0).get("email"));
			
			this.cancel.setOnClickListener(new ViewOnClickListener());
			this.change.setOnClickListener(new ViewOnClickListener());
		}

		
		private class ViewOnClickListener implements View.OnClickListener{
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.button_change_detail_information:
					// 1. 获取账号和密码
					String sNickName = nickName.getText().toString().trim();
					String sHobby = hobby.getText().toString().trim();
					String sGender=gender.getText().toString().trim();
					String sMajor = major.getText().toString().trim();
					String sPhone = phone.getText().toString().trim();
					String sLocation=location.getText().toString().trim();
					String sEmail = email.getText().toString().trim();
					// 2. 判断是否正确
					Detail_Inf detail_Inf=new Detail_Inf();
					detail_Inf.setUserId(UserManager.getUserId());
					detail_Inf.setNickName(sNickName);
					detail_Inf.setGender(sGender);
					detail_Inf.setHobby(sHobby);
					detail_Inf.setMajor(sMajor);
					detail_Inf.setEmail(sEmail);
					detail_Inf.setLocation(sLocation);
					detail_Inf.setPhone(sPhone);
					detail_Inf.setLocation(sLocation);
							
					// 步骤1-3：序列化
					Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-ddhh:mm:ss").create();
					String information_data = gson.toJson(detail_Inf);
					
					// 步骤2：设置请求参数集合并调用方法向网络发送请求数据			
					
					// 步骤2-1：创建一个参数集合
					List<NameValuePair> lstNameValuePairs = new ArrayList<NameValuePair>();
					lstNameValuePairs.add(new BasicNameValuePair("information_data", information_data));
					
					// 步骤2-2：调用方式实现请求的发送
					String response = WebAccessUtils.httpRequest("AUpdateInfServlet", lstNameValuePairs);				
					
					// 步骤3：处理JSON数据
					// 步骤3-1：反序列化数据封装成一个对象
					Detail_Inf detailInformation = null;
					detailInformation = gson.fromJson(response, Detail_Inf.class);
					
					if(detailInformation!= null){
						Toast.makeText(getApplicationContext(), "成功", Toast.LENGTH_LONG).show();
						Intent intent = new Intent();
						intent.setClass(ChangeDetailInformationActivity.this, PersonDetailInformationActivity.class);
						startActivity(intent);
					}else{
						Toast.makeText(getApplicationContext(), "修改失败", Toast.LENGTH_LONG).show();
					}
					
					break;
				case R.id.button_cancel_change_information:
					Intent intent = new Intent();
					intent.setClass(ChangeDetailInformationActivity.this, InformationActivity.class);
					startActivity(intent);			
					break;
				default:
					break;
				}
			}
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
					.httpRequest("AShowUserInformationServlet",lstNameValuePairs).trim();

			// 步骤4-3：设置一个全新的类型Type
			Type ListInformation = new TypeToken<ArrayList<Detail_Inf>>() {
			}.getType();
			// 步骤4-4：创建并实例化一个Gson对象

			// 步骤4-5：解析JSon数据
			List<Detail_Inf> lstInformation = gson.fromJson(response,
					ListInformation);

			// 步骤4-6：使用循环遍历集合对象
			for (Detail_Inf detail_inf : lstInformation) {
				Map<String, Object> item = new HashMap<String, Object>();
				item.put("nickName", detail_inf.getNickName());
				item.put("score", detail_inf.getScore());
				item.put("hobby", detail_inf.getHobby());
				item.put("gender", detail_inf.getGender());
				item.put("major", detail_inf.getMajor());
				item.put("location", detail_inf.getLocation());
				item.put("phone", detail_inf.getPhone());
				item.put("email", detail_inf.getEmail());
				// 步骤4-7：将创建好的选项对象添加到集合中
				lstData.add(item);
			}
			return lstData;
		}

}



