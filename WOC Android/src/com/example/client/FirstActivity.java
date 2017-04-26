package com.example.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.gr.wocandroid.activity.R;
import org.gr.wocandroid.activity.UserManager;

import com.example.data.Config;
import com.example.data.Data;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends Activity {
	private Button button;
	private Socket socket=null;
	DataOutputStream dos=null;
	DataInputStream dis=null;
	Handler handler;
	String FileName=null;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		button=(Button) findViewById(R.id.button1);
		button.setOnClickListener(new MyListner());
		 handler=new Handler();
		 FileName = Environment.getExternalStorageDirectory().getAbsolutePath();
			if(FileName==null)
				System.out.println("SD鍗℃病浜�");
			FileName += "/audiorecordtest.3gp";
		//杩炴帴socket
		new Thread() {
			public void run() {
				try {
					Data.setSocket(new Socket(Data.getIpAddress(),Data.getPort()));
					socket=Data.getSocket();
					Data.setDos(new DataOutputStream(new BufferedOutputStream(
							socket.getOutputStream())));
					//dos = new DataOutputStream(new BufferedOutputStream(
						//	socket.getOutputStream()));
					Data.setDis(new DataInputStream(new BufferedInputStream(
							socket.getInputStream())));
					//dis = new DataInputStream(new BufferedInputStream(
						//	socket.getInputStream()));
					dos=Data.getDos();
					dis=Data.getDis();
					dos.writeInt(Config.REQUEST_LOGIN);
					int userid=UserManager.getUserId();
					dos.writeInt(userid);
					dos.flush();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
		}.start();
		
		
		
		//鏂扮嚎绋嬫帴鏀舵秷鎭�
		new Thread() {
			@Override
			public void run() {
				while (true) {
					if (dis!=null) {
						try {
							int message=dis.readInt();
							if(Config.RESULT_GET_FRIENDS==message)
							{
								int size=dis.readInt();
								for(int i=0;i<size;i++)
								{
									int friendsid=dis.readInt();
									String nickname=dis.readUTF();
									String content=dis.readUTF();
									Data.putfriends(nickname, content,friendsid);
								}
							}
							if(Config.MESSAGE_TYPE_TXT==message)
							{
								int friendid=dis.readInt();
								String frendnickname=dis.readUTF();
								String content=dis.readUTF();
								Data.putData(frendnickname, content,true,friendid);
								System.out.println("鍙楀埌鏂版秷鎭�");
							}
							if(Config.HAVA_NEW_MESSAGE==message)
							{
								int friedid=dis.readInt();
								String nickname=dis.readUTF();
								System.out.println("浣犳湁涓�釜鏉ヨ嚜"+nickname+"鐨勬柊娑堟伅");
								String content="浣犳湁涓�釜鏉ヨ嚜"+nickname+"鐨勬柊娑堟伅";
								handler.post(new MyRunnuble(content));
							}
							if(Config.RESULT_GET_OFFLINE_MSG==message)
							{
								int size=dis.readInt();
								for(int i=0;i<size;i++)
								{
									int fid=dis.readInt();
									String friendnickname=dis.readUTF();
									String date=dis.readUTF();
									String content=dis.readUTF();
									Data.putData(friendnickname, content, true,date,fid);
								}
							}
							if(Config.MESSAGE_TYPE_AUDIO==message)
							{
								int frinendid=dis.readInt();
								DataOutputStream ddos=new DataOutputStream(new FileOutputStream(FileName));   
						        int length=0;
						        int totalNum=0;
						        byte[] buffer=new byte[2048];
						        while((length=dis.readInt())!=0){
						            length=dis.read(buffer, 0, length);
						            totalNum+=length;
						            ddos.write(buffer, 0, length);
						            ddos.flush();
						        }
						        
						        if(ddos!=null){
						            try {
						                ddos.close();
						                ddos=null;
						            } catch (IOException e) {
						                e.printStackTrace();
						            }
						        }
								 Data.recordStatus=Config.PLAY_RECORD;
								 handler.post(new MyRunnuble("鎮ㄦ敹鍒颁竴涓闊虫秷鎭�"+frinendid));
							}
							Thread.sleep(500);
	
						} catch (IOException | InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				// handler.sendEmptyMessage(0);
			}
		}.start();
		
		
	}
	class MyListner implements OnClickListener
	{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			intent.setClass(FirstActivity.this,FriendsActivity.class);
			intent.putExtra("userid", UserManager.getUserId());
			System.out.println("鍑嗗璺冲埌濂藉弸椤甸潰");
			Data.getLstFriends().clear();
			startActivity(intent);
			
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
			Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
			r.play(); 
			Toast.makeText(getApplicationContext(), content, Toast.LENGTH_LONG).show();
			
		
		}
	}
	

}
