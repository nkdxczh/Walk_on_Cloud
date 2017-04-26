package org.gr.wocandroid.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.gr.wocandroid.internet.WebAccessUtils;
import org.gr.wocandroid.po.User;
import org.gr.wocandroid.vo.Person;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private EditText username, password;
	private Button loginbutton, newuserbutton;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.activity_login);
		this.username = (EditText) findViewById(R.id.username);
		this.password = (EditText) findViewById(R.id.password);
		this.loginbutton = (Button) findViewById(R.id.loginbutton);
		this.newuserbutton = (Button) findViewById(R.id.newuserbutton);
		this.loginbutton.setOnClickListener(new ViewOcl());
		this.newuserbutton.setOnClickListener(new ViewOcl());
		
		/*Button upload=(Button)findViewById(R.id.upload);
		upload.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				WebAccessUtils.uploadFile("1");
			}
		});*/
	}

	private class ViewOcl implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.loginbutton:
				String uname = username.getText().toString();
				String pwd = password.getText().toString();
				if (uname.length() == 0)
					Toast.makeText(getApplicationContext(), "用户名不能为空",
							Toast.LENGTH_LONG).show();
				else if (pwd.length() == 0)
					Toast.makeText(getApplicationContext(), "密码不能为空",
							Toast.LENGTH_LONG).show();
				else {
					// Do some thing.
					User user = new User();
					user.setUserName(uname);
					user.setUserPassword(pwd);
					Gson gson = new GsonBuilder().setDateFormat(
							"yyyy-MM-ddhh:mm:ss").create();
					String user_data = gson.toJson(user);
					List<NameValuePair> lstNameValuePairs = new ArrayList<NameValuePair>();
					lstNameValuePairs.add(new BasicNameValuePair("user",
							user_data));
					String response = WebAccessUtils.httpRequest(
							"LoginServletA", lstNameValuePairs);
					if (response.trim().equals("failure")) {
						System.out.println("i am here");
						Toast.makeText(getApplicationContext(), "账号或密码错误!",
								Toast.LENGTH_LONG).show();
					} else {
						Person person = gson.fromJson(response, Person.class);
						if (person != null) {
							UserManager.setUserId(person.getUserId());
							UserManager.setUserName(person.getUserName());
							UserManager.setNickName(person.getNickName());
							Intent intent = new Intent();
							intent.setClass(LoginActivity.this,
									MainActivity.class);
							startActivity(intent);
						}
					}
				}
				break;
			case R.id.newuserbutton:
				// 跳转到注册界面，待实现
				Intent intent = new Intent();
				intent.setClass(LoginActivity.this, RegistActivity.class);
				startActivity(intent);
				break;
			}
		}

	}
}
