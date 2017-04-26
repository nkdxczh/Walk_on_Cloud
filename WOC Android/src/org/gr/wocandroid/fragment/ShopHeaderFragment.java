package org.gr.wocandroid.fragment;

import org.gr.wocandroid.activity.MainActivity;
import org.gr.wocandroid.activity.R;
import org.gr.wocandroid.activity.SearchResultActivity;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

@SuppressLint("NewApi")
public class ShopHeaderFragment extends Fragment {

	private Spinner selectshop;
	private EditText searchcontent;
	private RadioGroup searchtype;
	private Button searchbutton;
	int option;
	String key;
	private View view;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.frag_header, container, false);
		this.selectshop=(Spinner) view.findViewById(R.id.shopselect);
		this.searchbutton=(Button) view.findViewById(R.id.searchbutton);
		this.searchcontent=(EditText) view.findViewById(R.id.searchcontent);
		this.searchtype=(RadioGroup) view.findViewById(R.id.seachtype);
		ArrayAdapter<String>adpter=new ArrayAdapter<String>(getActivity(), R.layout.spinner_view);
		adpter.add("商城首页");
		adpter.add("交易商品");
		adpter.add("交换商品");
		adpter.add("漂流物品");
		adpter.setDropDownViewResource(R.layout.spinner_view);
		selectshop.setAdapter(adpter);
		selectshop.setOnItemSelectedListener(new SpinnerListener());
		searchtype.setOnCheckedChangeListener(new CheckListener());
		searchbutton.setOnClickListener(new BtnClk());
		return view;
	}
	class SpinnerListener implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			MainActivity s=(MainActivity) getActivity();
			switch(arg2){
			case 0:
				s.setNewFragment(new ShopBodyFragment());
				break;
			case 1:
				s.key=10;s.sort=1;
				s.setNewFragment(new ShopTypeFragment());
				break;
			case 2:
				s.key=11;s.sort=1;
				s.setNewFragment(new ShopTypeFragment());
				break;
			case 3:
				s.key=12;s.sort=1;
				s.setNewFragment(new ShopTypeFragment());
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
			Intent intent=new Intent();
			intent.putExtra("option", option);
			key=searchcontent.getText().toString();
			intent.putExtra("key", key);
			intent.setClass(ShopHeaderFragment.this.getActivity(), SearchResultActivity.class);
			startActivity(intent);
		}
	}
	class CheckListener implements OnCheckedChangeListener{

		@Override
		public void onCheckedChanged(RadioGroup arg0, int arg1) {
			// TODO Auto-generated method stub
			switch(arg0.getCheckedRadioButtonId()){
			case R.id.keyword:
				option=1;break;
			case R.id.location:
				option=2;break;
			case R.id.type:
				option=3;break;
			case R.id.seller:
				option=4;break;
			}
		}
		
	}
	
}
