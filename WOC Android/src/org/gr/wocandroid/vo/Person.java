package org.gr.wocandroid.vo;

public class Person {

	private int userId;
	private String userName;
	private String nickName;
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Person(int userId, String userName) {
		super();
		this.userId = userId;
		this.userName = userName;
	}
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Person [userId=" + userId + ", userName=" + userName + "]";
	}
	
}
