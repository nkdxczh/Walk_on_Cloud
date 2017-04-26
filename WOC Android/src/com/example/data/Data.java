package com.example.data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gr.wocandroid.activity.R;
import org.gr.wocandroid.internet.InternetConfig;



public class Data {
	public static int recordStatus=-1;
	private static Socket socket=null;
	private static String IpAddress = InternetConfig.IP;
	private static int Port = 10000;
	private static DataOutputStream dos=null;
	private static DataInputStream dis=null;
	
	//private static List<Map<String, ?>> lstData=new ArrayList<Map<String, ?>>();
	private  static Map<Integer, List<ChatMsgEntity> > mDataArrays = new HashMap < Integer,List<ChatMsgEntity> >();
	private static List<Map<String, ?>> lstFriends=new ArrayList<Map<String, ?>>();
	public static Socket getSocket() {
		return socket;
	}
	public static void setSocket(Socket socket) {
		Data.socket = socket;
	}
	public static String getIpAddress() {
		return IpAddress;
	}
	public static void setIpAddress(String ipAddress) {
		IpAddress = ipAddress;
	}
	public static int getPort() {
		return Port;
	}
	public static void setPort(int port) {
		Port = port;
	}
	public static DataOutputStream getDos() {
		return dos;
	}
	public static void setDos(DataOutputStream dos) {
		Data.dos = dos;
	}
	public static DataInputStream getDis() {
		return dis;
	}
	public static void setDis(DataInputStream dis) {
		Data.dis = dis;
	}
	
	public static List<ChatMsgEntity> getmDataArrays(int i) {
		List<ChatMsgEntity> l= mDataArrays.get(i);
		if(l==null)
		{
			mDataArrays.put(i, new ArrayList<ChatMsgEntity>());
			return mDataArrays.get(i);
		}
		else
			return l;
		
	}
	
	public static List<Map<String, ?>> getLstFriends() {
		return lstFriends;
	}
	public static void setLstFriends(List<Map<String, ?>> lstFriends) {
		Data.lstFriends = lstFriends;
	}
	public static void  putData(String nickname,String content,boolean isComMsg,String data,int i) {
		// TODO Auto-generated method stub
		// 锟斤拷锟斤拷4-1锟斤拷锟斤拷锟斤拷一锟斤拷锟秸硷拷锟较讹拷锟斤拷
		//HashMap<String, Object> map = new HashMap<String, Object>();
		// 锟斤拷锟斤拷4-2锟斤拷锟斤拷锟斤拷一锟斤拷锟叫憋拷锟斤拷选锟斤拷锟斤拷锟绞碉拷锟�
		ChatMsgEntity item01=new ChatMsgEntity();
		item01.setDate(data);
		item01.setName(nickname);
		item01.setText(content);
		item01.setMsgType(isComMsg);
		List<ChatMsgEntity> l=mDataArrays.get(i);
		if(l==null)
			mDataArrays.put(i, new ArrayList<ChatMsgEntity>());
		else
			mDataArrays.get(i).add(item01);	
		// 锟斤拷锟斤拷4-3锟斤拷锟斤拷锟斤拷锟斤拷锟矫碉拷选锟斤拷锟斤拷锟斤拷锟接碉拷锟斤拷锟斤拷锟斤拷
	}
	
	public static void  putData(String nickname,String content,boolean isComMsg,int i)
	{
		putData(nickname, content, isComMsg, getDate(),i);
	}
	
	public static void  putfriends(String nickname,String content,int friendid) {
		// TODO Auto-generated method stub
		// 锟斤拷锟斤拷4-1锟斤拷锟斤拷锟斤拷一锟斤拷锟秸硷拷锟较讹拷锟斤拷
		//HashMap<String, Object> map = new HashMap<String, Object>();
		// 锟斤拷锟斤拷4-2锟斤拷锟斤拷锟斤拷一锟斤拷锟叫憋拷锟斤拷选锟斤拷锟斤拷锟绞碉拷锟�
		Map<String, Object> item01 = new HashMap<String, Object>();
		item01.put("mid", friendid);
		item01.put("friendPhoto", R.drawable.p2);
		item01.put("friendNickName", nickname);
		item01.put("friendPublish", "2014-8-24");
		item01.put("friendMessage", content);

		// 锟斤拷锟斤拷4-3锟斤拷锟斤拷锟斤拷锟斤拷锟矫碉拷选锟斤拷锟斤拷锟斤拷锟接碉拷锟斤拷锟斤拷锟斤拷
		lstFriends.add(item01);

	}
	  private static String getDate() {
	        Calendar c = Calendar.getInstance();

	        String year = String.valueOf(c.get(Calendar.YEAR));
	        String month = String.valueOf(c.get(Calendar.MONTH));
	        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + 1);
	        String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
	        String mins = String.valueOf(c.get(Calendar.MINUTE));
	        
	        
	        StringBuffer sbBuffer = new StringBuffer();
	        sbBuffer.append(year + "-" + month + "-" + day + " " + hour + ":" + mins); 
	        						
	        						
	        return sbBuffer.toString();
	    }

}
