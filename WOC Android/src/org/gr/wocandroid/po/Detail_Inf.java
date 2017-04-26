package org.gr.wocandroid.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Detail_Inf implements Serializable {
private int infId;
private int userId;
private String location;
private String phone;
private int postCode;
private String email;
private String gender;
private String hobby;
private String major;
private int score;
private String nickName;
private String userRegion;
private String userPhoto;
public Detail_Inf() {
	super();
	// TODO Auto-generated constructor stub
}
public Detail_Inf(int infId, int userId, String location, String phone,
		int postCode, String email, String gender, String hobby, String major,
		int score, String nickName, String userRegion, String userPhoto) {
	super();
	this.infId = infId;
	this.userId = userId;
	this.location = location;
	this.phone = phone;
	this.postCode = postCode;
	this.email = email;
	this.gender = gender;
	this.hobby = hobby;
	this.major = major;
	this.score = score;
	this.nickName = nickName;
	this.userRegion = userRegion;
	this.userPhoto = userPhoto;
}
public int getInfId() {
	return infId;
}
public void setInfId(int infId) {
	this.infId = infId;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
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
public int getPostCode() {
	return postCode;
}
public void setPostCode(int postCode) {
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
public String getUserRegion() {
	return userRegion;
}
public void setUserRegion(String userRegion) {
	this.userRegion = userRegion;
}
public String getUserPhoto() {
	return userPhoto;
}
public void setUserPhoto(String userPhoto) {
	this.userPhoto = userPhoto;
}
@Override
public String toString() {
	return "Detail_Info [infId=" + infId + ", userId=" + userId + ", location="
			+ location + ", phone=" + phone + ", postCode=" + postCode
			+ ", email=" + email + ", gender=" + gender + ", hobby=" + hobby
			+ ", major=" + major + ", score=" + score + ", nickName="
			+ nickName + ", userRegion=" + userRegion + ", userPhoto="
			+ userPhoto + "]";
}




}
