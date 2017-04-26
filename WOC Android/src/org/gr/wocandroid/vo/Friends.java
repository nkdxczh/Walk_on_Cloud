package org.gr.wocandroid.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Friends implements Serializable {
	private int userId;
	private int friendId;
	private String friendName;
	private int friendLevel;
	private int friendStatus;
	private String location;
	private String phone;
	private String postCode;
	private String email;
	private String gender;
	private String hobby;
	private String major;
	private int score;
	private String nickName;
	private String friendRegion;
	private String friendPhoto;
	public Friends() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getFriendId() {
		return friendId;
	}
	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}
	public String getFriendName() {
		return friendName;
	}
	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}
	public int getFriendLevel() {
		return friendLevel;
	}
	public void setFriendLevel(int friendLevel) {
		this.friendLevel = friendLevel;
	}
	public int getFriendStatus() {
		return friendStatus;
	}
	public void setFriendStatus(int friendStatus) {
		this.friendStatus = friendStatus;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getFriendRegion() {
		return friendRegion;
	}
	public void setFriendRegion(String friendRegion) {
		this.friendRegion = friendRegion;
	}
	public String getFriendPhoto() {
		return friendPhoto;
	}
	public void setFriendPhoto(String friendPhoto) {
		this.friendPhoto = friendPhoto;
	}
	@Override
	public String toString() {
		return "Friends [userId=" + userId + ", friendId=" + friendId
				+ ", friendName=" + friendName + ", friendLevel=" + friendLevel
				+ ", friendStatus=" + friendStatus + ", location=" + location
				+ ", phone=" + phone + ", postCode=" + postCode + ", email="
				+ email + ", gender=" + gender + ", hobby=" + hobby
				+ ", major=" + major + ", score=" + score + ", nickName="
				+ nickName + ", friendRegion=" + friendRegion
				+ ", friendPhoto=" + friendPhoto + "]";
	}


}
