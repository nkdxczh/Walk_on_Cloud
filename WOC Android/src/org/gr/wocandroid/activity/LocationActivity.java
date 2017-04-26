package org.gr.wocandroid.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Window;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.platform.comapi.basestruct.GeoPoint;

@SuppressLint("NewApi")
public class LocationActivity extends Activity {
	BMapManager  mBMapMan= null;
	MapView mMapView = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mBMapMan=new BMapManager(getApplicationContext());
		mBMapMan.init("hVGR8GhcGBPPjtfY8ELuYElv", null);  
		setContentView(R.layout.function_location);		
		mMapView=(MapView)findViewById(R.id.function_location);
		mMapView.setBuiltInZoomControls(true);
		MapController mMapController=mMapView.getController();
		GeoPoint point =new GeoPoint((int)(39.915* 1E6),(int)(116.404* 1E6));
		mMapController.setCenter(point);
		mMapController.setZoom(12);

	}
	@Override
	protected void onDestroy(){
	        mMapView.destroy();
	        if(mBMapMan!=null){
	                mBMapMan.destroy();
	                mBMapMan=null;
	        }
	        super.onDestroy();
	}
	@Override
	protected void onPause(){
	        mMapView.onPause();
	        if(mBMapMan!=null){
	               mBMapMan.stop();
	        }
	        super.onPause();
	}
	@Override
	protected void onResume(){
	        mMapView.onResume();
	        if(mBMapMan!=null){
	                mBMapMan.start();
	        }
	       super.onResume();

	}

}
