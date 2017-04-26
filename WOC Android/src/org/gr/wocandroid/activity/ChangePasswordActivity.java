package org.gr.wocandroid.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.gr.wocandroid.internet.WebAccessUtils;
import org.gr.wocandroid.po.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ChangePasswordActivity extends Activity {
	private TextView private_password;
	private TextView new_password;
	private TextView confirm_password;
	private Button change_password;
	private Button cancel;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			// 窗体绑定布局文件
			this.setContentView(R.layout.activity_main);
			this.setContentView(R.layout.change_password);
			// 步骤2：实例化列表视图组件
			this.private_password = (TextView) this.findViewById(R.id.private_password);
			this.new_password = (TextView) this.findViewById(R.id.new_password);
			this.confirm_password = (TextView) this.findViewById(R.id.confirm_password);
			this.change_password = (Button) this.findViewById(R.id.button_change_password);
			this.cancel = (Button) this.findViewById(R.id.button_cancel);
			this.cancel.setOnClickListener(new ViewOnClickListener());
			this.change_password.setOnClickListener(new ViewOnClickListener());
		}

		
		private class ViewOnClickListener implements View.OnClickListener{
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				switch (view.getId()) {
				case R.id.button_change_password:
					Toast.makeText(getApplicationContext(), "已选择修改密码", Toast.LENGTH_LONG).show();
					// 1. 获取账号和密码
					String sPrivatePassword = private_password.getText().toString().trim();
					String sNewPassword = new_password.getText().toString().trim();
					String sConfirmPassword=confirm_password.getText().toString().trim();
					// 2. 判断是否正确
					if(sNewPassword.equals(sConfirmPassword)){
						// 3. 响应动作
						Toast.makeText(getApplicationContext(), "已接受到数据", Toast.LENGTH_LONG).show();
						
						User user =new User();
						user.setUserId(27);
						user.setUserPassword(sNewPassword);
						// 步骤1-3：序列化
						Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-ddhh:mm:ss").create();
						String information_data = gson.toJson(user);
						
						// 步骤2：设置请求参数集合并调用方法向网络发送请求数据			
						
						// 步骤2-1：创建一个参数集合
						List<NameValuePair> lstNameValuePairs = new ArrayList<NameValuePair>();
						lstNameValuePairs.add(new BasicNameValuePair("information_data", information_data));
						
						// 步骤2-2：调用方式实现请求的发送
						String response = WebAccessUtils.httpRequest("AUpdateInfServlet", lstNameValuePairs);				
						
						// 步骤3：处理JSON数据
						// 步骤3-1：反序列化数据封装成一个对象
						User tUser = null;
						tUser = gson.fromJson(response, User.class);
						if(user!=null)
						{
							Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_LONG).show();
							Intent intent = new Intent();
							intent.setClass(ChangePasswordActivity.this, InformationActivity.class);
							startActivity(intent);
						}
					}else{
						// 3. 响应动作
						Toast.makeText(getApplicationContext(), "密码不一致", Toast.LENGTH_LONG).show();
					}		
					
					break;
				case R.id.button_cancel:
					Intent intent = new Intent();
					intent.setClass(ChangePasswordActivity.this, InformationActivity.class);
					startActivity(intent);
					
					break;
				default:
					break;
				}
			}
		}


}



