package org.gr.wocandroid.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.gr.wocandroid.internet.WebAccessUtils;
import org.gr.wocandroid.po.Detail_Inf;
import org.gr.wocandroid.po.User;
import org.gr.wocandroid.vo.Person;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class RegistActivity extends Activity {

	private EditText regusername, regnickname, regpassword, 
					 reglocation, regpassagain;
	private RadioButton male, female;
	private RadioGroup regsex;
	private Button backbutton, regist;
	private String sex="男";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.activity_regist);
		this.regusername=(EditText) findViewById(R.id.regusername);
		this.regnickname=(EditText) findViewById(R.id.regnickname);
		this.regpassword=(EditText) findViewById(R.id.regpassword);
		this.regpassagain=(EditText) findViewById(R.id.regpassagain);
		this.reglocation=(EditText) findViewById(R.id.reglocation);
		this.male=(RadioButton) findViewById(R.id.male);
		this.female=(RadioButton) findViewById(R.id.female);
		this.regist=(Button) findViewById(R.id.regist);
		this.backbutton=(Button) findViewById(R.id.backbutton);
		this.regsex=(RadioGroup) findViewById(R.id.regsex);
		backbutton.setOnClickListener(new ViewOcl());
		regist.setOnClickListener(new ViewOcl());
		regsex.setOnCheckedChangeListener(new CheckListener());
	}
	private class ViewOcl implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.backbutton:
				RegistActivity.this.finish();
				break;
			case R.id.regist:
				//do into shop
				Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-ddhh:mm:ss").create();
				String username=regusername.getText().toString();
				String password=regpassword.getText().toString();
				String passagain=regpassagain.getText().toString();
				String nickname=regnickname.getText().toString();
				String location=reglocation.getText().toString();
				User user=new User();
				Detail_Inf detail=new Detail_Inf();
				user.setUserName(username);
				user.setUserPassword(password);
				detail.setNickName(nickname);
				detail.setGender(sex);
				detail.setLocation(location);
				if(username.equals("")||password.equals("")||!password.equals(passagain)||nickname.equals(""))
				{
					Toast.makeText(getApplicationContext(), "请完善信息!", Toast.LENGTH_LONG).show();
				}
				else{
					List<NameValuePair> lstNameValuePairs = new ArrayList<NameValuePair>();
					lstNameValuePairs.add(new BasicNameValuePair("user", gson.toJson(user)));
					lstNameValuePairs.add(new BasicNameValuePair("detail", gson.toJson(detail)));
					String response = WebAccessUtils.httpRequest("RegistServletA", lstNameValuePairs);
					if(response.trim().equals("yonghuchongfu")){
						Toast.makeText(getApplicationContext(), "换一个用户名吧", Toast.LENGTH_LONG).show();
					}else{
						Person person=gson.fromJson(response, Person.class);
						UserManager.setUserId(person.getUserId());
						UserManager.setUserName(person.getUserName());
						UserManager.setNickName(person.getNickName());
						Intent intent = new Intent();
						intent.setClass(RegistActivity.this,
								MainActivity.class);
						startActivity(intent);
					}
				}
				break;
			}
		}
		
	}
	class CheckListener implements OnCheckedChangeListener{

		@Override
		public void onCheckedChanged(RadioGroup arg0, int arg1) {
			// TODO Auto-generated method stub
			switch(arg0.getCheckedRadioButtonId()){
			case R.id.male:
				sex="男";break;
			case R.id.female:
				sex="女";break;
			}
		}
		
	}
	
}
