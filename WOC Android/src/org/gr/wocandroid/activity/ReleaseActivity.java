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
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class ReleaseActivity extends Activity {

	private EditText comname_release, location_release, price_release, 
	describe_release;
	private Spinner yongtu_release, leixing_release;
	private Button release_release;
	String yongtu;
	String leixing;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_release);
		comname_release=(EditText) findViewById(R.id.comname_release);
		location_release=(EditText) findViewById(R.id.location_release);
		price_release=(EditText) findViewById(R.id.price_release);
		describe_release=(EditText) findViewById(R.id.describe_release);
		yongtu_release=(Spinner) findViewById(R.id.yongtu_release);
		leixing_release=(Spinner) findViewById(R.id.leixing_release);
		release_release=(Button) findViewById(R.id.release_release);
		yongtu="1";
		leixing="1";
		ArrayAdapter<String>adpter1=new ArrayAdapter<String>(this, R.layout.spinner_release);
		adpter1.add("交易商品");
		adpter1.add("交换商品");
		adpter1.add("漂流物品");
		adpter1.setDropDownViewResource(R.layout.spinner_release);
		ArrayAdapter<String>adpter2=new ArrayAdapter<String>(this, R.layout.spinner_release);
		adpter2.add("书籍");
		adpter2.add("日用品");
		adpter2.add("其他");
		adpter2.setDropDownViewResource(R.layout.spinner_release);
		yongtu_release.setAdapter(adpter1);
		leixing_release.setAdapter(adpter2);
		yongtu_release.setOnItemSelectedListener(new SpinnerListenerYongtu());
		leixing_release.setOnItemSelectedListener(new SpinnerListenerLeixing());
		release_release.setOnClickListener(new BtnClk());
	}
	class SpinnerListenerYongtu implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			switch(arg2){
			case 0:
				yongtu="1";
				break;
			case 1:
				yongtu="2";
				break;
			case 2:
				yongtu="3";
				break;
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	class SpinnerListenerLeixing implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			switch(arg2){
			case 0:
				leixing="1";
				break;
			case 1:
				leixing="2";
				break;
			case 2:
				leixing="3";
				break;
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	class BtnClk implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
			List<NameValuePair> lstNameValuePairs = new ArrayList<NameValuePair>();
			lstNameValuePairs.add(new BasicNameValuePair("userId",""+ UserManager.getUserId()));
			lstNameValuePairs.add(new BasicNameValuePair("yongtu",yongtu));
			lstNameValuePairs.add(new BasicNameValuePair("leixing",leixing));
			lstNameValuePairs.add(new BasicNameValuePair("price",price_release.getText().toString()));
			lstNameValuePairs.add(new BasicNameValuePair("describe",describe_release.getText().toString()));
			lstNameValuePairs.add(new BasicNameValuePair("comRegion",location_release.getText().toString()));
			lstNameValuePairs.add(new BasicNameValuePair("comName", comname_release.getText().toString()));
			String response=WebAccessUtils.httpRequest("ReleaseCommodityServletA", lstNameValuePairs);
			if(response.trim().equals("suc")){
				ReleaseActivity.this.finish();
			}
		}
		
	}

}
