package org.gr.woc.po;

import java.util.Date;

public class Post {
	private int postId,userId,postProperty,resId,postCode,score;
	private int postEnterNum,scoreCount,comCount,userLevel,userStatus;
	private String userName,userPhoto,propertyName,postName,resPath;
	private String location,phone,email,gender,hobby,major,nickName,userRegion;
	private double postScore;
	private Date postTime,lastComTime;
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPostProperty() {
		return postProperty;
	}
	public void setPostProperty(int postProperty) {
		this.postProperty = postProperty;
	}
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	public int getPostCode() {
		return postCode;
	}
	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getPostEnterNum() {
		return postEnterNum;
	}
	public void setPostEnterNum(int postEnterNum) {
		this.postEnterNum = postEnterNum;
	}
	public int getScoreCount() {
		return scoreCount;
	}
	public void setScoreCount(int scoreCount) {
		this.scoreCount = scoreCount;
	}
	public int getComCount() {
		return comCount;
	}
	public void setComCount(int comCount) {
		this.comCount = comCount;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getResPath() {
		return resPath;
	}
	public void setResPath(String resPath) {
		this.resPath = resPath;
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
	public double getPostScore() {
		return postScore;
	}
	public void setPostScore(double postScore) {
		this.postScore = postScore;
	}
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	public Date getLastComTime() {
		return lastComTime;
	}
	public void setLastComTime(Date lastComTime) {
		this.lastComTime = lastComTime;
	}
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Post [PostId=" + postId + ", UserId=" + userId
				+ ", PostProperty=" + postProperty + ", ResId=" + resId
				+ ", PostCode=" + postCode + ", Score=" + score
				+ ", PostEnterNum=" + postEnterNum + ", ScoreCount="
				+ scoreCount + ", ComCount=" + comCount + ", UserLevel="
				+ userLevel + ", UserStatus=" + userStatus + ", UserName="
				+ userName + ", UserPhoto=" + userPhoto + ", PropertyName="
				+ propertyName + ", PostName=" + postName + ", ResPath="
				+ resPath + ", Location=" + location + ", Phone=" + phone
				+ ", Email=" + email + ", Gender=" + gender + ", Hobby="
				+ hobby + ", Major=" + major + ", NickName=" + nickName
				+ ", UserRegion=" + userRegion + ", PostScore=" + postScore
				+ ", PostTime=" + postTime + ", LastComTime=" + lastComTime
				+ "]";
	}
	
	
	
	

}
