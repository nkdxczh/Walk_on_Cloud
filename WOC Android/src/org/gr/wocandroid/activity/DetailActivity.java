package org.gr.wocandroid.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.gr.wocandroid.internet.WebAccessUtils;
import org.gr.wocandroid.vo.Commodities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends Activity {

	private ImageView img_detail;
	private TextView comname_detail, descibe_detail, price_detail,
	seller_detail, location_detail;
	Button buy_detail, sellerinfo_detail;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_detail);
	//	this.setContentView(R.layout.activity_main);
		img_detail=(ImageView) findViewById(R.id.img_detail);
		comname_detail=(TextView) findViewById(R.id.comname_detail);
		descibe_detail=(TextView) findViewById(R.id.descibe_detail);
		price_detail=(TextView) findViewById(R.id.price_detail);
		seller_detail=(TextView) findViewById(R.id.seller_detail);
		location_detail=(TextView) findViewById(R.id.location_detail);
		sellerinfo_detail=(Button) findViewById(R.id.sellerinfo_detail);
		buy_detail=(Button) findViewById(R.id.buy_detail);
		sellerinfo_detail.setOnClickListener(new ItemOcl());
		buy_detail.setOnClickListener(new ItemOcl());
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		List<NameValuePair> lstNameValuePairs = new ArrayList<NameValuePair>();
		lstNameValuePairs.add(new BasicNameValuePair("comId", ""+getIntent().getIntExtra("comId", 0)));
		String response=WebAccessUtils.httpRequest("ShowCommodityInfoServletA", lstNameValuePairs);
		Commodities commodity=gson.fromJson(response, Commodities.class);
		comname_detail.setText(commodity.getComName());
		descibe_detail.setText(commodity.getDescribe());
		price_detail.setText(""+commodity.getPrice());
		seller_detail.setText(commodity.getOwnerName());
		location_detail.setText(commodity.getComRegion());
	}

	class ItemOcl implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch(arg0.getId()){
			case R.id.buy_detail:
				Intent intent = new Intent();
				intent.putExtra("comId", DetailActivity.this.getIntent().getIntExtra("comId", 0));
				intent.setClass(DetailActivity.this,
						OrderActivity.class);
				startActivity(intent);
			}
		}
		
	}
}
