package org.gr.wocandroid.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.gr.wocandroid.internet.WebAccessUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class OrderActivity extends Activity {

	private EditText name_order, location_order, phone_order, email_order;
	private Button order_order;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_order);
		name_order=(EditText) findViewById(R.id.name_order);
		location_order=(EditText) findViewById(R.id.location_order);
		phone_order=(EditText) findViewById(R.id.phone_order);
		email_order=(EditText) findViewById(R.id.email_order);
		order_order=(Button) findViewById(R.id.order_order);
		order_order.setOnClickListener(new BtnClk());
	}
	class BtnClk implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
			List<NameValuePair> lstNameValuePairs = new ArrayList<NameValuePair>();
			lstNameValuePairs.add(new BasicNameValuePair("userId",""+ UserManager.getUserId()));
			lstNameValuePairs.add(new BasicNameValuePair("comId",""+OrderActivity.this.getIntent().getIntExtra("comId", 0)));
			lstNameValuePairs.add(new BasicNameValuePair("name",name_order.getText().toString()));
			lstNameValuePairs.add(new BasicNameValuePair("location",location_order.getText().toString()));
			lstNameValuePairs.add(new BasicNameValuePair("email",email_order.getText().toString()));
			lstNameValuePairs.add(new BasicNameValuePair("phone",phone_order.getText().toString()));
			String response=WebAccessUtils.httpRequest("ApplyOrderServletA", lstNameValuePairs);
			if(response.trim().equals("suc")){
				OrderActivity.this.finish();
			}
		}
	}
	
}
