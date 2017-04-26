package org.gr.woc.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CComments implements Serializable{
private int comId;
private int userId;
private String userName;
private int userLevel;
private int userStatus;
private String comComment;
private Date releaseTime;
private String nickName;
public CComments() {
	super();
	// TODO Auto-generated constructor stub
}
public int getComId() {
	return comId;
}
public void setComId(int comId) {
	this.comId = comId;
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
public int getUserLevel() {
	return userLevel;
}
public void setUserLevel(int userLevel) {
	this.userLevel = userLevel;
}
public int getUserStatus() {
	return userStatus;
}
public void setUserStatus(int userStatus) {
	this.userStatus = userStatus;
}
public String getComComment() {
	return comComment;
}
public void setComComment(String comComment) {
	this.comComment = comComment;
}
public Date getReleaseTime() {
	return releaseTime;
}
public void setReleaseTime(Date releaseTime) {
	this.releaseTime = releaseTime;
}
public String getNickName() {
	return nickName;
}
public void setNickName(String nickName) {
	this.nickName = nickName;
}
@Override
public String toString() {
	return "CComments [comId=" + comId + ", userId=" + userId + ", userName="
			+ userName + ", userLevel=" + userLevel + ", userStatus="
			+ userStatus + ", comComment=" + comComment + ", releaseTime="
			+ releaseTime + ", nickName=" + nickName + "]";
}

}
