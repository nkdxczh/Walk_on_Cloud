package com.example.client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.gr.wocandroid.activity.R;
import org.gr.wocandroid.activity.UserManager;

import com.example.data.ChatMsgViewAdapter;
import com.example.data.Config;
import com.example.data.Data;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class TalkActivity extends Activity {
	//private static String IpAddress = "10.0.2.2";
	//private static int Port = 10000;
	private Button mBtnSend;
	private static final String LOG_TAG = "Chat";
	private Button mBtnBack;
	private Button mBtnRecord;
	private EditText mEditTextContent;
	private ListView mListView;
	private ImageButton imageButton;
	private ChatMsgViewAdapter mAdapter;
	int k=-1;
	
	//Socket socket = null;
	DataOutputStream dos=Data.getDos();
	DataInputStream dis=Data.getDis();
	boolean b = false;
	boolean t = false;
	String m;
	private String FileName=null;
	int userid;
	String usernickname="233";
	int oldsize=0;	
	int friendsid;
	Handler handler=null;
	boolean isafterrecored=false;
	private MediaPlayer mPlayer = null;
	private MediaRecorder mRecorder = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		System.out.println(savedInstanceState);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat_xiaohei);
		mListView = (ListView) findViewById(R.id.listview);
    	mBtnSend = (Button) findViewById(R.id.btn_send);
    	mBtnBack = (Button) findViewById(R.id.btn_back);  	
    	mBtnRecord = (Button) findViewById(R.id.btn_record);
    	mEditTextContent = (EditText) findViewById(R.id.et_sendmessage);
		imageButton = (ImageButton) findViewById(R.id.right_btn);
		userid = UserManager.getUserId();
		friendsid = getIntent().getIntExtra("friendid", 0);
		Data.recordStatus=-1;
		handler = new Handler();
		FileName = Environment.getExternalStorageDirectory().getAbsolutePath();
		if(FileName==null)
			System.out.println("SD涓嶅瓨鍦�");
		FileName += "/audiorecordtest.3gp";
		mAdapter = new ChatMsgViewAdapter(this, Data.getmDataArrays(friendsid));
		mListView.setAdapter(mAdapter);

		mBtnSend.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				b = true;
				t = true;
				m=mEditTextContent.getText().toString();
				mEditTextContent.setText("");
			}
		});
		mBtnBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					dos.writeInt(Config.RETRUN_FRIENDS);
					dos.flush();
					Data.getLstFriends().clear();
					Intent intent=new Intent();
					intent.setClass(TalkActivity.this,FriendsActivity.class);
					startActivity(intent);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		mBtnRecord.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if (Data.recordStatus == -1) {
					// TODO Auto-generated method stub
					new Thread() {
						@Override
						public void run() {
							mRecorder = new MediaRecorder();
							mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
							mRecorder
									.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
							
							mRecorder
									.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
							mRecorder.setOutputFile(FileName);
							isafterrecored=true;
							try {
								mRecorder.prepare();
							} catch (IOException e) {
								Log.e(LOG_TAG, "prepare() failed");
							}
							mRecorder.start();
							Data.recordStatus = Config.START_RECORD;
							
							handler.post(new MyRunnuble("寮�褰曢煶"));
	
						}
					}.start();	
				}
				if(Data.recordStatus==Config.START_RECORD)
				{
					Data.recordStatus=Config.STOP_RECORD;
					mRecorder.stop();
				     mRecorder.release();
				     mRecorder = null;
				     k=0;
				     Toast.makeText(getApplicationContext(), "缁撴潫褰曢煶", Toast.LENGTH_LONG).show();
				     
				}
				if(Data.recordStatus==Config.PLAY_RECORD)
				{
					mPlayer = new MediaPlayer();
					try{
						mPlayer.setDataSource(FileName);
						mPlayer.prepare();
						mPlayer.start();
						Data.recordStatus=-1;
					}catch(IOException e){
						Log.e(LOG_TAG,"鎾斁澶辫触");
					}
				}
			}
		});
		imageButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					dos.writeInt(Config.RETRUN_FRIENDS);
					dos.flush();
					Data.getLstFriends().clear();
					Intent intent=new Intent();
					intent.setClass(TalkActivity.this,FriendsActivity.class);
					startActivity(intent);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		new Thread() {
			@Override
			public void run() {
				while (true) {			
					try {
						if (b == true) {
							if (k==0)//鍙戦�褰曢煶
							{
								k=-1;
								dos.writeInt(Config.MESSAGE_TYPE_AUDIO);
								dos.writeInt(friendsid);
								dos.flush();
								 DataInputStream ddis=new DataInputStream(new FileInputStream(FileName));
							        int length=0;
							        int totalNum=0;
							        byte[] buffer=new byte[1024];
							       // Log.i(TAG, "img.avaliable="+ddis.available());
							        
							        while((length=ddis.read(buffer))!=-1){
							            totalNum+=length;
							            dos.writeInt(length);
							            dos.write(buffer, 0, length);
							            dos.flush();
							        }
							        
							        dos.writeInt(0);
							        dos.flush();
								if (ddis != null) {
									ddis.close();
									ddis = null;
								}
								Data.recordStatus=-1;
								b=false;
								Thread.sleep(500);

							} else {
								if(m.length()==0)
								{
									b=false;
									//continue;
									Thread.sleep(500);
								}
								else
								{
									
									System.out.println("["+m+"]123");
								//	if(isafterrecored==true)
								//		dos.writeInt(Config.END_OF_FILE);
									isafterrecored=false;
								dos.writeInt(Config.MESSAGE_TYPE_TXT);
								dos.writeInt(friendsid);
								dos.writeUTF(m);
								dos.flush();
								Data.putData(usernickname, m, false, friendsid);
								b = false;
								Thread.sleep(500);
								}
							}
						}
						
						
					} catch (IOException | InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					
				}
			}
			// handler.sendEmptyMessage(0);
		}.start();

		new Thread() {
			@Override
			public void run() {
				while (true) {
					if(Data.getmDataArrays(friendsid).size()!=oldsize)
					{
						//adapter.notifyDataSetChanged();
						handler.post(runnableUi);
						oldsize=Data.getmDataArrays(friendsid).size();
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}

				// handler.sendEmptyMessage(0);
			}
		}.start();

	}
	Runnable   runnableUi=new  Runnable(){
		@Override
		public void run() {
			//鏇存柊瀵硅瘽妗�
			mAdapter.notifyDataSetChanged();
		}
    	
    };

	



	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	
class MyRunnuble implements Runnable{
		
		String content;
		public MyRunnuble(String content) {
			super();
			this.content=content;
			// TODO Auto-generated constructor stub
		}

		@Override
		public void run() {
		// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), content, Toast.LENGTH_LONG).show();
		
		}
	}
	

}
