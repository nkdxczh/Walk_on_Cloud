package org.gr.wocandroid.activity;

import org.gr.wocandroid.po.Detail_Inf;
import org.gr.wocandroid.po.User;

public class UserManager {

	private static int userId;
	private static String userName;
	private static String nickName;
	public static String getNickName() {
		return nickName;
	}
	public static void setNickName(String nickName) {
		UserManager.nickName = nickName;
	}
	public static int getUserId() {
		return userId;
	}
	public static void setUserId(int userId) {
		UserManager.userId = userId;
	}
	public static String getUserName() {
		return userName;
	}
	public static void setUserName(String userName) {
		UserManager.userName = userName;
	}
	
	
}
